package com.example.chatcliente

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import java.net.Socket
import androidx.appcompat.app.AppCompatActivity

class FirstWindows : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_port)

        val entradaPuerto: EditText = findViewById(R.id.entradaPuerto)
        val botonConectar: Button = findViewById(R.id.botonConectar)

        botonConectar.setOnClickListener {
            val puertoTexto = entradaPuerto.text.toString()
            if (puertoTexto.isNotEmpty()) {
                try {
                    val puerto = puertoTexto.toInt()
                    if (puerto in 1..65535) {
                        // Verifica si el puerto está disponible
                        if (probarConexion(puerto)) {
                            // Lanza la MainActivity con el puerto válido
                            val intent = Intent(this, MainActivity::class.java)
                            intent.putExtra("puerto", puerto)
                            startActivity(intent)
                            finish()
                        } else {
                            Toast.makeText(
                                this,
                                "El puerto $puerto está ocupado. Intente con otro.",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    } else {
                        Toast.makeText(
                            this,
                            "Ingrese un puerto válido (1-65535)",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                } catch (e: NumberFormatException) {
                    Toast.makeText(this, "Ingrese un número válido", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "El campo de puerto no puede estar vacío", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.new_game -> {
                newGame() // Llamada al método newGame
                true
            }
            R.id.help -> {
                showHelp() // Llamada al método showHelp
                true
            }
            else -> super.onOptionsItemSelected(item) // Delegar a la clase padre si no es un ítem manejado
        }
    }


    // Metodo para probar si el puerto está disponible
    private fun probarConexion(puerto: Int): Boolean {
        return try {
            // Intentar conectar al servidor en el puerto
            val socket = Socket("10.0.2.2", puerto)
            socket.close() // Cierra inmediatamente si logra conectar
            true
        } catch (e: Exception) {
            false // Si falla, el puerto no está disponible
        }
    }
}