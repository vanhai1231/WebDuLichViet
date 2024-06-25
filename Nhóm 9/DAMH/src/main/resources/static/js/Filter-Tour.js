$(document).ready(function() {
    $(".flatpickr").flatpickr({
        dateFormat: "d/m/Y",  // Date format to match your backend processing
    });

    $('#filterButton').click(function() {
        var formData = $('#filterForm').serialize(); // Serialize form data

        $.ajax({
            url: '/api/filter', // Replace with your backend API endpoint
            type: 'GET',
            data: formData,
            success: function(response) {
                console.log('Success: ', response);

                // Clear existing tour list
                $('#tourList').empty();

                // Append filtered tours to tourList
                response.forEach(function(tour) {
                    // Extract variables from the 'tour' object
                    var mainImageUrl = tour.mainImageUrl;
                    var secondaryImageUrl = tour.secondaryImageUrl;
                    var tenTour = tour.tenTour;
                    var ngayKH = tour.ngayKH;
                    var giaTour = tour.giaTour;
                    var maTour = tour.maTour;

                    // Construct tour card HTML with Thymeleaf URLs
                    var tourCardHtml = `
                        <div class="tour-card">
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
                            <a class="dangky-btn" href="/DuLichViet/TourDetail/${maTour}" onclick="checkLogin(event)" th:text="#{register}"></a>
                        </div>
                    `;

                    // Append tour card to tourList
                    $('#tourList').append(tourCardHtml);
                });
            },
            error: function(error) {
                console.log('Error fetching filtered tours:', error);
            }
        });
    });
});
