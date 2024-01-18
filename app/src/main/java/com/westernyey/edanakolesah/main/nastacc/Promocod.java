package com.westernyey.edanakolesah.main.nastacc;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.westernyey.edanakolesah.R;
import com.westernyey.edanakolesah.main.Bin;
import com.westernyey.edanakolesah.main.Main;
import com.westernyey.edanakolesah.vost.kodsmsactivity;
import com.westernyey.edanakolesah.vost.newpassActivity;

public class Promocod extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.promocod);

        Button buttonMain = findViewById(R.id.buttonmain);
        Button buttonBin = findViewById(R.id.buttonbin);
        Button buttonAccount = findViewById(R.id.buttonaccount);

        buttonMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(com.westernyey.edanakolesah.main.nastacc.Promocod.this, Main.class);
                startActivity(intent);
            }
        });

        buttonBin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(com.westernyey.edanakolesah.main.nastacc.Promocod.this, Bin.class);
                startActivity(intent);
            }
        });

        buttonAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(com.westernyey.edanakolesah.main.nastacc.Promocod.this, com.westernyey.edanakolesah.main.Account.class);
                startActivity(intent);
            }
        });

        EditText codeEditText = findViewById(R.id.promoup);
        Button sendButton = findViewById(R.id.confirmprom);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String code = codeEditText.getText().toString();
                Toast.makeText(Promocod.this, "Промокод " + code + "не активирован, попробуйте позже", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
