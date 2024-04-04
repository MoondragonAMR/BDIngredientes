package com.example.bdingredientes.clases

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.filled.Logout
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
import com.example.bdingredientes.ui.theme.PantallaIngredients
import com.example.bdingredientes.ui.theme.PantallaIngredients2
import com.example.bdingredientes.ui.theme.PantallaLogin
import com.example.bdingredientes.ui.theme.PantallaMenu
import com.example.bdingredientes.ui.theme.PantallaModificar
import com.example.bdingredientes.ui.theme.PantallaRandom
import com.example.bdingredientes.ui.theme.auth

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Navigator() {
    val navController = rememberNavController()
    val entradaNavActual by navController.currentBackStackEntryAsState()
    val rutaActual = entradaNavActual?.destination?.route
    var db : VMBD = viewModel()
    var db2 : VMBD2 = viewModel()
    var sf : ViewModelScaffold = viewModel()


    Scaffold(topBar = { if (rutaActual == Rutas.Usuario.Ruta) {
        BarraSuperiorUsuario(navController = navController)
    } else if (rutaActual == Rutas.General.Ruta) {
        BarraSuperiorGeneral(navController = navController,db,sf)
    } else {
        TopAppBar(title = { Text(text = when (rutaActual) {
            Rutas.Add.Ruta -> {
                "Add a new ingredient"
            }
            Rutas.Update.Ruta -> {
                "Modify the selected ingredient"
            }
            Rutas.Login.Ruta -> {
                "Log in with your Firebase account"
            }
            Rutas.Auth.Ruta -> {
                "Create a new Firebase user"
            }
            Rutas.Menu.Ruta -> {
                "Choose a mode"
            }
            else -> {
                "Choose a game"
            }
        }
        )
        })
    }}, bottomBar = {
        when (rutaActual) {
            Rutas.Usuario.Ruta -> {
                BarraInferior(navController = navController)
            }
            Rutas.General.Ruta -> {
                BottomAppBar{
                    Text("By Aymara and Nayara Mendoza Rodríguez, 2024")
                }
            }
            Rutas.Add.Ruta -> {
                BarraInferiorAdd(navController = navController)
            }
            Rutas.Update.Ruta -> {
                BarraInferiorUpdate(navController = navController)
            }
            Rutas.Menu.Ruta -> {
                BottomAppBar{
                    IconButton(onClick = { navController.navigate(Rutas.Login.Ruta)}) {
                        Icon(Icons.AutoMirrored.Filled.Logout, "")
                    }
                    Text("By Aymara and Nayara Mendoza Rodríguez, 2024")
                }
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
            NavHost(navController = navController, startDestination = Rutas.Login.Ruta) {
                composable(Rutas.Usuario.Ruta) {
                    PantallaIngredients2(db2, sf, navController = navController)
                }
                composable(Rutas.General.Ruta) {
                    PantallaIngredients(db,sf, navController = navController)
                }
                composable(Rutas.Add.Ruta) {
                    PantallaAñadir()
                }
                composable(Rutas.Update.Ruta) {
                    PantallaModificar(codigo, nombre, tipo, sabor, deCelebracion, celebracion)
                }
                composable(Rutas.Login.Ruta) {
                    PantallaLogin(navController = navController)
                }
                composable(Rutas.Auth.Ruta) {
                    PantallaAuth(navController = navController)
                }
                composable(Rutas.Menu.Ruta) {
                    PantallaMenu(navController = navController)
                }
                composable(Rutas.Random.Ruta) {
                    PantallaRandom(navController = navController)
                }
            }
        }
    })
}