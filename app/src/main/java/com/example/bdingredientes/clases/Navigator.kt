package com.example.bdingredientes.clases

import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.bdingredientes.ui.theme.PantallaAuth
import com.example.bdingredientes.ui.theme.PantallaAñadir
import com.example.bdingredientes.ui.theme.PantallaAñadir2
import com.example.bdingredientes.ui.theme.PantallaAñadirAdmin
import com.example.bdingredientes.ui.theme.PantallaAñadirAdmin2
import com.example.bdingredientes.ui.theme.PantallaEquipment
import com.example.bdingredientes.ui.theme.PantallaEquipment2
import com.example.bdingredientes.ui.theme.PantallaEquipmentAdmin
import com.example.bdingredientes.ui.theme.PantallaIngredients
import com.example.bdingredientes.ui.theme.PantallaIngredients2
import com.example.bdingredientes.ui.theme.PantallaIngredientsAdmin
import com.example.bdingredientes.ui.theme.PantallaLogin
import com.example.bdingredientes.ui.theme.PantallaMenu
import com.example.bdingredientes.ui.theme.PantallaModificar
import com.example.bdingredientes.ui.theme.PantallaModificar2
import com.example.bdingredientes.ui.theme.PantallaModificarAdmin
import com.example.bdingredientes.ui.theme.PantallaModificarAdmin2
import com.example.bdingredientes.ui.theme.PantallaRandom

