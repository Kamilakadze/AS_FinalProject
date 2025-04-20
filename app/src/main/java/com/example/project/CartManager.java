package com.example.project;

import com.example.project.model.FoodItem;

import java.util.ArrayList;
import java.util.List;

public class CartManager {
    private static final List<FoodItem> cart = new ArrayList<>();

    public static void addToCart(FoodItem item) {
        cart.add(item);
    }


    public static void clearCart() {
        cart.clear();
    }
    public static List<FoodItem> getCartItems() {
        return cart; // не new ArrayList<>(cart)
    }

    public static void removeItem(int position) {
        if (position >= 0 && position < cart.size()) {
            cart.remove(position);
        }
    }

    public static void removeSelected(List<FoodItem> selected) {
        cart.removeAll(selected); // ✅ исправлено
    }


}
