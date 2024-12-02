package com.example.trespantallas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;


public class Pantalla1Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla1);

        public void Intent (View v) {
            Intent i = new Intent(this, Pantalla2Activity.class);
            startActivity(i);
        }

    }
}

