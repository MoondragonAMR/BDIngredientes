package com.example.bdingredientes.clases

sealed class Rutas(val Ruta: String) {
    object Usuario : Rutas("PantallaIngredients2")
    object General : Rutas("PantallaIngredients")
    object Add : Rutas("PantallaAñadir")
    object Update : Rutas("PantallaModificar")
}
