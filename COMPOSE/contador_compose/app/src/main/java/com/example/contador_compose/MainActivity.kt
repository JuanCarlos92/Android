package com.example.contador_compose

import android.os.Bundle
import android.widget.Space
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
//import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.contador_compose.ui.theme.Contador_composeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            // Contenido de la app que se verá al emular o ejecutar la aplicación
            ContadorApp()
        }
    }
}
@Preview // Recordamos la utilidad de la previsualización que nos permite ver el contenido de las funciones composables
@Composable
fun ContadorApp() {

    // Counter almacena el valor actual del contador, que será modificado por el slider y los buttons
    var counter by remember { mutableStateOf(0f) } // remember {} permite que el valor se mantenga
    // mutableStateOf(0f) inicializa el estado con valor 0, de tipo float

    Column( // Column es un layout en compose que alinea elementos de manera vertical
        modifier = Modifier.fillMaxSize(), // Atributo que expande la columna
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // "Contador: ${counter.toInt()}" mostrará el valor actual del contador convertido a entero
        Text(text = "Contador: ${counter.toInt()}", fontSize = 24.sp) //sp -> scaled pixels, que ajusta el tamaño según la densisdad de la pantalla
        Spacer(modifier = Modifier.height(20.dp) ) // Spacer crea un espacio vacío en la UI (en altura)
        Slider(
            value = counter, // ligamos el valor del slider a la variable counter para que refleje su valor actual
            onValueChange = {counter = it}, // cada vez que el usuario mueve el slider, it es el nuevo valor
            valueRange = 0f..100f
        )
        Spacer(modifier = Modifier.height(20.dp) )
        Row { // Elementos horizontales (filas)
            Button(onClick = { counter--}) {
                Text(text = "Decrementar")  // Nótese como los componentes Text están indentados en el cuerpo de otro componente ocmo Button
            }
            Spacer(modifier = Modifier.width(20.dp))
            Button(onClick = { counter++ }) {
                Text(text = "Incrementar")
            }
        }
    }
}