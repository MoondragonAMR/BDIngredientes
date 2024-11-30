package com.example.bdingredientes.ui.theme

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.KeyboardType
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
fun PantallaAñadirAdmin() {
    var nombreAdd by remember { mutableStateOf("") }
    nombre = nombreAdd
    var tipoAdd by remember { mutableStateOf("") }
    tipo = tipoAdd
    var saborAdd by remember { mutableStateOf("") }
    sabor = saborAdd
    var deCelebracionAdd by remember { mutableStateOf(false) }
    deCelebracion = deCelebracionAdd
    var celebracionAdd by remember { mutableStateOf("") }
    celebracion = celebracionAdd
    var comidaAdd by remember { mutableStateOf("") }
    comida2 = comidaAdd
    var juegoAdd by remember { mutableStateOf("") }
    juego3 = juegoAdd
    var parteAdd by remember { mutableStateOf("") }
    parte2 = parteAdd
    var numeroAdd by remember { mutableStateOf(0)}
    numero = numeroAdd

    Column {
        Row {
            Text ("Number: ")
            TextField(
                value = numeroAdd.toString(),
                onValueChange = { texto -> numeroAdd = texto.toInt(); numero = numeroAdd },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                placeholder = { Text("Insert the number of the ingredient") })
        }
        Row {
            Text ("Name: ")
            TextField(
                value = nombreAdd,
                onValueChange = { texto -> nombreAdd = texto; nombre = nombreAdd },
                placeholder = { Text("Insert the name of the ingredient") })
        }
        Row {
            Text ("Type: ")
            TextField(
                value = tipoAdd,
                onValueChange = { texto -> tipoAdd = texto; tipo = tipoAdd },
                placeholder = { Text("Insert the type of the ingredient") })
        }
        Row {
            Text("Flavor: ")
            TextField(
                value = saborAdd,
                onValueChange = { texto -> saborAdd = texto; sabor = saborAdd },
                placeholder = { Text("Insert the flavor of the ingredient") })
        }
        Row {
            Text("Holiday-exclusive: ")
            var seleccionado by remember { mutableStateOf(deCelebracionAdd) }
            Checkbox(checked = seleccionado, onCheckedChange = {
                seleccionado = !seleccionado; deCelebracionAdd = seleccionado; deCelebracion = deCelebracionAdd
            })
        }
        Row {
            Text("Holiday: ")
            TextField(
                value = celebracionAdd,
                onValueChange = { texto -> celebracionAdd = texto; celebracion = celebracionAdd },
                placeholder = { Text("Insert the holiday of the ingredient") })
        }
        Row {
            Text("Food: ")
            TextField(
                value = comidaAdd,
                onValueChange = { texto -> comidaAdd = texto; comida2 = comidaAdd },
                placeholder = { Text("Insert the food of the ingredient") })
        }
        Row {
            Text("Game: ")
            TextField(
                value = juegoAdd,
                onValueChange = { texto -> juegoAdd = texto; juego3 = juegoAdd },
                placeholder = { Text("Insert the game of the ingredient") })
        }
        Row {
            Text("Order Part: ")
            TextField(
                value = parteAdd,
                onValueChange = { texto -> parteAdd = texto; parte2 = parteAdd },
                placeholder = { Text("Insert the order part of the ingredient") })
        }
    }
}