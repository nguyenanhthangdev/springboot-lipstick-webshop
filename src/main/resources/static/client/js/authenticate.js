function checkAuthentication(event) {
    // Ngăn chặn hành động mặc định của thẻ <a> để tránh chuyển hướng trang
    event.preventDefault();

    // Kiểm tra xem người dùng đã đăng nhập hay chưa
    if (isAuthenticated()) {
        // Nếu đã đăng nhập, chuyển hướng đến trang checkout
        window.location.href = event.target.getAttribute('href');
    } else {
        // Nếu chưa đăng nhập, hiển thị modal login
        document.getElementById('id01').style.display='block'
    }
}

function validateCustomer() {
    var fullName = document.getElementById("fullName").value.trim();
    var fullNameError = document.getElementById("fullNameError");
    var fullNameTextError = document.getElementById("fullNameTextError");

    var account = document.getElementById("account").value.trim();
    var accountError = document.getElementById("accountError");
    var accountTextError = document.getElementById("accountTextError");

    var email = document.getElementById("email").value.trim();
    var emailError = document.getElementById("emailError");
    var emailTextError = document.getElementById("emailTextError");

    var password = document.getElementById("password").value.trim();
    var passwordError = document.getElementById("passwordError");
    var passwordTextError = document.getElementById("passwordTextError");

    fullNameError.style.display = "none"; accountError.style.display = "none"; passwordError.style.display = "none"; emailError.style.display = "none";

    if (fullName === "") {
        fullNameError.style.display = "block";
        fullNameTextError.textContent = "Họ tên không hợp lệ";
        return false;
    } else {
        fullNameError.style.display = "none";
    }

    var checkAccount = false;
    var accountPattern = /^[a-zA-Z0-9]{6,}$/ // Định dạng cho account (ít nhất 6 ký tự và không chứa ký tự đặc biệt)
    if (!accountPattern.test(account)) {
        accountError.style.display = "block";
        accountTextError.textContent = "Tên đăng nhập chứa ít nhất 6 ký tự và không chứa ký tự đặc biệt";
        checkAccount = true;
        return false;
    } else {
        accountError.style.display = "none";
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

    sendAccountCheckRequest(account, accountError, accountTextError, password, passwordError, passwordTextError, fullName, email);
    return false;
}

function sendAccountCheckRequest(account, accountError, accountTextError, password, passwordError, passwordTextError, fullName, email) {
    var csrfToken = document.querySelector('meta[name="_csrf"]').getAttribute('content');
    var csrfHeader = document.querySelector('meta[name="_csrf_header"]').getAttribute('content');
    var csrfTokenHeader = {};
    csrfTokenHeader[csrfHeader] = csrfToken;
    $.ajax({
        type: "POST",
        url: "/cosmetic/check-account-exists",
        data: { account: account },
        headers: csrfTokenHeader,
        success: function (response) {
            if (response === "true") {
                checkPassword(password, passwordError, passwordTextError, fullName, account, email);
            } else {
                accountError.style.display = "block";
                accountTextError.textContent = "Tên đăng nhập đã tồn tại";
            }
        },
        error: function (xhr, status, error) {
            console.error("Lỗi khi gửi yêu cầu: " + error);
            console.log("Mã lỗi: " + xhr.status);
            console.log("Thông báo lỗi: " + xhr.responseText);
        }
    });
}

function checkPassword(password, passwordError, passwordTextError, fullName, account, email) {
    var passwordPattern = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[*\-#!])[A-Za-z\d*\-#!]{8,}$/;
    if (!passwordPattern.test(password)) {
        passwordError.style.display = "block";
        passwordTextError.textContent = "Mật khẩu phải có ít nhất 8 ký tự bao gồm chữ số, chữ cái thường, chữ cái hoa và ký tự đặc biệt * - or #!";
    } else {
        sendRegistrationData(fullName, account, password, email);
    }
    return false;
}

function sendRegistrationData(fullName, account, password, email) {
    var csrfToken = document.querySelector('meta[name="_csrf"]').getAttribute('content');
    var csrfHeader = document.querySelector('meta[name="_csrf_header"]').getAttribute('content');
    var csrfTokenHeader = {};
    csrfTokenHeader[csrfHeader] = csrfToken;

    $.ajax({
        type: "POST",
        url: "/cosmetic/register",
        data: {
            fullName: fullName, account: account, password: password, email: email
        },
        headers: csrfTokenHeader,
        success: function (response) {
            var customerId = response.customer_id;
            var customerName = response.fullname;
            var customerAccount = response.account;

            // 2. Tạo cookie
            var customerData = {
                customerId: customerId,
                customerName: customerName,
                customerAccount: customerAccount
            };
            var cookieValue = JSON.stringify(customerData);

            // 3. Đặt cookie
            document.cookie = "customerData=" + encodeURIComponent(cookieValue) + "; path=/";
            document.getElementById('id02').style.display='none'
            showLoader();
            window.location.reload();
        },
        error: function (xhr, status, error) {
            console.error("Lỗi khi gửi yêu cầu: " + error);
            console.log("Mã lỗi: " + xhr.status);
            console.log("Thông báo lỗi: " + xhr.responseText);
        }
    });
}
function showNotification() {
    var notificationHTML = `
        <div id="notification" class="notification">
            <div class="notification-svg text-center">
                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-check-circle" viewBox="0 0 16 16">
                    <path d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14m0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16"/>
                    <path d="m10.97 4.97-.02.022-3.473 4.425-2.093-2.094a.75.75 0 0 0-1.06 1.06L6.97 11.03a.75.75 0 0 0 1.079-.02l3.992-4.99a.75.75 0 0 0-1.071-1.05"/>
                </svg>
            </div>
            <div class="text-center">Đăng kí tài khoản thành công</div>
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
function showLoader() {
    var loader = document.querySelector('.loader');
    var overlay = document.querySelector('.loader-overlay');
    loader.style.display = 'block';
    overlay.style.display = 'block';
    setTimeout(function() {
        loader.style.display = 'none';
        overlay.style.display = 'none';
    }, 2500); // 2 giây
}

function checkLoginAndProceed(checkoutUrl) {
    $.ajax({
        url: "/cosmetic/check-login-checkout",
        type: "GET",
        success: function(response) {
            if (response === false) {
                document.getElementById('id01').style.display='block';
            } else {
                alert("okok")
                // window.location.href = '/cosmetic/checkout';
            }
        },
        error: function(xhr, status, error) {
            console.log("Error:", error);
        }
    });
}

$(document).ready(function () {
    $('#loginButton').click(function (event) {
        event.preventDefault();
        var account = $('#costumer').val();
        var password = $('#pwd').val();
        var csrfToken = document.querySelector('meta[name="_csrf"]').getAttribute('content');
        var csrfHeader = document.querySelector('meta[name="_csrf_header"]').getAttribute('content');
        var csrfTokenHeader = {};
        csrfTokenHeader[csrfHeader] = csrfToken;

        $.ajax({
            type: 'POST',
            url: '/cosmetic/login',
            data: {
                account: account,
                password: password
            },
            headers: csrfTokenHeader,
            success: function(response) {
                var customerId = response.customer_id;
                var customerName = response.fullname;
                var customerAccount = response.account;

                // 2. Tạo cookie
                var customerData = {
                    customerId: customerId,
                    customerName: customerName,
                    customerAccount: customerAccount
                };
                var cookieValue = JSON.stringify(customerData);

                // 3. Đặt cookie
                document.cookie = "customerData=" + encodeURIComponent(cookieValue) + "; path=/";
                document.getElementById('id02').style.display='none'
                showLoader();
                window.location.reload();
            },
            error: function(xhr, status, error) {
                $('#error-login-message').text("Tài khoản hoặc mật khẩu không đúng").css('color', 'red');
                console.error('Đăng nhập thất bại');
            }
        });
    });
});

$(document).ready(function () {
    $('#button-logout').click(function (event) {
        event.preventDefault();
        var csrfToken = document.querySelector('meta[name="_csrf"]').getAttribute('content');
        var csrfHeader = document.querySelector('meta[name="_csrf_header"]').getAttribute('content');
        var csrfTokenHeader = {};
        csrfTokenHeader[csrfHeader] = csrfToken;
        $.ajax({
            type: 'POST',
            url: '/cosmetic/logout',
            headers: csrfTokenHeader,
            success: function(response) {
                document.cookie = "customerData=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;";
                showLoader();
                window.location.reload();
            },
            error: function(xhr, status, error) {
                alert("Đăng xuất thất bại")
                console.error('Đăng xuất thất bại');
            }
        });
    });
});