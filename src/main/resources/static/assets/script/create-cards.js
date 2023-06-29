const { createApp } = Vue;

createApp({
    data() {
        return {
            client: [],
            cards: [],
            cardType: "",
            cardColor: ""
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
                })
                .catch(error => console.log(error));
        },
        logOut() {
            axios.post('/api/logout')
            .then(response => window.location.href=("/assets/pages/login.html"))
            .catch(error => console.log(error))
        },
        createCard(){
            axios.post('/api/clients/current/cards',`type=${this.cardType}&color=${this.cardColor}`)
            .then(res => {
                this.loadData();
                window.location.href=("/assets/pages/cards.html")
            })
            .catch(err => console.log(err))
        }
    }
}).mount('#app');