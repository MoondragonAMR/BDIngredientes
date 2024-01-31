package com.example.bdingredientes.clases

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.bdingredientes.ui.theme.PantallaAñadir
import com.example.bdingredientes.ui.theme.PantallaIngredients
import com.example.bdingredientes.ui.theme.PantallaIngredients2
import com.example.bdingredientes.ui.theme.PantallaModificar

@Composable
fun Navigator() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Rutas.Usuario.Ruta) {

        composable(Rutas.Usuario.Ruta) {
            ventana = 1
            PantallaIngredients2(navController = navController)
        }

        composable(Rutas.General.Ruta) {
            ventana = 2
            PantallaIngredients()
        }

        composable(Rutas.Add.Ruta) {
            PantallaAñadir(navController = navController)
        }

        composable(Rutas.Update.Ruta) {
            PantallaModificar(navController = navController, codigo, nombre, tipo, sabor, deCelebracion, celebracion)
        }
    }
}