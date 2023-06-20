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
                .get("http://localhost:8080/api/clients")
                .then(response => {
                    this.client = response.data;
                    this.cards = this.client.flatMap(client => client.cards).sort((a, b) => b.id - a.id)
                    console.log(this.cards);
                })
                .catch(error => console.log(error));
        }
    }
}).mount('#app');
