package com.westernyey.edanakolesah.main;

import static android.content.Intent.FLAG_ACTIVITY_SINGLE_TOP;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.westernyey.edanakolesah.R;

public class Account extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account);
        Button buttonMain = findViewById(R.id.buttonmain);
        Button buttonBin = findViewById(R.id.buttonbin);
        Button buttonAccount = findViewById(R.id.buttonaccount);
        Button tpbutton = findViewById(R.id.tpbutton);
        Button promonutton = findViewById(R.id.promonutton);
        Button history = findViewById(R.id.zakbutton);
        buttonMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Обработчик для кнопки "главная"
                Intent intent = new Intent(Account.this, Main.class);
                startActivity(intent);
            }
        });
        tpbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Обработчик для кнопки "помощь"
                Intent intent = new Intent(Account.this, com.westernyey.edanakolesah.main.nastacc.Support.class);
                startActivity(intent);
            }
        });
        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Обработчик для кнопки "история"
                Intent intent = new Intent(Account.this, com.westernyey.edanakolesah.main.nastacc.Historyzk.class);
                startActivity(intent);
            }
        });
        promonutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Обработчик для кнопки "промокод"
                Intent intent = new Intent(Account.this, com.westernyey.edanakolesah.main.nastacc.Promocod.class);
                startActivity(intent);
            }
        });

        buttonBin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Обработчик для кнопки "корзина"
                Intent intent = new Intent(Account.this, Bin.class);
                intent.setFlags(FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });

        buttonAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Обработчик для кнопки "аккаунт"
                Intent intent = new Intent(Account.this, Account.class);
                startActivity(intent);
            }
        });

    }
}