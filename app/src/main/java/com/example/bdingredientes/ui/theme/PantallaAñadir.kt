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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaAñadir() {
    var nombre by remember { mutableStateOf("")}
    var tipo by remember { mutableStateOf("Topping")}
    var sabor by remember { mutableStateOf("sweet")}
    var deCelebracion by remember { mutableStateOf(false)}
    var celebracion by remember { mutableStateOf("Standard")}
    var activado by remember { mutableStateOf(false) }
    var activado2 by remember { mutableStateOf(false) }
    var activado3 by remember { mutableStateOf(false) }

    Column {
        Row {
            Text ("Name: ")
            TextField(
                value = nombre,
                onValueChange = { texto -> nombre = texto },
                placeholder = { Text("Insert the name of the ingredient") })
        }
        Row {
            Text ("Type: ")
            ExposedDropdownMenuBox(expanded = activado,
                onExpandedChange = {activado = !activado} ) {
                TextField(modifier = Modifier.menuAnchor(),
                    readOnly = true,
                    value = tipo,
                    onValueChange = {}
                )
                DropdownMenu(expanded = activado, onDismissRequest = { activado = false }) {
                    DropdownMenuItem(
                        text = { Text("Aditive") },
                        onClick = { tipo = "Aditive" })
                    DropdownMenuItem(
                        text = { Text("Bread") },
                        onClick = { tipo = "Bread" })
                    DropdownMenuItem(
                        text = { Text("Breading") },
                        onClick = { tipo = "Breading" })
                    DropdownMenuItem(
                        text = { Text("Breakfast Food") },
                        onClick = { tipo = "Breakfast Food" })
                    DropdownMenuItem(
                        text = { Text("Breakfast Mixable") },
                        onClick = { tipo = "Breakfast Mixable" })
                    DropdownMenuItem(
                        text = { Text("Bubble Tea") },
                        onClick = { tipo = "Bubble Tea" })
                    DropdownMenuItem(
                        text = { Text("Burger Bun") },
                        onClick = { tipo = "Burger Bun" })
                    DropdownMenuItem(
                        text = { Text("Burger Meat") },
                        onClick = { tipo = "Burger Meat" })
                    DropdownMenuItem(
                        text = { Text("Cake") },
                        onClick = { tipo = "Cake" })
                    DropdownMenuItem(
                        text = { Text("Cannoli Shell") },
                        onClick = { tipo = "Cannoli Shell" })
                    DropdownMenuItem(
                        text = { Text("Chicken Sauce") },
                        onClick = { tipo = "Chicken Sauce" })
                    DropdownMenuItem(
                        text = { Text("Chicken Wings") },
                        onClick = { tipo = "Chicken Wings" })
                    DropdownMenuItem(
                        text = { Text("Coffee") },
                        onClick = { tipo = "Coffee" })
                    DropdownMenuItem(
                        text = { Text("Cookie Dough") },
                        onClick = { tipo = "Cookie Dough" })
                    DropdownMenuItem(
                        text = { Text("Cookie Mixable") },
                        onClick = { tipo = "Cookie Mixable" })
                    DropdownMenuItem(
                        text = { Text("Cream") },
                        onClick = { tipo = "Cream" })
                    DropdownMenuItem(
                        text = { Text("Cream Dollop") },
                        onClick = { tipo = "Cream Dollop" })
                    DropdownMenuItem(
                        text = { Text("Dip") },
                        onClick = { tipo = "Dip" })
                    DropdownMenuItem(
                        text = { Text("Donut Cutter") },
                        onClick = { tipo = "Donut Cutter" })
                    DropdownMenuItem(
                        text = { Text("Donut Dough") },
                        onClick = { tipo = "Donut Dough" })
                    DropdownMenuItem(
                        text = { Text("Donut Filling") },
                        onClick = { tipo = "Donut Filling" })
                    DropdownMenuItem(
                        text = { Text("Drink") },
                        onClick = { tipo = "Drink" })
                    DropdownMenuItem(
                        text = { Text("Flat Topper") },
                        onClick = { tipo = "Flat Topper" })
                    DropdownMenuItem(
                        text = { Text("Fries") },
                        onClick = { tipo = "Fries" })
                    DropdownMenuItem(
                        text = { Text("Frosting") },
                        onClick = { tipo = "Frosting" })
                    DropdownMenuItem(
                        text = { Text("Fry Topping") },
                        onClick = { tipo = "Fry Topping" })
                    DropdownMenuItem(
                        text = { Text("Hot Dog Bun") },
                        onClick = { tipo = "Hot Dog Bun" })
                    DropdownMenuItem(
                        text = { Text("Ice") },
                        onClick = { tipo = "Ice" })
                    DropdownMenuItem(
                        text = { Text("Ice Cream") },
                        onClick = { tipo = "Ice Cream" })
                    DropdownMenuItem(
                        text = { Text("Long Topper") },
                        onClick = { tipo = "Long Topper" })
                    DropdownMenuItem(
                        text = { Text("Milk") },
                        onClick = { tipo = "Milk" })
                    DropdownMenuItem(
                        text = { Text("Mixable") },
                        onClick = { tipo = "Mixable" })
                    DropdownMenuItem(
                        text = { Text("Mixable Syrup") },
                        onClick = { tipo = "Mixable Syrup" })
                    DropdownMenuItem(
                        text = { Text("Nacho Chips") },
                        onClick = { tipo = "Nacho Chips" })
                    DropdownMenuItem(
                        text = { Text("Nacho Dip") },
                        onClick = { tipo = "Nacho Dip" })
                    DropdownMenuItem(
                        text = { Text("Pasta") },
                        onClick = { tipo = "Pasta" })
                    DropdownMenuItem(
                        text = { Text("Pasta Sauce") },
                        onClick = { tipo = "Pasta Sauce" })
                    DropdownMenuItem(
                        text = { Text("Pie Crust") },
                        onClick = { tipo = "Pie Crust" })
                    DropdownMenuItem(
                        text = { Text("Pie Filling") },
                        onClick = { tipo = "Pie Filling" })
                    DropdownMenuItem(
                        text = { Text("Pizza Cheese") },
                        onClick = { tipo = "Pizza Cheese" })
                    DropdownMenuItem(
                        text = { Text("Pizza Crust") },
                        onClick = { tipo = "Pizza Crust" })
                    DropdownMenuItem(
                        text = { Text("Pizza Sauce") },
                        onClick = { tipo = "Pizza Sauce" })
                    DropdownMenuItem(
                        text = { Text("Popcorn") },
                        onClick = { tipo = "Popcorn" })
                    DropdownMenuItem(
                        text = { Text("Powder") },
                        onClick = { tipo = "Powder" })
                    DropdownMenuItem(
                        text = { Text("Rice") },
                        onClick = { tipo = "Rice" })
                    DropdownMenuItem(
                        text = { Text("Sandwich Bread") },
                        onClick = { tipo = "Sandwich Bread" })
                    DropdownMenuItem(
                        text = { Text("Sandwich Cheese") },
                        onClick = { tipo = "Sandwich Cheese" })
                    DropdownMenuItem(
                        text = { Text("Sauce") },
                        onClick = { tipo = "Sauce" })
                    DropdownMenuItem(
                        text = { Text("Sausage") },
                        onClick = { tipo = "Sausage" })
                    DropdownMenuItem(
                        text = { Text("Shaker") },
                        onClick = { tipo = "Shaker" })
                    DropdownMenuItem(
                        text = { Text("Side") },
                        onClick = { tipo = "Side" })
                    DropdownMenuItem(
                        text = { Text("Slush") },
                        onClick = { tipo = "Slush" })
                    DropdownMenuItem(
                        text = { Text("Soda") },
                        onClick = { tipo = "Soda" })
                    DropdownMenuItem(
                        text = { Text("Soy Paper") },
                        onClick = { tipo = "Soy Paper" })
                    DropdownMenuItem(
                        text = { Text("Sushi Filling") },
                        onClick = { tipo = "Sushi Filling" })
                    DropdownMenuItem(
                        text = { Text("Syrup") },
                        onClick = { tipo = "Syrup" })
                    DropdownMenuItem(
                        text = { Text("Taco Meat") },
                        onClick = { tipo = "Taco Meat" })
                    DropdownMenuItem(
                        text = { Text("Taco Shell") },
                        onClick = { tipo = "Taco Shell" })
                    DropdownMenuItem(
                        text = { Text("Tea Bubbles") },
                        onClick = { tipo = "Tea Bubbles" })
                    DropdownMenuItem(
                        text = { Text("Top Crust") },
                        onClick = { tipo = "Top Crust" })
                    DropdownMenuItem(
                        text = { Text("Topper") },
                        onClick = { tipo = "Topper" })
                    DropdownMenuItem(
                        text = { Text("Topping") },
                        onClick = { tipo = "Topping" })
                }
            }
        }
        Row {
            Text("Flavor: ")
            ExposedDropdownMenuBox(expanded = activado2,
                onExpandedChange = {activado2 = !activado2} ) {
                TextField(modifier = Modifier.menuAnchor(),
                    readOnly = true,
                    value = sabor,
                    onValueChange = {}
                )
                DropdownMenu(expanded = activado2, onDismissRequest = { activado2 = false }) {
                    DropdownMenuItem(
                        text = { Text("savory") },
                        onClick = { sabor = "savory" })
                    DropdownMenuItem(
                        text = { Text("sweet") },
                        onClick = { sabor = "sweet" })
                }
            }
        }
        Row {
            Text("Holiday-exclusive: ")
            var seleccionado by remember { mutableStateOf(false) }
            Checkbox(checked = seleccionado, onCheckedChange = {
                seleccionado = !seleccionado; deCelebracion = seleccionado
            })
        }
        Row {
            Text("Holiday: ")
            ExposedDropdownMenuBox(expanded = activado3,
                onExpandedChange = {activado3 = !activado3} ) {
                TextField(modifier = Modifier.menuAnchor(),
                    readOnly = true,
                    value = celebracion,
                    onValueChange = {}
                )
                DropdownMenu(expanded = activado3, onDismissRequest = { activado3 = false }) {
                    DropdownMenuItem(
                        text = { Text("Standard") },
                        onClick = { celebracion = "Standard" })
                    DropdownMenuItem(
                        text = { Text("Baseball Season") },
                        onClick = { celebracion = "Baseball Season" })
                    DropdownMenuItem(
                        text = { Text("BavariaFest") },
                        onClick = { celebracion = "BavariaFest" })
                    DropdownMenuItem(
                        text = { Text("Big Top Carnival") },
                        onClick = { celebracion = "Big Top Carnival" })
                    DropdownMenuItem(
                        text = { Text("Cherry Blossom Festival") },
                        onClick = { celebracion = "Cherry Blossom Festival" })
                    DropdownMenuItem(
                        text = { Text("ChiliFest") },
                        onClick = { celebracion = "ChiliFest" })
                    DropdownMenuItem(
                        text = { Text("Christmas") },
                        onClick = { celebracion = "Christmas" })
                    DropdownMenuItem(
                        text = { Text("Cinco de Mayo") },
                        onClick = { celebracion = "Cinco de Mayo" })
                    DropdownMenuItem(
                        text = { Text("Comet Con") },
                        onClick = { celebracion = "Comet Con" })
                    DropdownMenuItem(
                        text = { Text("Day of the Dead") },
                        onClick = { celebracion = "Day of the Dead" })
                    DropdownMenuItem(
                        text = { Text("Easter") },
                        onClick = { celebracion = "Easter" })
                    DropdownMenuItem(
                        text = { Text("Film Fest") },
                        onClick = { celebracion = "Film Fest" })
                    DropdownMenuItem(
                        text = { Text("Gondola") },
                        onClick = { celebracion = "Gondola" })
                    DropdownMenuItem(
                        text = { Text("Groovstock") },
                        onClick = { celebracion = "Groovstock" })
                    DropdownMenuItem(
                        text = { Text("Halloween") },
                        onClick = { celebracion = "Halloween" })
                    DropdownMenuItem(
                        text = { Text("Holi") },
                        onClick = { celebracion = "Holi" })
                    DropdownMenuItem(
                        text = { Text("Lucky Lucky Matsuri") },
                        onClick = { celebracion = "Lucky Lucky Matsuri" })
                    DropdownMenuItem(
                        text = { Text("Lunar New Year") },
                        onClick = { celebracion = "Lunar New Year" })
                    DropdownMenuItem(
                        text = { Text("Maple Mornings") },
                        onClick = { celebracion = "Maple Mornings" })
                    DropdownMenuItem(
                        text = { Text("Mardi Gras") },
                        onClick = { celebracion = "Mardi Gras" })
                    DropdownMenuItem(
                        text = { Text("Neptune's Feast") },
                        onClick = { celebracion = "Neptune's Feast" })
                    DropdownMenuItem(
                        text = { Text("New Year") },
                        onClick = { celebracion = "New Year" })
                    DropdownMenuItem(
                        text = { Text("Onionfest") },
                        onClick = { celebracion = "Onionfest" })
                    DropdownMenuItem(
                        text = { Text("Pirate Bash") },
                        onClick = { celebracion = "Pirate Bash" })
                    DropdownMenuItem(
                        text = { Text("Portallini Feast") },
                        onClick = { celebracion = "Portallini Feast" })
                    DropdownMenuItem(
                        text = { Text("Romano Wedding") },
                        onClick = { celebracion = "Romano Wedding" })
                    DropdownMenuItem(
                        text = { Text("Sky Ninja Returns") },
                        onClick = { celebracion = "Sky Ninja Returns" })
                    DropdownMenuItem(
                        text = { Text("St Paddy") },
                        onClick = { celebracion = "St Paddy" })
                    DropdownMenuItem(
                        text = { Text("Starlight") },
                        onClick = { celebracion = "Starlight" })
                    DropdownMenuItem(
                        text = { Text("Summer Luau") },
                        onClick = { celebracion = "Summer Luau" })
                    DropdownMenuItem(
                        text = { Text("Thanksgiving") },
                        onClick = { celebracion = "Thanksgiving" })
                    DropdownMenuItem(
                        text = { Text("Valentines") },
                        onClick = { celebracion = "Valentines" })
                    DropdownMenuItem(
                        text = { Text("Volcano Gala") },
                        onClick = { celebracion = "Volcano Gala" })
                }
            }
        }
    }
}