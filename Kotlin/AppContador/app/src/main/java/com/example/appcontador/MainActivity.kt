package com.example.appcontador

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var contador: Int = 0

        //VINCULANDO EL CONTENIDO DE LA VISTA CON MI XML
        setContentView(R.layout.activity_main)

        //REFERENCIAR LOS ELEMENTOS DE LA INTERFAZ
        val textView = findViewById<TextView>(R.id.textView)
        val buttonIncrement = findViewById<Button>(R.id.buttonIncrement)
        val buttonDecrement = findViewById<Button>(R.id.buttonDecrement)

        // Función para actualizar el TextView y cambiar el color según el valor del contador
        fun updateCounterDisplay(textView: TextView) {
            textView.text = contador.toString()

            when {
                contador >= 10 -> {
                    textView.setTextColor(Color.GREEN)
                }

                contador <= -10 -> {
                    textView.setTextColor(Color.RED)
                }

                else -> {
                    textView.setTextColor(Color.BLACK)
                }
            }
        }
        // Incrementar y decrementar al pulsar el boton
        buttonIncrement.setOnClickListener {
            contador ++
            updateCounterDisplay(textView)

        }
        buttonDecrement.setOnClickListener {
            contador--
            updateCounterDisplay(textView)
        }

    }
}



