// shopmenu.java
package com.westernyey.edanakolesah.main;

import static android.content.Intent.FLAG_ACTIVITY_SINGLE_TOP;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.westernyey.edanakolesah.R;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.TextView;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class restaraunt extends AppCompatActivity {
    String addres;
    ImageView imgRest1;ImageView imgRest2;ImageView imgRest3;ImageView imgRest4;ImageView imgRest5;ImageView imgRest6;
    TextView nazvRest1,nazvRest2,nazvRest3,nazvRest4,nazvRest5,nazvRest6, priceRest1,priceRest2,priceRest3,priceRest4,priceRest5,priceRest6;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.restarauntw);

        Bundle extras1 = getIntent().getExtras();
        addres = extras1.getString("keyAddress");

        // Найдите ImageView после установки макета
        ImageView[] images = new ImageView[]{
        imgRest1 = findViewById(R.id.rest1),
        imgRest2 = findViewById(R.id.rest2),
        imgRest3 = findViewById(R.id.rest3),
        imgRest4 = findViewById(R.id.rest4),
        imgRest5 = findViewById(R.id.rest5),
        imgRest6 = findViewById(R.id.rest6)
        };

        TextView[] name_prod = new TextView[]{
        nazvRest1 = findViewById(R.id.nazvrest1),
        nazvRest2 = findViewById(R.id.nazvrest2),
        nazvRest3 = findViewById(R.id.nazvrest3),
        nazvRest4 = findViewById(R.id.nazvrest4),
        nazvRest5 = findViewById(R.id.nazvrest5),
        nazvRest6 = findViewById(R.id.nazvrest6)
    };
        TextView[] price_prod = new TextView[]{
                priceRest1 = findViewById(R.id.pricerest1),
                priceRest2 = findViewById(R.id.pricerest2),
                priceRest3 = findViewById(R.id.pricerest3),
                priceRest4 = findViewById(R.id.pricerest4),
                priceRest5 = findViewById(R.id.pricerest5),
                priceRest6 = findViewById(R.id.pricerest6)
        };

        for (int i = 13; i < 19; i++) {
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
                                    loadImageFromUrl(image, images[num-13]);

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
                                        name_prod[num-13].setText(name);
                                        price_prod[num-13].setText(price + " ₽");
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
                Intent intent = new Intent(restaraunt.this, Main.class);
                intent.setFlags(FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });

        buttonBin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Обработчик для кнопки "корзина"
                Intent intent = new Intent(restaraunt.this, Bin.class);
                intent.putExtra("keyAddress", addres);
                startActivity(intent);
            }
        });

        buttonAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Обработчик для кнопки "аккаунт"
                Intent intent = new Intent(restaraunt.this, Account.class);
                startActivity(intent);
            }
        });
    }
        public void onClickPlusOrMinus(View v) {
            // Получаем соответствующий TextView
            TextView scetchikTextView = null;

            if (v.getId() == R.id.buttonPlusrest1 || v.getId() == R.id.buttonminusrest1) {
                scetchikTextView = findViewById(R.id.scetchik1);
            } else if (v.getId() == R.id.buttonPlusrest2 || v.getId() == R.id.buttonminusrest2) {
                scetchikTextView = findViewById(R.id.scetchik2);
            } else if (v.getId() == R.id.buttonPlusrest3 || v.getId() == R.id.buttonminusrest3) {
                scetchikTextView = findViewById(R.id.scetchik3);
            } else if (v.getId() == R.id.buttonPlusrest4 || v.getId() == R.id.buttonminusrest4) {
                scetchikTextView = findViewById(R.id.scetchik4);
            } else if (v.getId() == R.id.buttonPlusrest5 || v.getId() == R.id.buttonminusrest5) {
                scetchikTextView = findViewById(R.id.scetchik5);
            } else if (v.getId() == R.id.buttonPlusrest6 || v.getId() == R.id.buttonminusrest6) {
                scetchikTextView = findViewById(R.id.scetchik6);
            }

            if (scetchikTextView != null) {
                // Получаем текущее значение
                int currentValue = Integer.parseInt(scetchikTextView.getText().toString());

                // Обрабатываем "+" или "-"
                if (v.getId() == R.id.buttonPlusrest1 || v.getId() == R.id.buttonPlusrest2 || v.getId() == R.id.buttonPlusrest3 || v.getId() == R.id.buttonPlusrest4 || v.getId() == R.id.buttonPlusrest5 || v.getId() == R.id.buttonPlusrest6) {
                    // Увеличиваем значение, если не превышает 10
                    if (currentValue < 10) {
                        currentValue++;
                    }
                } else if (v.getId() == R.id.buttonminusrest1 || v.getId() == R.id.buttonminusrest2 || v.getId() == R.id.buttonminusrest3 || v.getId() == R.id.buttonminusrest4 || v.getId() == R.id.buttonminusrest5 || v.getId() == R.id.buttonminusrest6) {
                    // Уменьшаем значение, если не отрицательное
                    if (currentValue > 0) {
                        currentValue--;
                    }
                }

                // Устанавливаем новое значение в TextView
                scetchikTextView.setText(String.valueOf(currentValue));
            }
        }}




