package com.example.project;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project.model.FoodItem;

import java.util.Arrays;
import java.util.List;

public class MenuFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.foodRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        List<FoodItem> foodList = Arrays.asList(
                new FoodItem("Пицца Маргарита", "Классическая с моцареллой", 1790, R.drawable.pizza_margherita),
                new FoodItem("Бургер Чеддер", "Говядина, сыр, соус BBQ", 1390, R.drawable.burger_cheddar),
                new FoodItem("Суши сет", "Роллы, васаби, имбирь", 2590, R.drawable.sushi_set),
                new FoodItem("Донер", "Курица, фри, салат, соус", 1695, R.drawable.doner)
        );


        FoodAdapter adapter = new FoodAdapter(foodList);
        recyclerView.setAdapter(adapter);

        return view;
    }
}
