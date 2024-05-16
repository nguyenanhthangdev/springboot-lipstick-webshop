function validateProduct() {
    var price = document.getElementById("price").value;
    var priceError = document.getElementById("priceError");
    var name = document.getElementById("name").value.trim();
    var nameError = document.getElementById("nameError");
    var discountValue = document.getElementById("discountValue").value;
    var discountValueError = document.getElementById("discountValueError");

    // Kiểm tra xem tên sản phẩm có trống hoặc chỉ chứa dấu cách không
    if (name === "" || /^\s+$/.test(name)) {
        nameError.style.display = "block"; // Hiển thị thông báo lỗi
        return false; // Ngăn chặn việc submit form
    } else {
        nameError.style.display = "none"; // Ẩn thông báo lỗi nếu tên sản phẩm hợp lệ
    }

    // Kiểm tra xem người dùng đã bỏ qua nhập trường giảm giá hay không
    if (!discountValue) {
        discountValueError.style.display = "none"; // Ẩn thông báo lỗi nếu giá trị hợp lệ hoặc không có giá trị
    } else {
        // Kiểm tra xem giá trị nhập vào có phải là số không
        if (!/^\d+$/.test(discountValue)) {
            discountValueError.style.display = "block"; // Hiển thị thông báo lỗi
            return false; // Ngăn chặn việc submit form
        } else {
            discountValueError.style.display = "none"; // Ẩn thông báo lỗi nếu giá trị hợp lệ
        }
    }

    if (!/^\d+$/.test(price)) {
        priceError.style.display = "block"; // Hiển thị thông báo lỗi
        return false; // Ngăn chặn việc submit form
    } else {
        priceError.style.display = "none"; // Ẩn thông báo lỗi nếu giá tiền hợp lệ
    }

    return true; // Tiếp tục submit form nếu không có lỗi
}

function validateManufacturer() {
    var name = document.getElementById("name").value;
    var nameError = document.getElementById("nameError");

    // Kiểm tra xem tên sản phẩm có trống hoặc chỉ chứa dấu cách không
    if (name === "" || /^\s+$/.test(name)) {
        nameError.style.display = "block"; // Hiển thị thông báo lỗi
        return false; // Ngăn chặn việc submit form
    } else {
        nameError.style.display = "none"; // Ẩn thông báo lỗi nếu tên sản phẩm hợp lệ
        return true; // Tiếp tục submit form nếu không có lỗi
    }
}

function validateCategory() {
    var name = document.getElementById("name").value;
    var nameError = document.getElementById("nameError");

    // Kiểm tra xem tên sản phẩm có trống hoặc chỉ chứa dấu cách không
    if (name === "" || /^\s+$/.test(name)) {
        nameError.style.display = "block"; // Hiển thị thông báo lỗi
        return false; // Ngăn chặn việc submit form
    } else {
        nameError.style.display = "none"; // Ẩn thông báo lỗi nếu tên sản phẩm hợp lệ
        return true; // Tiếp tục submit form nếu không có lỗi
    }
}

function validateUser() {
    var fullName = document.getElementById("fullname").value.trim();
    var fullNameError = document.getElementById("fullNameError");
    var email = document.getElementById("email").value.trim();
    var emailError = document.getElementById("emailError");
    var phone = document.getElementById("phone").value.trim();
    var phoneError = document.getElementById("phoneError");
    var userName = document.getElementById("username").value.trim();
    var userNameError = document.getElementById("userNameError");
    var passWord = document.getElementById("password").value.trim();
    var passWordError = document.getElementById("passWordError");

    if (fullName === "" || /^\s+$/.test(fullName)) {
        fullNameError.style.display = "block";
        return false;
    } else {
        fullNameError.style.display = "none";
    }

    if (email) {
        var emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        if (!emailPattern.test(email)) {
            emailError.style.display = "block"; // Hiển thị thông báo lỗi
            return false; // Ngăn chặn việc submit form
        } else {
            emailError.style.display = "none"; // Ẩn thông báo lỗi nếu địa chỉ email hợp lệ
        }
    } else {
        emailError.style.display = "none"; // Ẩn thông báo lỗi nếu không có địa chỉ email
    }

    var phonePattern = /^(0[1-9])+([0-9]{8,9})\b$/;
    // Kiểm tra xem số điện thoại có đúng định dạng của Việt Nam không
    if (!phonePattern.test(phone)) {
        phoneError.style.display = "block"; // Hiển thị thông báo lỗi
        return false; // Ngăn chặn việc submit form
    } else {
        phoneError.style.display = "none"; // Ẩn thông báo lỗi nếu số điện thoại hợp lệ
    }

    var userNamePattern = /^[a-zA-Z0-9]{6,}$/ // Định dạng cho username (ít nhất 6 ký tự và không chứa ký tự đặc biệt)
    if (!userNamePattern.test(userName)) {
        userNameError.style.display = "block"; // Hiển thị thông báo lỗi
        return false; // Ngăn chặn việc submit form
    } else {
        userNameError.style.display = "none"; // Ẩn thông báo lỗi nếu username hợp lệ
    }

    var passWordPattern = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[^\w\s])(?=.*\S).{8,}$/;
    if (!passWordPattern.test(passWord)) {
        passWordError.style.display = "block"; // Hiển thị thông báo lỗi
        return false; // Ngăn chặn việc submit form
    } else {
        passWordError.style.display = "none"; // Ẩn thông báo lỗi nếu mật khẩu hợp lệ
    }

    return true;
}

