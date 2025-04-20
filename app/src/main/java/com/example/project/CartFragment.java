package com.example.project;

import android.os.Bundle;
import android.view.*;
import android.widget.Button;
import androidx.annotation.*;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.*;

import com.example.project.model.FoodItem;

import java.util.List;

public class CartFragment extends Fragment {

    private CartAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.cartRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // ✅ Используем оригинальный список
        List<FoodItem> items = CartManager.getCartItems();
        adapter = new CartAdapter(items);
        recyclerView.setAdapter(adapter);

        Button btnClear = view.findViewById(R.id.btnClear);
        Button btnCheckout = view.findViewById(R.id.btnCheckout);

        btnClear.setOnClickListener(v -> {
            CartManager.clearCart();
            adapter.notifyDataSetChanged(); // обновить адаптер сразу
        });

        btnCheckout.setOnClickListener(v -> {
            List<FoodItem> selected = adapter.getSelectedItems();
            if (!selected.isEmpty()) {
                CartManager.removeSelected(selected);      // из менеджера
                OrderManager.addOrders(selected);          // сохранить в историю
                adapter.removeSelectedItems();             // из адаптера
            }
        });

        return view;
    }
}
