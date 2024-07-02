$(document).ready(function() {
    let wishlistId = null;

    function getWishlistId() {
        return $.ajax({
            url: '/api/wishlist/id',
            type: 'GET',
            success: function(data) {
                wishlistId = data;
            },
            error: function() {
                toastr.error('Failed to fetch wishlist ID');
            }
        });
    }

    $('.heart-icon').on('click', function() {
        const tourId = $(this).closest('.tour-card').find('.dangky-btn').data('ma-tour');
        const isNotLoggedIn = $('#isNotLoggedIn').val();

        if (isNotLoggedIn) {
            alert('Please log in to add items to your wishlist.');
            return;
        }

        if (wishlistId === null) {
            getWishlistId().done(function() {
                addTourToWishlist(tourId);
            });
        } else {
            addTourToWishlist(tourId);
        }
    });

    function addTourToWishlist(tourId) {
        toastr.options = {
            "closeButton": true,
            "positionClass": "toast-bottom-right",
            "progressBar": true,
            "timeOut": "3000", // 3s
        };
        $.ajax({
            url: '/api/wishlist/addTour',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify({ maTour: tourId, wishlistID: wishlistId }),
            success: function() {
                $('.heart-icon').addClass('active');
                toastr.success('Tour đã được thêm vào Wishlist');
            },
            error: function(xhr) {
                if (xhr.status === 409) {
                    toastr.info('Tour đã có trong Wishlist');
                } else {
                    toastr.error('Đã có lỗi khi thêm Tour vào Wishlist');
                }
            }
        });
    }

    $('.x-icon').on('click', function() {
        const tourId = $(this).closest('.tour-card').find('.dangky-btn').data('ma-tour');

        if (wishlistId === null) {
            getWishlistId().done(function() {
                removeTourFromWishlist(tourId);
            });
        } else {
            removeTourFromWishlist(tourId);
        }
    });

    function removeTourFromWishlist(tourId) {
        toastr.options = {
            "closeButton": true,
            "positionClass": "toast-bottom-right",
            "progressBar": true,
            "timeOut": "3000", // 3s
        };
        $.ajax({
            url: '/api/wishlist/removeTour',
            type: 'DELETE',
            contentType: 'application/json',
            data: JSON.stringify({ maTour: tourId, wishlistID: wishlistId }),
            success: function() {
                $(`.dangky-btn[data-ma-tour="${tourId}"]`).closest('.tour-card').remove(); // Remove the tour card from the DOM
                toastr.success('Tour đã được xóa khỏi Wishlist');
            },
            error: function(xhr) {
                if (xhr.status === 404) {
                    toastr.info('Tour không tồn tại trong Wishlist');
                } else {
                    toastr.error('Đã có lỗi khi xóa Tour khỏi Wishlist');
                    console.error('Error removing tour from wishlist:', xhr.responseText); // Log server error response
                }
            }
        });
    }
});
