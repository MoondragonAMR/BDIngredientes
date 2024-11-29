package com.example.bdingredientes.ui.theme

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ListItem
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.bdingredientes.clases.ImagenIngrediente
import com.example.bdingredientes.clases.VMBD
import com.example.bdingredientes.clases.VMBD2
import com.example.bdingredientes.clases.ViewModelScaffold
import com.example.bdingredientes.clases.imagenes
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.example.bdingredientes.clases.Equipment
import com.example.bdingredientes.clases.VMBD3
import com.example.bdingredientes.clases.VMBD4

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun PantallaEquipment(db : VMBD3, sf: ViewModelScaffold, navController: NavController) {
    var equipment = db.equipment.collectAsState().value
    var db4: VMBD4 = viewModel()
    var estado by remember { mutableStateOf(false) }
    var busqueda by remember { mutableStateOf("") }
    var filtro by remember { mutableStateOf("") }
    var numero by remember { mutableStateOf(119L) }
    var valor by remember { mutableStateOf(numero.toString())}
    var parametro by remember { mutableStateOf("id") }
    var filtroParametro by remember { mutableStateOf("none") }
    var activado by remember { mutableStateOf(false) }
    var activado2 by remember { mutableStateOf(false) }
    var aleatorio = sf.Aleatorio2.collectAsState().value
    var utensiliosRandom = db.equipmentAleatorio.collectAsState().value
    var listaMostrar: SnapshotStateList<Equipment>
    var url by remember { mutableStateOf("") }

    BackHandler{
        navController.popBackStack()
    }

    DisposableEffect(db) {
        db.crearListener()
        onDispose { db.borrarListener() }
    }

    Column() {

        Row() {
            Text("Click on an equipment to add it to your list", fontWeight = FontWeight.Bold)
        }
        SearchBar(placeholder = { Text("Search equipment by number or name") },
            query = busqueda,
            onQueryChange = { textoIntroducido -> busqueda = textoIntroducido },
            onSearch = { filtro = it; estado = false },
            active = estado,
            onActiveChange = { estado = !estado }) {
            LazyColumn() {
                items(equipment.size) {

                    var mostrar = false
                    var mostrar2 = false

                    mostrar = if (busqueda.length == 1) {
                        equipment[it].name.startsWith(busqueda, true)
                    } else {
                        (equipment[it].name.contains(
                            busqueda,
                            true
                        )) || (equipment[it].type.lowercase()
                            .contains(busqueda.lowercase())) || (busqueda.isBlank())
                    }

                    mostrar2 = ((equipment[it].number.toString() == busqueda) || (busqueda.isBlank()))

                    if (mostrar || mostrar2) {
                        ListItem(
                            headlineContent = { Text(equipment[it].name) },
                            Modifier.clickable { busqueda = equipment[it].name })
                    }
                }
            }
        }
        Row() {
            Text("Number of equipment shown: ")
            TextField(
                value = valor,
                onValueChange = { texto -> valor = texto },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                placeholder = { Text("Insert a number") })
        }
        Row() {
            Text("Order by: ")
            ExposedDropdownMenuBox(expanded = activado,
                onExpandedChange = { activado = !activado }) {
                TextField(modifier = Modifier.menuAnchor(),
                    readOnly = true,
                    value = parametro,
                    onValueChange = {}
                )
                DropdownMenu(expanded = activado, onDismissRequest = { activado = false }) {
                    DropdownMenuItem(
                        text = { Text("id") },
                        onClick = { parametro = "id" })
                    DropdownMenuItem(
                        text = { Text("number") },
                        onClick = { parametro = "number" })
                    DropdownMenuItem(
                        text = { Text("name") },
                        onClick = { parametro = "name" })
                    DropdownMenuItem(
                        text = { Text("type") },
                        onClick = { parametro = "type" })
                    DropdownMenuItem(
                        text = { Text("holiday") },
                        onClick = { parametro = "holiday" })
                    DropdownMenuItem(
                        text = { Text("food") },
                        onClick = { parametro = "food" })
                    DropdownMenuItem(
                        text = { Text("game") },
                        onClick = { parametro = "game" })
                    DropdownMenuItem(
                        text = { Text("orderPart") },
                        onClick = { parametro = "orderPart" })
                }
            }
        }
        Row() {
            Text("Filter: ")
            ExposedDropdownMenuBox(expanded = activado2,
                onExpandedChange = { activado2 = !activado2 }) {
                TextField(modifier = Modifier.menuAnchor(),
                    readOnly = true,
                    value = filtroParametro,
                    onValueChange = {}
                )
                DropdownMenu(expanded = activado2, onDismissRequest = { activado2 = false }) {
                    DropdownMenuItem(
                        text = { Text("none") },
                        onClick = { filtroParametro = "none" })
                    when (parametro) {
                        "type" -> {
                            DropdownMenuItem(
                                text = { Text("Alarm") },
                                onClick = { filtroParametro = "Alarm" })
                            DropdownMenuItem(
                                text = { Text("Bag Size") },
                                onClick = { filtroParametro = "Bag Size" })
                            DropdownMenuItem(
                                text = { Text("Cup Size") },
                                onClick = { filtroParametro = "Cup Size" })
                            DropdownMenuItem(
                                text = { Text("Cut") },
                                onClick = { filtroParametro = "Cut" })
                            DropdownMenuItem(
                                text = { Text("Liner") },
                                onClick = { filtroParametro = "Liner" })
                            DropdownMenuItem(
                                text = { Text("Position") },
                                onClick = { filtroParametro = "Position" })
                        }
                        "holiday" -> {
                            DropdownMenuItem(
                                text = { Text("Standard") },
                                onClick = { filtroParametro = "Standard" })
                            DropdownMenuItem(
                                text = { Text("Baseball Season") },
                                onClick = { filtroParametro = "Baseball Season" })
                            DropdownMenuItem(
                                text = { Text("Big Top Carnival") },
                                onClick = { filtroParametro = "Big Top Carnival" })
                            DropdownMenuItem(
                                text = { Text("Cherry Blossom Festival") },
                                onClick = { filtroParametro = "Cherry Blossom Festival" })
                            DropdownMenuItem(
                                text = { Text("Christmas") },
                                onClick = { filtroParametro = "Christmas" })
                            DropdownMenuItem(
                                text = { Text("Cinco de Mayo") },
                                onClick = { filtroParametro = "Cinco de Mayo" })
                            DropdownMenuItem(
                                text = { Text("Comet Con") },
                                onClick = { filtroParametro = "Comet Con" })
                            DropdownMenuItem(
                                text = { Text("Easter") },
                                onClick = { filtroParametro = "Easter" })
                            DropdownMenuItem(
                                text = { Text("Film Fest") },
                                onClick = { filtroParametro = "Film Fest" })
                            DropdownMenuItem(
                                text = { Text("Halloween") },
                                onClick = { filtroParametro = "Halloween" })
                            DropdownMenuItem(
                                text = { Text("Maple Mornings") },
                                onClick = { filtroParametro = "Maple Mornings" })
                            DropdownMenuItem(
                                text = { Text("New Year") },
                                onClick = { filtroParametro = "New Year" })
                            DropdownMenuItem(
                                text = { Text("Onionfest") },
                                onClick = { filtroParametro = "Onionfest" })
                            DropdownMenuItem(
                                text = { Text("Pirate Bash") },
                                onClick = { filtroParametro = "Pirate Bash" })
                            DropdownMenuItem(
                                text = { Text("St Paddy") },
                                onClick = { filtroParametro = "St Paddy" })
                            DropdownMenuItem(
                                text = { Text("Starlight") },
                                onClick = { filtroParametro = "Starlight" })
                            DropdownMenuItem(
                                text = { Text("Summer Luau") },
                                onClick = { filtroParametro = "Summer Luau" })
                            DropdownMenuItem(
                                text = { Text("Thanksgiving") },
                                onClick = { filtroParametro = "Thanksgiving" })
                            DropdownMenuItem(
                                text = { Text("Valentines") },
                                onClick = { filtroParametro = "Valentines" })
                        }
                        "food" -> {
                            DropdownMenuItem(
                                text = { Text("Breakfast") },
                                onClick = { filtroParametro = "Breakfast" })
                            DropdownMenuItem(
                                text = { Text("Burger") },
                                onClick = { filtroParametro = "Burger" })
                            DropdownMenuItem(
                                text = { Text("Chicken Sandwich") },
                                onClick = { filtroParametro = "Chicken Sandwich" })
                            DropdownMenuItem(
                                text = { Text("Chicken Wings") },
                                onClick = { filtroParametro = "Chicken Wings" })
                            DropdownMenuItem(
                                text = { Text("Cupcakes") },
                                onClick = { filtroParametro = "Cupcakes" })
                            DropdownMenuItem(
                                text = { Text("Hot Dog") },
                                onClick = { filtroParametro = "Hot Dog" })
                            DropdownMenuItem(
                                text = { Text("Mocha") },
                                onClick = { filtroParametro = "Mocha" })
                            DropdownMenuItem(
                                text = { Text("Paleta") },
                                onClick = { filtroParametro = "Paleta" })
                            DropdownMenuItem(
                                text = { Text("Pasta") },
                                onClick = { filtroParametro = "Pasta" })
                            DropdownMenuItem(
                                text = { Text("Pie") },
                                onClick = { filtroParametro = "Pie" })
                            DropdownMenuItem(
                                text = { Text("Pizza") },
                                onClick = { filtroParametro = "Pizza" })
                            DropdownMenuItem(
                                text = { Text("Sandwich") },
                                onClick = { filtroParametro = "Sandwich" })
                            DropdownMenuItem(
                                text = { Text("Sundae") },
                                onClick = { filtroParametro = "Sundae" })
                        }
                        "game" -> {
                            DropdownMenuItem(
                                text = { Text("Bakeria") },
                                onClick = { filtroParametro = "Bakeria" })
                            DropdownMenuItem(
                                text = { Text("Bakeria To Go") },
                                onClick = { filtroParametro = "Bakeria To Go" })
                            DropdownMenuItem(
                                text = { Text("Burgeria") },
                                onClick = { filtroParametro = "Burgeria" })
                            DropdownMenuItem(
                                text = { Text("Burgeria HD") },
                                onClick = { filtroParametro = "Burgeria HD" })
                            DropdownMenuItem(
                                text = { Text("Burgeria To Go") },
                                onClick = { filtroParametro = "Burgeria To Go" })
                            DropdownMenuItem(
                                text = { Text("Cheeseria") },
                                onClick = { filtroParametro = "Cheeseria" })
                            DropdownMenuItem(
                                text = { Text("Cheeseria To Go") },
                                onClick = { filtroParametro = "Cheeseria To Go" })
                            DropdownMenuItem(
                                text = { Text("Cluckeria To Go") },
                                onClick = { filtroParametro = "Cluckeria To Go" })
                            DropdownMenuItem(
                                text = { Text("Cupcakeria") },
                                onClick = { filtroParametro = "Cupcakeria" })
                            DropdownMenuItem(
                                text = { Text("Cupcakeria HD") },
                                onClick = { filtroParametro = "Cupcakeria HD" })
                            DropdownMenuItem(
                                text = { Text("Cupcakeria To Go") },
                                onClick = { filtroParametro = "Cupcakeria To Go" })
                            DropdownMenuItem(
                                text = { Text("Freezeria") },
                                onClick = { filtroParametro = "Freezeria" })
                            DropdownMenuItem(
                                text = { Text("Freezeria Deluxe") },
                                onClick = { filtroParametro = "Freezeria Deluxe" })
                            DropdownMenuItem(
                                text = { Text("Freezeria HD") },
                                onClick = { filtroParametro = "Freezeria HD" })
                            DropdownMenuItem(
                                text = { Text("Freezeria To Go") },
                                onClick = { filtroParametro = "Freezeria To Go" })
                            DropdownMenuItem(
                                text = { Text("Hot Doggeria") },
                                onClick = { filtroParametro = "Hot Doggeria" })
                            DropdownMenuItem(
                                text = { Text("Hot Doggeria HD") },
                                onClick = { filtroParametro = "Hot Doggeria HD" })
                            DropdownMenuItem(
                                text = { Text("Hot Doggeria To Go") },
                                onClick = { filtroParametro = "Hot Doggeria To Go" })
                            DropdownMenuItem(
                                text = { Text("Mocharia To Go") },
                                onClick = { filtroParametro = "Mocharia To Go" })
                            DropdownMenuItem(
                                text = { Text("Paleteria To Go") },
                                onClick = { filtroParametro = "Paleteria To Go" })
                            DropdownMenuItem(
                                text = { Text("Pancakeria") },
                                onClick = { filtroParametro = "Pancakeria" })
                            DropdownMenuItem(
                                text = { Text("Pancakeria HD") },
                                onClick = { filtroParametro = "Pancakeria HD" })
                            DropdownMenuItem(
                                text = { Text("Pancakeria To Go") },
                                onClick = { filtroParametro = "Pancakeria To Go" })
                            DropdownMenuItem(
                                text = { Text("Pastaria") },
                                onClick = { filtroParametro = "" })
                            DropdownMenuItem(
                                text = { Text("Pastaria To Go") },
                                onClick = { filtroParametro = "" })
                            DropdownMenuItem(
                                text = { Text("Pizzeria") },
                                onClick = { filtroParametro = "Pizzeria" })
                            DropdownMenuItem(
                                text = { Text("Pizzeria Deluxe") },
                                onClick = { filtroParametro = "Pizzeria Deluxe" })
                            DropdownMenuItem(
                                text = { Text("Pizzeria HD") },
                                onClick = { filtroParametro = "Pizzeria HD" })
                            DropdownMenuItem(
                                text = { Text("Pizzeria To Go") },
                                onClick = { filtroParametro = "Pizzeria To Go" })
                            DropdownMenuItem(
                                text = { Text("Wingeria") },
                                onClick = { filtroParametro = "Wingeria" })
                            DropdownMenuItem(
                                text = { Text("Wingeria HD") },
                                onClick = { filtroParametro = "Wingeria HD" })
                            DropdownMenuItem(
                                text = { Text("Wingeria To Go") },
                                onClick = { filtroParametro = "Wingeria To Go" })
                        }
                        "orderPart" -> {
                            DropdownMenuItem(
                                text = { Text("Bag Size") },
                                onClick = { filtroParametro = "Bag Size" })
                            DropdownMenuItem(
                                text = { Text("Burger Time") },
                                onClick = { filtroParametro = "Burger Time" })
                            DropdownMenuItem(
                                text = { Text("Chicken Wings Position") },
                                onClick = { filtroParametro = "Chicken Wings Position" })
                            DropdownMenuItem(
                                text = { Text("Cut") },
                                onClick = { filtroParametro = "Cut" })
                            DropdownMenuItem(
                                text = { Text("Drink Size") },
                                onClick = { filtroParametro = "Drink Size" })
                            DropdownMenuItem(
                                text = { Text("Liner") },
                                onClick = { filtroParametro = "Liner" })
                            DropdownMenuItem(
                                text = { Text("Mocha Size") },
                                onClick = { filtroParametro = "Mocha Size" })
                            DropdownMenuItem(
                                text = { Text("Paleta Topping Position") },
                                onClick = { filtroParametro = "Paleta Topping Position" })
                            DropdownMenuItem(
                                text = { Text("Pasta Time") },
                                onClick = { filtroParametro = "Pasta Time" })
                            DropdownMenuItem(
                                text = { Text("Pie Topping Position") },
                                onClick = { filtroParametro = "`Pie Topping Position" })
                            DropdownMenuItem(
                                text = { Text("Pizza Time") },
                                onClick = { filtroParametro = "Pizza Time" })
                            DropdownMenuItem(
                                text = { Text("Pizza Topping Position") },
                                onClick = { filtroParametro = "Pizza Topping Position" })
                            DropdownMenuItem(
                                text = { Text("Sandwich Time") },
                                onClick = { filtroParametro = "Sandwich Time" })
                            DropdownMenuItem(
                                text = { Text("Slush Size") },
                                onClick = { filtroParametro = "Slush Size" })
                            DropdownMenuItem(
                                text = { Text("Soda Size") },
                                onClick = { filtroParametro = "Soda Size" })
                            DropdownMenuItem(
                                text = { Text("Sundae Size") },
                                onClick = { filtroParametro = "Sundae Size" })
                            DropdownMenuItem(
                                text = { Text("Sundae Time") },
                                onClick = { filtroParametro = "Sundae Time" })
                        }
                    }
                }
            }
        }
        Row() {
            Button(onClick = {
                numero = valor.toLong()
                if (filtroParametro == "none") {
                    db.filtrarUtensilio(numero, parametro)
                } else db.filtrarUtensilio(numero, parametro, filtroParametro)
            }) {
                Text("Apply conditions")
            }
        }

        LazyColumn {

            if (aleatorio.value) {
                listaMostrar = utensiliosRandom
            } else {
                listaMostrar = equipment
            }
            items(listaMostrar.size) {

                var mostrar = false
                var mostrar2 = false

                mostrar = if (filtro.length == 1) {
                    (listaMostrar[it].name.startsWith(filtro, ignoreCase = true))
                } else {
                    (listaMostrar[it].name.contains(filtro, ignoreCase = true)) || (filtro.isBlank())
                }

                mostrar2 = ((listaMostrar[it].number.toString() == busqueda) || (filtro.isBlank()))

                if (mostrar || mostrar2) {
                    val numero2 = listaMostrar[it].number
                    val nombre = listaMostrar[it].name
                    val tipo = listaMostrar[it].type
                    val comida = listaMostrar[it].food
                    val juego = listaMostrar[it].game
                    val celebracion = listaMostrar[it].holiday
                    val parte = listaMostrar[it].orderPart

                    when (nombre) {
                        "4-Cut" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2F4-Cut.png?alt=media&token=042653a2-226a-4dfc-9d50-b226c7ec32b9"
                        }
                        "6-Cut" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2F6-Cut.png?alt=media&token=e6157e8c-f153-455c-8edf-85e96e5032ff"
                        }
                        "8-Cut" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2F8-Cut.png?alt=media&token=23822db8-f388-4e19-8cfd-621c035ffab8"
                        }
                        "Al Dente Alarm" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FAl_Dente_Alarm.png?alt=media&token=4ced455f-992e-42b1-a2a9-9bcc0d277a3b"
                        }
                        "All but Bottom-Left" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FAll_but_bottom-left.png?alt=media&token=86131a04-3a3b-4aeb-aa27-5a0f40ccaa38"
                        }
                        "All but Bottom-Right" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FAll_but_bottom-right.png?alt=media&token=c8af251a-3937-4c1d-9143-b51ea37bea00"
                        }
                        "All but Top-Left" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FAll_but_top-left.png?alt=media&token=408c16a2-3fc8-4cba-89c4-70aebf581c2d"
                        }
                        "All but Top-Right" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FAll_but_top-right.png?alt=media&token=3b29cc8a-cb43-4f6a-a16a-452d50526bf7"
                        }
                        "Around" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FAround.png?alt=media&token=1c4bd2c0-9d41-466c-ae70-32e67eba91b2"
                        }
                        "Baseball Liner A" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FBaseball_Liner_A.png?alt=media&token=7374c873-1818-4acc-9827-517818b82a41"
                        }
                        "Baseball Liner B" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FBaseball_Liner_B.png?alt=media&token=5c61eff0-d603-42cb-80be-bb426932c873"
                        }
                        "Baseball Liner C" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FBaseball_Liner_C.png?alt=media&token=14a08176-2ebb-47ae-aa25-4a7bcf540a5f"
                        }
                        "Baseball Liner D" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FBaseball_Liner_D.png?alt=media&token=91d11c2f-64bd-498c-8902-9f0ccc10606f"
                        }
                        "Bottom Half" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FBottom_Half.png?alt=media&token=27bc0921-49d0-478b-9536-7e9de062a321"
                        }
                        "Bottom-Left Corner" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FBottom-left_Corner.png?alt=media&token=cdc923a2-9c15-43bf-beb0-dbe50c25d119"
                        }
                        "Bottom-Right Corner" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FBottom-right_Corner.png?alt=media&token=dcb8bd93-7f8f-48fe-9527-4c4ff0c5f8d5"
                        }
                        "Carnival Liner A" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FCarnival_Liner_A.png?alt=media&token=d018c3d2-33aa-448e-955e-dad1ba8a6026"
                        }
                        "Carnival Liner B" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FCarnival_Liner_B.png?alt=media&token=3d212cd3-848c-4f3e-80b9-8537e1e46537"
                        }
                        "Carnival Liner C" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FCarnival_Liner_C.png?alt=media&token=4d6b5a5c-e46b-42c6-b5c5-b24135aa20dd"
                        }
                        "Carnival Liner D" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FCarnival_Liner_D.png?alt=media&token=8e942d51-4122-41f2-9f44-cf7c73d39d8d"
                        }
                        "Center" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FCenter.png?alt=media&token=6b831577-1da2-4649-acc6-847d34c9e269"
                        }
                        "Cherry Blossom Liner A" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FCherry_Blossom_Liner_A.png?alt=media&token=1cea4a60-8d01-4b99-8c8a-53827038bc44"
                        }
                        "Cherry Blossom Liner B" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FCherry_Blossom_Liner_B.png?alt=media&token=eeb998ab-9903-4c2c-b0ce-c68ce6cbf4e8"
                        }
                        "Cherry Blossom Liner C" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FCherry_Blossom_Liner_C.png?alt=media&token=437325a2-1aa9-45ef-8ce3-d79f84bec6db"
                        }
                        "Cherry Blossom Liner D" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FCherry_Blossom_Liner_D.png?alt=media&token=fd6a8b59-9420-411f-84b8-b8b0d8fc4e41"
                        }
                        "Christmas Liner A" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FChristmas_Liner_A.png?alt=media&token=d28c3748-d81a-4552-91a0-6112dd7147ae"
                        }
                        "Christmas Liner B" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FChristmas_Liner_B.png?alt=media&token=1accce2e-2dbd-4989-ad98-8066cd3c3880"
                        }
                        "Christmas Liner C" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FChristmas_Liner_C.png?alt=media&token=b47fceea-4e1f-4510-9a09-ffb151ef7bfd"
                        }
                        "Christmas Liner D" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FChristmas_Liner_D.png?alt=media&token=4ff5687f-72d1-49ab-b538-bdc68aaae47f"
                        }
                        "Chunky Alarm" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FChunky_Alarm.png?alt=media&token=ff8f6c20-90d0-4e57-b323-5c6fd82830a7"
                        }
                        "Cinco de Mayo Liner A" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FCinco_de_Mayo_Liner_A.png?alt=media&token=d4b4fdc0-971d-483b-b861-777601b0d1e5"
                        }
                        "Cinco de Mayo Liner B" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FCinco_de_Mayo_Liner_B.png?alt=media&token=2e52913d-d0bb-4ac7-b9b2-451018be9003"
                        }
                        "Cinco de Mayo Liner C" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FCinco_de_Mayo_Liner_C.png?alt=media&token=d7297008-06d5-4c92-aff0-2b65a8d830ad"
                        }
                        "Cinco de Mayo Liner D" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FCinco_de_Mayo_Liner_D%2B.png?alt=media&token=0f29c858-b74d-4ff3-8eb8-b4e2cac37cf3"
                        }
                        "Comet Con Liner A" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FComet_Con_Liner_A.png?alt=media&token=31aed6f2-6d66-459b-8430-384adc132280"
                        }
                        "Comet Con Liner B" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FComet_Con_Liner_B.png?alt=media&token=3e9a283f-9dfb-4d57-9099-168a054c4016"
                        }
                        "Comet Con Liner C" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FComet_Con_Liner_C.png?alt=media&token=fab640fc-6142-47b9-ba61-2d0f38e92bd9"
                        }
                        "Comet Con Liner D" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FComet_Con_Liner_D.png?alt=media&token=22443856-4ac8-4395-b94e-6d2a68907930"
                        }
                        "Easter Liner A" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FEaster_Liner_A.png?alt=media&token=8c07b369-3a70-4391-b562-9b6a12b6dd37"
                        }
                        "Easter Liner B" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FEaster_Liner_B.png?alt=media&token=48d9b759-49c3-459c-806d-a94c62df5535"
                        }
                        "Easter Liner C" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FEaster_Liner_C.png?alt=media&token=c4947bf9-4674-4bdc-afe4-d6eba95ea2f2"
                        }
                        "Easter Liner D" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FEaster_Liner_D.png?alt=media&token=d5f92e1b-85f6-4cc4-91ec-607acd9aa948"
                        }
                        "Film Fest Liner A" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FFilm_Fest_Liner_A.png?alt=media&token=0bcee6f9-0dd4-478d-a81b-b11c41d7d4f0"
                        }
                        "Film Fest Liner B" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FFilm_Fest_Liner_B.png?alt=media&token=3e78e081-ce76-4d96-9d07-6c8eb4d4c8df"
                        }
                        "Film Fest Liner C" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FFilm_Fest_Liner_C.png?alt=media&token=18b2171b-556c-4517-b0cd-dee6ebc8178f"
                        }
                        "Film Fest Liner D" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FFilm_Fest_Liner_D.png?alt=media&token=f482c655-aed6-46cc-9875-5783be5b7cb9"
                        }
                        "Full-Bake Alarm" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FFull-Bake_Alarm.png?alt=media&token=6d2a2708-9bf3-4b00-bd50-2e0698ee6854"
                        }
                        "Half-Quarter Alarm" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FHalf-Quarter_Alarm.png?alt=media&token=f45c8377-1f6d-49c8-b3d8-7f6604460eda"
                        }
                        "Halloween Liner A" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FHalloween_Liner_A.png?alt=media&token=f92c8dc3-27fa-48d0-81aa-488ede58395f"
                        }
                        "Halloween Liner B" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FHalloween_Liner_B.png?alt=media&token=26dd42a3-bf5d-4ccd-8b06-24acf95aa969"
                        }
                        "Halloween Liner C" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FHalloween_Liner_C.png?alt=media&token=22c3265b-c1a0-44e0-9dcc-1267921d5a59"
                        }
                        "Halloween Liner D" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FHalloween_Liner_D.png?alt=media&token=4540974d-7796-4945-8475-1da6d2ce6154"
                        }
                        "Inner Ring" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FInner_Ring.png?alt=media&token=2d147f70-71f3-4f84-885a-91095be1e982"
                        }
                        "Inner Ring and Center" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FInner_Ring_and_Center.png?alt=media&token=29dd316d-397e-4c31-a639-a4fb0f3ccd91"
                        }
                        "Large Bag" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FLarge_Bag.png?alt=media&token=1a5dab8e-9d58-4363-82b4-d6e514865a4b"
                        }
                        "Large Cup" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FLarge_Cup.png?alt=media&token=e06c307a-6ce6-4bfc-8f68-7d34c7ef6fa3"
                        }
                        "Left Diagonal" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FLeft_Diagonal.png?alt=media&token=223ac136-74f1-43ff-8fd3-124f3038fc40"
                        }
                        "Left Half" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FLeft_Half.png?alt=media&token=24b343c1-bf73-4a1d-b01a-35a0d25dbef7"
                        }
                        "Light-Bake Alarm" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FLight-Bake_Alarm.png?alt=media&token=2c2caa7f-7060-4aae-ad25-5f72d153fc3a"
                        }
                        "Maple Mornings Liner A" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FMaple_Mornings_Liner_A.png?alt=media&token=e6c67da3-179a-4567-9114-ff8bfbf003c7"
                        }
                        "Maple Mornings Liner B" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FMaple_Mornings_Liner_B.png?alt=media&token=7b0008a4-e872-414d-b409-9e1695e0c45b"
                        }
                        "Maple Mornings Liner C" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FMaple_Mornings_Liner_C.png?alt=media&token=be03faa1-962f-427d-9154-997774b13c29"
                        }
                        "Maple Mornings Liner D" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FMaple_Mornings_Liner_D.png?alt=media&token=96f89b03-05fb-4128-8eeb-13251f9e28d6"
                        }
                        "Medium Bag" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FMedium_Bag.png?alt=media&token=7f3c069a-9b14-498e-97cb-6d792a94a2a2.png"
                        }
                        "Medium Cup" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FMedium_Cup.png?alt=media&token=42f7f3ce-254a-46d2-966e-a6e8c775293a"
                        }
                        "New Year Liner A" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FNew_Year_Liner_A.png?alt=media&token=9eee3380-0b1e-459d-952d-f608db81c053"
                        }
                        "New Year Liner B" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FNew_Year_Liner_B.png?alt=media&token=80d0748f-0137-4055-ad0a-b360b4704d69"
                        }
                        "New Year Liner C" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FNew_Year_Liner_C.png?alt=media&token=a8988dbb-3418-449d-b462-4268005ce03f"
                        }
                        "New Year Liner D" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FNew_Year_Liner_D.png?alt=media&token=7f1f5b06-e075-48db-96ad-6f624ee7f8b5"
                        }
                        "Normal Liner A" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FNormal_Liner_A.png?alt=media&token=04c9af65-da10-4590-92f9-c8117c524014"
                        }
                        "Normal Liner B" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FNormal_Liner_B.png?alt=media&token=cde3188f-0275-4758-8845-5c93ac690560"
                        }
                        "Normal Liner C" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FNormal_Liner_C.png?alt=media&token=fddf78f0-7296-464f-bc0a-2e8bb7f81994"
                        }
                        "Normal Liner D" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FNormal_Liner_D.png?alt=media&token=ec608dd2-24c9-4a7c-921c-f4fcaf71870a"
                        }
                        "Onionfest Liner A" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FOnionfest_Liner_A.png?alt=media&token=3d54e6a4-9d06-4a29-a67b-eafdc835c969"
                        }
                        "Onionfest Liner B" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FOnionfest_Liner_B.png?alt=media&token=d945a8d0-385b-4f2b-9fa5-692628062724"
                        }
                        "Onionfest Liner C" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FOnionfest_Liner_C.png?alt=media&token=202dd41e-38e7-4e03-baad-70ae168208f9"
                        }
                        "Onionfest Liner D" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FOnionfest_Liner_D.png?alt=media&token=52e6389e-2278-4da3-aa08-c2619e4fb89b"
                        }
                        "Outer Ring" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FOuter_Ring.png?alt=media&token=7301fef5-775b-440a-b349-7b58ab661e50"
                        }
                        "Outer Ring and Center" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FOuter_Ring_and_Center.png?alt=media&token=23c8dbeb-f8ad-4894-8f39-e61377df81b2"
                        }
                        "Paddy Liner A" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FSt_Paddy_Liner_A.png?alt=media&token=ac110b94-f7bf-411a-a12a-c0bd3ee44138"
                        }
                        "Paddy Liner B" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FSt_Paddy_Liner_B.png?alt=media&token=91e8302e-e64b-495e-8f5b-b3b76d79cc13"
                        }
                        "Paddy Liner C" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FSt_Paddy_Liner_C.png?alt=media&token=48921cb3-619c-405b-a9f4-5ce04c4cf417"
                        }
                        "Paddy Liner D" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FSt_Paddy_Liner_D.png?alt=media&token=a08602ce-aed6-43d3-acd6-b2452eed94c7"
                        }
                        "Pirate Liner A" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FPirate_Liner_A.png?alt=media&token=be8456f5-e226-4f01-90ad-5f8c014fef07"
                        }
                        "Pirate Liner B" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FPirate_Liner_B.png?alt=media&token=0417fd9f-456c-4532-80c4-d92364c68d00"
                        }
                        "Pirate Liner C" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FPirate_Liner_C.png?alt=media&token=c023b19f-f501-4f9e-ab35-5ba16ba2c568"
                        }
                        "Pirate Liner D" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FPirate_Liner_D.png?alt=media&token=0480021b-6393-4903-93cc-8b92cc59a12a"
                        }
                        "Quarter-and-Half Alarm" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FQuarter-and-Half_Alarm.png?alt=media&token=0356e39d-c62e-4c63-b3de-81be095d82e1"
                        }
                        "Regular Alarm" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FRegular_Alarm.png?alt=media&token=b75aa2ea-f054-4a78-ae19-8f031c4f472e"
                        }
                        "Right Diagonal" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FRight_Diagonal.png?alt=media&token=f055778f-9627-4114-bd9d-92b35e1000cc"
                        }
                        "Right Half" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FRight_Half.png?alt=media&token=c539839a-fca5-4551-8f0b-0dec4a19893a"
                        }
                        "Small Bag" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FSmall_Bag.png?alt=media&token=4b86fce6-1e15-45fb-9d1b-b6945007518e"
                        }
                        "Small Cup" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FSmall_Cup.png?alt=media&token=4e7cf811-dda0-41cf-bd02-1322d5b9030a"
                        }
                        "Smooth Alarm" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FSmooth_Alarm.png?alt=media&token=657bee79-99a3-4f70-afac-9603381c1be4"
                        }
                        "Square-Cut" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FSquare-Cut.png?alt=media&token=06343963-5f6d-490f-be3a-c7a24238c4d9"
                        }
                        "Starlight Liner A" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FStarlight_Liner_A.png?alt=media&token=20a27249-5b43-4b54-9562-a232e5d6edbf"
                        }
                        "Starlight Liner B" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FStarlight_Liner_B%2B.png?alt=media&token=5b579a20-30c2-4354-810e-1ba990e0598d"
                        }
                        "Starlight Liner C" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FStarlight_Liner_C.png?alt=media&token=b6a87ae0-4552-4b8d-b49a-30f4325ff7ce"
                        }
                        "Starlight Liner D" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FStarlight_Liner_D.png?alt=media&token=01ca740e-eb2c-4d6b-a2e6-a29030856ca1"
                        }
                        "Summer Liner A" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FSummer_Liner_A.png?alt=media&token=70b4d2b5-c111-4ba2-b7ee-211de29a2ee3"
                        }
                        "Summer Liner B" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FSummer_Liner_B.png?alt=media&token=94bd4d54-26b6-4711-9353-77448511bfe8"
                        }
                        "Summer Liner C" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FSummer_Liner_C.png?alt=media&token=81d406ef-33da-4e6f-b5c1-b06e32d3e9af"
                        }
                        "Summer Liner D" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FSummer_Liner_D.png?alt=media&token=8079559b-a0e0-4061-9583-da25133d5e5f"
                        }
                        "Thanksgiving Liner A" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FThanksgiving_Liner_A.png?alt=media&token=ae3b770a-d137-4077-9e31-92d4ad01ada1"
                        }
                        "Thanksgiving Liner B" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FThanksgiving_Liner_B.png?alt=media&token=86bf0218-5613-4859-b755-df52674f33f4"
                        }
                        "Thanksgiving Liner C" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FThanksgiving_Liner_C.png?alt=media&token=231599c0-e073-45ca-aa2d-d434fb284816"
                        }
                        "Thanksgiving Liner D" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FThanksgiving_Liner_D.png?alt=media&token=513da279-5299-4eb4-9d43-a63aae2bd41f"
                        }
                        "Three-Quarters-and-Half Alarm" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FThree-Quarters-and-Half_Alarm.png?alt=media&token=018dca14-4631-4365-9a86-8491b43674be"
                        }
                        "To the Left" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FTo_the_Left.png?alt=media&token=b94b98c3-66e1-49a3-9483-3700d5d67232"
                        }
                        "To the Right" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FTo_the_right.png?alt=media&token=d733330a-3c91-472b-8da8-b155636dd74f"
                        }
                        "Top Half" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FTop_Half.png?alt=media&token=2922c2c0-a9c2-44cd-88d3-453deca327ef"
                        }
                        "Top-Left Corner" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FTop-left_Corner.png?alt=media&token=8758510b-060f-414d-bf87-d9c2c2052c9a"
                        }
                        "Top-Right Corner" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FTop-right_Corner.png?alt=media&token=5d153805-5a01-411e-aa20-90fd681f76b2"
                        }
                        "Two-Quarters-and-Half Alarm" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FTwo-Quarters-and-Half_Alarm.png?alt=media&token=9486d56d-1e3a-42f7-8482-449cf5f65d86"
                        }
                        "Valentine Liner A" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FValentine_liner_A.png?alt=media&token=2833341e-bc56-4fc4-9368-436ed96ff714"
                        }
                        "Valentine Liner B" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FValentine_liner_B.png?alt=media&token=2d1e3030-9039-4e99-bb28-ef1cf5cd5055"
                        }
                        "Valentine Liner C" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FValentine_liner_C.png?alt=media&token=15e7d739-2e92-4250-9037-48187bfc7059"
                        }
                        "Valentine Liner D" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FValentine_liner_D.png?alt=media&token=1201d71e-5681-4f7a-a19c-5024447bdce2"
                        }
                        "Well-Done Alarm" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FWell-Done_Alarm.png?alt=media&token=2da29ea2-551c-4b11-8e35-a3eb0014600c"
                        }
                        else -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Al%20azar.png?alt=media&token=ab58f4fe-3730-48ca-870d-439e3e65fb44"
                        }
                    }

                    Column(
                        Modifier
                            .padding(4.dp)
                            .border(width = 2.dp, color = Color.Cyan)
                            .combinedClickable {
                                db4.anyadirUtensilio(
                                    nombre,
                                    tipo,
                                    celebracion,
                                    comida,
                                    juego,
                                    parte,
                                    numero2
                                )
                            }) {
                        Text(text = "number = $numero2")
                        Text(text = "name = $nombre")
                        Text(text = "type = $tipo")
                        Text(text = "holiday = $celebracion")
                        Text(text = "food = $comida")
                        Text(text = "game = $juego")
                        Text(text = "order part = $parte")
                        ImagenIngrediente(url)
                    }
                }
            }
        }
    }
}