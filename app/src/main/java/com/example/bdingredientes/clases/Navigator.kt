package com.example.bdingredientes.clases

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Navigator() {
    val navController = rememberNavController()
    val entradaNavActual by navController.currentBackStackEntryAsState()
    val rutaActual = entradaNavActual?.destination?.route
    val db : VMBD = viewModel()
    val db2 : VMBD2 = viewModel()
    val db3 : VMBD3 = viewModel()
    val db4 : VMBD4 = viewModel()
    val sf : ViewModelScaffold = viewModel()


    Scaffold(topBar = {
        when (rutaActual) {
            Rutas.Usuario.ruta -> {
                BarraSuperiorUsuario(navController = navController)
            }
            Rutas.General.ruta -> {
                BarraSuperiorGeneral(navController = navController,db,sf)
            }
            Rutas.UtensiliosUsuario.ruta -> {
                BarraSuperiorUtensiliosUsuario(navController = navController)
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
            else -> {
                TopAppBar(title = { Text(text = when (rutaActual) {
                    Rutas.Add.ruta -> {
                        "Add a new ingredient"
                    }

                    Rutas.Update.ruta -> {
                        "Modify the selected ingredient"
                    }

                    Rutas.Login.ruta -> {
                        "Log in with your Firebase account"
                    }

                    Rutas.Auth.ruta -> {
                        "Create a new Firebase user"
                    }

                    Rutas.Menu.ruta -> {
                        "Choose a mode"
                    }

                    Rutas.EquipmentAdd.ruta -> {
                        "Add a new equipment"
                    }

                    Rutas.EquipmentUpdate.ruta -> {
                        "Modify the selected equipment"
                    }

                    Rutas.AdminUpdate.ruta -> {
                        "Modify the selected ingredient"
                    }

                    Rutas.AdminAdd.ruta -> {
                        "Add a new ingredient"
                    }

                    Rutas.EquipmentAdminUpdate.ruta -> {
                        "Modify the selected equipment"
                    }

                    Rutas.EquipmentAdminAdd.ruta -> {
                        "Add a new equipment"
                    }

                    else -> {
                        "Choose a game"
                    }
                }
                )
                })
            }
        }
    }, bottomBar = {
        when (rutaActual) {
            Rutas.Usuario.ruta -> {
                BarraInferior(navController = navController)
            }
            Rutas.Admin.ruta -> {
                BarraInferiorAdmin(navController = navController)
            }
            Rutas.General.ruta -> {
                BottomAppBar{
                    Text("By Aymara and Nayara Mendoza Rodríguez, 2024")
                }
            }
            Rutas.Add.ruta -> {
                BarraInferiorAdd(navController = navController)
            }
            Rutas.AdminAdd.ruta -> {
                BarraInferiorAdminAdd(navController = navController)
            }
            Rutas.Update.ruta -> {
                BarraInferiorUpdate(navController = navController)
            }
            Rutas.AdminUpdate.ruta -> {
                BarraInferiorAdminUpdate(navController = navController)
            }
            Rutas.Menu.ruta -> {
                BottomAppBar{
                    IconButton(onClick = { navController.navigate(Rutas.Login.ruta)}) {
                        Icon(Icons.AutoMirrored.Filled.Logout, "")
                    }
                    Text("By Aymara and Nayara Mendoza Rodríguez, 2024")
                }
            }
            Rutas.UtensiliosUsuario.ruta -> {
                BarraInferiorEquipment(navController = navController)
            }
            Rutas.UtensiliosGeneral.ruta -> {
                BottomAppBar{
                    Text("By Aymara and Nayara Mendoza Rodríguez, 2024")
                }
            }
            Rutas.EquipmentAdd.ruta -> {
                BarraInferiorEquipmentAdd(navController = navController)
            }
            Rutas.EquipmentUpdate.ruta -> {
                BarraInferiorEquipmentUpdate(navController = navController)
            }
            Rutas.UtensiliosAdmin.ruta -> {
                BarraInferiorEquipmentAdmin(navController = navController)
            }
            Rutas.EquipmentAdminUpdate.ruta -> {
                BarraInferiorEquipmentAdminUpdate(navController = navController)
            }
            Rutas.EquipmentAdminAdd.ruta -> {
                BarraInferiorEquipmentAdminAdd(navController = navController)
            }
            else -> {
                BottomAppBar{
                    Text("By Aymara and Nayara Mendoza Rodríguez, 2024")
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
                    PantallaAñadir()
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
                        parte2
                    )
                }
                composable(Rutas.Login.ruta) {
                    PantallaLogin(navController = navController)
                }
                composable(Rutas.Auth.ruta) {
                    PantallaAuth(navController = navController)
                }
                composable(Rutas.Menu.ruta) {
                    PantallaMenu(navController = navController)
                }
                composable(Rutas.Random.ruta) {
                    PantallaRandom(navController = navController)
                }
                composable(Rutas.UtensiliosUsuario.ruta) {
                    PantallaEquipment2(db4, sf, navController = navController)
                }
                composable(Rutas.UtensiliosGeneral.ruta) {
                    PantallaEquipment(db3,sf, navController = navController)
                }
                composable(Rutas.EquipmentUpdate.ruta) {
                    PantallaModificar2(nombre2, tipo2, comida, juego2, celebracion2, parte, numero2)
                }
                composable(Rutas.EquipmentAdd.ruta) {
                    PantallaAñadir2()
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
                        parte2
                    )
                }
                composable(Rutas.AdminAdd.ruta) {
                    PantallaAñadirAdmin()
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
                        numero2
                    )
                }
                composable(Rutas.EquipmentAdminAdd.ruta) {
                    PantallaAñadirAdmin2()
                }
            }
        }
    })
}