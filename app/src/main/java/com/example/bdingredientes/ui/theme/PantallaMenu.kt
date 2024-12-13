package com.example.bdingredientes.ui.theme

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.bdingredientes.clases.Rutas
import com.example.bdingredientes.clases.ViewModelScaffold

@Composable
fun PantallaMenu(navController: NavController, sf : ViewModelScaffold = viewModel()) {

    val idioma = sf.english.collectAsState().value

    Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        /*val textoIngredients = if (idioma.value) {
            "Ingredients"
        } else {
            "Ingredientes"
        }
        Button(onClick = { navController.navigate(Rutas.Usuario.ruta) }) {
            Text(textoIngredients)
        }
        val textoIngredientsAdmin = if (idioma.value) {
            "Ingredients Admin"
        } else {
            "Ingredientes Admin"
        }
        Button(onClick = { navController.navigate(Rutas.Admin.ruta) }, enabled = admin) {
            Text(textoIngredientsAdmin)
        }*/
        val textoEquipment = if (idioma.value) {
            "Equipment"
        } else {
            "Utensilios"
        }
        Button(onClick = { navController.navigate(Rutas.UtensiliosUsuario.ruta) }) {
            Text(textoEquipment)
        }
        val textoEquipmentAdmin = if (idioma.value) {
            "Equipment Admin"
        } else {
            "Utensilios Admin"
        }
        Button(onClick = { navController.navigate(Rutas.UtensiliosAdmin.ruta) }, enabled = admin) {
            Text(textoEquipmentAdmin)
        }
        /*val textoOrders = if (idioma.value) {
            "Orders"
        } else {
            "Pedidos"
        }
        Button(onClick = { navController.navigate(Rutas.Random.ruta) }) {
            Text(textoOrders)
        }
        val textoHI = if (idioma.value) {
            "Holiday Ingredients"
        } else {
            "Ingredientes de celebración"
        }
        Button(onClick = {}) {
            Text(textoHI)
        }*/
    }
}