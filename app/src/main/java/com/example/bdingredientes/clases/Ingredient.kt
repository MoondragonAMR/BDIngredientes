package com.example.bdingredientes.clases

data class Ingredient(var name : String = "", var type : String = "", var flavor : String = "sweet"
                      , var holidayExclusive : Boolean = false, var holiday : String = "Standard") {
    var id = ""
}
