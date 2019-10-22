package com.smartshopper.smartshoppergrocery;

public class SelectedcartItems {

    private String productImage;
    private String productname;
    private String productPrice;
    private String productQuantity;

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(String productQuantity) {
        this.productQuantity = productQuantity;
    }

    public SelectedcartItems(){}

    public SelectedcartItems(String productImage, String productname, String productPrice, String productQuantity) {
        this.productImage = productImage;
        this.productname = productname;
        this.productPrice = productPrice;
        this.productQuantity = productQuantity;
    }

}
