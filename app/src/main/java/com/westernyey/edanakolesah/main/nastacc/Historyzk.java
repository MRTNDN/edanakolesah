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

public class Historyzk extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.historyzk);

        Button buttonMain = findViewById(R.id.buttonmain);
        Button buttonBin = findViewById(R.id.buttonbin);
        Button buttonAccount = findViewById(R.id.buttonaccount);
        Button buttonZ = findViewById(R.id.buttonzakazat);

        buttonMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(com.westernyey.edanakolesah.main.nastacc.Historyzk.this, Main.class);
                startActivity(intent);
            }
        });

        buttonBin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(com.westernyey.edanakolesah.main.nastacc.Historyzk.this, Bin.class);
                startActivity(intent);
            }
        });

        buttonAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(com.westernyey.edanakolesah.main.nastacc.Historyzk.this, com.westernyey.edanakolesah.main.Account.class);
                startActivity(intent);
            }
        });
        buttonZ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(com.westernyey.edanakolesah.main.nastacc.Historyzk.this, com.westernyey.edanakolesah.main.Main.class);
                startActivity(intent);

            }
        });
    }
}
