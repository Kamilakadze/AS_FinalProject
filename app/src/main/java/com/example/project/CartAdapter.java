package com.example.project;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project.model.FoodItem;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private final List<FoodItem> cartItems;
    private final Set<Integer> selectedPositions = new HashSet<>();

    public CartAdapter(List<FoodItem> cartItems) {
        this.cartItems = cartItems;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_food, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        FoodItem item = cartItems.get(position);
        holder.name.setText(item.getName());
        holder.description.setText(item.getDescription());
        holder.price.setText("₸ " + item.getPrice());
        holder.image.setImageResource(item.getImageResId());

        holder.btnAdd.setVisibility(View.GONE);
        holder.checkBox.setVisibility(View.VISIBLE);
        holder.btnDelete.setVisibility(View.VISIBLE);

        holder.checkBox.setOnCheckedChangeListener(null);
        holder.checkBox.setChecked(selectedPositions.contains(position));
        holder.checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) selectedPositions.add(holder.getAdapterPosition());
            else selectedPositions.remove(holder.getAdapterPosition());
        });

        holder.btnDelete.setOnClickListener(v -> {
            CartManager.removeItem(holder.getAdapterPosition());
            notifyItemRemoved(holder.getAdapterPosition());
            notifyItemRangeChanged(holder.getAdapterPosition(), cartItems.size());
        });
    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }

    public List<FoodItem> getSelectedItems() {
        List<FoodItem> selected = new java.util.ArrayList<>();
        for (int pos : selectedPositions) {
            if (pos >= 0 && pos < cartItems.size()) {
                selected.add(cartItems.get(pos));
            }
        }
        return selected;
    }

    public void removeSelectedItems() {
        cartItems.removeAll(getSelectedItems());
        selectedPositions.clear();
        notifyDataSetChanged();
    }

    static class CartViewHolder extends RecyclerView.ViewHolder {
        TextView name, description, price;
        ImageView image;
        CheckBox checkBox;
        Button btnAdd, btnDelete;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.foodName);
            description = itemView.findViewById(R.id.foodDescription);
            price = itemView.findViewById(R.id.foodPrice);
            image = itemView.findViewById(R.id.foodImage);
            checkBox = itemView.findViewById(R.id.foodCheckbox);
            btnAdd = itemView.findViewById(R.id.btnAddToCart);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }
    }
}
