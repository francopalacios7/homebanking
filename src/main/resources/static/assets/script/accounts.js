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
                .get("http://localhost:8080/api/clients/current")
                .then(response => {
                    this.clients = response.data;
                    this.accounts = this.clients.accounts.sort((a,b) => b.id - a.id )
                    this.loans = this.clients.loans.sort((a,b) => b.id - a.id )
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



