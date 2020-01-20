package com.example.player.video;

public class ListViewItem {
    private String path;
    private boolean isDirectory;

    public ListViewItem(String path, boolean isDirectory) {
        this.path = path;
        this.isDirectory = isDirectory;
    }

    public String getPath() {
        return path;
    }


    public boolean isDirectory() {
        return isDirectory;
    }

}
