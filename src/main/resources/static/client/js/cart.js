function addToCart() {
    var productId = document.getElementById("id-product-hidden").value;
    var productName = document.getElementById("name-product-cart").value;
    var productOptionId = document.getElementById("id-product-option-cart").value;
    var quantity = document.getElementById("quantity").value;
    var productOptionImage = document.getElementById("image-product-option-cart").value;
    var productOptionName = document.getElementById("name-product-option-cart").value;
    var productOptionPrice = document.getElementById("price-product-option-cart").value;
    var priceDiscount = document.getElementById("price-discount-product-cart").value;
    var typeDiscount = document.getElementById("type-discount-product-cart").value;
    var productOptionAmount = document.getElementById("amount-product-option-cart").value;
    if (productOptionId === "") {
        showNotificationCheckChooseProductOption();
    } else {
        $.ajax({
            type: "GET",
            url: "/cosmetic/add-to-cart",
            data: {
                productId: productId,
                productOptionId: productOptionId,
                productOptionImage: productOptionImage,
                productName: productName,
                productOptionName: productOptionName,
                productOptionPrice: productOptionPrice,
                quantity: quantity,
                priceDiscount: priceDiscount,
                typeDiscount: typeDiscount,
                productOptionAmount: productOptionAmount
            },
            success: function (response) {
                showNotification();
                var cartItemCount = response.length;
                $('.badge.text-secondary').text(cartItemCount);
            },
            error: function (err) {
                console.error("Lỗi khi thêm sản phẩm vào giỏ hàng:", err);
            }
        });
    }
}

function showNotificationCheckChooseProductOption() {
    var notificationHTML = `
        <div id="notification" class="notification">
            <div class="notification-svg text-center">
                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-check2-square text-white" viewBox="0 0 16 16">
                  <path d="M3 14.5A1.5 1.5 0 0 1 1.5 13V3A1.5 1.5 0 0 1 3 1.5h8a.5.5 0 0 1 0 1H3a.5.5 0 0 0-.5.5v10a.5.5 0 0 0 .5.5h10a.5.5 0 0 0 .5-.5V8a.5.5 0 0 1 1 0v5a1.5 1.5 0 0 1-1.5 1.5z"/>
                  <path d="m8.354 10.354 7-7a.5.5 0 0 0-.708-.708L8 9.293 5.354 6.646a.5.5 0 1 0-.708.708l3 3a.5.5 0 0 0 .708 0"/>
                </svg>
            </div>
            <div class="text-center">Vui lòng chọn màu của sản phẩm</div>
        </div>
    `;

    // Thêm chuỗi HTML vào body
    document.body.insertAdjacentHTML('beforeend', notificationHTML);

    // Đặt thời gian để ẩn thông báo sau 2 giây
    setTimeout(function () {
        var notification = document.getElementById("notification");
        if (notification) {
            notification.remove();
        }
    }, 2000); // 2 giây
}

function showNotification() {
    var notificationHTML = `
        <div id="notification" class="notification">
            <div class="notification-svg text-center">
                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-check2-square text-white" viewBox="0 0 16 16">
                  <path d="M3 14.5A1.5 1.5 0 0 1 1.5 13V3A1.5 1.5 0 0 1 3 1.5h8a.5.5 0 0 1 0 1H3a.5.5 0 0 0-.5.5v10a.5.5 0 0 0 .5.5h10a.5.5 0 0 0 .5-.5V8a.5.5 0 0 1 1 0v5a1.5 1.5 0 0 1-1.5 1.5z"/>
                  <path d="m8.354 10.354 7-7a.5.5 0 0 0-.708-.708L8 9.293 5.354 6.646a.5.5 0 1 0-.708.708l3 3a.5.5 0 0 0 .708 0"/>
                </svg>
            </div>
            <div class="text-center">Sản phẩm đã được thêm vào giỏ hàng</div>
        </div>
    `;

    // Thêm chuỗi HTML vào body
    document.body.insertAdjacentHTML('beforeend', notificationHTML);

    // Đặt thời gian để ẩn thông báo sau 2 giây
    setTimeout(function () {
        var notification = document.getElementById("notification");
        if (notification) {
            notification.remove();
        }
    }, 2500); // 2 giây
}

