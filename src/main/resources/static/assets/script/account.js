const { createApp } = Vue;

createApp({
    data() {
        return {
            account: []
        }
    },
    created() {
        this.loadData();
    },
    methods: {
        loadData() {
            axios.get("http://localhost:8080/api/accounts/2")
              .then(response => {
                this.account = response.data;
                console.log(this.account);
              })
              .catch(error => console.log(error));
        }
    }
}).mount('#app');

