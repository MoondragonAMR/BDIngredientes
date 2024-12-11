package com.example.bdingredientes.ui.theme

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.KeyboardType
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bdingredientes.clases.ViewModelScaffold
import com.example.bdingredientes.clases.celebracion2
import com.example.bdingredientes.clases.comida
import com.example.bdingredientes.clases.juego2
import com.example.bdingredientes.clases.nombre2
import com.example.bdingredientes.clases.numero2
import com.example.bdingredientes.clases.parte
import com.example.bdingredientes.clases.tipo2

@Composable
fun PantallaAñadirAdmin2(sf : ViewModelScaffold = viewModel()) {

    val idioma = sf.english.collectAsState().value

    var nombreAdd by remember { mutableStateOf("") }
    nombre2 = nombreAdd
    var tipoAdd by remember { mutableStateOf("") }
    tipo2 = tipoAdd
    var comidaAdd by remember { mutableStateOf("") }
    comida = comidaAdd
    var juegoAdd by remember { mutableStateOf("") }
    juego2 = juegoAdd
    var celebracionAdd by remember { mutableStateOf("") }
    celebracion2 = celebracionAdd
    var parteAdd by remember { mutableStateOf("") }
    parte = parteAdd
    var numeroAdd by remember { mutableStateOf(0)}
    numero2 = numeroAdd

    Column {
        Row {
            val textoNumero = if (idioma.value) {
                "Number: "
            } else {
                "Número: "
            }
            Text (textoNumero)
            val textoPlaceholderNumero = if (idioma.value) {
                "Insert the number of the equipment"
            } else {
                "Introduce el número del utensilio"
            }
            TextField(
                value = numeroAdd.toString(),
                onValueChange = { texto -> numeroAdd = texto.toInt(); numero2 = numeroAdd },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                placeholder = { Text(textoPlaceholderNumero) })
        }
        val textoNombre = if (idioma.value) {
            "Name: "
        } else {
            "Nombre: "
        }
        Row {
            Text (textoNombre)
            val textoPlaceholderNombre = if (idioma.value) {
                "Insert the name of the equipment"
            } else {
                "Introduce el nombre del utensilio"
            }
            TextField(
                value = nombreAdd,
                onValueChange = { texto -> nombreAdd = texto; nombre2 = nombreAdd },
                placeholder = { Text(textoPlaceholderNombre) })
        }
        val textoTipo = if (idioma.value) {
            "Type: "
        } else {
            "Tipo: "
        }
        Row {
            Text (textoTipo)
            val textoPlaceholderTipo = if (idioma.value) {
                "Insert the type of the equipment"
            } else {
                "Introduce el tipo del utensilio"
            }
            TextField(
                value = tipoAdd,
                onValueChange = { texto -> tipoAdd = texto; tipo2 = tipoAdd },
                placeholder = { Text(textoPlaceholderTipo) })
        }
        val textoCelebracion = if (idioma.value) {
            "Holiday: "
        } else {
            "Celebración: "
        }
        Row {
            Text(textoCelebracion)
            val textoPlaceholderCelebracion = if (idioma.value) {
                "Insert the holiday of the equipment"
            } else {
                "Introduce la celebración del utensilio"
            }
            TextField(
                value = celebracionAdd,
                onValueChange = { texto -> celebracionAdd = texto; celebracion2 = celebracionAdd },
                placeholder = { Text(textoPlaceholderCelebracion) })
        }
        val textoComida = if (idioma.value) {
            "Food: "
        } else {
            "Comida: "
        }
        Row {
            Text(textoComida)
            val textoPlaceholderComida = if (idioma.value) {
                "Insert the food of the equipment"
            } else {
                "Introduce la comida del utensilio"
            }
            TextField(
                value = comidaAdd,
                onValueChange = { texto -> comidaAdd = texto; comida = comidaAdd },
                placeholder = { Text(textoPlaceholderComida) })
        }
        val textoJuego = if (idioma.value) {
            "Game: "
        } else {
            "Juego: "
        }
        Row {
            Text(textoJuego)
            val textoPlaceholderJuego = if (idioma.value) {
                "Insert the game of the equipment"
            } else {
                "Introduce el juego del utensilio"
            }
            TextField(
                value = juegoAdd,
                onValueChange = { texto -> juegoAdd = texto; juego2 = juegoAdd },
                placeholder = { Text(textoPlaceholderJuego) })
        }
        val textoOP = if (idioma.value) {
            "Order Part: "
        } else {
            "Parte del pedido: "
        }
        Row {
            Text(textoOP)
            val textoPlaceholderOP = if (idioma.value) {
                "Insert the order part of the equipment"
            } else {
                "Introduce la parte del pedido del utensilio"
            }
            TextField(
                value = parteAdd,
                onValueChange = { texto -> parteAdd = texto; parte = parteAdd },
                placeholder = { Text(textoPlaceholderOP) })
        }
    }
}