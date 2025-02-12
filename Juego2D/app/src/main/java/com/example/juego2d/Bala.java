package com.example.juego2d;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import static com.example.juego2d.GameView.screenRatioX;
import static com.example.juego2d.GameView.screenRatioY;

public class Bala {

    int x, y, width, height;
    Bitmap bala;

    // Constructor de la clase
    Bala(Resources res) {
        // Carga la imagen del recurso
        bala = BitmapFactory.decodeResource(res, R.drawable.bullet);

        // Obtiene las dimensiones de la imagen
        width = bala.getWidth();
        height = bala.getHeight();

        // Redimensiona la imagen para reducir el tamaño
        width /= 5;
        height /= 5;

        // Ajusta las dimensiones de la bala la pantalla
        width = (int) (width * screenRatioX);
        height = (int) (height * screenRatioY);

        // Crea una nueva imagen con las dimensiones ajustadas
        bala = Bitmap.createScaledBitmap(bala, width, height, false);

    }
    // Metodo que devuelve la forma de colision de la baja
    Rect getColision() {
        return new Rect(x, y, x + width, y + height);
    }

}
