package com.example.bdingredientes.clases
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class VMBD : ViewModel() {
    val conexion = FirebaseFirestore.getInstance()
    private lateinit var listener: ListenerRegistration
    var _ingredients = MutableStateFlow(mutableStateListOf<Ingredient>())
    var ingredients = _ingredients.asStateFlow()
    var _ingredientsAleatorio = MutableStateFlow(mutableStateListOf<Ingredient>())
    var ingredientsAleatorio = _ingredientsAleatorio.asStateFlow()
    fun crearListener() {
        listener = conexion.collection("Ingredients").addSnapshotListener { datos, error ->
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
        conexion.collection("Ingredients").add(newIngredient)
    }

    fun borrarIngrediente(id: String) {
        conexion.collection("Ingredients").document(id).delete()
    }

    fun modificarIngrediente(id: String, name : String, type : String, flavor : String, holidayExclusive : Boolean, holiday : String) {
        val newIngredient = Ingredient(name, type, flavor, holidayExclusive, holiday)
        conexion.collection("Ingredients").document(id).set(newIngredient)
    }

    fun filtrarIngredientes(numero : Long, parametro : String, valor : String) {
        val valores = mutableListOf<String>()
        when (valor) {
            "Aditive" -> {
                valores.add("Aditive")
                valores.add("Aditive/Ice")
            }
            "Bread" -> {
                valores.add("Bread")
                valores.add("Bread/Burger Bun")
                valores.add("Bread/Sandwich Bread")
            }
            "Breading" -> {
                valores.add("Breading")
            }
            "Breakfast Food" -> {
                valores.add("Breakfast Food")
                valores.add("Breakfast Food/Burger Bun")
            }
            "Breakfast Mixable" -> {
                valores.add("Breakfast Mixable")
            }
            "Bubble Tea" -> {
                valores.add("Bubble Tea")
                valores.add("Bubble Tea/Drink")
                valores.add("Drink/Bubble Tea")
            }
            "Burger Bun" -> {
                valores.add("Burger Bun")
                valores.add("Bread/Burger Bun")
                valores.add("Breakfast Food/Burger Bun")
                valores.add("Hot Dog Bun/Burger Bun")
                valores.add("Pizza Crust/Burger Bun")
            }
            "Burger Meat" -> {
                valores.add("Burger Meat")
                valores.add("Sushi Filling/Burger Meat")
                valores.add("Topping/Burger Meat")
            }
            "Cake" -> {
                valores.add("Cake")
                valores.add("Cake/Donut Dough")
                valores.add("Cake/Mixable")
            }
            "Cannoli Shell" -> {
                valores.add("Cannoli Shell")
                valores.add("Topper/Long Topper/Cannoli Shell")
            }
            "Chicken Sauce" -> {
                valores.add("Chicken Sauce")
                valores.add("Chicken Sauce/Pizza Sauce")
                valores.add("Fry Topping/Chicken Sauce")
                valores.add("Sauce/Chicken Sauce")
                valores.add("Sauce/Chicken Sauce/Fry Topping")
                valores.add("Sauce/Chicken Sauce/Pizza Sauce")
                valores.add("Sauce/Fry Topping/Chicken Sauce")
                valores.add("Syrup/Sauce/Chicken Sauce")
            }
            "Chicken Wings" -> {
                valores.add("Chicken Wings")
                valores.add("Chicken Wings/Topping")
            }
            "Coffee" -> {
                valores.add("Coffee")
                valores.add("Drink/Coffee")
            }
            "Cookie Dough" -> {
                valores.add("Cookie Dough")
            }
            "Cookie Mixable" -> {
                valores.add("Cookie Mixable")
                valores.add("Cookie Mixable/Shaker")
                valores.add("Mixable/Cookie Mixable")
                valores.add("Mixable/Cookie Mixable/Shaker")
                valores.add("Pie Filling/Cookie Mixable")
                valores.add("Popcorn/Shaker/Cookie Mixable")
                valores.add("Shaker/Cookie Mixable")
                valores.add("Tea Bubbles/Cookie Mixable")
                valores.add("Topper/Cookie Mixable/Shaker")
                valores.add("Topping/Cookie Mixable")
                valores.add("Topping/Cookie Mixable/Shaker/Topper")
            }
            "Cream" -> {
                valores.add("Cream")
                valores.add("Cream/Cream Dollop/Donut Filling")
                valores.add("Cream/Donut Filling")
                valores.add("Cream/Donut Filling/Cream Dollop")
                valores.add("Cream/Milk")
                valores.add("Donut Filling/Cream")
                valores.add("Pie Filling/Cream")
            }
            "Cream Dollop" -> {
                valores.add("Cream Dollop")
                valores.add("Cream/Cream Dollop/Donut Filling")
                valores.add("Cream/Donut Filling/Cream Dollop")
                valores.add("Topper/Cream Dollop")
            }
            "Dip" -> {
                valores.add("Dip")
                valores.add("Dip/Pasta Sauce")
                valores.add("Dip/Sauce")
                valores.add("Pasta Sauce/Pizza Sauce/Dip")
                valores.add("Sauce/Dip")
                valores.add("Sauce/Dip/Fry Topping")
                valores.add("Topping/Dip")
                valores.add("Topping/Sauce/Fry Topping/Dip/Nacho Dip")
            }
            "Donut Cutter" -> {
                valores.add("Donut Cutter")
            }
            "Donut Dough" -> {
                valores.add("Donut Dough")
                valores.add("Cake/Donut Dough")
            }
            "Donut Filling" -> {
                valores.add("Donut Filling")
                valores.add("Donut Filling/Pie Filling")
                valores.add("Donut Filling/Cream")
                valores.add("Cream/Cream Dollop/Donut Filling")
                valores.add("Cream/Donut Filling")
                valores.add("Cream/Donut Filling/Cream Dollop")
            }
            "Drink" -> {
                valores.add("Drink")
                valores.add("Bubble Tea/Drink")
                valores.add("Drink/Bubble Tea")
                valores.add("Drink/Coffee")
                valores.add("Drink/Milk")
                valores.add("Drink/Soda")
            }
            "Flat Topper" -> {
                valores.add("Flat Topper")
                valores.add("Flat Topper/Mixable")
                valores.add("Mixable/Flat Topper/Topping")
                valores.add("Topper/Flat Topper")
                valores.add("Topper/Long Topper/Topping/Flat Topper")
                valores.add("Topping/Flat Topper")
            }
            "Fries" -> {
                valores.add("Fries")
                valores.add("Fries/Side")
                valores.add("Fries/Topping/Sushi Filling/Side")
                valores.add("Side/Fries")
            }
            "Frosting" -> {
                valores.add("Frosting")
            }
            "Fry Topping" -> {
                valores.add("Fry Topping")
                valores.add("Fry Topping/Chicken Sauce")
                valores.add("Sauce/Chicken Sauce/Fry Topping")
                valores.add("Sauce/Dip/Fry Topping")
                valores.add("Sauce/Fry Topping")
                valores.add("Sauce/Fry Topping/Chicken Sauce")
                valores.add("Sauce/Nacho Dip/Fry Topping")
                valores.add("Sauce/Topping/Fry Topping")
                valores.add("Shaker/Fry Topping")
                valores.add("Side/Topping/Fry Topping")
                valores.add("Topping/Fry Topping")
                valores.add("Topping/Fry Topping/Shaker")
                valores.add("Topping/Fry Topping/Sushi Filling")
                valores.add("Topping/Pasta Sauce/Fry Topping")
                valores.add("Topping/Sauce/Fry Topping")
                valores.add("Topping/Sauce/Fry Topping/Dip/Nacho Dip")
            }
            "Hot Dog Bun" -> {
                valores.add("Hot Dog Bun")
                valores.add("Hot Dog Bun/Burger Bun")
            }
            "Ice" -> {
                valores.add("Ice")
                valores.add("Aditive/Ice")
            }
            "Ice Cream" -> {
                valores.add("Ice Cream")
            }
            "Icing" -> {
                valores.add("Icing")
                valores.add("Icing/Powder")
                valores.add("Icing/Shaker")
                valores.add("Shaker/Icing")
            }
            "Long Topper" -> {
                valores.add("Topper/Long Topper")
                valores.add("Topper/Long Topper/Cannoli Shell")
                valores.add("Topper/Long Topper/Topping/Flat Topper")
                valores.add("Topping/Long Topper")
                valores.add("Topping/Topper/Long Topper/Sushi Filling/Side")
            }
            "Milk" -> {
                valores.add("Milk")
                valores.add("Cream/Milk")
                valores.add("Drink/Milk")
            }
            "Mixable" -> {
                valores.add("Mixable")
                valores.add("Cake/Mixable")
                valores.add("Flat Topper/Mixable")
                valores.add("Mixable/Cookie Mixable")
                valores.add("Mixable/Cookie Mixable/Shaker")
                valores.add("Mixable/Flat Topper/Topping")
                valores.add("Mixable/Popcorn")
                valores.add("Mixable/Shaker")
                valores.add("Mixable/Sushi Filling/Topping")
                valores.add("Mixable/Topper")
                valores.add("Mixable/Topping/Sushi Filling")
                valores.add("Pie Filling/Mixable")
                valores.add("Topping/Mixable")
            }
            "Mixable Syrup" -> {
                valores.add("Mixable Syrup")
                valores.add("Mixable Syrup/Syrup")
                valores.add("Syrup/Mixable Syrup")
            }
            "Nacho Chips" -> {
                valores.add("Nacho Chips")
            }
            "Nacho Dip" -> {
                valores.add("Nacho Dip")
                valores.add("Sauce/Nacho Dip")
                valores.add("Sauce/Nacho Dip/Fry Topping")
                valores.add("Topping/Nacho Dip")
                valores.add("Topping/Nacho Dip/Sauce")
                valores.add("Topping/Sauce/Fry Topping/Dip/Nacho Dip")
            }
            "Pasta" -> {
                valores.add("Pasta")
            }
            "Pasta Sauce" -> {
                valores.add("Pasta Sauce")
                valores.add("Dip/Pasta Sauce")
                valores.add("Pasta Sauce/Pizza Sauce")
                valores.add("Pasta Sauce/Pizza Sauce/Dip")
                valores.add("Pasta Sauce/Sauce")
                valores.add("Sauce/Pasta Sauce/Pizza Sauce")
                valores.add("Sauce/Pasta Sauce/Pizza Sauce/Topping")
                valores.add("Topping/Pasta Sauce/Fry Topping")
            }
            "Pie Crust" -> {
                valores.add("Pie Crust")
            }
            "Pie Filling" -> {
                valores.add("Pie Filling")
                valores.add("Donut Filling/Pie Filling")
                valores.add("Pie Filling/Cookie Mixable")
                valores.add("Pie Filling/Cream")
                valores.add("Pie Filling/Mixable")
            }
            "Pizza Cheese" -> {
                valores.add("Pizza Cheese")
                valores.add("Pizza Cheese/Sandwich Cheese")
                valores.add("Topping/Pizza Cheese/Sandwich Cheese")
            }
            "Pizza Crust" -> {
                valores.add("Pizza Crust")
                valores.add("Pizza Crust/Burger Bun")
            }
            "Pizza Sauce" -> {
                valores.add("Pizza Sauce")
                valores.add("Chicken Sauce/Pizza Sauce")
                valores.add("Pasta Sauce/Pizza Sauce")
                valores.add("Pasta Sauce/Pizza Sauce/Dip")
                valores.add("Sauce/Chicken Sauce/Pizza Sauce")
                valores.add("Sauce/Pasta Sauce/Pizza Sauce")
                valores.add("Sauce/Pasta Sauce/Pizza Sauce/Topping")
                valores.add("Sauce/Pizza Sauce")
            }
            "Popcorn" -> {
                valores.add("Popcorn")
                valores.add("Mixable/Popcorn")
                valores.add("Popcorn/Shaker/Cookie Mixable")
                valores.add("Popcorn/Topping")
                valores.add("Topper/Shaker/Popcorn")
            }
            "Powder" -> {
                valores.add("Powder")
                valores.add("Icing/Powder")
            }
            "Rice" -> {
                valores.add("Rice")
                valores.add("Rice/Topping")
                valores.add("Topping/Rice")
            }
            "Sandwich Bread" -> {
                valores.add("Sandwich Bread")
                valores.add("Bread/Sandwich Bread")
                valores.add("Sandwich Bread/Soy Paper")
            }
            "Sandwich Cheese" -> {
                valores.add("Sandwich Cheese")
                valores.add("Pizza Cheese/Sandwich Cheese")
                valores.add("Topping/Pizza Cheese/Sandwich Cheese")
                valores.add("Topping/Sandwich Cheese")
            }
            "Sauce" -> {
                valores.add("Sauce")
                valores.add("Dip/Sauce")
                valores.add("Pasta Sauce/Sauce")
                valores.add("Sauce/Chicken Sauce")
                valores.add("Sauce/Chicken Sauce/Fry Topping")
                valores.add("Sauce/Chicken Sauce/Pizza Sauce")
                valores.add("Sauce/Dip")
                valores.add("Sauce/Dip/Fry Topping")
                valores.add("Sauce/Fry Topping")
                valores.add("Sauce/Fry Topping/Chicken Sauce")
                valores.add("Sauce/Nacho Dip")
                valores.add("Sauce/Nacho Dip/Fry Topping")
                valores.add("Sauce/Pasta Sauce/Pizza Sauce")
                valores.add("Sauce/Pasta Sauce/Pizza Sauce/Topping")
                valores.add("Sauce/Pizza Sauce")
                valores.add("Sauce/Topping")
                valores.add("Sauce/Topping/Fry Topping")
                valores.add("Syrup/Sauce")
                valores.add("Syrup/Sauce/Chicken Sauce")
                valores.add("Topping/Nacho Dip/Sauce")
                valores.add("Topping/Sauce/Fry Topping")
                valores.add("Topping/Sauce/Fry Topping/Dip/Nacho Dip")
            }
            "Sausage" -> {
                valores.add("Sausage")
            }
            "Shaker" -> {
                valores.add("Shaker")
                valores.add("Cookie Mixable/Shaker")
                valores.add("Mixable/Cookie Mixable/Shaker")
                valores.add("Mixable/Shaker")
                valores.add("Popcorn/Shaker/Cookie Mixable")
                valores.add("Icing/Shaker")
                valores.add("Shaker/Cookie Mixable")
                valores.add("Shaker/Fry Topping")
                valores.add("Shaker/Icing")
                valores.add("Shaker/Topper")
                valores.add("Top Crust/Shaker")
                valores.add("Topper/Cookie Mixable/Shaker")
                valores.add("Topper/Shaker/Popcorn")
                valores.add("Topping/Cookie Mixable/Shaker/Topper")
                valores.add("Topping/Fry Topping/Shaker")
                valores.add("Topping/Shaker")
            }
            "Side" -> {
                valores.add("Side")
                valores.add("Fries/Side")
                valores.add("Fries/Topping/Sushi Filling/Side")
                valores.add("Side/Fries")
                valores.add("Side/Sushi Filling")
                valores.add("Side/Topping/Fry Topping")
                valores.add("Taco Meat/Side")
                valores.add("Topping/Side")
                valores.add("Topping/Topper/Long Topper/Sushi Filling/Side")
            }
            "Slush" -> {
                valores.add("Slush")
            }
            "Soda" -> {
                valores.add("Soda")
                valores.add("Drink/Soda")
            }
            "Soy Paper" -> {
                valores.add("Soy Paper")
                valores.add("Sandwich Bread/Soy Paper")
            }
            "Sushi Filling" -> {
                valores.add("Sushi Filling")
                valores.add("Fries/Topping/Sushi Filling/Side")
                valores.add("Mixable/Sushi Filling/Topping")
                valores.add("Mixable/Topping/Sushi Filling")
                valores.add("Side/Sushi Filling")
                valores.add("Sushi Filling/Burger Meat")
                valores.add("Sushi Filling/Topping")
                valores.add("Taco Meat/Sushi Filling")
                valores.add("Topping/Fry Topping/Sushi Filling")
                valores.add("Topping/Sushi Filling")
                valores.add("Topping/Sushi Filling/Taco Meat")
                valores.add("Topping/Topper/Long Topper/Sushi Filling/Side")
            }
            "Syrup" -> {
                valores.add("Syrup")
                valores.add("Mixable Syrup/Syrup")
                valores.add("Syrup/Mixable Syrup")
                valores.add("Syrup/Sauce")
                valores.add("Syrup/Sauce/Chicken Sauce")
            }
            "Taco Meat" -> {
                valores.add("Taco Meat")
                valores.add("Taco Meat/Side")
                valores.add("Taco Meat/Sushi Filling")
                valores.add("Taco Meat/Topping")
                valores.add("Topping/Sushi Filling/Taco Meat")
                valores.add("Topping/Taco Meat")
            }
            "Taco Shell" -> {
                valores.add("Taco Shell")
            }
            "Tea Bubbles" -> {
                valores.add("Tea Bubbles")
                valores.add("Tea Bubbles/Cookie Mixable")
            }
            "Top Crust" -> {
                valores.add("Top Crust")
                valores.add("Top Crust/Shaker")
            }
            "Topper" -> {
                valores.add("Topper")
                valores.add("Mixable/Topper")
                valores.add("Topper/Cookie Mixable/Shaker")
                valores.add("Topper/Cream Dollop")
                valores.add("Topper/Flat Topper")
                valores.add("Topper/Long Topper")
                valores.add("Topper/Long Topper/Cannoli Shell")
                valores.add("Topper/Long Topper/Topping/Flat Topper")
                valores.add("Topper/Shaker/Popcorn")
                valores.add("Topper/Topping")
                valores.add("Topping/Cookie Mixable/Shaker/Topper")
                valores.add("Topping/Topper")
                valores.add("Topping/Topper/Long Topper/Sushi Filling/Side")
            }
            "Topping" -> {
                valores.add("Topping")
                valores.add("Topper/Long Topper/Topping/Flat Topper")
                valores.add("Topper/Topping")
                valores.add("Taco Meat/Topping")
                valores.add("Fries/Topping/Sushi Filling/Side")
                valores.add("Mixable/Sushi Filling/Topping")
                valores.add("Mixable/Topping/Sushi Filling")
                valores.add("Sushi Filling/Topping")
                valores.add("Side/Topping/Fry Topping")
                valores.add("Sauce/Topping")
                valores.add("Sauce/Topping/Fry Topping")
                valores.add("Rice/Topping")
                valores.add("Popcorn/Topping")
                valores.add("Sauce/Pasta Sauce/Pizza Sauce/Topping")
                valores.add("Mixable/Flat Topper/Topping")
                valores.add("Chicken Wings/Topping")
                valores.add("Topping/Burger Meat")
                valores.add("Topping/Cookie Mixable")
                valores.add("Topping/Cookie Mixable/Shaker/Topper")
                valores.add("Topping/Dip")
                valores.add("Topping/Flat Topper")
                valores.add("Topping/Fry Topping")
                valores.add("Topping/Fry Topping/Shaker")
                valores.add("Topping/Fry Topping/Sushi Filling")
                valores.add("Topping/Long Topper")
                valores.add("Topping/Mixable")
                valores.add("Topping/Nacho Dip")
                valores.add("Topping/Nacho Dip/Sauce")
                valores.add("Topping/Pasta Sauce/Fry Topping")
                valores.add("Topping/Pizza Cheese/Sandwich Cheese")
                valores.add("Topping/Rice")
                valores.add("Topping/Sandwich Cheese")
                valores.add("Topping/Sauce/Fry Topping")
                valores.add("Topping/Sauce/Fry Topping/Dip/Nacho Dip")
                valores.add("Topping/Shaker")
                valores.add("Topping/Side")
                valores.add("Topping/Sushi Filling")
                valores.add("Topping/Sushi Filling/Taco Meat")
                valores.add("Topping/Taco Meat")
                valores.add("Topping/Topper")
                valores.add("Topping/Topper/Long Topper/Sushi Filling/Side")
            }
            "savory" -> {
                valores.add("savory")
                valores.add("savory/sour")
                valores.add("savory/spicy")
                valores.add("savory/spicy/sour")
                valores.add("savory/sweet")
                valores.add("savory/sweet/spicy")
                valores.add("sweet and savory")
            }
            "sweet" -> {
                valores.add("sweet")
                valores.add("sweet and savory")
                valores.add("sweet/sour")
                valores.add("sweet/spicy")
            }
            "true" -> {
                valores.add("true")
            }
            "false" -> {
                valores.add("false")
            }
            "Standard" -> {
                valores.add("Standard")
                valores.add("Portallini Feast/Standard")
                valores.add("Easter/Standard")
                valores.add("Valentines/Standard")
                valores.add("Lunar New Year/Standard")
                valores.add("Comet Con/Standard")
                valores.add("Cherry Blossom Festival/Standard")
                valores.add("Summer Luau/Standard")
                valores.add("Lucky Lucky Matsuri/Standard")
                valores.add("Starlight/Standard")
                valores.add("Chilifest/Standard")
                valores.add("Onionfest/Standard")
                valores.add("Big Top Carnival/Standard")
                valores.add("St Paddy/Standard")
                valores.add("Thanksgiving/Standard")
                valores.add("Bavaria Fest/Standard")
                valores.add("New Year/Standard")
                valores.add("Groovstock/Standard")
                valores.add("Maple Mornings/Standard")
                valores.add("Cinco de Mayo/Standard")
                valores.add("Easter/Big Top Carnival/Standard")
                valores.add("Baseball Season/Standard")
                valores.add("Film Fest/Standard")
                valores.add("Christmas/Standard")
                valores.add("Pirate Bash/Standard")
                valores.add("Baseball Season/Maple Mornings/Standard")
                valores.add("Baseball Season/Big Top Carnival/Film Fest/Standard")
                valores.add("Summer Luau/Maple Mornings/Standard")
                valores.add("Neptune's Feast/Pirate Bash/Standard")
                valores.add("Halloween/Standard")
                valores.add("Onionfest/New Year/Standard")
                valores.add("Onionfest/Mardi Gras/Standard")
                valores.add("St Paddy/Cinco de Mayo/Standard")
            }
            "Baseball Season" -> {
                valores.add("Baseball Season")
                valores.add("Baseball Season/Big Top Carnival/Film Fest")
                valores.add("Baseball Season/Maple Mornings")
                valores.add("Baseball Season/Standard")
                valores.add("Baseball Season/Maple Mornings/Standard")
                valores.add("Baseball Season/Big Top Carnival/Film Fest/Standard")
            }
            "Bavaria Fest" -> {
                valores.add("Bavaria Fest")
                valores.add("Bavaria Fest/Standard")
            }
            "Big Top Carnival" -> {
                valores.add("Big Top Carnival")
                valores.add("Baseball Season/Big Top Carnival/Film Fest")
                valores.add("Big Top Carnival/Maple Mornings")
                valores.add("Easter/Big Top Carnival")
                valores.add("New Year/Big Top Carnival")
                valores.add("Big Top Carnival/Standard")
                valores.add("Easter/Big Top Carnival/Standard")
                valores.add("Baseball Season/Big Top Carnival/Film Fest/Standard")
            }
            "Cherry Blossom Festival" -> {
                valores.add("Cherry Blossom Festival")
                valores.add("Cherry Blossom Festival/Sky Ninja Returns")
                valores.add("Cherry Blossom Festival/Standard")
            }
            "Chilifest" -> {
                valores.add("Chilifest")
                valores.add("Chilifest/Standard")
            }
            "Christmas" -> {
                valores.add("Christmas")
                valores.add("Christmas/Standard")
            }
            "Cinco de Mayo" -> {
                valores.add("Cinco de Mayo")
                valores.add("Cinco de Mayo/New Year")
                valores.add("New Year/Cinco de Mayo")
                valores.add("St Paddy/Cinco de Mayo")
                valores.add("Cinco de Mayo/Standard")
                valores.add("St Paddy/Cinco de Mayo/Standard")
            }
            "Comet Con" -> {
                valores.add("Comet Con")
                valores.add("Comet Con/Standard")
            }
            "Day of the Dead" -> {
                valores.add("Day of the Dead")
                valores.add("Halloween/Day of the Dead")
            }
            "Easter" -> {
                valores.add("Easter")
                valores.add("Easter/Big Top Carnival")
                valores.add("Easter/Maple Mornings")
                valores.add("Easter/Romano Wedding")
                valores.add("Easter/Starlight")
                valores.add("Easter/Standard")
                valores.add("Easter/Big Top Carnival/Standard")
            }
            "Film Fest" -> {
                valores.add("Film Fest")
                valores.add("Baseball Season/Big Top Carnival/Film Fest")
                valores.add("Film Fest/Standard")
                valores.add("Baseball Season/Big Top Carnival/Film Fest/Standard")
            }
            "Gondola" -> {
                valores.add("Gondola")
            }
            "Groovstock" -> {
                valores.add("Groovstock")
                valores.add("Groovstock/Standard")
            }
            "Halloween" -> {
                valores.add("Halloween")
                valores.add("Halloween/Day of the Dead")
                valores.add("Halloween/Thanksgiving")
                valores.add("Halloween/Standard")
            }
            "Holi" -> {
                valores.add("Holi")
            }
            "Lucky Lucky Matsuri" -> {
                valores.add("Lucky Lucky Matsuri")
                valores.add("St Paddy/Lucky Lucky Matsuri")
                valores.add("Lucky Lucky Matsuri/Standard")
            }
            "Lunar New Year" -> {
                valores.add("Lunar New Year")
                valores.add("Lunar New Year/Standard")
            }
            "Maple Mornings" -> {
                valores.add("Maple Mornings")
                valores.add("Baseball Season/Maple Mornings")
                valores.add("Big Top Carnival/Maple Mornings")
                valores.add("Easter/Maple Mornings")
                valores.add("Maple Mornings/Portallini Feast")
                valores.add("Summer Luau/Maple Mornings")
                valores.add("Maple Mornings/Standard")
                valores.add("Baseball Season/Maple Mornings/Standard")
                valores.add("Summer Luau/Maple Mornings/Standard")
            }
            "Mardi Gras" -> {
                valores.add("Mardi Gras")
                valores.add("Neptune's Feast/Mardi Gras")
                valores.add("Onionfest/Mardi Gras")
                valores.add("Onionfest/Mardi Gras/Standard")
            }
            "Neptune's Feast" -> {
                valores.add("Neptune's Feast")
                valores.add("Neptune's Feast/Mardi Gras")
                valores.add("Neptune's Feast/Pirate Bash")
                valores.add("Neptune's Feast/Pirate Bash/Standard")
            }
            "New Year" -> {
                valores.add("New Year")
                valores.add("Cinco de Mayo/New Year")
                valores.add("New Year/Big Top Carnival")
                valores.add("New Year/Cinco de Mayo")
                valores.add("New Year/Starlight")
                valores.add("Onionfest/New Year")
                valores.add("New Year/Standard")
                valores.add("Onionfest/New Year/Standard")
            }
            "Onionfest" -> {
                valores.add("Onionfest")
                valores.add("Onionfest/Mardi Gras")
                valores.add("Onionfest/New Year")
                valores.add("Onionfest/Standard")
                valores.add("Onionfest/New Year/Standard")
                valores.add("Onionfest/Mardi Gras/Standard")
            }
            "Pirate Bash" -> {
                valores.add("Pirate Bash")
                valores.add("Neptune's Feast/Pirate Bash")
                valores.add("Pirate Bash/Standard")
                valores.add("Neptune's Feast/Pirate Bash/Standard")
            }
            "Portallini Feast" -> {
                valores.add("Portallini Feast")
                valores.add("Maple Mornings/Portallini Feast")
                valores.add("Portallini Feast/Standard")
            }
            "Romano Wedding" -> {
                valores.add("Romano Wedding")
                valores.add("Easter/Romano Wedding")
            }
            "Sky Ninja Returns" -> {
                valores.add("Sky Ninja Returns")
                valores.add("Cherry Blossom Festival/Sky Ninja Returns")
            }
            "St Paddy" -> {
                valores.add("St Paddy")
                valores.add("St Paddy/Cinco de Mayo")
                valores.add("St Paddy/Lucky Lucky Matsuri")
                valores.add("St Paddy/Standard")
                valores.add("St Paddy/Cinco de Mayo/Standard")
            }
            "Starlight" -> {
                valores.add("Starlight")
                valores.add("Easter/Starlight")
                valores.add("New Year/Starlight")
                valores.add("Starlight/Standard")
            }
            "Summer Luau" -> {
                valores.add("Summer Luau")
                valores.add("Summer Luau/Maple Mornings")
                valores.add("Summer Luau/Standard")
                valores.add("Summer Luau/Maple Mornings/Standard")
            }
            "Thanksgiving" -> {
                valores.add("Thanksgiving")
                valores.add("Halloween/Thanksgiving")
                valores.add("Thanksgiving/Standard")
            }
            "Valentines" -> {
                valores.add("Valentines")
                valores.add("Valentines/Standard")
            }
            "Volcano Gala" -> {
                valores.add("Volcano Gala")
            }
        }
        conexion.collection("Ingredients").whereIn(parametro, valores).orderBy(parametro).limit(numero).get().addOnSuccessListener {
            _ingredients.value.clear()
            it.documents.forEach {
                docu->_ingredients.value.add(docu.toObject()!!)
            }
        }
    }

    fun filtrarIngredientes(numero : Long, parametro : String) {
        conexion.collection("Ingredients").orderBy(parametro).limit(numero).get().addOnSuccessListener {
            _ingredients.value.clear()
            it.documents.forEach { docu ->
                _ingredients.value.add(docu.toObject()!!)
            }
        }
    }

    fun mezclarIngredientes() {
        _ingredientsAleatorio.value = _ingredients.value.shuffled().toMutableStateList()
    }

}