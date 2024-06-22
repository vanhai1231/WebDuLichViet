
document.addEventListener("DOMContentLoaded", function() {
    var loginModal = document.getElementById('loginModal');
    var registerModal = document.getElementById('registerModal');

    if (loginModal) {
        loginModal.addEventListener('show.bs.modal', function () {
            loginModal.classList.add('slide-down');
        });

        loginModal.addEventListener('hidden.bs.modal', function () {
            loginModal.classList.remove('slide-down');
        });
    }

    if (registerModal) {
        registerModal.addEventListener('show.bs.modal', function () {
            registerModal.classList.add('slide-down');
        });

        registerModal.addEventListener('hidden.bs.modal', function () {
            registerModal.classList.remove('slide-down');
        });
    }
});

