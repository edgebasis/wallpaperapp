package com.edgebasis.wallpapers.Model;

/**
 * Created by mujtabamahmood on 4/1/18.
 */

public class Category {
    public String name;
    public String link;


    public Category() {
    }

    public Category(String name, String link) {
        this.name = name;
        this.link = link;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
