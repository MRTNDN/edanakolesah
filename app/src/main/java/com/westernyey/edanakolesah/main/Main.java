package com.westernyey.edanakolesah.main;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.westernyey.edanakolesah.R;

import java.util.List;

public class Main extends AppCompatActivity {

    Button button1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        //Меняем имя вошедшего
        button1 = findViewById(R.id.buttonTop);
        Bundle extras = getIntent().getExtras();

        String value = extras.getString("key");
        button1.setText("Здравствуйте, "+value);

        Bundle extras1 = getIntent().getExtras();
        String addres = extras1.getString("keyAddress");


        // Находим RelativeLayout по его идентификатору
        RelativeLayout relativeLayout = findViewById(R.id.RELAP);

        // Устанавливаем слушатель клика по RelativeLayout
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Создаем Intent для перехода в следующую активити
                Intent intent = new Intent(Main.this, shopmenu.class);

                // Добавляем дополнительные данные, если необходимо
                intent.putExtra("keyAddress", addres);
                // Запускаем следующую активити
                startActivity(intent);
            }
        });

        // Находим RelativeLayout по его идентификатору
        relativeLayout = findViewById(R.id.RELAr);

        // Устанавливаем слушатель клика по RelativeLayout
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Создаем Intent для перехода в следующую активити
                Intent intent = new Intent(Main.this, restaraunt.class);

                // Добавляем дополнительные данные, если необходимо
                intent.putExtra("key", "value");
                intent.putExtra("keyAddress", addres);
                // Запускаем следующую активити
                startActivity(intent);
            }
        });

        // Находим RelativeLayout по его идентификатору
        relativeLayout = findViewById(R.id.RELA);

        // Устанавливаем слушатель клика по RelativeLayout
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Создаем Intent для перехода в следующую активити
                Intent intent = new Intent(Main.this, Stocks.class);

                // Добавляем дополнительные данные, если необходимо
                intent.putExtra("key", "value");
                intent.putExtra("keyAddress", addres);
                // Запускаем следующую активити
                startActivity(intent);
            }
        });

        // Пример обработки нажатия кнопки "Назад"
        Button backButton = findViewById(R.id.Back); // Замените на ваш реальный ID кнопки
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed(); // Вызывает обработчик нажатия кнопки "Назад"
            }
        });
    }

    @Override
    public void onBackPressed() {
        // Ваш код для обработки нажатия кнопки "Назад"
        // Здесь вы можете выполнить дополнительные действия перед переходом на предыдущую активность
        super.onBackPressed(); // Это вызывает стандартное поведение кнопки "Назад" (возврат к предыдущей активности)
    }
}