$(document).ready(function () {
    $('#cart-icon').click(function (e) {
        e.preventDefault();
        $.ajax({
            url: "/cosmetic/cart-mini",
            type: "GET",
            success: function (response) {
                $('.modal-body').empty();
                if (response.length === 0) {
                    $('.modal-body').append('<p>Giỏ hàng trống</p>');
                } else {
                    var cartTable = '<table class="table">' +
                        '<thead>' +
                        '<tr>' +
                        '<th>Ảnh</th>' +
                        '<th>Tên</th>' +
                        '<th>Số lượng</th>' +
                        '</tr>' +
                        '</thead>' +
                        '<tbody>';
                    $.each(response, function (index, item) {
                        cartTable += '<tr class="cart-item-row">' +
                            '<td><img src="' + item.productOptionImage + '" width="30" height="30"/></td>' +
                            '<td>' + item.productOptionName + '</td>' +
                            '<td>' + item.quantity + '</td>' +
                            '</tr>';
                    });
                    cartTable += '</tbody></table>';
                    $('.modal-body').html(cartTable);
                }
                $('#miniCartModal').modal('show');
            },
            error: function (xhr, status, error) {
                console.error("Lỗi khi tải dữ liệu giỏ hàng:", error);
            }
        });
    });
});

function updateQuantity(action, productOptionId) {
    var inputElement = document.getElementById("quantity_" + productOptionId);
    var quantity = parseInt(inputElement.value);
    var amount = parseInt(inputElement.getAttribute("id-amount_product_option"));
    if (action === "increase" && amount > quantity) {
        quantity++;
    }
    if (action === "decrease" && quantity > 1) {
        quantity--;
    }
    inputElement.value = quantity;
    var priceProductOptions = parseFloat(document.getElementById('price-product-option-input-' + productOptionId).value);
    var discountPrice = parseFloat(document.getElementById('value-discount-product-input-' + productOptionId).value);
    var discountType = document.getElementById('type-discount-product-input-' + productOptionId).value;
    if (discountType === 'AMOUNT') {
        var priceNew = priceProductOptions - discountPrice;
        var totalPrice = priceNew * quantity;
        document.getElementById('price-total-product-options-' + productOptionId).innerHTML = totalPrice.toLocaleString('vi-VN', {
            style: 'currency',
            currency: 'VND'
        });
    } else if (discountType === 'PERCENTAGE') {
        var priceNew = priceProductOptions - (priceProductOptions * (discountPrice / 100));
        var totalPrice = priceNew * quantity;
        document.getElementById('price-total-product-options-' + productOptionId).innerHTML = totalPrice.toLocaleString('vi-VN', {
            style: 'currency',
            currency: 'VND'
        });
    } else {
        var totalPrice = priceProductOptions * quantity;
        document.getElementById('price-total-product-options-' + productOptionId).innerHTML = totalPrice.toLocaleString('vi-VN', {
            style: 'currency',
            currency: 'VND'
        });
    }

    $.ajax({
        url: "/cosmetic/update-quantity-cart",
        type: "GET",
        data: {productOptionId: productOptionId, quantity: quantity},
        success: function (response) {
            loadTotalPriceAfterUpdateAndDeleteProduct()
        },
        error: function (xhr, status, error) {
            console.error("Error updating quantity:", error);
        }
    });
}

function updateQuantityProductDetail(action, productOptionId) {
    var inputElement = document.getElementById("quantity");
    var quantity = parseInt(inputElement.value);
    var amount = parseInt(document.getElementById("amount-product-option-cart").value);
    if (action === "increase" && amount > quantity) {
        quantity++;
    }
    if (action === "decrease" && quantity > 1) {
        quantity--;
    }
    inputElement.value = quantity;
}

