package com.smartshopper.smartshoppergrocery;

public class GroceryItems {

    // Store the url of the  grocery image
    private String Image;
    // Store the name of the grocery item
    private String Name;
    // Store the description of the groecry item
    private String Description;
    // Store the price of the grocery item
    private double Price;

    public GroceryItems(){}

    public GroceryItems(String image, String name, String description, double price) {
        Image = image;
        Name = name;
        Description = description;
        Price = price;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }

}
