package com.westernyey.edanakolesah.main;

import static android.content.Intent.FLAG_ACTIVITY_SINGLE_TOP;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.westernyey.edanakolesah.R;

public class Account extends AppCompatActivity {
    String addres, numberOfOrder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account);
        Button buttonMain = findViewById(R.id.buttonmain);
        Button buttonBin = findViewById(R.id.buttonbin);
        Button buttonAccount = findViewById(R.id.buttonaccount);
        Button tpbutton = findViewById(R.id.tpbutton);
        Button promonutton = findViewById(R.id.promonutton);
        Button passbutton = findViewById(R.id.passbutton);

        Bundle extras1 = getIntent().getExtras();

        addres = extras1.getString("keyAddress");
        numberOfOrder = extras1.getString("number");

        buttonMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Обработчик для кнопки "главная"
                Intent intent = new Intent(Account.this, Main.class);
                intent.putExtra("keyAddress", addres);
                startActivity(intent);
            }
        });
        tpbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Обработчик для кнопки "помощь"
                Intent intent = new Intent(Account.this, com.westernyey.edanakolesah.main.nastacc.Support.class);
                intent.putExtra("keyAddress", addres);
                if(numberOfOrder != null){
                    intent.putExtra("number", numberOfOrder);}
                startActivity(intent);
            }
        });
        promonutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Обработчик для кнопки "промокод"
                Intent intent = new Intent(Account.this, com.westernyey.edanakolesah.main.nastacc.Promocod.class);
                intent.setFlags(FLAG_ACTIVITY_SINGLE_TOP);
                intent.putExtra("keyAddress", addres);
                if(numberOfOrder != null){
                intent.putExtra("number", numberOfOrder);}
                startActivity(intent);
            }
        });
        passbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Обработчик для кнопки "изменить пароль."
                Intent intent = new Intent(Account.this, com.westernyey.edanakolesah.vost.vostanovlenieActivity.class);
                intent.setFlags(FLAG_ACTIVITY_SINGLE_TOP);

                startActivity(intent);
            }
        });


        buttonBin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Обработчик для кнопки "корзина"
                Intent intent = new Intent(Account.this, Bin.class);
                intent.setFlags(FLAG_ACTIVITY_SINGLE_TOP);
                intent.putExtra("keyAddress", addres);
                intent.putExtra("number",numberOfOrder);
                startActivity(intent);
            }
        });

        buttonAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Обработчик для кнопки "аккаунт"
                Intent intent = new Intent(Account.this, Account.class);
                intent.setFlags(FLAG_ACTIVITY_SINGLE_TOP);
                intent.putExtra("keyAddress", addres);
                if(numberOfOrder != null){
                    intent.putExtra("number", numberOfOrder);}
                startActivity(intent);
            }
        });

    }
}