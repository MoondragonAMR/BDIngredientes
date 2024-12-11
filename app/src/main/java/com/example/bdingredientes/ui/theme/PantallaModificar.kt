package com.example.bdingredientes.ui.theme

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bdingredientes.clases.ViewModelScaffold
import com.example.bdingredientes.clases.celebracion
import com.example.bdingredientes.clases.comida2
import com.example.bdingredientes.clases.deCelebracion
import com.example.bdingredientes.clases.juego3
import com.example.bdingredientes.clases.nombre
import com.example.bdingredientes.clases.numero
import com.example.bdingredientes.clases.parte2
import com.example.bdingredientes.clases.sabor
import com.example.bdingredientes.clases.tipo

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaModificar(
    name: String,
    type: String,
    flavor: String,
    holidayExclusive: Boolean,
    holiday: String,
    food: String,
    number: Int,
    game: String,
    part: String, sf : ViewModelScaffold = viewModel()
) {

    val idioma = sf.english.collectAsState().value

    var nombreAdd by remember { mutableStateOf(name) }
    nombre = nombreAdd
    var tipoAdd by remember { mutableStateOf(type) }
    tipo = tipoAdd
    var saborAdd by remember { mutableStateOf(flavor) }
    sabor = saborAdd
    var deCelebracionAdd by remember { mutableStateOf(holidayExclusive) }
    deCelebracion = deCelebracionAdd
    var celebracionAdd by remember { mutableStateOf(holiday) }
    celebracion = celebracionAdd
    var comidaAdd by remember { mutableStateOf(food) }
    comida2 = comidaAdd
    var juegoAdd by remember { mutableStateOf(game) }
    juego3 = juegoAdd
    var parteAdd by remember { mutableStateOf(part) }
    parte2 = parteAdd
    numero = number
    var activado by remember { mutableStateOf(false) }
    var activado2 by remember { mutableStateOf(false) }
    var activado3 by remember { mutableStateOf(false) }
    var activado4 by remember { mutableStateOf(false) }
    var activado5 by remember { mutableStateOf(false) }
    var activado6 by remember { mutableStateOf(false) }

    Column {
        Row {
            val textoNombre = if (idioma.value) {
                "Name: "
            } else {
                "Nombre: "
            }
            Text (textoNombre)
            val textoPlaceholderNombre = if (idioma.value) {
                "Insert the name of the ingredient"
            } else {
                "Introduce el nombre del ingrediente"
            }
            TextField(
                value = nombreAdd,
                onValueChange = { texto -> nombreAdd = texto; nombre = nombreAdd },
                placeholder = { Text(textoPlaceholderNombre) })
        }
        Row {
            val textoTipo = if (idioma.value) {
                "Type: "
            } else {
                "Tipo: "
            }

            Text (textoTipo)
            ExposedDropdownMenuBox(expanded = activado,
                onExpandedChange = {activado = !activado} ) {
                TextField(modifier = Modifier.menuAnchor(),
                    readOnly = true,
                    value = tipoAdd,
                    onValueChange = {}
                )
                DropdownMenu(expanded = activado, onDismissRequest = { activado = false }) {
                    val textoTipo1 = if (idioma.value) {
                        "Aditive"
                    } else {
                        "Aditivo"
                    }
                    DropdownMenuItem(
                        text = { Text(textoTipo1) },
                        onClick = { tipoAdd = textoTipo1; tipo = "Aditive" })
                    val textoTipo2 = if (idioma.value) {
                        "Bread"
                    } else {
                        "Pan"
                    }
                    DropdownMenuItem(
                        text = { Text(textoTipo2) },
                        onClick = { tipoAdd = textoTipo2; tipo = "Bread" })
                    val textoTipo3 = if (idioma.value) {
                        "Breading"
                    } else {
                        "Empanado"
                    }
                    DropdownMenuItem(
                        text = { Text(textoTipo3) },
                        onClick = { tipoAdd = textoTipo3; tipo = "Breading" })
                    val textoTipo4 = if (idioma.value) {
                        "Breakfast Food"
                    } else {
                        "Comida de desayuno"
                    }
                    DropdownMenuItem(
                        text = { Text(textoTipo4) },
                        onClick = { tipoAdd = textoTipo4; tipo = "Breakfast Food" })
                    val textoTipo5 = if (idioma.value) {
                        "Breakfast Mixable"
                    } else {
                        "Trozos para desayuno"
                    }
                    DropdownMenuItem(
                        text = { Text(textoTipo5) },
                        onClick = { tipoAdd = textoTipo5; tipo = "Breakfast Mixable" })
                    val textoTipo6 = if (idioma.value) {
                        "Bubble Tea"
                    } else {
                        "Té de burbujas"
                    }
                    DropdownMenuItem(
                        text = { Text(textoTipo6) },
                        onClick = { tipoAdd = textoTipo6; tipo = "Bubble Tea" })
                    val textoTipo7 = if (idioma.value) {
                        "Burger Bun"
                    } else {
                        "Pan de hamburguesa"
                    }
                    DropdownMenuItem(
                        text = { Text(textoTipo7) },
                        onClick = { tipoAdd = textoTipo7; tipo = "Burger Bun" })
                    val textoTipo8 = if (idioma.value) {
                        "Burger Meat"
                    } else {
                        "Carne de hamburguesa"
                    }
                    DropdownMenuItem(
                        text = { Text(textoTipo8) },
                        onClick = { tipoAdd = textoTipo8; tipo = "Burger Meat" })
                    val textoTipo9 = if (idioma.value) {
                        "Cake"
                    } else {
                        "Masa de cupcakes"
                    }
                    DropdownMenuItem(
                        text = { Text(textoTipo9) },
                        onClick = { tipoAdd = textoTipo9; tipo = "Cake" })
                    val textoTipo10 = if (idioma.value) {
                        "Cannoli Shell"
                    } else {
                        "Masa de cannoli"
                    }
                    DropdownMenuItem(
                        text = { Text(textoTipo10) },
                        onClick = { tipoAdd = textoTipo10; tipo = "Cannoli Shell" })
                    val textoTipo11 = if (idioma.value) {
                        "Chicken Sauce"
                    } else {
                        "Salsa para pollo"
                    }
                    DropdownMenuItem(
                        text = { Text(textoTipo11) },
                        onClick = { tipoAdd = textoTipo11; tipo = "Chicken Sauce" })
                    val textoTipo12 = if (idioma.value) {
                        "Chicken Wings"
                    } else {
                        "Pollo frito"
                    }
                    DropdownMenuItem(
                        text = { Text(textoTipo12) },
                        onClick = { tipoAdd = textoTipo12; tipo = "Chicken Wings" })
                    val textoTipo13 = if (idioma.value) {
                        "Coffee"
                    } else {
                        "Café"
                    }
                    DropdownMenuItem(
                        text = { Text(textoTipo13) },
                        onClick = { tipoAdd = textoTipo13; tipo = "Coffee" })
                    val textoTipo14 = if (idioma.value) {
                        "Cookie Dough"
                    } else {
                        "Masa de galletas"
                    }
                    DropdownMenuItem(
                        text = { Text(textoTipo14) },
                        onClick = { tipoAdd = textoTipo14; tipo = "Cookie Dough" })
                    val textoTipo15 = if (idioma.value) {
                        "Cookie Mixable"
                    } else {
                        "Trozos para galletas"
                    }
                    DropdownMenuItem(
                        text = { Text(textoTipo15) },
                        onClick = { tipoAdd = textoTipo15; tipo = "Cookie Mixable" })
                    val textoTipo16 = if (idioma.value) {
                        "Cream"
                    } else {
                        "Crema"
                    }
                    DropdownMenuItem(
                        text = { Text(textoTipo16) },
                        onClick = { tipoAdd = textoTipo16; tipo = "Cream" })
                    val textoTipo17 = if (idioma.value) {
                        "Cream Dollop"
                    } else {
                        "Bola de crema"
                    }
                    DropdownMenuItem(
                        text = { Text(textoTipo17) },
                        onClick = { tipoAdd = textoTipo17; tipo = "Cream Dollop" })
                    val textoTipo18 = if (idioma.value) {
                        "Dip"
                    } else {
                        "Salsa para mojar"
                    }
                    DropdownMenuItem(
                        text = { Text(textoTipo18) },
                        onClick = { tipoAdd = textoTipo18; tipo = "Dip" })
                    val textoTipo19 = if (idioma.value) {
                        "Donut Cutter"
                    } else {
                        "Forma de donut"
                    }
                    DropdownMenuItem(
                        text = { Text(textoTipo19) },
                        onClick = { tipoAdd = textoTipo19; tipo = "Donut Cutter" })
                    val textoTipo20 = if (idioma.value) {
                        "Donut Dough"
                    } else {
                        "Masa de donut"
                    }
                    DropdownMenuItem(
                        text = { Text(textoTipo20) },
                        onClick = { tipoAdd = textoTipo20; tipo = "Donut Dough" })
                    val textoTipo21 = if (idioma.value) {
                        "Donut Filling"
                    } else {
                        "Relleno de donut"
                    }
                    DropdownMenuItem(
                        text = { Text(textoTipo21) },
                        onClick = { tipoAdd = textoTipo21; tipo = "Donut Filling" })
                    val textoTipo22 = if (idioma.value) {
                        "Drink"
                    } else {
                        "Bebida"
                    }
                    DropdownMenuItem(
                        text = { Text(textoTipo22) },
                        onClick = { tipoAdd = textoTipo22; tipo = "Drink" })
                    val textoTipo23 = if (idioma.value) {
                        "Flat Topper"
                    } else {
                        "Topper plano"
                    }
                    DropdownMenuItem(
                        text = { Text(textoTipo23) },
                        onClick = { tipoAdd = textoTipo23; tipo = "Flat Topper" })
                    val textoTipo24 = if (idioma.value) {
                        "Fries"
                    } else {
                        "Papas fritas"
                    }
                    DropdownMenuItem(
                        text = { Text(textoTipo24) },
                        onClick = { tipoAdd = textoTipo24; tipo = "Fries" })
                    val textoTipo25 = "Frosting"
                    DropdownMenuItem(
                        text = { Text(textoTipo25) },
                        onClick = { tipoAdd = textoTipo25; tipo = "Frosting" })
                    val textoTipo26 = if (idioma.value) {
                        "Fry Topping"
                    } else {
                        "Topping para papas fritas"
                    }
                    DropdownMenuItem(
                        text = { Text(textoTipo26) },
                        onClick = { tipoAdd = textoTipo26; tipo = "Fry Topping" })
                    val textoTipo27 = if (idioma.value) {
                        "Hot Dog Bun"
                    } else {
                        "Pan de perrito caliente"
                    }
                    DropdownMenuItem(
                        text = { Text(textoTipo27) },
                        onClick = { tipoAdd = textoTipo27; tipo = "Hot Dog Bun" })
                    val textoTipo28 = if (idioma.value) {
                        "Ice"
                    } else {
                        "Hielo"
                    }
                    DropdownMenuItem(
                        text = { Text(textoTipo28) },
                        onClick = { tipoAdd = textoTipo28; tipo = "Ice" })
                    val textoTipo29 = if (idioma.value) {
                        "Ice Cream"
                    } else {
                        "Helado"
                    }
                    DropdownMenuItem(
                        text = { Text(textoTipo29) },
                        onClick = { tipoAdd = textoTipo29; tipo = "Ice Cream" })
                    val textoTipo67 = if (idioma.value) {
                        "Icing"
                    } else {
                        "Glaseado"
                    }
                    DropdownMenuItem(
                        text = { Text(textoTipo67) },
                        onClick = { tipoAdd = textoTipo67; tipo = "Icing" })
                    val textoTipo30 = if (idioma.value) {
                        "Long Topper"
                    } else {
                        "Topper largo"
                    }
                    DropdownMenuItem(
                        text = { Text(textoTipo30) },
                        onClick = { tipoAdd = textoTipo30; tipo = "Long Topper" })
                    val textoTipo31 = if (idioma.value) {
                        "Milk"
                    } else {
                        "Leche"
                    }
                    DropdownMenuItem(
                        text = { Text(textoTipo31) },
                        onClick = { tipoAdd = textoTipo31; tipo = "Milk" })
                    val textoTipo32 = if (idioma.value) {
                        "Mixable"
                    } else {
                        "Sabor en trozos"
                    }
                    DropdownMenuItem(
                        text = { Text(textoTipo32) },
                        onClick = { tipoAdd = textoTipo32; tipo = "Mixable" })
                    val textoTipo33 = if (idioma.value) {
                        "Mixable Syrup"
                    } else {
                        "Sabor sirope"
                    }
                    DropdownMenuItem(
                        text = { Text(textoTipo33) },
                        onClick = { tipoAdd = textoTipo33; tipo = "Mixable Syrup" })
                    val textoTipo34 = if (idioma.value) {
                        "Nacho Chips"
                    } else {
                        "Nachos"
                    }
                    DropdownMenuItem(
                        text = { Text(textoTipo34) },
                        onClick = { tipoAdd = textoTipo34; tipo = "Nacho Chips" })
                    val textoTipo35 = if (idioma.value) {
                        "Nacho Dip"
                    } else {
                        "Salsa para nachos"
                    }
                    DropdownMenuItem(
                        text = { Text(textoTipo35) },
                        onClick = { tipoAdd = textoTipo35; tipo = "Nacho Dip" })
                    val textoTipo36 = "Pasta"
                    DropdownMenuItem(
                        text = { Text(textoTipo36) },
                        onClick = { tipoAdd = textoTipo36; tipo = "Pasta" })
                    val textoTipo37 = if (idioma.value) {
                        "Pasta Sauce"
                    } else {
                        "Salsa para pasta"
                    }
                    DropdownMenuItem(
                        text = { Text(textoTipo37) },
                        onClick = { tipoAdd = textoTipo37; tipo = "Pasta Sauce" })
                    val textoTipo38 = if (idioma.value) {
                        "Pie Crust"
                    } else {
                        "Masa de pastel"
                    }
                    DropdownMenuItem(
                        text = { Text(textoTipo38) },
                        onClick = { tipoAdd = textoTipo38; tipo = "Pie Crust" })
                    val textoTipo39 = if (idioma.value) {
                        "Pie Filling"
                    } else {
                        "Relleno de pastel"
                    }
                    DropdownMenuItem(
                        text = { Text(textoTipo39) },
                        onClick = { tipoAdd = textoTipo39; tipo = "Pie Filling" })
                    val textoTipo40 = if (idioma.value) {
                        "Pizza Cheese"
                    } else {
                        "Queso para pizza"
                    }
                    DropdownMenuItem(
                        text = { Text(textoTipo40) },
                        onClick = { tipoAdd = textoTipo40; tipo = "Pizza Cheese" })
                    val textoTipo41 = if (idioma.value) {
                        "Pizza Crust"
                    } else {
                        "Masa de pizza"
                    }
                    DropdownMenuItem(
                        text = { Text(textoTipo41) },
                        onClick = { tipoAdd = textoTipo41; tipo = "Pizza Crust" })
                    val textoTipo42 = if (idioma.value) {
                        "Pizza Sauce"
                    } else {
                        "Salsa para pizza"
                    }
                    DropdownMenuItem(
                        text = { Text(textoTipo42) },
                        onClick = { tipoAdd = textoTipo42; tipo = "Pizza Sauce" })
                    val textoTipo43 = if (idioma.value) {
                        "Popcorn"
                    } else {
                        "Palomitas"
                    }
                    DropdownMenuItem(
                        text = { Text(textoTipo43) },
                        onClick = { tipoAdd = textoTipo43; tipo = "Popcorn" })
                    val textoTipo44 = if (idioma.value) {
                        "Powder"
                    } else {
                        "Polvo"
                    }
                    DropdownMenuItem(
                        text = { Text(textoTipo44) },
                        onClick = { tipoAdd = textoTipo44; tipo = "Powder" })
                    val textoTipo45 = if (idioma.value) {
                        "Rice"
                    } else {
                        "Arroz"
                    }
                    DropdownMenuItem(
                        text = { Text(textoTipo45) },
                        onClick = { tipoAdd = textoTipo45; tipo = "Rice" })
                    val textoTipo46 = if (idioma.value) {
                        "Sandwich Bread"
                    } else {
                        "Pan de sandwich"
                    }
                    DropdownMenuItem(
                        text = { Text(textoTipo46) },
                        onClick = { tipoAdd = textoTipo46; tipo = "Sandwich Bread" })
                    val textoTipo47 = if (idioma.value) {
                        "Sandwich Cheese"
                    } else {
                        "Queso para sandwich"
                    }
                    DropdownMenuItem(
                        text = { Text(textoTipo47) },
                        onClick = { tipoAdd = textoTipo47; tipo = "Sandwich Cheese" })
                    val textoTipo48 = if (idioma.value) {
                        "Sauce"
                    } else {
                        "Salsa"
                    }
                    DropdownMenuItem(
                        text = { Text(textoTipo48) },
                        onClick = { tipoAdd = textoTipo48; tipo = "Sauce" })
                    val textoTipo49 = if (idioma.value) {
                        "Sausage"
                    } else {
                        "Salchicha"
                    }
                    DropdownMenuItem(
                        text = { Text(textoTipo49) },
                        onClick = { tipoAdd = textoTipo49; tipo = "Sausage" })
                    val textoTipo50 = if (idioma.value) {
                        "Shaker"
                    } else {
                        "Topping de bote"
                    }
                    DropdownMenuItem(
                        text = { Text(textoTipo50) },
                        onClick = { tipoAdd = textoTipo50; tipo = "Shaker" })
                    val textoTipo51 = if (idioma.value) {
                        "Side"
                    } else {
                        "Acompañamiento"
                    }
                    DropdownMenuItem(
                        text = { Text(textoTipo51) },
                        onClick = { tipoAdd = textoTipo51; tipo = "Side" })
                    val textoTipo52 = if (idioma.value) {
                        "Slush"
                    } else {
                        "Batido"
                    }
                    DropdownMenuItem(
                        text = { Text(textoTipo52) },
                        onClick = { tipoAdd = textoTipo52; tipo = "Slush" })
                    val textoTipo53 = if (idioma.value) {
                        "Soda"
                    } else {
                        "Refresco"
                    }
                    DropdownMenuItem(
                        text = { Text(textoTipo53) },
                        onClick = { tipoAdd = textoTipo53; tipo = "Soda" })
                    val textoTipo54 = if (idioma.value) {
                        "Soy Paper"
                    } else {
                        "Papel de soja"
                    }
                    DropdownMenuItem(
                        text = { Text(textoTipo54) },
                        onClick = { tipoAdd = textoTipo54; tipo = "Soy Paper" })
                    val textoTipo55 = if (idioma.value) {
                        "Sushi Filling"
                    } else {
                        "Relleno de sushi"
                    }
                    DropdownMenuItem(
                        text = { Text(textoTipo55) },
                        onClick = { tipoAdd = textoTipo55; tipo = "Sushi Filling" })
                    val textoTipo56 = if (idioma.value) {
                        "Syrup"
                    } else {
                        "Sirope"
                    }
                    DropdownMenuItem(
                        text = { Text(textoTipo56) },
                        onClick = { tipoAdd = textoTipo56; tipo = "Syrup" })
                    val textoTipo57 = if (idioma.value) {
                        "Taco Meat"
                    } else {
                        "Carne para tacos"
                    }
                    DropdownMenuItem(
                        text = { Text(textoTipo57) },
                        onClick = { tipoAdd = textoTipo57; tipo = "Taco Meat" })
                    val textoTipo58 = if (idioma.value) {
                        "Taco Shell"
                    } else {
                        "Pan de tacos"
                    }
                    DropdownMenuItem(
                        text = { Text(textoTipo58) },
                        onClick = { tipoAdd = textoTipo58; tipo = "Taco Shell" })
                    val textoTipo59 = if (idioma.value) {
                        "Tea Bubbles"
                    } else {
                        "Burbujas de té"
                    }
                    DropdownMenuItem(
                        text = { Text(textoTipo59) },
                        onClick = { tipoAdd = textoTipo59; tipo = "Tea Bubbles" })
                    val textoTipo60 = if (idioma.value) {
                        "Top Crust"
                    } else {
                        "Tapa de pastel"
                    }
                    DropdownMenuItem(
                        text = { Text(textoTipo60) },
                        onClick = { tipoAdd = textoTipo60; tipo = "Top Crust" })
                    val textoTipo61 = "Topper"
                    DropdownMenuItem(
                        text = { Text(textoTipo61) },
                        onClick = { tipoAdd = textoTipo61; tipo = "Topper" })
                    val textoTipo62 = "Topping"
                    DropdownMenuItem(
                        text = { Text(textoTipo62) },
                        onClick = { tipoAdd = textoTipo62; tipo = "Topping" })
                    val textoTipo63 = if (idioma.value) {
                        "Paleta Shape"
                    } else {
                        "Forma de paleta"
                    }
                    DropdownMenuItem(
                        text = { Text(textoTipo63) },
                        onClick = { tipoAdd = textoTipo63; tipo = "Paleta Shape"})
                    val textoTipo64 = if (idioma.value) {
                        "Chunky Filling"
                    } else {
                        "Relleno en trozos"
                    }
                    DropdownMenuItem(
                        text = { Text(textoTipo64) },
                        onClick = { tipoAdd = textoTipo64; tipo = "Chunky Filling" })
                    val textoTipo65 = if (idioma.value) {
                        "Cream Filling"
                    } else {
                        "Relleno cremoso"
                    }
                    DropdownMenuItem(
                        text = { Text(textoTipo65) },
                        onClick = { tipoAdd = textoTipo65; tipo = "Cream Filling" })
                    val textoTipo66 = if (idioma.value) {
                        "Paleta Dip"
                    } else {
                        "Cobertura para paletas"
                    }
                    DropdownMenuItem(
                        text = { Text(textoTipo66) },
                        onClick = { tipoAdd = textoTipo66; tipo = "Paleta Dip" })
                }
            }
        }
        val textoSabor = if (idioma.value) {
            "Flavor: "
        } else {
            "Sabor: "
        }
        Row {
            Text(textoSabor)
            ExposedDropdownMenuBox(expanded = activado2,
                onExpandedChange = {activado2 = !activado2} ) {
                TextField(modifier = Modifier.menuAnchor(),
                    readOnly = true,
                    value = saborAdd,
                    onValueChange = {}
                )
                val textoSabor1 = if (idioma.value) {
                    "savory"
                } else {
                    "salado"
                }
                val textoSabor2 = if (idioma.value) {
                    "sweet"
                } else {
                    "dulce"
                }
                DropdownMenu(expanded = activado2, onDismissRequest = { activado2 = false }) {
                    DropdownMenuItem(
                        text = { Text(textoSabor1) },
                        onClick = { saborAdd = textoSabor1; sabor = "savory" })
                    DropdownMenuItem(
                        text = { Text(textoSabor2) },
                        onClick = { saborAdd = textoSabor2; sabor = "sweet" })
                }
            }
        }
        val textoHE = if (idioma.value) {
            "Holiday-exclusive: "
        } else {
            "De celebración: "
        }
        Row {
            Text(textoHE)
            var seleccionado by remember { mutableStateOf(deCelebracionAdd) }
            Checkbox(checked = seleccionado, onCheckedChange = {
                seleccionado = !seleccionado; deCelebracionAdd = seleccionado; deCelebracion = deCelebracionAdd
            })
        }
        val textoCelebracion = if (idioma.value) {
            "Holiday: "
        } else {
            "Celebración: "
        }
        Row {
            Text(textoCelebracion)
            ExposedDropdownMenuBox(expanded = activado3,
                onExpandedChange = {activado3 = !activado3} ) {
                TextField(modifier = Modifier.menuAnchor(),
                    readOnly = true,
                    value = celebracionAdd,
                    onValueChange = {}
                )
                DropdownMenu(expanded = activado3, onDismissRequest = { activado3 = false }) {
                    val textoCelebracion1 = if (idioma.value) {
                        "Standard"
                    } else {
                        "Estándar"
                    }
                    DropdownMenuItem(
                        text = { Text(textoCelebracion1) },
                        onClick = { celebracionAdd = textoCelebracion1; celebracion = "Standard" })
                    val textoCelebracion2 = if (idioma.value) {
                        "Baseball Season"
                    } else {
                        "Temporada de baseball"
                    }
                    DropdownMenuItem(
                        text = { Text(textoCelebracion2) },
                        onClick = { celebracionAdd = textoCelebracion2; celebracion = "Baseball Season" })
                    val textoCelebracion3 = if (idioma.value) {
                        "Bavaria Fest"
                    } else {
                        "Festival de Bavaria"
                    }
                    DropdownMenuItem(
                        text = { Text(textoCelebracion3) },
                        onClick = { celebracionAdd = textoCelebracion3; celebracion = "Bavaria Fest" })
                    val textoCelebracion4 = if (idioma.value) {
                        "Big Top Carnival"
                    } else {
                        "Gran Feria"
                    }
                    DropdownMenuItem(
                        text = { Text(textoCelebracion4) },
                        onClick = { celebracionAdd = textoCelebracion4; celebracion = "Big Top Carnival" })
                    val textoCelebracion5 = if (idioma.value) {
                        "Cherry Blossom Festival"
                    } else {
                        "Festival de la flor de cerezo"
                    }
                    DropdownMenuItem(
                        text = { Text(textoCelebracion5) },
                        onClick = { celebracionAdd = textoCelebracion5; celebracion = "Cherry Blossom Festival" })
                    val textoCelebracion6 = if (idioma.value) {
                        "Chilifest"
                    } else {
                        "Festival del chili"
                    }
                    DropdownMenuItem(
                        text = { Text(textoCelebracion6) },
                        onClick = { celebracionAdd = textoCelebracion6; celebracion = "Chilifest" })
                    val textoCelebracion7 = if (idioma.value) {
                        "Christmas"
                    } else {
                        "Navidad"
                    }
                    DropdownMenuItem(
                        text = { Text(textoCelebracion7) },
                        onClick = { celebracionAdd = textoCelebracion7; celebracion = "Christmas" })
                    DropdownMenuItem(
                        text = { Text("Cinco de Mayo") },
                        onClick = { celebracionAdd = "Cinco de Mayo"; celebracion = celebracionAdd })
                    DropdownMenuItem(
                        text = { Text("Comet Con") },
                        onClick = { celebracionAdd = "Comet Con"; celebracion = celebracionAdd })
                    val textoCelebracion8 = if (idioma.value) {
                        "Day of the Dead"
                    } else {
                        "Día de Muertos"
                    }
                    DropdownMenuItem(
                        text = { Text(textoCelebracion8) },
                        onClick = { celebracionAdd = textoCelebracion8; celebracion = "Day of the Dead" })
                    val textoCelebracion9 = if (idioma.value) {
                        "Easter"
                    } else {
                        "Pascua"
                    }
                    DropdownMenuItem(
                        text = { Text(textoCelebracion9) },
                        onClick = { celebracionAdd = textoCelebracion9; celebracion = "Easter" })
                    val textoCelebracion10 = if (idioma.value) {
                        "Film Fest"
                    } else {
                        "Festival de las películas"
                    }
                    DropdownMenuItem(
                        text = { Text(textoCelebracion10) },
                        onClick = { celebracionAdd = textoCelebracion10; celebracion = "Film Fest" })
                    DropdownMenuItem(
                        text = { Text("Gondola") },
                        onClick = { celebracionAdd = "Gondola"; celebracion = celebracionAdd })
                    DropdownMenuItem(
                        text = { Text("Groovstock") },
                        onClick = { celebracionAdd = "Groovstock"; celebracion = celebracionAdd })
                    DropdownMenuItem(
                        text = { Text("Halloween") },
                        onClick = { celebracionAdd = "Halloween"; celebracion = celebracionAdd })
                    DropdownMenuItem(
                        text = { Text("Holi") },
                        onClick = { celebracionAdd = "Holi"; celebracion = celebracionAdd })
                    val textoCelebracion26 = if (idioma.value) {
                        "Lucky Lucky Matsuri"
                    } else {
                        "Matsuri de la Suerte"
                    }
                    DropdownMenuItem(
                        text = { Text(textoCelebracion26) },
                        onClick = { celebracionAdd = textoCelebracion26; celebracion = "Lucky Lucky Matsuri" })
                    val textoCelebracion11 = if (idioma.value) {
                        "Lunar New Year"
                    } else {
                        "Año Nuevo Lunar"
                    }
                    DropdownMenuItem(
                        text = { Text(textoCelebracion11) },
                        onClick = { celebracionAdd = textoCelebracion11; celebracion = "Lunar New Year" })
                    val textoCelebracion12 = if (idioma.value) {
                        "Maple Mornings"
                    } else {
                        "Mañanas de arce"
                    }
                    DropdownMenuItem(
                        text = { Text(textoCelebracion12) },
                        onClick = { celebracionAdd = textoCelebracion12; celebracion = "Maple Mornings" })
                    DropdownMenuItem(
                        text = { Text("Mardi Gras") },
                        onClick = { celebracionAdd = "Mardi Gras"; celebracion = celebracionAdd })
                    val textoCelebracion13 = if (idioma.value) {
                        "Neptune's Feast"
                    } else {
                        "Festín de Neptuno"
                    }
                    DropdownMenuItem(
                        text = { Text(textoCelebracion13) },
                        onClick = { celebracionAdd = textoCelebracion13; celebracion = "Neptune's Feast" })
                    val textoCelebracion14 = if (idioma.value) {
                        "New Year"
                    } else {
                        "Año Nuevo"
                    }
                    DropdownMenuItem(
                        text = { Text(textoCelebracion14) },
                        onClick = { celebracionAdd = textoCelebracion14; celebracion = "New Year" })
                    val textoCelebracion15 = if (idioma.value) {
                        "Onionfest"
                    } else {
                        "Festival de las cebollas"
                    }
                    DropdownMenuItem(
                        text = { Text(textoCelebracion15) },
                        onClick = { celebracionAdd = textoCelebracion15; celebracion = "Onionfest" })
                    val textoCelebracion16 = if (idioma.value) {
                        "Pirate Bash"
                    } else {
                        "Fiesta de los piratas"
                    }
                    DropdownMenuItem(
                        text = { Text(textoCelebracion16) },
                        onClick = { celebracionAdd = textoCelebracion16; celebracion = "Pirate Bash" })
                    val textoCelebracion17 = if (idioma.value) {
                        "Portallini Feast"
                    } else {
                        "Festín de Portallini"
                    }
                    DropdownMenuItem(
                        text = { Text(textoCelebracion17) },
                        onClick = { celebracionAdd = textoCelebracion17; celebracion = "Portallini Feast" })
                    val textoCelebracion18 = if (idioma.value) {
                        "Romano Wedding"
                    } else {
                        "Boda Romano"
                    }
                    DropdownMenuItem(
                        text = { Text(textoCelebracion18) },
                        onClick = { celebracionAdd = textoCelebracion18; celebracion = "Romano Wedding" })
                    val textoCelebracion19 = if (idioma.value) {
                        "Sky Ninja Returns"
                    } else {
                        "Retorno del Sky Ninja"
                    }
                    DropdownMenuItem(
                        text = { Text(textoCelebracion19) },
                        onClick = { celebracionAdd = textoCelebracion19; celebracion = "Sky Ninja Returns" })
                    val textoCelebracion20 = if (idioma.value) {
                        "St Paddy"
                    } else {
                        "San Patricio"
                    }
                    DropdownMenuItem(
                        text = { Text(textoCelebracion20) },
                        onClick = { celebracionAdd = textoCelebracion20; celebracion = "St Paddy" })
                    val textoCelebracion21 = if (idioma.value) {
                        "Starlight"
                    } else {
                        "Festival de las estrellas"
                    }
                    DropdownMenuItem(
                        text = { Text(textoCelebracion21) },
                        onClick = { celebracionAdd = textoCelebracion21; celebracion = "Starlight" })
                    val textoCelebracion22 = if (idioma.value) {
                        "Summer Luau"
                    } else {
                        "Luau de verano"
                    }
                    DropdownMenuItem(
                        text = { Text(textoCelebracion22) },
                        onClick = { celebracionAdd = textoCelebracion22; celebracion = "Summer Luau" })
                    val textoCelebracion23 = if (idioma.value) {
                        "Thanksgiving"
                    } else {
                        "Acción de gracias"
                    }
                    DropdownMenuItem(
                        text = { Text(textoCelebracion23) },
                        onClick = { celebracionAdd = textoCelebracion23; celebracion = "Thanksgiving" })
                    val textoCelebracion24 = if (idioma.value) {
                        "Valentines"
                    } else {
                        "San Valentín"
                    }
                    DropdownMenuItem(
                        text = { Text(textoCelebracion24) },
                        onClick = { celebracionAdd = textoCelebracion24; celebracion = "Valentines" })
                    val textoCelebracion25 = if (idioma.value) {
                        "Volcano Gala"
                    } else {
                        "Gala del volcán"
                    }
                    DropdownMenuItem(
                        text = { Text(textoCelebracion25) },
                        onClick = { celebracionAdd = textoCelebracion25; celebracion = "Volcano Gala" })
                }
            }
        }
        val textoComida = if (idioma.value) {
            "Food: "
        } else {
            "Comida: "
        }
        Row {
            Text(textoComida)
            ExposedDropdownMenuBox(expanded = activado4,
                onExpandedChange = {activado4 = !activado4} ) {
                TextField(modifier = Modifier.menuAnchor(),
                    readOnly = true,
                    value = comidaAdd,
                    onValueChange = {}
                )
                DropdownMenu(expanded = activado4, onDismissRequest = { activado4 = false }) {
                    DropdownMenuItem(
                        text = { Text("Pizza") },
                        onClick = { comidaAdd = "Pizza"; comida2 = comidaAdd })
                    val textoComida1 = if (idioma.value) {
                        "Burger"
                    } else {
                        "Hamburguesa"
                    }
                    DropdownMenuItem(
                        text = { Text(textoComida1) },
                        onClick = { comidaAdd = textoComida1; comida2 = "Burger" })
                    DropdownMenuItem(
                        text = { Text("Taco") },
                        onClick = { comidaAdd = "Taco"; comida2 = comidaAdd })
                    val textoComida2 = if (idioma.value) {
                        "Sundae"
                    } else {
                        "Helado"
                    }
                    DropdownMenuItem(
                        text = { Text(textoComida2) },
                        onClick = { comidaAdd = textoComida2; comida2 = "Sundae" })
                    val textoComida3 = if (idioma.value) {
                        "Breakfast"
                    } else {
                        "Desayuno"
                    }
                    DropdownMenuItem(
                        text = { Text(textoComida3) },
                        onClick = { comidaAdd = textoComida3; comida2 = "Breakfast" })
                    val textoComida4 = if (idioma.value) {
                        "Chicken Wings"
                    } else {
                        "Pollo frito"
                    }
                    DropdownMenuItem(
                        text = { Text(textoComida4) },
                        onClick = { comidaAdd = textoComida4; comida2 = "Chicken Wings" })
                    val textoComida5 = if (idioma.value) {
                        "Hot Dog"
                    } else {
                        "Perrito caliente"
                    }
                    DropdownMenuItem(
                        text = { Text(textoComida5) },
                        onClick = { comidaAdd = textoComida5; comida2 = "Hot Dog" })
                    DropdownMenuItem(
                        text = { Text("Cupcakes") },
                        onClick = { comidaAdd = "Cupcakes"; comida2 = comidaAdd })
                    DropdownMenuItem(
                        text = { Text("Pasta") },
                        onClick = { comidaAdd = "Pasta"; comida2 = comidaAdd })
                    DropdownMenuItem(
                        text = { Text("Donuts") },
                        onClick = { comidaAdd = "Donuts"; comida2 = comidaAdd })
                    DropdownMenuItem(
                        text = { Text("Sandwich") },
                        onClick = { comidaAdd = "Sandwich"; comida2 = comidaAdd })
                    val textoComida6 = if (idioma.value) {
                        "Pie"
                    } else {
                        "Pastel"
                    }
                    DropdownMenuItem(
                        text = { Text(textoComida6) },
                        onClick = { comidaAdd = textoComida6; comida2 = "Pie" })
                    DropdownMenuItem(
                        text = { Text("Sushi") },
                        onClick = { comidaAdd = "Sushi"; comida2 = comidaAdd })
                    val textoComida7 = if (idioma.value) {
                        "Cookie Sundae"
                    } else {
                        "Helado con galletas"
                    }
                    DropdownMenuItem(
                        text = { Text(textoComida7) },
                        onClick = { comidaAdd = textoComida7; comida2 = "Cookie Sundae" })
                    DropdownMenuItem(
                        text = { Text("Mocha") },
                        onClick = { comidaAdd = "Mocha"; comida2 = comidaAdd })
                    val textoComida8 = if (idioma.value) {
                        "Chicken Sandwich"
                    } else {
                        "Hamburguesa de pollo"
                    }
                    DropdownMenuItem(
                        text = { Text(textoComida8) },
                        onClick = { comidaAdd = textoComida8; comida2 = "Chicken Sandwich" })
                    DropdownMenuItem(
                        text = { Text("Paleta") },
                        onClick = { comidaAdd = "Paleta"; comida2 = comidaAdd })
                }
            }
        }
        val textoJuego = if (idioma.value) {
            "Game: "
        } else {
            "Juego: "
        }
        Row {
            Text(textoJuego)
            ExposedDropdownMenuBox(expanded = activado5,
                onExpandedChange = {activado5 = !activado5} ) {
                TextField(modifier = Modifier.menuAnchor(),
                    readOnly = true,
                    value = saborAdd,
                    onValueChange = {}
                )
                DropdownMenu(expanded = activado5, onDismissRequest = { activado5 = false }) {
                    DropdownMenuItem(
                        text = { Text("Pizzeria") },
                        onClick = { juegoAdd = "Pizzeria"; juego3 = juegoAdd })
                    DropdownMenuItem(
                        text = { Text("Pizzeria To Go") },
                        onClick = { juegoAdd = "Pizzeria To Go"; juego3 = juegoAdd })
                    DropdownMenuItem(
                        text = { Text("Pizzeria HD") },
                        onClick = { juegoAdd = "Pizzeria HD"; juego3 = juegoAdd })
                    DropdownMenuItem(
                        text = { Text("Pizzeria Deluxe") },
                        onClick = { juegoAdd = "Pizzeria Deluxe"; juego3 = juegoAdd })
                    DropdownMenuItem(
                        text = { Text("Burgeria") },
                        onClick = { juegoAdd = "Burgeria"; juego3 = juegoAdd })
                    DropdownMenuItem(
                        text = { Text("Burgeria HD") },
                        onClick = { juegoAdd = "Burgeria HD"; juego3 = juegoAdd })
                    DropdownMenuItem(
                        text = { Text("Burgeria To Go") },
                        onClick = { juegoAdd = "Burgeria To Go"; juego3 = juegoAdd })
                    DropdownMenuItem(
                        text = { Text("Taco Mia") },
                        onClick = { juegoAdd = "Taco Mia"; juego3 = juegoAdd })
                    DropdownMenuItem(
                        text = { Text("Taco Mia HD") },
                        onClick = { juegoAdd = "Taco Mia HD"; juego3 = juegoAdd })
                    DropdownMenuItem(
                        text = { Text("Taco Mia To Go") },
                        onClick = { juegoAdd = "Taco Mia To Go"; juego3 = juegoAdd })
                    DropdownMenuItem(
                        text = { Text("Freezeria") },
                        onClick = { juegoAdd = "Freezeria"; juego3 = juegoAdd })
                    DropdownMenuItem(
                        text = { Text("Freezeria HD") },
                        onClick = { juegoAdd = "Freezeria HD"; juego3 = juegoAdd })
                    DropdownMenuItem(
                        text = { Text("Freezeria To Go") },
                        onClick = { juegoAdd = "Freezeria To Go"; juego3 = juegoAdd })
                    DropdownMenuItem(
                        text = { Text("Freezeria Deluxe") },
                        onClick = { juegoAdd = "Freezeria Deluxe"; juego3 = juegoAdd })
                    DropdownMenuItem(
                        text = { Text("Pancakeria") },
                        onClick = { juegoAdd = "Pancakeria"; juego3 = juegoAdd })
                    DropdownMenuItem(
                        text = { Text("Pancakeria HD") },
                        onClick = { juegoAdd = "Pancakeria HD"; juego3 = juegoAdd })
                    DropdownMenuItem(
                        text = { Text("Pancakeria To Go") },
                        onClick = { juegoAdd = "Pancakeria To Go"; juego3 = juegoAdd })
                    DropdownMenuItem(
                        text = { Text("Wingeria") },
                        onClick = { juegoAdd = "Wingeria"; juego3 = juegoAdd })
                    DropdownMenuItem(
                        text = { Text("Wingeria HD") },
                        onClick = { juegoAdd = "Wingeria HD"; juego3 = juegoAdd })
                    DropdownMenuItem(
                        text = { Text("Wingeria To Go") },
                        onClick = { juegoAdd = "Wingeria To Go"; juego3 = juegoAdd })
                    DropdownMenuItem(
                        text = { Text("Hot Doggeria") },
                        onClick = { juegoAdd = "Hot Doggeria"; juego3 = juegoAdd })
                    DropdownMenuItem(
                        text = { Text("Hot Doggeria HD") },
                        onClick = { juegoAdd = "Hot Doggeria HD"; juego3 = juegoAdd })
                    DropdownMenuItem(
                        text = { Text("Hot Doggeria To Go") },
                        onClick = { juegoAdd = "Hot Doggeria To Go"; juego3 = juegoAdd })
                    DropdownMenuItem(
                        text = { Text("Cupcakeria") },
                        onClick = { juegoAdd = "Cupcakeria"; juego3 = juegoAdd })
                    DropdownMenuItem(
                        text = { Text("Cupcakeria HD") },
                        onClick = { juegoAdd = "Cupcakeria HD"; juego3 = juegoAdd })
                    DropdownMenuItem(
                        text = { Text("Cupcakeria To Go") },
                        onClick = { juegoAdd = "Cupcakeria To Go"; juego3 = juegoAdd })
                    DropdownMenuItem(
                        text = { Text("Pastaria") },
                        onClick = { juegoAdd = "Pastaria"; juego3 = juegoAdd })
                    DropdownMenuItem(
                        text = { Text("Pastaria To Go") },
                        onClick = { juegoAdd = "Pastaria To Go"; juego3 = juegoAdd })
                    DropdownMenuItem(
                        text = { Text("Donuteria") },
                        onClick = { juegoAdd = "Donuteria"; juego3 = juegoAdd })
                    DropdownMenuItem(
                        text = { Text("Donuteria To Go") },
                        onClick = { juegoAdd = "Donuteria To Go"; juego3 = juegoAdd })
                    DropdownMenuItem(
                        text = { Text("Cheeseria") },
                        onClick = { juegoAdd = "Cheeseria"; juego3 = juegoAdd })
                    DropdownMenuItem(
                        text = { Text("Cheeseria To Go") },
                        onClick = { juegoAdd = "Cheeseria To Go"; juego3 = juegoAdd })
                    DropdownMenuItem(
                        text = { Text("Bakeria") },
                        onClick = { juegoAdd = "Bakeria"; juego3 = juegoAdd })
                    DropdownMenuItem(
                        text = { Text("Bakeria To Go") },
                        onClick = { juegoAdd = "Bakeria To Go"; juego3 = juegoAdd })
                    DropdownMenuItem(
                        text = { Text("Sushiria") },
                        onClick = { juegoAdd = "Sushiria"; juego3 = juegoAdd })
                    DropdownMenuItem(
                        text = { Text("Sushiria To Go") },
                        onClick = { juegoAdd = "Sushiria To Go"; juego3 = juegoAdd })
                    DropdownMenuItem(
                        text = { Text("Scooperia") },
                        onClick = { juegoAdd = "Scooperia"; juego3 = juegoAdd })
                    DropdownMenuItem(
                        text = { Text("Scooperia To Go") },
                        onClick = { juegoAdd = "Scooperia To Go"; juego3 = juegoAdd })
                    DropdownMenuItem(
                        text = { Text("Mocharia To Go") },
                        onClick = { juegoAdd = "Mocharia To Go"; juego3 = juegoAdd })
                    DropdownMenuItem(
                        text = { Text("Cluckeria To Go") },
                        onClick = { juegoAdd = "Cluckeria To Go"; juego3 = juegoAdd })
                    DropdownMenuItem(
                        text = { Text("Paleteria To Go") },
                        onClick = { juegoAdd = "Paleteria To Go"; juego3 = juegoAdd })
                }
            }
        }
        val textoOP = if (idioma.value) {
            "Order Part: "
        } else {
            "Parte del pedido: "
        }
        Row {
            Text(textoOP)
            ExposedDropdownMenuBox(expanded = activado6,
                onExpandedChange = { activado6 = !activado6 }) {
                TextField(modifier = Modifier.menuAnchor(),
                    readOnly = true,
                    value = saborAdd,
                    onValueChange = {}
                )
                DropdownMenu(expanded = activado6, onDismissRequest = { activado6 = false }) {
                    val textoOP1 = if (idioma.value) {
                        "Aditive"
                    } else {
                        "Aditivo"
                    }
                    DropdownMenuItem(
                        text = { Text(textoOP1) },
                        onClick = { parteAdd = textoOP1; parte2 = "Aditive" })
                    val textoOP2 = if (idioma.value) {
                        "Bread"
                    } else {
                        "Pan"
                    }
                    DropdownMenuItem(
                        text = { Text(textoOP2) },
                        onClick = { parteAdd = textoOP2; parte2 = "Bread" })
                    val textoOP3 = if (idioma.value) {
                        "Breading"
                    } else {
                        "Empanado"
                    }
                    DropdownMenuItem(
                        text = { Text(textoOP3) },
                        onClick = { parteAdd = textoOP3; parte2 = "Breading" })
                    val textoOP4 = if (idioma.value) {
                        "Breakfast Food"
                    } else {
                        "Comida de desayuno"
                    }
                    DropdownMenuItem(
                        text = { Text(textoOP4) },
                        onClick = { parteAdd = textoOP4; parte2 = "Breakfast Food" })
                    val textoOP5 = if (idioma.value) {
                        "Breakfast Mixable"
                    } else {
                        "Trozos para desayuno"
                    }
                    DropdownMenuItem(
                        text = { Text(textoOP5) },
                        onClick = { parteAdd = textoOP5; parte2 = "Breakfast Topping" })
                    val textoOP6 = if (idioma.value) {
                        "Breakfast Topping"
                    } else {
                        "Topping para desayuno"
                    }
                    DropdownMenuItem(
                        text = { Text(textoOP6) },
                        onClick = { parteAdd = textoOP6; parte2 = "Breakfast Topping" })
                    val textoOP7 = if (idioma.value) {
                        "Bubble Tea"
                    } else {
                        "Té de burbujas"
                    }
                    DropdownMenuItem(
                        text = { Text(textoOP7) },
                        onClick = { parteAdd = textoOP7; parte2 = "Bubble Tea" })
                    val textoOP8 = if (idioma.value) {
                        "Burger Meat"
                    } else {
                        "Carne de hamburguesa"
                    }
                    DropdownMenuItem(
                        text = { Text(textoOP8) },
                        onClick = { parteAdd = textoOP8; parte2 = "Burger Meat" })
                    val textoOP9 = if (idioma.value) {
                        "Burger Topping"
                    } else {
                        "Topping para hamburguesa"
                    }
                    DropdownMenuItem(
                        text = { Text(textoOP9) },
                        onClick = { parteAdd = textoOP9; parte2 = "Burger Topping" })
                    val textoOP10 = if (idioma.value) {
                        "Cake"
                    } else {
                        "Masa de cupcakes"
                    }
                    DropdownMenuItem(
                        text = { Text(textoOP10) },
                        onClick = { parteAdd = textoOP10; parte2 = "Cake" })
                    val textoOP11 = if (idioma.value) {
                        "Cannoli Shell"
                    } else {
                        "Masa de cannoli"
                    }
                    DropdownMenuItem(
                        text = { Text(textoOP11) },
                        onClick = { parteAdd = textoOP11; parte2 = "Cannoli Shell" })
                    val textoOP12 = if (idioma.value) {
                        "Cannoli Topping"
                    } else {
                        "Topping para cannoli"
                    }
                    DropdownMenuItem(
                        text = { Text(textoOP12) },
                        onClick = { parteAdd = textoOP12; parte2 = "Cannoli Topping" })
                    val textoOP13 = if (idioma.value) {
                        "Chicken Sandwich Bun"
                    } else {
                        "Pan de hamburguesa de pollo"
                    }
                    DropdownMenuItem(
                        text = { Text(textoOP13) },
                        onClick = { parteAdd = textoOP13; parte2 = "Chicken Sandwich Bun" })
                    val textoOP14 = if (idioma.value) {
                        "Chicken Sandwich Meat"
                    } else {
                        "Carne de hamburguesa de pollo"
                    }
                    DropdownMenuItem(
                        text = { Text(textoOP14) },
                        onClick = { parteAdd = textoOP14; parte2 = "Chicken Sandwich Meat" })
                    val textoOP15 = if (idioma.value) {
                        "Chicken Sandwich Topping"
                    } else {
                        "Topping para hamburguesa de pollo"
                    }
                    DropdownMenuItem(
                        text = { Text(textoOP15) },
                        onClick = { parteAdd = textoOP15; parte2 = "Chicken Sandwich Topping" })
                    val textoOP16 = if (idioma.value) {
                        "Chicken Sauce"
                    } else {
                        "Salsa para pollo"
                    }
                    DropdownMenuItem(
                        text = { Text(textoOP16) },
                        onClick = { parteAdd = textoOP16; parte2 = "Chicken Sauce" })
                    val textoOP17 = if (idioma.value) {
                        "Chicken Wings"
                    } else {
                        "Pollo frito"
                    }
                    DropdownMenuItem(
                        text = { Text(textoOP17) },
                        onClick = { parteAdd = textoOP17; parte2 = "Chicken Wings" })
                    val textoOP18 = if (idioma.value) {
                        "Coffee"
                    } else {
                        "Café"
                    }
                    DropdownMenuItem(
                        text = { Text(textoOP18) },
                        onClick = { parteAdd = textoOP18; parte2 = "Coffee" })
                    val textoOP19 = if (idioma.value) {
                        "Cookie Dough"
                    } else {
                        "Masa de galletas"
                    }
                    DropdownMenuItem(
                        text = { Text(textoOP19) },
                        onClick = { parteAdd = textoOP19; parte2 = "Cookie Dough" })
                    val textoOP20 = if (idioma.value) {
                        "Cookie Mixable"
                    } else {
                        "Trozos para galletas"
                    }
                    DropdownMenuItem(
                        text = { Text(textoOP20) },
                        onClick = { parteAdd = textoOP20; parte2 = "Cookie Mixable" })
                    val textoOP21 = if (idioma.value) {
                        "Cookie Sundae Long Topper"
                    } else {
                        "Topper largo para helado con galletas"
                    }
                    DropdownMenuItem(
                        text = { Text(textoOP21) },
                        onClick = { parteAdd = textoOP21; parte2 = "Cookie Sundae Long Topper" })
                    val textoOP22 = if (idioma.value) {
                        "Cookie Sundae Topper"
                    } else {
                        "Topper para helado con galletas"
                    }
                    DropdownMenuItem(
                        text = { Text(textoOP22) },
                        onClick = { parteAdd = textoOP22; parte2 = "Cookie Sundae Topper" })
                    val textoOP23 = if (idioma.value) {
                        "Cookie Sundae Topping"
                    } else {
                        "Topping para helado con galletas"
                    }
                    DropdownMenuItem(
                        text = { Text(textoOP23) },
                        onClick = { parteAdd = textoOP23; parte2 = "Cookie Sundae Topping" })
                    val textoOP24 = if (idioma.value) {
                        "Cream"
                    } else {
                        "Crema"
                    }
                    DropdownMenuItem(
                        text = { Text(textoOP24) },
                        onClick = { parteAdd = textoOP24; parte2 = "Cream" })
                    val textoOP25 = if (idioma.value) {
                        "Cream Dollop"
                    } else {
                        "Bola de crema"
                    }
                    DropdownMenuItem(
                        text = { Text(textoOP25) },
                        onClick = { parteAdd = textoOP25; parte2 = "Cream Dollop" })
                    val textoOP26 = if (idioma.value) {
                        "Cupcake Topper"
                    } else {
                        "Topper para cupcakes"
                    }
                    DropdownMenuItem(
                        text = { Text(textoOP26) },
                        onClick = { parteAdd = textoOP26; parte2 = "Cupcake Topper" })
                    val textoOP27 = if (idioma.value) {
                        "Cupcake Topping"
                    } else {
                        "Topping para cupcakes"
                    }
                    DropdownMenuItem(
                        text = { Text(textoOP27) },
                        onClick = { parteAdd = textoOP27; parte2 = "Cupcake Topping" })
                    val textoOP28 = if (idioma.value) {
                        "Dip"
                    } else {
                        "Salsa para mojar"
                    }
                    DropdownMenuItem(
                        text = { Text(textoOP28) },
                        onClick = { parteAdd = textoOP28; parte2 = "Dip" })
                    val textoOP29 = if (idioma.value) {
                        "Donut Cutter"
                    } else {
                        "Forma de donut"
                    }
                    DropdownMenuItem(
                        text = { Text(textoOP29) },
                        onClick = { parteAdd = textoOP29; parte2 = "Donut Cutter" })
                    val textoOP30 = if (idioma.value) {
                        "Donut Dough"
                    } else {
                        "Masa de donut"
                    }
                    DropdownMenuItem(
                        text = { Text(textoOP30) },
                        onClick = { parteAdd = textoOP30; parte2 = "Donut Dough" })
                    val textoOP31 = if (idioma.value) {
                        "Donut Filling"
                    } else {
                        "Relleno de donut"
                    }
                    DropdownMenuItem(
                        text = { Text(textoOP31) },
                        onClick = { parteAdd = textoOP31; parte2 = "Donut Filling" })
                    val textoOP32 = if (idioma.value) {
                        "Donut Topping"
                    } else {
                        "Topping para donuts"
                    }
                    DropdownMenuItem(
                        text = { Text(textoOP32) },
                        onClick = { parteAdd = textoOP32; parte2 = "Donut Topping" })
                    val textoOP33 = if (idioma.value) {
                        "Drink"
                    } else {
                        "Bebida"
                    }
                    DropdownMenuItem(
                        text = { Text(textoOP33) },
                        onClick = { parteAdd = textoOP33; parte2 = "Drink" })
                    val textoOP34 = if (idioma.value) {
                        "Fries"
                    } else {
                        "Papas fritas"
                    }
                    DropdownMenuItem(
                        text = { Text(textoOP34) },
                        onClick = { parteAdd = textoOP34; parte2 = "Fries" })
                    val textoOP35 = "Frosting"
                    DropdownMenuItem(
                        text = { Text(textoOP35) },
                        onClick = { parteAdd = textoOP35; parte2 = "Frosting" })
                    val textoOP36 = if (idioma.value) {
                        "Fry Topping"
                    } else {
                        "Topping para papas fritas"
                    }
                    DropdownMenuItem(
                        text = { Text(textoOP36) },
                        onClick = { parteAdd = textoOP36; parte2 = "Fry Topping" })
                    val textoOP37 = if (idioma.value) {
                        "Hot Dog Bun"
                    } else {
                        "Pan de perrito caliente"
                    }
                    DropdownMenuItem(
                        text = { Text(textoOP37) },
                        onClick = { parteAdd = textoOP37; parte2 = "Hot Dog Bun" })
                    val textoOP38 = if (idioma.value) {
                        "Hot Dog Long Topper"
                    } else {
                        "Topper largo para perrito caliente"
                    }
                    DropdownMenuItem(
                        text = { Text(textoOP38) },
                        onClick = { parteAdd = textoOP38; parte2 = "Hot Dog Long Topper" })
                    val textoOP39 = if (idioma.value) {
                        "Hot Dog Topper"
                    } else {
                        "Topper para perrito caliente"
                    }
                    DropdownMenuItem(
                        text = { Text(textoOP39) },
                        onClick = { parteAdd = textoOP39; parte2 = "Hot Dog Topper" })
                    val textoOP40 = if (idioma.value) {
                        "Hot Dog Topping"
                    } else {
                        "Topping para perrito caliente"
                    }
                    DropdownMenuItem(
                        text = { Text(textoOP40) },
                        onClick = { parteAdd = textoOP40; parte2 = "Hot Dog Topping" })
                    val textoOP41 = if (idioma.value) {
                        "Ice"
                    } else {
                        "Hielo"
                    }
                    DropdownMenuItem(
                        text = { Text(textoOP41) },
                        onClick = { parteAdd = textoOP41; parte2 = "Ice" })
                    val textoOP42 = if (idioma.value) {
                        "Ice Cream"
                    } else {
                        "Helado"
                    }
                    DropdownMenuItem(
                        text = { Text(textoOP42) },
                        onClick = { parteAdd = textoOP42; parte2 = "Ice Cream" })
                    val textoOP43 = if (idioma.value) {
                        "Icing"
                    } else {
                        "Glaseado"
                    }
                    DropdownMenuItem(
                        text = { Text(textoOP43) },
                        onClick = { parteAdd = textoOP43; parte2 = "Icing" })
                    val textoOP44 = if (idioma.value) {
                        "Milk"
                    } else {
                        "Leche"
                    }
                    DropdownMenuItem(
                        text = { Text(textoOP44) },
                        onClick = { parteAdd = textoOP44; parte2 = "Milk" })
                    val textoOP45 = if (idioma.value) {
                        "Mixable"
                    } else {
                        "Sabor en trozos"
                    }
                    DropdownMenuItem(
                        text = { Text(textoOP45) },
                        onClick = { parteAdd = textoOP45; parte2 = "Mixable" })
                    val textoOP46 = if (idioma.value) {
                        "Mixable Syrup"
                    } else {
                        "Sabor sirope"
                    }
                    DropdownMenuItem(
                        text = { Text(textoOP46) },
                        onClick = { parteAdd = textoOP46; parte2 = "Mixable Syrup" })
                    val textoOP47 = if (idioma.value) {
                        "Mocha Syrup"
                    } else {
                        "Sirope para mocha"
                    }
                    DropdownMenuItem(
                        text = { Text(textoOP47) },
                        onClick = { parteAdd = textoOP47; parte2 = "Mocha Syrup" })
                    val textoOP48 = if (idioma.value) {
                        "Mocha Topper"
                    } else {
                        "Topper para mocha"
                    }
                    DropdownMenuItem(
                        text = { Text(textoOP48) },
                        onClick = { parteAdd = textoOP48; parte2 = "Mocha Topper" })
                    val textoOP49 = if (idioma.value) {
                        "Mocha Topping"
                    } else {
                        "Topping para mocha"
                    }
                    DropdownMenuItem(
                        text = { Text(textoOP49) },
                        onClick = { parteAdd = textoOP49; parte2 = "Mocha Topping" })
                    val textoOP50 = if (idioma.value) {
                        "Nacho Chips"
                    } else {
                        "Nachos"
                    }
                    DropdownMenuItem(
                        text = { Text(textoOP50) },
                        onClick = { parteAdd = textoOP50; parte2 = "Nacho Chips" })
                    val textoOP51 = if (idioma.value) {
                        "Nacho Dip"
                    } else {
                        "Salsa para nachos"
                    }
                    DropdownMenuItem(
                        text = { Text(textoOP51) },
                        onClick = { parteAdd = textoOP51; parte2 = "Nacho Dip" })
                    val textoOP52 = if (idioma.value) {
                        "Paleta Filling"
                    } else {
                        "Relleno de paleta"
                    }
                    DropdownMenuItem(
                        text = { Text(textoOP52) },
                        onClick = { parteAdd = textoOP52; parte2 = "Paleta Filling" })
                    val textoOP53 = if (idioma.value) {
                        "Paleta Shape"
                    } else {
                        "Forma de paleta"
                    }
                    DropdownMenuItem(
                        text = { Text(textoOP53) },
                        onClick = { parteAdd = textoOP53; parte2 = "Paleta Shape" })
                    val textoOP54 = if (idioma.value) {
                        "Paleta Topping"
                    } else {
                        "Topping para paletas"
                    }
                    DropdownMenuItem(
                        text = { Text(textoOP54) },
                        onClick = { parteAdd = textoOP54; parte2 = "Paleta Topping" })
                    val textoOP55 = "Pasta"
                    DropdownMenuItem(
                        text = { Text(textoOP55) },
                        onClick = { parteAdd = textoOP55; parte2 = "Pasta" })
                    val textoOP56 = if (idioma.value) {
                        "Pasta Sauce"
                    } else {
                        "Salsa para pasta"
                    }
                    DropdownMenuItem(
                        text = { Text(textoOP56) },
                        onClick = { parteAdd = textoOP56; parte2 = "Pasta Sauce" })
                    val textoOP57 = if (idioma.value) {
                        "Pasta Topping"
                    } else {
                        "Topping para pasta"
                    }
                    DropdownMenuItem(
                        text = { Text(textoOP57) },
                        onClick = { parteAdd = textoOP57; parte2 = "Pasta Topping" })
                    val textoOP58 = if (idioma.value) {
                        "Pie Crust"
                    } else {
                        "Masa de pastel"
                    }
                    DropdownMenuItem(
                        text = { Text(textoOP58) },
                        onClick = { parteAdd = textoOP58; parte2 = "Pie Crust" })
                    val textoOP59 = if (idioma.value) {
                        "Pie Filling"
                    } else {
                        "Relleno de pastel"
                    }
                    DropdownMenuItem(
                        text = { Text(textoOP59) },
                        onClick = { parteAdd = textoOP59; parte2 = "Pie Filling" })
                    val textoOP60 = if (idioma.value) {
                        "Pie Topping"
                    } else {
                        "Topping para pastel"
                    }
                    DropdownMenuItem(
                        text = { Text(textoOP60) },
                        onClick = { parteAdd = textoOP60; parte2 = "Pie Topping" })
                    val textoOP61 = if (idioma.value) {
                        "Pizza Cheese"
                    } else {
                        "Queso para pizza"
                    }
                    DropdownMenuItem(
                        text = { Text(textoOP61) },
                        onClick = { parteAdd = textoOP61; parte2 = "Pizza Cheese" })
                    val textoOP62 = if (idioma.value) {
                        "Pizza Crust"
                    } else {
                        "Masa de pizza"
                    }
                    DropdownMenuItem(
                        text = { Text(textoOP62) },
                        onClick = { parteAdd = textoOP62; parte2 = "Pizza Crust" })
                    val textoOP63 = if (idioma.value) {
                        "Pizza Sauce"
                    } else {
                        "Salsa para pizza"
                    }
                    DropdownMenuItem(
                        text = { Text(textoOP63) },
                        onClick = { parteAdd = textoOP63; parte2 = "Pizza Sauce" })
                    val textoOP64 = if (idioma.value) {
                        "Pizza Topping"
                    } else {
                        "Topping para pizza"
                    }
                    DropdownMenuItem(
                        text = { Text(textoOP64) },
                        onClick = { parteAdd = textoOP64; parte2 = "Pizza Topping" })
                    val textoOP65 = if (idioma.value) {
                        "Popcorn"
                    } else {
                        "Palomitas"
                    }
                    DropdownMenuItem(
                        text = { Text(textoOP65) },
                        onClick = { parteAdd = textoOP65; parte2 = "Popcorn" })
                    val textoOP66 = if (idioma.value) {
                        "Rice"
                    } else {
                        "Arroz"
                    }
                    DropdownMenuItem(
                        text = { Text(textoOP66) },
                        onClick = { parteAdd = textoOP66; parte2 = "Rice" })
                    val textoOP67 = if (idioma.value) {
                        "Sandwich Bread"
                    } else {
                        "Pan de sandwich"
                    }
                    DropdownMenuItem(
                        text = { Text(textoOP67) },
                        onClick = { parteAdd = textoOP67; parte2 = "Sandwich Bread" })
                    val textoOP68 = if (idioma.value) {
                        "Sandwich Cheese"
                    } else {
                        "Queso para sandwich"
                    }
                    DropdownMenuItem(
                        text = { Text(textoOP68) },
                        onClick = { parteAdd = textoOP68; parte2 = "Sandwich Cheese" })
                    val textoOP69 = if (idioma.value) {
                        "Sandwich Topping"
                    } else {
                        "Topping para sandwich"
                    }
                    DropdownMenuItem(
                        text = { Text(textoOP69) },
                        onClick = { parteAdd = textoOP69; parte2 = "Sandwich Topping" })
                    val textoOP70 = if (idioma.value) {
                        "Sausage"
                    } else {
                        "Salchicha"
                    }
                    DropdownMenuItem(
                        text = { Text(textoOP70) },
                        onClick = { parteAdd = textoOP70; parte2 = "Sausage" })
                    val textoOP71 = if (idioma.value) {
                        "Side"
                    } else {
                        "Acompañamiento"
                    }
                    DropdownMenuItem(
                        text = { Text(textoOP71) },
                        onClick = { parteAdd = textoOP71; parte2 = "Side" })
                    val textoOP72 = if (idioma.value) {
                        "Slush"
                    } else {
                        "Batido"
                    }
                    DropdownMenuItem(
                        text = { Text(textoOP72) },
                        onClick = { parteAdd = textoOP72; parte2 = "Slush" })
                    val textoOP73 = if (idioma.value) {
                        "Soda"
                    } else {
                        "Refresco"
                    }
                    DropdownMenuItem(
                        text = { Text(textoOP73) },
                        onClick = { parteAdd = textoOP73; parte2 = "Soda" })
                    val textoOP74 = if (idioma.value) {
                        "Soy Paper"
                    } else {
                        "Papel de soja"
                    }
                    DropdownMenuItem(
                        text = { Text(textoOP74) },
                        onClick = { parteAdd = textoOP74; parte2 = "Soy Paper" })
                    val textoOP75 = if (idioma.value) {
                        "Sundae Topper"
                    } else {
                        "Topper para helados"
                    }
                    DropdownMenuItem(
                        text = { Text(textoOP75) },
                        onClick = { parteAdd = textoOP75; parte2 = "Sundae Topper" })
                    val textoOP76 = if (idioma.value) {
                        "Sundae Topping"
                    } else {
                        "Topping para helados"
                    }
                    DropdownMenuItem(
                        text = { Text(textoOP76) },
                        onClick = { parteAdd = textoOP76; parte2 = "Sundae Topping" })
                    val textoOP77 = if (idioma.value) {
                        "Sushi Filling"
                    } else {
                        "Relleno de sushi"
                    }
                    DropdownMenuItem(
                        text = { Text(textoOP77) },
                        onClick = { parteAdd = textoOP77; parte2 = "Sushi Filling" })
                    val textoOP78 = if (idioma.value) {
                        "Sushi Topping"
                    } else {
                        "Topping para sushi"
                    }
                    DropdownMenuItem(
                        text = { Text(textoOP78) },
                        onClick = { parteAdd = textoOP78; parte2 = "Sushi Topping" })
                    val textoOP79 = if (idioma.value) {
                        "Taco Meat"
                    } else {
                        "Carne para tacos"
                    }
                    DropdownMenuItem(
                        text = { Text(textoOP79) },
                        onClick = { parteAdd = textoOP79; parte2 = "Taco Meat" })
                    val textoOP80 = if (idioma.value) {
                        "Taco Shell"
                    } else {
                        "Pan de tacos"
                    }
                    DropdownMenuItem(
                        text = { Text(textoOP80) },
                        onClick = { parteAdd = textoOP80; parte2 = "Taco Shell" })
                    val textoOP81 = if (idioma.value) {
                        "Taco Topping"
                    } else {
                        "Topping para tacos"
                    }
                    DropdownMenuItem(
                        text = { Text(textoOP81) },
                        onClick = { parteAdd = textoOP81; parte2 = "Taco Topping" })
                    val textoOP82 = if (idioma.value) {
                        "Tea Bubbles"
                    } else {
                        "Burbujas de té"
                    }
                    DropdownMenuItem(
                        text = { Text(textoOP82) },
                        onClick = { parteAdd = textoOP82; parte2 = "Tea Bubbles" })
                    val textoOP83 = if (idioma.value) {
                        "Top Crust"
                    } else {
                        "Tapa de pastel"
                    }
                    DropdownMenuItem(
                        text = { Text(textoOP83) },
                        onClick = { parteAdd = textoOP83; parte2 = "Top Crust" })
                }
            }
        }
    }
}