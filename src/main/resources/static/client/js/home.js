var manufacturerElements = document.querySelectorAll('.src-manufacturer');
for (var i = 0; i < manufacturerElements.length; i++) {
    var srcValue = manufacturerElements[i].getAttribute("src");
    var lastIndex = srcValue.lastIndexOf("/");
    var folderPath = srcValue.substring(0, lastIndex);
    var folderPathTemp = folderPath.replaceAll("/", ".");
    var fileImageName = srcValue.substring(lastIndex + 1);
    manufacturerElements[i].src = "/get-image/" + folderPathTemp + "/" + fileImageName;
}

var categoryElements = document.querySelectorAll('.src-category');
for (var i = 0; i < categoryElements.length; i++) {
    var srcValue = categoryElements[i].getAttribute("src");
    var lastIndex = srcValue.lastIndexOf("/");
    var folderPath = srcValue.substring(0, lastIndex);
    var folderPathTemp = folderPath.replaceAll("/", ".");
    var fileImageName = srcValue.substring(lastIndex + 1);
    categoryElements[i].src = "/get-image/" + folderPathTemp + "/" + fileImageName;
}

var productElements = document.querySelectorAll('.src-product');
for (var i = 0; i < productElements.length; i++) {
    var srcValue = productElements[i].getAttribute("src");
    var lastIndex = srcValue.lastIndexOf("/");
    var folderPath = srcValue.substring(0, lastIndex);
    var folderPathTemp = folderPath.replaceAll("/", ".");
    var fileImageName = srcValue.substring(lastIndex + 1);
    productElements[i].src = "/get-image/" + folderPathTemp + "/" + fileImageName;
}

var galleryElements = document.querySelectorAll('.src-gallery');
for (var i = 0; i < galleryElements.length; i++) {
    var srcValue = galleryElements[i].getAttribute("src");
    var lastIndex = srcValue.lastIndexOf("/");
    var folderPath = srcValue.substring(0, lastIndex);
    var folderPathTemp = folderPath.replaceAll("/", ".");
    var fileImageName = srcValue.substring(lastIndex + 1);
    galleryElements[i].src = "/get-image/" + folderPathTemp + "/" + fileImageName;
}

var productOptionsImgElements = document.querySelectorAll('.src-product-options-img');
for (var i = 0; i < productOptionsImgElements.length; i++) {
    var srcValue = productOptionsImgElements[i].getAttribute("src");
    var lastIndex = srcValue.lastIndexOf("/");
    var folderPath = srcValue.substring(0, lastIndex);
    var folderPathTemp = folderPath.replaceAll("/", ".");
    var fileImageName = srcValue.substring(lastIndex + 1);
    productOptionsImgElements[i].src = "/get-image/" + folderPathTemp + "/" + fileImageName;
}

var galleryLargeElements = document.querySelectorAll('.src-gallery-large');
for (var i = 0; i < galleryLargeElements.length; i++) {
    var srcValue = galleryLargeElements[i].getAttribute("src");
    var lastIndex = srcValue.lastIndexOf("/");
    var folderPath = srcValue.substring(0, lastIndex);
    var folderPathTemp = folderPath.replaceAll("/", ".");
    var fileImageName = srcValue.substring(lastIndex + 1);
    galleryLargeElements[i].src = "/get-image/" + folderPathTemp + "/" + fileImageName;
}

var imgManufacturerElements = document.querySelectorAll('.img-manufacturer-more');
for (var i = 0; i < imgManufacturerElements.length; i++) {
    var srcValue = imgManufacturerElements[i].getAttribute("src");
    var lastIndex = srcValue.lastIndexOf("/");
    var folderPath = srcValue.substring(0, lastIndex);
    var folderPathTemp = folderPath.replaceAll("/", ".");
    var fileImageName = srcValue.substring(lastIndex + 1);
    imgManufacturerElements[i].src = "/get-image/" + folderPathTemp + "/" + fileImageName;
}

