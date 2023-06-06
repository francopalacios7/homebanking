const { createApp } = Vue;

createApp({
    data(){
        return{
            clients: [],
        }
    },
    created(){
        this.loadData()
    },
    methods: {
        loadData() {
            axios.get("http://localhost:8080/api/clients/2")
              .then(response => {
                this.clients = response.data;
                console.log(this.clients);
              })
              .catch(error => console.log(error));
    }
}
}).mount('#app');


