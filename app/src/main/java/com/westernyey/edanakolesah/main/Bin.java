package com.westernyey.edanakolesah.main;

import static android.content.Intent.FLAG_ACTIVITY_SINGLE_TOP;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.westernyey.edanakolesah.R;

import java.util.Arrays;
import java.util.List;

public class Bin extends AppCompatActivity {
    String addres;
    String numberOfOrder;
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
        Button buttonMain = findViewById(R.id.buttonAccount);
        Button buttonBin = findViewById(R.id.buttonBin);
        Button buttonAccount = findViewById(R.id.buttonMain);
        Bundle extras1 = getIntent().getExtras();
        addres = extras1.getString("keyAddress");
        numberOfOrder = extras1.getString("number");
        CollectionReference colRef = db.collection("shopping_cart");
        colRef
                .whereEqualTo("address", addres)
                .whereEqualTo("order_number", numberOfOrder)
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

        buttonMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Обработчик для кнопки "главная"
                Intent intent = new Intent(Bin.this, Main.class);
                intent.setFlags(FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });

        buttonBin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Обработчик для кнопки "корзина"
                Intent intent = new Intent(Bin.this, Bin.class);
                intent.putExtra("keyAddress", addres);
                startActivity(intent);
            }
        });

        buttonAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Обработчик для кнопки "аккаунт"
                Intent intent = new Intent(Bin.this, Account.class);
                startActivity(intent);
            }
        });
    }

    public void deleteDoc(View v){
        FirebaseFirestore rootRef = FirebaseFirestore.getInstance();
        CollectionReference itemsRef = rootRef.collection("shopping_cart");
        Query query = itemsRef.whereEqualTo("address", addres);
        query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (DocumentSnapshot document : task.getResult()) {
                        itemsRef.document(document.getId()).delete();
                    }
                } else {
                    Log.d("Myapp", "Error getting documents: ", task.getException());
                }
            }
        });
        Intent intent = new Intent(Bin.this, Main.class);
        startActivity(intent);
    }

}

