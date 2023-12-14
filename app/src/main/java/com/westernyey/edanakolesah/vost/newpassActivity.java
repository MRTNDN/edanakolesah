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

public class newpassActivity extends AppCompatActivity {
    EditText editTextDoublePass, editTextPass;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newpassactivity);

        // Находим компоненты в макете
         editTextPass = findViewById(R.id.editTextpass);
         editTextDoublePass = findViewById(R.id.doublepass);
         button = findViewById(R.id.BText);

        editTextPass.addTextChangedListener(new TextWatcher() {
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
        editTextDoublePass.addTextChangedListener(new TextWatcher() {
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

        // Назначаем слушателя для кнопки
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Показываем уведомление
                Toast.makeText(newpassActivity.this, "Пароль изменен!", Toast.LENGTH_SHORT).show();

                // Создаем Intent для перехода к MainActivity
                Intent intent = new Intent(newpassActivity.this, com.westernyey.edanakolesah.MainActivity.class);

                // Запускаем активность MainActivity
                startActivity(intent);

                // Закрываем текущую активность
                finish();
            }
        });
    }

    private void updateButtonState() {
        if (editTextPass.getText().toString().trim().isEmpty() | editTextDoublePass.getText().toString().trim().isEmpty()){
        } else {
            button.setVisibility(View.VISIBLE);
        }
    }
}
