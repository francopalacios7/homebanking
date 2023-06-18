const { createApp } = Vue;

createApp({
    data() {
        return {
            clients: [],
            accounts: [],
            loans: []
        };
    },
    created() {
        this.loadData();
    },
    methods: {
        loadData() {
            axios
                .get("http://localhost:8080/api/clients")
                .then(response => {
                    this.clients = response.data;
                    this.accounts = this.clients.flatMap(client => client.accounts);
                    this.loans = this.clients.flatMap(client => client.loans);
                })
                .catch(error => console.log(error));
        }
    }
}).mount('#app');



