package com.edgebasis.wallpapers.Model;

/**
 * Created by mujtabamahmood on 4/2/18.
 */

public class Wallpaper {
    private String categoryId;
    private String link;


    public Wallpaper() {
    }

    public Wallpaper(String categoryId, String link) {
        this.categoryId = categoryId;
        this.link = link;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }
}
