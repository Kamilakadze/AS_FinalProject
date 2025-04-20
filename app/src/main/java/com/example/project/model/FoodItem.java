package com.example.project.model;

public class FoodItem {
    private String name;
    private String description;
    private int price;
    private int imageResId; // 📷 ID картинки

    public FoodItem(String name, String description, int price, int imageResId) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.imageResId = imageResId;
    }

    // Геттеры
    public String getName() { return name; }
    public String getDescription() { return description; }
    public int getPrice() { return price; }
    public int getImageResId() { return imageResId; }
}

