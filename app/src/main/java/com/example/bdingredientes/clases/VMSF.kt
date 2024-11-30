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
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.bdingredientes.R
import com.example.bdingredientes.ui.theme.ExoPlayerViewModel


@Composable
fun BarraInferiorAdmin(navController: NavController) {
    val sf : ViewModelScaffold = viewModel()
    val delete = sf.borrar3.collectAsState().value
    val update = sf.modificar3.collectAsState().value
    val deleted = sf.puedeBorrar3.collectAsState().value
    val updated = sf.puedeModificar3.collectAsState().value

    var iconoBorrar by remember {  mutableStateOf(Icons.Default.Delete) }

    iconoBorrar = if (delete.value) {
        Icons.Default.Delete
    } else {
        Icons.Default.DeleteForever
    }
    var iconoModificar by remember {  mutableStateOf(Icons.Default.ModeEdit) }
    iconoModificar = if (update.value) {
        Icons.Default.ModeEdit
    } else {
        Icons.Default.EditOff
    }
    BottomAppBar(Modifier.fillMaxWidth()) {
            Row {
                IconButton(onClick = {navController.navigate(Rutas.AdminAdd.ruta)}) {
                    Icon(Icons.Default.Add, contentDescription = "")
                }
                IconButton(onClick = {delete.value =
                    !delete.value
                    updated.value =
                        !updated.value},
                    enabled = deleted.value) {
                    Icon(iconoBorrar, contentDescription = "")
                }
                IconButton(onClick = {update.value = true
                    deleted.value = false},
                    enabled = updated.value) {
                    Icon(iconoModificar, contentDescription = "")
                }
            }
            Text("By Aymara and Nayara Mendoza Rodríguez, 2024")
    }
}