function deleteProductToCart(productOptionId) {
    $.ajax({
        url: "/cosmetic/delete-product-to-cart",
        type: "GET",
        data: {productOptionId: productOptionId},
        success: function (response) {
            $('#row_' + productOptionId).remove();
            var cartItemCount = response.length;
            $('.badge.text-secondary').text(cartItemCount);
            if (cartItemCount == 0) {
                document.getElementById('view-cart-empty').style.display = 'block';
                document.getElementById('view-cart').style.display = 'none';
            }
            loadTotalPriceAfterUpdateAndDeleteProduct();
        },
        error: function (xhr, status, error) {
            console.error("Error updating quantity:", error);
        }
    });
}

function loadTotalPriceAfterUpdateAndDeleteProduct() {
    var productPageCartElements = document.querySelectorAll('.quantity');
    var totalPriceOfTheProducts = 0;
    for (var i = 0; i < productPageCartElements.length; i++) {
        var productOptionsID = productPageCartElements[i].getAttribute('data-id');
        var priceProductOptions = parseFloat(productPageCartElements[i].querySelector('#price-product-option-input-' + productOptionsID).value);
        var discountType = productPageCartElements[i].querySelector('#type-discount-product-input-' + productOptionsID).value;
        var discountPrice = parseFloat(productPageCartElements[i].querySelector('#value-discount-product-input-' + productOptionsID).value);
        var quantity = parseFloat(productPageCartElements[i].querySelector('#quantity_' + productOptionsID).value);
        if (discountType === 'AMOUNT') {
            document.getElementById('discount-product-' + productOptionsID).innerHTML = priceProductOptions.toLocaleString('vi-VN', {
                style: 'currency',
                currency: 'VND'
            });
            var priceNew = priceProductOptions - discountPrice;
            document.getElementById('price-product-options-' + productOptionsID).innerHTML = priceNew.toLocaleString('vi-VN', {
                style: 'currency',
                currency: 'VND'
            });
            var totalPrice = priceNew * quantity;
            totalPriceOfTheProducts += totalPrice;
            document.getElementById('price-total-product-options-' + productOptionsID).innerHTML = totalPrice.toLocaleString('vi-VN', {
                style: 'currency',
                currency: 'VND'
            });
        } else if (discountType === 'PERCENTAGE') {
            document.getElementById('discount-product-' + productOptionsID).innerHTML = priceProductOptions.toLocaleString('vi-VN', {
                style: 'currency',
                currency: 'VND'
            });
            var priceNew = priceProductOptions - (priceProductOptions * (discountPrice / 100));
            document.getElementById('price-product-options-' + productOptionsID).innerHTML = priceNew.toLocaleString('vi-VN', {
                style: 'currency',
                currency: 'VND'
            });
            var totalPrice = priceNew * quantity;
            totalPriceOfTheProducts += totalPrice;
            document.getElementById('price-total-product-options-' + productOptionsID).innerHTML = totalPrice.toLocaleString('vi-VN', {
                style: 'currency',
                currency: 'VND'
            });
        } else {
            document.getElementById('price-product-options-' + productOptionsID).innerHTML = priceProductOptions.toLocaleString('vi-VN', {
                style: 'currency',
                currency: 'VND'
            });
            var totalPrice = priceProductOptions * quantity;
            totalPriceOfTheProducts += totalPrice;
            document.getElementById('price-total-product-options-' + productOptionsID).innerHTML = totalPrice.toLocaleString('vi-VN', {
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
}

function loadNumberProductInCart() {
    $.ajax({
        url: "/cosmetic/load-number-product-in-cart",
        type: "GET",
        success: function (cart) {
            $('.badge.text-secondary').text(cart.length);
        },
        error: function (xhr, status, error) {
            console.error("Error updating cart item count:", error);
        }
    });
}

$(document).ready(function () {
    loadNumberProductInCart();
});

$(document).ready(function () {
    $('#btn-checkout').click(function () {
        $.ajax({
            url: "/cosmetic/check-logged-checkout",
            type: "GET",
            success: function (response) {
                if (response) {
                    window.location.href = "/cosmetic/checkout";
                } else {
                    document.getElementById('id01').style.display = 'block'
                }
            },
            error: function (xhr, status, error) {
                console.error("Error: ", error);
            }
        });
    });
});
