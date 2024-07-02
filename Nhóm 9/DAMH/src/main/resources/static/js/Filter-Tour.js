$(document).ready(function() {
    $(".flatpickr").flatpickr({
        dateFormat: "d/m/Y",
    });

    $('#filterButton').click(function() {
        var formData = $('#filterForm').serialize();

        $.ajax({
            url: '/api/filter',
            type: 'POST',
            data: formData,
            success: function(response) {
                $('#tourList').empty();

                response.forEach(function(tour) {
                    var mainImageUrl = tour.mainImageUrl;
                    var secondaryImageUrl = tour.secondaryImageUrl;
                    var tenTour = tour.tenTour;
                    var ngayKH = tour.ngayKH;
                    var giaTour = tour.giaTour;
                    var maTour = tour.maTour;

                    var tourCardHtml =
                        `<div class="tour-card">
                            <div class="tour-image-container">
                                <img src="${mainImageUrl}" alt="Main Tour Image" class="tour-image">
                                <img src="${secondaryImageUrl}" alt="Secondary Tour Image" class="tour-image secondary-image">
                                <span><i class="bi bi-heart"></i></span>
                            </div>
                            <div class="tour-info">
                                <h2 class="tour-name">${tenTour}</h2>
                                <span class="star-icon">⭐⭐⭐⭐⭐</span>
                                <p class="tour-date">Ngày khởi hành: ${ngayKH}</p>
                                <p class="price">Giá: ${giaTour}</p>
                            </div>
                            <a class="dangky-btn" href="/DuLichViet/TourDetail/${maTour}" onclick="checkLogin(event)">Đăng ký</a>
                        </div>`;

                    $('#tourList').append(tourCardHtml);
                });
            },
            error: function(error) {
                console.log('Error fetching filtered tours:', error);
            },
            complete: function() {
                // Re-apply Flatpickr if needed
                $(".flatpickr").flatpickr({
                    dateFormat: "d/m/Y",
                });
            }
        });
    });
});
