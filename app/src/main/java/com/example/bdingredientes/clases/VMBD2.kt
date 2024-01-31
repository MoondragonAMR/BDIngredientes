package com.example.bdingredientes.clases
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class VMBD2 : ViewModel() {
    val conexion = FirebaseFirestore.getInstance()
    private lateinit var listener: ListenerRegistration
    var _ingredients = MutableStateFlow(mutableStateListOf<Ingredient>())
    var ingredients = _ingredients.asStateFlow()
    fun crearListener() {
        listener = conexion.collection("Customers/RDTQ7Q7OmNbcvu6n1hiC/MyIngredients").addSnapshotListener { datos, error ->
            if (error == null) {
                datos?.documentChanges?.forEach { cambio ->
                    if (cambio.type == DocumentChange.Type.ADDED) {
                        var ingrediente = cambio.document.toObject<Ingredient>()
                        ingrediente.id = cambio.document.id

                        _ingredients.value.add(ingrediente)
                    } else if (cambio.type == DocumentChange.Type.MODIFIED) {
                        var ingrediente = cambio.document.toObject<Ingredient>()
                        _ingredients.value[cambio.newIndex] = ingrediente
                    } else {
                        var ingrediente = cambio.document.toObject<Ingredient>()
                        _ingredients.value.remove(ingrediente)
                    }
                }
            }
        }
    }

    fun borrarListener() {
        listener.remove()
    }

    fun anyadirIngrediente(
        name: String, flavor: String, type: String, holidayExclusive: Boolean, holiday: String
    ) {
        val newIngredient = Ingredient(name, type, flavor, holidayExclusive, holiday)
        conexion.collection("Customers/RDTQ7Q7OmNbcvu6n1hiC/MyIngredients").add(newIngredient)
    }

    fun borrarIngrediente(id: String) {
        conexion.collection("Customers/RDTQ7Q7OmNbcvu6n1hiC/MyIngredients").document(id).delete()
    }

    fun modificarIngrediente(id: String, name : String, type : String, flavor : String, holidayExclusive : Boolean, holiday : String) {
        val newIngredient = Ingredient(name, type, flavor, holidayExclusive, holiday)
        conexion.collection("Customers/RDTQ7Q7OmNbcvu6n1hiC/MyIngredients").document(id).set(newIngredient)
    }

}