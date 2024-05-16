var avatarUserElement = document.getElementById('avatar-user');
var srcValueAvatarUser = avatarUserElement.getAttribute("src");
var lastIndexAvatarUser = srcValueAvatarUser.lastIndexOf("/");
var folderPathAvatarUser = srcValueAvatarUser.substring(0, lastIndexAvatarUser);
var folderPathTempAvatarUser = folderPathAvatarUser.replaceAll("/", ".");
var fileImageNameAvatarUser = srcValueAvatarUser.substring(lastIndex + 1);
avatarUserElement.src = "/get-image/" + folderPathTempAvatarUser + "/" + fileImageNameAvatarUser;

// var idGallery = 0;
// $("#rowAdder").click(function () {
//     idGallery++;
//     var newRowAdd =
//         '<tr>' +
//         '<td>' +
//         '<a class="btn btn-sm btn-danger btn-filemanager btn-delete-gallery">' +
//         '<span>' +
//         '<svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor" class="bi bi-dash-lg text-white" viewBox="0 0 16 16">' +
//         '<path fill-rule="evenodd" d="M2 8a.5.5 0 0 1 .5-.5h11a.5.5 0 0 1 0 1h-11A.5.5 0 0 1 2 8"/>' +
//         '</svg>' +
//         '</span>' +
//         '</a>' +
//         '</td>' +
//         '<td>' +
//         '<div class="col-sm">' +
//         '<a id="test-gallery-' + idGallery + '" data-toggle="image" class="test-img-thumbnail" onclick="showGalleryButtons(' + idGallery + ')"><img class="img-thumbnail avt-gallery" id="avt-image-' + idGallery + '" src="/images/noImage/img.png" alt="" title=""/></a>' +
//         '<input type="hidden" name="imageGallery" value="/images/noImage/img.png" id="input-avt-image-' + idGallery + '" />' +
//         '<div class="buttons-gallery-' + idGallery + ' hide-show-filemanager-button" style="display: none;">' +
//         '<a class="btn btn-sm btn-primary btn-filemanager me-1" onclick="handleAddGalleryButtonClick(' + idGallery + ')" data-bs-toggle="modal" data-bs-target="#myModalGallery">' +
//         '<span>' +
//         '<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-pencil-fill text-white" viewBox="0 0 16 16">' +
//         '<path d="M12.854.146a.5.5 0 0 0-.707 0L10.5 1.793 14.207 5.5l1.647-1.646a.5.5 0 0 0 0-.708zm.646 6.061L9.793 2.5 3.293 9H3.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.207zm-7.468 7.468A.5.5 0 0 1 6 13.5V13h-.5a.5.5 0 0 1-.5-.5V12h-.5a.5.5 0 0 1-.5-.5V11h-.5a.5.5 0 0 1-.5-.5V10h-.5a.5.5 0 0 1-.175-.032l-.179.178a.5.5 0 0 0-.11.168l-2 5a.5.5 0 0 0 .65.65l5-2a.5.5 0 0 0 .168-.11z"/>' +
//         '</svg>' +
//         '<span>' +
//         '</a>' +
//         '<a class="btn btn-sm btn-danger btn-delete btn-filemanager me-1" onclick="deleteImagePresentButtonClick(' + idGallery + ')">' +
//         '<span>' +
//         '<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-trash-fill text-white" viewBox="0 0 16 16">' +
//         '<path d="M2.5 1a1 1 0 0 0-1 1v1a1 1 0 0 0 1 1H3v9a2 2 0 0 0 2 2h6a2 2 0 0 0 2-2V4h.5a1 1 0 0 0 1-1V2a1 1 0 0 0-1-1H10a1 1 0 0 0-1-1H7a1 1 0 0 0-1 1zm3 4a.5.5 0 0 1 .5.5v7a.5.5 0 0 1-1 0v-7a.5.5 0 0 1 .5-.5M8 5a.5.5 0 0 1 .5.5v7a.5.5 0 0 1-1 0v-7A.5.5 0 0 1 8 5m3 .5v7a.5.5 0 0 1-1 0v-7a.5.5 0 0 1 1 0"/>' +
//         '</svg>' +
//         '<span>' +
//         '</a>' +
//         '</div>' +
//         '</div>' +
//         '</td>' +
//         '</tr>';
//
//     $('#galleryBody').append(newRowAdd);
// });
//
// $("body").on("click", ".btn-delete-gallery", function () {
//     $(this).closest("tr").remove();
// });
//
// function showGalleryButtons(id) {
//     var divId = 'buttons-gallery-' + id;
//     var divElement = document.querySelector('.' + divId);
//     var displayStyle = divElement.style.display;
//     if (displayStyle === 'block') {
//         $('.' + divId).css('display', 'none');
//     } else {
//         $('.' + divId).css('display', 'block');
//     }
// }
//
// function handleAddGalleryButtonClick(id) {
//     processDataGalleryBeforeModal(id);
// }
//
// function chooseImageGalleryFromFileManager(urlImage) {
//     var myModalGallery = $('#myModalGallery');
//     myModalGallery.modal('hide');
//
//     var galleryTr = document.getElementById("modal-body-url-directory-gallery-tr").value;
//     var divId = 'buttons-gallery-' + galleryTr;
//     var divElement = document.querySelector('.' + divId);
//     var displayStyle = divElement.style.display;
//     if (displayStyle === 'block') {
//         $('.' + divId).css('display', 'none');
//     } else {
//         $('.' + divId).css('display', 'none');
//     }
//
//     var element = document.querySelector('.buttons.hide-show-filemanager-button');
//     element.style.display = 'none';
//
//     var lastIndex = urlImage.lastIndexOf("/");
//     var folderPath = urlImage.substring(0, lastIndex);
//     var folderPathTemp = folderPath.replaceAll("/", ".");
//     var fileImageName = urlImage.substring(lastIndex + 1);
//
//     var avtImage = document.getElementById('avt-image-' + galleryTr);
//     var inputAvtImage = document.getElementById('input-avt-image-' + galleryTr);
//
//     avtImage.src = "/get-image/" + folderPathTemp + "/" + fileImageName;
//
//     inputAvtImage.value = urlImage;
// }
//
// // Hàm xử lý dữ liệu trước khi mở modal
// function processDataGalleryBeforeModal(id) {
//     $.ajax({
//         url: "/image-manager",
//         type: "GET",
//         success: function (response) {
//             var inputUrlDirectory = document.getElementById("modal-body-url-directory-gallery");
//             inputUrlDirectory.value = response.urlDirectory;
//             var inputUrlDirectory = document.getElementById("modal-body-url-directory-gallery-tr");
//             inputUrlDirectory.value = id;
//             $(".modal-body-directory-gallery").empty();
//             $.each(response.directories, function (index, directory) {
//                 var urlDirectoryName = response.urlDirectory + "/" + directory;
//                 var listItem = $("<div>").html(
//                     "<a href='#' data-name='" + directory + "' onclick='loadDirectoryContentGallery(this.getAttribute(\"data-name\"))'>" +
//                     "<div><svg xmlns=\"http://www.w3.org/2000/svg\" width=\"60\" height=\"60\" fill=\"currentColor\" class=\"bi bi-folder-fill\" viewBox=\"0 0 16 16\">" +
//                     "<path d=\"M9.828 3h3.982a2 2 0 0 1 1.992 2.181l-.637 7A2 2 0 0 1 13.174 14H2.825a2 2 0 0 1-1.991-1.819l-.637-7a2 2 0 0 1 .342-1.31L.5 3a2 2 0 0 1 2-2h3.672a2 2 0 0 1 1.414.586l.828.828A2 2 0 0 0 9.828 3m-8.322.12q.322-.119.684-.12h5.396l-.707-.707A1 1 0 0 0 6.172 2H2.5a1 1 0 0 0-1 .981z\"/>" +
//                     "</svg></div></a>" +
//                     "<div><input type='checkbox' name='path[]' value='" + urlDirectoryName + "'><label for='directoryCheckbox" + index + "'>" + directory + "</label></div>"
//                 );
//
//                 var directoryDiv = $("<div>").append(listItem).addClass("col-md-3 text-center").css({"margin-bottom": "17px"});
//                 $(".modal-body-directory-gallery").append(directoryDiv);
//             });
//
//             $(".modal-body-image-gallery").empty();
//             $.each(response.images, function (index, image) {
//                 var urlImageName = response.urlDirectory;
//                 var url = urlImageName.replaceAll("/", ".");
//                 var x = "/images/img"
//                 var html = $("<div>").html(
//                     '<div><img src="' + '/get-image-upload/' + url + "/" + image.name + '" alt="Hình Ảnh"' + 'style="' + 'width: 110px; height: 110px"' + 'onclick="chooseImageGalleryFromFileManager(' + "'" + urlImageName + "/" + image.name + "'" + ')"' + '></div>' +
//                     "<div><input type='checkbox' name='path[]' value='" + urlImageName + "/" + image.name + "'><label>" + image.name + "</label></div>"
//                 );
//                 var imageDiv = $("<div>").append(html).addClass("col-md-3 text-center img-thumbnail").css({
//                     "margin-bottom": "17px",
//                     "object-fit": "cover",
//                     "image-rendering": "auto"
//                 });
//                 $(".modal-body-image-gallery").append(imageDiv)
//             });
//         },
//         error: function (xhr, status, error) {
//             console.error(error);
//         }
//     });
// }
//
// function loadDirectoryContentGallery(directoryName) {
//     var inputDirectoryValue = document.getElementById("modal-body-url-directory-gallery").value;
//     var urlDirectory = inputDirectoryValue + "/" + directoryName;
//     var galleryTr = document.getElementById("modal-body-url-directory-gallery-tr").value;
//     // var urlDirectory = inputDirectoryValue + "/" + directoryName;
//     $.ajax({
//         url: "/get-directory-in-directory",
//         type: "GET",
//         data: {directoryName: directoryName, urlDirectory: urlDirectory},
//         success: function (response) {
//             var inputElement = document.getElementById("modal-body-url-directory-gallery");
//             inputElement.value = response.urlDirectory;
//             galleryTr.value = galleryTr;
//
//             $(".modal-body-directory-gallery").empty();
//             // Hiển thị danh sách thư mục
//             $.each(response.directories, function (index, directory) {
//                 var urlDirectoryName = response.urlDirectory + "/" + directory;
//                 var listItem = $("<div>").html(
//                     "<a href='#' data-name='" + directory + "' onclick='loadDirectoryContentGallery(this.getAttribute(\"data-name\"))'>" +
//                     "<div><svg xmlns=\"http://www.w3.org/2000/svg\" width=\"60\" height=\"60\" fill=\"currentColor\" class=\"bi bi-folder-fill\" viewBox=\"0 0 16 16\">" +
//                     "<path d=\"M9.828 3h3.982a2 2 0 0 1 1.992 2.181l-.637 7A2 2 0 0 1 13.174 14H2.825a2 2 0 0 1-1.991-1.819l-.637-7a2 2 0 0 1 .342-1.31L.5 3a2 2 0 0 1 2-2h3.672a2 2 0 0 1 1.414.586l.828.828A2 2 0 0 0 9.828 3m-8.322.12q.322-.119.684-.12h5.396l-.707-.707A1 1 0 0 0 6.172 2H2.5a1 1 0 0 0-1 .981z\"/>" +
//                     "</svg></div></a>" +
//                     "<div><input type='checkbox' name='path[]' value='" + urlDirectoryName + "'><label for='directoryCheckbox" + index + "'>" + directory + "</label></div>"
//                 );
//                 var directoryDiv = $("<div>").append(listItem).addClass("col-md-3 text-center").css({"margin-bottom": "17px"});
//                 $(".modal-body-directory-gallery").append(directoryDiv);
//             });
//
//             // Xóa nội dung hiện có của phần tử
//             $(".modal-body-image-gallery").empty();
//             // Tạo HTML mới bằng Thymeleaf
//             var html = '';
//             $.each(response.images, function (index, image) {
//                 var urlImageName = response.urlDirectory;
//                 var url = urlImageName.replaceAll("/", ".");
//                 var x = "/images/img"
//                 var html = $("<div>").html(
//                     '<div><img src="' + '/get-image-upload/' + url + "/" + image.name + '" alt="Hình Ảnh"' + 'style="' + 'width: 110px; height: 110px"' + 'onclick="chooseImageGalleryFromFileManager(' + "'" + urlImageName + "/" + image.name + "'" + ')"' + '></div>' +
//                     "<div><input type='checkbox' name='path[]' value='" + urlImageName + "/" + image.name + "'><label>" + image.name + "</label></div>"
//                 );
//                 var imageDiv = $("<div>").append(html).addClass("col-md-3 text-center img-thumbnail").css({
//                     "margin-bottom": "17px",
//                     "object-fit": "cover",
//                     "image-rendering": "auto"
//                 });
//                 $(".modal-body-image-gallery").append(imageDiv)
//             });
//         },
//         error: function (xhr, status, error) {
//             console.error(xhr.responseText);
//         }
//     });
// }
//
// function loadReturnDirectoryContentGallery() {
//     var galleryTr = document.getElementById("modal-body-url-directory-gallery-tr").value;
//     var inputDirectoryValue = document.getElementById("modal-body-url-directory-gallery").value;
//     inputDirectoryValue = inputDirectoryValue.toString();
//     var rootDirectoryName = "/images";
//     if (inputDirectoryValue === rootDirectoryName) {
//         return true;
//     } else {
//         var newInputDirectoryValue = removeLastSegment(inputDirectoryValue);
//         $.ajax({
//             url: "/get-directory-in-directory",
//             type: "GET",
//             data: {directoryName: "", urlDirectory: newInputDirectoryValue},
//             success: function (response) {
//                 var inputElement = document.getElementById("modal-body-url-directory-gallery");
//                 inputElement.value = response.urlDirectory;
//                 galleryTr.value = galleryTr;
//
//                 $(".modal-body-directory-gallery").empty();
//                 // Hiển thị danh sách thư mục
//                 $.each(response.directories, function (index, directory) {
//                     var urlDirectoryName = response.urlDirectory + "/" + directory;
//                     var listItem = $("<div>").html(
//                         "<a href='#' data-name='" + directory + "' onclick='loadDirectoryContentGallery(this.getAttribute(\"data-name\"))'>" +
//                         "<div><svg xmlns=\"http://www.w3.org/2000/svg\" width=\"60\" height=\"60\" fill=\"currentColor\" class=\"bi bi-folder-fill\" viewBox=\"0 0 16 16\">" +
//                         "<path d=\"M9.828 3h3.982a2 2 0 0 1 1.992 2.181l-.637 7A2 2 0 0 1 13.174 14H2.825a2 2 0 0 1-1.991-1.819l-.637-7a2 2 0 0 1 .342-1.31L.5 3a2 2 0 0 1 2-2h3.672a2 2 0 0 1 1.414.586l.828.828A2 2 0 0 0 9.828 3m-8.322.12q.322-.119.684-.12h5.396l-.707-.707A1 1 0 0 0 6.172 2H2.5a1 1 0 0 0-1 .981z\"/>" +
//                         "</svg></div></a>" +
//                         "<div><input type='checkbox' name='path[]' value='" + urlDirectoryName + "'><label for='directoryCheckbox" + index + "'>" + directory + "</label></div>"
//                     );
//                     var directoryDiv = $("<div>").append(listItem).addClass("col-md-3 text-center").css({"margin-bottom": "17px"});
//                     $(".modal-body-directory-gallery").append(directoryDiv);
//                 });
//
//                 // Xóa nội dung hiện có của phần tử
//                 $(".modal-body-image-gallery").empty();
//                 // Tạo HTML mới bằng Thymeleaf
//                 $.each(response.images, function (index, image) {
//                     var urlImageName = response.urlDirectory;
//                     var url = urlImageName.replaceAll("/", ".");
//                     var x = "/images/img"
//                     var html = $("<div>").html(
//                         '<div><img src="' + '/get-image-upload/' + url + "/" + image.name + '" alt="Hình Ảnh"' + 'style="' + 'width: 110px; height: 110px"' + 'onclick="chooseImageGalleryFromFileManager(' + "'" + urlImageName + "/" + image.name + "'" + ')"' + '></div>' +
//                         "<div><input type='checkbox' name='path[]' value='" + urlImageName + "/" + image.name + "'><label>" + image.name + "</label></div>"
//                     );
//                     var imageDiv = $("<div>").append(html).addClass("col-md-3 text-center img-thumbnail").css({
//                         "margin-bottom": "17px",
//                         "object-fit": "cover",
//                         "image-rendering": "auto"
//                     });
//                     $(".modal-body-image-gallery").append(imageDiv)
//                 });
//             },
//             error: function (xhr, status, error) {
//                 console.error(xhr.responseText);
//             }
//         });
//     }
// }
//
// function loadDeleteDirectoryContentGallery() {
//     var galleryTr = document.getElementById("modal-body-url-directory-gallery-tr").value;
//     var selectedValues = []; // Mảng để lưu giá trị của các input đã chọn
//     // Lặp qua tất cả các checkbox
//     $("input[name='path[]']").each(function () {
//         // Kiểm tra xem checkbox có được chọn không
//         if ($(this).is(":checked")) {
//             // Lấy giá trị của checkbox đã chọn và thêm vào mảng
//             selectedValues.push($(this).val());
//         }
//     });
//     if (selectedValues === 0) {
//         alert("không")
//     }
//     var inputDirectoryValue = document.getElementById("modal-body-url-directory-gallery").value;
//     inputDirectoryValue = inputDirectoryValue.toString();
//     var data = {
//         selectedValues: JSON.stringify(selectedValues),
//         inputDirectoryValue: inputDirectoryValue
//     };
//     $.ajax({
//         url: "/delete-directory",
//         type: "GET",
//         data: data,
//         success: function (response) {
//             alert("Xóa thư mục/hình ảnh thành công!")
//             var inputElement = document.getElementById("modal-body-url-directory-gallery");
//             inputElement.value = response.urlDirectory;
//             galleryTr.value = galleryTr;
//
//             $(".modal-body-directory-gallery").empty();
//             // Hiển thị danh sách thư mục
//             $.each(response.directories, function (index, directory) {
//                 var urlDirectoryName = response.urlDirectory + "/" + directory;
//                 var listItem = $("<div>").html(
//                     "<a href='#' data-name='" + directory + "' onclick='loadDirectoryContentGallery(this.getAttribute(\"data-name\"))'>" +
//                     "<div><svg xmlns=\"http://www.w3.org/2000/svg\" width=\"60\" height=\"60\" fill=\"currentColor\" class=\"bi bi-folder-fill\" viewBox=\"0 0 16 16\">" +
//                     "<path d=\"M9.828 3h3.982a2 2 0 0 1 1.992 2.181l-.637 7A2 2 0 0 1 13.174 14H2.825a2 2 0 0 1-1.991-1.819l-.637-7a2 2 0 0 1 .342-1.31L.5 3a2 2 0 0 1 2-2h3.672a2 2 0 0 1 1.414.586l.828.828A2 2 0 0 0 9.828 3m-8.322.12q.322-.119.684-.12h5.396l-.707-.707A1 1 0 0 0 6.172 2H2.5a1 1 0 0 0-1 .981z\"/>" +
//                     "</svg></div></a>" +
//                     "<div><input type='checkbox' name='path[]' value='" + urlDirectoryName + "'><label for='directoryCheckbox" + index + "'>" + directory + "</label></div>"
//                 );
//                 var directoryDiv = $("<div>").append(listItem).addClass("col-md-3 text-center").css({"margin-bottom": "17px"});
//                 $(".modal-body-directory-gallery").append(directoryDiv);
//             });
//
//             // Xóa nội dung hiện có của phần tử
//             $(".modal-body-image-gallery").empty();
//             // Tạo HTML mới bằng Thymeleaf
//             $.each(response.images, function (index, image) {
//                 var urlImageName = response.urlDirectory;
//                 var url = urlImageName.replaceAll("/", ".");
//                 var x = "/images/img"
//                 var html = $("<div>").html(
//                     '<div><img src="' + '/get-image-upload/' + url + "/" + image.name + '" alt="Hình Ảnh"' + 'style="' + 'width: 110px; height: 110px"' + 'onclick="chooseImageGalleryFromFileManager(' + "'" + urlImageName + "/" + image.name + "'" + ')"' + '></div>' +
//                     "<div><input type='checkbox' name='path[]' value='" + urlImageName + "/" + image.name + "'><label>" + image.name + "</label></div>"
//                 );
//                 var imageDiv = $("<div>").append(html).addClass("col-md-3 text-center img-thumbnail").css({
//                     "margin-bottom": "17px",
//                     "object-fit": "cover",
//                     "image-rendering": "auto"
//                 });
//                 $(".modal-body-image-gallery").append(imageDiv)
//             });
//         },
//         error: function (xhr, status, error) {
//             alert("Xóa thư mục/hình ảnh không thành công!")
//             console.error(error);
//         }
//     });
// }
//
// function uploadImageGalleryFileManager(file) {
//     document.getElementById('uploadImageGalleryFileManagerInput').click();
// }
//
// function handleImageGalleryUpload(event) {
//     const file = event.target.files[0];
//     if (file) {
//         var galleryTr = document.getElementById("modal-body-url-directory-gallery-tr").value;
//         var inputDirectoryValue = document.getElementById("modal-body-url-directory-gallery").value;
//         inputDirectoryValue = inputDirectoryValue.toString();
//         const formData = new FormData();
//         formData.append('file', file);
//         formData.append('inputDirectoryValue', inputDirectoryValue);
//
//         var csrfToken = document.querySelector('meta[name="_csrf"]').getAttribute('content'); // Lấy CSRF token từ thẻ meta trong mã HTML
//         var csrfHeader = document.querySelector('meta[name="_csrf_header"]').getAttribute('content');
//
//         var csrfTokenHeader = {};
//         csrfTokenHeader[csrfHeader] = csrfToken;
//         $.ajax({
//             url: '/upload-image',
//             type: 'POST', // Thay đổi từ 'GET' sang 'POST'
//             data: formData,
//             headers: csrfTokenHeader, // Thêm CSRF token vào header của yêu cầu
//             contentType: false, // Thêm dòng này để đảm bảo FormData không bị xử lý
//             processData: false, // Thêm dòng này để đảm bảo FormData không bị xử lý
//             success: function (response) {
//                 alert('Upload ảnh thành công!');
//                 var inputElement = document.getElementById("modal-body-url-directory-gallery");
//                 inputElement.value = response.urlDirectory;
//                 galleryTr.value = galleryTr;
//
//                 $(".modal-body-directory-gallery").empty();
//                 // Hiển thị danh sách thư mục
//                 $.each(response.directories, function (index, directory) {
//                     var urlDirectoryName = response.urlDirectory + "/" + directory;
//                     var listItem = $("<div>").html(
//                         "<a href='#' data-name='" + directory + "' onclick='loadDirectoryContentGallery(this.getAttribute(\"data-name\"))'>" +
//                         "<div><svg xmlns=\"http://www.w3.org/2000/svg\" width=\"60\" height=\"60\" fill=\"currentColor\" class=\"bi bi-folder-fill\" viewBox=\"0 0 16 16\">" +
//                         "<path d=\"M9.828 3h3.982a2 2 0 0 1 1.992 2.181l-.637 7A2 2 0 0 1 13.174 14H2.825a2 2 0 0 1-1.991-1.819l-.637-7a2 2 0 0 1 .342-1.31L.5 3a2 2 0 0 1 2-2h3.672a2 2 0 0 1 1.414.586l.828.828A2 2 0 0 0 9.828 3m-8.322.12q.322-.119.684-.12h5.396l-.707-.707A1 1 0 0 0 6.172 2H2.5a1 1 0 0 0-1 .981z\"/>" +
//                         "</svg></div></a>" +
//                         "<div><input type='checkbox' name='path[]' value='" + urlDirectoryName + "'><label for='directoryCheckbox" + index + "'>" + directory + "</label></div>"
//                     );
//                     var directoryDiv = $("<div>").append(listItem).addClass("col-md-3 text-center").css({"margin-bottom": "17px"});
//                     $(".modal-body-directory-gallery").append(directoryDiv);
//                 });
//
//                 // Xóa nội dung hiện có của phần tử
//                 $(".modal-body-image-gallery").empty();
//                 // Tạo HTML mới bằng Thymeleaf
//                 var html = '';
//                 $.each(response.images, function (index, image) {
//                     var urlImageName = response.urlDirectory;
//                     var url = urlImageName.replaceAll("/", ".");
//                     var x = "/images/img"
//                     var html = $("<div>").html(
//                         '<div><img src="' + '/get-image-upload/' + url + "/" + image.name + '" alt="Hình Ảnh"' + 'style="' + 'width: 110px; height: 110px"' + 'onclick="chooseImageGalleryFromFileManager(' + "'" + urlImageName + "/" + image.name + "'" + ')"' + '></div>' +
//                         "<div><input type='checkbox' name='path[]' value='" + urlImageName + "/" + image.name + "'><label>" + image.name + "</label></div>"
//                     );
//                     var imageDiv = $("<div>").append(html).addClass("col-md-3 text-center img-thumbnail").css({
//                         "margin-bottom": "17px",
//                         "object-fit": "cover",
//                         "image-rendering": "auto"
//                     });
//                     $(".modal-body-image-gallery").append(imageDiv)
//                 });
//             },
//             error: function (xhr, status, error) {
//                 // Xử lý lỗi (nếu có)
//                 alert('Đã xảy ra lỗi khi upload ảnh.');
//                 console.error(error);
//             }
//         });
//     }
// }
//
// function createFolderGalleryFileManager(event) {
//     // Lấy giá trị từ input
//     var folderName = document.getElementById("input-create-folder-gallery").value;
//     var urlDirectory = document.getElementById("modal-body-url-directory-gallery").value;
//     const formData = new FormData();
//     formData.append('folderName', folderName);
//     formData.append('urlDirectory', urlDirectory);
//
//     var csrfToken = document.querySelector('meta[name="_csrf"]').getAttribute('content'); // Lấy CSRF token từ thẻ meta trong mã HTML
//     var csrfHeader = document.querySelector('meta[name="_csrf_header"]').getAttribute('content');
//
//     var csrfTokenHeader = {};
//     csrfTokenHeader[csrfHeader] = csrfToken;
//     // Gửi dữ liệu đến máy chủ bằng AJAX
//     $.ajax({
//         url: '/create-folder',
//         type: 'POST', // Thay đổi từ 'GET' sang 'POST'
//         data: formData,
//         headers: csrfTokenHeader, // Thêm CSRF token vào header của yêu cầu
//         contentType: false, // Thêm dòng này để đảm bảo FormData không bị xử lý
//         processData: false, // Thêm dòng này để đảm bảo FormData không bị xử lý
//         success: function (response) {
//             alert("Tạo thư mục thành công!")
//             var inputElement = document.getElementById("modal-body-url-directory-gallery");
//             inputElement.value = response.urlDirectory;
//
//             $(".modal-body-directory-gallery").empty();
//             // Hiển thị danh sách thư mục
//             $.each(response.directories, function (index, directory) {
//                 var urlDirectoryName = response.urlDirectory + "/" + directory;
//                 var listItem = $("<div>").html(
//                     "<a href='#' data-name='" + directory + "' onclick='loadDirectoryContentGallery(this.getAttribute(\"data-name\"))'>" +
//                     "<div><svg xmlns=\"http://www.w3.org/2000/svg\" width=\"60\" height=\"60\" fill=\"currentColor\" class=\"bi bi-folder-fill\" viewBox=\"0 0 16 16\">" +
//                     "<path d=\"M9.828 3h3.982a2 2 0 0 1 1.992 2.181l-.637 7A2 2 0 0 1 13.174 14H2.825a2 2 0 0 1-1.991-1.819l-.637-7a2 2 0 0 1 .342-1.31L.5 3a2 2 0 0 1 2-2h3.672a2 2 0 0 1 1.414.586l.828.828A2 2 0 0 0 9.828 3m-8.322.12q.322-.119.684-.12h5.396l-.707-.707A1 1 0 0 0 6.172 2H2.5a1 1 0 0 0-1 .981z\"/>" +
//                     "</svg></div></a>" +
//                     "<div><input type='checkbox' name='path[]' value='" + urlDirectoryName + "'><label for='directoryCheckbox" + index + "'>" + directory + "</label></div>"
//                 );
//                 var directoryDiv = $("<div>").append(listItem).addClass("col-md-3 text-center").css({"margin-bottom": "17px"});
//                 $(".modal-body-directory-gallery").append(directoryDiv);
//             });
//
//             // Xóa nội dung hiện có của phần tử
//             $(".modal-body-image-gallery").empty();
//             // Tạo HTML mới bằng Thymeleaf
//             var html = '';
//             $.each(response.images, function (index, image) {
//                 var urlImageName = response.urlDirectory;
//                 var url = urlImageName.replaceAll("/", ".");
//                 var x = "/images/img"
//                 var html = $("<div>").html(
//                     '<div><img src="' + '/get-image-upload/' + url + "/" + image.name + '" alt="Hình Ảnh"' + 'style="' + 'width: 110px; height: 110px"' + 'onclick="chooseImageGalleryFromFileManager(' + "'" + urlImageName + "/" + image.name + "'" + ')"' + '></div>' +
//                     "<div><input type='checkbox' name='path[]' value='" + urlImageName + "/" + image.name + "'><label>" + image.name + "</label></div>"
//                 );
//                 var imageDiv = $("<div>").append(html).addClass("col-md-3 text-center img-thumbnail").css({
//                     "margin-bottom": "17px",
//                     "object-fit": "cover",
//                     "image-rendering": "auto"
//                 });
//                 $(".modal-body-image-gallery").append(imageDiv)
//             });
//         },
//         error: function (xhr, status, error) {
//             alert('Đã xảy ra lỗi khi tạo thư mục.');
//             console.error(error);
//         }
//     });
// }
//
// function loadDirectoryContent(directoryName) {
//     var inputDirectoryValue = document.getElementById("modal-body-url-directory").value;
//     var urlDirectory = inputDirectoryValue + "/" + directoryName;
//     $.ajax({
//         url: "/get-directory-in-directory",
//         type: "GET",
//         data: {directoryName: directoryName, urlDirectory: urlDirectory},
//         success: function (response) {
//             var inputElement = document.getElementById("modal-body-url-directory");
//             inputElement.value = response.urlDirectory;
//
//             $(".modal-body-directory").empty();
//             // Hiển thị danh sách thư mục
//             $.each(response.directories, function (index, directory) {
//                 var urlDirectoryName = response.urlDirectory + "/" + directory;
//                 var listItem = $("<div>").html(
//                     "<a href='#' data-name='" + directory + "' onclick='loadDirectoryContent(this.getAttribute(\"data-name\"))'>" +
//                     "<div><svg xmlns=\"http://www.w3.org/2000/svg\" width=\"60\" height=\"60\" fill=\"currentColor\" class=\"bi bi-folder-fill\" viewBox=\"0 0 16 16\">" +
//                     "<path d=\"M9.828 3h3.982a2 2 0 0 1 1.992 2.181l-.637 7A2 2 0 0 1 13.174 14H2.825a2 2 0 0 1-1.991-1.819l-.637-7a2 2 0 0 1 .342-1.31L.5 3a2 2 0 0 1 2-2h3.672a2 2 0 0 1 1.414.586l.828.828A2 2 0 0 0 9.828 3m-8.322.12q.322-.119.684-.12h5.396l-.707-.707A1 1 0 0 0 6.172 2H2.5a1 1 0 0 0-1 .981z\"/>" +
//                     "</svg></div></a>" +
//                     "<div><input type='checkbox' name='path[]' value='" + urlDirectoryName + "'><label for='directoryCheckbox" + index + "'>" + directory + "</label></div>"
//                 );
//                 var directoryDiv = $("<div>").append(listItem).addClass("col-md-3 text-center").css({"margin-bottom": "17px"});
//                 $(".modal-body-directory").append(directoryDiv);
//             });
//
//             // Xóa nội dung hiện có của phần tử
//             $(".modal-body-image").empty();
//             // Tạo HTML mới bằng Thymeleaf
//             var html = '';
//             $.each(response.images, function (index, image) {
//                 var urlImageName = response.urlDirectory;
//                 var url = urlImageName.replaceAll("/", ".");
//                 var x = "/images/img"
//                 var html = $("<div>").html(
//                     '<div><img src="' + '/get-image-upload/' + url + "/" + image.name + '" alt="Hình Ảnh"' + 'style="' + 'width: 110px; height: 110px"' + 'onclick="chooseImageFromFileManager(' + "'" + urlImageName + "/" + image.name + "'" + ')"' + '></div>' +
//                     "<div><input type='checkbox' name='path[]' value='" + urlImageName + "/" + image.name + "'><label>" + image.name + "</label></div>"
//                 );
//                 var imageDiv = $("<div>").append(html).addClass("col-md-3 text-center img-thumbnail").css({
//                     "margin-bottom": "17px",
//                     "object-fit": "cover",
//                     "image-rendering": "auto"
//                 });
//                 $(".modal-body-image").append(imageDiv)
//             });
//         },
//         error: function (xhr, status, error) {
//             console.error(xhr.responseText);
//         }
//     });
// }
//
// function removeLastSegment(path) {
//     return path.substring(0, path.lastIndexOf("/"));
// }
//
// function loadReturnDirectoryContent() {
//     var inputDirectoryValue = document.getElementById("modal-body-url-directory").value;
//     inputDirectoryValue = inputDirectoryValue.toString();
//     var rootDirectoryName = "/images";
//     if (inputDirectoryValue === rootDirectoryName) {
//         return true;
//     } else {
//         var newInputDirectoryValue = removeLastSegment(inputDirectoryValue);
//         $.ajax({
//             url: "/get-directory-in-directory",
//             type: "GET",
//             data: {directoryName: "", urlDirectory: newInputDirectoryValue},
//             success: function (response) {
//                 var inputElement = document.getElementById("modal-body-url-directory");
//                 inputElement.value = response.urlDirectory;
//
//                 $(".modal-body-directory").empty();
//                 // Hiển thị danh sách thư mục
//                 $.each(response.directories, function (index, directory) {
//                     var urlDirectoryName = response.urlDirectory + "/" + directory;
//                     var listItem = $("<div>").html(
//                         "<a href='#' data-name='" + directory + "' onclick='loadDirectoryContent(this.getAttribute(\"data-name\"))'>" +
//                         "<div><svg xmlns=\"http://www.w3.org/2000/svg\" width=\"60\" height=\"60\" fill=\"currentColor\" class=\"bi bi-folder-fill\" viewBox=\"0 0 16 16\">" +
//                         "<path d=\"M9.828 3h3.982a2 2 0 0 1 1.992 2.181l-.637 7A2 2 0 0 1 13.174 14H2.825a2 2 0 0 1-1.991-1.819l-.637-7a2 2 0 0 1 .342-1.31L.5 3a2 2 0 0 1 2-2h3.672a2 2 0 0 1 1.414.586l.828.828A2 2 0 0 0 9.828 3m-8.322.12q.322-.119.684-.12h5.396l-.707-.707A1 1 0 0 0 6.172 2H2.5a1 1 0 0 0-1 .981z\"/>" +
//                         "</svg></div></a>" +
//                         "<div><input type='checkbox' name='path[]' value='" + urlDirectoryName + "'><label for='directoryCheckbox" + index + "'>" + directory + "</label></div>"
//                     );
//                     var directoryDiv = $("<div>").append(listItem).addClass("col-md-3 text-center").css({"margin-bottom": "17px"});
//                     $(".modal-body-directory").append(directoryDiv);
//                 });
//
//                 // Xóa nội dung hiện có của phần tử
//                 $(".modal-body-image").empty();
//                 // Tạo HTML mới bằng Thymeleaf
//                 $.each(response.images, function (index, image) {
//                     var urlImageName = response.urlDirectory;
//                     var url = urlImageName.replaceAll("/", ".");
//                     var x = "/images/img"
//                     var html = $("<div>").html(
//                         '<div><img src="' + '/get-image-upload/' + url + "/" + image.name + '" alt="Hình Ảnh"' + 'style="' + 'width: 110px; height: 110px"' + 'onclick="chooseImageFromFileManager(' + "'" + urlImageName + "/" + image.name + "'" + ')"' + '></div>' +
//                         "<div><input type='checkbox' name='path[]' value='" + urlImageName + "/" + image.name + "'><label>" + image.name + "</label></div>"
//                     );
//                     var imageDiv = $("<div>").append(html).addClass("col-md-3 text-center img-thumbnail").css({
//                         "margin-bottom": "17px",
//                         "object-fit": "cover",
//                         "image-rendering": "auto"
//                     });
//                     $(".modal-body-image").append(imageDiv)
//                 });
//             },
//             error: function (xhr, status, error) {
//                 console.error(xhr.responseText);
//             }
//         });
//     }
// }
//
// $(document).ready(function () {
//     $("#test-image").click(function (e) {
//         e.preventDefault(); // Ngăn chặn hành động mặc định của thẻ a
//
//         // Hiển thị hoặc ẩn các button
//         $(".buttons").toggle();
//     });
// });
//
// function handleAddButtonClick() {
//     // Gọi hàm xử lý dữ liệu trước khi mở modal
//     processDataBeforeModal();
// }
//
// // Hàm xử lý dữ liệu trước khi mở modal
// function processDataBeforeModal() {
//     $.ajax({
//         url: "/image-manager",
//         type: "GET",
//         success: function (response) {
//             var inputUrlDirectory = document.getElementById("modal-body-url-directory");
//             inputUrlDirectory.value = response.urlDirectory;
//             $(".modal-body-directory").empty();
//             $.each(response.directories, function (index, directory) {
//                 var urlDirectoryName = response.urlDirectory + "/" + directory;
//                 var listItem = $("<div>").html(
//                     "<a href='#' data-name='" + directory + "' onclick='loadDirectoryContent(this.getAttribute(\"data-name\"))'>" +
//                     "<div><svg xmlns=\"http://www.w3.org/2000/svg\" width=\"60\" height=\"60\" fill=\"currentColor\" class=\"bi bi-folder-fill\" viewBox=\"0 0 16 16\">" +
//                     "<path d=\"M9.828 3h3.982a2 2 0 0 1 1.992 2.181l-.637 7A2 2 0 0 1 13.174 14H2.825a2 2 0 0 1-1.991-1.819l-.637-7a2 2 0 0 1 .342-1.31L.5 3a2 2 0 0 1 2-2h3.672a2 2 0 0 1 1.414.586l.828.828A2 2 0 0 0 9.828 3m-8.322.12q.322-.119.684-.12h5.396l-.707-.707A1 1 0 0 0 6.172 2H2.5a1 1 0 0 0-1 .981z\"/>" +
//                     "</svg></div></a>" +
//                     "<div><input type='checkbox' name='path[]' value='" + urlDirectoryName + "'><label for='directoryCheckbox" + index + "'>" + directory + "</label></div>"
//                 );
//
//                 var directoryDiv = $("<div>").append(listItem).addClass("col-md-3 text-center").css({"margin-bottom": "17px"});
//                 $(".modal-body-directory").append(directoryDiv);
//             });
//
//             $(".modal-body-image").empty();
//             $.each(response.images, function (index, image) {
//                 var urlImageName = response.urlDirectory;
//                 var url = urlImageName.replaceAll("/", ".");
//                 var x = "/images/img"
//                 var html = $("<div>").html(
//                     '<div><img src="' + '/get-image-upload/' + url + "/" + image.name + '" alt="Hình Ảnh"' + 'style="' + 'width: 110px; height: 110px"' + 'onclick="chooseImageFromFileManager(' + "'" + urlImageName + "/" + image.name + "'" + ')"' + '></div>' +
//                     "<div><input type='checkbox' name='path[]' value='" + urlImageName + "/" + image.name + "'><label>" + image.name + "</label></div>"
//                 );
//                 var imageDiv = $("<div>").append(html).addClass("col-md-3 text-center img-thumbnail").css({
//                     "margin-bottom": "17px",
//                     "object-fit": "cover",
//                     "image-rendering": "auto"
//                 });
//                 $(".modal-body-image").append(imageDiv)
//             });
//         },
//         error: function (xhr, status, error) {
//             console.error(error);
//         }
//     });
// }
//
// function loadDirectoryAndImageAfterDelete() {
//     var inputDirectoryValue = document.getElementById("modal-body-url-directory").value;
//     $.ajax({
//         url: "/get-directory-in-directory",
//         type: "GET",
//         data: {directoryName: "", urlDirectory: urlDirectory},
//         success: function (response) {
//             var inputElement = document.getElementById("modal-body-url-directory");
//             inputElement.value = response.urlDirectory;
//
//             $(".modal-body-directory").empty();
//             // Hiển thị danh sách thư mục
//             $.each(response.directories, function (index, directory) {
//                 var urlDirectoryName = response.urlDirectory + "/" + directory;
//                 var listItem = $("<div>").html(
//                     "<a href='#' data-name='" + directory + "' onclick='loadDirectoryContent(this.getAttribute(\"data-name\"))'>" +
//                     "<div><svg xmlns=\"http://www.w3.org/2000/svg\" width=\"60\" height=\"60\" fill=\"currentColor\" class=\"bi bi-folder-fill\" viewBox=\"0 0 16 16\">" +
//                     "<path d=\"M9.828 3h3.982a2 2 0 0 1 1.992 2.181l-.637 7A2 2 0 0 1 13.174 14H2.825a2 2 0 0 1-1.991-1.819l-.637-7a2 2 0 0 1 .342-1.31L.5 3a2 2 0 0 1 2-2h3.672a2 2 0 0 1 1.414.586l.828.828A2 2 0 0 0 9.828 3m-8.322.12q.322-.119.684-.12h5.396l-.707-.707A1 1 0 0 0 6.172 2H2.5a1 1 0 0 0-1 .981z\"/>" +
//                     "</svg></div></a>" +
//                     "<div><input type='checkbox' name='path[]' value='" + urlDirectoryName + "'><label for='directoryCheckbox" + index + "'>" + directory + "</label></div>"
//                 );
//                 var directoryDiv = $("<div>").append(listItem).addClass("col-md-3 text-center").css({"margin-bottom": "17px"});
//                 $(".modal-body-directory").append(directoryDiv);
//             });
//
//             // Xóa nội dung hiện có của phần tử
//             $(".modal-body-image").empty();
//             // Tạo HTML mới bằng Thymeleaf
//             var html = '';
//             $.each(response.images, function (index, image) {
//                 var urlImageName = response.urlDirectory;
//                 var url = urlImageName.replaceAll("/", ".");
//                 var x = "/images/img"
//                 var html = $("<div>").html(
//                     '<div><img src="' + '/get-image-upload/' + url + "/" + image.name + '" alt="Hình Ảnh"' + 'style="' + 'width: 110px; height: 110px"' + 'onclick="chooseImageFromFileManager(' + "'" + urlImageName + "/" + image.name + "'" + ')"' + '></div>' +
//                     "<div><input type='checkbox' name='path[]' value='" + urlImageName + "/" + image.name + "'><label>" + image.name + "</label></div>"
//                 );
//                 var imageDiv = $("<div>").append(html).addClass("col-md-3 text-center img-thumbnail").css({
//                     "margin-bottom": "17px",
//                     "object-fit": "cover",
//                     "image-rendering": "auto"
//                 });
//                 $(".modal-body-image").append(imageDiv)
//             });
//         },
//         error: function (xhr, status, error) {
//             console.error(xhr.responseText);
//         }
//     });
// }
//
// function loadDeleteDirectoryContent() {
//     var selectedValues = []; // Mảng để lưu giá trị của các input đã chọn
//     // Lặp qua tất cả các checkbox
//     $("input[name='path[]']").each(function () {
//         // Kiểm tra xem checkbox có được chọn không
//         if ($(this).is(":checked")) {
//             // Lấy giá trị của checkbox đã chọn và thêm vào mảng
//             selectedValues.push($(this).val());
//         }
//     });
//     if (selectedValues === 0) {
//         alert("không")
//     }
//     var inputDirectoryValue = document.getElementById("modal-body-url-directory").value;
//     inputDirectoryValue = inputDirectoryValue.toString();
//     var data = {
//         selectedValues: JSON.stringify(selectedValues),
//         inputDirectoryValue: inputDirectoryValue
//     };
//     $.ajax({
//         url: "/delete-directory",
//         type: "GET",
//         data: data,
//         success: function (response) {
//             alert("Xóa thư mục/hình ảnh thành công!")
//             var inputElement = document.getElementById("modal-body-url-directory");
//             inputElement.value = response.urlDirectory;
//
//             $(".modal-body-directory").empty();
//             // Hiển thị danh sách thư mục
//             $.each(response.directories, function (index, directory) {
//                 var urlDirectoryName = response.urlDirectory + "/" + directory;
//                 var listItem = $("<div>").html(
//                     "<a href='#' data-name='" + directory + "' onclick='loadDirectoryContent(this.getAttribute(\"data-name\"))'>" +
//                     "<div><svg xmlns=\"http://www.w3.org/2000/svg\" width=\"60\" height=\"60\" fill=\"currentColor\" class=\"bi bi-folder-fill\" viewBox=\"0 0 16 16\">" +
//                     "<path d=\"M9.828 3h3.982a2 2 0 0 1 1.992 2.181l-.637 7A2 2 0 0 1 13.174 14H2.825a2 2 0 0 1-1.991-1.819l-.637-7a2 2 0 0 1 .342-1.31L.5 3a2 2 0 0 1 2-2h3.672a2 2 0 0 1 1.414.586l.828.828A2 2 0 0 0 9.828 3m-8.322.12q.322-.119.684-.12h5.396l-.707-.707A1 1 0 0 0 6.172 2H2.5a1 1 0 0 0-1 .981z\"/>" +
//                     "</svg></div></a>" +
//                     "<div><input type='checkbox' name='path[]' value='" + urlDirectoryName + "'><label for='directoryCheckbox" + index + "'>" + directory + "</label></div>"
//                 );
//                 var directoryDiv = $("<div>").append(listItem).addClass("col-md-3 text-center").css({"margin-bottom": "17px"});
//                 $(".modal-body-directory").append(directoryDiv);
//             });
//
//             // Xóa nội dung hiện có của phần tử
//             $(".modal-body-image").empty();
//             // Tạo HTML mới bằng Thymeleaf
//             var html = '';
//             $.each(response.images, function (index, image) {
//                 var urlImageName = response.urlDirectory;
//                 var url = urlImageName.replaceAll("/", ".");
//                 var x = "/images/img"
//                 var html = $("<div>").html(
//                     '<div><img src="' + '/get-image-upload/' + url + "/" + image.name + '" alt="Hình Ảnh"' + 'style="' + 'width: 110px; height: 110px"' + 'onclick="chooseImageFromFileManager(' + "'" + urlImageName + "/" + image.name + "'" + ')"' + '></div>' +
//                     "<div><input type='checkbox' name='path[]' value='" + urlImageName + "/" + image.name + "'><label>" + image.name + "</label></div>"
//                 );
//                 var imageDiv = $("<div>").append(html).addClass("col-md-3 text-center img-thumbnail").css({
//                     "margin-bottom": "17px",
//                     "object-fit": "cover",
//                     "image-rendering": "auto"
//                 });
//                 $(".modal-body-image").append(imageDiv)
//             });
//         },
//         error: function (xhr, status, error) {
//             alert("Xóa thư mục/hình ảnh không thành công!")
//             console.error(error);
//         }
//     });
// }
//
// function uploadImageFileManager(file) {
//     document.getElementById('uploadImageFileManagerInput').click();
// }
//
// function handleImageUpload(event) {
//     const file = event.target.files[0];
//     if (file) {
//         var inputDirectoryValue = document.getElementById("modal-body-url-directory").value;
//         inputDirectoryValue = inputDirectoryValue.toString();
//         const formData = new FormData();
//         formData.append('file', file);
//         formData.append('inputDirectoryValue', inputDirectoryValue);
//
//         var csrfToken = document.querySelector('meta[name="_csrf"]').getAttribute('content'); // Lấy CSRF token từ thẻ meta trong mã HTML
//         var csrfHeader = document.querySelector('meta[name="_csrf_header"]').getAttribute('content');
//
//         var csrfTokenHeader = {};
//         csrfTokenHeader[csrfHeader] = csrfToken;
//         $.ajax({
//             url: '/upload-image',
//             type: 'POST', // Thay đổi từ 'GET' sang 'POST'
//             data: formData,
//             headers: csrfTokenHeader, // Thêm CSRF token vào header của yêu cầu
//             contentType: false, // Thêm dòng này để đảm bảo FormData không bị xử lý
//             processData: false, // Thêm dòng này để đảm bảo FormData không bị xử lý
//             success: function (response) {
//                 alert('Upload ảnh thành công!');
//                 var inputElement = document.getElementById("modal-body-url-directory");
//                 inputElement.value = response.urlDirectory;
//
//                 $(".modal-body-directory").empty();
//                 // Hiển thị danh sách thư mục
//                 $.each(response.directories, function (index, directory) {
//                     var urlDirectoryName = response.urlDirectory + "/" + directory;
//                     var listItem = $("<div>").html(
//                         "<a href='#' data-name='" + directory + "' onclick='loadDirectoryContent(this.getAttribute(\"data-name\"))'>" +
//                         "<div><svg xmlns=\"http://www.w3.org/2000/svg\" width=\"60\" height=\"60\" fill=\"currentColor\" class=\"bi bi-folder-fill\" viewBox=\"0 0 16 16\">" +
//                         "<path d=\"M9.828 3h3.982a2 2 0 0 1 1.992 2.181l-.637 7A2 2 0 0 1 13.174 14H2.825a2 2 0 0 1-1.991-1.819l-.637-7a2 2 0 0 1 .342-1.31L.5 3a2 2 0 0 1 2-2h3.672a2 2 0 0 1 1.414.586l.828.828A2 2 0 0 0 9.828 3m-8.322.12q.322-.119.684-.12h5.396l-.707-.707A1 1 0 0 0 6.172 2H2.5a1 1 0 0 0-1 .981z\"/>" +
//                         "</svg></div></a>" +
//                         "<div><input type='checkbox' name='path[]' value='" + urlDirectoryName + "'><label for='directoryCheckbox" + index + "'>" + directory + "</label></div>"
//                     );
//                     var directoryDiv = $("<div>").append(listItem).addClass("col-md-3 text-center").css({"margin-bottom": "17px"});
//                     $(".modal-body-directory").append(directoryDiv);
//                 });
//
//                 // Xóa nội dung hiện có của phần tử
//                 $(".modal-body-image").empty();
//                 // Tạo HTML mới bằng Thymeleaf
//                 var html = '';
//                 $.each(response.images, function (index, image) {
//                     var urlImageName = response.urlDirectory;
//                     var url = urlImageName.replaceAll("/", ".");
//                     var x = "/images/img"
//                     var html = $("<div>").html(
//                         '<div><img src="' + '/get-image-upload/' + url + "/" + image.name + '" alt="Hình Ảnh"' + 'style="' + 'width: 110px; height: 110px"' + 'onclick="chooseImageFromFileManager(' + "'" + urlImageName + "/" + image.name + "'" + ')"' + '></div>' +
//                         "<div><input type='checkbox' name='path[]' value='" + urlImageName + "/" + image.name + "'><label>" + image.name + "</label></div>"
//                     );
//                     var imageDiv = $("<div>").append(html).addClass("col-md-3 text-center img-thumbnail").css({
//                         "margin-bottom": "17px",
//                         "object-fit": "cover",
//                         "image-rendering": "auto"
//                     });
//                     $(".modal-body-image").append(imageDiv)
//                 });
//             },
//             error: function (xhr, status, error) {
//                 // Xử lý lỗi (nếu có)
//                 alert('Đã xảy ra lỗi khi upload ảnh.');
//                 console.error(error);
//             }
//         });
//     }
// }
//
// function createFolderFileManager(event) {
//     // Lấy giá trị từ input
//     var folderName = document.getElementById("input-create-folder").value;
//     var urlDirectory = document.getElementById("modal-body-url-directory").value;
//     const formData = new FormData();
//     formData.append('folderName', folderName);
//     formData.append('urlDirectory', urlDirectory);
//
//     var csrfToken = document.querySelector('meta[name="_csrf"]').getAttribute('content'); // Lấy CSRF token từ thẻ meta trong mã HTML
//     var csrfHeader = document.querySelector('meta[name="_csrf_header"]').getAttribute('content');
//
//     var csrfTokenHeader = {};
//     csrfTokenHeader[csrfHeader] = csrfToken;
//     // Gửi dữ liệu đến máy chủ bằng AJAX
//     $.ajax({
//         url: '/create-folder',
//         type: 'POST', // Thay đổi từ 'GET' sang 'POST'
//         data: formData,
//         headers: csrfTokenHeader, // Thêm CSRF token vào header của yêu cầu
//         contentType: false, // Thêm dòng này để đảm bảo FormData không bị xử lý
//         processData: false, // Thêm dòng này để đảm bảo FormData không bị xử lý
//         success: function (response) {
//             alert("Tạo thư mục thành công!")
//             var inputElement = document.getElementById("modal-body-url-directory");
//             inputElement.value = response.urlDirectory;
//
//             $(".modal-body-directory").empty();
//             // Hiển thị danh sách thư mục
//             $.each(response.directories, function (index, directory) {
//                 var urlDirectoryName = response.urlDirectory + "/" + directory;
//                 var listItem = $("<div>").html(
//                     "<a href='#' data-name='" + directory + "' onclick='loadDirectoryContent(this.getAttribute(\"data-name\"))'>" +
//                     "<div><svg xmlns=\"http://www.w3.org/2000/svg\" width=\"60\" height=\"60\" fill=\"currentColor\" class=\"bi bi-folder-fill\" viewBox=\"0 0 16 16\">" +
//                     "<path d=\"M9.828 3h3.982a2 2 0 0 1 1.992 2.181l-.637 7A2 2 0 0 1 13.174 14H2.825a2 2 0 0 1-1.991-1.819l-.637-7a2 2 0 0 1 .342-1.31L.5 3a2 2 0 0 1 2-2h3.672a2 2 0 0 1 1.414.586l.828.828A2 2 0 0 0 9.828 3m-8.322.12q.322-.119.684-.12h5.396l-.707-.707A1 1 0 0 0 6.172 2H2.5a1 1 0 0 0-1 .981z\"/>" +
//                     "</svg></div></a>" +
//                     "<div><input type='checkbox' name='path[]' value='" + urlDirectoryName + "'><label for='directoryCheckbox" + index + "'>" + directory + "</label></div>"
//                 );
//                 var directoryDiv = $("<div>").append(listItem).addClass("col-md-3 text-center").css({"margin-bottom": "17px"});
//                 $(".modal-body-directory").append(directoryDiv);
//             });
//
//             // Xóa nội dung hiện có của phần tử
//             $(".modal-body-image").empty();
//             // Tạo HTML mới bằng Thymeleaf
//             var html = '';
//             $.each(response.images, function (index, image) {
//                 var urlImageName = response.urlDirectory;
//                 var url = urlImageName.replaceAll("/", ".");
//                 var x = "/images/img"
//                 var html = $("<div>").html(
//                     '<div><img src="' + '/get-image-upload/' + url + "/" + image.name + '" alt="Hình Ảnh"' + 'style="' + 'width: 110px; height: 110px"' + 'onclick="chooseImageFromFileManager(' + "'" + urlImageName + "/" + image.name + "'" + ')"' + '></div>' +
//                     "<div><input type='checkbox' name='path[]' value='" + urlImageName + "/" + image.name + "'><label>" + image.name + "</label></div>"
//                 );
//                 var imageDiv = $("<div>").append(html).addClass("col-md-3 text-center img-thumbnail").css({
//                     "margin-bottom": "17px",
//                     "object-fit": "cover",
//                     "image-rendering": "auto"
//                 });
//                 $(".modal-body-image").append(imageDiv)
//             });
//         },
//         error: function (xhr, status, error) {
//             alert('Đã xảy ra lỗi khi tạo thư mục.');
//             console.error(error);
//         }
//     });
// }
//
// function chooseImageFromFileManager(urlImage) {
//     // Lấy đối tượng modal
//     var myModal = $('#myModal');
//     // Gọi phương thức hide() để ẩn modal
//     myModal.modal('hide');
//
//     // Lấy đối tượng phần tử HTML cần thay đổi style
//     var element = document.querySelector('.buttons.hide-show-filemanager-button');
//
//     // Thay đổi style của phần tử
//     element.style.display = 'none'; // Hoặc 'inline', 'flex', 'inline-block', tùy thuộc vào yêu cầu của bạn
//
//     var lastIndex = urlImage.lastIndexOf("/");
//     var folderPath = urlImage.substring(0, lastIndex);
//     var folderPathTemp = folderPath.replaceAll("/", ".");
//     var fileImageName = urlImage.substring(lastIndex + 1);
//     console.log("+, Folder:" + folderPath + " | file name: " + fileImageName)
//
//     var avtImage = document.getElementById('avt-image');
//     var inputAvtImage = document.getElementById('input-avt-image');
//
//     avtImage.src = "/get-image/" + folderPathTemp + "/" + fileImageName;
//
//     inputAvtImage.value = urlImage;
// }
//
// function deleteImagePresentButtonClick() {
//     // Lấy đối tượng phần tử HTML cần thay đổi style
//     var element = document.querySelector('.buttons.hide-show-filemanager-button');
//
//     // Thay đổi style của phần tử
//     element.style.display = 'none'; // Hoặc 'inline', 'flex', 'inline-block', tùy thuộc vào yêu cầu của bạn
//
//     var avtImage = document.getElementById('avt-image');
//     var inputAvtImage = document.getElementById('input-avt-image');
//
//     var urlImage = "/images/noImage/img.png";
//
//     avtImage.src = urlImage;
//     inputAvtImage.value = urlImage;
// }
//
// // Lấy tất cả các phần tử có lớp CSS là "src-image"
// var imageElements = document.querySelectorAll('.src-image');
//
// // Lặp qua từng phần tử và lấy giá trị của thuộc tính "src"
// for (var i = 0; i < imageElements.length; i++) {
//     var srcValue = imageElements[i].getAttribute("src");
//     var lastIndex = srcValue.lastIndexOf("/");
//     var folderPath = srcValue.substring(0, lastIndex);
//     var folderPathTemp = folderPath.replaceAll("/", ".");
//     var fileImageName = srcValue.substring(lastIndex + 1);
//     imageElements[i].src = "/get-image/" + folderPathTemp + "/" + fileImageName;
// }
//
// document.addEventListener('DOMContentLoaded', function () {
//     var formInputs = document.querySelectorAll('form input');
//
//     formInputs.forEach(function (input) {
//         input.addEventListener('keydown', function (event) {
//             if (event.key === 'Enter') {
//                 event.preventDefault(); // Ngăn chặn hành động mặc định của nút "Enter"
//             }
//         });
//     });
// });