package com.westernyey.edanakolesah.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.westernyey.edanakolesah.R;

public class Main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        // Находим RelativeLayout по его идентификатору
        RelativeLayout relativeLayout = findViewById(R.id.RELA);
        RelativeLayout relativeLayoutp = findViewById(R.id.RELAP);
        RelativeLayout relativeLayoutr = findViewById(R.id.RELAr);

        // Добавляем обработчик событий OnClickListener для первого RelativeLayout
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Создаем Intent для запуска активности shopmenu
                Intent intent = new Intent(Main.this, shopmenu.class);
                // Запускаем новую активность
                intent.putExtra("fragmentType", "RELAP");
                startActivity(intent);
            }
        });

        // Добавляем обработчик событий OnClickListener для второго RelativeLayout
        relativeLayoutp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Создаем Intent для запуска активности shopmenu
                Intent intent = new Intent(Main.this, shopmenu.class);
                intent.putExtra("fragmentType", "RELAr");
                // Запускаем новую активность
                startActivity(intent);
            }
        });

        // Добавляем обработчик событий OnClickListener для третьего RelativeLayout
        relativeLayoutr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Создаем Intent для запуска активности shopmenu
                Intent intent = new Intent(Main.this, shopmenu.class);
                intent.putExtra("fragmentType", "RELA");
                // Запускаем новую активность
                startActivity(intent);
            }
        });
    }
}
