package com.example.bdingredientes.ui.theme

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaAñadir() {
    var nombre by remember { mutableStateOf("")}
    var tipo by remember { mutableStateOf("")}
    var sabor by remember { mutableStateOf("")}
    var deCelebracion by remember { mutableStateOf(false)}
    var celebracion by remember { mutableStateOf("")}
    var activadoMenu by remember { mutableStateOf(false) }
    Column{
        Row{
            TextField(value = nombre, onValueChange = {texto -> nombre = texto}, placeholder = { Text("Introduce el nombre del ingrediente") })
            DropdownMenu(expanded = activadoMenu, onDismissRequest = { activadoMenu = false }) {
                DropdownMenuItem(
                    text = {  Text("Aditive") },
                    onClick = { tipo = "Aditive" })
                DropdownMenuItem(
                    text = {  Text("Bread") },
                    onClick = { tipo = "Bread" })
                DropdownMenuItem(
                    text = {  Text("Breading") },
                    onClick = { tipo = "Breading" })
                DropdownMenuItem(
                    text = {  Text("Breakfast Food") },
                    onClick = { tipo = "Breakfast Food" })
                DropdownMenuItem(
                    text = {  Text("Breakfast Mixable") },
                    onClick = { tipo = "Breakfast Mixable" })
                DropdownMenuItem(
                    text = {  Text("Bubble Tea") },
                    onClick = { tipo = "Bubble Tea" })
                DropdownMenuItem(
                    text = {  Text("Burger Bun") },
                    onClick = { tipo = "Burger Bun" })
                DropdownMenuItem(
                    text = {  Text("Burger Meat") },
                    onClick = { tipo = "Burger Meat" })
                DropdownMenuItem(
                    text = {  Text("Cake") },
                    onClick = { tipo = "Cake" })
                DropdownMenuItem(
                    text = {  Text("Cannoli Shell") },
                    onClick = { tipo = "Cannoli Shell" })
                DropdownMenuItem(
                    text = {  Text("Chicken Sauce") },
                    onClick = { tipo = "Chicken Sauce" })
                DropdownMenuItem(
                    text = {  Text("Chicken Wings") },
                    onClick = { tipo = "Chicken Wings" })
                DropdownMenuItem(
                    text = {  Text("Coffee") },
                    onClick = { tipo = "Coffee" })
                DropdownMenuItem(
                    text = {  Text("Cookie Dough") },
                    onClick = { tipo = "Cookie Dough" })
                DropdownMenuItem(
                    text = {  Text("Cookie Mixable") },
                    onClick = { tipo = "Cookie Mixable" })
                DropdownMenuItem(
                    text = {  Text("Cream") },
                    onClick = { tipo = "Cream" })
                DropdownMenuItem(
                    text = {  Text("Cream Dollop") },
                    onClick = { tipo = "Cream Dollop" })
                DropdownMenuItem(
                    text = {  Text("Dip") },
                    onClick = { tipo = "Dip" })
                DropdownMenuItem(
                    text = {  Text("Donut Cutter") },
                    onClick = { tipo = "Donut Cutter" })
                DropdownMenuItem(
                    text = {  Text("Donut Dough") },
                    onClick = { tipo = "Donut Dough" })
                DropdownMenuItem(
                    text = {  Text("Donut Filling") },
                    onClick = { tipo = "Donut Filling" })
                DropdownMenuItem(
                    text = {  Text("Drink") },
                    onClick = { tipo = "Drink" })
                DropdownMenuItem(
                    text = {  Text("Flat Topper") },
                    onClick = { tipo = "Flat Topper" })
                DropdownMenuItem(
                    text = {  Text("Fries") },
                    onClick = { tipo = "Fries" })
                DropdownMenuItem(
                    text = {  Text("Frosting") },
                    onClick = { tipo = "Frosting" })
                DropdownMenuItem(
                    text = {  Text("Fry Topping") },
                    onClick = { tipo = "Fry Topping" })
                DropdownMenuItem(
                    text = {  Text("Hot Dog Bun") },
                    onClick = { tipo = "Hot Dog Bun" })
                DropdownMenuItem(
                    text = {  Text("Ice") },
                    onClick = { tipo = "Ice" })
                DropdownMenuItem(
                    text = {  Text("Ice Cream") },
                    onClick = { tipo = "Ice Cream" })
                DropdownMenuItem(
                    text = {  Text("Long Topper") },
                    onClick = { tipo = "Long Topper" })
                DropdownMenuItem(
                    text = {  Text("Milk") },
                    onClick = { tipo = "Milk" })
                DropdownMenuItem(
                    text = {  Text("Mixable") },
                    onClick = { tipo = "Mixable" })
                DropdownMenuItem(
                    text = {  Text("Mixable Syrup") },
                    onClick = { tipo = "Mixable Syrup" })
                DropdownMenuItem(
                    text = {  Text("Nacho Chips") },
                    onClick = { tipo = "Nacho Chips" })
                DropdownMenuItem(
                    text = {  Text("Nacho Dip") },
                    onClick = { tipo = "Nacho Dip" })
                DropdownMenuItem(
                    text = {  Text("Pasta") },
                    onClick = { tipo = "Pasta" })
                DropdownMenuItem(
                    text = {  Text("Pasta Sauce") },
                    onClick = { tipo = "Pasta Sauce" })
                DropdownMenuItem(
                    text = {  Text("Pie Crust") },
                    onClick = { tipo = "Pie Crust" })
                DropdownMenuItem(
                    text = {  Text("Pie Filling") },
                    onClick = { tipo = "Pie Filling" })
                DropdownMenuItem(
                    text = {  Text("Pizza Cheese") },
                    onClick = { tipo = "Pizza Cheese" })
                DropdownMenuItem(
                    text = {  Text("Pizza Crust") },
                    onClick = { tipo = "Pizza Crust" })
                DropdownMenuItem(
                    text = {  Text("Pizza Sauce") },
                    onClick = { tipo = "Pizza Sauce" })
                DropdownMenuItem(
                    text = {  Text("Popcorn") },
                    onClick = { tipo = "Popcorn" })
                DropdownMenuItem(
                    text = {  Text("Powder") },
                    onClick = { tipo = "Powder" })
                DropdownMenuItem(
                    text = {  Text("Rice") },
                    onClick = { tipo = "Rice" })
                DropdownMenuItem(
                    text = {  Text("Sandwich Bread") },
                    onClick = { tipo = "Sandwich Bread" })
                DropdownMenuItem(
                    text = {  Text("Sandwich Cheese") },
                    onClick = { tipo = "Sandwich Cheese" })
                DropdownMenuItem(
                    text = {  Text("Sauce") },
                    onClick = { tipo = "Sauce" })
                DropdownMenuItem(
                    text = {  Text("Sausage") },
                    onClick = { tipo = "Sausage" })
                DropdownMenuItem(
                    text = {  Text("Shaker") },
                    onClick = { tipo = "Shaker" })
                DropdownMenuItem(
                    text = {  Text("Side") },
                    onClick = { tipo = "Side" })
                DropdownMenuItem(
                    text = {  Text("Slush") },
                    onClick = { tipo = "Slush" })
                DropdownMenuItem(
                    text = {  Text("Soda") },
                    onClick = { tipo = "Soda" })
                DropdownMenuItem(
                    text = {  Text("Soy Paper") },
                    onClick = { tipo = "Soy Paper" })
                DropdownMenuItem(
                    text = {  Text("Sushi Filling") },
                    onClick = { tipo = "Sushi Filling" })
                DropdownMenuItem(
                    text = {  Text("Syrup") },
                    onClick = { tipo = "Syrup" })
                DropdownMenuItem(
                    text = {  Text("Taco Meat") },
                    onClick = { tipo = "Taco Meat" })
                DropdownMenuItem(
                    text = {  Text("Taco Shell") },
                    onClick = { tipo = "Taco Shell" })
                DropdownMenuItem(
                    text = {  Text("Tea Bubbles") },
                    onClick = { tipo = "Tea Bubbles" })
                DropdownMenuItem(
                    text = {  Text("Top Crust") },
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
}