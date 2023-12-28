package com.westernyey.edanakolesah.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import com.westernyey.edanakolesah.R;


public class Main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        // Находим RelativeLayout по его идентификатору
        RelativeLayout relativeLayout = findViewById(R.id.RELA);

        // Добавляем обработчик событий OnClickListener
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Создаем Intent для запуска новой активности
                Intent intent = new Intent(Main.this, com.westernyey.edanakolesah.main.shopmenu.class);
                // Запускаем новую активность
                startActivity(intent);
            }
        });
    }
}
