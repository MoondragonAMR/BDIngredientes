@file:Suppress("NAME_SHADOWING")

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
import com.example.bdingredientes.clases.ViewModelScaffold
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
    val equipment = db.equipment.collectAsState().value
    val db4: VMBD4 = viewModel()
    var estado by remember { mutableStateOf(false) }
    var busqueda by remember { mutableStateOf("") }
    var filtro by remember { mutableStateOf("") }
    var numero by remember { mutableStateOf(119L) }
    var valor by remember { mutableStateOf(numero.toString())}
    var parametro by remember { mutableStateOf("id") }
    var filtroParametro by remember { mutableStateOf("none") }
    var activado by remember { mutableStateOf(false) }
    var activado2 by remember { mutableStateOf(false) }
    val aleatorio = sf.aleatorio2.collectAsState().value
    val utensiliosRandom = db.equipmentAleatorio.collectAsState().value
    var listaMostrar: SnapshotStateList<Equipment>
    var url by remember { mutableStateOf("") }

    val idioma = sf.english.collectAsState().value

    BackHandler{
        navController.popBackStack()
    }

    DisposableEffect(db) {
        db.crearListener()
        onDispose { db.borrarListener() }
    }

    Column {
        val textoTitulo = if (idioma.value) {
            "Click on an equipment to add it to your list"
        } else {
            "Haz click en un utensilio para añadirlo a tu lista"
        }
        Row {
            Text(textoTitulo, fontWeight = FontWeight.Bold)
        }
        val textoBusqueda = if (idioma.value) {
            "Search equipment by number or name"
        } else {
            "Busca utensilios por número o nombre"
        }
        SearchBar(placeholder = { Text(textoBusqueda) },
            query = busqueda,
            onQueryChange = { textoIntroducido -> busqueda = textoIntroducido },
            onSearch = { filtro = it; estado = false },
            active = estado,
            onActiveChange = { estado = !estado }) {
            LazyColumn {
                items(equipment.size) {

                    @Suppress("VARIABLE_WITH_REDUNDANT_INITIALIZER") var mostrar = false
                    @Suppress("VARIABLE_WITH_REDUNDANT_INITIALIZER") var mostrar2 = false

                    var nombreT = equipment[it].name
                    when (equipment[it].name) {
                        "4-Cut" -> {
                            if (!idioma.value) {
                                nombreT = "Corte de 4"
                            }
                        }
                        "6-Cut" -> {
                            if (!idioma.value) {
                                nombreT = "Corte de 6"
                            }
                        }
                        "8-Cut" -> {
                            if (!idioma.value) {
                                nombreT = "Corte de 8"
                            }
                        }
                        "Al Dente Alarm" -> {
                            if (!idioma.value) {
                                nombreT = "Alarma Al Dente"
                            }
                        }
                        "All but Bottom-Left" -> {
                            if (!idioma.value) {
                                nombreT = "Todo menos la esquina inferior izquierda"
                            }
                        }
                        "All but Bottom-Right" -> {
                            if (!idioma.value) {
                                nombreT = "Todo menos la esquina inferior derecha"
                            }
                        }
                        "All but Top-Left" -> {
                            if (!idioma.value) {
                                nombreT = "Todo menos la esquina superior izquierda"
                            }
                        }
                        "All but Top-Right" -> {
                            if (!idioma.value) {
                                nombreT = "Todo menos la esquina superior derecha"
                            }
                        }
                        "Around" -> {
                            if (!idioma.value) {
                                nombreT = "Alrededor"
                            }
                        }
                        "Baseball Liner A" -> {
                            if (!idioma.value) {
                                nombreT = "Envoltorio de Baseball A"
                            }
                        }
                        "Baseball Liner B" -> {
                            if (!idioma.value) {
                                nombreT = "Envoltorio de Baseball B"
                            }
                        }
                        "Baseball Liner C" -> {
                            if (!idioma.value) {
                                nombreT = "Envoltorio de Baseball C"
                            }
                        }
                        "Baseball Liner D" -> {
                            if (!idioma.value) {
                                nombreT = "Envoltorio de Baseball D"
                            }
                        }
                        "Bottom Half" -> {
                            if (!idioma.value) {
                                nombreT = "Mitad inferior"
                            }
                        }
                        "Bottom-Left Corner" -> {
                            if (!idioma.value) {
                                nombreT = "Esquina inferior izquierda"
                            }
                        }
                        "Bottom-Right Corner" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FBottom-right_Corner.png?alt=media&token=dcb8bd93-7f8f-48fe-9527-4c4ff0c5f8d5"
                            if (!idioma.value) {
                                nombreT = "Esquina inferior derecha"
                            }
                        }
                        "Carnival Liner A" -> {
                            if (!idioma.value) {
                                nombreT = "Envoltorio de Feria A"
                            }
                        }
                        "Carnival Liner B" -> {
                            if (!idioma.value) {
                                nombreT = "Envoltorio de Feria B"
                            }
                        }
                        "Carnival Liner C" -> {
                            if (!idioma.value) {
                                nombreT = "Envoltorio de Feria C"
                            }
                        }
                        "Carnival Liner D" -> {
                            if (!idioma.value) {
                                nombreT = "Envoltorio de Feria D"
                            }
                        }
                        "Center" -> {
                            if (!idioma.value) {
                                nombreT = "Centro"
                            }
                        }
                        "Cherry Blossom Liner A" -> {
                            if (!idioma.value) {
                                nombreT = "Envoltorio de Flor de Cerezo A"
                            }
                        }
                        "Cherry Blossom Liner B" -> {
                            if (!idioma.value) {
                                nombreT = "Envoltorio de Flor de Cerezo B"
                            }
                        }
                        "Cherry Blossom Liner C" -> {
                            if (!idioma.value) {
                                nombreT = "Envoltorio de Flor de Cerezo C"
                            }
                        }
                        "Cherry Blossom Liner D" -> {
                            if (!idioma.value) {
                                nombreT = "Envoltorio de Flor de Cerezo D"
                            }
                        }
                        "Christmas Liner A" -> {
                            if (!idioma.value) {
                                nombreT = "Envoltorio de Navidad A"
                            }
                        }
                        "Christmas Liner B" -> {
                            if (!idioma.value) {
                                nombreT = "Envoltorio de Navidad B"
                            }
                        }
                        "Christmas Liner C" -> {
                            if (!idioma.value) {
                                nombreT = "Envoltorio de Navidad C"
                            }
                        }
                        "Christmas Liner D" -> {
                            if (!idioma.value) {
                                nombreT = "Envoltorio de Navidad D"
                            }
                        }
                        "Chunky Alarm" -> {
                            if (!idioma.value) {
                                nombreT = "Alarma de poco batido"
                            }
                        }
                        "Cinco de Mayo Liner A" -> {
                            if (!idioma.value) {
                                nombreT = "Envoltorio de Cinco de Mayo A"
                            }
                        }
                        "Cinco de Mayo Liner B" -> {
                            if (!idioma.value) {
                                nombreT = "Envoltorio de Cinco de Mayo B"
                            }
                        }
                        "Cinco de Mayo Liner C" -> {
                            if (!idioma.value) {
                                nombreT = "Envoltorio de Cinco de Mayo C"
                            }
                        }
                        "Cinco de Mayo Liner D" -> {
                            if (!idioma.value) {
                                nombreT = "Envoltorio de Cinco de Mayo D"
                            }
                        }
                        "Comet Con Liner A" -> {
                            if (!idioma.value) {
                                nombreT = "Envoltorio de Comet Con A"
                            }
                        }
                        "Comet Con Liner B" -> {
                            if (!idioma.value) {
                                nombreT = "Envoltorio de Comet Con B"
                            }
                        }
                        "Comet Con Liner C" -> {
                            if (!idioma.value) {
                                nombreT = "Envoltorio de Comet Con C"
                            }
                        }
                        "Comet Con Liner D" -> {
                            if (!idioma.value) {
                                nombreT = "Envoltorio de Comet Con D"
                            }
                        }
                        "Easter Liner A" -> {
                            if (!idioma.value) {
                                nombreT = "Envoltorio de Pascua A"
                            }
                        }
                        "Easter Liner B" -> {
                            if (!idioma.value) {
                                nombreT = "Envoltorio de Pascua B"
                            }
                        }
                        "Easter Liner C" -> {
                            if (!idioma.value) {
                                nombreT = "Envoltorio de Pascua C"
                            }
                        }
                        "Easter Liner D" -> {
                            if (!idioma.value) {
                                nombreT = "Envoltorio de Pascua D"
                            }
                        }
                        "Film Fest Liner A" -> {
                            if (!idioma.value) {
                                nombreT = "Envoltorio de Festival de las Películas A"
                            }
                        }
                        "Film Fest Liner B" -> {
                            if (!idioma.value) {
                                nombreT = "Envoltorio de Festival de las Películas B"
                            }
                        }
                        "Film Fest Liner C" -> {
                            if (!idioma.value) {
                                nombreT = "Envoltorio de Festival de las Películas C"
                            }
                        }
                        "Film Fest Liner D" -> {
                            if (!idioma.value) {
                                nombreT = "Envoltorio de Festival de las Películas D"
                            }
                        }
                        "Full-Bake Alarm" -> {
                            if (!idioma.value) {
                                nombreT = "Alarma de todo hecho"
                            }
                        }
                        "Half-Quarter Alarm" -> {
                            if (!idioma.value) {
                                nombreT = "Alarma de medio cuarto"
                            }
                        }
                        "Halloween Liner A" -> {
                            if (!idioma.value) {
                                nombreT = "Envoltorio de Halloween A"
                            }
                        }
                        "Halloween Liner B" -> {
                            if (!idioma.value) {
                                nombreT = "Envoltorio de Halloween B"
                            }
                        }
                        "Halloween Liner C" -> {
                            if (!idioma.value) {
                                nombreT = "Envoltorio de Halloween C"
                            }
                        }
                        "Halloween Liner D" -> {
                            if (!idioma.value) {
                                nombreT = "Envoltorio de Halloween D"
                            }
                        }
                        "Inner Ring" -> {
                            if (!idioma.value) {
                                nombreT = "Sección interior"
                            }
                        }
                        "Inner Ring and Center" -> {
                            if (!idioma.value) {
                                nombreT = "Sección interior y centro"
                            }
                        }
                        "Large Bag" -> {
                            if (!idioma.value) {
                                nombreT = "Bolsa grande"
                            }
                        }
                        "Large Cup" -> {
                            if (!idioma.value) {
                                nombreT = "Vaso grande"
                            }
                        }
                        "Left Diagonal" -> {
                            if (!idioma.value) {
                                nombreT = "Diagonal izquierda"
                            }
                        }
                        "Left Half" -> {
                            if (!idioma.value) {
                                nombreT = "Mitad izquierda"
                            }
                        }
                        "Light-Bake Alarm" -> {
                            if (!idioma.value) {
                                nombreT = "Alarma de poco hecho"
                            }
                        }
                        "Maple Mornings Liner A" -> {
                            if (!idioma.value) {
                                nombreT = "Envoltorio de Mañanas de Arce A"
                            }
                        }
                        "Maple Mornings Liner B" -> {
                            if (!idioma.value) {
                                nombreT = "Envoltorio de Mañanas de Arce B"
                            }
                        }
                        "Maple Mornings Liner C" -> {
                            if (!idioma.value) {
                                nombreT = "Envoltorio de Mañanas de Arce C"
                            }
                        }
                        "Maple Mornings Liner D" -> {
                            if (!idioma.value) {
                                nombreT = "Envoltorio de Mañanas de Arce D"
                            }
                        }
                        "Medium Bag" -> {
                            if (!idioma.value) {
                                nombreT = "Bolsa mediana"
                            }
                        }
                        "Medium Cup" -> {
                            if (!idioma.value) {
                                nombreT = "Vaso mediano"
                            }
                        }
                        "New Year Liner A" -> {
                            if (!idioma.value) {
                                nombreT = "Envoltorio de Año Nuevo A"
                            }
                        }
                        "New Year Liner B" -> {
                            if (!idioma.value) {
                                nombreT = "Envoltorio de Año Nuevo B"
                            }
                        }
                        "New Year Liner C" -> {
                            if (!idioma.value) {
                                nombreT = "Envoltorio de Año Nuevo C"
                            }
                        }
                        "New Year Liner D" -> {
                            if (!idioma.value) {
                                nombreT = "Envoltorio de Año Nuevo D"
                            }
                        }
                        "Normal Liner A" -> {
                            if (!idioma.value) {
                                nombreT = "Envoltorio Normal A"
                            }
                        }
                        "Normal Liner B" -> {
                            if (!idioma.value) {
                                nombreT = "Envoltorio Normal B"
                            }
                        }
                        "Normal Liner C" -> {
                            if (!idioma.value) {
                                nombreT = "Envoltorio Normal C"
                            }
                        }
                        "Normal Liner D" -> {
                            if (!idioma.value) {
                                nombreT = "Envoltorio Normal D"
                            }
                        }
                        "Onionfest Liner A" -> {
                            if (!idioma.value) {
                                nombreT = "Envoltorio del Festival de las Cebollas A"
                            }
                        }
                        "Onionfest Liner B" -> {
                            if (!idioma.value) {
                                nombreT = "Envoltorio del Festival de las Cebollas B"
                            }
                        }
                        "Onionfest Liner C" -> {
                            if (!idioma.value) {
                                nombreT = "Envoltorio del Festival de las Cebollas C"
                            }
                        }
                        "Onionfest Liner D" -> {
                            if (!idioma.value) {
                                nombreT = "Envoltorio del Festival de las Cebollas D"
                            }
                        }
                        "Outer Ring" -> {
                            if (!idioma.value) {
                                nombreT = "Sección exterior"
                            }
                        }
                        "Outer Ring and Center" -> {
                            if (!idioma.value) {
                                nombreT = "Sección exterior y centro"
                            }
                        }
                        "Paddy Liner A" -> {
                            if (!idioma.value) {
                                nombreT = "Envoltorio de San Patricio A"
                            }
                        }
                        "Paddy Liner B" -> {
                            if (!idioma.value) {
                                nombreT = "Envoltorio de San Patricio B"
                            }
                        }
                        "Paddy Liner C" -> {
                            if (!idioma.value) {
                                nombreT = "Envoltorio de San Patricio C"
                            }
                        }
                        "Paddy Liner D" -> {
                            if (!idioma.value) {
                                nombreT = "Envoltorio de San Patricio D"
                            }
                        }
                        "Pirate Liner A" -> {
                            if (!idioma.value) {
                                nombreT = "Envoltorio Pirata A"
                            }
                        }
                        "Pirate Liner B" -> {
                            if (!idioma.value) {
                                nombreT = "Envoltorio Pirata B"
                            }
                        }
                        "Pirate Liner C" -> {
                            if (!idioma.value) {
                                nombreT = "Envoltorio Pirata C"
                            }
                        }
                        "Pirate Liner D" -> {
                            if (!idioma.value) {
                                nombreT = "Envoltorio Pirata D"
                            }
                        }
                        "Quarter-and-Half Alarm" -> {
                            if (!idioma.value) {
                                nombreT = "Alarma de un cuarto y medio"
                            }
                        }
                        "Regular Alarm" -> {
                            if (!idioma.value) {
                                nombreT = "Alarma normal"
                            }
                        }
                        "Right Diagonal" -> {
                            if (!idioma.value) {
                                nombreT = "Diagonal derecha"
                            }
                        }
                        "Right Half" -> {
                            if (!idioma.value) {
                                nombreT = "Mitad derecha"
                            }
                        }
                        "Small Bag" -> {
                            if (!idioma.value) {
                                nombreT = "Bolsa pequeña"
                            }
                        }
                        "Small Cup" -> {
                            if (!idioma.value) {
                                nombreT = "Vaso pequeño"
                            }
                        }
                        "Smooth Alarm" -> {
                            if (!idioma.value) {
                                nombreT = "Alarma de muy batido"
                            }
                        }
                        "Square-Cut" -> {
                            if (!idioma.value) {
                                nombreT = "Corte de cuadrados"
                            }
                        }
                        "Starlight Liner A" -> {
                            if (!idioma.value) {
                                nombreT = "Envoltorio de Estrellas A"
                            }
                        }
                        "Starlight Liner B" -> {
                            if (!idioma.value) {
                                nombreT = "Envoltorio de Estrellas B"
                            }
                        }
                        "Starlight Liner C" -> {
                            if (!idioma.value) {
                                nombreT = "Envoltorio de Estrellas C"
                            }
                        }
                        "Starlight Liner D" -> {
                            if (!idioma.value) {
                                nombreT = "Envoltorio de Estrellas D"
                            }
                        }
                        "Summer Liner A" -> {
                            if (!idioma.value) {
                                nombreT = "Envoltorio de Verano A"
                            }
                        }
                        "Summer Liner B" -> {
                            if (!idioma.value) {
                                nombreT = "Envoltorio de Verano B"
                            }
                        }
                        "Summer Liner C" -> {
                            if (!idioma.value) {
                                nombreT = "Envoltorio de Verano C"
                            }
                        }
                        "Summer Liner D" -> {
                            if (!idioma.value) {
                                nombreT = "Envoltorio de Verano D"
                            }
                        }
                        "Thanksgiving Liner A" -> {
                            if (!idioma.value) {
                                nombreT = "Envoltorio de Acción de Gracias A"
                            }
                        }
                        "Thanksgiving Liner B" -> {
                            if (!idioma.value) {
                                nombreT = "Envoltorio de Acción de Gracias B"
                            }
                        }
                        "Thanksgiving Liner C" -> {
                            if (!idioma.value) {
                                nombreT = "Envoltorio de Acción de Gracias C"
                            }
                        }
                        "Thanksgiving Liner D" -> {
                            if (!idioma.value) {
                                nombreT = "Envoltorio de Acción de Gracias D"
                            }
                        }
                        "Three-Quarters-and-Half Alarm" -> {
                            if (!idioma.value) {
                                nombreT = "Alarma de tres cuartos y medio"
                            }
                        }
                        "To the Left" -> {
                            if (!idioma.value) {
                                nombreT = "A la izquierda"
                            }
                        }
                        "To the Right" -> {
                            if (!idioma.value) {
                                nombreT = "A la derecha"
                            }
                        }
                        "Top Half" -> {
                            if (!idioma.value) {
                                nombreT = "Mitad superior"
                            }
                        }
                        "Top-Left Corner" -> {
                            if (!idioma.value) {
                                nombreT = "Esquina superior izquierda"
                            }
                        }
                        "Top-Right Corner" -> {
                            if (!idioma.value) {
                                nombreT = "Esquina superior derecha"
                            }
                        }
                        "Two-Quarters-and-Half Alarm" -> {
                            if (!idioma.value) {
                                nombreT = "Alarma de dos cuartos y medio"
                            }
                        }
                        "Valentine Liner A" -> {
                            if (!idioma.value) {
                                nombreT = "Envoltorio de San Valentín A"
                            }
                        }
                        "Valentine Liner B" -> {
                            if (!idioma.value) {
                                nombreT = "Envoltorio de San Valentín B"
                            }
                        }
                        "Valentine Liner C" -> {
                            if (!idioma.value) {
                                nombreT = "Envoltorio de San Valentín C"
                            }
                        }
                        "Valentine Liner D" -> {
                            if (!idioma.value) {
                                nombreT = "Envoltorio de San Valentín C"
                            }
                        }
                        "Well-Done Alarm" -> {
                            if (!idioma.value) {
                                nombreT = "Alarma de muy hecho"
                            }
                        }
                    }

                    mostrar = if (busqueda.length == 1) {
                        nombreT.startsWith(busqueda, true)
                    } else {
                        (nombreT.contains(
                            busqueda,
                            true
                        )) || (busqueda.isBlank())
                    }

                    mostrar2 = ((equipment[it].number.toString() == busqueda) || (busqueda.isBlank()))

                    if (mostrar || mostrar2) {
                        ListItem(
                            headlineContent = { Text(nombreT) },
                            Modifier.clickable { busqueda = nombreT })
                    }
                }
            }
        }
        val textoMostrar = if (idioma.value) {
            "Number of equipment shown: "
        } else {
            "Número de utensilios mostrados: "
        }
        Row {
            Text(textoMostrar)
            val textoNumero = if (idioma.value) {
                "Insert a number"
            } else {
                "Introduce un número"
            }
            TextField(
                value = valor,
                onValueChange = { texto -> valor = texto },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                placeholder = { Text(textoNumero) })
        }
        Row {
            val textoOrden = if (idioma.value) {
                "Order by: "
            } else {
                "Ordenar por: "
            }
            Text(textoOrden)
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
                    val textoOrden1 = if (idioma.value) {
                        "number"
                    } else {
                        "número"
                    }
                    DropdownMenuItem(
                        text = { Text(textoOrden1) },
                        onClick = { parametro = "number" })
                    val textoOrden2 = if (idioma.value) {
                        "name"
                    } else {
                        "nombre"
                    }
                    DropdownMenuItem(
                        text = { Text(textoOrden2) },
                        onClick = { parametro = "name" })
                    val textoOrden3 = if (idioma.value) {
                        "type"
                    } else {
                        "tipo"
                    }
                    DropdownMenuItem(
                        text = { Text(textoOrden3) },
                        onClick = { parametro = "type" })
                    val textoOrden4 = if (idioma.value) {
                        "holiday"
                    } else {
                        "celebración"
                    }
                    DropdownMenuItem(
                        text = { Text(textoOrden4) },
                        onClick = { parametro = "holiday" })
                    val textoOrden5 = if (idioma.value) {
                        "food"
                    } else {
                        "comida"
                    }
                    DropdownMenuItem(
                        text = { Text(textoOrden5) },
                        onClick = { parametro = "food" })
                    val textoOrden6 = if (idioma.value) {
                        "game"
                    } else {
                        "juego"
                    }
                    DropdownMenuItem(
                        text = { Text(textoOrden6) },
                        onClick = { parametro = "game" })
                    val textoOrden7 = if (idioma.value) {
                        "orderPart"
                    } else {
                        "partePedido"
                    }
                    DropdownMenuItem(
                        text = { Text(textoOrden7) },
                        onClick = { parametro = "orderPart" })
                }
            }
        }
        Row {
            val textoFiltro = if (idioma.value) {
                "Filter: "
            } else {
                "Filtro: "
            }
            Text(textoFiltro)
            ExposedDropdownMenuBox(expanded = activado2,
                onExpandedChange = { activado2 = !activado2 }) {
                TextField(modifier = Modifier.menuAnchor(),
                    readOnly = true,
                    value = filtroParametro,
                    onValueChange = {}
                )
                DropdownMenu(expanded = activado2, onDismissRequest = { activado2 = false }) {
                    val textoFiltro1 = if (idioma.value) {
                        "none"
                    } else {
                        "ninguno"
                    }
                    DropdownMenuItem(
                        text = { Text(textoFiltro1) },
                        onClick = { filtroParametro = "none" })
                    when (parametro) {
                        "type" -> {
                            val textoFiltro2 = if (idioma.value) {
                                "Alarm"
                            } else {
                                "Alarma"
                            }
                            DropdownMenuItem(
                                text = { Text(textoFiltro2) },
                                onClick = { filtroParametro = "Alarm" })
                            val textoFiltro3 = if (idioma.value) {
                                "Bag Size"
                            } else {
                                "Tamaño de bolsa"
                            }
                            DropdownMenuItem(
                                text = { Text(textoFiltro3) },
                                onClick = { filtroParametro = "Bag Size" })
                            val textoFiltro4 = if (idioma.value) {
                                "Cup Size"
                            } else {
                                "Tamaño de vaso"
                            }
                            DropdownMenuItem(
                                text = { Text(textoFiltro4) },
                                onClick = { filtroParametro = "Cup Size" })
                            val textoFiltro5 = if (idioma.value) {
                                "Cut"
                            } else {
                                "Corte"
                            }
                            DropdownMenuItem(
                                text = { Text(textoFiltro5) },
                                onClick = { filtroParametro = "Cut" })
                            val textoFiltro6 = if (idioma.value) {
                                "Liner"
                            } else {
                                "Envoltorio"
                            }
                            DropdownMenuItem(
                                text = { Text(textoFiltro6) },
                                onClick = { filtroParametro = "Liner" })
                            val textoFiltro7 = if (idioma.value) {
                                "Position"
                            } else {
                                "Posición"
                            }
                            DropdownMenuItem(
                                text = { Text(textoFiltro7) },
                                onClick = { filtroParametro = "Position" })
                        }
                        "holiday" -> {
                            val textoFiltro8 = if (idioma.value) {
                                "Standard"
                            } else {
                                "Estándar"
                            }
                            DropdownMenuItem(
                                text = { Text(textoFiltro8) },
                                onClick = { filtroParametro = "Standard" })
                            val textoFiltro9 = if (idioma.value) {
                                "Baseball Season"
                            } else {
                                "Temporada de baseball"
                            }
                            DropdownMenuItem(
                                text = { Text(textoFiltro9) },
                                onClick = { filtroParametro = "Baseball Season" })
                            val textoFiltro10 = if (idioma.value) {
                                "Big Top Carnival"
                            } else {
                                "Gran Feria"
                            }
                            DropdownMenuItem(
                                text = { Text(textoFiltro10) },
                                onClick = { filtroParametro = "Big Top Carnival" })
                            val textoFiltro11 = if (idioma.value) {
                                "Cherry Blossom Festival"
                            } else {
                                "Festival de la flor de cerezo"
                            }
                            DropdownMenuItem(
                                text = { Text(textoFiltro11) },
                                onClick = { filtroParametro = "Cherry Blossom Festival" })
                            val textoFiltro12 = if (idioma.value) {
                                "Christmas"
                            } else {
                                "Navidad"
                            }
                            DropdownMenuItem(
                                text = { Text(textoFiltro12) },
                                onClick = { filtroParametro = "Christmas" })
                            DropdownMenuItem(
                                text = { Text("Cinco de Mayo") },
                                onClick = { filtroParametro = "Cinco de Mayo" })
                            DropdownMenuItem(
                                text = { Text("Comet Con") },
                                onClick = { filtroParametro = "Comet Con" })
                            val textoFiltro13 = if (idioma.value) {
                                "Easter"
                            } else {
                                "Pascua"
                            }
                            DropdownMenuItem(
                                text = { Text(textoFiltro13) },
                                onClick = { filtroParametro = "Easter" })
                            val textoFiltro14 = if (idioma.value) {
                                "Film Fest"
                            } else {
                                "Festival de las películas"
                            }
                            DropdownMenuItem(
                                text = { Text(textoFiltro14) },
                                onClick = { filtroParametro = "Film Fest" })
                            DropdownMenuItem(
                                text = { Text("Halloween") },
                                onClick = { filtroParametro = "Halloween" })
                            val textoFiltro15 = if (idioma.value) {
                                "Maple Mornings"
                            } else {
                                "Mañanas de arce"
                            }
                            DropdownMenuItem(
                                text = { Text(textoFiltro15) },
                                onClick = { filtroParametro = "Maple Mornings" })
                            val textoFiltro16 = if (idioma.value) {
                                "New Year"
                            } else {
                                "Año Nuevo"
                            }
                            DropdownMenuItem(
                                text = { Text(textoFiltro16) },
                                onClick = { filtroParametro = "New Year" })
                            val textoFiltro17 = if (idioma.value) {
                                "Onionfest"
                            } else {
                                "Festival de las cebollas"
                            }
                            DropdownMenuItem(
                                text = { Text(textoFiltro17) },
                                onClick = { filtroParametro = "Onionfest" })
                            val textoFiltro18 = if (idioma.value) {
                                "Pirate Bash"
                            } else {
                                "Fiesta de los piratas"
                            }
                            DropdownMenuItem(
                                text = { Text(textoFiltro18) },
                                onClick = { filtroParametro = "Pirate Bash" })
                            val textoFiltro19 = if (idioma.value) {
                                "St Paddy"
                            } else {
                                "San Patricio"
                            }
                            DropdownMenuItem(
                                text = { Text(textoFiltro19) },
                                onClick = { filtroParametro = "St Paddy" })
                            val textoFiltro20 = if (idioma.value) {
                                "Starlight"
                            } else {
                                "Festival de las estrellas"
                            }
                            DropdownMenuItem(
                                text = { Text(textoFiltro20) },
                                onClick = { filtroParametro = "Starlight" })
                            val textoFiltro21 = if (idioma.value) {
                                "Summer Luau"
                            } else {
                                "Luau de verano"
                            }
                            DropdownMenuItem(
                                text = { Text(textoFiltro21) },
                                onClick = { filtroParametro = "Summer Luau" })
                            val textoFiltro22 = if (idioma.value) {
                                "Thanksgiving"
                            } else {
                                "Acción de gracias"
                            }
                            DropdownMenuItem(
                                text = { Text(textoFiltro22) },
                                onClick = { filtroParametro = "Thanksgiving" })
                            val textoFiltro23 = if (idioma.value) {
                                "Valentines"
                            } else {
                                "San Valentín"
                            }
                            DropdownMenuItem(
                                text = { Text(textoFiltro23) },
                                onClick = { filtroParametro = "Valentines" })
                        }
                        "food" -> {
                            val textoFiltro24 = if (idioma.value) {
                                "Breakfast"
                            } else {
                                "Desayuno"
                            }
                            DropdownMenuItem(
                                text = { Text(textoFiltro24) },
                                onClick = { filtroParametro = "Breakfast" })
                            val textoFiltro25 = if (idioma.value) {
                                "Burger"
                            } else {
                                "Hamburguesa"
                            }
                            DropdownMenuItem(
                                text = { Text(textoFiltro25) },
                                onClick = { filtroParametro = "Burger" })
                            val textoFiltro26 = if (idioma.value) {
                                "Chicken Sandwich"
                            } else {
                                "Hamburguesa de pollo"
                            }
                            DropdownMenuItem(
                                text = { Text(textoFiltro26) },
                                onClick = { filtroParametro = "Chicken Sandwich" })
                            val textoFiltro27 = if (idioma.value) {
                                "Chicken Wings"
                            } else {
                                "Pollo frito"
                            }
                            DropdownMenuItem(
                                text = { Text(textoFiltro27) },
                                onClick = { filtroParametro = "Chicken Wings" })
                            DropdownMenuItem(
                                text = { Text("Cupcakes") },
                                onClick = { filtroParametro = "Cupcakes" })
                            val textoFiltro28 = if (idioma.value) {
                                "Hot Dog"
                            } else {
                                "Perrito caliente"
                            }
                            DropdownMenuItem(
                                text = { Text(textoFiltro28) },
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
                            val textoFiltro29 = if (idioma.value) {
                                "Pie"
                            } else {
                                "Pastel"
                            }
                            DropdownMenuItem(
                                text = { Text(textoFiltro29) },
                                onClick = { filtroParametro = "Pie" })
                            DropdownMenuItem(
                                text = { Text("Pizza") },
                                onClick = { filtroParametro = "Pizza" })
                            DropdownMenuItem(
                                text = { Text("Sandwich") },
                                onClick = { filtroParametro = "Sandwich" })
                            val textoFiltro30 = if (idioma.value) {
                                "Sundae"
                            } else {
                                "Helado"
                            }
                            DropdownMenuItem(
                                text = { Text(textoFiltro30) },
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
                            val textoFiltro31 = if (idioma.value) {
                                "Bag Size"
                            } else {
                                "Tamaño de bolsa"
                            }
                            DropdownMenuItem(
                                text = { Text(textoFiltro31) },
                                onClick = { filtroParametro = "Bag Size" })
                            val textoFiltro32 = if (idioma.value) {
                                "Burger Time"
                            } else {
                                "Tiempo para hamburguesas"
                            }
                            DropdownMenuItem(
                                text = { Text(textoFiltro32) },
                                onClick = { filtroParametro = "Burger Time" })
                            val textoFiltro33 = if (idioma.value) {
                                "Chicken Wings Position"
                            } else {
                                "Posición para pollo frito"
                            }
                            DropdownMenuItem(
                                text = { Text(textoFiltro33) },
                                onClick = { filtroParametro = "Chicken Wings Position" })
                            val textoFiltro34 = if (idioma.value) {
                                "Cut"
                            } else {
                                "Corte"
                            }
                            DropdownMenuItem(
                                text = { Text(textoFiltro34) },
                                onClick = { filtroParametro = "Cut" })
                            val textoFiltro35 = if (idioma.value) {
                                "Drink Size"
                            } else {
                                "Tamaño de bebida"
                            }
                            DropdownMenuItem(
                                text = { Text(textoFiltro35) },
                                onClick = { filtroParametro = "Drink Size" })
                            val textoFiltro36 = if (idioma.value) {
                                "Liner"
                            } else {
                                "Envoltorio"
                            }
                            DropdownMenuItem(
                                text = { Text(textoFiltro36) },
                                onClick = { filtroParametro = "Liner" })
                            val textoFiltro37 = if (idioma.value) {
                                "Mocha Size"
                            } else {
                                "Tamaño de mocha"
                            }
                            DropdownMenuItem(
                                text = { Text(textoFiltro37) },
                                onClick = { filtroParametro = "Mocha Size" })
                            val textoFiltro38 = if (idioma.value) {
                                "Paleta Topping Position"
                            } else {
                                "Posición para toppings para paletas"
                            }
                            DropdownMenuItem(
                                text = { Text(textoFiltro38) },
                                onClick = { filtroParametro = "Paleta Topping Position" })
                            val textoFiltro39 = if (idioma.value) {
                                "Pasta Time"
                            } else {
                                "Tiempo para pasta"
                            }
                            DropdownMenuItem(
                                text = { Text(textoFiltro39) },
                                onClick = { filtroParametro = "Pasta Time" })
                            val textoFiltro40 = if (idioma.value) {
                                "Pie Topping Position"
                            } else {
                                "Posición para toppings para pastel"
                            }
                            DropdownMenuItem(
                                text = { Text(textoFiltro40) },
                                onClick = { filtroParametro = "`Pie Topping Position" })
                            val textoFiltro41 = if (idioma.value) {
                                "Pizza Time"
                            } else {
                                "Tiempo para pizza"
                            }
                            DropdownMenuItem(
                                text = { Text(textoFiltro41) },
                                onClick = { filtroParametro = "Pizza Time" })
                            val textoFiltro42 = if (idioma.value) {
                                "Pizza Topping Position"
                            } else {
                                "Posición para toppings para pizza"
                            }
                            DropdownMenuItem(
                                text = { Text(textoFiltro42) },
                                onClick = { filtroParametro = "Pizza Topping Position" })
                            val textoFiltro43 = if (idioma.value) {
                                "Sandwich Time"
                            } else {
                                "Tiempo para sandwich"
                            }
                            DropdownMenuItem(
                                text = { Text(textoFiltro43) },
                                onClick = { filtroParametro = "Sandwich Time" })
                            val textoFiltro44 = if (idioma.value) {
                                "Slush Size"
                            } else {
                                "Tamaño de batido"
                            }
                            DropdownMenuItem(
                                text = { Text(textoFiltro44) },
                                onClick = { filtroParametro = "Slush Size" })
                            val textoFiltro45 = if (idioma.value) {
                                "Soda Size"
                            } else {
                                "Tamaño de refresco"
                            }
                            DropdownMenuItem(
                                text = { Text(textoFiltro45) },
                                onClick = { filtroParametro = "Soda Size" })
                            val textoFiltro46 = if (idioma.value) {
                                "Sundae Size"
                            } else {
                                "Tamaño de helado"
                            }
                            DropdownMenuItem(
                                text = { Text(textoFiltro46) },
                                onClick = { filtroParametro = "Sundae Size" })
                            val textoFiltro47 = if (idioma.value) {
                                "Sundae Time"
                            } else {
                                "Tiempo para helados"
                            }
                            DropdownMenuItem(
                                text = { Text(textoFiltro47) },
                                onClick = { filtroParametro = "Sundae Time" })
                        }
                    }
                }
            }
        }
        Row {
            Button(onClick = {
                numero = valor.toLong()
                if (filtroParametro == "none") {
                    db.filtrarUtensilio(numero, parametro)
                } else db.filtrarUtensilio(numero, parametro, filtroParametro)
            }) {
                val textoAplicar = if (idioma.value) {
                    "Apply conditions"
                } else {
                    "Aplicar condiciones"
                }
                Text(textoAplicar)
            }
        }

        LazyColumn {

            listaMostrar = if (aleatorio.value) {
                utensiliosRandom
            } else {
                equipment
            }
            items(listaMostrar.size) {

                @Suppress("VARIABLE_WITH_REDUNDANT_INITIALIZER") var mostrar = false
                @Suppress("VARIABLE_WITH_REDUNDANT_INITIALIZER") var mostrar2 = false

                var nombreT = equipment[it].name
                when (equipment[it].name) {
                    "4-Cut" -> {
                        if (!idioma.value) {
                            nombreT = "Corte de 4"
                        }
                    }
                    "6-Cut" -> {
                        if (!idioma.value) {
                            nombreT = "Corte de 6"
                        }
                    }
                    "8-Cut" -> {
                        if (!idioma.value) {
                            nombreT = "Corte de 8"
                        }
                    }
                    "Al Dente Alarm" -> {
                        if (!idioma.value) {
                            nombreT = "Alarma Al Dente"
                        }
                    }
                    "All but Bottom-Left" -> {
                        if (!idioma.value) {
                            nombreT = "Todo menos la esquina inferior izquierda"
                        }
                    }
                    "All but Bottom-Right" -> {
                        if (!idioma.value) {
                            nombreT = "Todo menos la esquina inferior derecha"
                        }
                    }
                    "All but Top-Left" -> {
                        if (!idioma.value) {
                            nombreT = "Todo menos la esquina superior izquierda"
                        }
                    }
                    "All but Top-Right" -> {
                        if (!idioma.value) {
                            nombreT = "Todo menos la esquina superior derecha"
                        }
                    }
                    "Around" -> {
                        if (!idioma.value) {
                            nombreT = "Alrededor"
                        }
                    }
                    "Baseball Liner A" -> {
                        if (!idioma.value) {
                            nombreT = "Envoltorio de Baseball A"
                        }
                    }
                    "Baseball Liner B" -> {
                        if (!idioma.value) {
                            nombreT = "Envoltorio de Baseball B"
                        }
                    }
                    "Baseball Liner C" -> {
                        if (!idioma.value) {
                            nombreT = "Envoltorio de Baseball C"
                        }
                    }
                    "Baseball Liner D" -> {
                        if (!idioma.value) {
                            nombreT = "Envoltorio de Baseball D"
                        }
                    }
                    "Bottom Half" -> {
                        if (!idioma.value) {
                            nombreT = "Mitad inferior"
                        }
                    }
                    "Bottom-Left Corner" -> {
                        if (!idioma.value) {
                            nombreT = "Esquina inferior izquierda"
                        }
                    }
                    "Bottom-Right Corner" -> {
                        url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FBottom-right_Corner.png?alt=media&token=dcb8bd93-7f8f-48fe-9527-4c4ff0c5f8d5"
                        if (!idioma.value) {
                            nombreT = "Esquina inferior derecha"
                        }
                    }
                    "Carnival Liner A" -> {
                        if (!idioma.value) {
                            nombreT = "Envoltorio de Feria A"
                        }
                    }
                    "Carnival Liner B" -> {
                        if (!idioma.value) {
                            nombreT = "Envoltorio de Feria B"
                        }
                    }
                    "Carnival Liner C" -> {
                        if (!idioma.value) {
                            nombreT = "Envoltorio de Feria C"
                        }
                    }
                    "Carnival Liner D" -> {
                        if (!idioma.value) {
                            nombreT = "Envoltorio de Feria D"
                        }
                    }
                    "Center" -> {
                        if (!idioma.value) {
                            nombreT = "Centro"
                        }
                    }
                    "Cherry Blossom Liner A" -> {
                        if (!idioma.value) {
                            nombreT = "Envoltorio de Flor de Cerezo A"
                        }
                    }
                    "Cherry Blossom Liner B" -> {
                        if (!idioma.value) {
                            nombreT = "Envoltorio de Flor de Cerezo B"
                        }
                    }
                    "Cherry Blossom Liner C" -> {
                        if (!idioma.value) {
                            nombreT = "Envoltorio de Flor de Cerezo C"
                        }
                    }
                    "Cherry Blossom Liner D" -> {
                        if (!idioma.value) {
                            nombreT = "Envoltorio de Flor de Cerezo D"
                        }
                    }
                    "Christmas Liner A" -> {
                        if (!idioma.value) {
                            nombreT = "Envoltorio de Navidad A"
                        }
                    }
                    "Christmas Liner B" -> {
                        if (!idioma.value) {
                            nombreT = "Envoltorio de Navidad B"
                        }
                    }
                    "Christmas Liner C" -> {
                        if (!idioma.value) {
                            nombreT = "Envoltorio de Navidad C"
                        }
                    }
                    "Christmas Liner D" -> {
                        if (!idioma.value) {
                            nombreT = "Envoltorio de Navidad D"
                        }
                    }
                    "Chunky Alarm" -> {
                        if (!idioma.value) {
                            nombreT = "Alarma de poco batido"
                        }
                    }
                    "Cinco de Mayo Liner A" -> {
                        if (!idioma.value) {
                            nombreT = "Envoltorio de Cinco de Mayo A"
                        }
                    }
                    "Cinco de Mayo Liner B" -> {
                        if (!idioma.value) {
                            nombreT = "Envoltorio de Cinco de Mayo B"
                        }
                    }
                    "Cinco de Mayo Liner C" -> {
                        if (!idioma.value) {
                            nombreT = "Envoltorio de Cinco de Mayo C"
                        }
                    }
                    "Cinco de Mayo Liner D" -> {
                        if (!idioma.value) {
                            nombreT = "Envoltorio de Cinco de Mayo D"
                        }
                    }
                    "Comet Con Liner A" -> {
                        if (!idioma.value) {
                            nombreT = "Envoltorio de Comet Con A"
                        }
                    }
                    "Comet Con Liner B" -> {
                        if (!idioma.value) {
                            nombreT = "Envoltorio de Comet Con B"
                        }
                    }
                    "Comet Con Liner C" -> {
                        if (!idioma.value) {
                            nombreT = "Envoltorio de Comet Con C"
                        }
                    }
                    "Comet Con Liner D" -> {
                        if (!idioma.value) {
                            nombreT = "Envoltorio de Comet Con D"
                        }
                    }
                    "Easter Liner A" -> {
                        if (!idioma.value) {
                            nombreT = "Envoltorio de Pascua A"
                        }
                    }
                    "Easter Liner B" -> {
                        if (!idioma.value) {
                            nombreT = "Envoltorio de Pascua B"
                        }
                    }
                    "Easter Liner C" -> {
                        if (!idioma.value) {
                            nombreT = "Envoltorio de Pascua C"
                        }
                    }
                    "Easter Liner D" -> {
                        if (!idioma.value) {
                            nombreT = "Envoltorio de Pascua D"
                        }
                    }
                    "Film Fest Liner A" -> {
                        if (!idioma.value) {
                            nombreT = "Envoltorio de Festival de las Películas A"
                        }
                    }
                    "Film Fest Liner B" -> {
                        if (!idioma.value) {
                            nombreT = "Envoltorio de Festival de las Películas B"
                        }
                    }
                    "Film Fest Liner C" -> {
                        if (!idioma.value) {
                            nombreT = "Envoltorio de Festival de las Películas C"
                        }
                    }
                    "Film Fest Liner D" -> {
                        if (!idioma.value) {
                            nombreT = "Envoltorio de Festival de las Películas D"
                        }
                    }
                    "Full-Bake Alarm" -> {
                        if (!idioma.value) {
                            nombreT = "Alarma de todo hecho"
                        }
                    }
                    "Half-Quarter Alarm" -> {
                        if (!idioma.value) {
                            nombreT = "Alarma de medio cuarto"
                        }
                    }
                    "Halloween Liner A" -> {
                        if (!idioma.value) {
                            nombreT = "Envoltorio de Halloween A"
                        }
                    }
                    "Halloween Liner B" -> {
                        if (!idioma.value) {
                            nombreT = "Envoltorio de Halloween B"
                        }
                    }
                    "Halloween Liner C" -> {
                        if (!idioma.value) {
                            nombreT = "Envoltorio de Halloween C"
                        }
                    }
                    "Halloween Liner D" -> {
                        if (!idioma.value) {
                            nombreT = "Envoltorio de Halloween D"
                        }
                    }
                    "Inner Ring" -> {
                        if (!idioma.value) {
                            nombreT = "Sección interior"
                        }
                    }
                    "Inner Ring and Center" -> {
                        if (!idioma.value) {
                            nombreT = "Sección interior y centro"
                        }
                    }
                    "Large Bag" -> {
                        if (!idioma.value) {
                            nombreT = "Bolsa grande"
                        }
                    }
                    "Large Cup" -> {
                        if (!idioma.value) {
                            nombreT = "Vaso grande"
                        }
                    }
                    "Left Diagonal" -> {
                        if (!idioma.value) {
                            nombreT = "Diagonal izquierda"
                        }
                    }
                    "Left Half" -> {
                        if (!idioma.value) {
                            nombreT = "Mitad izquierda"
                        }
                    }
                    "Light-Bake Alarm" -> {
                        if (!idioma.value) {
                            nombreT = "Alarma de poco hecho"
                        }
                    }
                    "Maple Mornings Liner A" -> {
                        if (!idioma.value) {
                            nombreT = "Envoltorio de Mañanas de Arce A"
                        }
                    }
                    "Maple Mornings Liner B" -> {
                        if (!idioma.value) {
                            nombreT = "Envoltorio de Mañanas de Arce B"
                        }
                    }
                    "Maple Mornings Liner C" -> {
                        if (!idioma.value) {
                            nombreT = "Envoltorio de Mañanas de Arce C"
                        }
                    }
                    "Maple Mornings Liner D" -> {
                        if (!idioma.value) {
                            nombreT = "Envoltorio de Mañanas de Arce D"
                        }
                    }
                    "Medium Bag" -> {
                        if (!idioma.value) {
                            nombreT = "Bolsa mediana"
                        }
                    }
                    "Medium Cup" -> {
                        if (!idioma.value) {
                            nombreT = "Vaso mediano"
                        }
                    }
                    "New Year Liner A" -> {
                        if (!idioma.value) {
                            nombreT = "Envoltorio de Año Nuevo A"
                        }
                    }
                    "New Year Liner B" -> {
                        if (!idioma.value) {
                            nombreT = "Envoltorio de Año Nuevo B"
                        }
                    }
                    "New Year Liner C" -> {
                        if (!idioma.value) {
                            nombreT = "Envoltorio de Año Nuevo C"
                        }
                    }
                    "New Year Liner D" -> {
                        if (!idioma.value) {
                            nombreT = "Envoltorio de Año Nuevo D"
                        }
                    }
                    "Normal Liner A" -> {
                        if (!idioma.value) {
                            nombreT = "Envoltorio Normal A"
                        }
                    }
                    "Normal Liner B" -> {
                        if (!idioma.value) {
                            nombreT = "Envoltorio Normal B"
                        }
                    }
                    "Normal Liner C" -> {
                        if (!idioma.value) {
                            nombreT = "Envoltorio Normal C"
                        }
                    }
                    "Normal Liner D" -> {
                        if (!idioma.value) {
                            nombreT = "Envoltorio Normal D"
                        }
                    }
                    "Onionfest Liner A" -> {
                        if (!idioma.value) {
                            nombreT = "Envoltorio del Festival de las Cebollas A"
                        }
                    }
                    "Onionfest Liner B" -> {
                        if (!idioma.value) {
                            nombreT = "Envoltorio del Festival de las Cebollas B"
                        }
                    }
                    "Onionfest Liner C" -> {
                        if (!idioma.value) {
                            nombreT = "Envoltorio del Festival de las Cebollas C"
                        }
                    }
                    "Onionfest Liner D" -> {
                        if (!idioma.value) {
                            nombreT = "Envoltorio del Festival de las Cebollas D"
                        }
                    }
                    "Outer Ring" -> {
                        if (!idioma.value) {
                            nombreT = "Sección exterior"
                        }
                    }
                    "Outer Ring and Center" -> {
                        if (!idioma.value) {
                            nombreT = "Sección exterior y centro"
                        }
                    }
                    "Paddy Liner A" -> {
                        if (!idioma.value) {
                            nombreT = "Envoltorio de San Patricio A"
                        }
                    }
                    "Paddy Liner B" -> {
                        if (!idioma.value) {
                            nombreT = "Envoltorio de San Patricio B"
                        }
                    }
                    "Paddy Liner C" -> {
                        if (!idioma.value) {
                            nombreT = "Envoltorio de San Patricio C"
                        }
                    }
                    "Paddy Liner D" -> {
                        if (!idioma.value) {
                            nombreT = "Envoltorio de San Patricio D"
                        }
                    }
                    "Pirate Liner A" -> {
                        if (!idioma.value) {
                            nombreT = "Envoltorio Pirata A"
                        }
                    }
                    "Pirate Liner B" -> {
                        if (!idioma.value) {
                            nombreT = "Envoltorio Pirata B"
                        }
                    }
                    "Pirate Liner C" -> {
                        if (!idioma.value) {
                            nombreT = "Envoltorio Pirata C"
                        }
                    }
                    "Pirate Liner D" -> {
                        if (!idioma.value) {
                            nombreT = "Envoltorio Pirata D"
                        }
                    }
                    "Quarter-and-Half Alarm" -> {
                        if (!idioma.value) {
                            nombreT = "Alarma de un cuarto y medio"
                        }
                    }
                    "Regular Alarm" -> {
                        if (!idioma.value) {
                            nombreT = "Alarma normal"
                        }
                    }
                    "Right Diagonal" -> {
                        if (!idioma.value) {
                            nombreT = "Diagonal derecha"
                        }
                    }
                    "Right Half" -> {
                        if (!idioma.value) {
                            nombreT = "Mitad derecha"
                        }
                    }
                    "Small Bag" -> {
                        if (!idioma.value) {
                            nombreT = "Bolsa pequeña"
                        }
                    }
                    "Small Cup" -> {
                        if (!idioma.value) {
                            nombreT = "Vaso pequeño"
                        }
                    }
                    "Smooth Alarm" -> {
                        if (!idioma.value) {
                            nombreT = "Alarma de muy batido"
                        }
                    }
                    "Square-Cut" -> {
                        if (!idioma.value) {
                            nombreT = "Corte de cuadrados"
                        }
                    }
                    "Starlight Liner A" -> {
                        if (!idioma.value) {
                            nombreT = "Envoltorio de Estrellas A"
                        }
                    }
                    "Starlight Liner B" -> {
                        if (!idioma.value) {
                            nombreT = "Envoltorio de Estrellas B"
                        }
                    }
                    "Starlight Liner C" -> {
                        if (!idioma.value) {
                            nombreT = "Envoltorio de Estrellas C"
                        }
                    }
                    "Starlight Liner D" -> {
                        if (!idioma.value) {
                            nombreT = "Envoltorio de Estrellas D"
                        }
                    }
                    "Summer Liner A" -> {
                        if (!idioma.value) {
                            nombreT = "Envoltorio de Verano A"
                        }
                    }
                    "Summer Liner B" -> {
                        if (!idioma.value) {
                            nombreT = "Envoltorio de Verano B"
                        }
                    }
                    "Summer Liner C" -> {
                        if (!idioma.value) {
                            nombreT = "Envoltorio de Verano C"
                        }
                    }
                    "Summer Liner D" -> {
                        if (!idioma.value) {
                            nombreT = "Envoltorio de Verano D"
                        }
                    }
                    "Thanksgiving Liner A" -> {
                        if (!idioma.value) {
                            nombreT = "Envoltorio de Acción de Gracias A"
                        }
                    }
                    "Thanksgiving Liner B" -> {
                        if (!idioma.value) {
                            nombreT = "Envoltorio de Acción de Gracias B"
                        }
                    }
                    "Thanksgiving Liner C" -> {
                        if (!idioma.value) {
                            nombreT = "Envoltorio de Acción de Gracias C"
                        }
                    }
                    "Thanksgiving Liner D" -> {
                        if (!idioma.value) {
                            nombreT = "Envoltorio de Acción de Gracias D"
                        }
                    }
                    "Three-Quarters-and-Half Alarm" -> {
                        if (!idioma.value) {
                            nombreT = "Alarma de tres cuartos y medio"
                        }
                    }
                    "To the Left" -> {
                        if (!idioma.value) {
                            nombreT = "A la izquierda"
                        }
                    }
                    "To the Right" -> {
                        if (!idioma.value) {
                            nombreT = "A la derecha"
                        }
                    }
                    "Top Half" -> {
                        if (!idioma.value) {
                            nombreT = "Mitad superior"
                        }
                    }
                    "Top-Left Corner" -> {
                        if (!idioma.value) {
                            nombreT = "Esquina superior izquierda"
                        }
                    }
                    "Top-Right Corner" -> {
                        if (!idioma.value) {
                            nombreT = "Esquina superior derecha"
                        }
                    }
                    "Two-Quarters-and-Half Alarm" -> {
                        if (!idioma.value) {
                            nombreT = "Alarma de dos cuartos y medio"
                        }
                    }
                    "Valentine Liner A" -> {
                        if (!idioma.value) {
                            nombreT = "Envoltorio de San Valentín A"
                        }
                    }
                    "Valentine Liner B" -> {
                        if (!idioma.value) {
                            nombreT = "Envoltorio de San Valentín B"
                        }
                    }
                    "Valentine Liner C" -> {
                        if (!idioma.value) {
                            nombreT = "Envoltorio de San Valentín C"
                        }
                    }
                    "Valentine Liner D" -> {
                        if (!idioma.value) {
                            nombreT = "Envoltorio de San Valentín C"
                        }
                    }
                    "Well-Done Alarm" -> {
                        if (!idioma.value) {
                            nombreT = "Alarma de muy hecho"
                        }
                    }
                }

                mostrar = if (filtro.length == 1) {
                    (nombreT.startsWith(filtro, ignoreCase = true))
                } else {
                    (nombreT.contains(filtro, ignoreCase = true)) || (filtro.isBlank())
                }

                mostrar2 = ((listaMostrar[it].number.toString() == busqueda) || (filtro.isBlank()))

                if (mostrar || mostrar2) {
                    val numero2 = listaMostrar[it].number
                    val nombre = listaMostrar[it].name
                    val tipo = listaMostrar[it].type
                    val celebracion = listaMostrar[it].holiday
                    val comida = listaMostrar[it].food
                    val juego = listaMostrar[it].game
                    val parte = listaMostrar[it].orderPart

                    var nombreT = nombre
                    var tipoT = tipo
                    var comidaT = comida
                    var celebracionT = celebracion
                    var parteT = parte

                    when (nombre) {
                        "4-Cut" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2F4-Cut.png?alt=media&token=042653a2-226a-4dfc-9d50-b226c7ec32b9"
                            if (!idioma.value) {
                                nombreT = "Corte de 4"
                                tipoT = "Corte"
                                comidaT = "Pizza"
                                celebracionT = "Estándar"
                                parteT = "Corte"
                            }
                        }
                        "6-Cut" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2F6-Cut.png?alt=media&token=e6157e8c-f153-455c-8edf-85e96e5032ff"
                            if (!idioma.value) {
                                nombreT = "Corte de 6"
                                tipoT = "Corte"
                                comidaT = "Pizza"
                                celebracionT = "Estándar"
                                parteT = "Corte"
                            }
                        }
                        "8-Cut" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2F8-Cut.png?alt=media&token=23822db8-f388-4e19-8cfd-621c035ffab8"
                            if (!idioma.value) {
                                nombreT = "Corte de 8"
                                tipoT = "Corte"
                                comidaT = "Pizza"
                                celebracionT = "Estándar"
                                parteT = "Corte"
                            }
                        }
                        "Al Dente Alarm" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FAl_Dente_Alarm.png?alt=media&token=4ced455f-992e-42b1-a2a9-9bcc0d277a3b"
                            if (!idioma.value) {
                                nombreT = "Alarma Al Dente"
                                tipoT = "Alarma"
                                comidaT = "Pasta"
                                celebracionT = "Estándar"
                                parteT = "Tiempo para pasta"
                            }
                        }
                        "All but Bottom-Left" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FAll_but_bottom-left.png?alt=media&token=86131a04-3a3b-4aeb-aa27-5a0f40ccaa38"
                            if (!idioma.value) {
                                nombreT = "Todo menos la esquina inferior izquierda"
                                tipoT = "Posición"
                                comidaT = "Pizza"
                                celebracionT = "Estándar"
                                parteT = "Posición para toppings para pizza"
                            }
                        }
                        "All but Bottom-Right" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FAll_but_bottom-right.png?alt=media&token=c8af251a-3937-4c1d-9143-b51ea37bea00"
                            if (!idioma.value) {
                                nombreT = "Todo menos la esquina inferior derecha"
                                tipoT = "Posición"
                                comidaT = "Pizza"
                                celebracionT = "Estándar"
                                parteT = "Posición para toppings para pizza"
                            }
                        }
                        "All but Top-Left" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FAll_but_top-left.png?alt=media&token=408c16a2-3fc8-4cba-89c4-70aebf581c2d"
                            if (!idioma.value) {
                                nombreT = "Todo menos la esquina superior izquierda"
                                tipoT = "Posición"
                                comidaT = "Pizza"
                                celebracionT = "Estándar"
                                parteT = "Posición para toppings para pizza"
                            }
                        }
                        "All but Top-Right" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FAll_but_top-right.png?alt=media&token=3b29cc8a-cb43-4f6a-a16a-452d50526bf7"
                            if (!idioma.value) {
                                nombreT = "Todo menos la esquina superior derecha"
                                tipoT = "Posición"
                                comidaT = "Pizza"
                                celebracionT = "Estándar"
                                parteT = "Posición para toppings para pizza"
                            }
                        }
                        "Around" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FAround.png?alt=media&token=1c4bd2c0-9d41-466c-ae70-32e67eba91b2"
                            if (!idioma.value) {
                                nombreT = "Alrededor"
                                tipoT = "Posición"
                                comidaT = "Pizza/Pollo frito/Pastel"
                                celebracionT = "Estándar"
                                parteT = "Posición para toppings para pizza/Posición para pollo frito/Posición para toppings para pastel"
                            }
                        }
                        "Baseball Liner A" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FBaseball_Liner_A.png?alt=media&token=7374c873-1818-4acc-9827-517818b82a41"
                            if (!idioma.value) {
                                nombreT = "Envoltorio de Baseball A"
                                tipoT = "Envoltorio"
                                comidaT = "Cupcakes"
                                celebracionT = "Temporada de baseball"
                                parteT = "Envoltorio"
                            }
                        }
                        "Baseball Liner B" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FBaseball_Liner_B.png?alt=media&token=5c61eff0-d603-42cb-80be-bb426932c873"
                            if (!idioma.value) {
                                nombreT = "Envoltorio de Baseball B"
                                tipoT = "Envoltorio"
                                comidaT = "Cupcakes"
                                celebracionT = "Temporada de baseball"
                                parteT = "Envoltorio"
                            }
                        }
                        "Baseball Liner C" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FBaseball_Liner_C.png?alt=media&token=14a08176-2ebb-47ae-aa25-4a7bcf540a5f"
                            if (!idioma.value) {
                                nombreT = "Envoltorio de Baseball C"
                                tipoT = "Envoltorio"
                                comidaT = "Cupcakes"
                                celebracionT = "Temporada de baseball"
                                parteT = "Envoltorio"
                            }
                        }
                        "Baseball Liner D" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FBaseball_Liner_D.png?alt=media&token=91d11c2f-64bd-498c-8902-9f0ccc10606f"
                            if (!idioma.value) {
                                nombreT = "Envoltorio de Baseball D"
                                tipoT = "Envoltorio"
                                comidaT = "Cupcakes"
                                celebracionT = "Temporada de baseball"
                                parteT = "Envoltorio"
                            }
                        }
                        "Bottom Half" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FBottom_Half.png?alt=media&token=27bc0921-49d0-478b-9536-7e9de062a321"
                            if (!idioma.value) {
                                nombreT = "Mitad inferior"
                                tipoT = "Posición"
                                comidaT = "Pizza"
                                celebracionT = "Estándar"
                                parteT = "Posición para toppings para pizza"
                            }
                        }
                        "Bottom-Left Corner" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FBottom-left_Corner.png?alt=media&token=cdc923a2-9c15-43bf-beb0-dbe50c25d119"
                            if (!idioma.value) {
                                nombreT = "Esquina inferior izquierda"
                                tipoT = "Posición"
                                comidaT = "Pizza"
                                celebracionT = "Estándar"
                                parteT = "Posición para toppings para pizza"
                            }
                        }
                        "Bottom-Right Corner" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FBottom-right_Corner.png?alt=media&token=dcb8bd93-7f8f-48fe-9527-4c4ff0c5f8d5"
                            if (!idioma.value) {
                                nombreT = "Esquina inferior derecha"
                                tipoT = "Posición"
                                comidaT = "Pizza"
                                celebracionT = "Estándar"
                                parteT = "Posición para toppings para pizza"
                            }
                        }
                        "Carnival Liner A" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FCarnival_Liner_A.png?alt=media&token=d018c3d2-33aa-448e-955e-dad1ba8a6026"
                            if (!idioma.value) {
                                nombreT = "Envoltorio de Feria A"
                                tipoT = "Envoltorio"
                                comidaT = "Cupcakes"
                                celebracionT = "Gran Feria"
                                parteT = "Envoltorio"
                            }
                        }
                        "Carnival Liner B" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FCarnival_Liner_B.png?alt=media&token=3d212cd3-848c-4f3e-80b9-8537e1e46537"
                            if (!idioma.value) {
                                nombreT = "Envoltorio de Feria B"
                                tipoT = "Envoltorio"
                                comidaT = "Cupcakes"
                                celebracionT = "Gran Feria"
                                parteT = "Envoltorio"
                            }
                        }
                        "Carnival Liner C" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FCarnival_Liner_C.png?alt=media&token=4d6b5a5c-e46b-42c6-b5c5-b24135aa20dd"
                            if (!idioma.value) {
                                nombreT = "Envoltorio de Feria C"
                                tipoT = "Envoltorio"
                                comidaT = "Cupcakes"
                                celebracionT = "Gran Feria"
                                parteT = "Envoltorio"
                            }
                        }
                        "Carnival Liner D" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FCarnival_Liner_D.png?alt=media&token=8e942d51-4122-41f2-9f44-cf7c73d39d8d"
                            if (!idioma.value) {
                                nombreT = "Envoltorio de Feria D"
                                tipoT = "Envoltorio"
                                comidaT = "Cupcakes"
                                celebracionT = "Gran Feria"
                                parteT = "Envoltorio"
                            }
                        }
                        "Center" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FCenter.png?alt=media&token=6b831577-1da2-4649-acc6-847d34c9e269"
                            if (!idioma.value) {
                                nombreT = "Centro"
                                tipoT = "Posición"
                                comidaT = "Pastel"
                                celebracionT = "Estándar"
                                parteT = "Posición para toppings para pastel"
                            }
                        }
                        "Cherry Blossom Liner A" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FCherry_Blossom_Liner_A.png?alt=media&token=1cea4a60-8d01-4b99-8c8a-53827038bc44"
                            if (!idioma.value) {
                                nombreT = "Envoltorio de Flor de Cerezo A"
                                tipoT = "Envoltorio"
                                comidaT = "Cupcakes"
                                celebracionT = "Festival de la flor de cerezo"
                                parteT = "Envoltorio"
                            }
                        }
                        "Cherry Blossom Liner B" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FCherry_Blossom_Liner_B.png?alt=media&token=eeb998ab-9903-4c2c-b0ce-c68ce6cbf4e8"
                            if (!idioma.value) {
                                nombreT = "Envoltorio de Flor de Cerezo B"
                                tipoT = "Envoltorio"
                                comidaT = "Cupcakes"
                                celebracionT = "Festival de la flor de cerezo"
                                parteT = "Envoltorio"
                            }
                        }
                        "Cherry Blossom Liner C" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FCherry_Blossom_Liner_C.png?alt=media&token=437325a2-1aa9-45ef-8ce3-d79f84bec6db"
                            if (!idioma.value) {
                                nombreT = "Envoltorio de Flor de Cerezo C"
                                tipoT = "Envoltorio"
                                comidaT = "Cupcakes"
                                celebracionT = "Festival de la flor de cerezo"
                                parteT = "Envoltorio"
                            }
                        }
                        "Cherry Blossom Liner D" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FCherry_Blossom_Liner_D.png?alt=media&token=fd6a8b59-9420-411f-84b8-b8b0d8fc4e41"
                            if (!idioma.value) {
                                nombreT = "Envoltorio de Flor de Cerezo D"
                                tipoT = "Envoltorio"
                                comidaT = "Cupcakes"
                                celebracionT = "Festival de la flor de cerezo"
                                parteT = "Envoltorio"
                            }
                        }
                        "Christmas Liner A" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FChristmas_Liner_A.png?alt=media&token=d28c3748-d81a-4552-91a0-6112dd7147ae"
                            if (!idioma.value) {
                                nombreT = "Envoltorio de Navidad A"
                                tipoT = "Envoltorio"
                                comidaT = "Cupcakes"
                                celebracionT = "Navidad"
                                parteT = "Envoltorio"
                            }
                        }
                        "Christmas Liner B" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FChristmas_Liner_B.png?alt=media&token=1accce2e-2dbd-4989-ad98-8066cd3c3880"
                            if (!idioma.value) {
                                nombreT = "Envoltorio de Navidad B"
                                tipoT = "Envoltorio"
                                comidaT = "Cupcakes"
                                celebracionT = "Navidad"
                                parteT = "Envoltorio"
                            }
                        }
                        "Christmas Liner C" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FChristmas_Liner_C.png?alt=media&token=b47fceea-4e1f-4510-9a09-ffb151ef7bfd"
                            if (!idioma.value) {
                                nombreT = "Envoltorio de Navidad C"
                                tipoT = "Envoltorio"
                                comidaT = "Cupcakes"
                                celebracionT = "Navidad"
                                parteT = "Envoltorio"
                            }
                        }
                        "Christmas Liner D" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FChristmas_Liner_D.png?alt=media&token=4ff5687f-72d1-49ab-b538-bdc68aaae47f"
                            if (!idioma.value) {
                                nombreT = "Envoltorio de Navidad D"
                                tipoT = "Envoltorio"
                                comidaT = "Cupcakes"
                                celebracionT = "Navidad"
                                parteT = "Envoltorio"
                            }
                        }
                        "Chunky Alarm" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FChunky_Alarm.png?alt=media&token=ff8f6c20-90d0-4e57-b323-5c6fd82830a7"
                            if (!idioma.value) {
                                nombreT = "Alarma de poco batido"
                                tipoT = "Alarma"
                                comidaT = "Helado"
                                celebracionT = "Estándar"
                                parteT = "Tiempo para helados"
                            }
                        }
                        "Cinco de Mayo Liner A" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FCinco_de_Mayo_Liner_A.png?alt=media&token=d4b4fdc0-971d-483b-b861-777601b0d1e5"
                            if (!idioma.value) {
                                nombreT = "Envoltorio de Cinco de Mayo A"
                                tipoT = ""
                                comidaT = ""
                                celebracionT = ""
                                parteT = ""
                            }
                        }
                        "Cinco de Mayo Liner B" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FCinco_de_Mayo_Liner_B.png?alt=media&token=2e52913d-d0bb-4ac7-b9b2-451018be9003"
                            if (!idioma.value) {
                                nombreT = "Envoltorio de Cinco de Mayo B"
                                tipoT = "Envoltorio"
                                comidaT = "Cupcakes"
                                celebracionT = "Cinco de Mayo"
                                parteT = "Envoltorio"
                            }
                        }
                        "Cinco de Mayo Liner C" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FCinco_de_Mayo_Liner_C.png?alt=media&token=d7297008-06d5-4c92-aff0-2b65a8d830ad"
                            if (!idioma.value) {
                                nombreT = "Envoltorio de Cinco de Mayo C"
                                tipoT = "Envoltorio"
                                comidaT = "Cupcakes"
                                celebracionT = "Cinco de Mayo"
                                parteT = "Envoltorio"
                            }
                        }
                        "Cinco de Mayo Liner D" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FCinco_de_Mayo_Liner_D%2B.png?alt=media&token=0f29c858-b74d-4ff3-8eb8-b4e2cac37cf3"
                            if (!idioma.value) {
                                nombreT = "Envoltorio de Cinco de Mayo D"
                                tipoT = "Envoltorio"
                                comidaT = "Cupcakes"
                                celebracionT = "Cinco de Mayo"
                                parteT = "Envoltorio"
                            }
                        }
                        "Comet Con Liner A" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FComet_Con_Liner_A.png?alt=media&token=31aed6f2-6d66-459b-8430-384adc132280"
                            if (!idioma.value) {
                                nombreT = "Envoltorio de Comet Con A"
                                tipoT = "Envoltorio"
                                comidaT = "Cupcakes"
                                celebracionT = "Comet Con"
                                parteT = "Envoltorio"
                            }
                        }
                        "Comet Con Liner B" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FComet_Con_Liner_B.png?alt=media&token=3e9a283f-9dfb-4d57-9099-168a054c4016"
                            if (!idioma.value) {
                                nombreT = "Envoltorio de Comet Con B"
                                tipoT = "Envoltorio"
                                comidaT = "Cupcakes"
                                celebracionT = "Comet Con"
                                parteT = "Envoltorio"
                            }
                        }
                        "Comet Con Liner C" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FComet_Con_Liner_C.png?alt=media&token=fab640fc-6142-47b9-ba61-2d0f38e92bd9"
                            if (!idioma.value) {
                                nombreT = "Envoltorio de Comet Con C"
                                tipoT = "Envoltorio"
                                comidaT = "Cupcakes"
                                celebracionT = "Comet Con"
                                parteT = "Envoltorio"
                            }
                        }
                        "Comet Con Liner D" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FComet_Con_Liner_D.png?alt=media&token=22443856-4ac8-4395-b94e-6d2a68907930"
                            if (!idioma.value) {
                                nombreT = "Envoltorio de Comet Con D"
                                tipoT = "Envoltorio"
                                comidaT = "Cupcakes"
                                celebracionT = "Comet Con"
                                parteT = "Envoltorio"
                            }
                        }
                        "Easter Liner A" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FEaster_Liner_A.png?alt=media&token=8c07b369-3a70-4391-b562-9b6a12b6dd37"
                            if (!idioma.value) {
                                nombreT = "Envoltorio de Pascua A"
                                tipoT = "Envoltorio"
                                comidaT = "Cupcakes"
                                celebracionT = "Pascua"
                                parteT = "Envoltorio"
                            }
                        }
                        "Easter Liner B" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FEaster_Liner_B.png?alt=media&token=48d9b759-49c3-459c-806d-a94c62df5535"
                            if (!idioma.value) {
                                nombreT = "Envoltorio de Pascua B"
                                tipoT = "Envoltorio"
                                comidaT = "Cupcakes"
                                celebracionT = "Pascua"
                                parteT = "Envoltorio"
                            }
                        }
                        "Easter Liner C" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FEaster_Liner_C.png?alt=media&token=c4947bf9-4674-4bdc-afe4-d6eba95ea2f2"
                            if (!idioma.value) {
                                nombreT = "Envoltorio de Pascua C"
                                tipoT = "Envoltorio"
                                comidaT = "Cupcakes"
                                celebracionT = "Pascua"
                                parteT = "Envoltorio"
                            }
                        }
                        "Easter Liner D" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FEaster_Liner_D.png?alt=media&token=d5f92e1b-85f6-4cc4-91ec-607acd9aa948"
                            if (!idioma.value) {
                                nombreT = "Envoltorio de Pascua D"
                                tipoT = "Envoltorio"
                                comidaT = "Cupcakes"
                                celebracionT = "Pascua"
                                parteT = "Envoltorio"
                            }
                        }
                        "Film Fest Liner A" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FFilm_Fest_Liner_A.png?alt=media&token=0bcee6f9-0dd4-478d-a81b-b11c41d7d4f0"
                            if (!idioma.value) {
                                nombreT = "Envoltorio de Festival de las Películas A"
                                tipoT = "Envoltorio"
                                comidaT = "Cupcakes"
                                celebracionT = "Festival de las películas"
                                parteT = "Envoltorio"
                            }
                        }
                        "Film Fest Liner B" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FFilm_Fest_Liner_B.png?alt=media&token=3e78e081-ce76-4d96-9d07-6c8eb4d4c8df"
                            if (!idioma.value) {
                                nombreT = "Envoltorio de Festival de las Películas B"
                                tipoT = "Envoltorio"
                                comidaT = "Cupcakes"
                                celebracionT = "Festival de las películas"
                                parteT = "Envoltorio"
                            }
                        }
                        "Film Fest Liner C" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FFilm_Fest_Liner_C.png?alt=media&token=18b2171b-556c-4517-b0cd-dee6ebc8178f"
                            if (!idioma.value) {
                                nombreT = "Envoltorio de Festival de las Películas C"
                                tipoT = "Envoltorio"
                                comidaT = "Cupcakes"
                                celebracionT = "Festival de las películas"
                                parteT = "Envoltorio"
                            }
                        }
                        "Film Fest Liner D" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FFilm_Fest_Liner_D.png?alt=media&token=f482c655-aed6-46cc-9875-5783be5b7cb9"
                            if (!idioma.value) {
                                nombreT = "Envoltorio de Festival de las Películas D"
                                tipoT = "Envoltorio"
                                comidaT = "Cupcakes"
                                celebracionT = "Festival de las películas"
                                parteT = "Envoltorio"
                            }
                        }
                        "Full-Bake Alarm" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FFull-Bake_Alarm.png?alt=media&token=6d2a2708-9bf3-4b00-bd50-2e0698ee6854"
                            if (!idioma.value) {
                                nombreT = "Alarma de todo hecho"
                                tipoT = "Alarma"
                                comidaT = "Pizza"
                                celebracionT = "Estándar"
                                parteT = "Tiempo para pizza"
                            }
                        }
                        "Half-Quarter Alarm" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FHalf-Quarter_Alarm.png?alt=media&token=f45c8377-1f6d-49c8-b3d8-7f6604460eda"
                            if (!idioma.value) {
                                nombreT = "Alarma de medio cuarto"
                                tipoT = "Alarma"
                                comidaT = "Pizza"
                                celebracionT = "Estándar"
                                parteT = "Tiempo para pizza"
                            }
                        }
                        "Halloween Liner A" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FHalloween_Liner_A.png?alt=media&token=f92c8dc3-27fa-48d0-81aa-488ede58395f"
                            if (!idioma.value) {
                                nombreT = "Envoltorio de Halloween A"
                                tipoT = "Envoltorio"
                                comidaT = "Cupcakes"
                                celebracionT = "Halloween"
                                parteT = "Envoltorio"
                            }
                        }
                        "Halloween Liner B" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FHalloween_Liner_B.png?alt=media&token=26dd42a3-bf5d-4ccd-8b06-24acf95aa969"
                            if (!idioma.value) {
                                nombreT = "Envoltorio de Halloween B"
                                tipoT = "Envoltorio"
                                comidaT = "Cupcakes"
                                celebracionT = "Halloween"
                                parteT = "Envoltorio"
                            }
                        }
                        "Halloween Liner C" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FHalloween_Liner_C.png?alt=media&token=22c3265b-c1a0-44e0-9dcc-1267921d5a59"
                            if (!idioma.value) {
                                nombreT = "Envoltorio de Halloween C"
                                tipoT = "Envoltorio"
                                comidaT = "Cupcakes"
                                celebracionT = "Halloween"
                                parteT = "Envoltorio"
                            }
                        }
                        "Halloween Liner D" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FHalloween_Liner_D.png?alt=media&token=4540974d-7796-4945-8475-1da6d2ce6154"
                            if (!idioma.value) {
                                nombreT = "Envoltorio de Halloween D"
                                tipoT = "Envoltorio"
                                comidaT = "Cupcakes"
                                celebracionT = "Halloween"
                                parteT = "Envoltorio"
                            }
                        }
                        "Inner Ring" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FInner_Ring.png?alt=media&token=2d147f70-71f3-4f84-885a-91095be1e982"
                            if (!idioma.value) {
                                nombreT = "Sección interior"
                                tipoT = "Posición"
                                comidaT = "Pastel"
                                celebracionT = "Estándar"
                                parteT = "Posición para toppings para pastel"
                            }
                        }
                        "Inner Ring and Center" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FInner_Ring_and_Center.png?alt=media&token=29dd316d-397e-4c31-a639-a4fb0f3ccd91"
                            if (!idioma.value) {
                                nombreT = "Sección interior y centro"
                                tipoT = "Posición"
                                comidaT = "Pastel"
                                celebracionT = "Estándar"
                                parteT = "Posición para toppings para pastel"
                            }
                        }
                        "Large Bag" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FLarge_Bag.png?alt=media&token=1a5dab8e-9d58-4363-82b4-d6e514865a4b"
                            if (!idioma.value) {
                                nombreT = "Bolsa grande"
                                tipoT = "Tamaño de bolsa"
                                comidaT = "Perrito caliente"
                                celebracionT = "Estándar"
                                parteT = "Tamaño de bolsa"
                            }
                        }
                        "Large Cup" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FLarge_Cup.png?alt=media&token=e06c307a-6ce6-4bfc-8f68-7d34c7ef6fa3"
                            if (!idioma.value) {
                                nombreT = "Vaso grande"
                                tipoT = "Tamaño de vaso"
                                comidaT = "Helado/Desayuno/Perrito caliente/Mocha/Hamburguesa de pollo"
                                celebracionT = "Estándar"
                                parteT = "Tamaño de helado/Tamaño de bebida/Tamaño de refresco/Tamaño de mocha/Tamaño de batido"
                            }
                        }
                        "Left Diagonal" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FLeft_Diagonal.png?alt=media&token=223ac136-74f1-43ff-8fd3-124f3038fc40"
                            if (!idioma.value) {
                                nombreT = "Diagonal izquierda"
                                tipoT = "Posición"
                                comidaT = "Paleta"
                                celebracionT = "Estándar"
                                parteT = "Posición para toppings para paleta"
                            }
                        }
                        "Left Half" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FLeft_Half.png?alt=media&token=24b343c1-bf73-4a1d-b01a-35a0d25dbef7"
                            if (!idioma.value) {
                                nombreT = "Mitad izquierda"
                                tipoT = "Posición"
                                comidaT = "Pizza/Paleta"
                                celebracionT = "Estándar"
                                parteT = "Posición para toppings para pizza/Posición para toppings para paleta"
                            }
                        }
                        "Light-Bake Alarm" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FLight-Bake_Alarm.png?alt=media&token=2c2caa7f-7060-4aae-ad25-5f72d153fc3a"
                            if (!idioma.value) {
                                nombreT = "Alarma de poco hecho"
                                tipoT = "Alarma"
                                comidaT = "Pizza/Hamburguesa/Sandwich"
                                celebracionT = "Estándar"
                                parteT = "Tiempo para pizza/Tiempo para hamburguesas/Tiempo para sandwich"
                            }
                        }
                        "Maple Mornings Liner A" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FMaple_Mornings_Liner_A.png?alt=media&token=e6c67da3-179a-4567-9114-ff8bfbf003c7"
                            if (!idioma.value) {
                                nombreT = "Envoltorio de Mañanas de Arce A"
                                tipoT = "Envoltorio"
                                comidaT = "Cupcakes"
                                celebracionT = "Mañanas de arce"
                                parteT = "Envoltorio"
                            }
                        }
                        "Maple Mornings Liner B" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FMaple_Mornings_Liner_B.png?alt=media&token=7b0008a4-e872-414d-b409-9e1695e0c45b"
                            if (!idioma.value) {
                                nombreT = "Envoltorio de Mañanas de Arce B"
                                tipoT = "Envoltorio"
                                comidaT = "Cupcakes"
                                celebracionT = "Mañanas de arce"
                                parteT = "Envoltorio"
                            }
                        }
                        "Maple Mornings Liner C" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FMaple_Mornings_Liner_C.png?alt=media&token=be03faa1-962f-427d-9154-997774b13c29"
                            if (!idioma.value) {
                                nombreT = "Envoltorio de Mañanas de Arce C"
                                tipoT = "Envoltorio"
                                comidaT = "Cupcakes"
                                celebracionT = "Mañanas de arce"
                                parteT = "Envoltorio"
                            }
                        }
                        "Maple Mornings Liner D" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FMaple_Mornings_Liner_D.png?alt=media&token=96f89b03-05fb-4128-8eeb-13251f9e28d6"
                            if (!idioma.value) {
                                nombreT = "Envoltorio de Mañanas de Arce D"
                                tipoT = "Envoltorio"
                                comidaT = "Cupcakes"
                                celebracionT = "Mañanas de arce"
                                parteT = "Envoltorio"
                            }
                        }
                        "Medium Bag" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FMedium_Bag.png?alt=media&token=7f3c069a-9b14-498e-97cb-6d792a94a2a2.png"
                            if (!idioma.value) {
                                nombreT = "Bolsa mediana"
                                tipoT = "Tamaño de bolsa"
                                comidaT = "Perrito caliente"
                                celebracionT = "Estándar"
                                parteT = "Tamaño de bolsa"
                            }
                        }
                        "Medium Cup" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FMedium_Cup.png?alt=media&token=42f7f3ce-254a-46d2-966e-a6e8c775293a"
                            if (!idioma.value) {
                                nombreT = "Vaso mediano"
                                tipoT = "Tamaño de vaso"
                                comidaT = "Helado/Perrito caliente/Mocha/Hamburguesa de pollo"
                                celebracionT = "Estándar"
                                parteT = "Tamaño de helado/Tamaño de refresco/Tamaño de mocha/Tamaño de batido"
                            }
                        }
                        "New Year Liner A" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FNew_Year_Liner_A.png?alt=media&token=9eee3380-0b1e-459d-952d-f608db81c053"
                            if (!idioma.value) {
                                nombreT = "Envoltorio de Año Nuevo A"
                                tipoT = "Envoltorio"
                                comidaT = "Cupcakes"
                                celebracionT = "Año Nuevo"
                                parteT = "Envoltorio"
                            }
                        }
                        "New Year Liner B" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FNew_Year_Liner_B.png?alt=media&token=80d0748f-0137-4055-ad0a-b360b4704d69"
                            if (!idioma.value) {
                                nombreT = "Envoltorio de Año Nuevo B"
                                tipoT = "Envoltorio"
                                comidaT = "Cupcakes"
                                celebracionT = "Año Nuevo"
                                parteT = "Envoltorio"
                            }
                        }
                        "New Year Liner C" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FNew_Year_Liner_C.png?alt=media&token=a8988dbb-3418-449d-b462-4268005ce03f"
                            if (!idioma.value) {
                                nombreT = "Envoltorio de Año Nuevo C"
                                tipoT = "Envoltorio"
                                comidaT = "Cupcakes"
                                celebracionT = "Año Nuevo"
                                parteT = "Envoltorio"
                            }
                        }
                        "New Year Liner D" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FNew_Year_Liner_D.png?alt=media&token=7f1f5b06-e075-48db-96ad-6f624ee7f8b5"
                            if (!idioma.value) {
                                nombreT = "Envoltorio de Año Nuevo D"
                                tipoT = "Envoltorio"
                                comidaT = "Cupcakes"
                                celebracionT = "Año Nuevo"
                                parteT = "Envoltorio"
                            }
                        }
                        "Normal Liner A" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FNormal_Liner_A.png?alt=media&token=04c9af65-da10-4590-92f9-c8117c524014"
                            if (!idioma.value) {
                                nombreT = "Envoltorio Normal A"
                                tipoT = "Envoltorio"
                                comidaT = "Cupcakes"
                                celebracionT = "Estándar"
                                parteT = "Envoltorio"
                            }
                        }
                        "Normal Liner B" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FNormal_Liner_B.png?alt=media&token=cde3188f-0275-4758-8845-5c93ac690560"
                            if (!idioma.value) {
                                nombreT = "Envoltorio Normal B"
                                tipoT = "Envoltorio"
                                comidaT = "Cupcakes"
                                celebracionT = "Estándar"
                                parteT = "Envoltorio"
                            }
                        }
                        "Normal Liner C" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FNormal_Liner_C.png?alt=media&token=fddf78f0-7296-464f-bc0a-2e8bb7f81994"
                            if (!idioma.value) {
                                nombreT = "Envoltorio Normal C"
                                tipoT = "Envoltorio"
                                comidaT = "Cupcakes"
                                celebracionT = "Estándar"
                                parteT = "Envoltorio"
                            }
                        }
                        "Normal Liner D" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FNormal_Liner_D.png?alt=media&token=ec608dd2-24c9-4a7c-921c-f4fcaf71870a"
                            if (!idioma.value) {
                                nombreT = "Envoltorio Normal D"
                                tipoT = "Envoltorio"
                                comidaT = "Cupcakes"
                                celebracionT = "Estándar"
                                parteT = "Envoltorio"
                            }
                        }
                        "Onionfest Liner A" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FOnionfest_Liner_A.png?alt=media&token=3d54e6a4-9d06-4a29-a67b-eafdc835c969"
                            if (!idioma.value) {
                                nombreT = "Envoltorio del Festival de las Cebollas A"
                                tipoT = "Envoltorio"
                                comidaT = "Cupcakes"
                                celebracionT = "Festival de las cebollas"
                                parteT = "Envoltorio"
                            }
                        }
                        "Onionfest Liner B" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FOnionfest_Liner_B.png?alt=media&token=d945a8d0-385b-4f2b-9fa5-692628062724"
                            if (!idioma.value) {
                                nombreT = "Envoltorio del Festival de las Cebollas B"
                                tipoT = "Envoltorio"
                                comidaT = "Cupcakes"
                                celebracionT = "Festival de las cebollas"
                                parteT = "Envoltorio"
                            }
                        }
                        "Onionfest Liner C" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FOnionfest_Liner_C.png?alt=media&token=202dd41e-38e7-4e03-baad-70ae168208f9"
                            if (!idioma.value) {
                                nombreT = "Envoltorio del Festival de las Cebollas C"
                                tipoT = "Envoltorio"
                                comidaT = "Cupcakes"
                                celebracionT = "Festival de las cebollas"
                                parteT = "Envoltorio"
                            }
                        }
                        "Onionfest Liner D" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FOnionfest_Liner_D.png?alt=media&token=52e6389e-2278-4da3-aa08-c2619e4fb89b"
                            if (!idioma.value) {
                                nombreT = "Envoltorio del Festival de las Cebollas D"
                                tipoT = "Envoltorio"
                                comidaT = "Cupcakes"
                                celebracionT = "Festival de las cebollas"
                                parteT = "Envoltorio"
                            }
                        }
                        "Outer Ring" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FOuter_Ring.png?alt=media&token=7301fef5-775b-440a-b349-7b58ab661e50"
                            if (!idioma.value) {
                                nombreT = "Sección exterior"
                                tipoT = "Posición"
                                comidaT = "Pastel"
                                celebracionT = "Estándar"
                                parteT = "Posición de toppings de pastel"
                            }
                        }
                        "Outer Ring and Center" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FOuter_Ring_and_Center.png?alt=media&token=23c8dbeb-f8ad-4894-8f39-e61377df81b2"
                            if (!idioma.value) {
                                nombreT = "Sección exterior y centro"
                                tipoT = "Posición"
                                comidaT = "Pastel"
                                celebracionT = "Estándar"
                                parteT = "Posición de toppings de pastel"
                            }
                        }
                        "Paddy Liner A" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FSt_Paddy_Liner_A.png?alt=media&token=ac110b94-f7bf-411a-a12a-c0bd3ee44138"
                            if (!idioma.value) {
                                nombreT = "Envoltorio de San Patricio A"
                                tipoT = "Envoltorio"
                                comidaT = "Cupcakes"
                                celebracionT = "San Patricio"
                                parteT = "Envoltorio"
                            }
                        }
                        "Paddy Liner B" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FSt_Paddy_Liner_B.png?alt=media&token=91e8302e-e64b-495e-8f5b-b3b76d79cc13"
                            if (!idioma.value) {
                                nombreT = "Envoltorio de San Patricio B"
                                tipoT = "Envoltorio"
                                comidaT = "Cupcakes"
                                celebracionT = "San Patricio"
                                parteT = "Envoltorio"
                            }
                        }
                        "Paddy Liner C" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FSt_Paddy_Liner_C.png?alt=media&token=48921cb3-619c-405b-a9f4-5ce04c4cf417"
                            if (!idioma.value) {
                                nombreT = "Envoltorio de San Patricio C"
                                tipoT = "Envoltorio"
                                comidaT = "Cupcakes"
                                celebracionT = "San Patricio"
                                parteT = "Envoltorio"
                            }
                        }
                        "Paddy Liner D" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FSt_Paddy_Liner_D.png?alt=media&token=a08602ce-aed6-43d3-acd6-b2452eed94c7"
                            if (!idioma.value) {
                                nombreT = "Envoltorio de San Patricio D"
                                tipoT = "Envoltorio"
                                comidaT = "Cupcakes"
                                celebracionT = "San Patricio"
                                parteT = "Envoltorio"
                            }
                        }
                        "Pirate Liner A" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FPirate_Liner_A.png?alt=media&token=be8456f5-e226-4f01-90ad-5f8c014fef07"
                            if (!idioma.value) {
                                nombreT = "Envoltorio Pirata A"
                                tipoT = "Envoltorio"
                                comidaT = "Cupcakes"
                                celebracionT = "Fiesta de los piratas"
                                parteT = "Envoltorio"
                            }
                        }
                        "Pirate Liner B" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FPirate_Liner_B.png?alt=media&token=0417fd9f-456c-4532-80c4-d92364c68d00"
                            if (!idioma.value) {
                                nombreT = "Envoltorio Pirata B"
                                tipoT = "Envoltorio"
                                comidaT = "Cupcakes"
                                celebracionT = "Fiesta de los piratas"
                                parteT = "Envoltorio"
                            }
                        }
                        "Pirate Liner C" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FPirate_Liner_C.png?alt=media&token=c023b19f-f501-4f9e-ab35-5ba16ba2c568"
                            if (!idioma.value) {
                                nombreT = "Envoltorio Pirata C"
                                tipoT = "Envoltorio"
                                comidaT = "Cupcakes"
                                celebracionT = "Fiesta de los piratas"
                                parteT = "Envoltorio"
                            }
                        }
                        "Pirate Liner D" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FPirate_Liner_D.png?alt=media&token=0480021b-6393-4903-93cc-8b92cc59a12a"
                            if (!idioma.value) {
                                nombreT = "Envoltorio Pirata D"
                                tipoT = "Envoltorio"
                                comidaT = "Cupcakes"
                                celebracionT = "Fiesta de los piratas"
                                parteT = "Envoltorio"
                            }
                        }
                        "Quarter-and-Half Alarm" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FQuarter-and-Half_Alarm.png?alt=media&token=0356e39d-c62e-4c63-b3de-81be095d82e1"
                            if (!idioma.value) {
                                nombreT = "Alarma de un cuarto y medio"
                                tipoT = "Alarma"
                                comidaT = "Pizza"
                                celebracionT = "Estándar"
                                parteT = "Tiempo para pizza"
                            }
                        }
                        "Regular Alarm" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FRegular_Alarm.png?alt=media&token=b75aa2ea-f054-4a78-ae19-8f031c4f472e"
                            if (!idioma.value) {
                                nombreT = "Alarma normal"
                                tipoT = "Alarma"
                                comidaT = "Pizza/Hamburguesa/Helado/Pasta/Sandwich"
                                celebracionT = "Estándar"
                                parteT = "Tiempo para pizza/Tiempo para hamburguesas/Tiempo para helados/Tiempo para pasta/Tiempo para sandwich"
                            }
                        }
                        "Right Diagonal" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FRight_Diagonal.png?alt=media&token=f055778f-9627-4114-bd9d-92b35e1000cc"
                            if (!idioma.value) {
                                nombreT = "Diagonal derecha"
                                tipoT = "Posición"
                                comidaT = "Paleta"
                                celebracionT = "Estándar"
                                parteT = "Posición para toppings para paletas"
                            }
                        }
                        "Right Half" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FRight_Half.png?alt=media&token=c539839a-fca5-4551-8f0b-0dec4a19893a"
                            if (!idioma.value) {
                                nombreT = "Mitad derecha"
                                tipoT = "Posición"
                                comidaT = "Pizza/Paleta"
                                celebracionT = "Estándar"
                                parteT = "Posición para toppings para pizza/Posición para toppings para paletas"
                            }
                        }
                        "Small Bag" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FSmall_Bag.png?alt=media&token=4b86fce6-1e15-45fb-9d1b-b6945007518e"
                            if (!idioma.value) {
                                nombreT = "Bolsa pequeña"
                                tipoT = "Tamaño de bolsa"
                                comidaT = "Perrito caliente"
                                celebracionT = "Estándar"
                                parteT = "Tamaño de bolsa"
                            }
                        }
                        "Small Cup" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FSmall_Cup.png?alt=media&token=4e7cf811-dda0-41cf-bd02-1322d5b9030a"
                            if (!idioma.value) {
                                nombreT = "Vaso pequeño"
                                tipoT = "Tamaño de vaso"
                                comidaT = "Helado/Desayuno/Perrito caliente/Mocha/Hamburguesa de pollo"
                                celebracionT = "Estándar"
                                parteT = "Tamaño de helado/Tamaño de bebida/Tamaño de refresco/Tamaño de mocha/Tamaño de batido"
                            }
                        }
                        "Smooth Alarm" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FSmooth_Alarm.png?alt=media&token=657bee79-99a3-4f70-afac-9603381c1be4"
                            if (!idioma.value) {
                                nombreT = "Alarma de muy batido"
                                tipoT = "Alarma"
                                comidaT = "Helado"
                                celebracionT = "Estándar"
                                parteT = "Tiempo para helados"
                            }
                        }
                        "Square-Cut" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FSquare-Cut.png?alt=media&token=06343963-5f6d-490f-be3a-c7a24238c4d9"
                            if (!idioma.value) {
                                nombreT = "Corte de cuadrados"
                                tipoT = "Corte"
                                comidaT = "Pizza"
                                celebracionT = "Estándar"
                                parteT = "Corte"
                            }
                        }
                        "Starlight Liner A" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FStarlight_Liner_A.png?alt=media&token=20a27249-5b43-4b54-9562-a232e5d6edbf"
                            if (!idioma.value) {
                                nombreT = "Envoltorio de Estrellas A"
                                tipoT = "Envoltorio"
                                comidaT = "Cupcakes"
                                celebracionT = "Festival de las estrellas"
                                parteT = "Envoltorio"
                            }
                        }
                        "Starlight Liner B" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FStarlight_Liner_B%2B.png?alt=media&token=5b579a20-30c2-4354-810e-1ba990e0598d"
                            if (!idioma.value) {
                                nombreT = "Envoltorio de Estrellas B"
                                tipoT = "Envoltorio"
                                comidaT = "Cupcakes"
                                celebracionT = "Festival de las estrellas"
                                parteT = "Envoltorio"
                            }
                        }
                        "Starlight Liner C" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FStarlight_Liner_C.png?alt=media&token=b6a87ae0-4552-4b8d-b49a-30f4325ff7ce"
                            if (!idioma.value) {
                                nombreT = "Envoltorio de Estrellas C"
                                tipoT = "Envoltorio"
                                comidaT = "Cupcakes"
                                celebracionT = "Festival de las estrellas"
                                parteT = "Envoltorio"
                            }
                        }
                        "Starlight Liner D" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FStarlight_Liner_D.png?alt=media&token=01ca740e-eb2c-4d6b-a2e6-a29030856ca1"
                            if (!idioma.value) {
                                nombreT = "Envoltorio de Estrellas D"
                                tipoT = "Envoltorio"
                                comidaT = "Cupcakes"
                                celebracionT = "Festival de las estrellas"
                                parteT = "Envoltorio"
                            }
                        }
                        "Summer Liner A" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FSummer_Liner_A.png?alt=media&token=70b4d2b5-c111-4ba2-b7ee-211de29a2ee3"
                            if (!idioma.value) {
                                nombreT = "Envoltorio de Verano A"
                                tipoT = "Envoltorio"
                                comidaT = "Cupcakes"
                                celebracionT = "Luau de verano"
                                parteT = "Envoltorio"
                            }
                        }
                        "Summer Liner B" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FSummer_Liner_B.png?alt=media&token=94bd4d54-26b6-4711-9353-77448511bfe8"
                            if (!idioma.value) {
                                nombreT = "Envoltorio de Verano B"
                                tipoT = "Envoltorio"
                                comidaT = "Cupcakes"
                                celebracionT = "Luau de verano"
                                parteT = "Envoltorio"
                            }
                        }
                        "Summer Liner C" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FSummer_Liner_C.png?alt=media&token=81d406ef-33da-4e6f-b5c1-b06e32d3e9af"
                            if (!idioma.value) {
                                nombreT = "Envoltorio de Verano C"
                                tipoT = "Envoltorio"
                                comidaT = "Cupcakes"
                                celebracionT = "Luau de verano"
                                parteT = "Envoltorio"
                            }
                        }
                        "Summer Liner D" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FSummer_Liner_D.png?alt=media&token=8079559b-a0e0-4061-9583-da25133d5e5f"
                            if (!idioma.value) {
                                nombreT = "Envoltorio de Verano D"
                                tipoT = "Envoltorio"
                                comidaT = "Cupcakes"
                                celebracionT = "Luau de verano"
                                parteT = "Envoltorio"
                            }
                        }
                        "Thanksgiving Liner A" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FThanksgiving_Liner_A.png?alt=media&token=ae3b770a-d137-4077-9e31-92d4ad01ada1"
                            if (!idioma.value) {
                                nombreT = "Envoltorio de Acción de Gracias A"
                                tipoT = "Envoltorio"
                                comidaT = "Cupcakes"
                                celebracionT = "Acción de gracias"
                                parteT = "Envoltorio"
                            }
                        }
                        "Thanksgiving Liner B" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FThanksgiving_Liner_B.png?alt=media&token=86bf0218-5613-4859-b755-df52674f33f4"
                            if (!idioma.value) {
                                nombreT = "Envoltorio de Acción de Gracias B"
                                tipoT = "Envoltorio"
                                comidaT = "Cupcakes"
                                celebracionT = "Acción de gracias"
                                parteT = "Envoltorio"
                            }
                        }
                        "Thanksgiving Liner C" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FThanksgiving_Liner_C.png?alt=media&token=231599c0-e073-45ca-aa2d-d434fb284816"
                            if (!idioma.value) {
                                nombreT = "Envoltorio de Acción de Gracias C"
                                tipoT = "Envoltorio"
                                comidaT = "Cupcakes"
                                celebracionT = "Acción de gracias"
                                parteT = "Envoltorio"
                            }
                        }
                        "Thanksgiving Liner D" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FThanksgiving_Liner_D.png?alt=media&token=513da279-5299-4eb4-9d43-a63aae2bd41f"
                            if (!idioma.value) {
                                nombreT = "Envoltorio de Acción de Gracias D"
                                tipoT = "Envoltorio"
                                comidaT = "Cupcakes"
                                celebracionT = "Acción de gracias"
                                parteT = "Envoltorio"
                            }
                        }
                        "Three-Quarters-and-Half Alarm" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FThree-Quarters-and-Half_Alarm.png?alt=media&token=018dca14-4631-4365-9a86-8491b43674be"
                            if (!idioma.value) {
                                nombreT = "Alarma de tres cuartos y medio"
                                tipoT = "Alarma"
                                comidaT = "Pizza"
                                celebracionT = "Estándar"
                                parteT = "Tiempo para pizza"
                            }
                        }
                        "To the Left" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FTo_the_Left.png?alt=media&token=b94b98c3-66e1-49a3-9483-3700d5d67232"
                            if (!idioma.value) {
                                nombreT = "A la izquierda"
                                tipoT = "Posición"
                                comidaT = "Pollo frito"
                                celebracionT = "Estándar"
                                parteT = "Posición para pollo frito"
                            }
                        }
                        "To the Right" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FTo_the_right.png?alt=media&token=d733330a-3c91-472b-8da8-b155636dd74f"
                            if (!idioma.value) {
                                nombreT = "A la derecha"
                                tipoT = "Posición"
                                comidaT = "Pollo frito"
                                celebracionT = "Estándar"
                                parteT = "Posición para pollo frito"
                            }
                        }
                        "Top Half" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FTop_Half.png?alt=media&token=2922c2c0-a9c2-44cd-88d3-453deca327ef"
                            if (!idioma.value) {
                                nombreT = "Mitad superior"
                                tipoT = "Posición"
                                comidaT = "Pizza/Paleta"
                                celebracionT = "Estándar"
                                parteT = "Posición para toppings para pizza/Posición para toppings para paletas"
                            }
                        }
                        "Top-Left Corner" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FTop-left_Corner.png?alt=media&token=8758510b-060f-414d-bf87-d9c2c2052c9a"
                            if (!idioma.value) {
                                nombreT = "Esquina superior izquierda"
                                tipoT = "Posición"
                                comidaT = "Pizza"
                                celebracionT = "Estándar"
                                parteT = "Posición para toppings para pizza"
                            }
                        }
                        "Top-Right Corner" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FTop-right_Corner.png?alt=media&token=5d153805-5a01-411e-aa20-90fd681f76b2"
                            if (!idioma.value) {
                                nombreT = "Esquina superior derecha"
                                tipoT = "Posición"
                                comidaT = "Pizza"
                                celebracionT = "Estándar"
                                parteT = "Posición para toppings para pizza"
                            }
                        }
                        "Two-Quarters-and-Half Alarm" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FTwo-Quarters-and-Half_Alarm.png?alt=media&token=9486d56d-1e3a-42f7-8482-449cf5f65d86"
                            if (!idioma.value) {
                                nombreT = "Alarma de dos cuartos y medio"
                                tipoT = "Alarma"
                                comidaT = "Pizza"
                                celebracionT = "Estándar"
                                parteT = "Tiempo para pizza"
                            }
                        }
                        "Valentine Liner A" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FValentine_liner_A.png?alt=media&token=2833341e-bc56-4fc4-9368-436ed96ff714"
                            if (!idioma.value) {
                                nombreT = "Envoltorio de San Valentín A"
                                tipoT = "Envoltorio"
                                comidaT = "Cupcakes"
                                celebracionT = "San Valentín"
                                parteT = "Envoltorio"
                            }
                        }
                        "Valentine Liner B" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FValentine_liner_B.png?alt=media&token=2d1e3030-9039-4e99-bb28-ef1cf5cd5055"
                            if (!idioma.value) {
                                nombreT = "Envoltorio de San Valentín B"
                                tipoT = "Envoltorio"
                                comidaT = "Cupcakes"
                                celebracionT = "San Valentín"
                                parteT = "Envoltorio"
                            }
                        }
                        "Valentine Liner C" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FValentine_liner_C.png?alt=media&token=15e7d739-2e92-4250-9037-48187bfc7059"
                            if (!idioma.value) {
                                nombreT = "Envoltorio de San Valentín C"
                                tipoT = "Envoltorio"
                                comidaT = "Cupcakes"
                                celebracionT = "San Valentín"
                                parteT = "Envoltorio"
                            }
                        }
                        "Valentine Liner D" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FValentine_liner_D.png?alt=media&token=1201d71e-5681-4f7a-a19c-5024447bdce2"
                            if (!idioma.value) {
                                nombreT = "Envoltorio de San Valentín C"
                                tipoT = "Envoltorio"
                                comidaT = "Cupcakes"
                                celebracionT = "San Valentín"
                                parteT = "Envoltorio"
                            }
                        }
                        "Well-Done Alarm" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Equipment%2FWell-Done_Alarm.png?alt=media&token=2da29ea2-551c-4b11-8e35-a3eb0014600c"
                            if (!idioma.value) {
                                nombreT = "Alarma de muy hecho"
                                tipoT = "Alarma"
                                comidaT = "Pizza/Hamburguesa/Sandwich"
                                celebracionT = "Estándar"
                                parteT = "Tiempo para pizza/Tiempo para hamburguesas/Tiempo para sandwich"
                            }
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
                        val textoNumero = if (idioma.value) {
                            "number"
                        } else {
                            "número"
                        }
                        val textoNombre = if (idioma.value) {
                            "name"
                        } else {
                            "nombre"
                        }
                        val textoTipo = if (idioma.value) {
                            "type"
                        } else {
                            "tipo"
                        }
                        val textoCelebracion = if (idioma.value) {
                            "holiday"
                        } else {
                            "celebración"
                        }
                        val textoComida = if (idioma.value) {
                            "food"
                        } else {
                            "comida"
                        }
                        val textoJuego = if (idioma.value) {
                            "game"
                        } else {
                            "juego"
                        }
                        val textoParte = if (idioma.value) {
                            "order part"
                        } else {
                            "parte de pedido"
                        }
                        Text(text = "$textoNumero = $numero2")
                        Text(text = "$textoNombre = $nombreT")
                        Text(text = "$textoTipo = $tipoT")
                        Text(text = "$textoCelebracion = $celebracionT")
                        Text(text = "$textoComida = $comidaT")
                        Text(text = "$textoJuego = $juego")
                        Text(text = "$textoParte = $parteT")
                        ImagenIngrediente(url)
                    }
                }
            }
        }
    }
}