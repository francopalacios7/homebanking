const { createApp } = Vue;

createApp({
    data() {
        return {
            client: [],
            loans: [],
            accounts: [],
            endModal: true,
            name: "",
            maxAmount: 0,
            payments: "",
            percentage: 0

        }
    },
    created() {
    },
    methods: {
        logOut() {
            axios
                .post('/api/logout')
                .then(response => window.location.href = "/assets/pages/login.html")
                .catch(error => console.log(error));
        },
        performLoan() {
            if (this.maxAmount === "" || this.payments === 0 || this.name === "" || this.percentage === 0) {
                this.missingData();
                this.closeModal();
            }
            else if(this.maxAmount <= 0){
                this.notAmount()
                this.closeModal()
            }
            else {
                const payments = this.payments.split(',').map(Number);
                axios
                    .post('/api/admin/loans', {
                        "name": this.name,
                        "maxAmount": this.maxAmount,
                        "payments": payments,
                        "percentage": this.percentage
                    })
                    .then(res => {
                        console.log("hola");
                        this.maxAmount = null;
                        this.payments = null;
                        this.name = "";
                        this.percentage = null;
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