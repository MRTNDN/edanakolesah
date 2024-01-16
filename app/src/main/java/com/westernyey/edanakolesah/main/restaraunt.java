// shopmenu.java
package com.westernyey.edanakolesah.main;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.squareup.picasso.Picasso;
import com.westernyey.edanakolesah.MainActivity;
import com.westernyey.edanakolesah.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class restaraunt extends AppCompatActivity {
    ImageView imgRest1;ImageView imgRest2;ImageView imgRest3;ImageView imgRest4;ImageView imgRest5;ImageView imgRest6;
    TextView nazvRest1,nazvRest2,nazvRest3,nazvRest4,nazvRest5,nazvRest6, priceRest1,priceRest2,priceRest3,priceRest4,priceRest5,priceRest6;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.restarauntw);

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
                                        if(num == 13){
                                            loadImageFromUrl(image, images[num-13]);
                                        }
                                        else {
                                            loadImageFromUrl(image, images[num-13]);
                                        }
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
                                    if(num == 13){
                                        name_prod[num-13].setText(name);
                                        price_prod[num-13].setText(price + " ₽");
                                    }
                                    else {
                                        name_prod[num-13].setText(name);
                                        price_prod[num-13].setText(price+ " ₽");
                                    }
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




