package com.westernyey.edanakolesah.main;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.westernyey.edanakolesah.R;

import java.util.Arrays;
import java.util.List;

public class Bin extends AppCompatActivity {
    String addres;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    String data = null;
    String[] valuesInDB;
    String[] product = new String[0];
    ArrayAdapter<String> adapter;
    String kolvo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bin);

        Bundle extras1 = getIntent().getExtras();
        addres = extras1.getString("keyAddress");

        CollectionReference colRef = db.collection("shopping_cart");
        colRef
                .whereEqualTo("address", addres)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        List<DocumentSnapshot> documents = task.getResult().getDocuments();
                        for (DocumentSnapshot doc : documents) {
                            String idProduct = doc.getString("id_product"); // Получаем значение поля "id_product"
                            kolvo = doc.getString("product_quantity");
                            data = idProduct;
                        }
                        if (data == null) {
                            String[] nullAdapter = new String[]{"Пока что тут пусто!"};
                            ArrayAdapter<String> adapterNull = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, nullAdapter);
                            ListView listView = findViewById(R.id.listView);
                            listView.setAdapter(adapterNull);
                        } else {
                            valuesInDB = data.split(" ");
                            String[] arrKol = kolvo.split(" ");
                            int ind = -1;
                            for (String value : valuesInDB) {
                                ind++;
                                CollectionReference colRef2 = db.collection("product");
                                int finalInd = ind;
                                colRef2
                                        .whereEqualTo("id_product", value)
                                        .get()
                                        .addOnCompleteListener(task1 -> {
                                            if (task1.isSuccessful()) {
                                                List<DocumentSnapshot> documents1 = task1.getResult().getDocuments();
                                                for (DocumentSnapshot doc : documents1) {
                                                    String nazvProduct = doc.getString("name_product");

                                                    product = Arrays.copyOf(product, product.length + 1);

                                                    product[product.length - 1] = nazvProduct + "                    " + arrKol[finalInd];
                                                }
                                                adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, product);
                                                ListView listView = findViewById(R.id.listView);
                                                listView.setAdapter(adapter);

                                            }
                                        });
                            }
                        }
                    }
                });


    }
}
