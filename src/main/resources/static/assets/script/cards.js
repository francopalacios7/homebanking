const { createApp } = Vue;

createApp({
    data() {
        return {
            client: [],
            cards: [],
            debitCards: [],
            creditCards: []
        }
    },
    created() {
        this.loadData()
    },
    methods: {
        loadData() {
            axios
                .get("http://localhost:8080/api/clients/current")
                .then(response => {
                    this.client = response.data;
                    this.cards = this.client.cards.sort((a, b) => b.id - a.id)
                    this.debitCards = this.cards.filter(card => card.type == "DEBIT")
                    this.creditCards = this.cards.filter(card => card.type == "CREDIT")
                    console.log(this.debitCards, this.creditCards);
                })
                .catch(error => console.log(error));
        },
        logOut() {
            axios.post('/api/logout')
            .then(response => window.location.href=("/assets/pages/login.html"))
            .catch(error => console.log(error))
        }
    }
}).mount('#app');
