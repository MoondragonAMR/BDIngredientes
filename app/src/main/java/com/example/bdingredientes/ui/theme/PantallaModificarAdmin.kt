package com.example.bdingredientes.ui.theme

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Checkbox
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
import com.example.bdingredientes.clases.celebracion
import com.example.bdingredientes.clases.comida2
import com.example.bdingredientes.clases.deCelebracion
import com.example.bdingredientes.clases.juego3
import com.example.bdingredientes.clases.nombre
import com.example.bdingredientes.clases.numero
import com.example.bdingredientes.clases.parte2
import com.example.bdingredientes.clases.sabor
import com.example.bdingredientes.clases.tipo

@Composable
fun PantallaModificarAdmin(
    name: String,
    type: String,
    flavor: String,
    holidayExclusive: Boolean,
    holiday: String,
    food: String,
    number: Int,
    game: String,
    part: String, sf : ViewModelScaffold = viewModel()
) {

    val idioma = sf.english.collectAsState().value

    var nombreAdd by remember { mutableStateOf(name) }
    nombre = nombreAdd
    var tipoAdd by remember { mutableStateOf(type) }
    tipo = tipoAdd
    var saborAdd by remember { mutableStateOf(flavor) }
    sabor = saborAdd
    var deCelebracionAdd by remember { mutableStateOf(holidayExclusive) }
    deCelebracion = deCelebracionAdd
    var celebracionAdd by remember { mutableStateOf(holiday) }
    celebracion = celebracionAdd
    var comidaAdd by remember { mutableStateOf(food) }
    comida2 = comidaAdd
    var juegoAdd by remember { mutableStateOf(game) }
    juego3 = juegoAdd
    var parteAdd by remember { mutableStateOf(part) }
    parte2 = parteAdd
    var numeroAdd by remember { mutableStateOf(number)}
    numero = numeroAdd

    Column {
        Row {
            val textoNumero = if (idioma.value) {
                "Number: "
            } else {
                "Número: "
            }
            Text (textoNumero)
            val textoPlaceholderNumero = if (idioma.value) {
                "Insert the number of the ingredient"
            } else {
                "Introduce el número del ingrediente"
            }
            TextField(
                value = numeroAdd.toString(),
                onValueChange = { texto -> numeroAdd = texto.toInt(); numero = numeroAdd },
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
                "Insert the name of the ingredient"
            } else {
                "Introduce el nombre del ingrediente"
            }
            TextField(
                value = nombreAdd,
                onValueChange = { texto -> nombreAdd = texto; nombre = nombreAdd },
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
                "Insert the type of the ingredient"
            } else {
                "Introduce el tipo del ingrediente"
            }
            TextField(
                value = tipoAdd,
                onValueChange = { texto -> tipoAdd = texto; tipo = tipoAdd },
                placeholder = { Text(textoPlaceholderTipo) })
        }
        val textoSabor = if (idioma.value) {
            "Flavor: "
        } else {
            "Sabor: "
        }
        Row {
            Text(textoSabor)
            val textoPlaceholderSabor = if (idioma.value) {
                "Insert the flavor of the ingredient"
            } else {
                "Introduce el sabor del ingrediente"
            }
            TextField(
                value = saborAdd,
                onValueChange = { texto -> saborAdd = texto; sabor = saborAdd },
                placeholder = { Text(textoPlaceholderSabor) })
        }
        val textoHE = if (idioma.value) {
            "Holiday-exclusive: "
        } else {
            "De celebración: "
        }
        Row {
            Text(textoHE)
            var seleccionado by remember { mutableStateOf(deCelebracionAdd) }
            Checkbox(checked = seleccionado, onCheckedChange = {
                seleccionado = !seleccionado; deCelebracionAdd = seleccionado; deCelebracion = deCelebracionAdd
            })
        }
        val textoCelebracion = if (idioma.value) {
            "Holiday: "
        } else {
            "Celebración: "
        }
        Row {
            Text(textoCelebracion)
            val textoPlaceholderCelebracion = if (idioma.value) {
                "Insert the holiday of the ingredient"
            } else {
                "Introduce la celebración del ingrediente"
            }
            TextField(
                value = celebracionAdd,
                onValueChange = { texto -> celebracionAdd = texto; celebracion = celebracionAdd },
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
                "Insert the food of the ingredient"
            } else {
                "Introduce la comida del ingrediente"
            }
            TextField(
                value = comidaAdd,
                onValueChange = { texto -> comidaAdd = texto; comida2 = comidaAdd },
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
                "Insert the game of the ingredient"
            } else {
                "Introduce el juego del ingrediente"
            }
            TextField(
                value = juegoAdd,
                onValueChange = { texto -> juegoAdd = texto; juego3 = juegoAdd },
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
                "Insert the order part of the ingredient"
            } else {
                "Introduce la parte del pedido del ingrediente"
            }
            TextField(
                value = parteAdd,
                onValueChange = { texto -> parteAdd = texto; parte2 = parteAdd },
                placeholder = { Text(textoPlaceholderOP) })
        }
    }
}