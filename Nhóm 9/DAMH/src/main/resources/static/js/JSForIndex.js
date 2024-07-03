document.addEventListener('DOMContentLoaded', (event) => {
    document.querySelector('.support').addEventListener('click', function() {
        document.getElementById('chat-window').style.display = 'block';
    });

    document.querySelector('.close-btn').addEventListener('click', function() {
        document.getElementById('chat-window').style.display = 'none';
    });
});

window.addEventListener('DOMContentLoaded', (event) => {
    flatpickr("input[type=date]", {
        position: "below", // Đảm bảo lịch luôn nằm dưới
        dateFormat: "Y-m-d",

    });
});
