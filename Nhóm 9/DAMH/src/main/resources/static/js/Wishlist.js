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
                toastr.success('Tour added to wishlist');
            },
            error: function(xhr) {
                if (xhr.status === 409) {
                    toastr.info('Tour is already in wishlist');
                } else {
                    toastr.error('Failed to add tour to wishlist');
                }
            }
        });
    }
});
