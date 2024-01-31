package com.example.bdingredientes.clases

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.ModeEdit
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.bdingredientes.R
import com.example.bdingredientes.ui.theme.ExoPlayerViewModel

var Aleatorio: Boolean = false
var ingredientsAleatorio = SnapshotStateList<Ingredient>()
var borrar: Boolean = false
var modificar: Boolean = false
var puedeBorrar: Boolean = true
var puedeModificar: Boolean = true
@Composable
fun BarraInferior(navController: NavController) {
    BottomAppBar(Modifier.fillMaxWidth()) {
            Row() {
                IconButton(onClick = {navController.navigate(Rutas.Add.Ruta)}) {
                    Icon(Icons.Default.Add, contentDescription = "")
                }
                IconButton(onClick = {borrar = !borrar; puedeModificar = !puedeModificar}, enabled = puedeBorrar) {
                    Icon(Icons.Default.Delete, contentDescription = "")
                }
                IconButton(onClick = {modificar = true; puedeBorrar = false}, enabled = puedeModificar) {
                    Icon(Icons.Default.ModeEdit, contentDescription = "")
                }
            }
    }
}

@Composable
fun BarraInferiorAdd(navController: NavController) {
    var db : VMBD2 = viewModel()
    BottomAppBar(Modifier.fillMaxWidth()) {
        Row() {
            IconButton(onClick = {db.anyadirIngrediente(nombre, tipo, sabor, deCelebracion, celebracion)
                navController.popBackStack()}) {
                Icon(Icons.Default.Add, contentDescription = "")
            }
        }
    }
}

@Composable
fun BarraInferiorUpdate(navController: NavController) {
    var db : VMBD2 = viewModel()
    BottomAppBar(Modifier.fillMaxWidth()) {
        Row() {
            IconButton(onClick = {db.anyadirIngrediente(nombre, tipo, sabor, deCelebracion, celebracion)
                db.modificarIngrediente(codigo, nombre, tipo, sabor, deCelebracion, celebracion)
                modificar = false
                puedeBorrar = true
                navController.popBackStack()}) {
                Icon(Icons.Default.ModeEdit, contentDescription = "")
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BarraSuperiorUsuario(navController: NavController) {
    var vm : ExoPlayerViewModel = viewModel()
    val contexto = LocalContext.current
    val exoplayer = vm.exoPlayer.collectAsState().value

    if(exoplayer == null){
        vm.crearExoPlayer(contexto)
        vm.hacerSonarMusica(contexto)
    }
    TopAppBar(title = { Text(text = "My ingredients") }, actions = {Row() {
        Button(onClick = {Aleatorio = !Aleatorio
            }){
            if (Aleatorio) {
                Icon(painterResource(id = R.drawable.baseline_shuffle_on_24), contentDescription = "")
            } else {
                Icon(painterResource(id = R.drawable.baseline_shuffle_24), contentDescription = "")
            }
            }
        Button(onClick = {vm.PausarOSeguirMusica()}) {
            if(!vm.exoPlayer.value!!.isPlaying ){
                Icon(painterResource(id = R.drawable.baseline_music_note_24), contentDescription = "")
            }else{
                Icon(painterResource(id = R.drawable.baseline_music_off_24), contentDescription = "")
            }
        }
        Button(onClick = {
            navController.navigate(Rutas.General.Ruta)
        }) {
            Icon(painterResource(id = R.drawable.baseline_list_alt_24), contentDescription = "")
        }}
    } )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BarraSuperiorGeneral(navController: NavController) {
    var vm : ExoPlayerViewModel = viewModel()
    val contexto = LocalContext.current
    val exoplayer = vm.exoPlayer.collectAsState().value

    if(exoplayer == null){
        vm.crearExoPlayer(contexto)
        vm.hacerSonarMusica(contexto)
    }
    TopAppBar(title = { Text(text = "All ingredients") }, actions = {Row() {
        Button(onClick = {Aleatorio = !Aleatorio
        }){
            if (Aleatorio) {
                Icon(painterResource(id = R.drawable.baseline_shuffle_on_24), contentDescription = "")
            } else {
                Icon(painterResource(id = R.drawable.baseline_shuffle_24), contentDescription = "")
            }
        }
        Button(onClick = {vm.PausarOSeguirMusica()}) {
            if(!vm.exoPlayer.value!!.isPlaying ){
                Icon(painterResource(id = R.drawable.baseline_music_note_24), contentDescription = "")
            }else{
                Icon(painterResource(id = R.drawable.baseline_music_off_24), contentDescription = "")
            }
        }
        Button(onClick = {
            navController.navigate(Rutas.Usuario.Ruta)
        }) {
            Icon(painterResource(id = R.drawable.baseline_list_alt_24), contentDescription = "")
        }}
    } )
}