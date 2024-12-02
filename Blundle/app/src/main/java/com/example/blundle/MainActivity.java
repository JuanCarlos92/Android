package com.example.blundle;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Referencias a los elementos del diseño
        EditText editTextNumber1 = findViewById(R.id.editTextNumber1);
        EditText editTextNumber2 = findViewById(R.id.editTextNumber2);
        Button buttonSumar = findViewById(R.id.buttonSumar);

        // Configurar el evento del botón
        buttonSumar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Leer los valores de los campos de texto
                String num1String = editTextNumber1.getText().toString();
                String num2String = editTextNumber2.getText().toString();

                // Convertir a enteros y calcular la suma
                int num1 = Integer.parseInt(num1String);
                int num2 = Integer.parseInt(num2String);
                int suma = num1 + num2;

                // Crear un Intent para iniciar la SecondActivity
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);

                // Pasar la suma usando un Bundle
                Bundle bundle = new Bundle();
                bundle.putInt("resultado", suma);
                intent.putExtras(bundle);

                // Iniciar la actividad
                startActivity(intent);
            }
        });
    }
}