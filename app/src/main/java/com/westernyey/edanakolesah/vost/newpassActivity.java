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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newpassactivity);

        // Находим компоненты в макете
        EditText editTextPass = findViewById(R.id.editTextpass);
        EditText editTextDoublePass = findViewById(R.id.doublepass);
        Button button = findViewById(R.id.BText);

        // Назначаем слушателя для текстового ввода
        editTextDoublePass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // Ничего не делаем перед изменением текста
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // Ничего не делаем во время изменения текста
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // После изменения текста проверяем, одинаковые ли значения в обоих EditText
                String pass = editTextPass.getText().toString();
                String doublePass = editable.toString();

                // Разрешаем или запрещаем нажатие на кнопку в зависимости от результата проверки
                button.setEnabled(pass.equals(doublePass));
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
}
