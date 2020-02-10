package com.example.onlinenurserystore.model;

public class Products {
    private String _id;
    private String Productname;
    private String Productimage;
    private  float Price;
    private String Productdescription;
    private String Date;
    private  String Categoryid;

    public Products(String _id) {
        this._id = _id;
    }

    public Products(String productname, String productimage, float price, String productdescription, String date, String categoryid) {
        Productname = productname;
        Productimage = productimage;
        Price = price;
        Productdescription = productdescription;
        Date = date;
        Categoryid = categoryid;
    }

    public String get_id() {
        return _id;
    }

    public String getProductname() {
        return Productname;
    }

    public void setProductname(String productname) {
        Productname = productname;
    }

    public String getProductimage() {
        return Productimage;
    }

    public void setProductimage(String productimage) {
        Productimage = productimage;
    }

    public float getPrice() {
        return Price;
    }

    public void setPrice(float price) {
        Price = price;
    }

    public String getProductdescription() {
        return Productdescription;
    }

    public void setProductdescription(String productdescription) {
        Productdescription = productdescription;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getCategoryid() {
        return Categoryid;
    }

    public void setCategoryid(String categoryid) {
        Categoryid = categoryid;
    }
}
