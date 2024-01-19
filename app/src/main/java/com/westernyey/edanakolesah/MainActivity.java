package com.westernyey.edanakolesah;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.PictureDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.caverock.androidsvg.SVG;
import com.caverock.androidsvg.SVGParseException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.westernyey.edanakolesah.main.Main;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    private EditText editText1;
    private EditText editText2;
    private Button button4;
    private Button button3;
    private Button button5;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button4 = findViewById(R.id.button4);
        button3 = findViewById(R.id.button3);
        button5 = findViewById(R.id.button5);

        editText1 = findViewById(R.id.editText1);
        editText2 = findViewById(R.id.editText2);


        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, registrActivity.class);
                startActivity(intent);
            }
        });

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, com.westernyey.edanakolesah.vost.vostanovlenieActivity.class);
                startActivity(intent);
            }
        });

        // Добавляем прослушиватель текста в EditText1
        editText1.addTextChangedListener(new TextWatcher() {
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
        editText2.addTextChangedListener(new TextWatcher() {
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
        if (editText1.getText().toString().trim().isEmpty() || editText2.getText().toString().trim().isEmpty()) {
        } else {
            button3.setVisibility(View.VISIBLE);
        }
    }



    public void click(View view){
        db.collection("client")
                .whereEqualTo("login", editText1.getText().toString())
                .whereEqualTo("password", editText2.getText().toString())
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                String name = document.getString("name");
                                String address = document.getString("address");
                                //передача данных между активити

                                Intent intent = new Intent(MainActivity.this, Main.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                                intent.putExtra("key",name);
                                intent.putExtra("keyAddress", address);
                                startActivity(intent);
                            }
                        } else {
                            Toast.makeText(MainActivity.this, "Пользователь не найден!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

}
