package com.example.project;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project.model.FoodItem;

import java.util.List;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.FoodViewHolder> {

    private final List<FoodItem> foodList;

    public FoodAdapter(List<FoodItem> foodList) {
        this.foodList = foodList;
    }

    @NonNull
    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_food, parent, false);
        return new FoodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodViewHolder holder, int position) {
        FoodItem food = foodList.get(position);

        holder.name.setText(food.getName());
        holder.description.setText(food.getDescription());
        holder.price.setText("₸ " + food.getPrice());
        holder.image.setImageResource(food.getImageResId());

        holder.btnAdd.setOnClickListener(v -> {
            CartManager.addToCart(food);
            Toast.makeText(v.getContext(), food.getName() + " добавлено в корзину", Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public int getItemCount() {
        return foodList.size();
    }

    static class FoodViewHolder extends RecyclerView.ViewHolder {
        TextView name, description, price;
        ImageView image;
        Button btnAdd;

        public FoodViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.foodName);
            description = itemView.findViewById(R.id.foodDescription);
            price = itemView.findViewById(R.id.foodPrice);
            image = itemView.findViewById(R.id.foodImage); // связываем ImageView
            btnAdd = itemView.findViewById(R.id.btnAddToCart);
        }
    }
}
