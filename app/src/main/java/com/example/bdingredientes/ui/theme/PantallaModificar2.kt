package com.example.bdingredientes.ui.theme

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.bdingredientes.clases.celebracion
import com.example.bdingredientes.clases.celebracion2
import com.example.bdingredientes.clases.comida
import com.example.bdingredientes.clases.deCelebracion
import com.example.bdingredientes.clases.juego2
import com.example.bdingredientes.clases.nombre
import com.example.bdingredientes.clases.nombre2
import com.example.bdingredientes.clases.numero2
import com.example.bdingredientes.clases.parte
import com.example.bdingredientes.clases.sabor
import com.example.bdingredientes.clases.tipo
import com.example.bdingredientes.clases.tipo2

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaModificar2(id : String, name : String, type : String, holiday : String, food : String, game : String, part : String, number : Int) {
    var nombreAdd by remember { mutableStateOf(name) }
    nombre2 = nombreAdd
    var tipoAdd by remember { mutableStateOf(type) }
    tipo2 = tipoAdd
    var comidaAdd by remember { mutableStateOf(food) }
    comida = comidaAdd
    var juegoAdd by remember { mutableStateOf(game) }
    juego2 = juegoAdd
    var celebracionAdd by remember { mutableStateOf(holiday) }
    celebracion2 = celebracionAdd
    var parteAdd by remember { mutableStateOf(part) }
    parte = parteAdd
    numero2 = number
    var activado by remember { mutableStateOf(false) }
    var activado2 by remember { mutableStateOf(false) }
    var activado3 by remember { mutableStateOf(false) }
    var activado4 by remember { mutableStateOf(false) }
    var activado5 by remember { mutableStateOf(false) }

    Column {
        Row {
            Text ("Name: ")
            TextField(
                value = nombreAdd,
                onValueChange = { texto -> nombreAdd = texto; nombre2 = nombreAdd },
                placeholder = { Text("Insert the name of the equipment") })
        }
        Row {
            Text ("Type: ")
            ExposedDropdownMenuBox(expanded = activado,
                onExpandedChange = {activado = !activado} ) {
                TextField(modifier = Modifier.menuAnchor(),
                    readOnly = true,
                    value = tipoAdd,
                    onValueChange = {}
                )
                DropdownMenu(expanded = activado, onDismissRequest = { activado = false }) {
                    DropdownMenuItem(
                        text = { Text("Alarm") },
                        onClick = { tipoAdd = "Alarm"; tipo2 = tipoAdd })
                    DropdownMenuItem(
                        text = { Text("Bag Size") },
                        onClick = { tipoAdd = "Bag Size"; tipo2 = tipoAdd })
                    DropdownMenuItem(
                        text = { Text("Cup Size") },
                        onClick = { tipoAdd = "Cup Size"; tipo2 = tipoAdd })
                    DropdownMenuItem(
                        text = { Text("Cut") },
                        onClick = { tipoAdd = "Cut"; tipo2 = tipoAdd })
                    DropdownMenuItem(
                        text = { Text("Liner") },
                        onClick = { tipoAdd = "Liner"; tipo2 = tipoAdd })
                    DropdownMenuItem(
                        text = { Text("Position") },
                        onClick = { tipoAdd = "Position"; tipo2 = tipoAdd })
                }
            }
        }
        Row {
            Text("Holiday: ")
            ExposedDropdownMenuBox(expanded = activado2,
                onExpandedChange = {activado2 = !activado2} ) {
                TextField(modifier = Modifier.menuAnchor(),
                    readOnly = true,
                    value = celebracionAdd,
                    onValueChange = {}
                )
                DropdownMenu(expanded = activado2, onDismissRequest = { activado2 = false }) {
                    DropdownMenuItem(
                        text = { Text("Standard") },
                        onClick = { celebracionAdd = "Standard"; celebracion2 = celebracionAdd })
                    DropdownMenuItem(
                        text = { Text("Baseball Season") },
                        onClick = { celebracionAdd = "Baseball Season"; celebracion2 = celebracionAdd })
                    DropdownMenuItem(
                        text = { Text("Big Top Carnival") },
                        onClick = { celebracionAdd = "Big Top Carnival"; celebracion2 = celebracionAdd })
                    DropdownMenuItem(
                        text = { Text("Cherry Blossom Festival") },
                        onClick = { celebracionAdd = "Cherry Blossom Festival"; celebracion2 = celebracionAdd })
                    DropdownMenuItem(
                        text = { Text("Christmas") },
                        onClick = { celebracionAdd = "Christmas"; celebracion2 = celebracionAdd })
                    DropdownMenuItem(
                        text = { Text("Cinco de Mayo") },
                        onClick = { celebracionAdd = "Cinco de Mayo"; celebracion2 = celebracionAdd })
                    DropdownMenuItem(
                        text = { Text("Comet Con") },
                        onClick = { celebracionAdd = "Comet Con"; celebracion2 = celebracionAdd })
                    DropdownMenuItem(
                        text = { Text("Easter") },
                        onClick = { celebracionAdd = "Easter"; celebracion2 = celebracionAdd })
                    DropdownMenuItem(
                        text = { Text("Film Fest") },
                        onClick = { celebracionAdd = "Film Fest"; celebracion2 = celebracionAdd })
                    DropdownMenuItem(
                        text = { Text("Halloween") },
                        onClick = { celebracionAdd = "Halloween"; celebracion2 = celebracionAdd })
                    DropdownMenuItem(
                        text = { Text("Maple Mornings") },
                        onClick = { celebracionAdd = "Maple Mornings"; celebracion2 = celebracionAdd })
                    DropdownMenuItem(
                        text = { Text("New Year") },
                        onClick = { celebracionAdd = "New Year"; celebracion2 = celebracionAdd })
                    DropdownMenuItem(
                        text = { Text("Onionfest") },
                        onClick = { celebracionAdd = "Onionfest"; celebracion2 = celebracionAdd })
                    DropdownMenuItem(
                        text = { Text("Pirate Bash") },
                        onClick = { celebracionAdd = "Pirate Bash"; celebracion2 = celebracionAdd })
                    DropdownMenuItem(
                        text = { Text("St Paddy") },
                        onClick = { celebracionAdd = "St Paddy"; celebracion2 = celebracionAdd })
                    DropdownMenuItem(
                        text = { Text("Starlight") },
                        onClick = { celebracionAdd = "Starlight"; celebracion2 = celebracionAdd })
                    DropdownMenuItem(
                        text = { Text("Summer Luau") },
                        onClick = { celebracionAdd = "Summer Luau"; celebracion2 = celebracionAdd })
                    DropdownMenuItem(
                        text = { Text("Thanksgiving") },
                        onClick = { celebracionAdd = "Thanksgiving"; celebracion2 = celebracionAdd })
                    DropdownMenuItem(
                        text = { Text("Valentines") },
                        onClick = { celebracionAdd = "Valentines"; celebracion2 = celebracionAdd })
                }
            }
        }
        Row {
            Text("Food: ")
            ExposedDropdownMenuBox(expanded = activado3,
                onExpandedChange = {activado3 = !activado3} ) {
                TextField(modifier = Modifier.menuAnchor(),
                    readOnly = true,
                    value = comidaAdd,
                    onValueChange = {}
                )
                DropdownMenu(expanded = activado3, onDismissRequest = { activado3 = false }) {
                    DropdownMenuItem(
                        text = { Text("Breakfast") },
                        onClick = { comidaAdd = "Breakfast"; comida = comidaAdd })
                    DropdownMenuItem(
                        text = { Text("Burger") },
                        onClick = { comidaAdd = "Burger"; comida = comidaAdd })
                    DropdownMenuItem(
                        text = { Text("Chicken Sandwich") },
                        onClick = { comidaAdd = "Chicken Sandwich"; comida = comidaAdd })
                    DropdownMenuItem(
                        text = { Text("Chicken Wings") },
                        onClick = { comidaAdd = "Chicken Wings"; comida = comidaAdd })
                    DropdownMenuItem(
                        text = { Text("Cupcakes") },
                        onClick = { comidaAdd = "Cupcakes"; comida = comidaAdd })
                    DropdownMenuItem(
                        text = { Text("Hot Dog") },
                        onClick = { comidaAdd = "Hot Dog"; comida = comidaAdd })
                    DropdownMenuItem(
                        text = { Text("Mocha") },
                        onClick = { comidaAdd = "Mocha"; comida = comidaAdd })
                    DropdownMenuItem(
                        text = { Text("Paleta") },
                        onClick = { comidaAdd = "Paleta"; comida = comidaAdd })
                    DropdownMenuItem(
                        text = { Text("Pasta") },
                        onClick = { comidaAdd = "Pasta"; comida = comidaAdd })
                    DropdownMenuItem(
                        text = { Text("Pie") },
                        onClick = { comidaAdd = "Pie"; comida = comidaAdd })
                    DropdownMenuItem(
                        text = { Text("Pizza") },
                        onClick = { comidaAdd = "Pizza"; comida = comidaAdd })
                    DropdownMenuItem(
                        text = { Text("Sandwich") },
                        onClick = { comidaAdd = "Sandwich"; comida = comidaAdd })
                    DropdownMenuItem(
                        text = { Text("Sundae") },
                        onClick = { comidaAdd = "Sundae"; comida = comidaAdd })
                }
            }
        }
        Row {
            Text("Game: ")
            ExposedDropdownMenuBox(expanded = activado4,
                onExpandedChange = {activado4 = !activado4} ) {
                TextField(modifier = Modifier.menuAnchor(),
                    readOnly = true,
                    value = juegoAdd,
                    onValueChange = {}
                )
                DropdownMenu(expanded = activado4, onDismissRequest = { activado4 = false }) {
                    DropdownMenuItem(
                        text = { Text("Bakeria") },
                        onClick = { juegoAdd = "Bakeria"; juego2 = juegoAdd })
                    DropdownMenuItem(
                        text = { Text("Bakeria To Go") },
                        onClick = { juegoAdd = "Bakeria To Go"; juego2 = juegoAdd })
                    DropdownMenuItem(
                        text = { Text("Burgeria") },
                        onClick = { juegoAdd = "Burgeria"; juego2 = juegoAdd })
                    DropdownMenuItem(
                        text = { Text("Burgeria HD") },
                        onClick = { juegoAdd = "Burgeria HD"; juego2 = juegoAdd })
                    DropdownMenuItem(
                        text = { Text("Burgeria To Go") },
                        onClick = { juegoAdd = "Burgeria To Go"; juego2 = juegoAdd })
                    DropdownMenuItem(
                        text = { Text("Cheeseria") },
                        onClick = { juegoAdd = "Cheeseria"; juego2 = juegoAdd })
                    DropdownMenuItem(
                        text = { Text("Cheeseria To Go") },
                        onClick = { juegoAdd = "Cheeseria To Go"; juego2 = juegoAdd })
                    DropdownMenuItem(
                        text = { Text("Cluckeria To Go") },
                        onClick = { juegoAdd = "Cluckeria To Go"; juego2 = juegoAdd })
                    DropdownMenuItem(
                        text = { Text("Cupcakeria") },
                        onClick = { juegoAdd = "Cupcakeria"; juego2 = juegoAdd })
                    DropdownMenuItem(
                        text = { Text("Cupcakeria HD") },
                        onClick = { juegoAdd = "Cupcakeria HD"; juego2 = juegoAdd })
                    DropdownMenuItem(
                        text = { Text("Cupcakeria To Go") },
                        onClick = { juegoAdd = "Cupcakeria To Go"; juego2 = juegoAdd })
                    DropdownMenuItem(
                        text = { Text("Freezeria") },
                        onClick = { juegoAdd = "Freezeria"; juego2 = juegoAdd })
                    DropdownMenuItem(
                        text = { Text("Freezeria Deluxe") },
                        onClick = { juegoAdd = "Freezeria Deluxe"; juego2 = juegoAdd })
                    DropdownMenuItem(
                        text = { Text("Freezeria HD") },
                        onClick = { juegoAdd = "Freezeria HD"; juego2 = juegoAdd })
                    DropdownMenuItem(
                        text = { Text("Freezeria To Go") },
                        onClick = { juegoAdd = "Freezeria To Go"; juego2 = juegoAdd })
                    DropdownMenuItem(
                        text = { Text("Hot Doggeria") },
                        onClick = { juegoAdd = "Hot Doggeria"; juego2 = juegoAdd })
                    DropdownMenuItem(
                        text = { Text("Hot Doggeria HD") },
                        onClick = { juegoAdd = "Hot Doggeria HD"; juego2 = juegoAdd })
                    DropdownMenuItem(
                        text = { Text("Hot Doggeria To Go") },
                        onClick = { juegoAdd = "Hot Doggeria To Go"; juego2 = juegoAdd })
                    DropdownMenuItem(
                        text = { Text("Mocharia To Go") },
                        onClick = { juegoAdd = "Mocharia To Go"; juego2 = juegoAdd })
                    DropdownMenuItem(
                        text = { Text("Paleteria To Go") },
                        onClick = { juegoAdd = "Paleteria To Go"; juego2 = juegoAdd })
                    DropdownMenuItem(
                        text = { Text("Pancakeria") },
                        onClick = { juegoAdd = "Pancakeria"; juego2 = juegoAdd })
                    DropdownMenuItem(
                        text = { Text("Pancakeria HD") },
                        onClick = { juegoAdd = "Pancakeria HD"; juego2 = juegoAdd })
                    DropdownMenuItem(
                        text = { Text("Pancakeria To Go") },
                        onClick = { juegoAdd = "Pancakeria To Go"; juego2 = juegoAdd })
                    DropdownMenuItem(
                        text = { Text("Pastaria") },
                        onClick = { juegoAdd = "Pastaria"; juego2 = juegoAdd })
                    DropdownMenuItem(
                        text = { Text("Pastaria To Go") },
                        onClick = { juegoAdd = "Pastaria To Go"; juego2 = juegoAdd })
                    DropdownMenuItem(
                        text = { Text("Pizzeria") },
                        onClick = { juegoAdd = "Pizzeria"; juego2 = juegoAdd })
                    DropdownMenuItem(
                        text = { Text("Pizzeria Deluxe") },
                        onClick = { juegoAdd = "Pizzeria Deluxe"; juego2 = juegoAdd })
                    DropdownMenuItem(
                        text = { Text("Pizzeria HD") },
                        onClick = { juegoAdd = "Pizzeria HD"; juego2 = juegoAdd })
                    DropdownMenuItem(
                        text = { Text("Pizzeria To Go") },
                        onClick = { juegoAdd = "Pizzeria To Go"; juego2 = juegoAdd })
                    DropdownMenuItem(
                        text = { Text("Wingeria") },
                        onClick = { juegoAdd = "Wingeria"; juego2 = juegoAdd })
                    DropdownMenuItem(
                        text = { Text("Wingeria HD") },
                        onClick = { juegoAdd = "Wingeria HD"; juego2 = juegoAdd })
                    DropdownMenuItem(
                        text = { Text("Wingeria To Go") },
                        onClick = { juegoAdd = "Wingeria To Go"; juego2 = juegoAdd })
                }
            }
        }
        Row {
            Text("Order Part: ")
            ExposedDropdownMenuBox(expanded = activado5,
                onExpandedChange = {activado5 = !activado5} ) {
                TextField(modifier = Modifier.menuAnchor(),
                    readOnly = true,
                    value = parteAdd,
                    onValueChange = {}
                )
                DropdownMenu(expanded = activado5, onDismissRequest = { activado5 = false }) {
                    DropdownMenuItem(
                        text = { Text("Bag Size") },
                        onClick = { parteAdd = "Bag Size"; parte = parteAdd })
                    DropdownMenuItem(
                        text = { Text("Burger Time") },
                        onClick = { parteAdd = "Burger Time"; parte = parteAdd })
                    DropdownMenuItem(
                        text = { Text("Chicken Wings Position") },
                        onClick = { parteAdd = "Chicken Wings Position"; parte = parteAdd })
                    DropdownMenuItem(
                        text = { Text("Cut") },
                        onClick = { parteAdd = "Cut"; parte = parteAdd })
                    DropdownMenuItem(
                        text = { Text("Drink Size") },
                        onClick = { parteAdd = "Drink Size"; parte = parteAdd })
                    DropdownMenuItem(
                        text = { Text("Liner") },
                        onClick = { parteAdd = "Liner"; parte = parteAdd })
                    DropdownMenuItem(
                        text = { Text("Mocha Size") },
                        onClick = { parteAdd = "Mocha Size"; parte = parteAdd })
                    DropdownMenuItem(
                        text = { Text("Paleta Topping Position") },
                        onClick = { parteAdd = "Paleta Topping Position"; parte = parteAdd })
                    DropdownMenuItem(
                        text = { Text("Pasta Time") },
                        onClick = { parteAdd = "Pasta Time"; parte = parteAdd })
                    DropdownMenuItem(
                        text = { Text("Pie Topping Position") },
                        onClick = { parteAdd = "Pie Topping Position"; parte = parteAdd })
                    DropdownMenuItem(
                        text = { Text("Pizza Time") },
                        onClick = { parteAdd = "Pizza Time"; parte = parteAdd })
                    DropdownMenuItem(
                        text = { Text("Pizza Topping Position") },
                        onClick = { parteAdd = "Pizza Topping Position"; parte = parteAdd })
                    DropdownMenuItem(
                        text = { Text("Sandwich Time") },
                        onClick = { parteAdd = "Sandwich Time"; parte = parteAdd })
                    DropdownMenuItem(
                        text = { Text("Slush Size") },
                        onClick = { parteAdd = "Slush Size"; parte = parteAdd })
                    DropdownMenuItem(
                        text = { Text("Soda Size") },
                        onClick = { parteAdd = "Soda Size"; parte = parteAdd })
                    DropdownMenuItem(
                        text = { Text("Sundae Size") },
                        onClick = { parteAdd = "Sundae Size"; parte = parteAdd })
                    DropdownMenuItem(
                        text = { Text("Sundae Time") },
                        onClick = { parteAdd = "Sundae Time"; parte = parteAdd })
                }
            }
        }
    }
}