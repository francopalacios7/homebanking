const { createApp } = Vue;

createApp({
    data() {
        return {
            email: "",
            password: ""
        };
    },
    created() {
    },
    methods: {
        logIn() {
            axios.post('/api/login',`email=${this.email}&password=${this.password}`,{headers:{'content-type':'application/x-www-form-urlencoded'}})
                .then(response => window.location.href=("/assets/pages/accounts.html"))
                .catch(error => {
                    console.error(error);
                });
        },
    }
}).mount("#app")