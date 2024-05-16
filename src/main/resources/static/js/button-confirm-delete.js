function confirmManufacturerDelete(manufacturerId) {
    Swal.fire({
        title: 'XÓA NHÀ SẢN XUẤT',
        text: "Bạn có chắc chắn muốn xóa?",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#15a362',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Xóa',
        cancelButtonText: 'Hủy'
    }).then((result) => {
        if (result.isConfirmed) {
            // Nếu người dùng nhấn "Xóa", tiến hành chuyển hướng đến trang xóa banner với tham số id
            window.location.href = '/admin/manufacturer/delete-manufacturer?id=' + manufacturerId;
        }
    });
}

function confirmCategoryDelete(categoryId) {
    Swal.fire({
        title: 'XÓA DANH MỤC SẢN PHẨM',
        text: "Bạn có chắc chắn muốn xóa?",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#15a362',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Xóa',
        cancelButtonText: 'Hủy'
    }).then((result) => {
        if (result.isConfirmed) {
            // Nếu người dùng nhấn "Xóa", tiến hành chuyển hướng đến trang xóa banner với tham số id
            window.location.href = '/admin/category/delete-category?id=' + categoryId;
        }
    });
}

function confirmProductDelete(productId) {
    Swal.fire({
        title: 'XÓA SẢN PHẨM',
        text: "Bạn có chắc chắn muốn xóa?",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#15a362',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Xóa',
        cancelButtonText: 'Hủy'
    }).then((result) => {
        if (result.isConfirmed) {
            // Nếu người dùng nhấn "Xóa", tiến hành chuyển hướng đến trang xóa banner với tham số id
            window.location.href = '/admin/product/delete-product?id=' + productId;
        }
    });
}

function confirmBannerDelete(bannerId) {
    Swal.fire({
        title: 'XÓA ẢNH BANNER',
        text: "Bạn có chắc chắn muốn xóa?",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#15a362',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Xóa',
        cancelButtonText: 'Hủy'
    }).then((result) => {
        if (result.isConfirmed) {
            // Nếu người dùng nhấn "Xóa", tiến hành chuyển hướng đến trang xóa banner với tham số id
            window.location.href = '/admin/banner/delete-banner?id=' + bannerId;
        }
    });
}

function confirmUserDelete(userId) {
    Swal.fire({
        title: 'XÓA NHÂN VIÊN',
        text: "Bạn có chắc chắn muốn xóa?",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#15a362',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Xóa',
        cancelButtonText: 'Hủy'
    }).then((result) => {
        if (result.isConfirmed) {
            // Nếu người dùng nhấn "Xóa", tiến hành chuyển hướng đến trang xóa banner với tham số id
            window.location.href = '/admin/user/delete-user?id=' + userId;
        }
    });
}

function confirmCustomerDelete(customerId) {
    Swal.fire({
        title: 'XÓA TÀI KHOẢN KHÁCH HÀNG',
        text: "Bạn có chắc chắn muốn xóa?",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#15a362',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Xóa',
        cancelButtonText: 'Hủy'
    }).then((result) => {
        if (result.isConfirmed) {
            window.location.href = '/admin/customer/delete-customer?id=' + customerId;
        }
    });
}

function confirmPostsDelete(posts_id) {
    Swal.fire({
        title: 'XÓA TÀI BÀI VIẾT',
        text: "Bạn có chắc chắn muốn xóa?",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#15a362',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Xóa',
        cancelButtonText: 'Hủy'
    }).then((result) => {
        if (result.isConfirmed) {
            window.location.href = '/admin/posts/delete-posts?id=' + posts_id;
        }
    });
}