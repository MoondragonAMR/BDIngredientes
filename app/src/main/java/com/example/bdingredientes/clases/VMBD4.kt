package com.example.bdingredientes.clases
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import com.example.bdingredientes.ui.theme.usuario
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class VMBD4 : ViewModel() {
    val conexion = FirebaseFirestore.getInstance()
    private lateinit var listener: ListenerRegistration
    var _equipment = MutableStateFlow(mutableStateListOf<Equipment>())
    var equipment = _equipment.asStateFlow()
    var _equipmentAleatorio = MutableStateFlow(mutableStateListOf<Equipment>())
    var equipmentAleatorio = _equipmentAleatorio.asStateFlow()

    var _listaMostrar = MutableStateFlow(mutableStateListOf<Equipment>())
    var listaMostrar = _listaMostrar.asStateFlow()
    fun crearListener() {
        listener = conexion.collection("Customers/$usuario/MyEquipment").addSnapshotListener { datos, error ->
            if (error == null) {
                datos?.documentChanges?.forEach { cambio ->
                    if (cambio.type == DocumentChange.Type.ADDED) {
                        var utensilio = cambio.document.toObject<Equipment>()
                        utensilio.id = cambio.document.id

                        _equipment.value.add(utensilio)
                    } else if (cambio.type == DocumentChange.Type.MODIFIED) {
                        var utensilio = cambio.document.toObject<Equipment>()
                        _equipment.value[cambio.newIndex] = utensilio
                    } else {
                        var utensilio = cambio.document.toObject<Equipment>()
                        _equipment.value.remove(utensilio)
                    }
                }
            }
        }
    }

    fun borrarListener() {
        listener.remove()
    }

    fun anyadirUtensilio(
        name: String, type: String, holiday: String, food : String, game : String, orderPart : String
    ) {
        val newEquipment = Equipment(name, type, holiday, food, game, orderPart)
        conexion.collection("Customers/$usuario/MyEquipment").add(newEquipment)
    }

    fun borrarUtensilio(id: String) {
        conexion.collection("Customers/$usuario/MyEquipment").document(id).delete()
    }

    fun modificarUtensilio(id: String, name: String, type: String, holiday: String, food : String, game : String, orderPart : String) {
        val newEquipment = Equipment(name, type, holiday, food, game, orderPart)
        conexion.collection("Customers/$usuario/MyEquipment").document(id).set(newEquipment)
    }

    fun filtrarUtensilio(numero : Long, parametro : String, valor : String) {
        val valores = mutableListOf<String>()
        when (valor) {
            "Alarm" -> {
                valores.add("Alarm")
            }
            "Bag Size" -> {
                valores.add("Bag Size")
            }
            "Cup Size" -> {
                valores.add("Cup Size")
            }
            "Cut" -> {
                valores.add("Cut")
            }
            "Liner" -> {
                valores.add("Liner")
            }
            "Position" -> {
                valores.add("Position")
            }
            "Standard" -> {
                valores.add("Standard")
            }
            "Baseball Season" -> {
                valores.add("Baseball Season")
            }
            "Big Top Carnival" -> {
                valores.add("Big Top Carnival")
            }
            "Cherry Blossom Festival" -> {
                valores.add("Cherry Blossom Festival")
            }
            "Christmas" -> {
                valores.add("Christmas")
            }
            "Cinco de Mayo" -> {
                valores.add("Cinco de Mayo")
            }
            "Comet Con" -> {
                valores.add("Comet Con")
            }
            "Easter" -> {
                valores.add("Easter")
            }
            "Film Fest" -> {
                valores.add("Film Fest")
            }
            "Halloween" -> {
                valores.add("Halloween")
            }
            "Maple Mornings" -> {
                valores.add("Maple Mornings")
            }
            "New Year" -> {
                valores.add("New Year")
            }
            "Onionfest" -> {
                valores.add("Onionfest")
            }
            "Pirate Bash" -> {
                valores.add("Pirate Bash")
            }
            "St Paddy" -> {
                valores.add("St Paddy")
            }
            "Starlight" -> {
                valores.add("Starlight")
            }
            "Summer Luau" -> {
                valores.add("Summer Luau")
            }
            "Thanksgiving" -> {
                valores.add("Thanksgiving")
            }
            "Valentines" -> {
                valores.add("Valentines")
            }
            "Bakeria" -> {
                valores.add("Bakeria")
                valores.add("Bakeria/Bakeria To Go")
                valores.add("Pizzeria/Pizzeria To Go/Pizzeria HD/Wingeria/Wingeria HD/Wingeria To Go/Bakeria/Bakeria To Go")
            }
            "Bakeria To Go" -> {
                valores.add("Bakeria To Go")
                valores.add("Bakeria/Bakeria To Go")
                valores.add("Pizzeria/Pizzeria To Go/Pizzeria HD/Wingeria/Wingeria HD/Wingeria To Go/Bakeria/Bakeria To Go")
            }
            "Cupcakeria" -> {
                valores.add("Cupcakeria")
                valores.add("Cupcakeria/Cupcakeria HD/Cupcakeria To Go")
            }
            "Cupcakeria HD" -> {
                valores.add("Cupcakeria HD")
                valores.add("Cupcakeria/Cupcakeria HD/Cupcakeria To Go")
            }
            "Cupcakeria To Go" -> {
                valores.add("Cupcakeria To Go")
                valores.add("Cupcakeria/Cupcakeria HD/Cupcakeria To Go")
            }
            "Freezeria" -> {
                valores.add("Freezeria")
                valores.add("Freezeria/Freezeria HD/Freezeria To Go/Freezeria Deluxe")
                valores.add("Freezeria/Freezeria HD/Freezeria To Go/Freezeria Deluxe/Hot Doggeria/Hot Doggeria HD/Hot Doggeria To Go/Mocharia To Go/Cluckeria To Go")
                valores.add("Freezeria/Freezeria HD/Freezeria To Go/Freezeria Deluxe/Pancakeria/Pancakeria HD/Pancakeria To Go/Hot Doggeria/Hot Doggeria HD/Hot Doggeria To Go/Mocharia To Go/Cluckeria To Go")
                valores.add("Pizzeria/Pizzeria To Go/Pizzeria HD/Burgeria/Burgeria HD/Burgeria To Go/Freezeria/Freezeria HD/Freezeria To Go/Freezeria Deluxe/Pastaria/Pastaria To Go/Cheeseria/Cheeseria To Go")
            }
            "Freezeria Deluxe" -> {
                valores.add("Freezeria Deluxe")
                valores.add("Freezeria/Freezeria HD/Freezeria To Go/Freezeria Deluxe")
                valores.add("Freezeria/Freezeria HD/Freezeria To Go/Freezeria Deluxe/Hot Doggeria/Hot Doggeria HD/Hot Doggeria To Go/Mocharia To Go/Cluckeria To Go")
                valores.add("Freezeria/Freezeria HD/Freezeria To Go/Freezeria Deluxe/Pancakeria/Pancakeria HD/Pancakeria To Go/Hot Doggeria/Hot Doggeria HD/Hot Doggeria To Go/Mocharia To Go/Cluckeria To Go")
                valores.add("Pizzeria/Pizzeria To Go/Pizzeria HD/Burgeria/Burgeria HD/Burgeria To Go/Freezeria/Freezeria HD/Freezeria To Go/Freezeria Deluxe/Pastaria/Pastaria To Go/Cheeseria/Cheeseria To Go")
            }
            "Freezeria HD" -> {
                valores.add("Freezeria HD")
                valores.add("Freezeria/Freezeria HD/Freezeria To Go/Freezeria Deluxe")
                valores.add("Freezeria/Freezeria HD/Freezeria To Go/Freezeria Deluxe/Hot Doggeria/Hot Doggeria HD/Hot Doggeria To Go/Mocharia To Go/Cluckeria To Go")
                valores.add("Freezeria/Freezeria HD/Freezeria To Go/Freezeria Deluxe/Pancakeria/Pancakeria HD/Pancakeria To Go/Hot Doggeria/Hot Doggeria HD/Hot Doggeria To Go/Mocharia To Go/Cluckeria To Go")
                valores.add("Pizzeria/Pizzeria To Go/Pizzeria HD/Burgeria/Burgeria HD/Burgeria To Go/Freezeria/Freezeria HD/Freezeria To Go/Freezeria Deluxe/Pastaria/Pastaria To Go/Cheeseria/Cheeseria To Go")
            }
            "Freezeria To Go" -> {
                valores.add("Freezeria To Go")
                valores.add("Freezeria/Freezeria HD/Freezeria To Go/Freezeria Deluxe")
                valores.add("Freezeria/Freezeria HD/Freezeria To Go/Freezeria Deluxe/Hot Doggeria/Hot Doggeria HD/Hot Doggeria To Go/Mocharia To Go/Cluckeria To Go")
                valores.add("Freezeria/Freezeria HD/Freezeria To Go/Freezeria Deluxe/Pancakeria/Pancakeria HD/Pancakeria To Go/Hot Doggeria/Hot Doggeria HD/Hot Doggeria To Go/Mocharia To Go/Cluckeria To Go")
                valores.add("Pizzeria/Pizzeria To Go/Pizzeria HD/Burgeria/Burgeria HD/Burgeria To Go/Freezeria/Freezeria HD/Freezeria To Go/Freezeria Deluxe/Pastaria/Pastaria To Go/Cheeseria/Cheeseria To Go")
            }
            "Hot Doggeria" -> {
                valores.add("Hot Doggeria")
                valores.add("Freezeria/Freezeria HD/Freezeria To Go/Freezeria Deluxe/Hot Doggeria/Hot Doggeria HD/Hot Doggeria To Go/Mocharia To Go/Cluckeria To Go")
                valores.add("Freezeria/Freezeria HD/Freezeria To Go/Freezeria Deluxe/Pancakeria/Pancakeria HD/Pancakeria To Go/Hot Doggeria/Hot Doggeria HD/Hot Doggeria To Go/Mocharia To Go/Cluckeria To Go")
                valores.add("Hot Doggeria/Hot Doggeria HD/Hot Doggeria To Go")
            }
            "Hot Doggeria HD" -> {
                valores.add("Hot Doggeria HD")
                valores.add("Freezeria/Freezeria HD/Freezeria To Go/Freezeria Deluxe/Hot Doggeria/Hot Doggeria HD/Hot Doggeria To Go/Mocharia To Go/Cluckeria To Go")
                valores.add("Freezeria/Freezeria HD/Freezeria To Go/Freezeria Deluxe/Pancakeria/Pancakeria HD/Pancakeria To Go/Hot Doggeria/Hot Doggeria HD/Hot Doggeria To Go/Mocharia To Go/Cluckeria To Go")
                valores.add("Hot Doggeria/Hot Doggeria HD/Hot Doggeria To Go")
            }
            "Hot Doggeria To Go" -> {
                valores.add("Hot Doggeria To Go")
                valores.add("Freezeria/Freezeria HD/Freezeria To Go/Freezeria Deluxe/Hot Doggeria/Hot Doggeria HD/Hot Doggeria To Go/Mocharia To Go/Cluckeria To Go")
                valores.add("Freezeria/Freezeria HD/Freezeria To Go/Freezeria Deluxe/Pancakeria/Pancakeria HD/Pancakeria To Go/Hot Doggeria/Hot Doggeria HD/Hot Doggeria To Go/Mocharia To Go/Cluckeria To Go")
                valores.add("Hot Doggeria/Hot Doggeria HD/Hot Doggeria To Go")
            }
            "Mocharia To Go" -> {
                valores.add("Mocharia To Go")
                valores.add("Freezeria/Freezeria HD/Freezeria To Go/Freezeria Deluxe/Hot Doggeria/Hot Doggeria HD/Hot Doggeria To Go/Mocharia To Go/Cluckeria To Go")
                valores.add("Freezeria/Freezeria HD/Freezeria To Go/Freezeria Deluxe/Pancakeria/Pancakeria HD/Pancakeria To Go/Hot Doggeria/Hot Doggeria HD/Hot Doggeria To Go/Mocharia To Go/Cluckeria To Go")
            }
            "Cluckeria To Go" -> {
                valores.add("Cluckeria To Go")
                valores.add("Freezeria/Freezeria HD/Freezeria To Go/Freezeria Deluxe/Hot Doggeria/Hot Doggeria HD/Hot Doggeria To Go/Mocharia To Go/Cluckeria To Go")
                valores.add("Freezeria/Freezeria HD/Freezeria To Go/Freezeria Deluxe/Pancakeria/Pancakeria HD/Pancakeria To Go/Hot Doggeria/Hot Doggeria HD/Hot Doggeria To Go/Mocharia To Go/Cluckeria To Go")
            }
            "Pancakeria" -> {
                valores.add("Pancakeria")
                valores.add("Freezeria/Freezeria HD/Freezeria To Go/Freezeria Deluxe/Pancakeria/Pancakeria HD/Pancakeria To Go/Hot Doggeria/Hot Doggeria HD/Hot Doggeria To Go/Mocharia To Go/Cluckeria To Go")
            }
            "Pancakeria HD" -> {
                valores.add("Pancakeria HD")
                valores.add("Freezeria/Freezeria HD/Freezeria To Go/Freezeria Deluxe/Pancakeria/Pancakeria HD/Pancakeria To Go/Hot Doggeria/Hot Doggeria HD/Hot Doggeria To Go/Mocharia To Go/Cluckeria To Go")
            }
            "Pancakeria To Go" -> {
                valores.add("Pancakeria To Go")
                valores.add("Freezeria/Freezeria HD/Freezeria To Go/Freezeria Deluxe/Pancakeria/Pancakeria HD/Pancakeria To Go/Hot Doggeria/Hot Doggeria HD/Hot Doggeria To Go/Mocharia To Go/Cluckeria To Go")
            }
            "Paleteria To Go" -> {
                valores.add("Paleteria To Go")
                valores.add("Pizzeria/Pizzeria To Go/Pizzeria HD/Paleteria To Go")
            }
            "Pastaria" -> {
                valores.add("Pastaria")
                valores.add("Pastaria/Pastaria To Go")
                valores.add("Pizzeria/Pizzeria To Go/Pizzeria HD/Burgeria/Burgeria HD/Burgeria To Go/Freezeria/Freezeria HD/Freezeria To Go/Freezeria Deluxe/Pastaria/Pastaria To Go/Cheeseria/Cheeseria To Go")
            }
            "Pastaria To Go" -> {
                valores.add("Pastaria To Go")
                valores.add("Pastaria/Pastaria To Go")
                valores.add("Pizzeria/Pizzeria To Go/Pizzeria HD/Burgeria/Burgeria HD/Burgeria To Go/Freezeria/Freezeria HD/Freezeria To Go/Freezeria Deluxe/Pastaria/Pastaria To Go/Cheeseria/Cheeseria To Go")
            }
            "Pizzeria" -> {
                valores.add("Pizzeria")
                valores.add("Pizzeria/Pizzeria To Go/Pizzeria HD")
                valores.add("Pizzeria/Pizzeria To Go/Pizzeria HD/Burgeria/Burgeria HD/Burgeria To Go/Cheeseria/Cheeseria To Go")
                valores.add("Pizzeria/Pizzeria To Go/Pizzeria HD/Burgeria/Burgeria HD/Burgeria To Go/Freezeria/Freezeria HD/Freezeria To Go/Freezeria Deluxe/Pastaria/Pastaria To Go/Cheeseria/Cheeseria To Go")
                valores.add("Pizzeria/Pizzeria To Go/Pizzeria HD/Paleteria To Go")
                valores.add("Pizzeria/Pizzeria To Go/Pizzeria HD/Wingeria/Wingeria HD/Wingeria To Go/Bakeria/Bakeria To Go")
            }
            "Pizzeria HD" -> {
                valores.add("Pizzeria HD")
                valores.add("Pizzeria/Pizzeria To Go/Pizzeria HD")
                valores.add("Pizzeria To Go/Pizzeria HD")
                valores.add("Pizzeria/Pizzeria To Go/Pizzeria HD/Burgeria/Burgeria HD/Burgeria To Go/Cheeseria/Cheeseria To Go")
                valores.add("Pizzeria/Pizzeria To Go/Pizzeria HD/Burgeria/Burgeria HD/Burgeria To Go/Freezeria/Freezeria HD/Freezeria To Go/Freezeria Deluxe/Pastaria/Pastaria To Go/Cheeseria/Cheeseria To Go")
                valores.add("Pizzeria/Pizzeria To Go/Pizzeria HD/Paleteria To Go")
                valores.add("Pizzeria/Pizzeria To Go/Pizzeria HD/Wingeria/Wingeria HD/Wingeria To Go/Bakeria/Bakeria To Go")
            }
            "Pizzeria To Go" -> {
                valores.add("Pizzeria To Go")
                valores.add("Pizzeria/Pizzeria To Go/Pizzeria HD")
                valores.add("Pizzeria To Go/Pizzeria HD")
                valores.add("Pizzeria/Pizzeria To Go/Pizzeria HD/Burgeria/Burgeria HD/Burgeria To Go/Cheeseria/Cheeseria To Go")
                valores.add("Pizzeria/Pizzeria To Go/Pizzeria HD/Burgeria/Burgeria HD/Burgeria To Go/Freezeria/Freezeria HD/Freezeria To Go/Freezeria Deluxe/Pastaria/Pastaria To Go/Cheeseria/Cheeseria To Go")
                valores.add("Pizzeria/Pizzeria To Go/Pizzeria HD/Paleteria To Go")
                valores.add("Pizzeria/Pizzeria To Go/Pizzeria HD/Wingeria/Wingeria HD/Wingeria To Go/Bakeria/Bakeria To Go")
            }
            "Burgeria" -> {
                valores.add("Burgeria")
                valores.add("Pizzeria/Pizzeria To Go/Pizzeria HD/Burgeria/Burgeria HD/Burgeria To Go/Cheeseria/Cheeseria To Go")
                valores.add("Pizzeria/Pizzeria To Go/Pizzeria HD/Burgeria/Burgeria HD/Burgeria To Go/Freezeria/Freezeria HD/Freezeria To Go/Freezeria Deluxe/Pastaria/Pastaria To Go/Cheeseria/Cheeseria To Go")
            }
            "Burgeria HD" -> {
                valores.add("Burgeria HD")
                valores.add("Pizzeria/Pizzeria To Go/Pizzeria HD/Burgeria/Burgeria HD/Burgeria To Go/Cheeseria/Cheeseria To Go")
                valores.add("Pizzeria/Pizzeria To Go/Pizzeria HD/Burgeria/Burgeria HD/Burgeria To Go/Freezeria/Freezeria HD/Freezeria To Go/Freezeria Deluxe/Pastaria/Pastaria To Go/Cheeseria/Cheeseria To Go")
            }
            "Burgeria To Go" -> {
                valores.add("Burgeria To Go")
                valores.add("Pizzeria/Pizzeria To Go/Pizzeria HD/Burgeria/Burgeria HD/Burgeria To Go/Cheeseria/Cheeseria To Go")
                valores.add("Pizzeria/Pizzeria To Go/Pizzeria HD/Burgeria/Burgeria HD/Burgeria To Go/Freezeria/Freezeria HD/Freezeria To Go/Freezeria Deluxe/Pastaria/Pastaria To Go/Cheeseria/Cheeseria To Go")
            }
            "Cheeseria" -> {
                valores.add("Cheeseria")
                valores.add("Pizzeria/Pizzeria To Go/Pizzeria HD/Burgeria/Burgeria HD/Burgeria To Go/Cheeseria/Cheeseria To Go")
                valores.add("Pizzeria/Pizzeria To Go/Pizzeria HD/Burgeria/Burgeria HD/Burgeria To Go/Freezeria/Freezeria HD/Freezeria To Go/Freezeria Deluxe/Pastaria/Pastaria To Go/Cheeseria/Cheeseria To Go")
            }
            "Cheeseria To Go" -> {
                valores.add("Cheeseria To Go")
                valores.add("Pizzeria/Pizzeria To Go/Pizzeria HD/Burgeria/Burgeria HD/Burgeria To Go/Cheeseria/Cheeseria To Go")
                valores.add("Pizzeria/Pizzeria To Go/Pizzeria HD/Burgeria/Burgeria HD/Burgeria To Go/Freezeria/Freezeria HD/Freezeria To Go/Freezeria Deluxe/Pastaria/Pastaria To Go/Cheeseria/Cheeseria To Go")
            }
            "Wingeria" -> {
                valores.add("Wingeria")
                valores.add("Pizzeria/Pizzeria To Go/Pizzeria HD/Wingeria/Wingeria HD/Wingeria To Go/Bakeria/Bakeria To Go")
                valores.add("Wingeria/Wingeria HD/Wingeria To Go")
            }
            "Wingeria HD" -> {
                valores.add("Wingeria HD")
                valores.add("Pizzeria/Pizzeria To Go/Pizzeria HD/Wingeria/Wingeria HD/Wingeria To Go/Bakeria/Bakeria To Go")
                valores.add("Wingeria/Wingeria HD/Wingeria To Go")
            }
            "Wingeria To Go" -> {
                valores.add("Wingeria To Go")
                valores.add("Pizzeria/Pizzeria To Go/Pizzeria HD/Wingeria/Wingeria HD/Wingeria To Go/Bakeria/Bakeria To Go")
                valores.add("Wingeria/Wingeria HD/Wingeria To Go")
            }
            "Chicken Wings" -> {
                valores.add("Chicken Wings")
                valores.add("Pizza/Chicken Wings/Pie")
            }
            "Cupcakes" -> {
                valores.add("Cupcakes")
            }
            "Hot Dog" -> {
                valores.add("Hot Dog")
                valores.add("Sundae/Breakfast/Hot Dog/Mocha/Chicken Sandwich")
                valores.add("Sundae/Hot Dog/Mocha/Chicken Sandwich")
            }
            "Paleta" -> {
                valores.add("Paleta")
                valores.add("Pizza/Paleta")
            }
            "Pasta" -> {
                valores.add("Pasta")
                valores.add("Pizza/Burger/Sundae/Pasta/Sandwich")
            }
            "Pie" -> {
                valores.add("Pie")
                valores.add("Pizza/Chicken Wings/Pie")
            }
            "Pizza" -> {
                valores.add("Pizza")
                valores.add("Pizza/Burger/Sandwich")
                valores.add("Pizza/Burger/Sundae/Pasta/Sandwich")
                valores.add("Pizza/Chicken Wings/Pie")
                valores.add("Pizza/Paleta")
            }
            "Burger" -> {
                valores.add("Burger")
                valores.add("Pizza/Burger/Sandwich")
                valores.add("Pizza/Burger/Sundae/Pasta/Sandwich")
            }
            "Sandwich" -> {
                valores.add("Sandwich")
                valores.add("Pizza/Burger/Sandwich")
                valores.add("Pizza/Burger/Sundae/Pasta/Sandwich")
            }
            "Sundae" -> {
                valores.add("Sundae")
                valores.add("Pizza/Burger/Sundae/Pasta/Sandwich")
                valores.add("Sundae/Breakfast/Hot Dog/Mocha/Chicken Sandwich")
                valores.add("Sundae/Hot Dog/Mocha/Chicken Sandwich")
            }
            "Breakfast" -> {
                valores.add("Breakfast")
                valores.add("Sundae/Breakfast/Hot Dog/Mocha/Chicken Sandwich")
            }
            "Mocha" -> {
                valores.add("Mocha")
                valores.add("Sundae/Breakfast/Hot Dog/Mocha/Chicken Sandwich")
                valores.add("Sundae/Hot Dog/Mocha/Chicken Sandwich")
            }
            "Chicken Sandwich" -> {
                valores.add("Chicken Sandwich")
                valores.add("Sundae/Breakfast/Hot Dog/Mocha/Chicken Sandwich")
                valores.add("Sundae/Hot Dog/Mocha/Chicken Sandwich")
            }
            "Chicken Wings Position" -> {
                valores.add("Chicken Wings Position")
                valores.add("Pizza Topping Position/Chicken Wings Position/Pie Topping Position")
            }
            "Paleta Topping Position" -> {
                valores.add("Paleta Topping Position")
                valores.add("Pizza Topping Position/Paleta Topping Position")
            }
            "Pasta Time" -> {
                valores.add("Pasta Time")
                valores.add("Pizza Time/Burger Time/Sundae Time/Pasta Time/Sandwich Time")
            }
            "Pie Topping Position" -> {
                valores.add("Pie Topping Position")
                valores.add("Pizza Topping Position/Chicken Wings Position/Pie Topping Position")
            }
            "Pizza Time" -> {
                valores.add("Pizza Time")
                valores.add("Pizza Time/Burger Time/Sandwich Time")
                valores.add("Pizza Time/Burger Time/Sundae Time/Pasta Time/Sandwich Time")
            }
            "Burger Time" -> {
                valores.add("Burger Time")
                valores.add("Pizza Time/Burger Time/Sandwich Time")
                valores.add("Pizza Time/Burger Time/Sundae Time/Pasta Time/Sandwich Time")
            }
            "Sandwich Time" -> {
                valores.add("Sandwich Time")
                valores.add("Pizza Time/Burger Time/Sandwich Time")
                valores.add("Pizza Time/Burger Time/Sundae Time/Pasta Time/Sandwich Time")
            }
            "Sundae Time" -> {
                valores.add("Sundae Time")
                valores.add("Pizza Time/Burger Time/Sundae Time/Pasta Time/Sandwich Time")
            }
            "Pizza Topping Position" -> {
                valores.add("Pizza Topping Position")
                valores.add("Pizza Topping Position/Chicken Wings Position/Pie Topping Position")
                valores.add("Pizza Topping Position/Paleta Topping Position")
            }
            "Sundae Size" -> {
                valores.add("Sundae Size")
                valores.add("Sundae Size/Drink Size/Soda Size/Mocha Size/Slush Size")
                valores.add("Sundae Size/Soda Size/Mocha Size/Slush Size")
            }
            "Drink Size" -> {
                valores.add("Drink Size")
                valores.add("Sundae Size/Drink Size/Soda Size/Mocha Size/Slush Size")
            }
            "Soda Size" -> {
                valores.add("Soda Size")
                valores.add("Sundae Size/Drink Size/Soda Size/Mocha Size/Slush Size")
                valores.add("Sundae Size/Soda Size/Mocha Size/Slush Size")
            }
            "Mocha Size" -> {
                valores.add("Mocha Size")
                valores.add("Sundae Size/Drink Size/Soda Size/Mocha Size/Slush Size")
                valores.add("Sundae Size/Soda Size/Mocha Size/Slush Size")
            }
            "Slush Size" -> {
                valores.add("Slush Size")
                valores.add("Sundae Size/Drink Size/Soda Size/Mocha Size/Slush Size")
                valores.add("Sundae Size/Soda Size/Mocha Size/Slush Size")
            }
        }
        conexion.collection("Customers/$usuario/MyEquipment").whereIn(parametro, valores).orderBy(parametro).limit(numero).get().addOnSuccessListener {
            _equipment.value.clear()
            it.documents.forEach {
                docu->_equipment.value.add(docu.toObject()!!)
            }
        }
    }

    fun filtrarUtensilio(numero : Long, parametro : String) {
        conexion.collection("Customers/$usuario/MyEquipment").orderBy(parametro).limit(numero).get().addOnSuccessListener {
            _equipment.value.clear()
            it.documents.forEach { docu ->
                _equipment.value.add(docu.toObject()!!)
            }
        }
    }

    fun mezclarUtensilios() {
        _equipmentAleatorio.value = _equipment.value.shuffled().toMutableStateList()
    }

}