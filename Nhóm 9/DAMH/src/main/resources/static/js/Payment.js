    <!--        //kiểm tra số điện thoại khi nhấn payment-->
            function validateAndMakePayment() {
             var addressInput = document.getElementById('address');
             var phoneNumberInput = document.getElementById('phoneNumber');
             var phoneRegex = /^[0-9]{10,11}$/; // Ví dụ: Số điện thoại từ 10 đến 12 chữ số
            var adultsInput = document.getElementById("adults");
            var childrenInput = document.getElementById("children");

            if(adultsInput <= 0 || addressInput == "" || !phoneRegex.test(phoneNumberInput.value) ||childrenInput <0 ){
            checkAddressValue();//gọi kiểm tra Address
        checkNumberValue();//gọi kiểm tra Nubmer
    checkAdultsValue();//gọi kiểm tra số người lớn
    validateChildrenInput();//gọi kiểm tra trẻ em
    return;
    }

    <!--    // Tiếp tục thực hiện thanh toán nếu hợp lệ-->
        makePayment();
    }



        function changeMainImage(src) {
            document.getElementById('mainImage').src = src;
        }

        function calculateTotal() {
            var adults = document.getElementById('adults').value;
            var pricePerAdult = parseFloat(document.getElementById('pricePerAdult').value);
            var pricePerSale= parseFloat(document.getElementById('priceSale').value);
            var totalSale = adults * pricePerSale; // tiền sale
            var total = adults * pricePerAdult; // Chỉ tính giá tiền cho người lớn
            document.getElementById('totalPrice').textContent = total.toLocaleString('vi-VN') + ' VND';
            document.getElementById('totalPriceSale').textContent = total.toLocaleString('vi-VN') + ' VND';
        }

      function makePayment() {
            var totalPriceSale = document.getElementById('totalPriceSale').textContent;
            var totalPrice = document.getElementById('totalPrice').textContent;
             var tongTienSale = parseFloat(totalPriceSale.replace(' VND', '').replace(/\./g, ''));
            var tongTien = parseFloat(totalPrice.replace(' VND', '').replace(/\./g, ''));
            var maTour = document.getElementById('${tour.maTour}').value;
            var sdt = encodeURIComponent(document.getElementById('phoneNumber').value);
            var diachi = encodeURIComponent(document.getElementById('address').value);
            var adults = document.getElementById('adults').value;
            var children = document.getElementById('children').value;

            fetch(`/api/v1/payment/${maTour}/${sdt}/${diachi}/${adults}/${children}?amount=${tongTienSale}`, {
                method: 'GET',
                headers: {
                    'Content-Type': 'application/json'
                }
            })
            .then(response => response.json())
            .then(data => {
                if (data.payUrl) {
                    window.location.href = data.payUrl;
                } else {
                    alert('Có lỗi xảy ra, vui lòng thử lại sau.');
                }
            })
            .catch(error => console.error('Error:', error));
        }
<!--        //Lỗi nhập 0 cho tre em-->
     function validateChildrenInput() {
            var childrenInput = document.getElementById("children");
            var childrenError = document.getElementById("childrenError");

            if (childrenInput.value < 0) {
                childrenError.textContent = "Số trẻ em không được nhỏ hơn 0.";
                childrenInput.setCustomValidity("Invalid");
                     // Hiển thị lỗi trong 3 giây
                setTimeout(function() {
                    childrenError.textContent = "";
                    childrenInput.setCustomValidity("");
                }, 3000);
            } else {
                childrenError.textContent = "";
                childrenInput.setCustomValidity("");
            }
        }
<!--//đổi giá trị  về 0 khi click ra ngoài -->
        function checkChildrenValue() {
            var childrenInput = document.getElementById("children");

            if (childrenInput.value < 0) {
                childrenInput.value = 0; // Đổi giá trị về 0 nếu nhỏ hơn 0
            }
        }
        //ràng buộc số người lớn
         function checkAdultsValue() {

            var adultsInput = document.getElementById("adults");
            var adultsError = document.getElementById("adultsError");
            var slot = document.getElementById("${tour.soluong}");
            if (adultsInput.value < 1) {
                adultsError.textContent = "Số người lớn không được nhỏ hơn 1.";
                adultsInput.setCustomValidity("Invalid");
                adultsInput.value = 1; // Đổi giá trị về 1 nếu nhỏ hơn 0
                setTimeout(function() {
                    adultsError.textContent = "";

                    adultsInput.setCustomValidity("");
                }, 3000);
            }else if (adultsInput.value > slot.value) {
                             adultsError.textContent = "Lỗi quá số lượng tour hiện có.";
                             adultsInput.setCustomValidity("Invalid");
                             adultsInput.value = 1; // Đổi giá trị về 1 nếu nhỏ hơn 0
                             setTimeout(function() {
                                 adultsError.textContent = "";

                                 adultsInput.setCustomValidity("");
                             }, 3000);
                         }
             else {
                adultsError.textContent = "";
                adultsInput.setCustomValidity("");
            }
        }
        //số điện thoại
        function checkNumberValue() {
        var phoneNumberInput = document.getElementById('phoneNumber');
        var phoneError = document.getElementById('phoneError');

        // Định nghĩa regex để kiểm tra định dạng số điện thoại (ví dụ đơn giản)
        var phoneRegex = /^[0-9]{10,11}$/; // Ví dụ: Số điện thoại từ 10 đến 12 chữ số

        if (!phoneRegex.test(phoneNumberInput.value)) {
            phoneError.textContent = 'Số điện thoại không hợp lệ. Vui lòng nhập lại.';
            phoneNumberInput.setCustomValidity('Invalid');
            setTimeout(function() {
                phoneError.textContent = '';
                phoneNumberInput.setCustomValidity('');
            }, 3000);
        } else {
            phoneError.textContent = '';
            phoneNumberInput.setCustomValidity('');
        }
    }
        // Hàm kiểm tra và địa chỉ
function checkAddressValue() {
    var addressInput = document.getElementById('address');
    var addressError = document.getElementById('addressError');
    var addressValue = addressInput.value.trim(); // Lấy giá trị của địa chỉ và loại bỏ các khoảng trắng đầu cuối

    if (addressValue === '') {
        addressError.textContent = 'Địa chỉ không được bỏ trống.';
        addressInput.setCustomValidity('Invalid');
        setTimeout(function() {
            addressError.textContent = '';
            addressInput.setCustomValidity('');
        }, 3000);
    } else {
        addressError.textContent = '';
        addressInput.setCustomValidity('');
    }
}
//load thì cập nhật giá
document.addEventListener('DOMContentLoaded', function() {
      // Lấy giá trị ban đầu của tour.giaTour từ priceSale
    var giaTour = parseFloat(document.getElementById('priceSale').value);

    // Định dạng giá trị sang tiền Việt Nam (VND)
    var formattedGiaTour = giaTour.toLocaleString('vi-VN');

    // Cập nhật giá trị của totalPrice
    var totalPriceElement = document.getElementById('totalPrice');
    totalPriceElement.textContent = formattedGiaTour + ' VND';

    // Cập nhật giá trị của totalPriceSale
    var totalPriceSale = document.getElementById('totalPriceSale');
    totalPriceSale.textContent = formattedGiaTour + ' VND';
});
