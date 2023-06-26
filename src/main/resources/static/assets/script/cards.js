const { createApp } = Vue;

createApp({
    data() {
        return {
            client: [],
            cards: []
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
        }
    }
}).mount('#app');
