const { createApp } = Vue;

createApp({
    data() {
        return {
            logged: true
        };
    },
    created() {
        
    },
    methods: {
            logged(){
                axios.get("http://localhost:8080/api/clients/current")
                .then(response => this.logged)
                .catch(error => this.logged = false)
            },
            logOut() {
                axios.post('/api/logout')
                .then(response => window.location.href=("/assets/pages/login.html"))
                .catch(error => console.log(error))
            }
    }
}).mount('#app');