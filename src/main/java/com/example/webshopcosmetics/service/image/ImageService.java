package com.example.webshopcosmetics.service.image;

import com.example.webshopcosmetics.model.DirectoryContent;
import com.example.webshopcosmetics.model.ImageItem;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
public interface ImageService {

    public List<ImageItem> getDirectories();

    public DirectoryContent getDirectoryContent();

    public DirectoryContent getDirectoryInDirectoryContent(String urlDirectory, String directoryName);

    public List<ImageItem> getImages();

    public List<ImageItem> getImagesInDirectory(String directory);

    public List<ImageItem> getDirectoryInDirectory(String directory);

    public void createFolder(String folderName, String urlDirectory);

    public void saveImage(MultipartFile imageFile, String urlDirectoty);

    public void deleteDirectoryAndImage(List<String> selectedValues, String inputDirectoryValue);

    public void deleteImage(String imageName);
}
