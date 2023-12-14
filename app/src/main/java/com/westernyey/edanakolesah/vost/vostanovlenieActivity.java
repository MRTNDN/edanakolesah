package com.westernyey.edanakolesah.vost;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.westernyey.edanakolesah.R;

public class vostanovlenieActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vostanovlenieactivity);

        Button editTextkodButton = findViewById(R.id.editTextkod);

        editTextkodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Создаем Intent для перехода к kodsmsactivity
                Intent intent = new Intent(vostanovlenieActivity.this, kodsmsactivity.class);

                // Запускаем активность для ввода кода SMS
                startActivity(intent);
            }
        });
    }
}
