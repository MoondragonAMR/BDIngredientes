package com.example.bdingredientes.ui.theme

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun PantallaIngredients(){
    var vm : ExoPlayerViewModel = viewModel()
    val contexto = LocalContext.current
    val exoplayer = vm.exoPlayer.collectAsState().value
    val duracion = vm.duracion.collectAsState().value
    val progreso = vm.progreso.collectAsState().value

    if(exoplayer == null){
        vm.crearExoPlayer(contexto)
    }


    Text("By Aymara and Nayara Mendoza Rodríguez, 2024")
}