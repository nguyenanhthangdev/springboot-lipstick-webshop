var modalAddress = document.getElementById("myModalAddress");
var btnAddress = document.getElementById("myBtnAddress");
var spanAddress = document.getElementsByClassName("close-address")[0];
var btnCancelAddress = document.getElementsByClassName("btn-all-address-cancel")[0];
// var modalAddress1 = document.getElementById("myModalAddAddress1");
// var btnAddress1 = document.getElementById("myBtnAddress1");

btnAddress.onclick = function() {
    modalAddress.style.display = "block";
}

function addAddressNewOfCustomer() {
    document.getElementById("myModalAddAddress1").style.display="block";
}
function myModalAddAddress2() {
    document.getElementById("myModalAddAddress2").style.display="block";
}
function addAddressCustomerReturn2() {
    document.getElementById("myModalAddAddress2").style.display="none";
    document.getElementById("myModalAddress").style.display="block";
}
function returnPageCheckout() {
    document.getElementById("myModalAddAddress1").style.display="none";
}
function allAddressCancel() {
    document.getElementById("myModalAddress").style.display="none";
}
function changePaymentsCancel() {
    document.getElementById("myModalPayments").style.display="none";
}
function savePayments() {
    document.getElementById("myModalPayments").style.display="none";
}
function getAllAddressOfCustomer() {
    document.getElementById("myModalAddress").style.display="block";
    $.ajax({
        url: "/cosmetic/get-all-address-of-customer",
        type: "GET",
        success: function(shipping) {
            var html = ''
            for (var i=0; i<shipping.length; i++) {
                html +=
                    '<div class="row mb-1">' +
                        '<div class="col-10 d-flex">' +
                        '<p class="mr-2 mb-1"><input type="radio" data-id="' + shipping[i].id + '" name="default-shipping"' + (shipping[i].status == true ? 'checked' : '') + '></p>' +
                        '<p class="mr-2 mb-1">' + shipping[i].fullName + '</p>' +
                        '<p class="mr-2 mb-1">|</p>' +
                        '<p class="mb-1">' + shipping[i].phone + '</p>' +
                        '</div>' +
                        '<div class="col-2 mb-1 text-right">' +
                            '<a class="card-a" onclick="deleteShippingByIdOfCustomer(' + shipping[i].id + ')">' +
                                '<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-trash text-danger" viewBox="0 0 16 16">' +
                                    '<path d="M5.5 5.5A.5.5 0 0 1 6 6v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5m2.5 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5m3 .5a.5.5 0 0 0-1 0v6a.5.5 0 0 0 1 0z"/>' +
                                    '<path d="M14.5 3a1 1 0 0 1-1 1H13v9a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V4h-.5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1H6a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1h3.5a1 1 0 0 1 1 1zM4.118 4 4 4.059V13a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V4.059L11.882 4zM2.5 3h11V2h-11z"/>' +
                                '</svg>' +
                            '</a>' +
                        '</div>' +
                    '</div>' +
                    '<p class="mb-1">' + shipping[i].addressSpecific +
                    '<div class="mb-1>'
                        html += '<p class="mb-1">' + shipping[i].city.city_name + ', ' + shipping[i].district.district_name + ', ' + shipping[i].ward.ward_name + '</p>' +
                    '</div>'
                    if (shipping[i].status) {
                        html += '<p class="shipping-default mb-1 mt-0 d-inline-block "><span>Mặc định</span></p>'
                    }
                    html += '<hr>'
            }
            $('.myModalBodyAddress').html(html)
        },
        error: function(xhr, status, error) {
            console.error("Error: ", error);
        }
    });
}

function selectThisAddressAsTheDefaultAddress() {
    var idDefaultShipping = 0;
    // Lấy tất cả các input radio có thuộc tính name là 'default-shipping'
    var radioButtons = document.querySelectorAll('input[name="default-shipping"]');

    // Lặp qua từng input radio
    radioButtons.forEach(function(radioButton) {
        // Kiểm tra xem input radio này có được chọn hay không
        if (radioButton.checked) {
            // Lấy giá trị của thuộc tính data-id
            var dataId = radioButton.getAttribute('data-id');
            idDefaultShipping = dataId;
        }
    });
    if (idDefaultShipping !== 0) {
        changeThisAddressToDefaultInDB(idDefaultShipping);
    }
}

