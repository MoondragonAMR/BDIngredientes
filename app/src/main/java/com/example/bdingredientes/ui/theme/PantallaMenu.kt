package com.example.bdingredientes.ui.theme

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.navigation.NavController
import com.example.bdingredientes.clases.Rutas

@Composable
fun PantallaMenu(navController: NavController) {
    Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        Button(onClick = { navController.navigate(Rutas.Usuario.Ruta) }) {
            Text("Ingredients")
        }
        Button(onClick = { navController.navigate(Rutas.UtensiliosUsuario.Ruta)}) {
            Text("Equipment")
        }
        Button(onClick = { navController.navigate(Rutas.Random.Ruta)}) {
            Text("Orders")
        }
        Button(onClick = {}) {
            Text("Holiday Ingredients")
        }
    }
}