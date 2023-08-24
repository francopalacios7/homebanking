const { createApp } = Vue;

createApp({
    data() {
        return {
            clients: [],
            accounts: [],
            loans: [],
            type: "",
            accountsActive: []
        };
    },
    created() {
        this.loadData();
    },
    methods: {
        loadData() {
            axios
                .get("/api/clients/current")
                .then(response => {
                    this.clients = response.data;
                    this.accounts = this.clients.accounts.sort((a,b) => b.id - a.id ).filter(account => account.active == true)
                    this.loans = this.clients.loans.sort((a,b) => b.id - a.id )
                })
                .catch(error => console.log(error));
        },
        logOut() {
            axios.post('/api/logout')
            .then(response => window.location.href=("/assets/pages/login.html"))
            .catch(error => console.log(error))
        },
        createAccount(){
            console.log(this.type);
            axios.post('/api/clients/current/accounts','type=' + this.type)
            .then(response => {
                this.loadData()
            })
            .catch(error => console.log(error))
        },
        deleteAccount(id){
            axios.put(`/api/clients/current/accounts/${id}`)
            .then(res => {
                this.loadData()
            })
            .catch(err => console.log(err))
        },
        accountType(){
            document.getElementById('account-type').style.display = 'block';
        },
        closeModal() {
            document.getElementById('account-type').style.display = 'none';
        }
    }
}).mount('#app');



