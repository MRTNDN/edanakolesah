// shopmenu.java
package com.westernyey.edanakolesah.main;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.westernyey.edanakolesah.R;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class Stocks extends AppCompatActivity {
    String addres;
    ImageView imgStock1;
    ImageView imgStock2;
    ImageView imgStock3;
    ImageView imgStock4;
    ImageView imgStock5;
    ImageView imgStock6;

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    TextView nazvStock1, nazvStock2, nazvStock3, nazvStock4, nazvStock5, nazvStock6, priceStock1, priceStock2, priceStock3, priceStock4, priceStock5, priceStock6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stocks);

        Bundle extras1 = getIntent().getExtras();
        addres = extras1.getString("keyAddress");

        //Создание массива imageView
        ImageView[] images = new ImageView[]{
                imgStock1 = findViewById(R.id.stockprod1),
                imgStock2 = findViewById(R.id.stockprod2),
                imgStock3 = findViewById(R.id.stockprod3),
                imgStock4 = findViewById(R.id.stockprod4),
                imgStock5 = findViewById(R.id.stockprod5),
                imgStock6 = findViewById(R.id.stockprod6)
        };

        //Создание массивов textView
        TextView[] name_prod = new TextView[]{
                nazvStock1 = findViewById(R.id.nazvstock1),
                nazvStock2 = findViewById(R.id.nazvstock2),
                nazvStock3 = findViewById(R.id.nazvstock3),
                nazvStock4 = findViewById(R.id.nazvstock4),
                nazvStock5 = findViewById(R.id.nazvstock5),
                nazvStock6 = findViewById(R.id.nazvstock6)
        };
        TextView[] price_prod = new TextView[]{
                priceStock1 = findViewById(R.id.pricestock1),
                priceStock2 = findViewById(R.id.pricestock2),
                priceStock3 = findViewById(R.id.pricestock3),
                priceStock4 = findViewById(R.id.pricestock4),
                priceStock5 = findViewById(R.id.pricestock5),
                priceStock6 = findViewById(R.id.pricestock6)
        };

        //Парсинг изображения из бд для конкретного окна
        for (int i = 1; i < 7; i++) {
            int num = i;
            String id = String.valueOf(i);
            CollectionReference colRef = db.collection("image_to_product");
            colRef.get()
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            List<DocumentSnapshot> documents = task.getResult().getDocuments();
                            for (DocumentSnapshot doc : documents) {

                                String idProduct = doc.getString("id_product"); // Получаем значение поля "id_product"
                                if (idProduct.equals(id)) {
                                    // Если нашли совпадение, выводим данные документа
                                    String image = doc.getString("image");
                                    loadImageFromUrl(image, images[num - 1]);

                                }
                            }
                        }
                    });

            CollectionReference colRef2 = db.collection("product");
            colRef2.get()
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            List<DocumentSnapshot> documents = task.getResult().getDocuments();
                            for (DocumentSnapshot doc : documents) {

                                String idProduct = doc.getString("id_product"); // Получаем значение поля "id_product"
                                if (idProduct.equals(id)) {
                                    // Если нашли совпадение, выводим данные документа
                                    String name = doc.getString("name_product");
                                    String price = doc.getString("price");
                                    name_prod[num - 1].setText(name);
                                    price_prod[num - 1].setText(price + " ₽");
                                }
                            }
                        }
                    });
        }
    }

    private void loadImageFromUrl(String url, ImageView imgRest) {

        new AsyncTask<String, Void, Bitmap>() {
            @Override
            protected Bitmap doInBackground(String... params) {
                try {
                    URL imageUrl = new URL(params[0]);
                    HttpURLConnection connection = (HttpURLConnection) imageUrl.openConnection();
                    connection.setDoInput(true);
                    connection.connect();
                    InputStream inputStream = connection.getInputStream();
                    return BitmapFactory.decodeStream(inputStream);
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            }

            @Override
            protected void onPostExecute(Bitmap bitmap) {
                if (bitmap != null) {
                    imgRest.setImageBitmap(bitmap);
                } else {
// Если изображение не загружено, установить изображение по умолчанию
                    imgRest.setImageResource(R.drawable.logo);
                }
            }
        }.execute(url);

        Button buttonMain = findViewById(R.id.buttonmain);
        Button buttonBin = findViewById(R.id.buttonbin);
        Button buttonAccount = findViewById(R.id.buttonaccount);

        buttonMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Обработчик для кнопки "главная"
                Intent intent = new Intent(Stocks.this, Main.class);
                startActivity(intent);
            }
        });

        buttonBin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Обработчик для кнопки "корзина"
                Intent intent = new Intent(Stocks.this, Bin.class);
                intent.putExtra("keyAddress", addres);
                startActivity(intent);
            }
        });

        buttonAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Обработчик для кнопки "аккаунт"
                Intent intent = new Intent(Stocks.this, Account.class); //
                startActivity(intent);
            }
        });
    }

        public void onClickPlusOrMinus(View v) {
            // Получаем соответствующий TextView
            TextView scetchikTextView = null;

            if (v.getId() == R.id.buttonPlusstocks1 || v.getId() == R.id.buttonminusstocks1) {
                scetchikTextView = findViewById(R.id.scetchikstocks1);
            } else if (v.getId() == R.id.buttonPlusstocks2 || v.getId() == R.id.buttonminusstocks2) {
                scetchikTextView = findViewById(R.id.scetchikstocks2);
            } else if (v.getId() == R.id.buttonPlusstocks3 || v.getId() == R.id.buttonminusstocks3) {
                scetchikTextView = findViewById(R.id.scetchikstocks3);
            } else if (v.getId() == R.id.buttonPlusstocks4 || v.getId() == R.id.buttonminusstocks4) {
                scetchikTextView = findViewById(R.id.scetchikstocks4);
            } else if (v.getId() == R.id.buttonPlusstocks5 || v.getId() == R.id.buttonminusstocks5) {
                scetchikTextView = findViewById(R.id.scetchikstocks5);
            } else if (v.getId() == R.id.buttonPlusstocks6 || v.getId() == R.id.buttonminusstocks6) {
                scetchikTextView = findViewById(R.id.scetchikstocks6);
            }

            if (scetchikTextView != null) {
                // Получаем текущее значение
                int currentValue = Integer.parseInt(scetchikTextView.getText().toString());

                // Обрабатываем "+" или "-"
                if (v.getId() == R.id.buttonPlusstocks1 || v.getId() == R.id.buttonPlusstocks2 || v.getId() == R.id.buttonPlusstocks3 || v.getId() == R.id.buttonPlusstocks4 || v.getId() == R.id.buttonPlusstocks5 || v.getId() == R.id.buttonPlusstocks6) {
                    // Увеличиваем значение, если не превышает 10
                    if (currentValue < 10) {
                        currentValue++;
                    }
                } else if (v.getId() == R.id.buttonminusstocks1 || v.getId() == R.id.buttonminusstocks2 || v.getId() == R.id.buttonminusstocks3 || v.getId() == R.id.buttonminusstocks4 || v.getId() == R.id.buttonminusstocks5 || v.getId() == R.id.buttonminusstocks6) {
                    // Уменьшаем значение, если не отрицательное
                    if (currentValue > 0) {
                        currentValue--;
                    }
                }

                // Устанавливаем новое значение в TextView
                scetchikTextView.setText(String.valueOf(currentValue));
            }
        }
}