package com.example.juego2d;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private boolean isMute = false; // Inicializa isMute en false por defecto

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Iniciar el juego
        findViewById(R.id.play).setOnClickListener(view -> {
            Intent intent = new Intent(this, GameActivity.class);
            startActivity(intent);
        });

        // Salir de la aplicación
        findViewById(R.id.exit).setOnClickListener(view -> {
            finishAffinity();
        });

        // Verifica si el sonido está en modo mute
        final ImageView volumeCtrl = findViewById(R.id.volumeCtrl);
        // Establece la imagen inicial según el estado de isMute
        volumeCtrl.setImageResource(R.drawable.ic_volume_up_black_24dp);

        volumeCtrl.setOnClickListener(view -> {
            isMute = !isMute;
            if (isMute)
                volumeCtrl.setImageResource(R.drawable.ic_volume_off_black_24dp);
            else
                volumeCtrl.setImageResource(R.drawable.ic_volume_up_black_24dp);

        });
    }

}


