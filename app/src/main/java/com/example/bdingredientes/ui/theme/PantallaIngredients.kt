package com.example.bdingredientes.ui.theme

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.bdingredientes.clases.VMBD

@Composable
fun PantallaIngredients(){
    var db : VMBD = viewModel()
    var ingredients = db.ingredients.collectAsState().value

    DisposableEffect(db){
        db.crearListener()
        onDispose { db.borrarListener() }
    }

    Column() {
        Row() {
            Text("All ingredients", fontWeight = FontWeight.Bold)
        }
        LazyColumn(Modifier.verticalScroll(state = rememberScrollState(), enabled = true, reverseScrolling = true)) {
            items(ingredients) {
                Row() {
                    Text(text = it.toString())
                    AsyncImage(model = "https://android.com/sample_image.jpg", contentDescription = null)

                }
            }
        }
        Row() {
            Text("By Aymara and Nayara Mendoza Rodríguez, 2024")
        }
    }
}