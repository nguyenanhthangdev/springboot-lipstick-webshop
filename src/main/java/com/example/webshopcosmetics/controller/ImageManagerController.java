package com.example.webshopcosmetics.controller;

import com.example.webshopcosmetics.model.DirectoryContent;
import com.example.webshopcosmetics.model.ImageItem;
import com.example.webshopcosmetics.model.User;
import com.example.webshopcosmetics.service.image.ImageService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

@Controller
public class ImageManagerController {

    @Autowired
    private ImageService imageService;

//    @GetMapping("/image-manager")
//    public String showFileManager(Model model) {
//        List<ImageItem> directories = imageService.getDirectories();
//        List<ImageItem> images = imageService.getImages();
//        model.addAttribute("directories", directories);
//        model.addAttribute("images", images);
//        return "admin/image-manager";
//    }

    @GetMapping("/image-manager")
    @ResponseBody
    public DirectoryContent getCurrentDirectoryContent(Model model) {
        DirectoryContent directoryContent = imageService.getDirectoryContent();
//        model.addAttribute("directoryContent", content);
        return directoryContent;
    }

    @GetMapping("/get-directory-in-directory")
    @ResponseBody
    public DirectoryContent getDirectoryInDirectory(Model model, @RequestParam(value = "directoryName", required = true) String directoryName,
                                                    @RequestParam(value = "urlDirectory", required = true) String urlDirectory) {
        DirectoryContent directoryContent = imageService.getDirectoryInDirectoryContent(urlDirectory, directoryName);
//        model.addAttribute("directoryContent", content);
        return directoryContent;
    }

    @GetMapping("/image-manager-add")
    public String getImageManagerPage() {
        return "admin/image-manager"; // Trả về tên của template HTML
    }

//    @GetMapping("/current-directory/{directoryName}")
//    public ResponseEntity<List<ImageItem>> getSubDirectoryContent() {
//        List<ImageItem> content = imageService.getImages();
//        return ResponseEntity.ok(content);
//    }

//    @GetMapping("/your-controller-url")
//    public String showImageManagerPage() {
//        return "admin/image-manager"; // Trả về tên của file HTML cần hiển thị
//    }

    @GetMapping("/directory/{directoryName}")
    public String showDirectory(@PathVariable String directoryName, Model model) {
        List<ImageItem> directories = imageService.getDirectoryInDirectory(directoryName);
        List<ImageItem> images = imageService.getImagesInDirectory(directoryName);
        model.addAttribute("directories", directories);
        model.addAttribute("images", images);
        return "admin/image-manager";
    }

//    @PostMapping("/image-manager/add-directory")
//    public String addDirectory(@RequestParam("directoryName") String directoryName) {
//        imageService.createFolder(directoryName);
//        return "redirect:/image-manager";
//    }

//    @PostMapping("/image-manager/upload-image")
//    public String uploadImage(@RequestParam("imageFile") MultipartFile imageFile) {
//        imageService.saveImage(imageFile);
//        return "redirect:/image-manager";
//    }

    @GetMapping("/delete-directory")
    @ResponseBody
    public DirectoryContent deleteDirectory(@RequestParam("selectedValues") String selectedValuesJson, @RequestParam(value = "inputDirectoryValue") String inputDirectoryValue) {
        try {
            // Sử dụng ObjectMapper mặc định của Spring Boot để chuyển đổi chuỗi JSON thành danh sách (list)
            ObjectMapper objectMapper = new ObjectMapper();
            List<String> selectedValues = objectMapper.readValue(selectedValuesJson, new TypeReference<List<String>>() {
            });
            imageService.deleteDirectoryAndImage(selectedValues, inputDirectoryValue);
            DirectoryContent directoryContent = imageService.getDirectoryInDirectoryContent(inputDirectoryValue, "");
            return directoryContent;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @PostMapping("/delete-image")
    public String deleteImage(@RequestParam("imageName") String imageName) {
        imageService.deleteImage(imageName);
        return "redirect:/image-manager";
    }

    @PostMapping("/upload-image")
    @ResponseBody
    public DirectoryContent uploadImage(@RequestParam("file") MultipartFile file, @RequestParam("inputDirectoryValue") String urlDirectory) {
        try {
            imageService.saveImage(file, urlDirectory);
            DirectoryContent directoryContent = imageService.getDirectoryInDirectoryContent(urlDirectory, "");
            return directoryContent;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @PostMapping("/create-folder")
    @ResponseBody
    public DirectoryContent createFolder(@RequestParam("folderName") String folderName, @RequestParam("urlDirectory") String urlDirectory) {
        try {
            imageService.createFolder(folderName, urlDirectory);
            DirectoryContent directoryContent = imageService.getDirectoryInDirectoryContent(urlDirectory, "");
            return directoryContent;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}