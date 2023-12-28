package com.westernyey.edanakolesah;

import static android.content.ContentValues.TAG;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class registrActivity extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    EditText log, pass, addres,number_phone, full_name;
    Spinner spinnerCity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registractivity);
        spinnerCity = findViewById(R.id.spinnerCity);
        log = findViewById(R.id.editTextLogin);
        pass = findViewById(R.id.editTextPassword);
        addres = findViewById(R.id.editText4);
        number_phone = findViewById(R.id.editText5);
        full_name = findViewById(R.id.editText6);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.city_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCity.setAdapter(adapter);

        // Другие действия, которые могут быть выполнены при создании активности
    }

    public void ButRegistr(View view){
        String selectedTown = spinnerCity.getSelectedItem().toString();

        String[] values = full_name.getText().toString().split(" ");
        Log.d(TAG, values[0]+" " +values[1]+" " +values[2]);
        String last_name =values[0];
        String name = values[1];
        String midle_name = values[2];
        // Create a new user with a first and last name
        Map<String, Object> client = new HashMap<>();
        client.put("login", log.getText().toString());
        client.put("password", pass.getText().toString());
        client.put("address", addres.getText().toString());
        client.put("id_town", selectedTown);
        client.put("last_name", last_name);
        client.put("midle_name",midle_name);
        client.put("name", name);
        client.put("number_phone",number_phone.getText().toString());

// Add a new document with a generated ID
        db.collection("client")
                .add(client)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        //Нужен тост, который говорит что всё успешно
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        //Нужен тост, который говорит что произошла ошибка сервера
                    }
                });

    }
}
