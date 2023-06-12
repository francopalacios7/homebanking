const { createApp } = Vue;

createApp({
    data() {
      return {
        account: [],
        param: "",
        transactions: []
      }
    },
    created() {
      this.loadData();
    },
    methods: {
      loadData() {
        this.param = new URLSearchParams(document.location.search).get("id");
        axios
          .get(`http://localhost:8080/api/accounts/${this.param}`)
          .then((response) => {
            this.account = response.data;
            console.log(this.account);
            this.transactions = this.account.transactions
            this.transactions.sort((a,b) => b.id - a.id )
          })
          .catch((error) => console.log(error));
      }
    }
  }).mount('#app');
  

