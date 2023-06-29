const { createApp } = Vue;

createApp({
    data() {
        return {
            firstName: "",
            lastName: "",
            email: "",
            password: ""
        };
    },
    created() {
    },
    methods: {
        signUp() {
            axios.post('/api/clients',`firstName=${this.firstName}&lastName=${this.lastName}&email=${this.email}&password=${this.password}`,{headers:{'content-type':'application/x-www-form-urlencoded'}})
                .then(response => {
                    axios.post('/api/login',`email=${this.email}&password=${this.password}`)
                    .then(res => window.location.href="/assets/pages/accounts.html")
                    .catch(err => console.log(err))
                })
                .catch(error => {
                    console.error(error);
                })
        }
    }
}).mount("#app")