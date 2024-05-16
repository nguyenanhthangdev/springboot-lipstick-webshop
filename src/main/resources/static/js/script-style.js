function eyeOldTransformation(x) {
    var eyeIcon = document.getElementById(x);
    var passwordInput = document.getElementById('passwordOld');

    if (eyeIcon.classList.contains('fa-eye')) {
        eyeIcon.classList.remove('fa-eye');
        eyeIcon.classList.add('fa-eye-slash');
        passwordInput.type = 'text';
    } else {
        eyeIcon.classList.remove('fa-eye-slash');
        eyeIcon.classList.add('fa-eye');
        passwordInput.type = 'password';
    }
}

function eyeNewTransformation(x) {
    var eyeIcon = document.getElementById(x);
    var passwordInput = document.getElementById('passwordNew');

    if (eyeIcon.classList.contains('fa-eye')) {
        eyeIcon.classList.remove('fa-eye');
        eyeIcon.classList.add('fa-eye-slash');
        passwordInput.type = 'text';
    } else {
        eyeIcon.classList.remove('fa-eye-slash');
        eyeIcon.classList.add('fa-eye');
        passwordInput.type = 'password';
    }
}

function eyeNewComfirmTransformation(x) {
    var eyeIcon = document.getElementById(x);
    var passwordInput = document.getElementById('comfirmPasswordNew');

    if (eyeIcon.classList.contains('fa-eye')) {
        eyeIcon.classList.remove('fa-eye');
        eyeIcon.classList.add('fa-eye-slash');
        passwordInput.type = 'text';
    } else {
        eyeIcon.classList.remove('fa-eye-slash');
        eyeIcon.classList.add('fa-eye');
        passwordInput.type = 'password';
    }
}

function eyeTransformation(x) {
    var eyeIcon = document.getElementById(x);
    var passwordInput = document.getElementById('signin-password');

    if (eyeIcon.classList.contains('fa-eye-slash')) {
        eyeIcon.classList.remove('fa-eye-slash');
        eyeIcon.classList.add('fa-eye');
        passwordInput.type = 'text';
    } else {
        eyeIcon.classList.remove('fa-eye');
        eyeIcon.classList.add('fa-eye-slash');
        passwordInput.type = 'password';
    }
}

function systemFixBug() {
    showNotificationFixBugSystem();
}
function showNotificationFixBugSystem() {
    var notificationHTML = `
        <div id="notification" class="notification text-center">
            <div class="notification-svg">
            <svg xmlns="http://www.w3.org/2000/svg" width="30" height="30" fill="currentColor" class="bi bi-geo-alt text-light" viewBox="0 0 16 16">
              <path d="M12.166 8.94c-.524 1.062-1.234 2.12-1.96 3.07A32 32 0 0 1 8 14.58a32 32 0 0 1-2.206-2.57c-.726-.95-1.436-2.008-1.96-3.07C3.304 7.867 3 6.862 3 6a5 5 0 0 1 10 0c0 .862-.305 1.867-.834 2.94M8 16s6-5.686 6-10A6 6 0 0 0 2 6c0 4.314 6 10 6 10"/>
              <path d="M8 8a2 2 0 1 1 0-4 2 2 0 0 1 0 4m0 1a3 3 0 1 0 0-6 3 3 0 0 0 0 6"/>
            </svg>
            </div>
            <div class="text-center">HỆ THÔNG ĐANG ĐƯỢC NÂNG CẤP, VUI LÒNG THỬ LẠI SAU</div>
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