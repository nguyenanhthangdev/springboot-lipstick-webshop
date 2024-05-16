package com.example.webshopcosmetics.model;

import java.util.List;

public class DirectoryContent {
    private List<ImageItem> images;
    private List<String> directories;

    private String urlDirectory;

    public DirectoryContent(List<ImageItem> images, List<String> directories, String urlDirectory) {
        this.images = images;
        this.directories = directories;
        this.urlDirectory = urlDirectory;
    }

    public List<ImageItem> getImages() {
        return images;
    }

    public void setImages(List<ImageItem> images) {
        this.images = images;
    }

    public List<String> getDirectories() {
        return directories;
    }

    public void setDirectories(List<String> directories) {
        this.directories = directories;
    }

    public String getUrlDirectory() {
        return urlDirectory;
    }

    public void setUrlDirectory(String urlDirectory) {
        this.urlDirectory = urlDirectory;
    }
}
