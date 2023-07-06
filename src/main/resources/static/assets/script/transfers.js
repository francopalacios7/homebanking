const { createApp } = Vue;

createApp({
  data() {
    return {
      numberOrigin: "",
      numberForeign: "",
      amount: 0.0,
      description: "",
      client: [],
      accounts: [],
      endModal: true,
      radio: ""
    }
  },
  created() {
    this.loadData();
  },
  methods: {
    loadData() {
      axios
        .get("/api/clients/current")
        .then(response => {
          this.client = response.data;
          this.accounts = this.client.accounts;
        })
        .catch(error => console.log(error));
    },
    logOut() {
      axios
        .post('/api/logout')
        .then(response => window.location.href = "/assets/pages/login.html")
        .catch(error => console.log(error));
    },
    performTransfer() {
      if (this.numberOrigin == "" || this.numberForeign == "" || this.amount == 0.0 || this.description == "") {
        this.missingData();
        this.closeModal();
      }
      else if (this.amount > this.numberOrigin.balance) {
        this.notAmount()
        this.closeModal()
      }
      else if (!this.accounts.find(account => account.number == this.numberOrigin)) {
        this.notNumber()
        this.closeModal()
      }
      else if (!this.accounts.find(account => account.number == this.numberForeign)) {
        this.notNumber()
        this.closeModal()
      }
      else {
        axios
          .post('/api/transactions', {
            "numberOrigin": this.numberOrigin,
            "numberForeign": this.numberForeign,
            "amount": this.amount,
            "description": this.description
          })
          .then(res => {
            this.loadData();
            this.endModal = document.getElementById('confirm').style.display = 'none';
            this.confirmed();
            this.numberOrigin = "";
            this.numberForeign = "";
            this.amount = 0.0;
            this.description = "";
          })
          .catch(err => console.log(err));
      }
    },
    missingData() {
      document.getElementById('missingData').style.display = 'block';
    },
    confirmTransfer() {
      document.getElementById('confirm').style.display = 'block';
    },
    notNumber() {
      document.getElementById('notNumber').style.display = 'block';
    },
    notAmount() {
      document.getElementById('notAmount').style.display = 'block';
    },
    closeModal() {
      document.getElementById('confirm').style.display = 'none';
    },
    confirmed() {
      document.getElementById('confirmed').style.display = 'block';
    },
    closeModal2() {
      document.getElementById('confirmed').style.display = 'none';
    },
    closeModal3() {
      document.getElementById('missingData').style.display = 'none';
    },
    closeModal4() {
      document.getElementById('notNumber').style.display = 'none';
    },
    closeModal5() {
      document.getElementById('notAmount').style.display = 'none';
    }
  }

}).mount("#app");



/* hay que arreglar el de numbers */

 
       