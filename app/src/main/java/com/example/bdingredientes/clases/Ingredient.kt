package com.example.bdingredientes.clases

data class Ingredient(var name : String = "", var flavor : String = "sweet", var type : String = ""
                      , var holidayExclusive : Boolean = false, var holiday : String = "standard") {
    var id = ""
}
