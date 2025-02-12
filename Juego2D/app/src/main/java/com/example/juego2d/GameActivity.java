package com.example.juego2d;

import android.graphics.Point;
import android.os.Bundle;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

public class GameActivity extends AppCompatActivity {

    private GameView gameView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Point point = new Point();
        getWindowManager().getDefaultDisplay().getSize(point);

        // Crea una instancia de GameView con el tamaño de la pantalla
        gameView = new GameView(this, point.x, point.y);
        setContentView(gameView);
    }

    // Metodo cuando se pausa el juego (por ejemplo, al minimizar la app)
    @Override
    protected void onPause() {
        super.onPause();
        gameView.pause();
    }

    // Metodo cuando se reanuda el juego (por ejemplo, al volver a la app)
    @Override
    protected void onResume() {
        super.onResume();
        gameView.resume();
    }
}


