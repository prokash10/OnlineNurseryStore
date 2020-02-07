package com.example.onlinenurserystore.model;

public class Category {
    private String categoryImg;
    private String categoryName;
    private String _id;

    public Category(String categoryImg, String categoryName, String _id) {
        this.categoryImg = categoryImg;
        this.categoryName = categoryName;
        this._id = _id;
    }

    public String getCategoryImg() {
        return categoryImg;
    }

    public void setCategoryImg(String categoryImg) {
        this.categoryImg = categoryImg;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }
}
