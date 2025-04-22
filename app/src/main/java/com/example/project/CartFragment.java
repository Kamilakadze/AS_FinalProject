package com.example.project;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project.model.FoodItem;

import java.util.List;

public class CartFragment extends Fragment {

    private CartAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.cartRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        List<FoodItem> items = CartManager.getCartItems();
        adapter = new CartAdapter(items);
        recyclerView.setAdapter(adapter);

        Button btnClear = view.findViewById(R.id.btnClear);
        Button btnCheckout = view.findViewById(R.id.btnCheckout);

        // Кнопка очистки корзины
        btnClear.setOnClickListener(v -> {
            CartManager.clearCart();  // Очищаем корзину
            adapter.notifyDataSetChanged();  // Обновляем адаптер
            Toast.makeText(getContext(), "Корзина очищена", Toast.LENGTH_SHORT).show();
        });

        // Кнопка оформления заказа
        btnCheckout.setOnClickListener(v -> {
            // Оформляем заказ
            showOrderNotification();

            List<FoodItem> selectedItems = adapter.getSelectedItems();
            if (!selectedItems.isEmpty()) {
                CartManager.removeSelected(selectedItems); // Удаляем выбранные блюда из корзины
                OrderManager.addOrders(selectedItems);      // Сохраняем в историю заказов
                adapter.removeSelectedItems();             // Обновляем адаптер
            }
        });

        return view;
    }

    private void showOrderNotification() {
        // Канал уведомлений
        String channelId = "order_channel";
        NotificationManager manager = (NotificationManager) getContext().getSystemService(Context.NOTIFICATION_SERVICE);

        if (manager == null) {
            Toast.makeText(getContext(), "Не удалось инициализировать NotificationManager", Toast.LENGTH_SHORT).show();
            return;
        }

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    channelId,
                    "Order Notifications",
                    NotificationManager.IMPORTANCE_DEFAULT
            );
            channel.setDescription("Notifications for orders");
            manager.createNotificationChannel(channel);
        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(getContext(), channelId)
                .setSmallIcon(R.drawable.ic_cart)
                .setContentTitle("Спасибо за покупку!")
                .setContentText("Ваш заказ оформлен успешно.")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        manager.notify(102, builder.build());
    }
}