function validateChangePassword() {
    var passwordOld = document.getElementById("passwordOld").value;
    var passwordOldError = document.getElementById("passwordOldError");
    var passwordOldTextError = document.getElementById("passwordOldTextError");

    var passwordNew = document.getElementById("passwordNew").value;
    var passwordNewError = document.getElementById("passwordNewError");
    var passwordNewTextError = document.getElementById("passwordNewTextError");

    var comfirmPasswordNew = document.getElementById("comfirmPasswordNew").value;
    var comfirmPasswordNewError = document.getElementById("comfirmPasswordNewError");
    var comfirmPasswordNewTextError = document.getElementById("comfirmPasswordNewTextError");

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
        url: "/admin/user/checkPasswordOld",
        data: { passwordOld: passwordOld },
        headers: csrfTokenHeader, // Thêm CSRF token vào header của yêu cầu
        success: function (response) {
            if (response === "true") {
                document.getElementById("form-change-password").submit(); // Submit form
            } else {
                passwordOldError.style.display = "block";
                passwordOldTextError.textContent = "Mật khẩu cũ không chính xác.";
                return false;
            }
        },
        error: function (xhr, status, error) {
            console.error("Lỗi khi gửi yêu cầu: " + error);
            console.log("Mã lỗi: " + xhr.status);
            console.log("Thông báo lỗi: " + xhr.responseText);
        }
    });
    return false;
}

function validateProfileUser() {
    var fullname = document.getElementById("fullname").value;
    var fullnameError = document.getElementById("fullnameError");
    var fullnameTextError = document.getElementById("fullnameTextError");

    var email = document.getElementById("email").value.trim();
    var emailError = document.getElementById("emailError");
    var emailTextError = document.getElementById("emailTextError");

    var phone = document.getElementById("phone").value.trim();
    var phoneError = document.getElementById("phoneError");
    var phoneTextError = document.getElementById("phoneTextError");

    fullnameError.style.display = "none"; emailError.style.display = "none"; phoneError.style.display = "none";

    // Kiểm tra xem tên sản phẩm có trống hoặc chỉ chứa dấu cách không
    if (username === "" || /^\s+$/.test(fullname)) {
        fullnameError.style.display = "block"; // Hiển thị phần tử .error-message
        fullnameTextError.textContent = "Tên nhân viên không hợp lệ"; // Đặt nội dung thông báo lỗi
        return false;
    } else {
        fullnameError.style.display = "none"; // Ẩn thông báo lỗi nếu tên sản phẩm hợp lệ
    }

    if (email) {
        var emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        if (!emailPattern.test(email)) {
            emailError.style.display = "block"; // Hiển thị phần tử .error-message
            emailTextError.textContent = "Email không hợp lệ"; // Đặt nội dung thông báo lỗi
            return false;
        } else {
            emailError.style.display = "none"; // Ẩn thông báo lỗi nếu địa chỉ email hợp lệ
        }
    } else {
        emailError.style.display = "none"; // Ẩn thông báo lỗi nếu không có địa chỉ email
    }

    var phonePattern = /^(0[1-9])+([0-9]{8,9})\b$/;
    // Kiểm tra xem số điện thoại có đúng định dạng của Việt Nam không
    if (!phonePattern.test(phone)) {
        phoneError.style.display = "block"; // Hiển thị phần tử .error-message
        phoneTextError.textContent = "Số điện thoại không hợp lệ"; // Đặt nội dung thông báo lỗi
        return false;
    } else {
        phoneError.style.display = "none"; // Ẩn thông báo lỗi nếu số điện thoại hợp lệ
    }

    return true;
}

