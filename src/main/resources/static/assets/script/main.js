const { createApp } = Vue;

createApp({
  data() {
    return {
      clients: [],
      restResponse: "",
      clientData: {
        firstName: "",
        lastName: "",
        email: ""
      }
    };
  },

  created() {
    this.loadData();
    this.postClient();
  },

  methods: {
    loadData() {
      axios.get("http://localhost:8080/rest/clients")
        .then(res => {
          this.clients = res.data._embedded.clients
          this.restResponse = this.clients;
          console.log(this.clients);
        })
        .catch(error => console.log(error));
    },

    addClient() {
      this.postClient();
    },

    postClient() {
      axios.post("http://localhost:8080/rest/clients/", this.clientData)
        .then(res => {
          this.loadData();
        })
        .catch(error => console.log(error));
    },

    deleteClient(id) {
      axios.delete(id)
        .then(res => {
          console.log(res = this.id);
          this.loadData();
        })
        .catch(error => console.log(error));
    },

    editClient(clientUrl) {
      axios.put(clientUrl, this.clientData)
        .then(res => {
          console.log(res);
          this.clientData.firstName = "";
          this.clientData.lastName = "";
          this.clientData.email = "";
          this.loadData();
        })
        .catch(error => console.log(error));
    }
  }
}).mount('#app');
