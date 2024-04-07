package com.example.bdingredientes.clases

sealed class Rutas(val Ruta: String) {
    object Usuario : Rutas("PantallaIngredients2")
    object General : Rutas("PantallaIngredients")
    object Add : Rutas("PantallaAñadir")
    object Update : Rutas("PantallaModificar")
    object Login : Rutas("PantallaLogin")
    object Auth : Rutas("PantallaAuth")
    object Menu : Rutas("PantallaMenu")
    object Random : Rutas("PantallaRandom")
    object UtensiliosUsuario : Rutas("PantallaEquipment2")
    object UtensiliosGeneral : Rutas("PantallaEquipment")
    object EquipmentUpdate : Rutas("PantallaModificar2")
    object EquipmentAdd : Rutas("PantallaAñadir2")
}
