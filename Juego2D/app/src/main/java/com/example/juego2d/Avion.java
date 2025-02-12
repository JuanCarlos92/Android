package com.example.juego2d;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import static com.example.juego2d.GameView.screenRatioX;
import static com.example.juego2d.GameView.screenRatioY;

public class Avion {
    int toShoot = 0;
    boolean isGoingUp = false;
    int x, y, width, height, wingCounter = 0, shootCounter = 1;
    Bitmap avion1, avion2, disparo1, disparo2, disparo3, disparo4, disparo5, muerte;
    private GameView gameView;

    // Constructor de la clase
    Avion(GameView gameView, int screenY, Resources res) {
        this.gameView = gameView;

        // Carga las imagenes del avion
        avion1 = BitmapFactory.decodeResource(res, R.drawable.fly1);
        avion2 = BitmapFactory.decodeResource(res, R.drawable.fly2);

        // Obtiene las dimensiones de las imagenes
        width = avion1.getWidth();
        height = avion1.getHeight();

        // Reduce el tamaño de las imagenes
        width /= 4;
        height /= 4;

        // Ajusta las dimensiones segun la pantalla
        width = (int) (width * screenRatioX);
        height = (int) (height * screenRatioY);

        // Redimensiona las imagenes del avion normal
        avion1 = Bitmap.createScaledBitmap(avion1, width, height, false);
        avion2 = Bitmap.createScaledBitmap(avion2, width, height, false);

        // Carga y redimensiona las imagenes de disparo
        disparo1 = BitmapFactory.decodeResource(res, R.drawable.shoot1);
        disparo2 = BitmapFactory.decodeResource(res, R.drawable.shoot2);
        disparo3 = BitmapFactory.decodeResource(res, R.drawable.shoot3);
        disparo4 = BitmapFactory.decodeResource(res, R.drawable.shoot4);
        disparo5 = BitmapFactory.decodeResource(res, R.drawable.shoot5);

        disparo1 = Bitmap.createScaledBitmap(disparo1, width, height, false);
        disparo2 = Bitmap.createScaledBitmap(disparo2, width, height, false);
        disparo3 = Bitmap.createScaledBitmap(disparo3, width, height, false);
        disparo4 = Bitmap.createScaledBitmap(disparo4, width, height, false);
        disparo5 = Bitmap.createScaledBitmap(disparo5, width, height, false);

        // Carga y redimensiona la imagen del avion roto
        muerte = BitmapFactory.decodeResource(res, R.drawable.dead);
        muerte = Bitmap.createScaledBitmap(muerte, width, height, false);

        // Posiciona el personaje en el centro vertical de la pantalla
        y = screenY / 2;
        x = (int) (64 * screenRatioX);

    }

    // Metodo que devuelve la imagen del personaje dependiendo de su estado
    Bitmap getAvion() {
        if (toShoot != 0) {

            // Si el personaje esta disparando....
            if (shootCounter == 1) {
                shootCounter++;
                return disparo1;
            }
            if (shootCounter == 2) {
                shootCounter++;
                return disparo2;
            }
            if (shootCounter == 3) {
                shootCounter++;
                return disparo3;
            }
            if (shootCounter == 4) {
                shootCounter++;
                return disparo4;
            }

            // Reinicia la animacion de disparo y genera una nueva bala
            shootCounter = 1;
            toShoot--;
            gameView.nuevaBala();

            return disparo5;
        }
        // Alterna entre las dos imágenes del vuelo normal
        if (wingCounter == 0) {
            wingCounter++;
            return avion1;
        }
        wingCounter--;
        return avion2;
    }

    // Metodo que devuelve la forma de colision del avion
    Rect getColision() {
        int padding = 10;  // Reduce la zona de colisión
        return new Rect(x + padding, y + padding, x + width - padding, y + height - padding);
    }

    // Metodo que devuelve la imagen del avion cuando pierde
    Bitmap getMuerte() {
        return muerte;
    }

}
