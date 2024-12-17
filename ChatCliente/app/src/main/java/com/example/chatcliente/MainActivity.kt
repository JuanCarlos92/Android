package com.example.chatcliente

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

    private lateinit var socket: Socket // variable de tipo Socket [conexión con el servidor]
    private lateinit var writer: PrintWriter // variable para enviar msj
    private lateinit var read: BufferedReader // variable para leer msj
    private lateinit var mensajeEntrada: EditText
    private lateinit var botonEnviar: Button
    private lateinit var vistaChat: TextView
    private var nombreCliente: String = ""
    private val host = "10.0.2.2" // Al estar emulado la IP 10.0.2.2 corresponde a 127.0.0.1
    private var puerto = 12345


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Obtén el puerto del intent
        puerto = intent.getIntExtra("puerto", 12345)
        val ip = intent.getStringExtra("ip") ?: host  // Obtener la IP, por defecto usar 10.0.2.2
        nombreCliente = intent.getStringExtra("nombre") ?: "ClienteSinNombre"  // Obtener el nombre del cliente

        // Asocia los elementos del XML
        mensajeEntrada = findViewById(R.id.mensajeEntrada)
        botonEnviar = findViewById(R.id.botonEnviar)
        vistaChat = findViewById(R.id.vistaChat)

        // Conéctate al servidor en un hilo separado (para no bloquear la UI)
        thread {
            connectToServer() // <-- Metodo conectar con el Server
        }

        // --> [BOTON ENVIAR] <--
        botonEnviar.setOnClickListener {
            val message = mensajeEntrada.text.toString() // Obtiene el mensaje
            if (message.isNotEmpty()) {
                sendMessage(message) // Llama a la función para enviar el mensaje al servidor
                mensajeEntrada.text.clear()
            }
        }
    }

    //--------------------------------------------------------------------------------------------

    // Metodo conectarse al Server
    private fun connectToServer() {
        try {
            // Establece la conexión
            socket = Socket(host, puerto)
            writer =
                PrintWriter(socket.getOutputStream(), true) // Para enviar mensajes
            read =
                BufferedReader(InputStreamReader(socket.getInputStream())) // Para leer mensajes

            // Envía el nombre del cliente inmediatamente después de conectarse
            writer.println(nombreCliente)

            // Lee los mensajes del servidor en un hilo separado (para no bloquear la UI)
            thread {
                while (true) {
                    val message = read.readLine() // Lee un mensaje enviado por el servidor

                    // Muestra el mensaje recibido en el TextArea
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

    // Metodo enviar un mensaje al servidor
    private fun sendMessage(message: String) {
        thread {
            try {
                writer.println(message) // Envía el mensaje al servidor

                // Muestra el mensaje enviado en el TextArea
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
