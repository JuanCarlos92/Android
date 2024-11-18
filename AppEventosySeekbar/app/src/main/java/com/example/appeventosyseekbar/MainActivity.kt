package com.example.appeventosyseekbar

import android.os.Bundle
import android.widget.SeekBar
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var contador: Int = 0

        setContentView(R.layout.activity_main)

        //REFERENCIAR LOS ELEMENTOS DE LA INTERFAZ
        var textView = findViewById<TextView>(R.id.textView)
        var buttonIncrement = findViewById<TextView>(R.id.buttonIncrement)
        var buttonDecrement = findViewById<TextView>(R.id.buttonDecrement)
        var seekBar: SeekBar = findViewById(R.id.seekBar)

        //inicializar el valor de textView
        textView.text = contador.toString()

        //Incrementar
        buttonIncrement.setOnClickListener{
            contador++
            textView.text = contador.toString()
            seekBar.progress = contador //actualizamos el seekBar
        }
        //Decrementar
        buttonDecrement.setOnClickListener {
            if (contador > 0) { //no baja de 0 el contador
                contador--
                textView.text = contador.toString()
                seekBar.progress = contador //actualizamos el seekBar
            }
        }
        //Seekbar
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progreso: Int, fromUser: Boolean){
                contador = progreso
                textView.text = contador.toString()
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
                TODO("Not yet implemented")
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
                TODO("Not yet implemented")
            }
        })



        }
    }
