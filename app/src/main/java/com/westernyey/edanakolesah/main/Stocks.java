// shopmenu.java
package com.westernyey.edanakolesah.main;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ImageView;
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
    ImageView imgStock1;ImageView imgStock2;ImageView imgStock3;ImageView imgStock4;ImageView imgStock5;ImageView imgStock6;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    TextView nazvStock1,nazvStock2,nazvStock3,nazvStock4,nazvStock5,nazvStock6, priceStock1,priceStock2,priceStock3,priceStock4,priceStock5,priceStock6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stocks);

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
                                    loadImageFromUrl(image, images[num-1]);

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
                                    name_prod[num-1].setText(name);
                                    price_prod[num-1].setText(price + " ₽");
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
    }
}
