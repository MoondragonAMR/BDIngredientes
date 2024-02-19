package com.example.bdingredientes.ui.theme

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ListItem
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.bdingredientes.clases.ImagenIngrediente
import com.example.bdingredientes.clases.Ingredient
import com.example.bdingredientes.clases.Rutas
import com.example.bdingredientes.clases.VMBD
import com.example.bdingredientes.clases.VMBD2
import com.example.bdingredientes.clases.ViewModelScaffold
import com.example.bdingredientes.clases.celebracion
import com.example.bdingredientes.clases.codigo
import com.example.bdingredientes.clases.deCelebracion
import com.example.bdingredientes.clases.imagenes
import com.example.bdingredientes.clases.nombre
import com.example.bdingredientes.clases.sabor
import com.example.bdingredientes.clases.tipo

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaIngredients2(db : VMBD2, sf: ViewModelScaffold,  navController: NavController){
    //var db : VMBD2 = viewModel()
    var ingredients = db.ingredients.collectAsState().value
    var estado by remember { mutableStateOf(false) }
    var busqueda by remember { mutableStateOf("") }
    var filtro by remember { mutableStateOf("") }
    var numero by remember { mutableStateOf(1L)}
    var valor by remember { mutableStateOf(numero.toString())}
    var parametro by remember { mutableStateOf("id")}
    var filtroParametro by remember { mutableStateOf("None") }
    var activado by remember { mutableStateOf(false) }
    var activado2 by remember { mutableStateOf(false) }
    //var sf : ViewModelScaffold = viewModel()
    var aleatorio = sf.Aleatorio.collectAsState().value
    var ingredientsRandom = db.ingredientsAleatorio.collectAsState().value
    var delete = sf.borrar.collectAsState().value
    var update = sf.modificar.collectAsState().value
    var listaMostrar = db.listaMostrar.collectAsState().value
    var url by remember { mutableStateOf("") }

    DisposableEffect(db){
        db.crearListener()
        onDispose { db.borrarListener() }
    }

    Column() {
        Row() {
            Text(text =
                if (delete.value) {
                    "Click on an ingredient to delete it, click the Delete button again to stop Delete Mode"
                } else if (update.value) {
                    "Click on an ingredient to modify it"
                } else {
                    "Here are all of your ingredients"
            }, fontWeight = FontWeight.Bold)
        }
        SearchBar(placeholder = { Text("Search ingredients by name") },
            query = busqueda,
            onQueryChange = { textoIntroducido -> busqueda = textoIntroducido },
            onSearch = { filtro = it; estado = false },
            active = estado,
            onActiveChange = { estado = !estado }) {
            LazyColumn() {
                items(ingredients.size) {
                    if ((ingredients[it].name.contains(busqueda, true)) || (ingredients[it].type.lowercase().contains(busqueda)) || (busqueda.isBlank())) {
                        ListItem(headlineContent = { Text(ingredients[it].name) }, Modifier.clickable { busqueda = ingredients[it].name })
                    }
                }
            }
        }
        Row() {
            Text("Number of ingredients shown: ")
            TextField(
                value = valor,
                onValueChange = { texto -> valor = texto },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                placeholder = { Text("Insert a number") })
        }
        Row() {
            Text("Order by: ")
            ExposedDropdownMenuBox(expanded = activado,
                onExpandedChange = { activado = !activado }) {
                TextField(modifier = Modifier.menuAnchor(),
                    readOnly = true,
                    value = parametro,
                    onValueChange = {}
                )
                DropdownMenu(expanded = activado, onDismissRequest = { activado = false }) {
                    DropdownMenuItem(
                        text = { Text("id") },
                        onClick = { parametro = "id" })
                    DropdownMenuItem(
                        text = { Text("name") },
                        onClick = { parametro = "name" })
                    DropdownMenuItem(
                        text = { Text("type") },
                        onClick = { parametro = "type" })
                    DropdownMenuItem(
                        text = { Text("flavor") },
                        onClick = { parametro = "flavor" })
                    DropdownMenuItem(
                        text = { Text("Holiday-exclusive") },
                        onClick = { parametro = "Holiday-exclusive" })
                    DropdownMenuItem(
                        text = { Text("Holiday") },
                        onClick = { parametro = "Holiday" })
                }
            }
        }
        Row() {
            Text("Filter: ")
            ExposedDropdownMenuBox(expanded = activado2,
                onExpandedChange = { activado2 = !activado2 }) {
                TextField(modifier = Modifier.menuAnchor(),
                    readOnly = true,
                    value = filtroParametro,
                    onValueChange = {}
                )
                DropdownMenu(expanded = activado2, onDismissRequest = { activado2 = false }) {
                    DropdownMenuItem(
                        text = { Text("None") },
                        onClick = { filtroParametro = "None" })
                    when (parametro) {
                        "type" -> {
                            DropdownMenuItem(
                                text = { Text("Aditive") },
                                onClick = { filtroParametro = "Aditive" })
                            DropdownMenuItem(
                                text = { Text("Bread") },
                                onClick = { filtroParametro = "Bread" })
                            DropdownMenuItem(
                                text = { Text("Breading") },
                                onClick = { filtroParametro = "Breading" })
                            DropdownMenuItem(
                                text = { Text("Breakfast Food") },
                                onClick = { filtroParametro = "Breakfast Food" })
                            DropdownMenuItem(
                                text = { Text("Breakfast Mixable") },
                                onClick = { filtroParametro = "Breakfast Mixable" })
                            DropdownMenuItem(
                                text = { Text("Bubble Tea") },
                                onClick = { filtroParametro = "Bubble Tea" })
                            DropdownMenuItem(
                                text = { Text("Burger Bun") },
                                onClick = { filtroParametro = "Burger Bun" })
                            DropdownMenuItem(
                                text = { Text("Burger Meat") },
                                onClick = { filtroParametro = "Burger Meat" })
                            DropdownMenuItem(
                                text = { Text("Cake") },
                                onClick = { filtroParametro = "Cake" })
                            DropdownMenuItem(
                                text = { Text("Cannoli Shell") },
                                onClick = { filtroParametro = "Cannoli Shell" })
                            DropdownMenuItem(
                                text = { Text("Chicken Sauce") },
                                onClick = { filtroParametro = "Chicken Sauce" })
                            DropdownMenuItem(
                                text = { Text("Chicken Wings") },
                                onClick = { filtroParametro = "Chicken Wings" })
                            DropdownMenuItem(
                                text = { Text("Coffee") },
                                onClick = { filtroParametro = "Coffee" })
                            DropdownMenuItem(
                                text = { Text("Cookie Dough") },
                                onClick = { filtroParametro = "Cookie Dough" })
                            DropdownMenuItem(
                                text = { Text("Cookie Mixable") },
                                onClick = { filtroParametro = "Cookie Mixable" })
                            DropdownMenuItem(
                                text = { Text("Cream") },
                                onClick = { filtroParametro = "Cream" })
                            DropdownMenuItem(
                                text = { Text("Cream Dollop") },
                                onClick = { filtroParametro = "Cream Dollop" })
                            DropdownMenuItem(
                                text = { Text("Dip") },
                                onClick = { filtroParametro = "Dip" })
                            DropdownMenuItem(
                                text = { Text("Donut Cutter") },
                                onClick = { filtroParametro = "Donut Cutter" })
                            DropdownMenuItem(
                                text = { Text("Donut Dough") },
                                onClick = { filtroParametro = "Donut Dough" })
                            DropdownMenuItem(
                                text = { Text("Donut Filling") },
                                onClick = { filtroParametro = "Donut Filling" })
                            DropdownMenuItem(
                                text = { Text("Drink") },
                                onClick = { filtroParametro = "Drink" })
                            DropdownMenuItem(
                                text = { Text("Flat Topper") },
                                onClick = { filtroParametro = "Flat Topper" })
                            DropdownMenuItem(
                                text = { Text("Fries") },
                                onClick = { filtroParametro = "Fries" })
                            DropdownMenuItem(
                                text = { Text("Frosting") },
                                onClick = { filtroParametro = "Frosting" })
                            DropdownMenuItem(
                                text = { Text("Fry Topping") },
                                onClick = { filtroParametro = "Fry Topping" })
                            DropdownMenuItem(
                                text = { Text("Hot Dog Bun") },
                                onClick = { filtroParametro = "Hot Dog Bun" })
                            DropdownMenuItem(
                                text = { Text("Ice") },
                                onClick = { filtroParametro = "Ice" })
                            DropdownMenuItem(
                                text = { Text("Ice Cream") },
                                onClick = { filtroParametro = "Ice Cream" })
                            DropdownMenuItem(
                                text = { Text("Long Topper") },
                                onClick = { filtroParametro = "Long Topper" })
                            DropdownMenuItem(
                                text = { Text("Milk") },
                                onClick = { filtroParametro = "Milk" })
                            DropdownMenuItem(
                                text = { Text("Mixable") },
                                onClick = { filtroParametro = "Mixable" })
                            DropdownMenuItem(
                                text = { Text("Mixable Syrup") },
                                onClick = { filtroParametro = "Mixable Syrup" })
                            DropdownMenuItem(
                                text = { Text("Nacho Chips") },
                                onClick = { filtroParametro = "Nacho Chips" })
                            DropdownMenuItem(
                                text = { Text("Nacho Dip") },
                                onClick = { filtroParametro = "Nacho Dip" })
                            DropdownMenuItem(
                                text = { Text("Pasta") },
                                onClick = { filtroParametro = "Pasta" })
                            DropdownMenuItem(
                                text = { Text("Pasta Sauce") },
                                onClick = { filtroParametro = "Pasta Sauce" })
                            DropdownMenuItem(
                                text = { Text("Pie Crust") },
                                onClick = { filtroParametro = "Pie Crust" })
                            DropdownMenuItem(
                                text = { Text("Pie Filling") },
                                onClick = { filtroParametro = "Pie Filling" })
                            DropdownMenuItem(
                                text = { Text("Pizza Cheese") },
                                onClick = { filtroParametro = "Pizza Cheese" })
                            DropdownMenuItem(
                                text = { Text("Pizza Crust") },
                                onClick = { filtroParametro = "Pizza Crust" })
                            DropdownMenuItem(
                                text = { Text("Pizza Sauce") },
                                onClick = { filtroParametro = "Pizza Sauce" })
                            DropdownMenuItem(
                                text = { Text("Popcorn") },
                                onClick = { filtroParametro = "Popcorn" })
                            DropdownMenuItem(
                                text = { Text("Powder") },
                                onClick = { filtroParametro = "Powder" })
                            DropdownMenuItem(
                                text = { Text("Rice") },
                                onClick = { filtroParametro = "Rice" })
                            DropdownMenuItem(
                                text = { Text("Sandwich Bread") },
                                onClick = { filtroParametro = "Sandwich Bread" })
                            DropdownMenuItem(
                                text = { Text("Sandwich Cheese") },
                                onClick = { filtroParametro = "Sandwich Cheese" })
                            DropdownMenuItem(
                                text = { Text("Sauce") },
                                onClick = { filtroParametro = "Sauce" })
                            DropdownMenuItem(
                                text = { Text("Sausage") },
                                onClick = { filtroParametro = "Sausage" })
                            DropdownMenuItem(
                                text = { Text("Shaker") },
                                onClick = { filtroParametro = "Shaker" })
                            DropdownMenuItem(
                                text = { Text("Side") },
                                onClick = { filtroParametro = "Side" })
                            DropdownMenuItem(
                                text = { Text("Slush") },
                                onClick = { filtroParametro = "Slush" })
                            DropdownMenuItem(
                                text = { Text("Soda") },
                                onClick = { filtroParametro = "Soda" })
                            DropdownMenuItem(
                                text = { Text("Soy Paper") },
                                onClick = { filtroParametro = "Soy Paper" })
                            DropdownMenuItem(
                                text = { Text("Sushi Filling") },
                                onClick = { filtroParametro = "Sushi Filling" })
                            DropdownMenuItem(
                                text = { Text("Syrup") },
                                onClick = { filtroParametro = "Syrup" })
                            DropdownMenuItem(
                                text = { Text("Taco Meat") },
                                onClick = { filtroParametro = "Taco Meat" })
                            DropdownMenuItem(
                                text = { Text("Taco Shell") },
                                onClick = { filtroParametro = "Taco Shell" })
                            DropdownMenuItem(
                                text = { Text("Tea Bubbles") },
                                onClick = { filtroParametro = "Tea Bubbles" })
                            DropdownMenuItem(
                                text = { Text("Top Crust") },
                                onClick = { filtroParametro = "Top Crust" })
                            DropdownMenuItem(
                                text = { Text("Topper") },
                                onClick = { filtroParametro = "Topper" })
                            DropdownMenuItem(
                                text = { Text("Topping") },
                                onClick = { filtroParametro = "Topping" })
                        }

                        "flavor" -> {
                            DropdownMenuItem(
                                text = { Text("savory") },
                                onClick = { filtroParametro = "savory" })
                            DropdownMenuItem(
                                text = { Text("sweet") },
                                onClick = { filtroParametro = "sweet" })
                        }

                        "Holiday-exclusive" -> {
                            DropdownMenuItem(
                                text = { Text("false") },
                                onClick = { filtroParametro = "false" })
                            DropdownMenuItem(
                                text = { Text("true") },
                                onClick = { filtroParametro = "true" })
                        }

                        "Holiday" -> {
                            DropdownMenuItem(
                                text = { Text("Standard") },
                                onClick = { filtroParametro = "Standard" })
                            DropdownMenuItem(
                                text = { Text("Baseball Season") },
                                onClick = { filtroParametro = "Baseball Season" })
                            DropdownMenuItem(
                                text = { Text("BavariaFest") },
                                onClick = { filtroParametro = "BavariaFest" })
                            DropdownMenuItem(
                                text = { Text("Big Top Carnival") },
                                onClick = { filtroParametro = "Big Top Carnival" })
                            DropdownMenuItem(
                                text = { Text("Cherry Blossom Festival") },
                                onClick = { filtroParametro = "Cherry Blossom Festival" })
                            DropdownMenuItem(
                                text = { Text("ChiliFest") },
                                onClick = { filtroParametro = "ChiliFest" })
                            DropdownMenuItem(
                                text = { Text("Christmas") },
                                onClick = { filtroParametro = "Christmas" })
                            DropdownMenuItem(
                                text = { Text("Cinco de Mayo") },
                                onClick = { filtroParametro = "Cinco de Mayo" })
                            DropdownMenuItem(
                                text = { Text("Comet Con") },
                                onClick = { filtroParametro = "Comet Con" })
                            DropdownMenuItem(
                                text = { Text("Day of the Dead") },
                                onClick = { filtroParametro = "Day of the Dead" })
                            DropdownMenuItem(
                                text = { Text("Easter") },
                                onClick = { filtroParametro = "Easter" })
                            DropdownMenuItem(
                                text = { Text("Film Fest") },
                                onClick = { filtroParametro = "Film Fest" })
                            DropdownMenuItem(
                                text = { Text("Gondola") },
                                onClick = { filtroParametro = "Gondola" })
                            DropdownMenuItem(
                                text = { Text("Groovstock") },
                                onClick = { filtroParametro = "Groovstock" })
                            DropdownMenuItem(
                                text = { Text("Halloween") },
                                onClick = { filtroParametro = "Halloween" })
                            DropdownMenuItem(
                                text = { Text("Holi") },
                                onClick = { filtroParametro = "Holi" })
                            DropdownMenuItem(
                                text = { Text("Lucky Lucky Matsuri") },
                                onClick = { filtroParametro = "Lucky Lucky Matsuri" })
                            DropdownMenuItem(
                                text = { Text("Lunar New Year") },
                                onClick = { filtroParametro = "Lunar New Year" })
                            DropdownMenuItem(
                                text = { Text("Maple Mornings") },
                                onClick = { filtroParametro = "Maple Mornings" })
                            DropdownMenuItem(
                                text = { Text("Mardi Gras") },
                                onClick = { filtroParametro = "Mardi Gras" })
                            DropdownMenuItem(
                                text = { Text("Neptune's Feast") },
                                onClick = { filtroParametro = "Neptune's Feast" })
                            DropdownMenuItem(
                                text = { Text("New Year") },
                                onClick = { filtroParametro = "New Year" })
                            DropdownMenuItem(
                                text = { Text("Onionfest") },
                                onClick = { filtroParametro = "Onionfest" })
                            DropdownMenuItem(
                                text = { Text("Pirate Bash") },
                                onClick = { filtroParametro = "Pirate Bash" })
                            DropdownMenuItem(
                                text = { Text("Portallini Feast") },
                                onClick = { filtroParametro = "Portallini Feast" })
                            DropdownMenuItem(
                                text = { Text("Romano Wedding") },
                                onClick = { filtroParametro = "Romano Wedding" })
                            DropdownMenuItem(
                                text = { Text("Sky Ninja Returns") },
                                onClick = { filtroParametro = "Sky Ninja Returns" })
                            DropdownMenuItem(
                                text = { Text("St Paddy") },
                                onClick = { filtroParametro = "St Paddy" })
                            DropdownMenuItem(
                                text = { Text("Starlight") },
                                onClick = { filtroParametro = "Starlight" })
                            DropdownMenuItem(
                                text = { Text("Summer Luau") },
                                onClick = { filtroParametro = "Summer Luau" })
                            DropdownMenuItem(
                                text = { Text("Thanksgiving") },
                                onClick = { filtroParametro = "Thanksgiving" })
                            DropdownMenuItem(
                                text = { Text("Valentines") },
                                onClick = { filtroParametro = "Valentines" })
                            DropdownMenuItem(
                                text = { Text("Volcano Gala") },
                                onClick = { filtroParametro = "Volcano Gala" })
                        }
                    }
                }
            }
        }
        Row() {
            Button(onClick = {
                numero = valor.toLong()
                if (filtroParametro == "None") {
                    db.filtrarIngredientes(numero, parametro)
                } else db.filtrarIngredientes(numero, parametro, filtroParametro)
            }) {
                Text("Apply conditions")
            }
        }
        LazyColumn(
        ) {

            if (aleatorio.value) {
                listaMostrar = ingredientsRandom
            } else {
                listaMostrar = ingredients
            }
            items(listaMostrar.size) {
                if ((listaMostrar[it].name.contains(
                        filtro,
                        ignoreCase = true
                    )) || (filtro.isBlank())
                ) {
                    nombre = listaMostrar[it].name
                    tipo = listaMostrar[it].type
                    sabor = listaMostrar[it].flavor
                    deCelebracion = listaMostrar[it].holidayExclusive
                    celebracion = listaMostrar[it].holiday
                    codigo = listaMostrar[it].id

                    when (nombre) {
                        "Acorn Cutter" -> {
                            url = imagenes[0]
                        }
                        "Adobo" -> {
                            url = imagenes[1]
                        }
                        "Aged Gouda" -> {
                            url = imagenes[2]
                        }
                        "Ahi Tuna" -> {
                            url = imagenes[3]
                        }
                        "Aji Amarillo" -> {
                            url = imagenes[4]
                        }
                        "Akai Soy Paper" -> {
                            url = imagenes[5]
                        }
                        "Alabama BBQ Sauce" -> {
                            url = imagenes[6]
                        }
                        "Alexandertorte" -> {
                            url = imagenes[7]
                        }
                        "Almond Snap Cookie" -> {
                            url = imagenes[8]
                        }
                        "Almond Snap Powder" -> {
                            url = imagenes[9]
                        }
                        "Almond Tea" -> {
                            url = imagenes[10]
                        }
                        "Amarena" -> {
                            url = imagenes[11]
                        }
                        "Ambrosia Ice Cream" -> {
                            url = imagenes[12]
                        }
                        "American Cheese" -> {
                            url = imagenes[13]
                        }
                        "Ancho Chile" -> {
                            url = imagenes[14]
                        }
                        "Anchor Cookie" -> {
                            url = imagenes[15]
                        }
                        "Anchovies" -> {
                            url = imagenes[16]
                        }
                        "Animal Crackers" -> {
                            url = imagenes[17]
                        }
                        "Anticucho" -> {
                            url = imagenes[18]
                        }
                        "Apple Crumb Cake" -> {
                            url = imagenes[19]
                        }
                        "Apple Filling" -> {
                            url = imagenes[20]
                        }
                        "Apple Pie Filling" -> {
                            url = imagenes[21]
                        }
                        "Aprajita Powder" -> {
                            url = imagenes[22]
                        }
                        "Apricot Drizzle" -> {
                            url = imagenes[23]
                        }
                        "Apricot Icing" -> {
                            url = imagenes[24]
                        }
                        "Artichoke Dip" -> {
                            url = imagenes[25]
                        }
                        "Artichoke Hearts" -> {
                            url = imagenes[26]
                        }
                        "Artisanal Truffle Corn" -> {
                            url = imagenes[27]
                        }
                        "Arugula Wreath" -> {
                            url = imagenes[28]
                        }
                        "Asiago Cheese" -> {
                            url = imagenes[29]
                        }
                        "Asparagus" -> {
                            url = imagenes[30]
                        }
                        "Asteroids" -> {
                            url = imagenes[31]
                        }
                        "Astro Elixir" -> {
                            url = imagenes[32]
                        }
                        "Astronaut Ice Cream" -> {
                            url = imagenes[33]
                        }
                        "Atomic Sauce" -> {
                            url = imagenes[34]
                        }
                        "Autumn Leaves Crust" -> {
                            url = imagenes[35]
                        }
                        "Autumn Leaves Sprinkles" -> {
                            url = imagenes[36]
                        }
                        "Avocado" -> {
                            url = imagenes[37]
                        }
                        "Awesome Sauce" -> {
                            url = imagenes[38]
                        }
                        "Awesome Sauce Dip" -> {
                            url = imagenes[39]
                        }
                        "Azuki Fluff" -> {
                            url = imagenes[40]
                        }
                        "Azuki Icing" -> {
                            url = imagenes[41]
                        }
                        "Azuki Sauce" -> {
                            url = imagenes[42]
                        }
                        "Azul Ranch Taco" -> {
                            url = imagenes[43]
                        }
                        "BBQ Ribs" -> {
                            url = imagenes[44]
                        }
                        "BBQ Rub" -> {
                            url = imagenes[45]
                        }
                        "BBQ Sauce" -> {
                            url = imagenes[46]
                        }
                        "Baby Blots" -> {
                            url = imagenes[47]
                        }
                        "Bacobites" -> {
                            url = imagenes[48]
                        }
                        "Bacon" -> {
                            url = imagenes[49]
                        }
                        "Bacon Jack" -> {
                            url = imagenes[50]
                        }
                        "Bacon Mix" -> {
                            url = imagenes[51]
                        }
                        "Bagel" -> {
                            url = imagenes[52]
                        }
                        "Baked Beans" -> {
                            url = imagenes[53]
                        }
                        "Balsamic Dressing" -> {
                            url = imagenes[54]
                        }
                        "Banana" -> {
                            url = imagenes[55]
                        }
                        "Banana Filling" -> {
                            url = imagenes[56]
                        }
                        "Banana Peppers" -> {
                            url = imagenes[57]
                        }
                        "Banana Syrup" -> {
                            url = imagenes[58]
                        }
                        "Barmbrack Bread" -> {
                            url = imagenes[59]
                        }
                        "Barmbrack Bun" -> {
                            url = imagenes[60]
                        }
                        "Basil Leaves" -> {
                            url = imagenes[61]
                        }
                        "Battenberg Cake" -> {
                            url = imagenes[62]
                        }
                        "Battenberg Shell" -> {
                            url = imagenes[63]
                        }
                        "Battered Perch" -> {
                            url = imagenes[64]
                        }
                        "Bearclaw" -> {
                            url = imagenes[65]
                        }
                        "Beef" -> {
                            url = imagenes[66]
                        }
                        "Beef Brisket" -> {
                            url = imagenes[67]
                        }
                        "Beefy Bolognese" -> {
                            url = imagenes[68]
                        }
                        "Beer Batter" -> {
                            url = imagenes[69]
                        }
                        "Beetbread" -> {
                            url = imagenes[70]
                        }
                        "Beetbread Bun" -> {
                            url = imagenes[71]
                        }
                        "Bellulli Chutney" -> {
                            url = imagenes[72]
                        }
                        "Beni Shoga" -> {
                            url = imagenes[73]
                        }
                        "Bierkase Dip" -> {
                            url = imagenes[74]
                        }
                        "Bierkase Sauce" -> {
                            url = imagenes[75]
                        }
                        "Birch Beer Topping" -> {
                            url = imagenes[76]
                        }
                        "Birthday Cake" -> {
                            url = imagenes[77]
                        }
                        "Black Beans" -> {
                            url = imagenes[78]
                        }
                        "Black Cherry Slush" -> {
                            url = imagenes[79]
                        }
                        "Black Forest Tea" -> {
                            url = imagenes[80]
                        }
                        "Black Frosting" -> {
                            url = imagenes[81]
                        }
                        "Black Mist" -> {
                            url = imagenes[82]
                        }
                        "Black Mist Topping" -> {
                            url = imagenes[83]
                        }
                        "Black Olives" -> {
                            url = imagenes[84]
                        }
                        "Black Pearl Crisps" -> {
                            url = imagenes[85]
                        }
                        "Black Pepper" -> {
                            url = imagenes[86]
                        }
                        "Black Rice" -> {
                            url = imagenes[87]
                        }
                        "Blackberries" -> {
                            url = imagenes[88]
                        }
                        "Blackberry Bark" -> {
                            url = imagenes[89]
                        }
                        "Blackberry Jelly" -> {
                            url = imagenes[90]
                        }
                        "Blackberry Remoulade" -> {
                            url = imagenes[91]
                        }
                        "Blazeberry Sauce" -> {
                            url = imagenes[92]
                        }
                        "Blockbuster Butter" -> {
                            url = imagenes[93]
                        }
                        "Blockmalz Slush" -> {
                            url = imagenes[94]
                        }
                        "Blondie" -> {
                            url = imagenes[95]
                        }
                        "Blossom Cookie" -> {
                            url = imagenes[96]
                        }
                        "Blot" -> {
                            url = imagenes[97]
                        }
                        "Blue Cheese" -> {
                            url = imagenes[98]
                        }
                        "Blue Cheese Crumbles" -> {
                            url = imagenes[99]
                        }
                        "Blue Cheese Dip" -> {
                            url = imagenes[100]
                        }
                        "Blue Corn Chips" -> {
                            url = imagenes[101]
                        }
                        "Blue Moon Ice Cream" -> {
                            url = imagenes[102]
                        }
                        "Blue Moon Syrup" -> {
                            url = imagenes[103]
                        }
                        "Blue Nimbus Icing" -> {
                            url = imagenes[104]
                        }
                        "Blue Nimbus Syrup" -> {
                            url = imagenes[105]
                        }
                        "Blue Raspberry Cream" -> {
                            url = imagenes[106]
                        }
                        "Blue Raspberry Slush" -> {
                            url = imagenes[107]
                        }
                        "Blue Star Sprinkles" -> {
                            url = imagenes[108]
                        }
                        "Blueberries" -> {
                            url = imagenes[109]
                        }
                        "Blueberry Cake" -> {
                            url = imagenes[110]
                        }
                        "Blueberry Custard" -> {
                            url = imagenes[111]
                        }
                        "Blueberry Filling" -> {
                            url = imagenes[112]
                        }
                        "Blueberry Milk" -> {
                            url = imagenes[113]
                        }
                        "Blueberry Mix" -> {
                            url = imagenes[114]
                        }
                        "Blueberry Pie-Tarts" -> {
                            url = imagenes[115]
                        }
                        "Blueberry Swizzle" -> {
                            url = imagenes[116]
                        }
                        "Blueberry Syrup" -> {
                            url = imagenes[117]
                        }
                        "Blueberry Tea" -> {
                            url = imagenes[118]
                        }
                        "Blueberry Wave Syrup" -> {
                            url = imagenes[119]
                        }
                        "Blues-Berry Cream" -> {
                            url = imagenes[120]
                        }
                        "Boba Bubbles" -> {
                            url = imagenes[121]
                        }
                        "Bolillo Bun" -> {
                            url = imagenes[122]
                        }
                        "Bolivian Chiles" -> {
                            url = imagenes[123]
                        }
                        "Boneless Wings" -> {
                            url = imagenes[124]
                        }
                        "Bonfire Toffee" -> {
                            url = imagenes[125]
                        }
                        "Bonito Flakes" -> {
                            url = imagenes[126]
                        }
                        "Boston Beanies" -> {
                            url = imagenes[127]
                        }
                        "Boston Cream" -> {
                            url = imagenes[128]
                        }
                        "Botamochi Cake" -> {
                            url = imagenes[129]
                        }
                        "Bottarga" -> {
                            url = imagenes[130]
                        }
                        "Bouquet Blend" -> {
                            url = imagenes[131]
                        }
                        "Bowtie" -> {
                            url = imagenes[132]
                        }
                        "Bratwurst" -> {
                            url = imagenes[133]
                        }
                        "Breakfast Blast" -> {
                            url = imagenes[134]
                        }
                        "Brezn" -> {
                            url = imagenes[135]
                        }
                        "Brioche Bun" -> {
                            url = imagenes[136]
                        }
                        "Broccoli" -> {
                            url = imagenes[137]
                        }
                        "Brown Rice" -> {
                            url = imagenes[138]
                        }
                        "Brownie Batter" -> {
                            url = imagenes[139]
                        }
                        "Bubble Gum Cream" -> {
                            url = imagenes[140]
                        }
                        "Bubble Planet" -> {
                            url = imagenes[141]
                        }
                        "Bubblegum Slush" -> {
                            url = imagenes[142]
                        }
                        "Bubblegum Whip" -> {
                            url = imagenes[143]
                        }
                        "Buckeye" -> {
                            url = imagenes[144]
                        }
                        "Buffalo Sauce" -> {
                            url = imagenes[145]
                        }
                        "Bunny Ear Candy" -> {
                            url = imagenes[146]
                        }
                        "Burgundy Truffle" -> {
                            url = imagenes[147]
                        }
                        "Buriti Topping" -> {
                            url = imagenes[148]
                        }
                        "Burnt Ends" -> {
                            url = imagenes[149]
                        }
                        "Butter" -> {
                            url = imagenes[150]
                        }
                        "Butter Pecan Cake" -> {
                            url = imagenes[151]
                        }
                        "Buttered Popcorn" -> {
                            url = imagenes[152]
                        }
                        "Buttermilk Biscuit" -> {
                            url = imagenes[153]
                        }
                        "Buttermilk Syrup" -> {
                            url = imagenes[154]
                        }
                        "Butterscotch Bubbles" -> {
                            url = imagenes[155]
                        }
                        "Butterscotch Cream" -> {
                            url = imagenes[156]
                        }
                        "Butterscotch Smooches" -> {
                            url = imagenes[157]
                        }
                        "Butterscotch Syrup" -> {
                            url = imagenes[158]
                        }
                        "Butterzinger Bits" -> {
                            url = imagenes[159]
                        }
                        "Butterzinger Syrup" -> {
                            url = imagenes[160]
                        }
                        else -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Al%20azar.png?alt=media&token=ab58f4fe-3730-48ca-870d-439e3e65fb44"
                        }
                    }

                    Column(
                        Modifier
                            .padding(4.dp)
                            .border(width = 2.dp, color = Color.Cyan)
                            .clickable {
                                if (delete.value) {
                                    db.borrarIngrediente(codigo)
                                } else if (update.value) {
                                    navController.navigate(Rutas.Update.Ruta)
                                }
                            }) {
                        Text(text = "name = $nombre")
                        Text(text = "type = $tipo")
                        Text(text = "flavor = $sabor")
                        Text(text = "holiday-exclusive = $deCelebracion")
                        Text(text = "holiday = $celebracion")
                        ImagenIngrediente(url)
                    }
                }
            }
        }
    }
}