function changeThisAddressToDefaultInDB(idDefaultShipping) {
    var csrfToken = document.querySelector('meta[name="_csrf"]').getAttribute('content');
    var csrfHeader = document.querySelector('meta[name="_csrf_header"]').getAttribute('content');
    var csrfTokenHeader = {};
    csrfTokenHeader[csrfHeader] = csrfToken;
    $.ajax({
        url: "/cosmetic/change-this-address-to-default-in-DB",
        type: "POST",
        data: {idDefaultShipping},
        headers: csrfTokenHeader,
        success: function(response) {
            window.location.href = "/cosmetic/checkout";
        },
        error: function(xhr, status, error) {
            console.error("Error:", error);
        }
    });
}

function deleteShippingByIdOfCustomer(id) {
    var csrfToken = document.querySelector('meta[name="_csrf"]').getAttribute('content');
    var csrfHeader = document.querySelector('meta[name="_csrf_header"]').getAttribute('content');
    var csrfTokenHeader = {};
    csrfTokenHeader[csrfHeader] = csrfToken;
    $.ajax({
        url: "/cosmetic/delete-shipping-by-id-of-customer",
        type: "POST",
        data: { id: id },
        headers: csrfTokenHeader,
        success: function(data) {
            if (data === false) {
                showNotificationDeleteShipping()
            } else {
                window.location.href = "/cosmetic/checkout";
            }
        },
        error: function(xhr, status, error) {
            showNotificationCanNotDeleteShipping()
        }
    });
}

