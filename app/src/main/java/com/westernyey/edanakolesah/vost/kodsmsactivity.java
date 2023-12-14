package com.westernyey.edanakolesah.vost;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.westernyey.edanakolesah.R;

public class kodsmsactivity extends AppCompatActivity {
    Button sendButton;
    EditText codeEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kodsmsactivity1);
        sendButton = findViewById(R.id.BText);
        codeEditText = findViewById(R.id.editText);
        // Назначим обработчик нажатия
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Найдем поле для ввода кода


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
        codeEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // Не используется, оставляем пустым
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // Не используется, оставляем пустым
            }
            @Override
            public void afterTextChanged(Editable editable) {
                // Проверяем, пустой ли текст в EditText1 и в зависимости от этого активируем/деактивируем кнопку4
                updateButtonState();
            }
        });


    }
    private void updateButtonState() {
        if (codeEditText.getText().toString().trim().isEmpty()){
        } else {
            sendButton.setVisibility(View.VISIBLE);
        }
    }
}
