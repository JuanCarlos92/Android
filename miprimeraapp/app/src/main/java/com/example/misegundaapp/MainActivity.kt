package com.example.misegundaapp

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        //VINCULANDO EL CONTENIDO DE LA VISTA CON MI XML
        setContentView(R.layout.activity_main)

        //REFERENCIAR LOS ELEMENTOS DE LA INTERFAZ
        val textView = findViewById<TextView>(R.id.textView)
        val buttonCambioText = findViewById<Button>(R.id.buttonCambioText)
        val buttonResetText = findViewById<Button>(R.id.buttonResetText)
        val buttonCambiarColor = findViewById<Button>(R.id.buttonCambiarColor)

        //ACCION PARA CAMBIAR TEXTO
        buttonCambioText.setOnClickListener {
        textView.text = "Texto Cambiado"
        }

        //ACCION PARA RESTABLECERLO
        buttonResetText.setOnClickListener {
            textView.text ="Restauración Hola Mundo"
        }

        //ACCION PARA CAMBIAR COLOR DEL TEXTO
        buttonCambiarColor.setOnClickListener {
            textView.setTextColor(randomColor())
        }


    }

    private fun randomColor(): Int {
        val colores = listOf(
                    android.R.color.holo_red_light,
                    android.R.color.holo_blue_light,
                    android.R.color.holo_green_light,
                    android.R.color.holo_orange_light,
                    android.R.color.holo_purple,
        )
        val ramdomIndex = Random.nextInt(colores.size)

        return ContextCompat.getColor(this,colores[ramdomIndex])

    }
}