var imgPostElements = document.querySelectorAll('.src-post');
for (var i = 0; i < imgPostElements.length; i++) {
    var srcValue = imgPostElements[i].getAttribute("src");
    var lastIndex = srcValue.lastIndexOf("/");
    var folderPath = srcValue.substring(0, lastIndex);
    var folderPathTemp = folderPath.replaceAll("/", ".");
    var fileImageName = srcValue.substring(lastIndex + 1);
    imgPostElements[i].src = "/get-image/" + folderPathTemp + "/" + fileImageName;
}

// Lấy tất cả các phần tử product-options-div
    var productOptionsDivs = document.querySelectorAll('.product-options-div');

// Lặp qua từng phần tử
    productOptionsDivs.forEach(function (productOptionDiv) {
        // Thêm sự kiện click cho mỗi phần tử
        productOptionDiv.addEventListener('click', function () {
            // Loại bỏ lớp "selected" từ tất cả các phần tử
            productOptionsDivs.forEach(function (item) {
                item.classList.remove('selected');
            });

            // Thêm lớp "selected" cho phần tử được chọn
            productOptionDiv.classList.add('selected');
        });
    });

    function changeLargeImage(thumbnail) {
        var largeImage = document.getElementById("large-image");
        largeImage.src = thumbnail.src;
    }

    document.addEventListener("DOMContentLoaded", function() {
        var productDiscountValueDetail = parseFloat(document.getElementById('product-discount-value-hidden').value);
        var productDiscountTypeDetail = document.getElementById('product-discount-type-hidden').value;
        var productPriceDetail = parseFloat(document.getElementById('product-price-hidden').value);

        if (productDiscountTypeDetail === 'AMOUNT') {
            var productPriceDetailNew = productPriceDetail - productDiscountValueDetail;
            productPriceDetail = productPriceDetail.toLocaleString('vi-VN', {style: 'currency', currency: 'VND'});
            productPriceDetailNew = productPriceDetailNew.toLocaleString('vi-VN', {style: 'currency', currency: 'VND'});
            productDiscountValueDetail = productDiscountValueDetail.toLocaleString('vi-VN', {style: 'currency', currency: 'VND'});
            var result = 'Giá thị trường: ' + productPriceDetail + ' - Tiết kiệm: ' + productDiscountValueDetail + '<span style="color:  #FF9689;">(-' + productDiscountValueDetail + ')</span>';
            document.querySelector('.product-price-detail').innerText = productPriceDetailNew;
            document.querySelector('.product-price-sale-detail').innerHTML = result;
        } else if (productDiscountTypeDetail === 'PERCENTAGE') {
            var productPriceDetailNew = productPriceDetail - (productPriceDetail * (productDiscountValueDetail / 100));
            var productPriceDetailNew1 = productPriceDetail * (productDiscountValueDetail / 100);
            productPriceDetail = productPriceDetail.toLocaleString('vi-VN', {style: 'currency', currency: 'VND'});
            productPriceDetailNew = productPriceDetailNew.toLocaleString('vi-VN', {style: 'currency', currency: 'VND'});
            productPriceDetailNew1 = productPriceDetailNew1.toLocaleString('vi-VN', {style: 'currency', currency: 'VND'});
            var result = 'Giá thị trường: ' + productPriceDetail + ' - Tiết kiệm: ' + productPriceDetailNew1 + '<span style="color:  #FF9689;">(-' + productDiscountValueDetail + '%)</span>';
            document.querySelector('.product-price-detail').innerText = productPriceDetailNew;
            document.querySelector('.product-price-sale-detail').innerHTML = result;
        } else {
            productPriceDetail = productPriceDetail.toLocaleString('vi-VN', {style: 'currency', currency: 'VND'});
            document.querySelector('.product-price-detail').innerText = productPriceDetail;
        }
    })

    function changeLargeImageProductOptions(selectedDiv) {
        var imgSrc = selectedDiv.querySelector('.src-product-options-img').getAttribute('src');
        document.getElementById('large-image').setAttribute('src', imgSrc);
        document.getElementById("quantity").value = 1;
        var id = selectedDiv.querySelector('.id-product-option-hidden').getAttribute('value');
        var image = selectedDiv.querySelector('.image-product-option-hidden').getAttribute('value');
        var name = selectedDiv.querySelector('.name-product-option-hidden').getAttribute('value');
        var price = selectedDiv.querySelector('.price-product-option-hidden').getAttribute('value');
        var discountPrice = selectedDiv.querySelector('.discount-price-product-hidden').getAttribute('value');
        var discountType = selectedDiv.querySelector('.discount-type-product-hidden').getAttribute('value');
        var amount = selectedDiv.querySelector('.amount-product-option-hidden').getAttribute('value');
        var quantityInput = document.getElementById('quantity');
        quantityInput.setAttribute('data-amount_product_option', amount);
        document.getElementById("id-product-option-cart").value = id;
        document.getElementById("image-product-option-cart").value = image;
        document.getElementById("name-product-option-cart").value = name;
        document.getElementById("price-product-option-cart").value = price;
        document.getElementById("amount-product-option-cart").value = amount;
        var buttonElementMinus = document.querySelector('.btn-minus');
        var buttonElementPlus = document.querySelector('.btn-plus');
        buttonElementMinus.setAttribute('data-product_option_id', id);
        buttonElementPlus.setAttribute('data-product_option_id', id);

        discountPrice = parseFloat(discountPrice);
        price = parseFloat(price);
        if (discountType === 'AMOUNT') {
            var priceNew = price - discountPrice;
            var formattedPriceNew = priceNew.toLocaleString('vi-VN', {style: 'currency', currency: 'VND'});
            var formattedPrice = price.toLocaleString('vi-VN', {style: 'currency', currency: 'VND'});
            var formattedDiscountValue = discountPrice.toLocaleString('vi-VN', {style: 'currency', currency: 'VND'});
            var result = 'Giá thị trường: ' + formattedPrice + ' - Tiết kiệm: ' + formattedDiscountValue + '<span style="color:  #FF9689;">(-' + formattedDiscountValue + ')</span>';
            document.querySelector('.product-price-sale-detail').innerHTML = result;
            document.querySelector('.product-price-detail').innerHTML = formattedPriceNew;
        } else if (discountType === 'PERCENTAGE') {
            var priceNew = price - (price * (discountPrice/100));
            var tietkiem = price - priceNew;
            var formattedPrice = price.toLocaleString('vi-VN', {style: 'currency', currency: 'VND'});
            var formattedDiscountValue = priceNew.toLocaleString('vi-VN', {style: 'currency', currency: 'VND'});
            var tietkiemvnd = tietkiem.toLocaleString('vi-VN', {style: 'currency', currency: 'VND'});
            var result = 'Giá thị trường: ' + formattedPrice + ' - Tiết kiệm: ' + tietkiemvnd + '<span style="color:  #FF9689;">(-' + discountPrice + '%)</span>';
            document.querySelector('.product-price-sale-detail').innerHTML = result;
            document.querySelector('.product-price-detail').innerHTML = formattedDiscountValue;
        } else {
            var formattedPriceValue = price.toLocaleString('vi-VN', {style: 'currency', currency: 'VND'});
            document.querySelector('.product-price-detail').innerHTML = formattedPriceValue;
        }
    }

    $(document).ready(function () {
        $('.choose-city').on('change', function () {
            var city_id = $(this).val();
            $.ajax({
                type: 'GET',
                url: '/get-all-district-by-city-id',
                data: {city_id: city_id},
                success: function (data) {
                    var result = '<option value="0">Chọn quận, huyện</option>';
                    for (var i = 0; i < data.length; i++) {
                        result = result + '<option value="' + data[i].district_id + '">' + data[i].district_name + '</option>'
                    }
                    $('#district').html(result);
                    $('#district1').html(result);
                    $('#district2').html(result);
                    // $('#wards').html('<option th:value="0">Chọn xã, thị trấn</option>');
                },
                error: function (xhr, status, error) {

                }
            });
        });
    });

    $(document).ready(function () {
        $('.choose-district').on('change', function () {
            var district_id = $(this).val();
            $.ajax({
                type: 'GET',
                url: '/get-all-ward-by-district-id',
                data: {district_id: district_id},
                success: function (data) {
                    var result = '<option value="0">Chọn xã, phường</option>';
                    for (var i = 0; i < data.length; i++) {
                        result = result + '<option value="' + data[i].ward_id + '">' + data[i].ward_name + '</option>'
                    }
                    $('#ward').html(result);
                    $('#ward1').html(result);
                    $('#ward2').html(result);
                },
                error: function (xhr, status, error) {

                }
            });
        });
    });

