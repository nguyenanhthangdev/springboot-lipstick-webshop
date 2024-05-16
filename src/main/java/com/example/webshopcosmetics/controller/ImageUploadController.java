package com.example.webshopcosmetics.controller;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class ImageUploadController {
    @RequestMapping(value="get-image-user/{image}", method= RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<ByteArrayResource> getImageUser(@PathVariable("image") String image) {
        if (!image.equals("") || image != null) {
            try {
                Path filename = Paths.get("src/main/resources/static/images/user/", image);
                byte[] buffer = Files.readAllBytes(filename);
                ByteArrayResource byteArrayResource = new ByteArrayResource(buffer);
                return ResponseEntity.ok()
                        .contentLength(buffer.length)
                        .contentType(MediaType.parseMediaType("image/png"))
                        .body(byteArrayResource);
            } catch (Exception e) {

            }
        }
        return ResponseEntity.badRequest().build();
    }

    @RequestMapping(value="get-image-manufacturer/{image}", method= RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<ByteArrayResource> getImageManufacturer(@PathVariable("image") String image) {
        if (!image.equals("") || image != null) {
            try {
                Path filename = Paths.get("src/main/resources/static/images/manufacturer/", image);
                byte[] buffer = Files.readAllBytes(filename);
                ByteArrayResource byteArrayResource = new ByteArrayResource(buffer);
                return ResponseEntity.ok()
                        .contentLength(buffer.length)
                        .contentType(MediaType.parseMediaType("image/png"))
                        .body(byteArrayResource);
            } catch (Exception e) {

            }
        }
        return ResponseEntity.badRequest().build();
    }

    @RequestMapping(value="get-image-category/{image}", method= RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<ByteArrayResource> getImageCategory(@PathVariable("image") String image) {
        if (!image.equals("") || image != null) {
            try {
                Path filename = Paths.get("src/main/resources/static/images/category/", image);
                byte[] buffer = Files.readAllBytes(filename);
                ByteArrayResource byteArrayResource = new ByteArrayResource(buffer);
                return ResponseEntity.ok()
                        .contentLength(buffer.length)
                        .contentType(MediaType.parseMediaType("image/png"))
                        .body(byteArrayResource);
            } catch (Exception e) {

            }
        }
        return ResponseEntity.badRequest().build();
    }

    @RequestMapping(value="get-image-product/{image}", method= RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<ByteArrayResource> getImageProduct(@PathVariable("image") String image) {
        if (!image.equals("") || image != null) {
            try {
                Path filename = Paths.get("src/main/resources/static/images/product/", image);
                byte[] buffer = Files.readAllBytes(filename);
                ByteArrayResource byteArrayResource = new ByteArrayResource(buffer);
                return ResponseEntity.ok()
                        .contentLength(buffer.length)
                        .contentType(MediaType.parseMediaType("image/png"))
                        .body(byteArrayResource);
            } catch (Exception e) {

            }
        }
        return ResponseEntity.badRequest().build();
    }

    @RequestMapping(value="get-image-gallery/{image}", method= RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<ByteArrayResource> getImageGallery(@PathVariable("image") String image) {
        if (!image.equals("") || image != null) {
            try {
                Path filename = Paths.get("src/main/resources/static/images/gallery/", image);
                byte[] buffer = Files.readAllBytes(filename);
                ByteArrayResource byteArrayResource = new ByteArrayResource(buffer);
                return ResponseEntity.ok()
                        .contentLength(buffer.length)
                        .contentType(MediaType.parseMediaType("image/png"))
                        .body(byteArrayResource);
            } catch (Exception e) {

            }
        }
        return ResponseEntity.badRequest().build();
    }

    @RequestMapping(value="get-image-banner/{image}", method= RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<ByteArrayResource> getImageBanner(@PathVariable("image") String image) {
        if (!image.equals("") || image != null) {
            try {
                Path filename = Paths.get("src/main/resources/static/images/banner/", image);
                byte[] buffer = Files.readAllBytes(filename);
                ByteArrayResource byteArrayResource = new ByteArrayResource(buffer);
                return ResponseEntity.ok()
                        .contentLength(buffer.length)
                        .contentType(MediaType.parseMediaType("image/png"))
                        .body(byteArrayResource);
            } catch (Exception e) {

            }
        }
        return ResponseEntity.badRequest().build();
    }

    @RequestMapping(value="get-image-logo/{image}", method= RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<ByteArrayResource> getImageLogo(@PathVariable("image") String image) {
        if (!image.equals("") || image != null) {
            try {
                Path filename = Paths.get("src/main/resources/static/images/logo/", image);
                byte[] buffer = Files.readAllBytes(filename);
                ByteArrayResource byteArrayResource = new ByteArrayResource(buffer);
                return ResponseEntity.ok()
                        .contentLength(buffer.length)
                        .contentType(MediaType.parseMediaType("image/png"))
                        .body(byteArrayResource);
            } catch (Exception e) {

            }
        }
        return ResponseEntity.badRequest().build();
    }

    @RequestMapping(value="get-image-upload/{url-image}/{img}", method= RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<ByteArrayResource> getImageUpload(@PathVariable("url-image") String image, @PathVariable("img") String img) {
        String url = image.replaceAll("\\.", "/");
        String urlImage = url + "/" + img;
        if (!image.equals("") || image != null) {
            try {
                Path filename = Paths.get("src/main/resources/static", urlImage);
                byte[] buffer = Files.readAllBytes(filename);
                ByteArrayResource byteArrayResource = new ByteArrayResource(buffer);
                return ResponseEntity.ok()
                        .contentLength(buffer.length)
                        .contentType(MediaType.parseMediaType("image/png"))
                        .body(byteArrayResource);
            } catch (Exception e) {

            }
        }
        return ResponseEntity.badRequest().build();
    }

    @RequestMapping(value="get-image/{url-image}/{img}", method= RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<ByteArrayResource> getImage(@PathVariable("url-image") String image, @PathVariable("img") String img) {
        String url = image.replaceAll("\\.", "/");
        String urlImage = url + "/" + img;
        if (!image.equals("") || image != null) {
            try {
                Path filename = Paths.get("src/main/resources/static", urlImage);
                byte[] buffer = Files.readAllBytes(filename);
                ByteArrayResource byteArrayResource = new ByteArrayResource(buffer);
                return ResponseEntity.ok()
                        .contentLength(buffer.length)
                        .contentType(MediaType.parseMediaType("image/png"))
                        .body(byteArrayResource);
            } catch (Exception e) {

            }
        }
        return ResponseEntity.badRequest().build();
    }
}
