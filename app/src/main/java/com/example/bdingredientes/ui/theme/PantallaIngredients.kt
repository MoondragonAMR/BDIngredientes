package com.example.bdingredientes.ui.theme

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.bdingredientes.clases.Aleatorio
import com.example.bdingredientes.clases.Ingredient
import com.example.bdingredientes.clases.VMBD
import com.example.bdingredientes.clases.borrar
import com.example.bdingredientes.clases.ingredientsAleatorio
import com.example.bdingredientes.clases.modificar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaIngredients(){
    var db : VMBD = viewModel()
    var ingredients = db.ingredients.collectAsState().value
    var estado by remember { mutableStateOf(false) }
    var busqueda by remember { mutableStateOf("") }
    var filtro by remember { mutableStateOf("") }

    DisposableEffect(db){
        db.crearListener()
        onDispose { db.borrarListener() }
    }

    Column() {

        Row() {
            Text("All ingredients", fontWeight = FontWeight.Bold)
        }
        SearchBar(placeholder = { Text("Busca ingredientes por nombre") },
            query = busqueda,
            onQueryChange = { textoIntroducido -> busqueda = textoIntroducido },
            onSearch = { filtro = it; estado = false },
            active = estado,
            onActiveChange = { estado = !estado }) {
            LazyColumn() {
                items(ingredients.size) {
                    if ((ingredients[it].name.contains(busqueda, true)) || (ingredients[it].type.lowercase().contains(busqueda)) || (busqueda.isBlank())) {
                        ListItem(headlineContent = { Text(ingredients[it].name) }, Modifier.clickable { busqueda = ingredients[it].name })
                    }
                }
            }
        }
        LazyColumn(
            //Modifier.verticalScroll(state = rememberScrollState(), enabled = true, reverseScrolling = true)
        ) {
            var lista: SnapshotStateList<Ingredient>? = null
            if (Aleatorio){
                lista = ingredientsAleatorio
            }
            else {
                lista = ingredients
            }
            println(" tamanyo es " + lista.size)
            items(lista.size) {
                println("Filtro es " + filtro
                       + "y la siguiente comida es " + lista[it].name)
                if ((lista[it].name.contains(filtro, ignoreCase = true)) || (filtro.isBlank())) {
                    val nombre = lista[it].name
                    val tipo = lista[it].type
                    val sabor = lista[it].flavor
                    val deCelebracion = lista[it].holidayExclusive
                    val celebracion = lista[it].holiday
                    Column(Modifier.padding(4.dp).border(width = 2.dp, color = Color.Cyan).clickable {
                        if (borrar) {
                        //db.borrarIngrediente()
                    }
                    else if (modificar) {
                        //db.modificarIngrediente()
                    }}) {
                        Text(text = "name = $nombre")
                        Text(text = "type = $tipo")
                        Text(text = "flavor = $sabor")
                        Text(text = "holiday-exclusive = $deCelebracion")
                        Text(text = "holiday = $celebracion")
                        //storage.getReference($nombre).downloadUrl.
                        AsyncImage(model = "https://android.com/sample_image.jpg", contentDescription = null)

                    }
                }
            }
        }
        Row() {
            Text("By Aymara and Nayara Mendoza Rodríguez, 2024")
        }
    }
}