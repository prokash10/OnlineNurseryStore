package com.example.onlinenurserystore.model;

public class Category {
    private int categoryImg;
    private String categoryName;

    public Category(int categoryImg, String categoryName) {
        this.categoryImg = categoryImg;
        this.categoryName = categoryName;
    }

    public int getCategoryImg() {
        return categoryImg;
    }

    public void setCategoryImg(int categoryImg) {
        this.categoryImg = categoryImg;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
