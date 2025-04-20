package com.example.project;

import android.os.Bundle;
import android.view.*;
import android.widget.*;
import androidx.annotation.*;
import androidx.fragment.app.Fragment;

import com.example.project.model.FoodItem;

import java.util.List;

public class OrdersFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        ScrollView scrollView = new ScrollView(getContext());
        LinearLayout layout = new LinearLayout(getContext());
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(24, 24, 24, 24);

        List<FoodItem> history = OrderManager.getOrderHistory();
        for (FoodItem item : history) {
            TextView tv = new TextView(getContext());
            tv.setText(item.getName() + "\n" + item.getDescription() + "\n₸ " + item.getPrice());
            tv.setPadding(0, 24, 0, 24);
            layout.addView(tv);
        }

        scrollView.addView(layout);
        return scrollView;
    }
}
