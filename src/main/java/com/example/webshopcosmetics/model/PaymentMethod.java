package com.example.webshopcosmetics.model;

public enum PaymentMethod {
    CHUYEN_KHOAN_NGAN_HANG("Chuyển khoản ngân hàng"),
    THANH_TOAN_KHI_NHAN_HANG("Thanh toán khi nhận hàng (COD)"),
    THANH_TOAN_TRUC_TUYEN("Thanh toán trực tuyến"),
    MOMO("Momo"),
    ZALO_PAY("Zalo Pay"),
    VIETTEL_PAY("Viettel Pay"),
    AIRPAY("AirPay"),
    VNPAY("VNPay"),
    PAYOO("Payoo"),
    QR_CODE("Thanh toán qua mã QR"),
    THE_NAP_DIEN_THOAI("Thẻ nạp điện thoại"),
    THE_NAP_GAME("Thẻ nạp game"),
    THE_CAO_DIEN_THOAI("Thẻ cào điện thoại"),
    KHAC("Phương thức thanh toán khác");

    private final String displayName;

    PaymentMethod(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}