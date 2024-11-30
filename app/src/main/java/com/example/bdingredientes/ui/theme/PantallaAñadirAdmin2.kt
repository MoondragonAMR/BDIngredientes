package com.example.bdingredientes.ui.theme

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.KeyboardType
import com.example.bdingredientes.clases.celebracion2
import com.example.bdingredientes.clases.comida
import com.example.bdingredientes.clases.juego2
import com.example.bdingredientes.clases.nombre2
import com.example.bdingredientes.clases.numero2
import com.example.bdingredientes.clases.parte
import com.example.bdingredientes.clases.tipo2

@Composable
fun PantallaAñadirAdmin2() {
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
            Text ("Number: ")
            TextField(
                value = numeroAdd.toString(),
                onValueChange = { texto -> numeroAdd = texto.toInt(); numero2 = numeroAdd },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                placeholder = { Text("Insert the number of the equipment") })
        }
        Row {
            Text ("Name: ")
            TextField(
                value = nombreAdd,
                onValueChange = { texto -> nombreAdd = texto; nombre2 = nombreAdd },
                placeholder = { Text("Insert the name of the equipment") })
        }
        Row {
            Text ("Type: ")
            TextField(
                value = tipoAdd,
                onValueChange = { texto -> tipoAdd = texto; tipo2 = tipoAdd },
                placeholder = { Text("Insert the type of the equipment") })
        }
        Row {
            Text("Holiday: ")
            TextField(
                value = celebracionAdd,
                onValueChange = { texto -> celebracionAdd = texto; celebracion2 = celebracionAdd },
                placeholder = { Text("Insert the holiday of the equipment") })
        }
        Row {
            Text("Food: ")
            TextField(
                value = comidaAdd,
                onValueChange = { texto -> comidaAdd = texto; comida = comidaAdd },
                placeholder = { Text("Insert the food of the equipment") })
        }
        Row {
            Text("Game: ")
            TextField(
                value = juegoAdd,
                onValueChange = { texto -> juegoAdd = texto; juego2 = juegoAdd },
                placeholder = { Text("Insert the game of the equipment") })
        }
        Row {
            Text("Order Part: ")
            TextField(
                value = parteAdd,
                onValueChange = { texto -> parteAdd = texto; parte = parteAdd },
                placeholder = { Text("Insert the order part of the equipment") })
        }
    }
}