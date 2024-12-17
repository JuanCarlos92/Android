package com.example.chatcliente

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import java.net.Socket
import androidx.appcompat.app.AppCompatActivity
import kotlin.concurrent.thread

class ConfigServer : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_config)

        val entradaIp: EditText = findViewById(R.id.entradaIp)
        val entradaPuerto: EditText = findViewById(R.id.entradaPuerto)
        val entradaNombre: EditText = findViewById(R.id.entradaNombre)
        val botonConectar: Button = findViewById(R.id.botonConectar)


        botonConectar.setOnClickListener {
            val ipText = entradaIp.text.toString()
            val puertoText = entradaPuerto.text.toString()
            val nombreText = entradaNombre.text.toString()

            if (ipText.isNotEmpty() && puertoText.isNotEmpty() && nombreText.isNotEmpty()) {
                try {
                    val puerto = puertoText.toInt()

                    if (puerto in 1..65535) {
                        // Usa el nuevo método en un hilo secundario
                        conectarEnHilo(ipText, puerto)
                    } else {
                        Toast.makeText(this, "Ingrese un puerto válido (1-65535)", Toast.LENGTH_SHORT).show()
                    }
                } catch (e: NumberFormatException) {
                    Toast.makeText(this, "Ingrese un número válido", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "El campo de puerto no puede estar vacío", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun conectarEnHilo(host: String, puerto: Int) {
        // Crea un nuevo hilo para la conexión
        thread {
            try {
                val socket = Socket(host, puerto)
                socket.close() // Cierra la conexión
                runOnUiThread {
                    // Conexión exitosa
                    Toast.makeText(applicationContext, "Conexión exitosa", Toast.LENGTH_SHORT).show()
                    // Lanza la siguiente actividad si la conexión es exitosa
                    val intent = Intent(this@ConfigServer, MainActivity::class.java)
                    intent.putExtra("ip", host)
                    intent.putExtra("puerto", puerto)
                    startActivity(intent)
                    finish()
                }
            } catch (e: Exception) {
                runOnUiThread {
                    // Si ocurre un error, muestra un mensaje
                    Toast.makeText(applicationContext, "Error de conexión: ${e.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    // Metodo para probar si el puerto está disponible
    private fun probarConexion(host: String, puerto: Int): Boolean {
        return try {
            // Intentar conectar al servidor en el puerto y el host
            val socket = Socket(host, puerto)
            socket.close() // Cierra inmediatamente si logra conectar
            true
        } catch (e: Exception) {
            // Imprime el error completo en Logcat
            e.printStackTrace()

            // Mostrar el tipo y el mensaje de la excepción en un Toast para el usuario
            val errorMessage = "Error de conexión: ${e.javaClass.simpleName} - ${e.localizedMessage}"
            runOnUiThread {
                Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
            }
            false // Si falla, el puerto no está disponibles
        }
    }
}