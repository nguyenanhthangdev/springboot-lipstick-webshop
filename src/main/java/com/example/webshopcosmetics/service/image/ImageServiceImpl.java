package com.example.webshopcosmetics.service.image;

import com.example.webshopcosmetics.model.DirectoryContent;
import com.example.webshopcosmetics.model.ImageItem;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ImageServiceImpl implements  ImageService{

    private static final String IMAGE_UPLOAD_DIR = "src/main/resources/static/images/";
    @Override
    public DirectoryContent getDirectoryContent() {
//        String directoryPath = imageUploadDir + File.separator + directoryName;
        File directory = new File(IMAGE_UPLOAD_DIR);

        List<ImageItem> images = new ArrayList<>();
        List<String> directories = new ArrayList<>();

        if (directory.exists() && directory.isDirectory()) {
            File[] files = directory.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        directories.add(file.getName());
                    } else {
                        String imagePath = file.getName();
                        images.add(new ImageItem(file.getName(), true, ""));
                    }
                }
            }
        }

        return new DirectoryContent(images, directories, "/images");
    }

    @Override
    public DirectoryContent getDirectoryInDirectoryContent(String urlDirectory, String directoryName) {
        String directoryPath = "src/main/resources/static" + urlDirectory;
        File directory = new File(directoryPath);

        List<ImageItem> images = new ArrayList<>();
        List<String> directories = new ArrayList<>();

        if (directory.exists() && directory.isDirectory()) {
            File[] files = directory.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        directories.add(file.getName());
                    } else {
                        String imagePath = file.getName();
                        images.add(new ImageItem(file.getName(), true, ""));
                    }
                }
            }
        }

        return new DirectoryContent(images, directories, urlDirectory);
    }

    @Override
    public List<ImageItem> getDirectories() {
        List<ImageItem> directories = new ArrayList<>();
        String path = IMAGE_UPLOAD_DIR;
        File folder = new File(path);
        File[] files = folder.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    directories.add(new ImageItem(file.getName(), true, file.getName()));
                }
            }
        }
        return directories;
    }

    @Override
    public List<ImageItem> getImages() {
        List<ImageItem> images = new ArrayList<>();
        String path = IMAGE_UPLOAD_DIR;
        File folder = new File(path);
        File[] files = folder.listFiles();
        if (files != null) {
            for (File file : files) {
                if (!file.isDirectory()) {
                    images.add(new ImageItem(file.getName(), false, file.getName()));
                }
            }
        }
        return images;
    }

    @Override
    public List<ImageItem> getImagesInDirectory(String directory) {
        List<ImageItem> images = new ArrayList<>();
        File folder = new File("IMAGE_UPLOAD_DIR" + directory);
        File[] files = folder.listFiles((dir, name) -> !dir.isDirectory());
        if (files != null) {
            for (File file : files) {
                System.out.println(file.getAbsolutePath());
                String filePath = file.getAbsolutePath();
                // Kiểm tra nếu tập tin là hình ảnh
                if (isImageFile(filePath)) {
                    images.add(new ImageItem(file.getName(), false, filePath));
                }
            }
        }
        return images;
    }

    // Phương thức để kiểm tra nếu tệp là hình ảnh
    private boolean isImageFile(String filePath) {
        // Kiểm tra phần mở rộng của tệp
        String[] allowedExtensions = {"jpg", "jpeg", "png", "gif"};
        for (String extension : allowedExtensions) {
            if (filePath.toLowerCase().endsWith("." + extension)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public List<ImageItem> getDirectoryInDirectory(String directory) {
        List<ImageItem> directories = new ArrayList<>();
        File folder = new File(IMAGE_UPLOAD_DIR + directory);
        File[] files = folder.listFiles(File::isDirectory);
        if (files != null) {
            for (File file : files) {
                directories.add(new ImageItem(file.getName(), true, ""));
            }
        }
        return directories;
    }

    public void createFolder(String folderName, String urlDirectory) {
        try {
            File directory = new File("src/main/resources/static" + urlDirectory + "/" + folderName);
            if (!directory.exists()) {
                directory.mkdir();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveImage(MultipartFile file, String urlDirectoty) {
        try {
            String uniqueString = UUID.randomUUID().toString();
            String fileName = uniqueString + "-" + file.getOriginalFilename();
            byte[] bytes = file.getBytes();
            Path path = Paths.get("src/main/resources/static" + urlDirectoty + "/" + fileName);
            Files.write(path, bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteDirectoryAndImage(List<String> selectedValues, String inputDirectoryValue) {
        String imagesDirectory = "src/main/resources/static";

        for (String selectedItem : selectedValues) {
            File fileToDelete = new File(imagesDirectory + selectedItem);
            // Kiểm tra xem file/folder tồn tại trước khi xóa
            if (fileToDelete.exists()) {
                if (fileToDelete.isDirectory()) {
                    // Nếu là thư mục, sử dụng FileSystemUtils để xóa toàn bộ thư mục và nội dung của nó
                    FileSystemUtils.deleteRecursively(fileToDelete);
                } else {
                    // Nếu là file, sử dụng phương thức delete() để xóa file
                    fileToDelete.delete();
                }
            }
        }
    }

    public void deleteImage(String imageName) {
        File image = new File(IMAGE_UPLOAD_DIR + imageName);
        if (image.exists()) {
            image.delete();
        }
    }
}
