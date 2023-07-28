const { createApp } = Vue;

createApp({
    data() {
        return {
            amount: null,
            payment: null,
            accountNumber: "",
            selectedLoan: [],
            client: [],
            accounts: [],
            loans: [],
            endModal: true,
            accountsActive: []
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
                    this.accountsActive = this.accounts.filter(account => account.active == true)
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
        performLoan() {
            if (this.amount === "" || this.payment === 0 || this.accountNumber === "" || this.name === "") {
                this.missingData();
                this.closeModal();
            }
            else if (this.amount > this.selectedLoan.maxAmount) {
                this.notAmount()
                this.closeModal()
            }
            else if(this.amount <= 0){
                this.notAmount()
                this.closeModal()
            }
            else {
                axios
                    .post('/api/loans', {
                        "amount": this.amount,
                        "payment": this.payment,
                        "accountNumber": this.accountNumber,
                        "name": this.selectedLoan.name
                    })
                    .then(res => {
                        this.loadData();
                        this.endModal = document.getElementById('confirm').style.display = 'none';
                        this.amount = null;
                        this.payment = null;
                        this.accountNumber = "";
                        this.name = "";
                        this.confirmed();
                    })
                    .catch(err => console.log(err));
            }
        },
        missingData() {
            document.getElementById('missingData').style.display = 'block';
        },
        confirmLoan() {
            document.getElementById('confirm').style.display = 'block';
        },
        notAmount() {
            document.getElementById('notAmount').style.display = 'block';
        },
        notNegativeAmount(){
            document.getElementById('notNegativeAmount').style.display = 'block';
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
        closeModal5() {
          document.getElementById('notAmount').style.display = 'none';
        }
    }

}).mount("#app");