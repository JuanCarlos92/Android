package com.example.juego2d;

import static com.example.juego2d.GameView.screenRatioX;
import static com.example.juego2d.GameView.screenRatioY;

import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.graphics.Bitmap;

public class Pajaro {
    public int speed = 20;
    public boolean wasShot = true;
    int x = 0, y, width, height, birdCounter = 1;
    Bitmap pajaro1, pajaro2, pajaro3, pajaro4;

    // Constructor de la clase
    Pajaro(Resources res) {
        // Carga las imágenes del pajaro
        pajaro1 = BitmapFactory.decodeResource(res, R.drawable.bird1);
        pajaro2 = BitmapFactory.decodeResource(res, R.drawable.bird2);
        pajaro3 = BitmapFactory.decodeResource(res, R.drawable.bird3);
        pajaro4 = BitmapFactory.decodeResource(res, R.drawable.bird4);

        // Obtiene el ancho y alto de la imagen
        width = pajaro1.getWidth();
        height = pajaro1.getHeight();

        // Reduce el tamaño de la imagen
        width /= 15;
        height /= 15;

        // Ajusta segun la pantalla
        width = (int) (width * screenRatioX);
        height = (int) (height * screenRatioY);

        // Escala las imágenes a los nuevos valores calculados
        pajaro1 = Bitmap.createScaledBitmap(pajaro1, width, height, false);
        pajaro2 = Bitmap.createScaledBitmap(pajaro2, width, height, false);
        pajaro3 = Bitmap.createScaledBitmap(pajaro3, width, height, false);
        pajaro4 = Bitmap.createScaledBitmap(pajaro4, width, height, false);

        // Inicializa la posición Y del pájaro fuera de la pantalla (arriba)
        y = -height;
    }

    // Metodo que devuelve la imagen del pajaro correspondiente a la animacion
    Bitmap getPajaro () {

        // Controla el ciclo de animacion cambiando el frame en cada llamada
        if (birdCounter == 1) {
            birdCounter++;
            return pajaro1;
        }
        if (birdCounter == 2) {
            birdCounter++;
            return pajaro2;
        }
        if (birdCounter == 3) {
            birdCounter++;
            return pajaro3;
        }

        // Reinicia el contador y devuelve el último frame
        birdCounter = 1;
        return pajaro4;
    }

    // Metodo que devuelve la forma de colisión del pajaro
    Rect getColision() {
        int padding = 10;  // Reduce la zona de colisión
        return new Rect(x + padding, y + padding, x + width - padding, y + height - padding);
    }
}