// Get the modal
    var modalLogin = document.getElementById('id01');

// When the user clicks anywhere outside of the modal, close it
    window.onclick = function (event) {
        if (event.target == modalLogin) {
            modalLogin.style.display = "none";
        }
    }

// Hàm mở modal đăng kí
    function openRegisterModal() {
        document.getElementById('id01').style.display = 'none';
        document.getElementById('id02').style.display = 'block';
    }

// Hàm mở modal đăng nhập
    function openLoginModal() {
        document.getElementById('id01').style.display = 'block';
        document.getElementById('id02').style.display = 'none';
    }

    $(document).ready(function () {
        $(document).on('click', '#show-more-btn', function () {
            $('.description-product-content').css('max-height', 'none');
            $(this).text('Ẩn bớt thông tin');
            $(this).attr('id', 'hide-more-btn');
        });

        $(document).on('click', '#hide-more-btn', function () {
            $('.description-product-content').css('max-height', '894px');
            $(this).text('Xem thêm thông tin');
            $(this).attr('id', 'show-more-btn');
        });
    });

    const stars = document.querySelectorAll('.rating .star');
    stars.forEach((star, index) => {
        star.addEventListener('click', function () {
            const ratingValue = index + 1;
            stars.forEach((s, i) => {
                if (i <= index) {
                    s.classList.add('selected');
                } else {
                    s.classList.remove('selected');
                }
            });
        });
        star.addEventListener('mouseover', function () {
            stars.forEach((s, i) => {
                if (i <= index) {
                    s.classList.add('selected');
                } else {
                    s.classList.remove('selected');
                }
            });
        });

        star.addEventListener('mouseout', function () {
            stars.forEach((s) => {
                s.classList.remove('selected');
            });
        });
    });

    $(document).ready(function () {
        $('.btn-show-product-review').click(function () {
            var productId = $('#id-product-hidden').val();
            var csrfToken = document.querySelector('meta[name="_csrf"]').getAttribute('content');
            var csrfHeader = document.querySelector('meta[name="_csrf_header"]').getAttribute('content');
            var csrfTokenHeader = {};
            csrfTokenHeader[csrfHeader] = csrfToken;
            $.ajax({
                type: 'GET',
                url: '/cosmetic/check-login',
                data: {
                    productId: productId
                },
                headers: csrfTokenHeader,
                success: function (response) {
                    if (response === 'true1') {
                        showNotificationReviewed();
                    } else if (response === 'true2') {
                        $('.product-review').css('display', 'block');
                    } else {
                        showNotificationReview();
                    }
                },
                error: function (xhr, status, error) {
                    alert('Đã xảy ra lỗi: ' + error);
                }
            });
        });
    });

    function showNotificationReview() {
        var notificationHTML = `
        <div id="notification" class="notification">
            <div class="notification-svg text-center">
                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-box-arrow-in-right" viewBox="0 0 16 16">
                  <path fill-rule="evenodd" d="M6 3.5a.5.5 0 0 1 .5-.5h8a.5.5 0 0 1 .5.5v9a.5.5 0 0 1-.5.5h-8a.5.5 0 0 1-.5-.5v-2a.5.5 0 0 0-1 0v2A1.5 1.5 0 0 0 6.5 14h8a1.5 1.5 0 0 0 1.5-1.5v-9A1.5 1.5 0 0 0 14.5 2h-8A1.5 1.5 0 0 0 5 3.5v2a.5.5 0 0 0 1 0z"/>
                  <path fill-rule="evenodd" d="M11.854 8.354a.5.5 0 0 0 0-.708l-3-3a.5.5 0 1 0-.708.708L10.293 7.5H1.5a.5.5 0 0 0 0 1h8.793l-2.147 2.146a.5.5 0 0 0 .708.708z"/>
                </svg>
            </div>
            <div class="text-center">Hãy đăng nhập để đánh giá sản phẩm</div>
        </div>
    `;
        document.body.insertAdjacentHTML('beforeend', notificationHTML);

        setTimeout(function () {
            var notification = document.getElementById("notification");
            if (notification) {
                notification.remove();
            }
        }, 2000); // 2 giây
    }

    function showNotificationReviewed() {
        var notificationHTML = `
        <div id="notification" class="notification">
            <div class="notification-svg text-center">
                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-star-half" viewBox="0 0 16 16">
                  <path d="M5.354 5.119 7.538.792A.52.52 0 0 1 8 .5c.183 0 .366.097.465.292l2.184 4.327 4.898.696A.54.54 0 0 1 16 6.32a.55.55 0 0 1-.17.445l-3.523 3.356.83 4.73c.078.443-.36.79-.746.592L8 13.187l-4.389 2.256a.5.5 0 0 1-.146.05c-.342.06-.668-.254-.6-.642l.83-4.73L.173 6.765a.55.55 0 0 1-.172-.403.6.6 0 0 1 .085-.302.51.51 0 0 1 .37-.245zM8 12.027a.5.5 0 0 1 .232.056l3.686 1.894-.694-3.957a.56.56 0 0 1 .162-.505l2.907-2.77-4.052-.576a.53.53 0 0 1-.393-.288L8.001 2.223 8 2.226z"/>
                </svg>
            </div>
            <div class="text-center">Sản phẩm này bạn đã đánh giá!</div>
        </div>
    `;
        document.body.insertAdjacentHTML('beforeend', notificationHTML);

        setTimeout(function () {
            var notification = document.getElementById("notification");
            if (notification) {
                notification.remove();
            }
        }, 2000); // 2 giây
    }

    function showNotificationThankReview() {
        var notificationHTML = `
        <div id="notification" class="notification">
            <div class="notification-svg text-center">
                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-star-half" viewBox="0 0 16 16">
                  <path d="M5.354 5.119 7.538.792A.52.52 0 0 1 8 .5c.183 0 .366.097.465.292l2.184 4.327 4.898.696A.54.54 0 0 1 16 6.32a.55.55 0 0 1-.17.445l-3.523 3.356.83 4.73c.078.443-.36.79-.746.592L8 13.187l-4.389 2.256a.5.5 0 0 1-.146.05c-.342.06-.668-.254-.6-.642l.83-4.73L.173 6.765a.55.55 0 0 1-.172-.403.6.6 0 0 1 .085-.302.51.51 0 0 1 .37-.245zM8 12.027a.5.5 0 0 1 .232.056l3.686 1.894-.694-3.957a.56.56 0 0 1 .162-.505l2.907-2.77-4.052-.576a.53.53 0 0 1-.393-.288L8.001 2.223 8 2.226z"/>
                </svg>
            </div>
            <div class="text-center">Cảm ơn bạn đã đánh giá sản phẩm này!</div>
        </div>
    `;
        document.body.insertAdjacentHTML('beforeend', notificationHTML);

        setTimeout(function () {
            var notification = document.getElementById("notification");
            if (notification) {
                notification.remove();
            }
        }, 2000); // 2 giây
    }

    $(document).ready(function () {
        $('.btn-product-review').click(function () {
            var nameProduct = $('#name-product-cart').val();
            var nameCustomer = $('#name-customer').val();
            var productId = $('#product-review-id').val();
            var rating = $('input[name="rating"]:checked').val();
            var description = $('#product-review-description').val();
            var csrfToken = document.querySelector('meta[name="_csrf"]').getAttribute('content');
            var csrfHeader = document.querySelector('meta[name="_csrf_header"]').getAttribute('content');
            var csrfTokenHeader = {};
            csrfTokenHeader[csrfHeader] = csrfToken;
            // var currentTime = new Date();
            // var hours = currentTime.getHours();
            // var minutes = currentTime.getMinutes();
            // var day = ('0' + currentTime.getDate()).slice(-2);
            // var month = ('0' + (currentTime.getMonth() + 1)).slice(-2); // Tháng bắt đầu từ 0, cần cộng thêm 1
            // var year = currentTime.getFullYear();
            // var formattedTime = ('0' + hours).slice(-2) + ':' + ('0' + minutes).slice(-2) + ' | ' + day + '/' + month + '/' + year;
            var scrollPosition = window.scrollY || window.pageYOffset;
            $.ajax({
                type: 'POST',
                url: '/cosmetic/save-review-product',
                data: {
                    productId: productId, rating: rating, description: description
                },
                headers: csrfTokenHeader,
                success: function (response) {
                    showNotificationThankReview()
                    setTimeout(function () {
                        window.scrollTo(0, scrollPosition);
                        window.location.reload();
                    }, 2000);
                },
                error: function (xhr, status, error) {
                    console.error('Đánh giá sản phẩm thất bại');
                }
            });
        });
    });

    var productPageCartElements = document.querySelectorAll('.product-page-cart');
    var totalPriceOfTheProducts = 0;
    for (var i = 0; i < productPageCartElements.length; i++) {
        var productId = productPageCartElements[i].getAttribute('data-id');
        var priceProductOptions = parseFloat(productPageCartElements[i].querySelector(".price-product-options-page-cart").innerText);
        var delElement = productPageCartElements[i].querySelector("del");
        var discountPrice = parseFloat(delElement.getAttribute('data-discount_price'));
        var discountType = delElement.getAttribute('data-discount_type');
        var quantity = parseInt(document.getElementById('quantity_' + productId).value);
        if (discountType === 'AMOUNT') {
            document.getElementById('discount-product-' + productId).innerHTML = priceProductOptions.toLocaleString('vi-VN', {
                style: 'currency',
                currency: 'VND'
            });
            var priceNew = priceProductOptions - discountPrice;
            document.getElementById('price-product-options-' + productId).innerHTML = priceNew.toLocaleString('vi-VN', {
                style: 'currency',
                currency: 'VND'
            });
            var totalPrice = priceNew * quantity;
            totalPriceOfTheProducts += totalPrice;
            document.getElementById('price-total-product-options-' + productId).innerHTML = totalPrice.toLocaleString('vi-VN', {
                style: 'currency',
                currency: 'VND'
            });
        } else if (discountType === 'PERCENTAGE') {
            document.getElementById('discount-product-' + productId).innerHTML = priceProductOptions.toLocaleString('vi-VN', {
                style: 'currency',
                currency: 'VND'
            });
            var priceNew = priceProductOptions - (priceProductOptions * (discountPrice / 100));
            document.getElementById('price-product-options-' + productId).innerHTML = priceNew.toLocaleString('vi-VN', {
                style: 'currency',
                currency: 'VND'
            });
            var totalPrice = priceNew * quantity;
            totalPriceOfTheProducts += totalPrice;
            document.getElementById('price-total-product-options-' + productId).innerHTML = totalPrice.toLocaleString('vi-VN', {
                style: 'currency',
                currency: 'VND'
            });
        } else {
            document.getElementById('price-product-options-' + productId).innerHTML = priceProductOptions.toLocaleString('vi-VN', {
                style: 'currency',
                currency: 'VND'
            });
            var totalPrice = priceProductOptions * quantity;
            totalPriceOfTheProducts += totalPrice;
            document.getElementById('price-total-product-options-' + productId).innerHTML = totalPrice.toLocaleString('vi-VN', {
                style: 'currency',
                currency: 'VND'
            });
        }
        document.getElementById('total-price-of-the-products').innerHTML = totalPriceOfTheProducts.toLocaleString('vi-VN', {
            style: 'currency',
            currency: 'VND'
        });
        document.getElementById('total-price-of-the-products-and-discounts').innerHTML = totalPriceOfTheProducts.toLocaleString('vi-VN', {
            style: 'currency',
            currency: 'VND'
        });
    }

    function scrollToContent(targetId) {
        var targetElement = document.getElementById(targetId);
        if (targetElement) {
            // Cuộn đến vị trí của phần tử mục tiêu
            targetElement.scrollIntoView({behavior: 'smooth', block: 'start'});
        }
    }

    function openLogin() {
        var inputs = document.getElementsByClassName("input-login");
        for (var i = 0; i < inputs.length; i++) {
            inputs[i].value = "";
        }
        document.getElementById('id01').style.display='block';
    }

    function openRegister() {
        var inputs = document.getElementsByClassName("input-register");
        for (var i = 0; i < inputs.length; i++) {
            inputs[i].value = "";
        }
        document.getElementById('id02').style.display='block';
    }