function showNotificationCanNotDeleteShipping() {
    var notificationHTML = `
        <div id="notification" class="notification-danger text-danger">
            <div class="notification-svg text-center">
                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-exclamation-circle" viewBox="0 0 16 16">
                  <path d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14m0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16"/>
                  <path d="M7.002 11a1 1 0 1 1 2 0 1 1 0 0 1-2 0M7.1 4.995a.905.905 0 1 1 1.8 0l-.35 3.507a.552.552 0 0 1-1.1 0z"/>
                </svg>
            </div>
            <div class="text-center">Lỗi: không thể xóa địa chỉ này của bạn!</div>
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

function showNotificationDeleteShipping() {
    var notificationHTML = `
        <div id="notification" class="notification-danger text-danger">
            <div class="notification-svg text-center">
                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-exclamation-circle" viewBox="0 0 16 16">
                  <path d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14m0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16"/>
                  <path d="M7.002 11a1 1 0 1 1 2 0 1 1 0 0 1-2 0M7.1 4.995a.905.905 0 1 1 1.8 0l-.35 3.507a.552.552 0 0 1-1.1 0z"/>
                </svg>
            </div>
            <div class="text-center">Không thể xóa địa chỉ mặc định!</div>
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

spanAddress.onclick = function() {
    modalAddress.style.display = "none";
}
btnCancelAddress.onclick = function() {
    modalAddress.style.display = "none";
}

var modalAddAddress = document.getElementById("myModalAddAddress");
var btnAddAddress = document.getElementById("btn-add-address");
var btnReturnAddAddress = document.getElementById("btn-add-address-return");
var spanAddAddress = document.getElementsByClassName("close-add-address")[0];

btnAddAddress.onclick = function() {
    modalAddress.style.display = "none";
    modalAddAddress.style.display = "block";
}

spanAddAddress.onclick = function() {
    modalAddAddress.style.display = "none";
}

btnReturnAddAddress.onclick = function () {
    modalAddAddress.style.display = "none";
    modalAddress.style.display = "block";
}

var btnAddAddressReturnHome = document.getElementById("btn-add-address-return-home");
btnAddAddressReturnHome.onclick = function () {
    window.location.href = "/cosmetic";
}

function handleRadioClick(id) {
    var radios = document.querySelectorAll('.radio-block');
    radios.forEach(function(radio) {
        radio.classList.remove('selected');
    });
    var selectedRadio = document.getElementById(id).parentNode;
    selectedRadio.classList.add('selected'); selectedRadio.checked = true;
    $('input[name="radio-shipping"]').prop('checked', false);
    $('#' + id).prop('checked', true);
}
function handleRadioClick2(id) {
    var radios = document.querySelectorAll('.radio-block2');
    radios.forEach(function(radio) {
        radio.classList.remove('selected');
    });
    var selectedRadio = document.getElementById(id).parentNode;
    selectedRadio.classList.add('selected'); selectedRadio.checked = true;
    $('input[name="radio-shipping2"]').prop('checked', false);
    $('#' + id).prop('checked', true);
}

function validateShipping() {
    var fullName = document.getElementById("fullNameShipping").value.trim();
    var fullNameError = document.getElementById("fullNameShippingError");
    var fullNameTextError = document.getElementById("fullNameShippingTextError");

    var email = document.getElementById("emailShipping").value.trim();
    var emailError = document.getElementById("emailShippingError");
    var emailTextError = document.getElementById("emailShippingTextError");

    var phone = document.getElementById("phoneShipping").value.trim();
    var phoneError = document.getElementById("phoneShippingError");
    var phoneTextError = document.getElementById("phoneShippingTextError");

    var addressSpecific = document.getElementById("addressSpecificShipping").value.trim();
    var addressSpecificError = document.getElementById("addressSpecificShippingError");
    var addressSpecificTextError = document.getElementById("addressSpecificShippingTextError");

    var city = document.getElementById("city1").value;
    var cityError = document.getElementById("cityShippingError");
    var cityTextError = document.getElementById("cityShippingTextError");
    var district = document.getElementById("district1").value;
    var districtError = document.getElementById("districtShippingError");
    var districtTextError = document.getElementById("districtShippingTextError");
    var ward = document.getElementById("ward1").value;
    var wardError = document.getElementById("wardShippingError");
    var wardTextError = document.getElementById("wardShippingTextError");

    var selectedRadioAddressValue = $('input[name="radio-shipping"]:checked').val();

    fullNameError.style.display = "none"; emailError.style.display = "none"; phoneError.style.display = "none"; addressSpecificError.style.display = "none";

    var check = false;
    if (fullName === "" || /^\s+$/.test(fullName)) {
        fullNameError.style.display = "block";
        fullNameTextError.textContent = "Họ tên không hợp lệ";
        check = true;
        return false;
    } else {
        fullNameError.style.display = "none";
    }

    if (email) {
        var emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        if (!emailPattern.test(email)) {
            emailError.style.display = "block";
            emailTextError.textContent = "Email không hợp lệ";
            check = true;
            return false;
        } else {
            emailError.style.display = "none";
        }
    } else {
        emailError.style.display = "none";
    }

    var phonePattern = /^(0[1-9])+([0-9]{8,9})\b$/;
    if (!phonePattern.test(phone)) {
        phoneError.style.display = "block";
        phoneTextError.textContent = "Số điện thoại không hợp lệ";
        check = true;
        return false;
    } else {
        phoneError.style.display = "none";
    }

    if (city === "0") {
        cityError.style.display = "block";
        cityTextError.textContent = "Vui lòng lựa chọn";
        check = true;
        return false;
    } else {
        cityError.style.display = "none";
    }

    if (district === "0") {
        districtError.style.display = "block";
        districtTextError.textContent = "Vui lòng lựa chọn";
        check = true;
        return false;
    } else {
        districtError.style.display = "none";
    }

    if (ward === "0") {
        wardError.style.display = "block";
        wardTextError.textContent = "Vui lòng lựa chọn";
        check = true;
        return false;
    } else {
        wardError.style.display = "none";
    }

    if (addressSpecific === "") {
        addressSpecificError.style.display = "block";
        addressSpecificTextError.textContent = "Vui lòng kiểm tra lại";
        check = true;
        return false;
    } else {
        addressSpecificError.style.display = "none";
    }

    saveShippingOfCustomer(fullName, phone, email, city, district, ward, addressSpecific, selectedRadioAddressValue);
    return false;
}
function saveShippingOfCustomer(fullName, phone, email, city, district, ward, addressSpecific, selectedRadioAddressValue) {
    var csrfToken = document.querySelector('meta[name="_csrf"]').getAttribute('content');
    var csrfHeader = document.querySelector('meta[name="_csrf_header"]').getAttribute('content');
    var csrfTokenHeader = {};
    csrfTokenHeader[csrfHeader] = csrfToken;
    $.ajax({
        url: "/cosmetic/save-shipping-of-customer",
        type: "POST",
        data: {
            fullName: fullName, phone: phone, email: email, city: city, district: district, ward: ward,
            addressSpecific: addressSpecific, addressType: selectedRadioAddressValue
        },
        headers: csrfTokenHeader,
        success: function(response) {
            window.location.href = "/cosmetic/checkout";
        },
        error: function(xhr, status, error) {
            console.error("Error:", error);
        }
    });
}

function validateShipping2() {
    var fullName = document.getElementById("fullNameShipping2").value.trim();
    var fullNameError = document.getElementById("fullNameShippingError2");
    var fullNameTextError = document.getElementById("fullNameShippingTextError2");

    var email = document.getElementById("emailShipping2").value.trim();
    var emailError = document.getElementById("emailShippingError2");
    var emailTextError = document.getElementById("emailShippingTextError2");

    var phone = document.getElementById("phoneShipping2").value.trim();
    var phoneError = document.getElementById("phoneShippingError2");
    var phoneTextError = document.getElementById("phoneShippingTextError2");

    var addressSpecific = document.getElementById("addressSpecificShipping2").value.trim();
    var addressSpecificError = document.getElementById("addressSpecificShippingError2");
    var addressSpecificTextError = document.getElementById("addressSpecificShippingTextError2");

    var city = document.getElementById("city2").value;
    var cityError = document.getElementById("cityShippingError2");
    var cityTextError = document.getElementById("cityShippingTextError2");
    var district = document.getElementById("district2").value;
    var districtError = document.getElementById("districtShippingError2");
    var districtTextError = document.getElementById("districtShippingTextError2");
    var ward = document.getElementById("ward2").value;
    var wardError = document.getElementById("wardShippingError2");
    var wardTextError = document.getElementById("wardShippingTextError2");

    var selectedRadioAddressValue = $('input[name="radio-shipping2"]:checked').val();

    fullNameError.style.display = "none"; emailError.style.display = "none"; phoneError.style.display = "none"; addressSpecificError.style.display = "none";

    var check = false;
    if (fullName === "" || /^\s+$/.test(fullName)) {
        fullNameError.style.display = "block";
        fullNameTextError.textContent = "Họ tên không hợp lệ";
        check = true;
        return false;
    } else {
        fullNameError.style.display = "none";
    }

    if (email) {
        var emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        if (!emailPattern.test(email)) {
            emailError.style.display = "block";
            emailTextError.textContent = "Email không hợp lệ";
            check = true;
            return false;
        } else {
            emailError.style.display = "none";
        }
    } else {
        emailError.style.display = "none";
    }

    var phonePattern = /^(0[1-9])+([0-9]{8,9})\b$/;
    if (!phonePattern.test(phone)) {
        phoneError.style.display = "block";
        phoneTextError.textContent = "Số điện thoại không hợp lệ";
        check = true;
        return false;
    } else {
        phoneError.style.display = "none";
    }

    if (city === "0") {
        cityError.style.display = "block";
        cityTextError.textContent = "Vui lòng lựa chọn";
        check = true;
        return false;
    } else {
        cityError.style.display = "none";
    }

    if (district === "0") {
        districtError.style.display = "block";
        districtTextError.textContent = "Vui lòng lựa chọn";
        check = true;
        return false;
    } else {
        districtError.style.display = "none";
    }

    if (ward === "0") {
        wardError.style.display = "block";
        wardTextError.textContent = "Vui lòng lựa chọn";
        check = true;
        return false;
    } else {
        wardError.style.display = "none";
    }

    if (addressSpecific === "") {
        addressSpecificError.style.display = "block";
        addressSpecificTextError.textContent = "Vui lòng kiểm tra lại";
        check = true;
        return false;
    } else {
        addressSpecificError.style.display = "none";
    }
    var isDefault = document.getElementById("defaultShipping").checked;

    saveShippingOfCustomer2(fullName, phone, email, city, district, ward, addressSpecific, selectedRadioAddressValue, isDefault);
    return false;
}
function saveShippingOfCustomer2(fullName, phone, email, city, district, ward, addressSpecific, selectedRadioAddressValue, isDefault) {
    var csrfToken = document.querySelector('meta[name="_csrf"]').getAttribute('content');
    var csrfHeader = document.querySelector('meta[name="_csrf_header"]').getAttribute('content');
    var csrfTokenHeader = {};
    csrfTokenHeader[csrfHeader] = csrfToken;
    $.ajax({
        url: "/cosmetic/save-shipping-of-customer2",
        type: "POST",
        data: {
            fullName: fullName, phone: phone, email: email, city: city, district: district, ward: ward,
            addressSpecific: addressSpecific, addressType: selectedRadioAddressValue, isDefault: isDefault
        },
        headers: csrfTokenHeader,
        success: function(response) {
            window.location.href = "/cosmetic/checkout";
        },
        error: function(xhr, status, error) {
            console.error("Error:", error);
        }
    });
}

function changePayment() {
    document.getElementById("myModalPayments").style.display="block";
}

function proceedToCheckAndPlaceAnOrder() {
    // showNotificationAlertFixBug();
    $.ajax({
        url: "/cosmetic/check-customer-has-shipping",
        type: "GET",
        success: function (response) {
            if (response) {
                checkTheNumberOfProductsLeftInTheShop();
            } else {
                showNotificationChooseShipping();
            }
        },
        error: function (xhr, status, error) {
            console.error("error:" + error);
        }
    });

}
function showNotificationAlertFixBug() {
    var notificationHTML = `
        <div id="notification" class="notification text-center">
            <div class="notification-svg">
                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-gear" viewBox="0 0 16 16">
                  <path d="M8 4.754a3.246 3.246 0 1 0 0 6.492 3.246 3.246 0 0 0 0-6.492M5.754 8a2.246 2.246 0 1 1 4.492 0 2.246 2.246 0 0 1-4.492 0"/>
                  <path d="M9.796 1.343c-.527-1.79-3.065-1.79-3.592 0l-.094.319a.873.873 0 0 1-1.255.52l-.292-.16c-1.64-.892-3.433.902-2.54 2.541l.159.292a.873.873 0 0 1-.52 1.255l-.319.094c-1.79.527-1.79 3.065 0 3.592l.319.094a.873.873 0 0 1 .52 1.255l-.16.292c-.892 1.64.901 3.434 2.541 2.54l.292-.159a.873.873 0 0 1 1.255.52l.094.319c.527 1.79 3.065 1.79 3.592 0l.094-.319a.873.873 0 0 1 1.255-.52l.292.16c1.64.893 3.434-.902 2.54-2.541l-.159-.292a.873.873 0 0 1 .52-1.255l.319-.094c1.79-.527 1.79-3.065 0-3.592l-.319-.094a.873.873 0 0 1-.52-1.255l.16-.292c.893-1.64-.902-3.433-2.541-2.54l-.292.159a.873.873 0 0 1-1.255-.52zm-2.633.283c.246-.835 1.428-.835 1.674 0l.094.319a1.873 1.873 0 0 0 2.693 1.115l.291-.16c.764-.415 1.6.42 1.184 1.185l-.159.292a1.873 1.873 0 0 0 1.116 2.692l.318.094c.835.246.835 1.428 0 1.674l-.319.094a1.873 1.873 0 0 0-1.115 2.693l.16.291c.415.764-.42 1.6-1.185 1.184l-.291-.159a1.873 1.873 0 0 0-2.693 1.116l-.094.318c-.246.835-1.428.835-1.674 0l-.094-.319a1.873 1.873 0 0 0-2.692-1.115l-.292.16c-.764.415-1.6-.42-1.184-1.185l.159-.291A1.873 1.873 0 0 0 1.945 8.93l-.319-.094c-.835-.246-.835-1.428 0-1.674l.319-.094A1.873 1.873 0 0 0 3.06 4.377l-.16-.292c-.415-.764.42-1.6 1.185-1.184l.292.159a1.873 1.873 0 0 0 2.692-1.115z"/>
                </svg>
            </div>
            <h3 class="text-center text-white">Hệ thống đang nâng cấp. Vui lòng thử lại sau</h3>
        </div>
    `;
    document.body.insertAdjacentHTML('beforeend', notificationHTML);

    setTimeout(function () {
        var notification = document.getElementById("notification");
        if (notification) {
            notification.remove();
        }
    }, 3000); // 2 giây
}
function showNotificationChooseShipping() {
    var notificationHTML = `
        <div id="notification" class="notification text-center">
            <div class="notification-svg">
            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-geo-alt text-light" viewBox="0 0 16 16">
              <path d="M12.166 8.94c-.524 1.062-1.234 2.12-1.96 3.07A32 32 0 0 1 8 14.58a32 32 0 0 1-2.206-2.57c-.726-.95-1.436-2.008-1.96-3.07C3.304 7.867 3 6.862 3 6a5 5 0 0 1 10 0c0 .862-.305 1.867-.834 2.94M8 16s6-5.686 6-10A6 6 0 0 0 2 6c0 4.314 6 10 6 10"/>
              <path d="M8 8a2 2 0 1 1 0-4 2 2 0 0 1 0 4m0 1a3 3 0 1 0 0-6 3 3 0 0 0 0 6"/>
            </svg>
            </div>
            <div class="text-center">Vui lòng chọn địa chỉ nhận hàng của bạn</div>
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
function checkTheNumberOfProductsLeftInTheShop() {
    var csrfToken = document.querySelector('meta[name="_csrf"]').getAttribute('content');
    var csrfHeader = document.querySelector('meta[name="_csrf_header"]').getAttribute('content');
    var csrfTokenHeader = {};
    csrfTokenHeader[csrfHeader] = csrfToken;
    $.ajax({
        url: "/cosmetic/check-the-number-of-products-left-in-the-shop",
        type: "POST",
        headers: csrfTokenHeader,
        success: function(response) {
            if (response) {
                productsPayment();
            } else {
                showNotificationCheckTheNumberOfProducts();
                setTimeout(function() {
                    window.location.href = "/cosmetic/checkout";
                }, 2500);
            }
        },
        error: function(xhr, status, error) {
        }
    });
}
function productsPayment() {
    var csrfToken = document.querySelector('meta[name="_csrf"]').getAttribute('content');
    var csrfHeader = document.querySelector('meta[name="_csrf_header"]').getAttribute('content');
    var csrfTokenHeader = {};
    csrfTokenHeader[csrfHeader] = csrfToken;
    $.ajax({
        url: "/cosmetic/products-payment",
        type: "POST",
        headers: csrfTokenHeader,
        success: function(response) {
            showNotificationCheckTheNumberOfProducts1();
            setTimeout(function() {
                window.location.href = "/cosmetic/my-order";
            }, 2500); // 2000 milliseconds = 2 giây
        },
        error: function(xhr, status, error) {
        }
    });
}
function showNotificationCheckTheNumberOfProducts1() {
    var notificationHTML = `
        <div id="notification" class="notification text-center">
            <div class="notification-svg">
                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-cart4 mb-3" viewBox="0 0 16 16">
                    <path d="M0 2.5A.5.5 0 0 1 .5 2H2a.5.5 0 0 1 .485.379L2.89 4H14.5a.5.5 0 0 1 .485.621l-1.5 6A.5.5 0 0 1 13 11H4a.5.5 0 0 1-.485-.379L1.61 3H.5a.5.5 0 0 1-.5-.5M3.14 5l.5 2H5V5zM6 5v2h2V5zm3 0v2h2V5zm3 0v2h1.36l.5-2zm1.11 3H12v2h.61zM11 8H9v2h2zM8 8H6v2h2zM5 8H3.89l.5 2H5zm0 5a1 1 0 1 0 0 2 1 1 0 0 0 0-2m-2 1a2 2 0 1 1 4 0 2 2 0 0 1-4 0m9-1a1 1 0 1 0 0 2 1 1 0 0 0 0-2m-2 1a2 2 0 1 1 4 0 2 2 0 0 1-4 0"/>
                </svg>
            </div>
            <div class="text-center">Một số sản phẩm sẽ không đủ số lượng hoặc không còn trong kho khi thanh toán</div>
        </div>
    `;
    document.body.insertAdjacentHTML('beforeend', notificationHTML);

    setTimeout(function () {
        var notification = document.getElementById("notification");
        if (notification) {
            notification.remove();
        }
    }, 2500); // 2 giây
}
function showNotificationCheckTheNumberOfProducts() {
    var notificationHTML = `
        <div id="notification" class="notification text-center">
            <div class="notification-svg">
                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-gear" viewBox="0 0 16 16">
                  <path d="M8 4.754a3.246 3.246 0 1 0 0 6.492 3.246 3.246 0 0 0 0-6.492M5.754 8a2.246 2.246 0 1 1 4.492 0 2.246 2.246 0 0 1-4.492 0"/>
                  <path d="M9.796 1.343c-.527-1.79-3.065-1.79-3.592 0l-.094.319a.873.873 0 0 1-1.255.52l-.292-.16c-1.64-.892-3.433.902-2.54 2.541l.159.292a.873.873 0 0 1-.52 1.255l-.319.094c-1.79.527-1.79 3.065 0 3.592l.319.094a.873.873 0 0 1 .52 1.255l-.16.292c-.892 1.64.901 3.434 2.541 2.54l.292-.159a.873.873 0 0 1 1.255.52l.094.319c.527 1.79 3.065 1.79 3.592 0l.094-.319a.873.873 0 0 1 1.255-.52l.292.16c1.64.893 3.434-.902 2.54-2.541l-.159-.292a.873.873 0 0 1 .52-1.255l.319-.094c1.79-.527 1.79-3.065 0-3.592l-.319-.094a.873.873 0 0 1-.52-1.255l.16-.292c.893-1.64-.902-3.433-2.541-2.54l-.292.159a.873.873 0 0 1-1.255-.52zm-2.633.283c.246-.835 1.428-.835 1.674 0l.094.319a1.873 1.873 0 0 0 2.693 1.115l.291-.16c.764-.415 1.6.42 1.184 1.185l-.159.292a1.873 1.873 0 0 0 1.116 2.692l.318.094c.835.246.835 1.428 0 1.674l-.319.094a1.873 1.873 0 0 0-1.115 2.693l.16.291c.415.764-.42 1.6-1.185 1.184l-.291-.159a1.873 1.873 0 0 0-2.693 1.116l-.094.318c-.246.835-1.428.835-1.674 0l-.094-.319a1.873 1.873 0 0 0-2.692-1.115l-.292.16c-.764.415-1.6-.42-1.184-1.185l.159-.291A1.873 1.873 0 0 0 1.945 8.93l-.319-.094c-.835-.246-.835-1.428 0-1.674l.319-.094A1.873 1.873 0 0 0 3.06 4.377l-.16-.292c-.415-.764.42-1.6 1.185-1.184l.292.159a1.873 1.873 0 0 0 2.692-1.115z"/>
                </svg>
            </div>
            <div class="text-center">Thanh toán không thành công, do các sản phẩm mà bạn lựa chọn đã hết hàng</div>
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