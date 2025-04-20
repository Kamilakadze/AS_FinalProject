package com.example.project;

import com.example.project.model.FoodItem;
import java.util.ArrayList;
import java.util.List;

public class OrderManager {
    private static final List<FoodItem> history = new ArrayList<>();

    public static void addOrders(List<FoodItem> items) {
        history.addAll(items);
    }

    public static List<FoodItem> getOrderHistory() {
        return new ArrayList<>(history);
    }
}
