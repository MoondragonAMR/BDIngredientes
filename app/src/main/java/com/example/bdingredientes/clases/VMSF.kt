package com.example.bdingredientes.clases

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.DeleteForever
import androidx.compose.material.icons.filled.EditOff
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.bdingredientes.R
import com.example.bdingredientes.ui.theme.ExoPlayerViewModel


@Composable
fun BarraInferior(navController: NavController) {
    var sf : ViewModelScaffold = viewModel()
    var delete = sf.borrar.collectAsState().value
    var update = sf.modificar.collectAsState().value
    var deleted = sf.puedeBorrar.collectAsState().value
    var updated = sf.puedeModificar.collectAsState().value

    var iconoBorrar by remember {  mutableStateOf(Icons.Default.Delete) }

    if (delete.value) {
        iconoBorrar = Icons.Default.Delete
    } else {
        iconoBorrar = Icons.Default.DeleteForever
    //(painterResource(id = R.drawable.baseline_delete_forever_24)) as ImageVector
    }
    var iconoModificar by remember {  mutableStateOf(Icons.Default.ModeEdit) }
    if (update.value) {
        iconoModificar = Icons.Default.ModeEdit
    } else {
        iconoModificar = Icons.Default.EditOff
    }
    BottomAppBar(Modifier.fillMaxWidth()) {
            Row() {
                IconButton(onClick = {navController.navigate(Rutas.Add.Ruta)}) {
                    Icon(Icons.Default.Add, contentDescription = "")
                }
                IconButton(onClick = {delete.value =
                    !delete.value;
                    updated.value =
                        !updated.value},
                    enabled = deleted.value) {
                    Icon(iconoBorrar, contentDescription = "")
                }
                IconButton(onClick = {update.value = true;
                    deleted.value = false},
                    enabled = updated.value) {
                    Icon(iconoModificar, contentDescription = "")
                }
            }
            Text("By Aymara and Nayara Mendoza Rodríguez, 2024")
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
            Text("By Aymara and Nayara Mendoza Rodríguez, 2024")
        }
    }
}

@Composable
fun BarraInferiorUpdate(navController: NavController) {
    var db : VMBD2 = viewModel()
    var sf : ViewModelScaffold = viewModel()
    var update = sf.modificar.collectAsState().value
    var deleted = sf.puedeBorrar.collectAsState().value

    BottomAppBar(Modifier.fillMaxWidth()) {
        Row() {
            IconButton(onClick = {db.modificarIngrediente(codigo, nombre, tipo, sabor, deCelebracion, celebracion)
                update.value = false
                deleted.value = true
                navController.popBackStack()}) {
                Icon(Icons.Default.ModeEdit, contentDescription = "")
            }
            Text("By Aymara and Nayara Mendoza Rodríguez, 2024")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BarraSuperiorUsuario(navController: NavController) {
    var vm : ExoPlayerViewModel = viewModel()
    val contexto = LocalContext.current
    val exoplayer = vm.exoPlayer.collectAsState().value
    var sf : ViewModelScaffold = viewModel()
    var aleatorio = sf.Aleatorio.collectAsState().value
    var db : VMBD2 = viewModel()

    if(exoplayer == null){
        vm.crearExoPlayer(contexto)
        vm.hacerSonarMusica(contexto)
    }

    var iconoAleatorio by remember {  mutableStateOf( R.drawable.baseline_shuffle_on_24) }
    if (aleatorio.value) {
        iconoAleatorio = R.drawable.baseline_shuffle_on_24
        db.mezclarIngredientes()

    } else {
        iconoAleatorio = R.drawable.baseline_shuffle_24
    }

    var iconoMusica by remember {  mutableStateOf(R.drawable.baseline_music_off_24) }
    if (!vm.exoPlayer.value!!.isPlaying) {
        iconoMusica = R.drawable.baseline_music_off_24
    } else {
        iconoMusica = R.drawable.baseline_music_note_24
    }

    TopAppBar(title = { Text(text = "My ingredients") }, actions = {Row() {
        IconButton(onClick = {
            aleatorio.value = !aleatorio.value
        }) {
            //Icon(iconoAleatorio, contentDescription = "")
            Icon(painterResource(iconoAleatorio), contentDescription = "")
        }
        IconButton(onClick = { vm.PausarOSeguirMusica() }) {
            Icon(painterResource(iconoMusica), contentDescription = "")
        }
        IconButton(onClick = {
            navController.navigate(Rutas.General.Ruta)
        }) {
            Icon(painterResource(id = R.drawable.baseline_list_alt_24), contentDescription = "")
        }
    }
    })
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BarraSuperiorGeneral(navController: NavController) {
    var vm : ExoPlayerViewModel = viewModel()
    val contexto = LocalContext.current
    val exoplayer = vm.exoPlayer.collectAsState().value
    var sf : ViewModelScaffold = viewModel()
    var aleatorio = sf.Aleatorio.collectAsState().value
    var db : VMBD = viewModel()

    if(exoplayer == null){
        vm.crearExoPlayer(contexto)
        vm.hacerSonarMusica(contexto)
    }

    var iconoAleatorio by remember {  mutableStateOf( R.drawable.baseline_shuffle_on_24) }
    if (aleatorio.value) {
        iconoAleatorio = R.drawable.baseline_shuffle_on_24
        db.mezclarIngredientes()
    } else {
        iconoAleatorio = R.drawable.baseline_shuffle_24
    }

    var iconoMusica by remember {  mutableStateOf(R.drawable.baseline_music_off_24) }
    if (!vm.exoPlayer.value!!.isPlaying) {
        iconoMusica = R.drawable.baseline_music_off_24
    } else {
        iconoMusica = R.drawable.baseline_music_note_24
    }

    TopAppBar(title = { Text(text = "All ingredients") }, actions = {Row() {
        IconButton(onClick = {
            aleatorio.value = !aleatorio.value
        }) {
            //Icon(iconoAleatorio, contentDescription = "")
            Icon(painterResource(iconoAleatorio), contentDescription = "")
        }
        IconButton(onClick = { vm.PausarOSeguirMusica() }) {
            Icon(painterResource(iconoMusica), contentDescription = "")
        }
        IconButton(onClick = {
            navController.navigate(Rutas.Usuario.Ruta)
        }) {
            Icon(painterResource(id = R.drawable.baseline_list_alt_24), contentDescription = "")
        }
    }
    })
}