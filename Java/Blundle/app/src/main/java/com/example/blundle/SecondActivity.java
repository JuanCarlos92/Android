package com.example.blundle;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        // Referencia al TextView
        TextView textViewResultado = findViewById(R.id.textViewResultado);

        // Obtener el Intent y el Bundle
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            int suma = bundle.getInt("resultado"); // Recuperar la suma
            textViewResultado.setText("Resultado de la suma: " + suma);
        }
    }
}