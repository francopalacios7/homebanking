const { createApp } = Vue;

createApp({
    data() {
      return {
        account: [],
        param: "",
        transactions: [],
        amount: []
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
            this.amount = this.transactions.amount
            this.amount = new Intl.NumberFormat('en-US',{
              style: 'currency',
              currency: 'USD'

            })
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
  

