const { createApp } = Vue;

createApp({
  data() {
    return {
      transfer: {
        numberOrigin: "",
        numberForeign: "",
        amount: 0.0,
        description: ""
      },
      client: [],
      accounts: [],
      endModal: true,
    }
  },
  created() {
    this.loadData();
  },
  methods: {
    loadData() {
      axios
        .get("http://localhost:8080/api/clients/current")
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
      if (this.transfer.numberOrigin === "" || this.transfer.numberForeign === "" || this.transfer.amount === 0 || this.transfer.description === "") {
        this.missingData();
        this.closeModal();
      }

      /* else if (this.transfer.amount > this.accounts.amount) {
        this.notAmount()
        this.closeModal()
      } */

       /* else if (this.accounts.number != this.transfer.numberOrigin) {
        this.notNumber()
        this.closeModal()
      }  */
      
      /* hay que arreglar estos dos */
      
      else {
        axios
          .post('/api/transactions', this.transfer)
          .then(res => {
            this.loadData();
            this.endModal = document.getElementById('confirm').style.display = 'none';
            this.transfer.numberOrigin = "";
            this.transfer.numberForeign = "";
            this.transfer.amount = 0;
            this.transfer.description = "";
            this.confirmed();
          })
          .catch(err => console.log(err));
      }
    },
    missingData() {
      document.getElementById('missingData').style.display = 'block';
    },
     /* notNumber() {
      document.getElementById('notNumber').style.display = 'block';
    }, */ 
    confirmTransfer() {
      document.getElementById('confirm').style.display = 'block';
    },
    /* notAmount() {
      document.getElementById('notAmount').style.display = 'block';
    }, */
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
    }/* ,
    closeModal5() {
      document.getElementById('notAmount').style.display = 'none';
    }  */
  }

}).mount("#app");