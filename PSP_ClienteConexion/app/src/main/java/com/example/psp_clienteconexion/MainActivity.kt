package com.example.psp_clienteconexion

import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import java.io.IOException
import java.net.Socket

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textArea: EditText = findViewById(R.id.textArea)
        val host = "10.0.2.2" //Al estar emulado la IP 10.0.2.2 corresponde a 127.0.0.1
        val puerto = 12345

        // Ejecutar la conexión en un hilo separado
        Thread {
            try {
                val cliente = Socket(host, puerto)

                // Mostrar información de conexión
                runOnUiThread {

                    // Mostrar información en el textArea
                    textArea.append("Conectado al servidor:\n")
                    textArea.append("Puerto local (cliente): ${cliente.localPort}\n")
                    textArea.append("Puerto remoto (servidor): ${cliente.port}\n")
                    textArea.append("Dirección IP del servidor: ${cliente.inetAddress.hostAddress}\n")
                }

                // Mantén la conexión abierta o realiza otras operaciones si es necesario
                // Cliente se cierra después de la operación
                cliente.close()

                // Desconectar después de la operación
                runOnUiThread {
                    textArea.append("Cliente desconectado.\n")
                }

            } catch (e: IOException) {
                e.printStackTrace()
                // Manejo de errores en el hilo principal
                runOnUiThread {
                    textArea.append("Error de conexión: ${e.message}\n")
                }
            }
        }.start() // Inicia el hilo
    }
}
