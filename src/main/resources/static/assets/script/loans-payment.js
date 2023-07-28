const { createApp } = Vue;

createApp({
    data() {
        return {
            accountNumber: [],
            client: [],
            accounts: [],
            accountsActive: [],
            clientLoans: [],
            selectedLoan: "",
            id: ""
        }
    },
    created() {
        this.loadData();
        this.getLoans()
    },
    methods: {
        loadData() {
            axios
                .get("/api/clients/current")
                .then(response => {
                    this.client = response.data;
                    this.accounts = this.client.accounts;
                    this.accountsActive = this.accounts.filter(account => account.active)
                    this.clientLoans = this.client.loans;
                })
                .catch(error => console.log(error));
        },
        getLoans() {
            axios.get("/api/loans")
                .then(res => {
                    this.loans = res.data;
                })
        },
        logOut() {
            axios
                .post('/api/logout')
                .then(response => window.location.href = "/assets/pages/login.html")
                .catch(error => console.log(error));
        },
        performLoanPayment() {
            this.id = this.selectedLoan.id
            if (this.accountNumber === ""|| this.selectedLoan === "") {
                this.missingData()
                this.closeModal()
            }
            if (this.accountsActive.map(account => account.balance) < this.selectedLoan.feeToPay) {
                this.notAmount()
                this.closeModal()
            }
                axios
                    .post('/api/clients/current/loans', `accountNumber=${this.accountNumber}&id=${this.id}`)
                    .then(res => {
                        this.loadData();
                        this.accountNumber = "";
                        this.selectedLoan = "";
                        this.confirmed();
                    })
                    .catch(err => console.log(err.response.data));
        },
        missingData() {
            document.getElementById('missingData').style.display = 'block';
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
            document.getElementById('notAmount').style.display = 'none';
          }
    }

}).mount("#app");