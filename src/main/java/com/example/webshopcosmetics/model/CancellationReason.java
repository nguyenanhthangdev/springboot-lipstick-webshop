package com.example.webshopcosmetics.model;

public enum CancellationReason {
    THAY_DOI_DIA_CHI("Tôi muốn cập nhật địa chỉ/sđt nhận hàng"),
    THAY_DOI_SAN_PHAM("Tôi muốn thay đổi sản phẩm"),
    VAN_DE_THANH_TOAN("Thủ tục thanh toán rắc rối"),
    TIM_THAY_LUA_CHON_TOT_HON("Tôi tìm thấy chỗ mua khác tốt hơn (Rẻ hơn, uy tín hơn, giao nhanh hơn…)"),
    KHONG_CO_NHU_CAU("Tôi không có nhu cầu mua nữa"),
    LY_DO_KHAC("Tôi không tìm thấy lý do hủy phù hợp");

    private final String displayName;

    CancellationReason(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
