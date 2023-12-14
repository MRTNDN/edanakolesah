package com.westernyey.edanakolesah.vost;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.westernyey.edanakolesah.R;

public class kodsmsactivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kodsmsactivity1);

        Button sendButton = findViewById(R.id.BText);

        // Назначим обработчик нажатия
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Найдем поле для ввода кода
                EditText codeEditText = findViewById(R.id.editText);

                // Получим введенный код
                String code = codeEditText.getText().toString();

                // Покажем всплывающее уведомление с введенным кодом (в реальном приложении нужно отправить код на сервер для проверки)
                Toast.makeText(kodsmsactivity.this, "Код: " + code, Toast.LENGTH_SHORT).show();

                // Создаем Intent для перехода к newpassActivity
                Intent intent = new Intent(kodsmsactivity.this, newpassActivity.class);

                // Запускаем активность newpassActivity
                startActivity(intent);
            }
        });
    }
}
