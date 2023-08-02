const { createApp } = Vue;

createApp({
    data() {
        return {
            client: [],
            cards: [],
            debitCards: [],
            creditCards: [],
        }
    },
    created() {
        this.loadData()
    },
    methods: {
        loadData() {
            axios
                .get("/api/clients/current")
                .then(response => {
                    this.client = response.data;
                    this.cards = this.client.cards.sort((a, b) => b.id - a.id)
                    this.debitCards = this.cards.filter(card => card.type === "DEBIT");
                    this.creditCards = this.cards.filter(card => card.type === "CREDIT");
                    console.log(this.debitCards, this.creditCards);
                })
                .catch(error => console.log(error));
        },
        deleteCard(id) {
            axios.put(`/api/clients/current/cards/${id}`)
                .then(res => {
                    this.loadData();
                })
                .catch(err => console.log(err));
        },
        getCurrentDate() {
            const currentDate = new Date();
            const year = currentDate.getFullYear();
            const month = String(currentDate.getMonth() + 1).padStart(2, '0');
            const day = String(currentDate.getDate()).padStart(2, '0');
            
            return `${year}-${month}-${day}`;
          },
        logOut() {
            axios.post('/api/logout')
                .then(response => window.location.href = ("/assets/pages/login.html"))
                .catch(error => console.log(error))
        }
    }
}).mount('#app');