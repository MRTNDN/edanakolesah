package com.westernyey.edanakolesah;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.PictureDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.caverock.androidsvg.SVG;
import com.caverock.androidsvg.SVGParseException;

import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    private EditText editText1;
    private EditText editText2;
    private Button button4;
    private Button button3;
    private Button button5;

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


        // Загружаем и устанавливаем SVG для каждого изображения
        loadSvgFromResource(R.raw.vk, R.id.imageView1);
        loadSvgFromResource(R.raw.yandex, R.id.imageView2);
        loadSvgFromResource(R.raw.mailru, R.id.imageView3);
        loadSvgFromResource(R.raw.telegram, R.id.imageView4);

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
            button3.setEnabled(false);
        } else {
            button3.setEnabled(true);
        }
    }

    private void loadSvgFromResource(int resourceId, int imageViewId) {
        try {
            Resources resources = getResources();
            InputStream inputStream = resources.openRawResource(resourceId);
            SVG svg = SVG.getFromInputStream(inputStream);
            PictureDrawable pictureDrawable = new PictureDrawable(svg.renderToPicture());

            // Находим ImageView по ID и устанавливаем SVG-изображение
            ImageView imageView = findViewById(imageViewId);
            imageView.setImageDrawable(pictureDrawable);
        } catch (SVGParseException e) {
            e.printStackTrace();
        }
    }

    public void startMain(View view){
        Intent intent = new Intent(MainActivity.this, com.westernyey.edanakolesah.main.Main.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
    }
}
