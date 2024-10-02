package com.example.tiendavirtualuco.models;

public class Product {
    private String name;
    private double price;
    private double shippingCost;
    private int quantity;
    private boolean hasShippingCost;
    private int imageResource;

    public Product(String name, double price, double shippingCost, int quantity, boolean hasShippingCost, int imageResource) {
        this.name = name;
        this.price = price;
        this.shippingCost = shippingCost;
        this.quantity = quantity;
        this.hasShippingCost = hasShippingCost;
        this.imageResource = imageResource; // Corregido: ahora se inicializa correctamente
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public double getShippingCost() {
        return shippingCost;
    }

    public int getQuantity() {
        return quantity;
    }

    public boolean hasShippingCost() {
        return hasShippingCost;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getImageResource() {
        return imageResource; // Getter para el recurso drawable
    }
}
