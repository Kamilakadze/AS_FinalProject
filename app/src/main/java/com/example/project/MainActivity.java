package com.example.project;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AlertDialog;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadFragment(new MenuFragment());

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            Fragment selected = null;
            int id = item.getItemId();

            if (id == R.id.nav_menu) {
                selected = new MenuFragment();
            } else if (id == R.id.nav_cart) {
                selected = new CartFragment();
            } else if (id == R.id.nav_orders) {
                selected = new OrdersFragment();
            } else if (id == R.id.nav_account) {
                showAccountOptionsDialog();
            }

            if (selected != null) {
                loadFragment(selected);
                return true;
            }
            return false;
        });
    }

    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)  // Заменяем фрагмент в контейнере
                .commit();
    }

    // Метод для отображения диалога с выбором: "Перейти в аккаунт" или "Выйти"
    private void showAccountOptionsDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Аккаунт");
        builder.setMessage("Что бы вы хотели сделать?");
        builder.setCancelable(true);

        // Кнопка "Выйти"
        builder.setPositiveButton("Выйти", (dialog, which) -> {
            logoutUser();  // Выйти из аккаунта
        });

        builder.show();
    }

    // Метод для выхода из аккаунта
    private void logoutUser() {
        // Логика выхода из аккаунта, например очистка данных SharedPreferences
        // SharedPreferences prefs = getSharedPreferences("user_data", MODE_PRIVATE);
        // prefs.edit().clear().apply();

        // Переход обратно на экран логина
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();  // Закрываем MainActivity
    }
}
