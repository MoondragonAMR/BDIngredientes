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
import com.example.bdingredientes.clases.comida2
import com.example.bdingredientes.clases.deCelebracion
import com.example.bdingredientes.clases.juego3
import com.example.bdingredientes.clases.nombre
import com.example.bdingredientes.clases.numero
import com.example.bdingredientes.clases.parte2
import com.example.bdingredientes.clases.sabor
import com.example.bdingredientes.clases.tipo

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaModificar(id : String, name : String, type : String, flavor : String, holidayExclusive : Boolean, holiday : String, food : String
, number : Int, game : String, part : String) {
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
    numero = number
    var activado by remember { mutableStateOf(false) }
    var activado2 by remember { mutableStateOf(false) }
    var activado3 by remember { mutableStateOf(false) }
    var activado4 by remember { mutableStateOf(false) }
    var activado5 by remember { mutableStateOf(false) }
    var activado6 by remember { mutableStateOf(false) }

    Column {
        Row {
            Text ("Name: ")
            TextField(
                value = nombreAdd,
                onValueChange = { texto -> nombreAdd = texto; nombre = nombreAdd },
                placeholder = { Text("Insert the name of the ingredient") })
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
                        text = { Text("Aditive") },
                        onClick = { tipoAdd = "Aditive"; tipo = tipoAdd })
                    DropdownMenuItem(
                        text = { Text("Bread") },
                        onClick = { tipoAdd = "Bread"; tipo = tipoAdd })
                    DropdownMenuItem(
                        text = { Text("Breading") },
                        onClick = { tipoAdd = "Breading"; tipo = tipoAdd })
                    DropdownMenuItem(
                        text = { Text("Breakfast Food") },
                        onClick = { tipoAdd = "Breakfast Food"; tipo = tipoAdd })
                    DropdownMenuItem(
                        text = { Text("Breakfast Mixable") },
                        onClick = { tipoAdd = "Breakfast Mixable"; tipo = tipoAdd })
                    DropdownMenuItem(
                        text = { Text("Bubble Tea") },
                        onClick = { tipoAdd = "Bubble Tea"; tipo = tipoAdd })
                    DropdownMenuItem(
                        text = { Text("Burger Bun") },
                        onClick = { tipoAdd = "Burger Bun"; tipo = tipoAdd })
                    DropdownMenuItem(
                        text = { Text("Burger Meat") },
                        onClick = { tipoAdd = "Burger Meat"; tipo = tipoAdd })
                    DropdownMenuItem(
                        text = { Text("Cake") },
                        onClick = { tipoAdd = "Cake"; tipo = tipoAdd })
                    DropdownMenuItem(
                        text = { Text("Cannoli Shell") },
                        onClick = { tipoAdd = "Cannoli Shell"; tipo = tipoAdd })
                    DropdownMenuItem(
                        text = { Text("Chicken Sauce") },
                        onClick = { tipoAdd = "Chicken Sauce"; tipo = tipoAdd })
                    DropdownMenuItem(
                        text = { Text("Chicken Wings") },
                        onClick = { tipoAdd = "Chicken Wings"; tipo = tipoAdd })
                    DropdownMenuItem(
                        text = { Text("Coffee") },
                        onClick = { tipoAdd = "Coffee"; tipo = tipoAdd })
                    DropdownMenuItem(
                        text = { Text("Cookie Dough") },
                        onClick = { tipoAdd = "Cookie Dough"; tipo = tipoAdd })
                    DropdownMenuItem(
                        text = { Text("Cookie Mixable") },
                        onClick = { tipoAdd = "Cookie Mixable"; tipo = tipoAdd })
                    DropdownMenuItem(
                        text = { Text("Cream") },
                        onClick = { tipoAdd = "Cream"; tipo = tipoAdd })
                    DropdownMenuItem(
                        text = { Text("Cream Dollop") },
                        onClick = { tipoAdd = "Cream Dollop"; tipo = tipoAdd })
                    DropdownMenuItem(
                        text = { Text("Dip") },
                        onClick = { tipoAdd = "Dip"; tipo = tipoAdd })
                    DropdownMenuItem(
                        text = { Text("Donut Cutter") },
                        onClick = { tipoAdd = "Donut Cutter"; tipo = tipoAdd })
                    DropdownMenuItem(
                        text = { Text("Donut Dough") },
                        onClick = { tipoAdd = "Donut Dough"; tipo = tipoAdd })
                    DropdownMenuItem(
                        text = { Text("Donut Filling") },
                        onClick = { tipoAdd = "Donut Filling"; tipo = tipoAdd })
                    DropdownMenuItem(
                        text = { Text("Drink") },
                        onClick = { tipoAdd = "Drink"; tipo = tipoAdd })
                    DropdownMenuItem(
                        text = { Text("Flat Topper") },
                        onClick = { tipoAdd = "Flat Topper"; tipo = tipoAdd })
                    DropdownMenuItem(
                        text = { Text("Fries") },
                        onClick = { tipoAdd = "Fries"; tipo = tipoAdd })
                    DropdownMenuItem(
                        text = { Text("Frosting") },
                        onClick = { tipoAdd = "Frosting"; tipo = tipoAdd })
                    DropdownMenuItem(
                        text = { Text("Fry Topping") },
                        onClick = { tipoAdd = "Fry Topping"; tipo = tipoAdd })
                    DropdownMenuItem(
                        text = { Text("Hot Dog Bun") },
                        onClick = { tipoAdd = "Hot Dog Bun"; tipo = tipoAdd })
                    DropdownMenuItem(
                        text = { Text("Ice") },
                        onClick = { tipoAdd = "Ice"; tipo = tipoAdd })
                    DropdownMenuItem(
                        text = { Text("Ice Cream") },
                        onClick = { tipoAdd = "Ice Cream"; tipo = tipoAdd })
                    DropdownMenuItem(
                        text = { Text("Long Topper") },
                        onClick = { tipoAdd = "Long Topper"; tipo = tipoAdd })
                    DropdownMenuItem(
                        text = { Text("Milk") },
                        onClick = { tipoAdd = "Milk"; tipo = tipoAdd })
                    DropdownMenuItem(
                        text = { Text("Mixable") },
                        onClick = { tipoAdd = "Mixable"; tipo = tipoAdd })
                    DropdownMenuItem(
                        text = { Text("Mixable Syrup") },
                        onClick = { tipoAdd = "Mixable Syrup"; tipo = tipoAdd })
                    DropdownMenuItem(
                        text = { Text("Nacho Chips") },
                        onClick = { tipoAdd = "Nacho Chips"; tipo = tipoAdd })
                    DropdownMenuItem(
                        text = { Text("Nacho Dip") },
                        onClick = { tipoAdd = "Nacho Dip"; tipo = tipoAdd })
                    DropdownMenuItem(
                        text = { Text("Pasta") },
                        onClick = { tipoAdd = "Pasta"; tipo = tipoAdd })
                    DropdownMenuItem(
                        text = { Text("Pasta Sauce") },
                        onClick = { tipoAdd = "Pasta Sauce"; tipo = tipoAdd })
                    DropdownMenuItem(
                        text = { Text("Pie Crust") },
                        onClick = { tipoAdd = "Pie Crust"; tipo = tipoAdd })
                    DropdownMenuItem(
                        text = { Text("Pie Filling") },
                        onClick = { tipoAdd = "Pie Filling"; tipo = tipoAdd })
                    DropdownMenuItem(
                        text = { Text("Pizza Cheese") },
                        onClick = { tipoAdd = "Pizza Cheese"; tipo = tipoAdd })
                    DropdownMenuItem(
                        text = { Text("Pizza Crust") },
                        onClick = { tipoAdd = "Pizza Crust"; tipo = tipoAdd })
                    DropdownMenuItem(
                        text = { Text("Pizza Sauce") },
                        onClick = { tipoAdd = "Pizza Sauce"; tipo = tipoAdd })
                    DropdownMenuItem(
                        text = { Text("Popcorn") },
                        onClick = { tipoAdd = "Popcorn"; tipo = tipoAdd })
                    DropdownMenuItem(
                        text = { Text("Powder") },
                        onClick = { tipoAdd = "Powder"; tipo = tipoAdd })
                    DropdownMenuItem(
                        text = { Text("Rice") },
                        onClick = { tipoAdd = "Rice"; tipo = tipoAdd })
                    DropdownMenuItem(
                        text = { Text("Sandwich Bread") },
                        onClick = { tipoAdd = "Sandwich Bread"; tipo = tipoAdd })
                    DropdownMenuItem(
                        text = { Text("Sandwich Cheese") },
                        onClick = { tipoAdd = "Sandwich Cheese"; tipo = tipoAdd })
                    DropdownMenuItem(
                        text = { Text("Sauce") },
                        onClick = { tipoAdd = "Sauce"; tipo = tipoAdd })
                    DropdownMenuItem(
                        text = { Text("Sausage") },
                        onClick = { tipoAdd = "Sausage"; tipo = tipoAdd })
                    DropdownMenuItem(
                        text = { Text("Shaker") },
                        onClick = { tipoAdd = "Shaker"; tipo = tipoAdd })
                    DropdownMenuItem(
                        text = { Text("Side") },
                        onClick = { tipoAdd = "Side"; tipo = tipoAdd })
                    DropdownMenuItem(
                        text = { Text("Slush") },
                        onClick = { tipoAdd = "Slush"; tipo = tipoAdd })
                    DropdownMenuItem(
                        text = { Text("Soda") },
                        onClick = { tipoAdd = "Soda"; tipo = tipoAdd })
                    DropdownMenuItem(
                        text = { Text("Soy Paper") },
                        onClick = { tipoAdd = "Soy Paper"; tipo = tipoAdd })
                    DropdownMenuItem(
                        text = { Text("Sushi Filling") },
                        onClick = { tipoAdd = "Sushi Filling"; tipo = tipoAdd })
                    DropdownMenuItem(
                        text = { Text("Syrup") },
                        onClick = { tipoAdd = "Syrup"; tipo = tipoAdd })
                    DropdownMenuItem(
                        text = { Text("Taco Meat") },
                        onClick = { tipoAdd = "Taco Meat"; tipo = tipoAdd })
                    DropdownMenuItem(
                        text = { Text("Taco Shell") },
                        onClick = { tipoAdd = "Taco Shell"; tipo = tipoAdd })
                    DropdownMenuItem(
                        text = { Text("Tea Bubbles") },
                        onClick = { tipoAdd = "Tea Bubbles"; tipo = tipoAdd })
                    DropdownMenuItem(
                        text = { Text("Top Crust") },
                        onClick = { tipoAdd = "Top Crust"; tipo = tipoAdd })
                    DropdownMenuItem(
                        text = { Text("Topper") },
                        onClick = { tipoAdd = "Topper"; tipo = tipoAdd })
                    DropdownMenuItem(
                        text = { Text("Topping") },
                        onClick = { tipoAdd = "Topping"; tipo = tipoAdd })
                    DropdownMenuItem(
                        text = { Text("Paleta Shape") },
                        onClick = { tipoAdd = "Paleta Shape"; tipo = tipoAdd })
                    DropdownMenuItem(
                        text = { Text("Chunky Filling") },
                        onClick = { tipoAdd = "Chunky Filling"; tipo = tipoAdd })
                    DropdownMenuItem(
                        text = { Text("Cream Filling") },
                        onClick = { tipoAdd = "Cream Filling"; tipo = tipoAdd })
                    DropdownMenuItem(
                        text = { Text("Paleta Dip") },
                        onClick = { tipoAdd = "Paleta Dip"; tipo = tipoAdd })
                }
            }
        }
        Row {
            Text("Flavor: ")
            ExposedDropdownMenuBox(expanded = activado2,
                onExpandedChange = {activado2 = !activado2} ) {
                TextField(modifier = Modifier.menuAnchor(),
                    readOnly = true,
                    value = saborAdd,
                    onValueChange = {}
                )
                DropdownMenu(expanded = activado2, onDismissRequest = { activado2 = false }) {
                    DropdownMenuItem(
                        text = { Text("savory") },
                        onClick = { saborAdd = "savory"; sabor = saborAdd })
                    DropdownMenuItem(
                        text = { Text("sweet") },
                        onClick = { saborAdd = "sweet"; sabor = saborAdd })
                }
            }
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
            ExposedDropdownMenuBox(expanded = activado3,
                onExpandedChange = {activado3 = !activado3} ) {
                TextField(modifier = Modifier.menuAnchor(),
                    readOnly = true,
                    value = celebracionAdd,
                    onValueChange = {}
                )
                DropdownMenu(expanded = activado3, onDismissRequest = { activado3 = false }) {
                    DropdownMenuItem(
                        text = { Text("Standard") },
                        onClick = { celebracionAdd = "Standard"; celebracion = celebracionAdd })
                    DropdownMenuItem(
                        text = { Text("Baseball Season") },
                        onClick = { celebracionAdd = "Baseball Season"; celebracion = celebracionAdd })
                    DropdownMenuItem(
                        text = { Text("BavariaFest") },
                        onClick = { celebracionAdd = "BavariaFest"; celebracion = celebracionAdd })
                    DropdownMenuItem(
                        text = { Text("Big Top Carnival") },
                        onClick = { celebracionAdd = "Big Top Carnival"; celebracion = celebracionAdd })
                    DropdownMenuItem(
                        text = { Text("Cherry Blossom Festival") },
                        onClick = { celebracionAdd = "Cherry Blossom Festival"; celebracion = celebracionAdd })
                    DropdownMenuItem(
                        text = { Text("ChiliFest") },
                        onClick = { celebracionAdd = "ChiliFest"; celebracion = celebracionAdd })
                    DropdownMenuItem(
                        text = { Text("Christmas") },
                        onClick = { celebracionAdd = "Christmas"; celebracion = celebracionAdd })
                    DropdownMenuItem(
                        text = { Text("Cinco de Mayo") },
                        onClick = { celebracionAdd = "Cinco de Mayo"; celebracion = celebracionAdd })
                    DropdownMenuItem(
                        text = { Text("Comet Con") },
                        onClick = { celebracionAdd = "Comet Con"; celebracion = celebracionAdd })
                    DropdownMenuItem(
                        text = { Text("Day of the Dead") },
                        onClick = { celebracionAdd = "Day of the Dead"; celebracion = celebracionAdd })
                    DropdownMenuItem(
                        text = { Text("Easter") },
                        onClick = { celebracionAdd = "Easter"; celebracion = celebracionAdd })
                    DropdownMenuItem(
                        text = { Text("Film Fest") },
                        onClick = { celebracionAdd = "Film Fest"; celebracion = celebracionAdd })
                    DropdownMenuItem(
                        text = { Text("Gondola") },
                        onClick = { celebracionAdd = "Gondola"; celebracion = celebracionAdd })
                    DropdownMenuItem(
                        text = { Text("Groovstock") },
                        onClick = { celebracionAdd = "Groovstock"; celebracion = celebracionAdd })
                    DropdownMenuItem(
                        text = { Text("Halloween") },
                        onClick = { celebracionAdd = "Halloween"; celebracion = celebracionAdd })
                    DropdownMenuItem(
                        text = { Text("Holi") },
                        onClick = { celebracionAdd = "Holi"; celebracion = celebracionAdd })
                    DropdownMenuItem(
                        text = { Text("Lucky Lucky Matsuri") },
                        onClick = { celebracionAdd = "Lucky Lucky Matsuri"; celebracion = celebracionAdd })
                    DropdownMenuItem(
                        text = { Text("Lunar New Year") },
                        onClick = { celebracionAdd = "Lunar New Year"; celebracion = celebracionAdd })
                    DropdownMenuItem(
                        text = { Text("Maple Mornings") },
                        onClick = { celebracionAdd = "Maple Mornings"; celebracion = celebracionAdd })
                    DropdownMenuItem(
                        text = { Text("Mardi Gras") },
                        onClick = { celebracionAdd = "Mardi Gras"; celebracion = celebracionAdd })
                    DropdownMenuItem(
                        text = { Text("Neptune's Feast") },
                        onClick = { celebracionAdd = "Neptune's Feast"; celebracion = celebracionAdd })
                    DropdownMenuItem(
                        text = { Text("New Year") },
                        onClick = { celebracionAdd = "New Year"; celebracion = celebracionAdd })
                    DropdownMenuItem(
                        text = { Text("Onionfest") },
                        onClick = { celebracionAdd = "Onionfest"; celebracion = celebracionAdd })
                    DropdownMenuItem(
                        text = { Text("Pirate Bash") },
                        onClick = { celebracionAdd = "Pirate Bash"; celebracion = celebracionAdd })
                    DropdownMenuItem(
                        text = { Text("Portallini Feast") },
                        onClick = { celebracionAdd = "Portallini Feast"; celebracion = celebracionAdd })
                    DropdownMenuItem(
                        text = { Text("Romano Wedding") },
                        onClick = { celebracionAdd = "Romano Wedding"; celebracion = celebracionAdd })
                    DropdownMenuItem(
                        text = { Text("Sky Ninja Returns") },
                        onClick = { celebracionAdd = "Sky Ninja Returns"; celebracion = celebracionAdd })
                    DropdownMenuItem(
                        text = { Text("St Paddy") },
                        onClick = { celebracionAdd = "St Paddy"; celebracion = celebracionAdd })
                    DropdownMenuItem(
                        text = { Text("Starlight") },
                        onClick = { celebracionAdd = "Starlight"; celebracion = celebracionAdd })
                    DropdownMenuItem(
                        text = { Text("Summer Luau") },
                        onClick = { celebracionAdd = "Summer Luau"; celebracion = celebracionAdd })
                    DropdownMenuItem(
                        text = { Text("Thanksgiving") },
                        onClick = { celebracionAdd = "Thanksgiving"; celebracion = celebracionAdd })
                    DropdownMenuItem(
                        text = { Text("Valentines") },
                        onClick = { celebracionAdd = "Valentines"; celebracion = celebracionAdd })
                    DropdownMenuItem(
                        text = { Text("Volcano Gala") },
                        onClick = { celebracionAdd = "Volcano Gala"; celebracion = celebracionAdd })
                }
            }
        }
        Row {
            Text("Food: ")
            ExposedDropdownMenuBox(expanded = activado4,
                onExpandedChange = {activado4 = !activado4} ) {
                TextField(modifier = Modifier.menuAnchor(),
                    readOnly = true,
                    value = comidaAdd,
                    onValueChange = {}
                )
                DropdownMenu(expanded = activado4, onDismissRequest = { activado4 = false }) {
                    DropdownMenuItem(
                        text = { Text("Pizza") },
                        onClick = { comidaAdd = "Pizza"; comida2 = comidaAdd })
                    DropdownMenuItem(
                        text = { Text("Burger") },
                        onClick = { comidaAdd = "Burger"; comida2 = comidaAdd })
                    DropdownMenuItem(
                        text = { Text("Taco") },
                        onClick = { comidaAdd = "Taco"; comida2 = comidaAdd })
                    DropdownMenuItem(
                        text = { Text("Sundae") },
                        onClick = { comidaAdd = "Sundae"; comida2 = comidaAdd })
                    DropdownMenuItem(
                        text = { Text("Breakfast") },
                        onClick = { comidaAdd = "Breakfast"; comida2 = comidaAdd })
                    DropdownMenuItem(
                        text = { Text("Chicken Wings") },
                        onClick = { comidaAdd = "Chicken Wings"; comida2 = comidaAdd })
                    DropdownMenuItem(
                        text = { Text("Hot Dog") },
                        onClick = { comidaAdd = "Hot Dog"; comida2 = comidaAdd })
                    DropdownMenuItem(
                        text = { Text("Cupcakes") },
                        onClick = { comidaAdd = "Cupcakes"; comida2 = comidaAdd })
                    DropdownMenuItem(
                        text = { Text("Pasta") },
                        onClick = { comidaAdd = "Pasta"; comida2 = comidaAdd })
                    DropdownMenuItem(
                        text = { Text("Donuts") },
                        onClick = { comidaAdd = "Donuts"; comida2 = comidaAdd })
                    DropdownMenuItem(
                        text = { Text("Sandwich") },
                        onClick = { comidaAdd = "Sandwich"; comida2 = comidaAdd })
                    DropdownMenuItem(
                        text = { Text("Pie") },
                        onClick = { comidaAdd = "Pie"; comida2 = comidaAdd })
                    DropdownMenuItem(
                        text = { Text("Sushi") },
                        onClick = { comidaAdd = "Sushi"; comida2 = comidaAdd })
                    DropdownMenuItem(
                        text = { Text("Cookie Sundae") },
                        onClick = { comidaAdd = "Cookie Sundae"; comida2 = comidaAdd })
                    DropdownMenuItem(
                        text = { Text("Mocha") },
                        onClick = { comidaAdd = "Mocha"; comida2 = comidaAdd })
                    DropdownMenuItem(
                        text = { Text("Chicken Sandwich") },
                        onClick = { comidaAdd = "Chicken Sandwich"; comida2 = comidaAdd })
                    DropdownMenuItem(
                        text = { Text("Paleta") },
                        onClick = { comidaAdd = "Paleta"; comida2 = comidaAdd })
                }
            }
        }
        Row {
            Text("Game: ")
            ExposedDropdownMenuBox(expanded = activado5,
                onExpandedChange = {activado5 = !activado5} ) {
                TextField(modifier = Modifier.menuAnchor(),
                    readOnly = true,
                    value = saborAdd,
                    onValueChange = {}
                )
                DropdownMenu(expanded = activado5, onDismissRequest = { activado5 = false }) {
                    DropdownMenuItem(
                        text = { Text("Pizzeria") },
                        onClick = { juegoAdd = "Pizzeria"; juego3 = juegoAdd })
                    DropdownMenuItem(
                        text = { Text("Pizzeria To Go") },
                        onClick = { juegoAdd = "Pizzeria To Go"; juego3 = juegoAdd })
                    DropdownMenuItem(
                        text = { Text("Pizzeria HD") },
                        onClick = { juegoAdd = "Pizzeria HD"; juego3 = juegoAdd })
                    DropdownMenuItem(
                        text = { Text("Pizzeria Deluxe") },
                        onClick = { juegoAdd = "Pizzeria Deluxe"; juego3 = juegoAdd })
                    DropdownMenuItem(
                        text = { Text("Burgeria") },
                        onClick = { juegoAdd = "Burgeria"; juego3 = juegoAdd })
                    DropdownMenuItem(
                        text = { Text("Burgeria HD") },
                        onClick = { juegoAdd = "Burgeria HD"; juego3 = juegoAdd })
                    DropdownMenuItem(
                        text = { Text("Burgeria To Go") },
                        onClick = { juegoAdd = "Burgeria To Go"; juego3 = juegoAdd })
                    DropdownMenuItem(
                        text = { Text("Taco Mia") },
                        onClick = { juegoAdd = "Taco Mia"; juego3 = juegoAdd })
                    DropdownMenuItem(
                        text = { Text("Taco Mia HD") },
                        onClick = { juegoAdd = "Taco Mia HD"; juego3 = juegoAdd })
                    DropdownMenuItem(
                        text = { Text("Taco Mia To Go") },
                        onClick = { juegoAdd = "Taco Mia To Go"; juego3 = juegoAdd })
                    DropdownMenuItem(
                        text = { Text("Freezeria") },
                        onClick = { juegoAdd = "Freezeria"; juego3 = juegoAdd })
                    DropdownMenuItem(
                        text = { Text("Freezeria HD") },
                        onClick = { juegoAdd = "Freezeria HD"; juego3 = juegoAdd })
                    DropdownMenuItem(
                        text = { Text("Freezeria To Go") },
                        onClick = { juegoAdd = "Freezeria To Go"; juego3 = juegoAdd })
                    DropdownMenuItem(
                        text = { Text("Freezeria Deluxe") },
                        onClick = { juegoAdd = "Freezeria Deluxe"; juego3 = juegoAdd })
                    DropdownMenuItem(
                        text = { Text("Pancakeria") },
                        onClick = { juegoAdd = "Pancakeria"; juego3 = juegoAdd })
                    DropdownMenuItem(
                        text = { Text("Pancakeria HD") },
                        onClick = { juegoAdd = "Pancakeria HD"; juego3 = juegoAdd })
                    DropdownMenuItem(
                        text = { Text("Pancakeria To Go") },
                        onClick = { juegoAdd = "Pancakeria To Go"; juego3 = juegoAdd })
                    DropdownMenuItem(
                        text = { Text("Wingeria") },
                        onClick = { juegoAdd = "Wingeria"; juego3 = juegoAdd })
                    DropdownMenuItem(
                        text = { Text("Wingeria HD") },
                        onClick = { juegoAdd = "Wingeria HD"; juego3 = juegoAdd })
                    DropdownMenuItem(
                        text = { Text("Wingeria To Go") },
                        onClick = { juegoAdd = "Wingeria To Go"; juego3 = juegoAdd })
                    DropdownMenuItem(
                        text = { Text("Hot Doggeria") },
                        onClick = { juegoAdd = "Hot Doggeria"; juego3 = juegoAdd })
                    DropdownMenuItem(
                        text = { Text("Hot Doggeria HD") },
                        onClick = { juegoAdd = "Hot Doggeria HD"; juego3 = juegoAdd })
                    DropdownMenuItem(
                        text = { Text("Hot Doggeria To Go") },
                        onClick = { juegoAdd = "Hot Doggeria To Go"; juego3 = juegoAdd })
                    DropdownMenuItem(
                        text = { Text("Cupcakeria") },
                        onClick = { juegoAdd = "Cupcakeria"; juego3 = juegoAdd })
                    DropdownMenuItem(
                        text = { Text("Cupcakeria HD") },
                        onClick = { juegoAdd = "Cupcakeria HD"; juego3 = juegoAdd })
                    DropdownMenuItem(
                        text = { Text("Cupcakeria To Go") },
                        onClick = { juegoAdd = "Cupcakeria To Go"; juego3 = juegoAdd })
                    DropdownMenuItem(
                        text = { Text("Pastaria") },
                        onClick = { juegoAdd = "Pastaria"; juego3 = juegoAdd })
                    DropdownMenuItem(
                        text = { Text("Pastaria To Go") },
                        onClick = { juegoAdd = "Pastaria To Go"; juego3 = juegoAdd })
                    DropdownMenuItem(
                        text = { Text("Donuteria") },
                        onClick = { juegoAdd = "Donuteria"; juego3 = juegoAdd })
                    DropdownMenuItem(
                        text = { Text("Donuteria To Go") },
                        onClick = { juegoAdd = "Donuteria To Go"; juego3 = juegoAdd })
                    DropdownMenuItem(
                        text = { Text("Cheeseria") },
                        onClick = { juegoAdd = "Cheeseria"; juego3 = juegoAdd })
                    DropdownMenuItem(
                        text = { Text("Cheeseria To Go") },
                        onClick = { juegoAdd = "Cheeseria To Go"; juego3 = juegoAdd })
                    DropdownMenuItem(
                        text = { Text("Bakeria") },
                        onClick = { juegoAdd = "Bakeria"; juego3 = juegoAdd })
                    DropdownMenuItem(
                        text = { Text("Bakeria To Go") },
                        onClick = { juegoAdd = "Bakeria To Go"; juego3 = juegoAdd })
                    DropdownMenuItem(
                        text = { Text("Sushiria") },
                        onClick = { juegoAdd = "Sushiria"; juego3 = juegoAdd })
                    DropdownMenuItem(
                        text = { Text("Sushiria To Go") },
                        onClick = { juegoAdd = "Sushiria To Go"; juego3 = juegoAdd })
                    DropdownMenuItem(
                        text = { Text("Scooperia") },
                        onClick = { juegoAdd = "Scooperia"; juego3 = juegoAdd })
                    DropdownMenuItem(
                        text = { Text("Scooperia To Go") },
                        onClick = { juegoAdd = "Scooperia To Go"; juego3 = juegoAdd })
                    DropdownMenuItem(
                        text = { Text("Mocharia To Go") },
                        onClick = { juegoAdd = "Mocharia To Go"; juego3 = juegoAdd })
                    DropdownMenuItem(
                        text = { Text("Cluckeria To Go") },
                        onClick = { juegoAdd = "Cluckeria To Go"; juego3 = juegoAdd })
                    DropdownMenuItem(
                        text = { Text("Paleteria To Go") },
                        onClick = { juegoAdd = "Paleteria To Go"; juego3 = juegoAdd })
                }
            }
        }
        Row {
            Text("Order Part: ")
            ExposedDropdownMenuBox(expanded = activado6,
                onExpandedChange = {activado6 = !activado6} ) {
                TextField(modifier = Modifier.menuAnchor(),
                    readOnly = true,
                    value = saborAdd,
                    onValueChange = {}
                )
                DropdownMenu(expanded = activado6, onDismissRequest = { activado6 = false }) {
                    DropdownMenuItem(
                        text = { Text("Aditive") },
                        onClick = { parteAdd = "Aditive"; parte2 = parteAdd })
                    DropdownMenuItem(
                        text = { Text("Bread") },
                        onClick = { parteAdd = "Bread"; parte2 = parteAdd })
                    DropdownMenuItem(
                        text = { Text("Breading") },
                        onClick = { parteAdd = "Breading"; parte2 = parteAdd })
                    DropdownMenuItem(
                        text = { Text("Breakfast Food") },
                        onClick = { parteAdd = "Breakfast Food"; parte2 = parteAdd })
                    DropdownMenuItem(
                        text = { Text("Breakfast Mixable") },
                        onClick = { parteAdd = "Breakfast Mixable"; parte2 = parteAdd })
                    DropdownMenuItem(
                        text = { Text("Breakfast Topping") },
                        onClick = { parteAdd = "Breakfast Topping"; parte2 = parteAdd })
                    DropdownMenuItem(
                        text = { Text("Bubble Tea") },
                        onClick = { parteAdd = "Bubble Tea"; parte2 = parteAdd })
                    DropdownMenuItem(
                        text = { Text("Burger Meat") },
                        onClick = { parteAdd = "Burger Meat"; parte2 = parteAdd })
                    DropdownMenuItem(
                        text = { Text("Burger Topping") },
                        onClick = { parteAdd = "Burger Topping"; parte2 = parteAdd })
                    DropdownMenuItem(
                        text = { Text("Cake") },
                        onClick = { parteAdd = "Cake"; parte2 = parteAdd })
                    DropdownMenuItem(
                        text = { Text("Cannoli Shell") },
                        onClick = { parteAdd = "Cannoli Shell"; parte2 = parteAdd })
                    DropdownMenuItem(
                        text = { Text("Cannoli Topping") },
                        onClick = { parteAdd = "Cannoli Topping"; parte2 = parteAdd })
                    DropdownMenuItem(
                        text = { Text("Chicken Sandwich Bun") },
                        onClick = { parteAdd = "Chicken Sandwich Bun"; parte2 = parteAdd })
                    DropdownMenuItem(
                        text = { Text("Chicken Sandwich Meat") },
                        onClick = { parteAdd = "Chicken Sandwich Meat"; parte2 = parteAdd })
                    DropdownMenuItem(
                        text = { Text("Chicken Sandwich Topping") },
                        onClick = { parteAdd = "Chicken Sandwich Topping"; parte2 = parteAdd })
                    DropdownMenuItem(
                        text = { Text("Chicken Sauce") },
                        onClick = { parteAdd = "Chicken Sauce"; parte2 = parteAdd })
                    DropdownMenuItem(
                        text = { Text("Chicken Wings") },
                        onClick = { parteAdd = "Chicken Wings"; parte2 = parteAdd })
                    DropdownMenuItem(
                        text = { Text("Coffee") },
                        onClick = { parteAdd = "Coffee"; parte2 = parteAdd })
                    DropdownMenuItem(
                        text = { Text("Cookie Dough") },
                        onClick = { parteAdd = "Cookie Dough"; parte2 = parteAdd })
                    DropdownMenuItem(
                        text = { Text("Cookie Mixable") },
                        onClick = { parteAdd = "Cookie Mixable"; parte2 = parteAdd })
                    DropdownMenuItem(
                        text = { Text("Cookie Sundae Long Topper") },
                        onClick = { parteAdd = "Cookie Sundae Long Topper"; parte2 = parteAdd })
                    DropdownMenuItem(
                        text = { Text("Cookie Sundae Topper") },
                        onClick = { parteAdd = "Cookie Sundae Topper"; parte2 = parteAdd })
                    DropdownMenuItem(
                        text = { Text("Cookie Sundae Topping") },
                        onClick = { parteAdd = "Cookie Sundae Topping"; parte2 = parteAdd })
                    DropdownMenuItem(
                        text = { Text("Cream") },
                        onClick = { parteAdd = "Cream"; parte2 = parteAdd })
                    DropdownMenuItem(
                        text = { Text("Cream Dollop") },
                        onClick = { parteAdd = "Cream Dollop"; parte2 = parteAdd })
                    DropdownMenuItem(
                        text = { Text("Cupcake Topper") },
                        onClick = { parteAdd = "Cupcake Topper"; parte2 = parteAdd })
                    DropdownMenuItem(
                        text = { Text("Cupcake Topping") },
                        onClick = { parteAdd = "Cupcake Topping"; parte2 = parteAdd })
                    DropdownMenuItem(
                        text = { Text("Dip") },
                        onClick = { parteAdd = "Dip"; parte2 = parteAdd })
                    DropdownMenuItem(
                        text = { Text("Donut Cutter") },
                        onClick = { parteAdd = "Donut Cutter"; parte2 = parteAdd })
                    DropdownMenuItem(
                        text = { Text("Donut Dough") },
                        onClick = { parteAdd = "Donut Dough"; parte2 = parteAdd })
                    DropdownMenuItem(
                        text = { Text("Donut Filling") },
                        onClick = { parteAdd = "Donut Filling"; parte2 = parteAdd })
                    DropdownMenuItem(
                        text = { Text("Donut Topping") },
                        onClick = { parteAdd = "Donut Topping"; parte2 = parteAdd })
                    DropdownMenuItem(
                        text = { Text("Drink") },
                        onClick = { parteAdd = "Drink"; parte2 = parteAdd })
                    DropdownMenuItem(
                        text = { Text("Fries") },
                        onClick = { parteAdd = "Fries"; parte2 = parteAdd })
                    DropdownMenuItem(
                        text = { Text("Frosting") },
                        onClick = { parteAdd = "Frosting"; parte2 = parteAdd })
                    DropdownMenuItem(
                        text = { Text("Fry Topping") },
                        onClick = { parteAdd = "Fry Topping"; parte2 = parteAdd })
                    DropdownMenuItem(
                        text = { Text("Hot Dog Bun") },
                        onClick = { parteAdd = "Hot Dog Bun"; parte2 = parteAdd })
                    DropdownMenuItem(
                        text = { Text("Hot Dog Long Topper") },
                        onClick = { parteAdd = "Hot Dog Long Topper"; parte2 = parteAdd })
                    DropdownMenuItem(
                        text = { Text("Hot Dog Topper") },
                        onClick = { parteAdd = "Hot Dog Topper"; parte2 = parteAdd })
                    DropdownMenuItem(
                        text = { Text("Hot Dog Topping") },
                        onClick = { parteAdd = "Hot Dog Topping"; parte2 = parteAdd })
                    DropdownMenuItem(
                        text = { Text("Ice") },
                        onClick = { parteAdd = "Ice"; parte2 = parteAdd })
                    DropdownMenuItem(
                        text = { Text("Ice Cream") },
                        onClick = { parteAdd = "Ice Cream"; parte2 = parteAdd })
                    DropdownMenuItem(
                        text = { Text("Icing") },
                        onClick = { parteAdd = "Icing"; parte2 = parteAdd })
                    DropdownMenuItem(
                        text = { Text("Milk") },
                        onClick = { parteAdd = "Milk"; parte2 = parteAdd })
                    DropdownMenuItem(
                        text = { Text("Mixable") },
                        onClick = { parteAdd = "Mixable"; parte2 = parteAdd })
                    DropdownMenuItem(
                        text = { Text("Mixable Syrup") },
                        onClick = { parteAdd = "Mixable Syrup"; parte2 = parteAdd })
                    DropdownMenuItem(
                        text = { Text("Mocha Syrup") },
                        onClick = { parteAdd = "Mocha Syrup"; parte2 = parteAdd })
                    DropdownMenuItem(
                        text = { Text("Mocha Topper") },
                        onClick = { parteAdd = "Mocha Topper"; parte2 = parteAdd })
                    DropdownMenuItem(
                        text = { Text("Mocha Topping") },
                        onClick = { parteAdd = "Mocha Topping"; parte2 = parteAdd })
                    DropdownMenuItem(
                        text = { Text("Nacho Chips") },
                        onClick = { parteAdd = "Nacho Chips"; parte2 = parteAdd })
                    DropdownMenuItem(
                        text = { Text("Nacho Dip") },
                        onClick = { parteAdd = "Nacho Dip"; parte2 = parteAdd })
                    DropdownMenuItem(
                        text = { Text("Paleta Filling") },
                        onClick = { parteAdd = "Paleta Filling"; parte2 = parteAdd })
                    DropdownMenuItem(
                        text = { Text("Paleta Shape") },
                        onClick = { parteAdd = "Paleta Shape"; parte2 = parteAdd })
                    DropdownMenuItem(
                        text = { Text("Paleta Topping") },
                        onClick = { parteAdd = "Paleta Topping"; parte2 = parteAdd })
                    DropdownMenuItem(
                        text = { Text("Pasta") },
                        onClick = { parteAdd = "Pasta"; parte2 = parteAdd })
                    DropdownMenuItem(
                        text = { Text("Pasta Sauce") },
                        onClick = { parteAdd = "Pasta Sauce"; parte2 = parteAdd })
                    DropdownMenuItem(
                        text = { Text("Pasta Topping") },
                        onClick = { parteAdd = "Pasta Topping"; parte2 = parteAdd })
                    DropdownMenuItem(
                        text = { Text("Pie Crust") },
                        onClick = { parteAdd = "Pie Crust"; parte2 = parteAdd })
                    DropdownMenuItem(
                        text = { Text("Pie Filling") },
                        onClick = { parteAdd = "Pie Filling"; parte2 = parteAdd })
                    DropdownMenuItem(
                        text = { Text("Pie Topping") },
                        onClick = { parteAdd = "Pie Topping"; parte2 = parteAdd })
                    DropdownMenuItem(
                        text = { Text("Pizza Cheese") },
                        onClick = { parteAdd = "Pizza Cheese"; parte2 = parteAdd })
                    DropdownMenuItem(
                        text = { Text("Pizza Crust") },
                        onClick = { parteAdd = "Pizza Crust"; parte2 = parteAdd })
                    DropdownMenuItem(
                        text = { Text("Pizza Sauce") },
                        onClick = { parteAdd = "Pizza Sauce"; parte2 = parteAdd })
                    DropdownMenuItem(
                        text = { Text("Pizza Topping") },
                        onClick = { parteAdd = "Pizza Topping"; parte2 = parteAdd })
                    DropdownMenuItem(
                        text = { Text("Popcorn") },
                        onClick = { parteAdd = "Popcorn"; parte2 = parteAdd })
                    DropdownMenuItem(
                        text = { Text("Rice") },
                        onClick = { parteAdd = "Rice"; parte2 = parteAdd })
                    DropdownMenuItem(
                        text = { Text("Sandwich Bread") },
                        onClick = { parteAdd = "Sandwich Bread"; parte2 = parteAdd })
                    DropdownMenuItem(
                        text = { Text("Sandwich Cheese") },
                        onClick = { parteAdd = "Sandwich Cheese"; parte2 = parteAdd })
                    DropdownMenuItem(
                        text = { Text("Sandwich Topping") },
                        onClick = { parteAdd = "Sandwich Topping"; parte2 = parteAdd })
                    DropdownMenuItem(
                        text = { Text("Sausage") },
                        onClick = { parteAdd = "Sausage"; parte2 = parteAdd })
                    DropdownMenuItem(
                        text = { Text("Side") },
                        onClick = { parteAdd = "Side"; parte2 = parteAdd })
                    DropdownMenuItem(
                        text = { Text("Slush") },
                        onClick = { parteAdd = "Slush"; parte2 = parteAdd })
                    DropdownMenuItem(
                        text = { Text("Soda") },
                        onClick = { parteAdd = "Soda"; parte2 = parteAdd })
                    DropdownMenuItem(
                        text = { Text("Soy Paper") },
                        onClick = { parteAdd = "Soy Paper"; parte2 = parteAdd })
                    DropdownMenuItem(
                        text = { Text("Sundae Topper") },
                        onClick = { parteAdd = "Sundae Topper"; parte2 = parteAdd })
                    DropdownMenuItem(
                        text = { Text("Sundae Topping") },
                        onClick = { parteAdd = "Sundae Topping"; parte2 = parteAdd })
                    DropdownMenuItem(
                        text = { Text("Sushi Filling") },
                        onClick = { parteAdd = "Sushi Filling"; parte2 = parteAdd })
                    DropdownMenuItem(
                        text = { Text("Sushi Topping") },
                        onClick = { parteAdd = "Sushi Topping"; parte2 = parteAdd })
                    DropdownMenuItem(
                        text = { Text("Taco Meat") },
                        onClick = { parteAdd = "Taco Meat"; parte2 = parteAdd })
                    DropdownMenuItem(
                        text = { Text("Taco Shell") },
                        onClick = { parteAdd = "Taco Shell"; parte2 = parteAdd })
                    DropdownMenuItem(
                        text = { Text("Taco Topping") },
                        onClick = { parteAdd = "Taco Topping"; parte2 = parteAdd })
                    DropdownMenuItem(
                        text = { Text("Tea Bubbles") },
                        onClick = { parteAdd = "Tea Bubbles"; parte2 = parteAdd })
                    DropdownMenuItem(
                        text = { Text("Top Crust") },
                        onClick = { parteAdd = "Top Crust"; parte2 = parteAdd })
                }
            }
        }
    }
}