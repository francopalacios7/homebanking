const { createApp } = Vue;

createApp({
    data() {
      return {
        account: [],
        param: "",
        transactions: [],
        amount: [],
        accounts: [],
        client: []
      }
    },
    created() {
      this.loadData();
    },
    methods: {
      loadData() {
        this.param = new URLSearchParams(document.location.search).get("id");
        axios
          .get(`http://localhost:8080/api/clients/current`)
          .then((response) => {
            this.client = response.data
            this.account = this.client.accounts.find(account => account.id == this.param)
            this.transactions = this.account.transactions
            this.transactions.sort((a,b) => b.id - a.id )
            this.amount = this.transactions.amount
            this.amount = new Intl.NumberFormat('en-US',{
              style: 'currency',
              currency: 'USD'
            }) 
            console.log(response);
          })
          .catch((error) => console.log(error));
      },
      logOut() {
        axios.post('/api/logout')
        .then(response => window.location.href=("/assets/pages/login.html"))
        .catch(error => console.log(error))
    }
    }
  }).mount('#app');
  