@Composable
fun Navigator(launcher: ActivityResultLauncher<Intent>) {
    val navController = rememberNavController()
    val entradaNavActual by navController.currentBackStackEntryAsState()
    val rutaActual = entradaNavActual?.destination?.route
    val db : VMBD = viewModel()
    val db2 : VMBD2 = viewModel()
    val db3 : VMBD3 = viewModel()
    val db4 : VMBD4 = viewModel()
    val sf : ViewModelScaffold = viewModel()
    val idioma = sf.english.collectAsState().value

    Scaffold(topBar = {
        when (rutaActual) {
            Rutas.Usuario.ruta -> {
                BarraSuperiorUsuario(navController = navController, sf)
            }
            Rutas.General.ruta -> {
                BarraSuperiorGeneral(navController = navController,db,sf)
            }
            Rutas.UtensiliosUsuario.ruta -> {
                BarraSuperiorUtensiliosUsuario(navController = navController, sf)
            }
            Rutas.UtensiliosGeneral.ruta -> {
                BarraSuperiorUtensiliosGeneral(navController = navController,db3,sf)
            }
            Rutas.UtensiliosAdmin.ruta -> {
                BarraSuperiorUtensiliosAdmin(db3, sf)
            }
            Rutas.Admin.ruta -> {
                BarraSuperiorAdmin(db, sf)
            }
            Rutas.Add.ruta -> {
                if (idioma.value) {
                    BarraSuperiorGenerica("Add a new ingredient", sf)
                } else BarraSuperiorGenerica("Añade un nuevo ingrediente", sf)
            }

            Rutas.Update.ruta -> {
                if (idioma.value) {
                    BarraSuperiorGenerica("Modify the selected ingredient", sf)
                } else BarraSuperiorGenerica("Modifica el ingrediente seleccionado", sf)
            }

            Rutas.Login.ruta -> {
                if (idioma.value) {
                    BarraSuperiorGenerica("Log in with your Firebase account", sf)
                } else BarraSuperiorGenerica("Inicia sesión con tu cuenta de Firebase", sf)
            }

            Rutas.Auth.ruta -> {
                if (idioma.value) {
                    BarraSuperiorGenerica("Create a new Firebase user", sf)
                } else BarraSuperiorGenerica("Crea un nuevo usuario de Firebase", sf)
            }

            Rutas.Menu.ruta -> {
                if (idioma.value) {
                    BarraSuperiorGenerica("Choose a mode", sf)
                } else BarraSuperiorGenerica("Elige un modo", sf)
            }

            Rutas.EquipmentAdd.ruta -> {
                if (idioma.value) {
                    BarraSuperiorGenerica("Add a new equipment", sf)
                } else BarraSuperiorGenerica("Añade un nuevo utensilio", sf)
            }

            Rutas.EquipmentUpdate.ruta -> {
                if (idioma.value) {
                    BarraSuperiorGenerica("Modify the selected equipment", sf)
                } else BarraSuperiorGenerica("Modifica el utensilio seleccionado", sf)
            }

            Rutas.AdminUpdate.ruta -> {
                if (idioma.value) {
                    BarraSuperiorGenerica("Modify the selected ingredient", sf)
                } else BarraSuperiorGenerica("Modifica el ingrediente seleccionado", sf)
            }

            Rutas.AdminAdd.ruta -> {
                if (idioma.value) {
                    BarraSuperiorGenerica("Add a new ingredient", sf)
                } else BarraSuperiorGenerica("Añade un nuevo ingrediente", sf)
            }

            Rutas.EquipmentAdminUpdate.ruta -> {
                if (idioma.value) {
                    BarraSuperiorGenerica("Modify the selected equipment", sf)
                } else BarraSuperiorGenerica("Modifica el utensilio seleccionado", sf)
            }

            Rutas.EquipmentAdminAdd.ruta -> {
                if (idioma.value) {
                    BarraSuperiorGenerica("Add a new equipment", sf)
                } else BarraSuperiorGenerica("Añade un nuevo utensilio", sf)
            }

            else -> {
                if (idioma.value) {
                    BarraSuperiorGenerica("Choose a game", sf)
                } else BarraSuperiorGenerica("Elige un juego", sf)
            }
        }
    }, bottomBar = {
        when (rutaActual) {
            Rutas.Usuario.ruta -> {
                BarraInferior(navController = navController, sf)
            }
            Rutas.Admin.ruta -> {
                BarraInferiorAdmin(navController = navController, sf)
            }
            Rutas.General.ruta -> {
                val texto: String = if (idioma.value) {
                    "By Aymara and Nayara Mendoza Rodríguez, 2024"
                } else "Por Aymara y Nayara Mendoza Rodríguez, 2024"
                BottomAppBar{
                    Text(texto)
                }
            }
            Rutas.Add.ruta -> {
                BarraInferiorAdd(navController = navController, sf)
            }
            Rutas.AdminAdd.ruta -> {
                BarraInferiorAdminAdd(navController = navController, sf)
            }
            Rutas.Update.ruta -> {
                BarraInferiorUpdate(navController = navController, sf)
            }
            Rutas.AdminUpdate.ruta -> {
                BarraInferiorAdminUpdate(navController = navController, sf)
            }
            Rutas.Menu.ruta -> {
                val texto: String = if (idioma.value) {
                    "By Aymara and Nayara Mendoza Rodríguez, 2024"
                } else "Por Aymara y Nayara Mendoza Rodríguez, 2024"
                BottomAppBar{
                    IconButton(onClick = { navController.navigate(Rutas.Login.ruta)}) {
                        Icon(Icons.AutoMirrored.Filled.Logout, "")
                    }
                    Text(texto)
                }
            }
            Rutas.UtensiliosUsuario.ruta -> {
                BarraInferiorEquipment(navController = navController, sf)
            }
            Rutas.UtensiliosGeneral.ruta -> {
                val texto: String = if (idioma.value) {
                    "By Aymara and Nayara Mendoza Rodríguez, 2024"
                } else "Por Aymara y Nayara Mendoza Rodríguez, 2024"
                BottomAppBar{
                    Text(texto)
                }
            }
            Rutas.EquipmentAdd.ruta -> {
                BarraInferiorEquipmentAdd(navController = navController, sf)
            }
            Rutas.EquipmentUpdate.ruta -> {
                BarraInferiorEquipmentUpdate(navController = navController, sf)
            }
            Rutas.UtensiliosAdmin.ruta -> {
                BarraInferiorEquipmentAdmin(navController = navController, sf)
            }
            Rutas.EquipmentAdminUpdate.ruta -> {
                BarraInferiorEquipmentAdminUpdate(navController = navController, sf)
            }
            Rutas.EquipmentAdminAdd.ruta -> {
                BarraInferiorEquipmentAdminAdd(navController = navController, sf)
            }
            else -> {
                val texto: String = if (idioma.value) {
                    "By Aymara and Nayara Mendoza Rodríguez, 2024"
                } else "Por Aymara y Nayara Mendoza Rodríguez, 2024"
                BottomAppBar{
                    Text(texto)
                }
            }
        }
    }, content = {
            paddingValues ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            color = MaterialTheme.colorScheme.background
        ) {
            NavHost(navController = navController, startDestination = Rutas.Login.ruta) {
                composable(Rutas.Usuario.ruta) {
                    PantallaIngredients2(db2, sf, navController = navController)
                }
                composable(Rutas.General.ruta) {
                    PantallaIngredients(db,sf, navController = navController)
                }
                composable(Rutas.Add.ruta) {
                    PantallaAñadir(sf)
                }
                composable(Rutas.Update.ruta) {
                    PantallaModificar(
                        nombre,
                        tipo,
                        sabor,
                        deCelebracion,
                        celebracion,
                        comida2,
                        numero,
                        juego3,
                        parte2, sf
                    )
                }
                composable(Rutas.Login.ruta) {
                    PantallaLogin(navController = navController, sf)
                }
                composable(Rutas.Auth.ruta) {
                    PantallaAuth(navController = navController, sf)
                }
                composable(Rutas.Menu.ruta) {
                    PantallaMenu(navController = navController, sf)
                }
                composable(Rutas.Random.ruta) {
                    PantallaRandom(navController = navController, sf)
                }
                composable(Rutas.UtensiliosUsuario.ruta) {
                    PantallaEquipment2(db4, sf, navController = navController)
                }
                composable(Rutas.UtensiliosGeneral.ruta) {
                    PantallaEquipment(db3,sf, navController = navController)
                }
                composable(Rutas.EquipmentUpdate.ruta) {
                    PantallaModificar2(nombre2, tipo2, comida, juego2, celebracion2, parte, numero2, sf)
                }
                composable(Rutas.EquipmentAdd.ruta) {
                    PantallaAñadir2(sf)
                }
                composable(Rutas.Admin.ruta) {
                    PantallaIngredientsAdmin(db,sf, navController = navController)
                }
                composable(Rutas.AdminUpdate.ruta) {
                    PantallaModificarAdmin(
                        nombre,
                        tipo,
                        sabor,
                        deCelebracion,
                        celebracion,
                        comida2,
                        numero,
                        juego3,
                        parte2, sf
                    )
                }
                composable(Rutas.AdminAdd.ruta) {
                    PantallaAñadirAdmin(sf)
                }
                composable(Rutas.UtensiliosAdmin.ruta) {
                    PantallaEquipmentAdmin(db3,sf, navController = navController)
                }
                composable(Rutas.EquipmentAdminUpdate.ruta) {
                    PantallaModificarAdmin2(
                        nombre2,
                        tipo2,
                        comida,
                        juego2,
                        celebracion2,
                        parte,
                        numero2, sf
                    )
                }
                composable(Rutas.EquipmentAdminAdd.ruta) {
                    PantallaAñadirAdmin2(sf)
                }
            }
        }
    })
}