package com.example.canvaslienzo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.MotionEvent;
import android.view.View;

public class myView extends View {
    private Path path = new Path();
    private Paint paint = new Paint();

    // Constructor
    public myView(Context context) {
        super(context);
        setFocusable(true);
        setFocusableInTouchMode(true);
        setContentDescription("Lienzo para dibujar"); // Descripción accesible
        PaintSettings();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(path, paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float pointX = event.getX();
        float pointY = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                path.moveTo(pointX, pointY);
                break;
            case MotionEvent.ACTION_MOVE:
                path.lineTo(pointX, pointY);
                break;
            default:
                return false;
        }

        invalidate();
        return true;
    }

    private void PaintSettings() {
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(20);
    }

    public void clearCanvas() {
        path.reset();
        invalidate();
    }
}
