package com.example.psp_u3_tareachatcliente

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import java.net.Socket
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    private lateinit var socket: Socket // variable de tipo Socket para la conexión con el servidor
    private lateinit var escribir: PrintWriter // variable para enviar mensajes al servidor
    private lateinit var leer: BufferedReader // variable para leer mensajes del servidor
    private lateinit var mensajeEntrada: EditText // Vista para la entrada de texto del mensaje
    private lateinit var botonEnviar: Button // Botón para enviar el mensaje
    private lateinit var vistaChat: TextView // Vista para mostrar el chat en la interfaz
    private val host = "10.0.2.2" //Al estar emulado la IP 10.0.2.2 corresponde a 127.0.0.1
    private val puerto = 12345

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Asocia las vistas con los elementos definidos en el XML
        mensajeEntrada = findViewById(R.id.mensajeEntrada)
        botonEnviar = findViewById(R.id.botonEnviar)
        vistaChat = findViewById(R.id.vistaChat)


        // Conéctate al servidor en un hilo separado para no bloquear la UI principal
        thread {
            connectToServer() // Metodo conectar con el servidor
        }

        // Boton enviar
        botonEnviar.setOnClickListener {
            val message = mensajeEntrada.text.toString() // Obtiene el mensaje ingresado
            if (message.isNotEmpty()) {
                sendMessage(message) // Llama a la función para enviar el mensaje al servidor
                mensajeEntrada.text.clear()
            }
        }
    }

    //--------------------------------------------------------------------------------------------

    // Metodo para conectarse al servidor
    private fun connectToServer() {
        try {
            // Establece la conexión con el servidor
            socket = Socket(host, puerto)
            escribir =
                PrintWriter(socket.getOutputStream(), true) // Para enviar mensajes al servidor
            leer =
                BufferedReader(InputStreamReader(socket.getInputStream())) // Para leer mensajes del servidor

            // Lee los mensajes del servidor en un hilo separado para no bloquear la UI
            thread {
                while (true) {
                    val message = leer.readLine() // Lee un mensaje enviado por el servidor

                    // Muestra el mensaje recibido en la UI
                    runOnUiThread {
                        vistaChat.append("\nRecibido: $message")
                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            runOnUiThread {
                vistaChat.append("\nError al conectar al servidor")
            }
        }
    }

    // Metodo para enviar un mensaje al servidor
    private fun sendMessage(message: String) {
        thread {
            try {
                escribir.println(message) // Envía el mensaje al servidor

                // Muestra el mensaje enviado en la UI
                runOnUiThread {
                    vistaChat.append("\nEnviado: $message")
                }
            } catch (e: Exception) {
                e.printStackTrace()
                runOnUiThread {
                    vistaChat.append("\nError al enviar mensaje")
                }
            }
        }
    }

    // Metodo que se ejecuta cuando se cierra la aplicación
    override fun onDestroy() {
        super.onDestroy()

        socket.close() // Cierra la conexión con el servidor
    }
}

