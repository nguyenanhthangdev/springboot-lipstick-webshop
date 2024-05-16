package com.example.webshopcosmetics.model;

public class ImageItem {
    private String name;
    private boolean isDirectory;

    private String path;

    public ImageItem(String name, boolean isDirectory, String path) {
        this.name = name;
        this.isDirectory = isDirectory;
        this.path = path;
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isDirectory() {
        return isDirectory;
    }

    public void setDirectory(boolean directory) {
        isDirectory = directory;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