function validateWebshopSettings() {
    var name = document.getElementById("name").value.trim();
    var nameError = document.getElementById("nameError");
    var nameTextError = document.getElementById("nameTextError");

    var address = document.getElementById("address").value.trim();
    var addressError = document.getElementById("addressError");
    var addressTextError = document.getElementById("addressTextError");

    var googleMapEmbed = document.getElementById("googleMapEmbed").value.trim();
    var googleMapEmbedError = document.getElementById("googleMapEmbedError");
    var googleMapEmbedTextError = document.getElementById("googleMapEmbedTextError");

    var email = document.getElementById("email").value.trim().trim();
    var emailError = document.getElementById("emailError");
    var emailTextError = document.getElementById("emailTextError");

    var phone = document.getElementById("phone").value.trim();
    var phoneError = document.getElementById("phoneError");
    var phoneTextError = document.getElementById("phoneTextError");

    nameError.style.display = "none"; emailError.style.display = "none"; phoneError.style.display = "none";

    if (name === "") {
        nameError.style.display = "block"; // Hiển thị phần tử .error-message
        nameTextError.textContent = "Tên webshop không hợp lệ"; // Đặt nội dung thông báo lỗi
        return false;
    } else {
        nameError.style.display = "none"; // Ẩn thông báo lỗi nếu tên sản phẩm hợp lệ
    }

    if (address === "") {
        addressError.style.display = "block"; // Hiển thị phần tử .error-message
        addressTextError.textContent = "Địa chỉ không hợp lệ"; // Đặt nội dung thông báo lỗi
        return false;
    } else {
        addressError.style.display = "none"; // Ẩn thông báo lỗi nếu tên sản phẩm hợp lệ
    }

    // if (googleMapEmbed === "") {
    //     googleMapEmbedError.style.display = "block"; // Hiển thị phần tử .error-message
    //     googleMapEmbedTextError.textContent = ""; // Đặt nội dung thông báo lỗi
    //     return false;
    // } else {
    //     googleMapEmbedError.style.display = "none"; // Ẩn thông báo lỗi nếu tên sản phẩm hợp lệ
    // }

    if (email) {
        var emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        if (!emailPattern.test(email)) {
            emailError.style.display = "block"; // Hiển thị phần tử .error-message
            emailTextError.textContent = "Email không hợp lệ"; // Đặt nội dung thông báo lỗi
            return false;
        } else {
            emailError.style.display = "none"; // Ẩn thông báo lỗi nếu địa chỉ email hợp lệ
        }
    } else {
        emailError.style.display = "none"; // Ẩn thông báo lỗi nếu không có địa chỉ email
    }

    var phonePattern = /^(0[1-9])+([0-9]{8,9})\b$/;
    // Kiểm tra xem số điện thoại có đúng định dạng của Việt Nam không
    if (!phonePattern.test(phone)) {
        phoneError.style.display = "block"; // Hiển thị phần tử .error-message
        phoneTextError.textContent = "Số điện thoại không hợp lệ"; // Đặt nội dung thông báo lỗi
        return false;
    } else {
        phoneError.style.display = "none"; // Ẩn thông báo lỗi nếu số điện thoại hợp lệ
    }

    return true;
}

