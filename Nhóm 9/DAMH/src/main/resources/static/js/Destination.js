 function filterByProvince() {
           const provinceId = document.getElementById("province").value;

           const xhr = new XMLHttpRequest();
           xhr.open("POST", "/DuLichViet/FilterDestination"); // Thay đổi đường dẫn
           xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

           // Gửi dữ liệu tỉnh được chọn
           xhr.send("province=" + provinceId);

           xhr.onload = function() {
             if (xhr.status === 200) {
               // Cập nhật nội dung HTML với dữ liệu mới
               const response = JSON.parse(xhr.responseText);
               const attractions = response.attractions;

               // Cập nhật danh sách điểm tham quan
               updateAttractions(attractions);
             } else {
               console.error("Lỗi khi lọc điểm đến:", xhr.statusText);
             }
           };
         }

          // Hàm cập nhật danh sách điểm tham quan
          function updateAttractions(attractions) {
            const attractionsContainer = document.querySelector(".main-attractions");
            attractionsContainer.innerHTML = ""; // Xóa nội dung cũ

            attractions.forEach(attraction => {
              const attractionElement = createAttractionElement(attraction);
              attractionsContainer.appendChild(attractionElement);
            });
          }

          // Hàm tạo phần tử HTML cho điểm tham quan
          function createAttractionElement(attraction) {
            const attractionElement = document.createElement("div");
            attractionElement.classList.add("attraction");

            const h3Element = document.createElement("h3");
            h3Element.textContent = attraction.tenDiemDen;
            attractionElement.appendChild(h3Element);

            const imgElement = document.createElement("img");
            imgElement.src = attraction.hinhAnh;
            imgElement.alt = "Hình ảnh điểm tham quan";
            attractionElement.appendChild(imgElement);

            const pElement = document.createElement("p");
            pElement.textContent = attraction.moTa;
            attractionElement.appendChild(pElement);

            const socialIconsElement = document.createElement("div");
            socialIconsElement.classList.add("social-icons");

            const thumbsUpIcon = document.createElement("i");
            thumbsUpIcon.classList.add("fas", "fa-thumbs-up");
            socialIconsElement.appendChild(thumbsUpIcon);

            const heartIcon = document.createElement("i");
            heartIcon.classList.add("fas", "fa-heart");
            socialIconsElement.appendChild(heartIcon);

            const commentIcon = document.createElement("i");
            commentIcon.classList.add("fas", "fa-comment");
            socialIconsElement.appendChild(commentIcon);

            attractionElement.appendChild(socialIconsElement);

            return attractionElement;
          }

          // Hàm cập nhật danh sách tỉnh (tùy chọn)
          function updateTinhList(tinhList) {
            const provinceSelect = document.getElementById("province");
            provinceSelect.innerHTML = ""; // Xóa nội dung cũ

            tinhList.forEach(tinh => {
              const optionElement = document.createElement("option");
              optionElement.value = tinh.maTinh;
              optionElement.textContent = tinh.tenTinh;
              provinceSelect.appendChild(optionElement);
            });
          }
