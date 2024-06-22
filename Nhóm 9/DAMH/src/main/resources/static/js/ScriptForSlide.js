$(document).ready(function(){
    $('.slideshow-container').slick({
        // Slick slider options can be configured here
        // For example:
        autoplay: true,
        autoplaySpeed: 1000,
        slidesToShow: 2,
        slidesToScroll: 1,
        prevArrow: '<a id="prevRegular" class="prev prev-regular">&#10094;</a>',
        nextArrow: '<a id="nextRegular" class="next next-regular">&#10095;</a>',
    });
});
