package com.example.project;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class AccountActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        // Пример отображения имени пользователя
        TextView tvUsername = findViewById(R.id.tvUsername);
        tvUsername.setText("Имя пользователя: " + "User123"); // Заменить на динамическое имя из SharedPreferences или БД
    }
}
