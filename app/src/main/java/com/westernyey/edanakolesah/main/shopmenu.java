// shopmenu.java
package com.westernyey.edanakolesah.main;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.westernyey.edanakolesah.R;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class shopmenu extends AppCompatActivity {

    ImageView shop1;
    ImageView shop2;
    ImageView shop3;
    ImageView shop4;
    ImageView shop5;
    ImageView shop6;
    TextView nazvShop1, nazvShop2, nazvShop3, nazvShop4, nazvShop5, nazvShop6, priceShop1, priceShop2, priceShop3, priceShop4, priceShop5, priceShop6;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shopmenuw);

        //Создание массивов
        ImageView[] images = new ImageView[]{
                shop1 = findViewById(R.id.shop1),
                shop2 = findViewById(R.id.shop2),
                shop3 = findViewById(R.id.shop3),
                shop4 = findViewById(R.id.shop4),
                shop5 = findViewById(R.id.shop5),
                shop6 = findViewById(R.id.shop6)
        };

        TextView[] name_prod = new TextView[]{
                nazvShop1 = findViewById(R.id.nazvshop1),
                nazvShop2 = findViewById(R.id.nazvshop2),
                nazvShop3 = findViewById(R.id.nazvshop3),
                nazvShop4 = findViewById(R.id.nazvshop4),
                nazvShop5 = findViewById(R.id.nazvshop5),
                nazvShop6 = findViewById(R.id.nazvshop6)
        };
        TextView[] price_prod = new TextView[]{
                priceShop1 = findViewById(R.id.priceshop1),
                priceShop2 = findViewById(R.id.priceshop2),
                priceShop3 = findViewById(R.id.priceshop3),
                priceShop4 = findViewById(R.id.priceshop4),
                priceShop5 = findViewById(R.id.priceshop5),
                priceShop6 = findViewById(R.id.priceshop6)
        };

        //Код для бд
        for (int i = 7; i < 13; i++) {
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
                                    loadImageFromUrl(image, images[num - 7]);

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
                                    name_prod[num - 7].setText(name);
                                    price_prod[num - 7].setText(price + " ₽");
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
