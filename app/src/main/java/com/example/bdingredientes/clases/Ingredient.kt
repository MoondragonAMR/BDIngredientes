package com.example.bdingredientes.clases

data class Ingredient(var name : String = "", var type : String = "Topping", var flavor : String = "sweet"
                      , var holidayExclusive : Boolean = false, var holiday : String = "Standard"
                      , var food : String = "Pizza", var number : Int = 0, var game : String = "Pizzeria"
                      , var orderPart : String = "Pizza Topping") {
    var id = ""
}
