package com.westernyey.edanakolesah.main.nastacc;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.westernyey.edanakolesah.R;
import com.westernyey.edanakolesah.main.Bin;
import com.westernyey.edanakolesah.main.Main;

public class Support extends AppCompatActivity {
    String addres, numberOfOrder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.support);
        Button buttonMain = findViewById(R.id.buttonmain);
        Button buttonBin = findViewById(R.id.buttonbin);
        Button buttonAccount = findViewById(R.id.buttonaccount);

        Bundle extras1 = getIntent().getExtras();
        addres = extras1.getString("keyAddress");
        numberOfOrder = extras1.getString("number");

        buttonMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Обработчик для кнопки "главная"
                Intent intent = new Intent(com.westernyey.edanakolesah.main.nastacc.Support.this, Main.class);
                intent.putExtra("keyAddress", addres);
                startActivity(intent);
            }
        });

        buttonBin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Обработчик для кнопки "корзина"
                Intent intent = new Intent(com.westernyey.edanakolesah.main.nastacc.Support.this, Bin.class);
                intent.putExtra("keyAddress", addres);
                intent.putExtra("number", numberOfOrder);
                startActivity(intent);
            }
        });

        buttonAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Обработчик для кнопки "аккаунт"
                Intent intent = new Intent(com.westernyey.edanakolesah.main.nastacc.Support.this, com.westernyey.edanakolesah.main.Account.class);
                intent.putExtra("keyAddress", addres);
                intent.putExtra("number", numberOfOrder);
                startActivity(intent);
            }
        });
    }
}