@Composable
fun BarraInferior(navController: NavController) {
    val sf : ViewModelScaffold = viewModel()
    val delete = sf.borrar.collectAsState().value
    val update = sf.modificar.collectAsState().value
    val deleted = sf.puedeBorrar.collectAsState().value
    val updated = sf.puedeModificar.collectAsState().value

    var iconoBorrar by remember {  mutableStateOf(Icons.Default.Delete) }

    iconoBorrar = if (delete.value) {
        Icons.Default.Delete
    } else {
        Icons.Default.DeleteForever
    }
    var iconoModificar by remember {  mutableStateOf(Icons.Default.ModeEdit) }
    iconoModificar = if (update.value) {
        Icons.Default.ModeEdit
    } else {
        Icons.Default.EditOff
    }
    BottomAppBar(Modifier.fillMaxWidth()) {
        Row {
            IconButton(onClick = {navController.navigate(Rutas.Add.ruta)}) {
                Icon(Icons.Default.Add, contentDescription = "")
            }
            IconButton(onClick = {delete.value =
                !delete.value
                updated.value =
                    !updated.value},
                enabled = deleted.value) {
                Icon(iconoBorrar, contentDescription = "")
            }
            IconButton(onClick = {update.value = true
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
    val db : VMBD2 = viewModel()
    BottomAppBar(Modifier.fillMaxWidth()) {
        Row {
            IconButton(onClick = {db.anyadirIngrediente(nombre, tipo, sabor, deCelebracion, celebracion, comida2, numero, juego3, parte2)
                navController.navigate(Rutas.Usuario.ruta)}) {
                Icon(Icons.Default.Add, contentDescription = "")
            }
            Text("By Aymara and Nayara Mendoza Rodríguez, 2024")
        }
    }
}

@Composable
fun BarraInferiorAdminAdd(navController: NavController) {
    val db : VMBD = viewModel()
    BottomAppBar(Modifier.fillMaxWidth()) {
        Row {
            IconButton(onClick = {db.anyadirIngrediente(nombre, tipo, sabor, deCelebracion, celebracion, comida2, numero, juego3, parte2)
                navController.navigate(Rutas.Admin.ruta)}) {
                Icon(Icons.Default.Add, contentDescription = "")
            }
            Text("By Aymara and Nayara Mendoza Rodríguez, 2024")
        }
    }
}

@Composable
fun BarraInferiorUpdate(navController: NavController) {
    val db : VMBD2 = viewModel()
    val sf : ViewModelScaffold = viewModel()
    val update = sf.modificar.collectAsState().value
    val deleted = sf.puedeBorrar.collectAsState().value

    BottomAppBar(Modifier.fillMaxWidth()) {
        Row {
            IconButton(onClick = {db.modificarIngrediente(codigo, nombre, tipo, sabor, deCelebracion, celebracion, comida2, numero, juego3, parte2)
                update.value = false
                deleted.value = true
                navController.navigate(Rutas.Usuario.ruta)}) {
                Icon(Icons.Default.ModeEdit, contentDescription = "")
            }
            Text("By Aymara and Nayara Mendoza Rodríguez, 2024")
        }
    }
}

@Composable
fun BarraInferiorAdminUpdate(navController: NavController) {
    val db : VMBD = viewModel()
    val sf : ViewModelScaffold = viewModel()
    val update = sf.modificar3.collectAsState().value
    val deleted = sf.puedeBorrar3.collectAsState().value

    BottomAppBar(Modifier.fillMaxWidth()) {
        Row {
            IconButton(onClick = {db.modificarIngrediente(codigo, nombre, tipo, sabor, deCelebracion, celebracion, comida2, numero, juego3, parte2)
                update.value = false
                deleted.value = true
                navController.navigate(Rutas.Admin.ruta)}) {
                Icon(Icons.Default.ModeEdit, contentDescription = "")
            }
            Text("By Aymara and Nayara Mendoza Rodríguez, 2024")
        }
    }
}

@Composable
fun BarraInferiorEquipment(navController: NavController) {
    val sf : ViewModelScaffold = viewModel()
    val delete = sf.borrar2.collectAsState().value
    val update = sf.modificar2.collectAsState().value
    val deleted = sf.puedeBorrar2.collectAsState().value
    val updated = sf.puedeModificar2.collectAsState().value

    var iconoBorrar by remember {  mutableStateOf(Icons.Default.Delete) }

    iconoBorrar = if (delete.value) {
        Icons.Default.Delete
    } else {
        Icons.Default.DeleteForever
        //(painterResource(id = R.drawable.baseline_delete_forever_24)) as ImageVector
    }
    var iconoModificar by remember {  mutableStateOf(Icons.Default.ModeEdit) }
    iconoModificar = if (update.value) {
        Icons.Default.ModeEdit
    } else {
        Icons.Default.EditOff
    }
    BottomAppBar(Modifier.fillMaxWidth()) {
        Row {
            IconButton(onClick = {navController.navigate(Rutas.EquipmentAdd.ruta)}) {
                Icon(Icons.Default.Add, contentDescription = "")
            }
            IconButton(onClick = {delete.value =
                !delete.value
                updated.value =
                    !updated.value},
                enabled = deleted.value) {
                Icon(iconoBorrar, contentDescription = "")
            }
            IconButton(onClick = {update.value = true
                deleted.value = false},
                enabled = updated.value) {
                Icon(iconoModificar, contentDescription = "")
            }
        }
        Text("By Aymara and Nayara Mendoza Rodríguez, 2024")
    }
}

@Composable
fun BarraInferiorEquipmentAdmin(navController: NavController) {
    val sf : ViewModelScaffold = viewModel()
    val delete = sf.borrar4.collectAsState().value
    val update = sf.modificar4.collectAsState().value
    val deleted = sf.puedeBorrar4.collectAsState().value
    val updated = sf.puedeModificar4.collectAsState().value

    var iconoBorrar by remember {  mutableStateOf(Icons.Default.Delete) }

    iconoBorrar = if (delete.value) {
        Icons.Default.Delete
    } else {
        Icons.Default.DeleteForever
        //(painterResource(id = R.drawable.baseline_delete_forever_24)) as ImageVector
    }
    var iconoModificar by remember {  mutableStateOf(Icons.Default.ModeEdit) }
    iconoModificar = if (update.value) {
        Icons.Default.ModeEdit
    } else {
        Icons.Default.EditOff
    }
    BottomAppBar(Modifier.fillMaxWidth()) {
        Row {
            IconButton(onClick = {navController.navigate(Rutas.EquipmentAdminAdd.ruta)}) {
                Icon(Icons.Default.Add, contentDescription = "")
            }
            IconButton(onClick = {delete.value =
                !delete.value
                updated.value =
                    !updated.value},
                enabled = deleted.value) {
                Icon(iconoBorrar, contentDescription = "")
            }
            IconButton(onClick = {update.value = true
                deleted.value = false},
                enabled = updated.value) {
                Icon(iconoModificar, contentDescription = "")
            }
        }
        Text("By Aymara and Nayara Mendoza Rodríguez, 2024")
    }
}

@Composable
fun BarraInferiorEquipmentAdd(navController: NavController) {
    val db : VMBD4 = viewModel()
    BottomAppBar(Modifier.fillMaxWidth()) {
        Row {
            IconButton(onClick = {db.anyadirUtensilio(nombre2, tipo2, celebracion2, comida, juego2, parte, numero2)
                navController.navigate(Rutas.UtensiliosUsuario.ruta)}) {
                Icon(Icons.Default.Add, contentDescription = "")
            }
            Text("By Aymara and Nayara Mendoza Rodríguez, 2024")
        }
    }
}

@Composable
fun BarraInferiorEquipmentAdminAdd(navController: NavController) {
    val db : VMBD3 = viewModel()
    BottomAppBar(Modifier.fillMaxWidth()) {
        Row {
            IconButton(onClick = {db.anyadirUtensilio(nombre2, tipo2, celebracion2, comida, juego2, parte, numero2)
                navController.navigate(Rutas.UtensiliosAdmin.ruta)}) {
                Icon(Icons.Default.Add, contentDescription = "")
            }
            Text("By Aymara and Nayara Mendoza Rodríguez, 2024")
        }
    }
}

@Composable
fun BarraInferiorEquipmentUpdate(navController: NavController) {
    val db : VMBD4 = viewModel()
    val sf : ViewModelScaffold = viewModel()
    val update = sf.modificar2.collectAsState().value
    val deleted = sf.puedeBorrar2.collectAsState().value

    BottomAppBar(Modifier.fillMaxWidth()) {
        Row {
            IconButton(onClick = {db.modificarUtensilio(codigo2, nombre2, tipo2, celebracion2, comida, juego2, parte, numero2)
                update.value = false
                deleted.value = true
                navController.navigate(Rutas.UtensiliosUsuario.ruta)}) {
                Icon(Icons.Default.ModeEdit, contentDescription = "")
            }
            Text("By Aymara and Nayara Mendoza Rodríguez, 2024")
        }
    }
}

@Composable
fun BarraInferiorEquipmentAdminUpdate(navController: NavController) {
    val db : VMBD3 = viewModel()
    val sf : ViewModelScaffold = viewModel()
    val update = sf.modificar4.collectAsState().value
    val deleted = sf.puedeBorrar4.collectAsState().value

    BottomAppBar(Modifier.fillMaxWidth()) {
        Row {
            IconButton(onClick = {db.modificarUtensilio(codigo2, nombre2, tipo2, celebracion2, comida, juego2, parte, numero2)
                update.value = false
                deleted.value = true
                navController.navigate(Rutas.UtensiliosAdmin.ruta)}) {
                Icon(Icons.Default.ModeEdit, contentDescription = "")
            }
            Text("By Aymara and Nayara Mendoza Rodríguez, 2024")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BarraSuperiorUsuario(navController: NavController) {
    val vm : ExoPlayerViewModel = viewModel()
    val contexto = LocalContext.current
    val exoplayer = vm.exoPlayer.collectAsState().value
    val sf : ViewModelScaffold = viewModel()
    val aleatorio = sf.aleatorio1.collectAsState().value
    val db : VMBD2 = viewModel()

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
    iconoMusica = if (!vm.exoPlayer.value!!.isPlaying) {
        R.drawable.baseline_music_off_24
    } else {
        R.drawable.baseline_music_note_24
    }

    TopAppBar(title = { Text(text = "My ingredients") }, actions = {Row {
        IconButton(onClick = {
            aleatorio.value = !aleatorio.value
        }) {
            //Icon(iconoAleatorio, contentDescription = "")
            Icon(painterResource(iconoAleatorio), contentDescription = "")
        }
        IconButton(onClick = { vm.pausarOSeguirMusica() }) {
            Icon(painterResource(iconoMusica), contentDescription = "")
        }
        IconButton(onClick = {
            navController.navigate(Rutas.General.ruta)
        }) {
            Icon(painterResource(id = R.drawable.baseline_list_alt_24), contentDescription = "")
        }
    }
    })
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BarraSuperiorUtensiliosAdmin(db: VMBD3, sf: ViewModelScaffold) {
    val vm : ExoPlayerViewModel = viewModel()
    val contexto = LocalContext.current
    val exoplayer = vm.exoPlayer.collectAsState().value
    val aleatorio = sf.aleatorio4.collectAsState().value

    if(exoplayer == null){
        vm.crearExoPlayer(contexto)
        vm.hacerSonarMusica(contexto)
    }

    var iconoAleatorio by remember {  mutableStateOf( R.drawable.baseline_shuffle_on_24) }
    iconoAleatorio = if (aleatorio.value) {
        R.drawable.baseline_shuffle_on_24
        //db.mezclarIngredientes()
    } else {
        R.drawable.baseline_shuffle_24
    }

    var iconoMusica by remember {  mutableStateOf(R.drawable.baseline_music_off_24) }
    iconoMusica = if (!vm.exoPlayer.value!!.isPlaying) {
        R.drawable.baseline_music_off_24
    } else {
        R.drawable.baseline_music_note_24
    }

    TopAppBar(title = { Text(text = "All equipment") }, actions = {Row {
        IconButton(onClick = {
            aleatorio.value = !aleatorio.value
            if(aleatorio.value) db.mezclarUtensilios()
        }) {
            //Icon(iconoAleatorio, contentDescription = "")
            Icon(painterResource(iconoAleatorio), contentDescription = "")
        }
        IconButton(onClick = { vm.pausarOSeguirMusica() }) {
            Icon(painterResource(iconoMusica), contentDescription = "")
        }
    }
    })
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BarraSuperiorUtensiliosGeneral(navController: NavController,db : VMBD3, sf: ViewModelScaffold ) {
    val vm : ExoPlayerViewModel = viewModel()
    val contexto = LocalContext.current
    val exoplayer = vm.exoPlayer.collectAsState().value
    val aleatorio = sf.aleatorio2.collectAsState().value

    if(exoplayer == null){
        vm.crearExoPlayer(contexto)
        vm.hacerSonarMusica(contexto)
    }

    var iconoAleatorio by remember {  mutableStateOf( R.drawable.baseline_shuffle_on_24) }
    iconoAleatorio = if (aleatorio.value) {
        R.drawable.baseline_shuffle_on_24
        //db.mezclarIngredientes()
    } else {
        R.drawable.baseline_shuffle_24
    }

    var iconoMusica by remember {  mutableStateOf(R.drawable.baseline_music_off_24) }
    iconoMusica = if (!vm.exoPlayer.value!!.isPlaying) {
        R.drawable.baseline_music_off_24
    } else {
        R.drawable.baseline_music_note_24
    }

    TopAppBar(title = { Text(text = "All equipment") }, actions = {Row {
        IconButton(onClick = {
            aleatorio.value = !aleatorio.value
            if(aleatorio.value) db.mezclarUtensilios()
        }) {
            //Icon(iconoAleatorio, contentDescription = "")
            Icon(painterResource(iconoAleatorio), contentDescription = "")
        }
        IconButton(onClick = { vm.pausarOSeguirMusica() }) {
            Icon(painterResource(iconoMusica), contentDescription = "")
        }
        IconButton(onClick = {
            navController.navigate(Rutas.UtensiliosUsuario.ruta)
        }) {
            Icon(painterResource(id = R.drawable.baseline_list_alt_24), contentDescription = "")
        }
    }
    })
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BarraSuperiorUtensiliosUsuario(navController: NavController) {
    val vm : ExoPlayerViewModel = viewModel()
    val contexto = LocalContext.current
    val exoplayer = vm.exoPlayer.collectAsState().value
    val sf : ViewModelScaffold = viewModel()
    val aleatorio = sf.aleatorio2.collectAsState().value
    val db : VMBD4 = viewModel()

    if(exoplayer == null){
        vm.crearExoPlayer(contexto)
        vm.hacerSonarMusica(contexto)
    }

    var iconoAleatorio by remember {  mutableStateOf( R.drawable.baseline_shuffle_on_24) }
    if (aleatorio.value) {
        iconoAleatorio = R.drawable.baseline_shuffle_on_24
        db.mezclarUtensilios()

    } else {
        iconoAleatorio = R.drawable.baseline_shuffle_24
    }

    var iconoMusica by remember {  mutableStateOf(R.drawable.baseline_music_off_24) }
    iconoMusica = if (!vm.exoPlayer.value!!.isPlaying) {
        R.drawable.baseline_music_off_24
    } else {
        R.drawable.baseline_music_note_24
    }

    TopAppBar(title = { Text(text = "My equipment") }, actions = {Row {
        IconButton(onClick = {
            aleatorio.value = !aleatorio.value
        }) {
            //Icon(iconoAleatorio, contentDescription = "")
            Icon(painterResource(iconoAleatorio), contentDescription = "")
        }
        IconButton(onClick = { vm.pausarOSeguirMusica() }) {
            Icon(painterResource(iconoMusica), contentDescription = "")
        }
        IconButton(onClick = {
            navController.navigate(Rutas.UtensiliosGeneral.ruta)
        }) {
            Icon(painterResource(id = R.drawable.baseline_list_alt_24), contentDescription = "")
        }
    }
    })
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BarraSuperiorAdmin(db: VMBD, sf: ViewModelScaffold) {
    val vm : ExoPlayerViewModel = viewModel()
    val contexto = LocalContext.current
    val exoplayer = vm.exoPlayer.collectAsState().value
    val aleatorio = sf.aleatorio3.collectAsState().value

    if(exoplayer == null){
        vm.crearExoPlayer(contexto)
        vm.hacerSonarMusica(contexto)
    }

    var iconoAleatorio by remember {  mutableStateOf( R.drawable.baseline_shuffle_on_24) }
    iconoAleatorio = if (aleatorio.value) {
        R.drawable.baseline_shuffle_on_24
        //db.mezclarIngredientes()
    } else {
        R.drawable.baseline_shuffle_24
    }

    var iconoMusica by remember {  mutableStateOf(R.drawable.baseline_music_off_24) }
    iconoMusica = if (!vm.exoPlayer.value!!.isPlaying) {
        R.drawable.baseline_music_off_24
    } else {
        R.drawable.baseline_music_note_24
    }

    TopAppBar(title = { Text(text = "All ingredients") }, actions = {Row {
        IconButton(onClick = {
            aleatorio.value = !aleatorio.value
            if(aleatorio.value) db.mezclarIngredientes()
        }) {
            //Icon(iconoAleatorio, contentDescription = "")
            Icon(painterResource(iconoAleatorio), contentDescription = "")
        }
        IconButton(onClick = { vm.pausarOSeguirMusica() }) {
            Icon(painterResource(iconoMusica), contentDescription = "")
        }
    }
    })
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BarraSuperiorGeneral(navController: NavController,db : VMBD, sf: ViewModelScaffold ) {
    val vm : ExoPlayerViewModel = viewModel()
    val contexto = LocalContext.current
    val exoplayer = vm.exoPlayer.collectAsState().value
    val aleatorio = sf.aleatorio1.collectAsState().value

    if(exoplayer == null){
        vm.crearExoPlayer(contexto)
        vm.hacerSonarMusica(contexto)
    }

    var iconoAleatorio by remember {  mutableStateOf( R.drawable.baseline_shuffle_on_24) }
    iconoAleatorio = if (aleatorio.value) {
        R.drawable.baseline_shuffle_on_24
        //db.mezclarIngredientes()
    } else {
        R.drawable.baseline_shuffle_24
    }

    var iconoMusica by remember {  mutableStateOf(R.drawable.baseline_music_off_24) }
    iconoMusica = if (!vm.exoPlayer.value!!.isPlaying) {
        R.drawable.baseline_music_off_24
    } else {
        R.drawable.baseline_music_note_24
    }

    TopAppBar(title = { Text(text = "All ingredients") }, actions = {Row {
        IconButton(onClick = {
            aleatorio.value = !aleatorio.value
            if(aleatorio.value) db.mezclarIngredientes()
        }) {
            //Icon(iconoAleatorio, contentDescription = "")
            Icon(painterResource(iconoAleatorio), contentDescription = "")
        }
        IconButton(onClick = { vm.pausarOSeguirMusica() }) {
            Icon(painterResource(iconoMusica), contentDescription = "")
        }
        IconButton(onClick = {
            navController.navigate(Rutas.Usuario.ruta)
        }) {
            Icon(painterResource(id = R.drawable.baseline_list_alt_24), contentDescription = "")
        }
    }
    })
}