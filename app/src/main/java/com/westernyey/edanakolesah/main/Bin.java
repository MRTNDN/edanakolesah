package com.westernyey.edanakolesah.main;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;

import com.westernyey.edanakolesah.R;

public class Bin extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bin);
        // Данные для заполнения списка
        String[] data = {"Элемент 1", "Элемент 2", "Элемент 3", "и так далее"};

        // Создание адаптера
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data);

        // Получение ссылки на ListView
        ListView listView = findViewById(R.id.listView); // Замените "listView" на ваш идентификатор ListView

        // Установка адаптера для ListView
        listView.setAdapter(adapter);
    }
}
