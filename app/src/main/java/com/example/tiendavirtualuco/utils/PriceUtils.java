package com.example.tiendavirtualuco.utils;

import com.example.tiendavirtualuco.models.Product;

import java.util.List;

public class PriceUtils {

    /**
     * Calcula el precio total de los productos en el carrito, sin incluir el costo de envío.
     * @param productList Lista de productos en el carrito.
     * @return El precio total de los productos.
     */
    public static double calculateTotalPrice(List<Product> productList) {
        double totalPrice = 0;

        for (Product product : productList) {
            totalPrice += product.getPrice() * product.getQuantity();
        }

        return totalPrice;
    }

    /**
     * Calcula el costo total del envío para los productos en el carrito, teniendo en cuenta la cantidad de productos.
     * @param productList Lista de productos en el carrito.
     * @return El costo total del envío.
     */
    public static double calculateTotalShippingCost(List<Product> productList) {
        double totalShippingCost = 0;

        for (Product product : productList) {
            if (product.hasShippingCost()) {
                totalShippingCost += product.getShippingCost() * product.getQuantity();
            }
        }

        return totalShippingCost;
    }

    /**
     * Calcula el costo total que incluye tanto los productos como el costo de envío.
     * @param productList Lista de productos en el carrito.
     * @return El costo total de los productos con envío incluido.
     */
    public static double calculateTotalWithShipping(List<Product> productList) {
        double totalPrice = calculateTotalPrice(productList);
        double totalShippingCost = calculateTotalShippingCost(productList);

        return totalPrice + totalShippingCost;
    }
}
