function openTheTableToSelectTheReasonForCancelingTheOrder(reason) {
    var html = ""
    html +=
        '<div class="mt-3 mb-2">' +
        '<input type="radio" name="reason-for-cancellation" data-order_id="' + reason + '" value="THAY_DOI_DIA_CHI" class="d-inline-block mr-3"/>' +
        '<span>Tôi muốn cập nhật địa chỉ/sđt nhận hàng</span>' +
        '</div>' +
        '<div class="mt-2 mb-2">' +
        '<input type="radio" name="reason-for-cancellation" data-order_id="' + reason + '" value="THAY_DOI_SAN_PHAM" class="d-inline-block mr-3"/>' +
        '<span>Tôi muốn thay đổi sản phẩm</span>' +
        '</div>' +
        '<div class="mt-2 mb-2">' +
        '<input type="radio" name="reason-for-cancellation" data-order_id="' + reason + '" value="VAN_DE_THANH_TOAN" class="d-inline-block mr-3"/>' +
        '<span>Thủ tục thanh toán rắc rối</span>' +
        '</div>' +
        '<div class="mt-2 mb-2">' +
        '<input type="radio" name="reason-for-cancellation" data-order_id="' + reason + '" value="TIM_THAY_LUA_CHON_TOT_HON" class="d-inline-block mr-3"/>' +
        '<span>Tôi tìm thấy chỗ mua khác tốt hơn (Rẻ hơn, uy tín hơn, giao nhanh hơn…)</span>' +
        '</div>' +
        '<div class="mt-2 mb-2">' +
        '<input type="radio" name="reason-for-cancellation" data-order_id="' + reason + '" value="KHONG_CON_NHU_CAU" class="d-inline-block mr-3"/>' +
        '<span>Tôi không có nhu cầu mua nữa</span>' +
        '</div>' +
        '<div class="mt-2 mb-2">' +
        '<input type="radio" name="reason-for-cancellation" data-order_id="' + reason + '" value="LY_DO_KHAC" class="d-inline-block mr-3"/>' +
        '<span>Tôi không tìm thấy lý do hủy phù hợp</span>' +
        '</div>'
    $('.modal-body-reason').html(html)
    document.getElementById("myModalReasonForCancellation").style.display = "block";
}

function returnToTheOrdersPage() {
    document.getElementById("myModalReasonForCancellation").style.display = "none";
}

function checkReasonForCancellation() {
    var selectedReason = null;
    var orderID = 0;
    var radioButtons = document.getElementsByName('reason-for-cancellation');
    for (var i = 0; i < radioButtons.length; i++) {
        if (radioButtons[i].checked) {
            selectedReason = radioButtons[i].value;
            orderID = radioButtons[i].getAttribute('data-order_id')
            break;
        }
    }

    if (selectedReason === null) {
        showNotificationReasonForCancellation(selectedReason)
    } else {
        saveReasonForCancellation(selectedReason, orderID)
    }
}

function saveReasonForCancellation(selectedReason, orderID) {
    var csrfToken = document.querySelector('meta[name="_csrf"]').getAttribute('content');
    var csrfHeader = document.querySelector('meta[name="_csrf_header"]').getAttribute('content');
    var csrfTokenHeader = {};
    csrfTokenHeader[csrfHeader] = csrfToken;
    $.ajax({
        url: "/admin/save-reason-for-cancellation",
        type: "POST",
        data: {selectedReason: selectedReason, orderID: orderID},
        headers: csrfTokenHeader,
        success: function(response) {
            location.reload();
        },
        error: function(xhr, status, error) {
            console.error("Error:", error);
        }
    });
}

function showNotificationReasonForCancellation() {
    var notificationHTML = `
        <div id="notification" class="notification">
            <div class="notification-svg text-center">
                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-ban" viewBox="0 0 16 16">
                  <path d="M15 8a6.97 6.97 0 0 0-1.71-4.584l-9.874 9.875A7 7 0 0 0 15 8M2.71 12.584l9.874-9.875a7 7 0 0 0-9.874 9.874ZM16 8A8 8 0 1 1 0 8a8 8 0 0 1 16 0"/>
                </svg>
            </div>
            <div class="text-center">Vui lòng lựa chọn lý do hủy đơn hàng</div>
        </div>
    `;
    document.body.insertAdjacentHTML('beforeend', notificationHTML);

    setTimeout(function () {
        var notification = document.getElementById("notification");
        if (notification) {
            notification.remove();
        }
    }, 2000);
}

function deliveryConfirmation(orderID) {
    var csrfToken = document.querySelector('meta[name="_csrf"]').getAttribute('content');
    var csrfHeader = document.querySelector('meta[name="_csrf_header"]').getAttribute('content');
    var csrfTokenHeader = {};
    csrfTokenHeader[csrfHeader] = csrfToken;
    $.ajax({
        url: "/admin/delivery-confirmation",
        type: "POST",
        data: {orderID: orderID},
        headers: csrfTokenHeader,
        success: function(response) {
            location.reload();
        },
        error: function(xhr, status, error) {
            console.error("Error:", error);
        }
    });
}

function deliveredSuccessfully(orderID) {
    var csrfToken = document.querySelector('meta[name="_csrf"]').getAttribute('content');
    var csrfHeader = document.querySelector('meta[name="_csrf_header"]').getAttribute('content');
    var csrfTokenHeader = {};
    csrfTokenHeader[csrfHeader] = csrfToken;
    $.ajax({
        url: "/admin/delivered-successfully",
        type: "POST",
        data: {orderID: orderID},
        headers: csrfTokenHeader,
        success: function(response) {
            location.reload();
        },
        error: function(xhr, status, error) {
            console.error("Error:", error);
        }
    });
}