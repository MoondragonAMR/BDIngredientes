package com.example.bdingredientes.clases

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
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
import com.example.bdingredientes.ui.theme.PantallaAñadir
import com.example.bdingredientes.ui.theme.PantallaIngredients
import com.example.bdingredientes.ui.theme.PantallaIngredients2
import com.example.bdingredientes.ui.theme.PantallaModificar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Navigator() {
    val navController = rememberNavController()
    val entradaNavActual by navController.currentBackStackEntryAsState()
    val rutaActual = entradaNavActual?.destination?.route
    val viewModelScaffold : ViewModelScaffold = viewModel()

    Scaffold(topBar = { if (rutaActual == Rutas.Usuario.Ruta) {
        BarraSuperiorUsuario(navController = navController, viewModelScaffold)
    } else if (rutaActual == Rutas.General.Ruta) {
        BarraSuperiorGeneral(navController = navController, viewModelScaffold)
    } else {
        TopAppBar(title = { Text(text = if (rutaActual == Rutas.Add.Ruta) {
            "Add a new ingredient"
        } else {
            "Modify the selected ingredient"
        })
        })
    }}, bottomBar = {
        when (rutaActual) {
            Rutas.Usuario.Ruta -> {
                BarraInferior(navController = navController, viewModelScaffold)
            }
            Rutas.General.Ruta -> {
                BottomAppBar{}
            }
            Rutas.Add.Ruta -> {
                BarraInferiorAdd(navController = navController)
            }
            else -> {
                BarraInferiorUpdate(navController = navController, viewModelScaffold)
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
            NavHost(navController = navController, startDestination = Rutas.Usuario.Ruta) {
                composable(Rutas.Usuario.Ruta) {
                    PantallaIngredients2(navController = navController, viewModelScaffold)
                }
                composable(Rutas.General.Ruta) {
                    PantallaIngredients(viewModelScaffold)
                }
                composable(Rutas.Add.Ruta) {
                    PantallaAñadir()
                }
                composable(Rutas.Update.Ruta) {
                    PantallaModificar(codigo, nombre, tipo, sabor, deCelebracion, celebracion)
                }
            }
        }
    })
}