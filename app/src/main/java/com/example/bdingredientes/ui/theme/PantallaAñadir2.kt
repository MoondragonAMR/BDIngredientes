package com.example.bdingredientes.ui.theme

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bdingredientes.clases.ViewModelScaffold
import com.example.bdingredientes.clases.celebracion2
import com.example.bdingredientes.clases.comida
import com.example.bdingredientes.clases.juego2
import com.example.bdingredientes.clases.nombre2
import com.example.bdingredientes.clases.numero2
import com.example.bdingredientes.clases.parte
import com.example.bdingredientes.clases.tipo2

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaAñadir2(sf : ViewModelScaffold = viewModel()) {

    val idioma = sf.english.collectAsState().value

    var nombreAdd by remember { mutableStateOf("") }
    nombre2 = nombreAdd
    var tipoAdd by remember { mutableStateOf("Cut") }
    tipo2 = tipoAdd
    if (!idioma.value) {
        tipoAdd = "Corte"
    }
    var comidaAdd by remember { mutableStateOf("Pizza") }
    comida = comidaAdd
    var juegoAdd by remember { mutableStateOf("Pizzeria") }
    juego2 = juegoAdd
    var celebracionAdd by remember { mutableStateOf("Standard") }
    celebracion2 = celebracionAdd
    if (!idioma.value) {
        celebracionAdd = "Estándar"
    }
    var parteAdd by remember { mutableStateOf("Cut") }
    parte = parteAdd
    if (!idioma.value) {
        parteAdd = "Corte"
    }
    numero2 = 0
    var activado by remember { mutableStateOf(false) }
    var activado2 by remember { mutableStateOf(false) }
    var activado3 by remember { mutableStateOf(false) }
    var activado4 by remember { mutableStateOf(false) }
    var activado5 by remember { mutableStateOf(false) }

    Column {
        Row {
            val textoNombre = if (idioma.value) {
                "Name: "
            } else {
                "Nombre: "
            }
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
        Row {
            val textoTipo = if (idioma.value) {
                "Type: "
            } else {
                "Tipo: "
            }
            Text (textoTipo)
            ExposedDropdownMenuBox(expanded = activado,
                onExpandedChange = {activado = !activado} ) {
                TextField(modifier = Modifier.menuAnchor(),
                    readOnly = true,
                    value = tipoAdd,
                    onValueChange = {}
                )
                DropdownMenu(expanded = activado, onDismissRequest = { activado = false }) {
                    val textoTipo1 = if (idioma.value) {
                        "Alarm"
                    } else {
                        "Alarma"
                    }
                    DropdownMenuItem(
                        text = { Text(textoTipo1) },
                        onClick = { tipoAdd = textoTipo1; tipo2 = "Alarm" })
                    val textoTipo2 = if (idioma.value) {
                        "Bag Size"
                    } else {
                        "Tamaño de bolsa"
                    }
                    DropdownMenuItem(
                        text = { Text(textoTipo2) },
                        onClick = { tipoAdd = textoTipo2; tipo2 = "Bag Size" })
                    val textoTipo3 = if (idioma.value) {
                        "Cup Size"
                    } else {
                        "Tamaño de vaso"
                    }
                    DropdownMenuItem(
                        text = { Text(textoTipo3) },
                        onClick = { tipoAdd = textoTipo3; tipo2 = "Cup Size" })
                    val textoTipo4 = if (idioma.value) {
                        "Cut"
                    } else {
                        "Corte"
                    }
                    DropdownMenuItem(
                        text = { Text(textoTipo4) },
                        onClick = { tipoAdd = textoTipo4; tipo2 = "Cut" })
                    val textoTipo5 = if (idioma.value) {
                        "Liner"
                    } else {
                        "Envoltorio"
                    }
                    DropdownMenuItem(
                        text = { Text(textoTipo5) },
                        onClick = { tipoAdd = textoTipo5; tipo2 = "Liner" })
                    val textoTipo6 = if (idioma.value) {
                        "Position"
                    } else {
                        "Posición"
                    }
                    DropdownMenuItem(
                        text = { Text(textoTipo6) },
                        onClick = { tipoAdd = textoTipo6; tipo2 = "Position" })
                }
            }
        }
        val textoCelebracion = if (idioma.value) {
            "Holiday: "
        } else {
            "Celebración: "
        }
        Row {
            Text(textoCelebracion)
            ExposedDropdownMenuBox(expanded = activado2,
                onExpandedChange = {activado2 = !activado2} ) {
                TextField(modifier = Modifier.menuAnchor(),
                    readOnly = true,
                    value = celebracionAdd,
                    onValueChange = {}
                )
                DropdownMenu(expanded = activado2, onDismissRequest = { activado2 = false }) {
                    val textoCelebracion1 = if (idioma.value) {
                        "Standard"
                    } else {
                        "Estándar"
                    }
                    DropdownMenuItem(
                        text = { Text(textoCelebracion1) },
                        onClick = { celebracionAdd = textoCelebracion1; celebracion2 = "Standard" })
                    val textoCelebracion2 = if (idioma.value) {
                        "Baseball Season"
                    } else {
                        "Temporada de baseball"
                    }
                    DropdownMenuItem(
                        text = { Text(textoCelebracion2) },
                        onClick = { celebracionAdd = textoCelebracion2; celebracion2 = "Baseball Season" })
                    val textoCelebracion3 = if (idioma.value) {
                        "Big Top Carnival"
                    } else {
                        "Gran Feria"
                    }
                    DropdownMenuItem(
                        text = { Text(textoCelebracion3) },
                        onClick = { celebracionAdd = textoCelebracion3; celebracion2 = "Big Top Carnival" })
                    val textoCelebracion4 = if (idioma.value) {
                        "Cherry Blossom Festival"
                    } else {
                        "Festival de la flor de cerezo"
                    }
                    DropdownMenuItem(
                        text = { Text(textoCelebracion4) },
                        onClick = { celebracionAdd = textoCelebracion4; celebracion2 = "Cherry Blossom Festival" })
                    val textoCelebracion5 = if (idioma.value) {
                        "Christmas"
                    } else {
                        "Navidad"
                    }
                    DropdownMenuItem(
                        text = { Text(textoCelebracion5) },
                        onClick = { celebracionAdd = textoCelebracion5; celebracion2 = "Christmas" })
                    DropdownMenuItem(
                        text = { Text("Cinco de Mayo") },
                        onClick = { celebracionAdd = "Cinco de Mayo"; celebracion2 = celebracionAdd })
                    DropdownMenuItem(
                        text = { Text("Comet Con") },
                        onClick = { celebracionAdd = "Comet Con"; celebracion2 = celebracionAdd })
                    val textoCelebracion6 = if (idioma.value) {
                        "Easter"
                    } else {
                        "Pascua"
                    }
                    DropdownMenuItem(
                        text = { Text(textoCelebracion6) },
                        onClick = { celebracionAdd = textoCelebracion6; celebracion2 = "Easter" })
                    val textoCelebracion7 = if (idioma.value) {
                        "Film Fest"
                    } else {
                        "Festival de las películas"
                    }
                    DropdownMenuItem(
                        text = { Text(textoCelebracion7) },
                        onClick = { celebracionAdd = textoCelebracion7; celebracion2 = "Film Fest" })
                    DropdownMenuItem(
                        text = { Text("Halloween") },
                        onClick = { celebracionAdd = "Halloween"; celebracion2 = celebracionAdd })
                    val textoCelebracion8 = if (idioma.value) {
                        "Maple Mornings"
                    } else {
                        "Mañanas de arce"
                    }
                    DropdownMenuItem(
                        text = { Text(textoCelebracion8) },
                        onClick = { celebracionAdd = textoCelebracion8; celebracion2 = "Maple Mornings" })
                    val textoCelebracion9 = if (idioma.value) {
                        "New Year"
                    } else {
                        "Año Nuevo"
                    }
                    DropdownMenuItem(
                        text = { Text(textoCelebracion9) },
                        onClick = { celebracionAdd = textoCelebracion9; celebracion2 = "New Year" })
                    val textoCelebracion10 = if (idioma.value) {
                        "Onionfest"
                    } else {
                        "Festival de las cebollas"
                    }
                    DropdownMenuItem(
                        text = { Text(textoCelebracion10) },
                        onClick = { celebracionAdd = textoCelebracion10; celebracion2 = "Onionfest" })
                    val textoCelebracion11 = if (idioma.value) {
                        "Pirate Bash"
                    } else {
                        "Fiesta de los piratas"
                    }
                    DropdownMenuItem(
                        text = { Text(textoCelebracion11) },
                        onClick = { celebracionAdd = textoCelebracion11; celebracion2 = "Pirate Bash" })
                    val textoCelebracion12 = if (idioma.value) {
                        "St Paddy"
                    } else {
                        "San Patricio"
                    }
                    DropdownMenuItem(
                        text = { Text(textoCelebracion12) },
                        onClick = { celebracionAdd = textoCelebracion12; celebracion2 = "St Paddy" })
                    val textoCelebracion13 = if (idioma.value) {
                        "Starlight"
                    } else {
                        "Festival de las estrellas"
                    }
                    DropdownMenuItem(
                        text = { Text(textoCelebracion13) },
                        onClick = { celebracionAdd = textoCelebracion13; celebracion2 = "Starlight" })
                    val textoCelebracion14 = if (idioma.value) {
                        "Summer Luau"
                    } else {
                        "Luau de verano"
                    }
                    DropdownMenuItem(
                        text = { Text(textoCelebracion14) },
                        onClick = { celebracionAdd = textoCelebracion14; celebracion2 = "Summer Luau" })
                    val textoCelebracion15 = if (idioma.value) {
                        "Thanksgiving"
                    } else {
                        "Acción de gracias"
                    }
                    DropdownMenuItem(
                        text = { Text(textoCelebracion15) },
                        onClick = { celebracionAdd = textoCelebracion15; celebracion2 = "Thanksgiving" })
                    val textoCelebracion16 = if (idioma.value) {
                        "Valentines"
                    } else {
                        "San Valentín"
                    }
                    DropdownMenuItem(
                        text = { Text(textoCelebracion16) },
                        onClick = { celebracionAdd = textoCelebracion16; celebracion2 = "Valentines" })
                }
            }
        }
        val textoComida = if (idioma.value) {
            "Food: "
        } else {
            "Comida: "
        }
        Row {
            Text(textoComida)
            ExposedDropdownMenuBox(expanded = activado3,
                onExpandedChange = {activado3 = !activado3} ) {
                TextField(modifier = Modifier.menuAnchor(),
                    readOnly = true,
                    value = comidaAdd,
                    onValueChange = {}
                )
                DropdownMenu(expanded = activado3, onDismissRequest = { activado3 = false }) {
                    val textoComida1 = if (idioma.value) {
                        "Breakfast"
                    } else {
                        "Desayuno"
                    }
                    DropdownMenuItem(
                        text = { Text(textoComida1) },
                        onClick = { comidaAdd = textoComida1; comida = "Breakfast" })
                    val textoComida2 = if (idioma.value) {
                        "Burger"
                    } else {
                        "Hamburguesa"
                    }
                    DropdownMenuItem(
                        text = { Text(textoComida2) },
                        onClick = { comidaAdd = textoComida2; comida = "Burger" })
                    val textoComida3 = if (idioma.value) {
                        "Chicken Sandwich"
                    } else {
                        "Hamburguesa de pollo"
                    }
                    DropdownMenuItem(
                        text = { Text(textoComida3) },
                        onClick = { comidaAdd = textoComida3; comida = "Chicken Sandwich" })
                    val textoComida4 = if (idioma.value) {
                        "Chicken Wings"
                    } else {
                        "Pollo frito"
                    }
                    DropdownMenuItem(
                        text = { Text(textoComida4) },
                        onClick = { comidaAdd = textoComida4; comida = "Chicken Wings" })
                    DropdownMenuItem(
                        text = { Text("Cupcakes") },
                        onClick = { comidaAdd = "Cupcakes"; comida = comidaAdd })
                    val textoComida5 = if (idioma.value) {
                        "Hot Dog"
                    } else {
                        "Perrito caliente"
                    }
                    DropdownMenuItem(
                        text = { Text(textoComida5) },
                        onClick = { comidaAdd = textoComida5; comida = "Hot Dog" })
                    DropdownMenuItem(
                        text = { Text("Mocha") },
                        onClick = { comidaAdd = "Mocha"; comida = comidaAdd })
                    DropdownMenuItem(
                        text = { Text("Paleta") },
                        onClick = { comidaAdd = "Paleta"; comida = comidaAdd })
                    DropdownMenuItem(
                        text = { Text("Pasta") },
                        onClick = { comidaAdd = "Pasta"; comida = comidaAdd })
                    val textoComida6 = if (idioma.value) {
                        "Pie"
                    } else {
                        "Pastel"
                    }
                    DropdownMenuItem(
                        text = { Text(textoComida6) },
                        onClick = { comidaAdd = textoComida6; comida = "Pie" })
                    DropdownMenuItem(
                        text = { Text("Pizza") },
                        onClick = { comidaAdd = "Pizza"; comida = comidaAdd })
                    DropdownMenuItem(
                        text = { Text("Sandwich") },
                        onClick = { comidaAdd = "Sandwich"; comida = comidaAdd })
                    val textoComida7 = if (idioma.value) {
                        "Sundae"
                    } else {
                        "Helado"
                    }
                    DropdownMenuItem(
                        text = { Text(textoComida7) },
                        onClick = { comidaAdd = textoComida7; comida = "Sundae" })
                }
            }
        }
        val textoJuego = if (idioma.value) {
            "Game: "
        } else {
            "Juego: "
        }
        Row {
            Text(textoJuego)
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
        val textoOP = if (idioma.value) {
            "Order Part: "
        } else {
            "Parte del pedido: "
        }
        Row {
            Text(textoOP)
            ExposedDropdownMenuBox(expanded = activado5,
                onExpandedChange = {activado5 = !activado5} ) {
                TextField(modifier = Modifier.menuAnchor(),
                    readOnly = true,
                    value = parteAdd,
                    onValueChange = {}
                )
                DropdownMenu(expanded = activado5, onDismissRequest = { activado5 = false }) {
                    val textoOP1 = if (idioma.value) {
                        "Bag Size"
                    } else {
                        "Tamaño de bolsa"
                    }
                    DropdownMenuItem(
                        text = { Text(textoOP1) },
                        onClick = { parteAdd = textoOP1; parte = "Bag Size" })
                    val textoOP2 = if (idioma.value) {
                        "Burger Time"
                    } else {
                        "Tiempo para hamburguesa"
                    }
                    DropdownMenuItem(
                        text = { Text(textoOP2) },
                        onClick = { parteAdd = textoOP2; parte = "Burger Time" })
                    val textoOP3 = if (idioma.value) {
                        "Chicken Wings Position"
                    } else {
                        "Posición para pollo frito"
                    }
                    DropdownMenuItem(
                        text = { Text(textoOP3) },
                        onClick = { parteAdd = textoOP3; parte = "Chicken Wings Position" })
                    val textoOP4 = if (idioma.value) {
                        "Cut"
                    } else {
                        "Corte"
                    }
                    DropdownMenuItem(
                        text = { Text(textoOP4) },
                        onClick = { parteAdd = textoOP4; parte = "Cut" })
                    val textoOP5 = if (idioma.value) {
                        "Drink Size"
                    } else {
                        "Tamaño de bebida"
                    }
                    DropdownMenuItem(
                        text = { Text(textoOP5) },
                        onClick = { parteAdd = textoOP5; parte = "Drink Size" })
                    val textoOP6 = if (idioma.value) {
                        "Liner"
                    } else {
                        "Envoltorio"
                    }
                    DropdownMenuItem(
                        text = { Text(textoOP6) },
                        onClick = { parteAdd = textoOP6; parte = "Liner" })
                    val textoOP7 = if (idioma.value) {
                        "Mocha Size"
                    } else {
                        "Tamaño de mocha"
                    }
                    DropdownMenuItem(
                        text = { Text(textoOP7) },
                        onClick = { parteAdd = textoOP7; parte = "Mocha Size" })
                    val textoOP8 = if (idioma.value) {
                        "Paleta Topping Position"
                    } else {
                        "Posición para toppings para paletas"
                    }
                    DropdownMenuItem(
                        text = { Text(textoOP8) },
                        onClick = { parteAdd = textoOP8; parte = "Paleta Topping Position" })
                    val textoOP9 = if (idioma.value) {
                        "Pasta Time"
                    } else {
                        "Tiempo para pasta"
                    }
                    DropdownMenuItem(
                        text = { Text(textoOP9) },
                        onClick = { parteAdd = textoOP9; parte = "Pasta Time" })
                    val textoOP10 = if (idioma.value) {
                        "Pie Topping Position"
                    } else {
                        "Posición para toppings para pastel"
                    }
                    DropdownMenuItem(
                        text = { Text(textoOP10) },
                        onClick = { parteAdd = textoOP10; parte = "Pie Topping Position" })
                    val textoOP11 = if (idioma.value) {
                        "Pizza Time"
                    } else {
                        "Tiempo para pizza"
                    }
                    DropdownMenuItem(
                        text = { Text(textoOP11) },
                        onClick = { parteAdd = textoOP11; parte = "Pizza Time" })
                    val textoOP12 = if (idioma.value) {
                        "Pizza Topping Position"
                    } else {
                        "Posición para toppings para pizza"
                    }
                    DropdownMenuItem(
                        text = { Text(textoOP12) },
                        onClick = { parteAdd = textoOP12; parte = "Pizza Topping Position" })
                    val textoOP13 = if (idioma.value) {
                        "Sandwich Time"
                    } else {
                        "Tiempo para sandwich"
                    }
                    DropdownMenuItem(
                        text = { Text(textoOP13) },
                        onClick = { parteAdd = textoOP13; parte = "Sandwich Time" })
                    val textoOP14 = if (idioma.value) {
                        "Slush Size"
                    } else {
                        "Tamaño de batido"
                    }
                    DropdownMenuItem(
                        text = { Text(textoOP14) },
                        onClick = { parteAdd = textoOP14; parte = "Slush Size" })
                    val textoOP15 = if (idioma.value) {
                        "Soda Size"
                    } else {
                        "Tamaño de refresco"
                    }
                    DropdownMenuItem(
                        text = { Text(textoOP15) },
                        onClick = { parteAdd = textoOP15; parte = "Soda Size" })
                    val textoOP16 = if (idioma.value) {
                        "Sundae Size"
                    } else {
                        "Tamaño de helado"
                    }
                    DropdownMenuItem(
                        text = { Text(textoOP16) },
                        onClick = { parteAdd = textoOP16; parte = "Sundae Size" })
                    val textoOP17 = if (idioma.value) {
                        "Sundae Time"
                    } else {
                        "Tiempo para helados"
                    }
                    DropdownMenuItem(
                        text = { Text(textoOP17) },
                        onClick = { parteAdd = textoOP17; parte = "Sundae Time" })
                }
            }
        }
    }
}