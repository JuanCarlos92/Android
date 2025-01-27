package com.example.canvaslienzo;

import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
import android.widget.FrameLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        // Crear un FrameLayout para contener la vista personalizada y el boton
        FrameLayout frameLayout = new FrameLayout(this);
        myView canvasView = new myView(this);
        frameLayout.addView(canvasView);


        Button btnClear = new Button(this);
        btnClear.setText("Reset");
        FrameLayout.LayoutParams clearParams = new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.WRAP_CONTENT,
                FrameLayout.LayoutParams.WRAP_CONTENT
        );

        clearParams.gravity = Gravity.TOP | Gravity.END;
        clearParams.setMargins(0, 60, 40, 0);
        btnClear.setLayoutParams(clearParams);

        btnClear.setOnClickListener(v -> canvasView.clearCanvas());
        frameLayout.addView(btnClear);

        // Establecer el FrameLayout como contenido de la actividad
        setContentView(frameLayout);
    }
}
