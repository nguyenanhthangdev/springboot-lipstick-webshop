function openTableChangePassword() {
    document.getElementById("changePwdOld").value = "";
    document.getElementById("changePwdNew").value = "";
    document.getElementById("changePwdNewConfirm").value = "";
    document.getElementById("myModalChangePassword").style.display="block";
}

function returnToTheOrdersPageChangePassword() {
    document.getElementById("myModalChangePassword").style.display = "none";
}

function checkChangePasswordOldAndNew() {
    var passwordOld = document.getElementById("changePwdOld").value;
    var passwordOldError = document.getElementById("changePwdOldError");
    var passwordOldTextError = document.getElementById("changePwdOldTextError");

    var passwordNew = document.getElementById("changePwdNew").value;
    var passwordNewError = document.getElementById("changePwdNewError");
    var passwordNewTextError = document.getElementById("changePwdNewTextError");

    var comfirmPasswordNew = document.getElementById("changePwdNewConfirm").value;
    var comfirmPasswordNewError = document.getElementById("changePwdNewConfirmError");
    var comfirmPasswordNewTextError = document.getElementById("changePwdNewConfirmTextError");

    passwordOldError.style.display = "none"; passwordNewError.style.display = "none"; comfirmPasswordNewError.style.display = "none";

    // Kiểm tra xác thực mật khẩu mới
    if (passwordNew !== comfirmPasswordNew) {
        comfirmPasswordNewError.style.display = "block"; // Hiển thị phần tử .error-message
        comfirmPasswordNewTextError.textContent = "Mật khẩu mới và xác nhận mật khẩu không khớp."; // Đặt nội dung thông báo lỗi
        return false;
    } else {
        comfirmPasswordNewError.style.display = "none"; // Ẩn phần tử .error-message nếu không có lỗi
    }

    var passwordPattern = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[*\-#!])[A-Za-z\d*\-#!]{8,}$/;
    if (!passwordPattern.test(passwordNew)) {
        passwordNewError.style.display = "block"; // Hiển thị phần tử .error-message
        passwordNewTextError.textContent = "Mật khẩu phải có ít nhất 8 ký tự, có ít nhất 1 số, 1 chữ cái thường, 1 chữ cái hoa và 1 ký tự đặc biệt như là * - or #!";
        return false;
    } else {
        passwordNewError.style.display = "none"; // Ẩn phần tử .error-message nếu không có lỗi
    }

    var csrfToken = document.querySelector('meta[name="_csrf"]').getAttribute('content'); // Lấy CSRF token từ thẻ meta trong mã HTML
    var csrfHeader = document.querySelector('meta[name="_csrf_header"]').getAttribute('content');

    var csrfTokenHeader = {};
    csrfTokenHeader[csrfHeader] = csrfToken;

    $.ajax({
        type: "POST",
        url: "/cosmetic/check-password-old",
        data: { passwordOld: passwordOld },
        headers: csrfTokenHeader, // Thêm CSRF token vào header của yêu cầu
        success: function (response) {
            if (response === true) {
                changePassword(passwordNew);
            } else {
                passwordOldError.style.display = "block";
                passwordOldTextError.textContent = "Mật khẩu cũ không chính xác.";
                return false;
            }
        },
        error: function (xhr, status, error) {
            showNotificationError();
            console.log("error: " + error);
        }
    });
    return false;
}

function changePassword(passwordNew) {
    var csrfToken = document.querySelector('meta[name="_csrf"]').getAttribute('content'); // Lấy CSRF token từ thẻ meta trong mã HTML
    var csrfHeader = document.querySelector('meta[name="_csrf_header"]').getAttribute('content');

    var csrfTokenHeader = {};
    csrfTokenHeader[csrfHeader] = csrfToken;

    $.ajax({
        type: "POST",
        url: "/cosmetic/change-password",
        data: { passwordNew: passwordNew },
        headers: csrfTokenHeader, // Thêm CSRF token vào header của yêu cầu
        success: function (response) {
            if (response) {
                showNotificationSuccess();
                document.getElementById("myModalChangePassword").style.display = "none";
            } else {
                showNotificationError();
            }
        },
        error: function (xhr, status, error) {
            showNotificationError();
            console.log("error: " + error);
        }
    });
    return false;
}

function showNotificationSuccess() {
    var notificationHTML = `
        <div id="notification" class="notification">
            <div class="notification-svg text-center">
                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-check-circle" viewBox="0 0 16 16">
                  <path d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14m0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16"/>
                  <path d="m10.97 4.97-.02.022-3.473 4.425-2.093-2.094a.75.75 0 0 0-1.06 1.06L6.97 11.03a.75.75 0 0 0 1.079-.02l3.992-4.99a.75.75 0 0 0-1.071-1.05"/>
                </svg>
            </div>
            <div class="text-center">Thay đổi mật khẩu thành công</div>
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

function showNotificationError() {
    var notificationHTML = `
        <div id="notification" class="notification-danger text-danger">
            <div class="notification-svg text-center">
                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-exclamation-circle" viewBox="0 0 16 16">
                  <path d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14m0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16"/>
                  <path d="M7.002 11a1 1 0 1 1 2 0 1 1 0 0 1-2 0M7.1 4.995a.905.905 0 1 1 1.8 0l-.35 3.507a.552.552 0 0 1-1.1 0z"/>
                </svg>
            </div>
            <div class="text-center">Đã xảy ra lỗi nào đó phía hệ thống! Thử lại sau</div>
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

function editInfoAccount() {
    var fullName = document.getElementById("editFullName").value.trim();
    var fullNameError = document.getElementById("editFullNameError");
    var fullNameTextError = document.getElementById("editFullNameTextError");

    var email = document.getElementById("editEmail").value.trim();
    var emailError = document.getElementById("editEmailError");
    var emailTextError = document.getElementById("editEmailTextError");

    fullNameError.style.display = "none"; emailError.style.display = "none";

    if (fullName === "") {
        fullNameError.style.display = "block";
        fullNameTextError.textContent = "Họ tên không hợp lệ";
        return false;
    } else {
        fullNameError.style.display = "none";
    }

    if (email === "") {
        emailError.style.display = "block";
        emailTextError.textContent = "Vui lòng nhập email";
        return false;
    } else {
        emailError.style.display = "none";
    }

    if (email) {
        var emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        if (!emailPattern.test(email)) {
            emailError.style.display = "block"; // Hiển thị thông báo lỗi
            emailTextError.textContent = "Email không hợp lệ";
            return false; // Ngăn chặn việc submit form
        } else {
            emailError.style.display = "none"; // Ẩn thông báo lỗi nếu địa chỉ email hợp lệ
        }
    } else {
        emailError.style.display = "none"; // Ẩn thông báo lỗi nếu không có địa chỉ email
    }

    var csrfToken = document.querySelector('meta[name="_csrf"]').getAttribute('content'); // Lấy CSRF token từ thẻ meta trong mã HTML
    var csrfHeader = document.querySelector('meta[name="_csrf_header"]').getAttribute('content');

    var csrfTokenHeader = {};
    csrfTokenHeader[csrfHeader] = csrfToken;

    $.ajax({
        type: "POST",
        url: "/cosmetic/update-info-account",
        data: { fullName: fullName, email: email},
        headers: csrfTokenHeader, // Thêm CSRF token vào header của yêu cầu
        success: function (response) {
            if (response === true) {
                updateCustomerNameInCookie(fullName);
                document.getElementById("fullname-customer-account").textContent = fullName;
                showNotificationUpdateInfoAccountSuccess();
            } else {
                showNotificationUpdateInfoAccountError();
                return false;
            }
        },
        error: function (xhr, status, error) {
            showNotificationUpdateInfoAccountError();
            console.log("error: " + error);
        }
    });

    return false;
}

// Hàm lấy giá trị của một cookie theo tên
function getCookie(name) {
    let matches = document.cookie.match(new RegExp(
        "(?:^|; )" + name.replace(/([\.$?*|{}\(\)\[\]\\\/\+^])/g, '\\$1') + "=([^;]*)"
    ));
    return matches ? decodeURIComponent(matches[1]) : undefined;
}

// Hàm tạo hoặc cập nhật cookie
function setCookie(name, value, options = {}) {
    options = {
        path: '/',
        // Các tùy chọn khác, ví dụ: 'expires', 'max-age', 'domain', 'secure'
        ...options
    };

    if (options.expires instanceof Date) {
        options.expires = options.expires.toUTCString();
    }

    let updatedCookie = encodeURIComponent(name) + "=" + encodeURIComponent(value);

    for (let optionKey in options) {
        updatedCookie += "; " + optionKey;
        let optionValue = options[optionKey];
        if (optionValue !== true) {
            updatedCookie += "=" + optionValue;
        }
    }

    document.cookie = updatedCookie;
}

// Hàm cập nhật giá trị của customerName trong cookie
function updateCustomerNameInCookie(newName) {
    // Lấy giá trị hiện tại của cookie
    let cookieValue = getCookie("customerData");

    if (cookieValue) {
        // Parse JSON từ giá trị cookie
        let customerData = JSON.parse(cookieValue);

        // Cập nhật giá trị của customerName
        customerData.customerName = newName;

        // Chuyển đổi đối tượng thành chuỗi JSON và đặt lại cookie
        setCookie("customerData", JSON.stringify(customerData), { 'max-age': 3600 });
    } else {
        alert('Lỗi khi thay đổi thông tin');
    }
}

function showNotificationUpdateInfoAccountError() {
    var notificationHTML = `
        <div id="notification" class="notification-danger text-danger">
            <div class="notification-svg text-center">
                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-exclamation-circle" viewBox="0 0 16 16">
                  <path d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14m0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16"/>
                  <path d="M7.002 11a1 1 0 1 1 2 0 1 1 0 0 1-2 0M7.1 4.995a.905.905 0 1 1 1.8 0l-.35 3.507a.552.552 0 0 1-1.1 0z"/>
                </svg>
            </div>
            <div class="text-center">Thay đổi thông tin không thành công! Thử lại sau</div>
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

function showNotificationUpdateInfoAccountSuccess() {
    var notificationHTML = `
        <div id="notification" class="notification">
            <div class="notification-svg text-center">
                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-check-circle" viewBox="0 0 16 16">
                  <path d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14m0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16"/>
                  <path d="m10.97 4.97-.02.022-3.473 4.425-2.093-2.094a.75.75 0 0 0-1.06 1.06L6.97 11.03a.75.75 0 0 0 1.079-.02l3.992-4.99a.75.75 0 0 0-1.071-1.05"/>
                </svg>
            </div>
            <div class="text-center">Thay đổi thông tin tài khoản thành công</div>
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
