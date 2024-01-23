package com.example.bdingredientes.ui.theme

import androidx.compose.foundation.gestures.scrollable
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bdingredientes.clases.VMBD

@Composable
fun PantallaIngredients(){
    var vm : ExoPlayerViewModel = viewModel()
    val contexto = LocalContext.current
    val exoplayer = vm.exoPlayer.collectAsState().value
    val duracion = vm.duracion.collectAsState().value
    val progreso = vm.progreso.collectAsState().value
    var db : VMBD = viewModel()
    var ingredients = db.ingredients.collectAsState().value
    
    DisposableEffect(db){
        db.crearListener()
        onDispose { db.borrarListener() }
    }

    if(exoplayer == null){
        vm.crearExoPlayer(contexto)
        vm.hacerSonarMusica(contexto)
    }

    Column() {
        Row() {
            Text("All ingredients", fontWeight = FontWeight.Bold)
        }
        LazyColumn() {
            items(ingredients) {
                Row() {
                    Text(text = it.toString())
                }
            }
        }
        Row() {
            Text("By Aymara and Nayara Mendoza Rodríguez, 2024")
        }
    }
}