// function validateCustomer() {
//     var fullName = document.getElementById("fullName").value.trim();
//     var fullNameError = document.getElementById("fullNameError");
//     var fullNameTextError = document.getElementById("fullNameTextError");
//
//     var account = document.getElementById("account").value.trim();
//     var accountError = document.getElementById("accountError");
//     var accountTextError = document.getElementById("accountTextError");
//
//     var password = document.getElementById("password").value.trim();
//     var passwordError = document.getElementById("passwordError");
//     var passwordTextError = document.getElementById("passwordTextError");
//
//     fullNameError.style.display = "none"; accountError.style.display = "none"; passwordError.style.display = "none";
//
//     if (fullName === "") {
//         fullNameError.style.display = "block";
//         fullNameTextError.textContent = "Họ tên không hợp lệ";
//         return false;
//     } else {
//         fullNameError.style.display = "none";
//     }
//
//     var checkAccount = false;
//     var accountPattern = /^[a-zA-Z0-9]{6,}$/ // Định dạng cho account (ít nhất 6 ký tự và không chứa ký tự đặc biệt)
//     if (!accountPattern.test(account)) {
//         accountError.style.display = "block";
//         accountTextError.textContent = "Tên đăng nhập chứa ít nhất 6 ký tự và không chứa ký tự đặc biệt";
//         checkAccount = true;
//         return false;
//     } else {
//         accountError.style.display = "none";
//     }
//
//     sendAccountCheckRequest(account, accountError, accountTextError, password, passwordError, passwordTextError, fullName);
//     return false;
// }
//
// function sendAccountCheckRequest(account, accountError, accountTextError, password, passwordError, passwordTextError, fullName) {
//     var csrfToken = document.querySelector('meta[name="_csrf"]').getAttribute('content');
//     var csrfHeader = document.querySelector('meta[name="_csrf_header"]').getAttribute('content');
//
//     var csrfTokenHeader = {};
//     csrfTokenHeader[csrfHeader] = csrfToken;
//
//     $.ajax({
//         type: "POST",
//         url: "/cosmetic/check-account-exists",
//         data: { account: account },
//         headers: csrfTokenHeader,
//         success: function (response) {
//             if (response === "true") {
//                 checkPassword(password, passwordError, passwordTextError, fullName, account);
//             } else {
//                 accountError.style.display = "block";
//                 accountTextError.textContent = "Tên đăng nhập đã tồn tại";
//             }
//         },
//         error: function (xhr, status, error) {
//             console.error("Lỗi khi gửi yêu cầu: " + error);
//             console.log("Mã lỗi: " + xhr.status);
//             console.log("Thông báo lỗi: " + xhr.responseText);
//         }
//     });
// }
//
// function checkPassword(password, passwordError, passwordTextError, fullName, account) {
//     var passwordPattern = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[*\-#!])[A-Za-z\d*\-#!]{8,}$/;
//     if (!passwordPattern.test(password)) {
//         passwordError.style.display = "block";
//         passwordTextError.textContent = "Mật khẩu phải có ít nhất 8 ký tự bao gồm chữ số, chữ cái thường, chữ cái hoa và ký tự đặc biệt * - or #!";
//     } else {
//         sendRegistrationData(fullName, account, password);
//     }
//     return false;
// }
//
// function sendRegistrationData(fullName, account, password) {
//     var csrfToken = document.querySelector('meta[name="_csrf"]').getAttribute('content');
//     var csrfHeader = document.querySelector('meta[name="_csrf_header"]').getAttribute('content');
//     var csrfTokenHeader = {};
//     csrfTokenHeader[csrfHeader] = csrfToken;
//
//     $.ajax({
//         type: "POST",
//         url: "/cosmetic/register",
//         data: {
//             fullName: fullName, account: account, password: password
//         },
//         headers: csrfTokenHeader,
//         success: function (response) {
//             var customerId = response.customer_id;
//             var customerName = response.fullname;
//             var customerAccount = response.account;
//
//             // 2. Tạo cookie
//             var customerData = {
//                 customerId: customerId,
//                 customerName: customerName,
//                 customerAccount: customerAccount
//             };
//             var cookieValue = JSON.stringify(customerData);
//
//             // 3. Đặt cookie
//             document.cookie = "customerData=" + encodeURIComponent(cookieValue) + "; path=/";
//             document.getElementById('id02').style.display='none'
//             showLoader();
//         },
//         error: function (xhr, status, error) {
//             console.error("Lỗi khi gửi yêu cầu: " + error);
//             console.log("Mã lỗi: " + xhr.status);
//             console.log("Thông báo lỗi: " + xhr.responseText);
//         }
//     });
// }
// function showNotification() {
//     var notificationHTML = `
//         <div id="notification" class="notification">
//             <div class="notification-svg text-center">
//                 <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-check-circle" viewBox="0 0 16 16">
//                     <path d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14m0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16"/>
//                     <path d="m10.97 4.97-.02.022-3.473 4.425-2.093-2.094a.75.75 0 0 0-1.06 1.06L6.97 11.03a.75.75 0 0 0 1.079-.02l3.992-4.99a.75.75 0 0 0-1.071-1.05"/>
//                 </svg>
//             </div>
//             <div class="text-center">Đăng kí tài khoản thành công</div>
//         </div>
//     `;
//
//     // Thêm chuỗi HTML vào body
//     document.body.insertAdjacentHTML('beforeend', notificationHTML);
//
//     // Đặt thời gian để ẩn thông báo sau 2 giây
//     setTimeout(function () {
//         var notification = document.getElementById("notification");
//         if (notification) {
//             notification.remove();
//         }
//     }, 2000); // 2 giây
// }
// function showLoader() {
//     var loader = document.querySelector('.loader');
//     var overlay = document.querySelector('.loader-overlay');
//     loader.style.display = 'block';
//     overlay.style.display = 'block';
//     setTimeout(function() {
//         loader.style.display = 'none';
//         overlay.style.display = 'none';
//     }, 2500); // 2 giây
// }
function hideErrorMessage(button) {
    var errorMessage = button.parentElement;
    errorMessage.style.display = 'none';
}

function hideSuccessMessage(button) {
    var successMessage = button.parentElement;
    successMessage.style.display = 'none';
}