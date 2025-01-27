package com.example.miprimercanvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Typeface;
import android.view.View;

public class MiPropiaVista extends View {

    public MiPropiaVista(Context context) {
        super(context);
    }

    protected void onDraw(Canvas canvas) {

        Paint miPincel = new Paint();
        /*miPincel.setColor(Color.BLUE);
        miPincel.setStrokeWidth(8);
        miPincel.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(175, 175, 100, miPincel);*/

        Path miTrazo = new Path();
        miTrazo.addCircle(350, 350, 200, Path.Direction.CW);

        miPincel.setStrokeWidth(1);
        miPincel.setColor(Color.BLUE);
        miPincel.setStyle(Paint.Style.FILL);
        miPincel.setTextSize(40);
        miPincel.setTypeface(Typeface.SANS_SERIF);


        canvas.drawTextOnPath("Buenas tardes, Soy Andrés", miTrazo, 0, 0, miPincel);
    }


}
