package com.example.onlinenurserystore.model;

public class Products {
    private String ProductName;
    private String ProductImage;
    private  float Price;
    private String ProductDescription;

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public String getProductImage() {
        return ProductImage;
    }

    public void setProductImage(String productImage) {
        ProductImage = productImage;
    }

    public float getPrice() {
        return Price;
    }

    public void setPrice(float price) {
        Price = price;
    }

    public String getProductDescription() {
        return ProductDescription;
    }

    public void setProductDescription(String productDescription) {
        ProductDescription = productDescription;
    }

    public Products(String productName, String productImage, float price, String productDescription) {
        ProductName = productName;
        ProductImage = productImage;
        Price = price;
        ProductDescription = productDescription;
    }
}
