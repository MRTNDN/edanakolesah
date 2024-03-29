// shopmenu.java
package com.westernyey.edanakolesah.main;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.westernyey.edanakolesah.R;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class shopmenu extends AppCompatActivity {
    TextView scetchikTextView1, scetchikTextView2, scetchikTextView3, scetchikTextView4,scetchikTextView5,scetchikTextView6;
    String addres;
    String numberOrder;
    private String kol="";
    private String id_prod = "";
    ImageView shop1;
    ImageView shop2;
    ImageView shop3;
    ImageView shop4;
    ImageView shop5;
    ImageView shop6;
    TextView nazvShop1, nazvShop2, nazvShop3, nazvShop4, nazvShop5, nazvShop6, priceShop1, priceShop2, priceShop3, priceShop4, priceShop5, priceShop6;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onStart() {
        super.onStart();
        setContentView(R.layout.shopmenuw);

        kol="";
        id_prod="";

        scetchikTextView1 = findViewById(R.id.scetchikshop1);
        scetchikTextView1.setText("0");
        scetchikTextView2 = findViewById(R.id.scetchikshop2);
        scetchikTextView2.setText("0");
        scetchikTextView3 = findViewById(R.id.scetchikshop3);
        scetchikTextView3.setText("0");
        scetchikTextView4 = findViewById(R.id.scetchikshop4);
        scetchikTextView4.setText("0");
        scetchikTextView5 = findViewById(R.id.scetchikshop5);
        scetchikTextView5.setText("0");
        scetchikTextView6 = findViewById(R.id.scetchikshop6);
        scetchikTextView6.setText("0");

        Bundle extras1 = getIntent().getExtras();
        addres = extras1.getString("keyAddress");

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
        Button buttonMain = findViewById(R.id.buttonmain);
        Button buttonBin = findViewById(R.id.buttonbin);
        Button buttonAccount = findViewById(R.id.buttonaccount);

        buttonMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Обработчик для кнопки "главная"
                Intent intent = new Intent(shopmenu.this, Main.class);
                intent.putExtra("keyAddress", addres);
                startActivity(intent);
            }
        });

        buttonBin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addToCart();
                // Обработчик для кнопки "корзина"
                Intent intent = new Intent(shopmenu.this, Bin.class);
                intent.putExtra("keyAddress", addres);
                intent.putExtra("number", numberOrder);
                startActivity(intent);
            }
        });

        buttonAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Обработчик для кнопки "аккаунт"
                Intent intent = new Intent(shopmenu.this, Account.class); //
                intent.putExtra("keyAddress", addres);
                intent.putExtra("number", numberOrder);
                startActivity(intent);
            }
        });
    }
        public void onClickPlusOrMinusShop(View v) {
            // Получаем соответствующий TextView
            TextView scetchikTextView = null;

            if (v.getId() == R.id.buttonPlusshop1 || v.getId() == R.id.buttonminusshop1) {
                scetchikTextView = findViewById(R.id.scetchikshop1);
            } else if (v.getId() == R.id.buttonPlusshop2 || v.getId() == R.id.buttonminusshop2) {
                scetchikTextView = findViewById(R.id.scetchikshop2);
            } else if (v.getId() == R.id.buttonPlusshop3 || v.getId() == R.id.buttonminusshop3) {
                scetchikTextView = findViewById(R.id.scetchikshop3);
            } else if (v.getId() == R.id.buttonPlusshop4 || v.getId() == R.id.buttonminusshop4) {
                scetchikTextView = findViewById(R.id.scetchikshop4);
            } else if (v.getId() == R.id.buttonPlusshop5 || v.getId() == R.id.buttonminusshop5) {
                scetchikTextView = findViewById(R.id.scetchikshop5);
            } else if (v.getId() == R.id.buttonPlusshop6|| v.getId() == R.id.buttonminusshop6) {
                scetchikTextView = findViewById(R.id.scetchikshop6);
            }

            if (scetchikTextView != null) {
                // Получаем текущее значение
                int currentValue = Integer.parseInt(scetchikTextView.getText().toString());

                // Обрабатываем "+" или "-"
                if (v.getId() == R.id.buttonPlusshop1  || v.getId() == R.id.buttonPlusshop2  || v.getId() == R.id.buttonPlusshop3 || v.getId() == R.id.buttonPlusshop4 || v.getId() == R.id.buttonPlusshop5 || v.getId() == R.id.buttonPlusshop6) {
                    // Увеличиваем значение, если не превышает 10
                    if (currentValue < 10) {
                        currentValue++;
                    }
                } else if (v.getId() == R.id.buttonminusshop1 || v.getId() == R.id.buttonminusshop2 || v.getId() == R.id.buttonminusshop3 || v.getId() == R.id.buttonminusshop4 || v.getId() == R.id.buttonminusshop5 || v.getId() == R.id.buttonminusshop6) {
                    // Уменьшаем значение, если не отрицательное
                    if (currentValue > 0) {
                        currentValue--;
                    }
                }

                // Устанавливаем новое значение в TextView
                scetchikTextView.setText(String.valueOf(currentValue));
            }
        }

    public void addToCart(){


        TextView[] scet = new TextView[]{
                scetchikTextView1 = findViewById(R.id.scetchikshop1),
                scetchikTextView2 = findViewById(R.id.scetchikshop2),
                scetchikTextView3 = findViewById(R.id.scetchikshop3),
                scetchikTextView4 = findViewById(R.id.scetchikshop4),
                scetchikTextView5 = findViewById(R.id.scetchikshop5),
                scetchikTextView6 = findViewById(R.id.scetchikshop6)
        };

        for (TextView textView : scet) {
            String text = textView.getText().toString(); // получаем текст из TextView
            if (!text.equals("0")) { // проверяем, равен ли текст "0"
                if (R.id.scetchikshop1 == textView.getId()){
                    id_prod += "7 ";
                    kol += text + " ";
                } else if (R.id.scetchikshop2 == textView.getId()) {
                    id_prod += "8 ";
                    kol += text + " ";
                } else if (R.id.scetchikshop3 == textView.getId()) {
                    id_prod += "9 ";
                    kol += text + " ";
                } else if (R.id.scetchikshop4 == textView.getId()) {
                    id_prod += "10 ";
                    kol += text + " ";
                } else if (R.id.scetchikshop5 == textView.getId()) {
                    id_prod += "11 ";
                    kol += text + " ";
                }else{
                    id_prod += "12 ";
                    kol += text + " ";
                }

            }

        }
        addToDB();
    }

    public void addToDB(){


        Random rand = new Random();
        numberOrder  = String.valueOf(rand.nextInt(10000 - 100 + 1) + 100);
        Map<String, Object> cart = new HashMap<>();
        cart.put("address", addres);
        cart.put("id_payment_method", "0");
        cart.put("id_product", id_prod);
        cart.put("order_number", numberOrder);
        cart.put("product_quantity", kol);

        db.collection("shopping_cart")
                .add(cart)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(shopmenu.this, "Товары добавлены в корзину!", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(shopmenu.this, "Ошибка сервера!", Toast.LENGTH_SHORT).show();
                    }
                });
    }
    }