const { createApp } = Vue;

createApp({
    data() {
        return {
            loggedUser: true
        };
    },
    created() {
        this.logged()
    },
    methods: {
            logged(){
                axios.get("http://localhost:8080/api/clients/current")
                .then(response => this.loggedUser)
                .catch(error => this.loggedUser = false)
            },
            logOut() {
                axios.post('/api/logout')
                .then(response => window.location.href=("/assets/pages/login.html"))
                .catch(error => console.log(error))
            },
            moreInfo(){
                document.getElementById('info-modal').style.display = 'block';
            },
            closeModal() {
                document.getElementById('info-modal').style.display = 'none';
            },
            moreInfo2(){
                document.getElementById('info-modal2').style.display = 'block';
            },
            closeModal2() {
                document.getElementById('info-modal2').style.display = 'none';
            },
            moreInfo3(){
                document.getElementById('info-modal3').style.display = 'block';
            },
            closeModal3() {
                document.getElementById('info-modal3').style.display = 'none';
            },
            moreInfo4(){
                document.getElementById('info-modal4').style.display = 'block';
            },
            closeModal4() {
                document.getElementById('info-modal4').style.display = 'none';
            },
            moreInfo5(){
                document.getElementById('info-modal5').style.display = 'block';
            },
            closeModal5() {
                document.getElementById('info-modal5').style.display = 'none';
            },
            moreInfo6(){
                document.getElementById('info-modal6').style.display = 'block';
            },
            closeModal6() {
                document.getElementById('info-modal6').style.display = 'none';
            },
            moreInfo7(){
                document.getElementById('info-modal7').style.display = 'block';
            },
            closeModal7() {
                document.getElementById('info-modal7').style.display = 'none';
            }
    }
}).mount('#app');