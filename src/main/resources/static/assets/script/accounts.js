const { createApp } = Vue;

createApp({
    data() {
        return {
            clients: [],
            accounts: []
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
                })
                .catch(error => console.log(error));
        }
    }
}).mount('#app');



