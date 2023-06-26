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
                .then(response => window.location.href=("/assets/pages/accounts.html"))
                .catch(error => {
                    console.error(error);
                });
        },
    }
}).mount("#app")