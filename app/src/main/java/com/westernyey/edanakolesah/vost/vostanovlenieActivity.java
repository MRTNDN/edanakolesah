package com.westernyey.edanakolesah.vost;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Firebase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.westernyey.edanakolesah.R;
import com.westernyey.edanakolesah.main.Bin;

public class vostanovlenieActivity extends AppCompatActivity {

    Button editTextkodButton;
    TextView editTextLogin, editText;
    String login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vostanovlenieactivity);
        editTextLogin = findViewById(R.id.editTextLogin);
        editTextkodButton = findViewById(R.id.editTextkod);
        editText = findViewById(R.id.editText);
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        editTextkodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.collection("client")
                        .whereEqualTo("login", editTextLogin.getText())
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (!task.isSuccessful()) {
                                    Toast.makeText(vostanovlenieActivity.this, "Такого пользователя не существует!", Toast.LENGTH_SHORT).show();
                                } else {
                                    for (QueryDocumentSnapshot document : task.getResult()) {
                                        login = document.getString("login");
                                    }
                                    // Создаем Intent для перехода к kodsmsactivity
                                    Intent intent = new Intent(vostanovlenieActivity.this, kodsmsactivity.class);
                                    intent.putExtra("log", login);
                                    // Запускаем активность для ввода кода SMS
                                    startActivity(intent);
                                }
                            }
                        });


            }
        });
        editText.addTextChangedListener(new TextWatcher() {
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

        // Добавляем прослушиватель текста в EditText2
        editTextLogin.addTextChangedListener(new TextWatcher() {
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
                // Проверяем, пустой ли текст в EditText2 и в зависимости от этого активируем/деактивируем кнопку4
                updateButtonState();
            }
        });
    }

    private void updateButtonState() {
        if (editText.getText().toString().trim().isEmpty() | editTextLogin.getText().toString().trim().isEmpty()) {
        } else {
            editTextkodButton.setVisibility(View.VISIBLE);
        }
    }
}
