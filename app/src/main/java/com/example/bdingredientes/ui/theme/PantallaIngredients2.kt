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
                        "Cajeta" -> {
                            url = imagenes[161]
                        }
                        "Cajun Shrimp" -> {
                            url = imagenes[162]
                        }
                        "Calico Jack" -> {
                            url = imagenes[163]
                        }
                        "Calypso Sauce" -> {
                            url = imagenes[164]
                        }
                        "Campagrain" -> {
                            url = imagenes[165]
                        }
                        "Cancha Corn" -> {
                            url = imagenes[166]
                        }
                        "Candied Pecans" -> {
                            url = imagenes[167]
                        }
                        "Candle" -> {
                            url = imagenes[168]
                        }
                        "Candy Apple Sauce" -> {
                            url = imagenes[169]
                        }
                        "Candy Baseball" -> {
                            url = imagenes[170]
                        }
                        "Candy Cactus" -> {
                            url = imagenes[171]
                        }
                        "Candy Cane" -> {
                            url = imagenes[172]
                        }
                        "Candy Cane Drizzle" -> {
                            url = imagenes[173]
                        }
                        "Candy Corn" -> {
                            url = imagenes[174]
                        }
                        "Candy Corn Cream" -> {
                            url = imagenes[175]
                        }
                        "Candy Corn Drizzle" -> {
                            url = imagenes[176]
                        }
                        "Candy Egg" -> {
                            url = imagenes[177]
                        }
                        "Candy Heart" -> {
                            url = imagenes[178]
                        }
                        "Candy Hearts" -> {
                            url = imagenes[179]
                        }
                        "Candy Jack" -> {
                            url = imagenes[180]
                        }
                        "Candy Jack-O-Lantern" -> {
                            url = imagenes[181]
                        }
                        "Candy Present" -> {
                            url = imagenes[182]
                        }
                        "Candy Rocket" -> {
                            url = imagenes[183]
                        }
                        "Canned Cranberry" -> {
                            url = imagenes[184]
                        }
                        "Canned Ham" -> {
                            url = imagenes[185]
                        }
                        "Cannoli Cream" -> {
                            url = imagenes[186]
                        }
                        "Cannonball Gum" -> {
                            url = imagenes[187]
                        }
                        "Cantaloupe Drizzle" -> {
                            url = imagenes[188]
                        }
                        "Capicola" -> {
                            url = imagenes[189]
                        }
                        "Capirotada Blend" -> {
                            url = imagenes[190]
                        }
                        "Caramel Apple" -> {
                            url = imagenes[191]
                        }
                        "Caramel Apple Drizzle" -> {
                            url = imagenes[192]
                        }
                        "Caramel Apple Ice Cream" -> {
                            url = imagenes[193]
                        }
                        "Caramel Apple Sauce" -> {
                            url = imagenes[194]
                        }
                        "Caramel Apple Shell" -> {
                            url = imagenes[195]
                        }
                        "Caramel Drizzle" -> {
                            url = imagenes[196]
                        }
                        "Caramuri" -> {
                            url = imagenes[197]
                        }
                        "Carolina Sauce" -> {
                            url = imagenes[198]
                        }
                        "Carrot" -> {
                            url = imagenes[199]
                        }
                        "Carrot Cake" -> {
                            url = imagenes[200]
                        }
                        "Carrot Crust" -> {
                            url = imagenes[201]
                        }
                        "Carrot Stick" -> {
                            url = imagenes[202]
                        }
                        "Cathedral Carbonara" -> {
                            url = imagenes[203]
                        }
                        "Cauldron Powder" -> {
                            url = imagenes[204]
                        }
                        "Caviar" -> {
                            url = imagenes[205]
                        }
                        "Celery" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Celery.png?alt=media&token=f1f69039-d080-411d-8a2b-e1843751ad8c"
                        }
                        "Cellentani" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Cellentani.png?alt=media&token=6bc951f3-7001-4f96-960e-05185e906d9a"
                        }
                        "Celtic Cannoli Shell" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Celtic_Cannoli_Shell.png?alt=media&token=c0db55c3-7814-452c-b192-4ab50513ba2b"
                        }
                        "Celtic Knot Crust" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Celtic_Knot_Crust.png?alt=media&token=c97c37da-da9a-48ef-b245-4dce162d0cf6"
                        }
                        "Chai Powder" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Chai_Powder.png?alt=media&token=0146d16b-848c-4d78-84c1-0cd6566093e9"
                        }
                        "Chai Reverb Drizzle" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Chai_Reverb.png?alt=media&token=9192fd6d-48e8-46a1-a0d9-9278ff9243db"
                        }
                        "Chai Tea" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Chai_Tea.png?alt=media&token=599cc414-5050-47a5-bb06-b16c02e87eae"
                        }
                        "Chairo Soy Paper" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Chairo_Soy_Paper.png?alt=media&token=767ae4ef-0e87-4080-a90b-fb4dcf5ce7be"
                        }
                        "Chamoyada Ice Cream" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Chamoyada_Ice_Cream.png?alt=media&token=4043b2f7-79f5-4fb7-a657-af42321c8dc8"
                        }
                        "Chamoyada Slush" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Chamoyada_Slush.png?alt=media&token=1e31fc48-ccba-481b-9f45-9e9fccc7dea5"
                        }
                        "Champurrado Powder" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Champurrado_Powder.png?alt=media&token=f55e2a51-38c3-4c9c-be0e-42179e28c66e"
                        }
                        "Champurrado Syrup" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Champurrado_Syrup.png?alt=media&token=e77534f8-e1f6-4760-830a-275f0ec4ad54"
                        }
                        "Cheddar Cheese" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Cheese.png?alt=media&token=d29d51c7-5bf2-44e6-8af9-4d39286deece"
                        }
                        "Cheddar Corn" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Cheddar_Corn.png?alt=media&token=6e6db65a-977b-4062-9f0d-3b54cbe0cdb2"
                        }
                        "Cheddar Swirl Bread" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Cheddar_Swirl.png?alt=media&token=ec1d8137-2efe-4a4a-b4f5-ec316c142651"
                        }
                        "Cheddarwurst" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Cheddarwurst.png?alt=media&token=7076238f-f78c-4520-a53c-abc644064818"
                        }
                        "Cheese Ball Spread" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Cheesebspread.png?alt=media&token=d6650ca3-263d-4c1a-9dd8-1f34af1247bb"
                        }
                        "Cheese Cubes" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Cheese_Cubes.png?alt=media&token=f6322aac-9446-45ce-9bfa-1ab792221159"
                        }
                        "Cheesecake Crumbles" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/CheeseCakeCrumbles.png?alt=media&token=a25b72d4-d492-4c0f-bffb-afb91d349aed"
                        }
                        "Cheesecake Filling" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Cheesecake_Filling.png?alt=media&token=93149d3a-bbd6-4fdf-a2db-1ad263831322"
                        }
                        "Cheesy Bread" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Cheesy_Bread.png?alt=media&token=709e89ac-33bb-4d14-892d-1b88cf614f2f"
                        }
                        "Cheez Puff Breading" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Cheez_Puff_Breading.png?alt=media&token=546eddc6-b8e1-48b0-9c8d-0d34ed02f2c7"
                        }
                        "Cheez Puffs" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/CheezPuffs.png?alt=media&token=f28cb815-68fa-4248-b3d7-7bed06652fb3"
                        }
                        "Cheezy Whip" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Cheezy_Whip.png?alt=media&token=a766e70e-83c5-4d9a-b329-7d4472553366"
                        }
                        "Cherry" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Cherries.png?alt=media&token=d00923af-9ac6-480c-b8d8-35a8ad74c8c5"
                        }
                        "Cherry Blossom Crust" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Cherry_Blossom_Crust.png?alt=media&token=67837ba2-aab4-4e47-b305-ae342f25b83a"
                        }
                        "Cherry Bubbles" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Cherry_Bubbles.png?alt=media&token=ad655d4a-2521-4f39-91f3-4d2e973a3b62"
                        }
                        "Cherry Cheesecake Syrup" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Cherry_Cheesecake_Syrup.png?alt=media&token=40fed43d-fe9a-4f60-b2cc-6e5e611523ad"
                        }
                        "Cherry Cordial Corn" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Cherry_Cordial_Corn.png?alt=media&token=a5e4d483-1b4a-456e-b3c8-77e8de61f8e0"
                        }
                        "Cherry Cordial Cream" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Cherry_Cordial_Cream.png?alt=media&token=e713a969-69ab-48c4-a59b-c47e226bcba8"
                        }
                        "Cherry Cordial Ice Cream" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Cherry_Cordial_Ice_Cream.png?alt=media&token=bf7e1a9a-4144-4a36-b8db-aa06523dee31"
                        }
                        "Cherry Cordials" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Cherry_Cordial.png?alt=media&token=aed65292-7528-46c9-9cf1-69272c267177"
                        }
                        "Cherry Filling" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Cherry_Filling%202.png?alt=media&token=0cde6309-8843-4290-84c8-4896e4c9ccaa"
                        }
                        "Cherry Syrup" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Cherry_Syrup.png?alt=media&token=a95e9064-e9d6-4cfe-95bf-9950fef76915"
                        }
                        "Cherry Tomato" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Cherry_Tomatoes.png?alt=media&token=6a802b7c-874f-4b72-8228-c0afd9518464"
                        }
                        "Cherrybomb Drizzle" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Cherrybomb_Drizzle.png?alt=media&token=a8fa5475-e21e-428c-8ad2-525c44fe29ce"
                        }
                        "Cherrybomb Milk" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Cherrybomb_Milk.png?alt=media&token=a1574fe9-83ad-49c2-b87a-d575516dbf31"
                        }
                        "Cheung Chau Bun" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Cheung_Chau_Bun.png?alt=media&token=bbcc3703-7026-4d8f-a020-8dd9c687f9e0"
                        }
                        "Chicago Bun" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Chicago_bun.png?alt=media&token=26bcdaeb-0a4e-4599-b763-5c1824b7cb31"
                        }
                        "Chicharrones" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Chicharrones.png?alt=media&token=c3de52c1-194f-470a-98ec-850954041118"
                        }
                        "Chichilo Mole" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Chichilo.png?alt=media&token=df97d1e3-bbef-45aa-b7ef-9c4a6ef576f5"
                        }
                        "Chicken" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Chicken.png?alt=media&token=770f3cb0-a572-4b4f-92ce-6fbd12274e3d"
                        }
                        "Chicken Breast" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Chicken_Breast.png?alt=media&token=fdd5d990-b13d-4bae-8f39-7d32c86c593a"
                        }
                        "Chicken Patty" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Chicken_Patty.png?alt=media&token=589da60a-6b30-4363-a9ff-66ac1d495e70"
                        }
                        "Chicken Strips" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/ChickenStrips.png?alt=media&token=6274a58e-5a36-4d3a-8177-a32b60d6a17c"
                        }
                        "Chicken Wings" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/ChickenWings.png?alt=media&token=522995ff-621e-4f69-8326-bd26e90aaf34"
                        }
                        "Chile Serrano" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Chile_Serrano_Salsa.png?alt=media&token=f430719a-4fab-404a-910e-0161f1a4df8e"
                        }
                        "Chili" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Chili.png?alt=media&token=13523cec-36bf-4486-8d7e-05d6273002e0"
                        }
                        "Chili Lime Tortillas" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Chili_Lime_Tortillas.png?alt=media&token=5db26612-5081-4693-882e-b26419a966ed"
                        }
                        "Chili Pepper" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Chili_Pepper.png?alt=media&token=4e2ec4db-e981-43aa-87ec-563bf334f783"
                        }
                        "Chimichurri" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Chimichurri.png?alt=media&token=b998bbb4-625e-43a5-bc76-8b1d180fa040"
                        }
                        "Chipotle Cheddar" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Chipotle_Cheddar.png?alt=media&token=851a5f3b-1b30-4065-8cdf-483d4db0bf9a"
                        }
                        "Chives" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/chives.png?alt=media&token=2c467489-945f-4078-8e97-a6841e18ca16"
                        }
                        "Choco Banana Drizzle" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Choco_Banana_Drizzle.png?alt=media&token=5100f28c-cd0a-400c-843d-601b53f85130"
                        }
                        "Choco Mint Custard" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Chocomint_custard.png?alt=media&token=062c8ea9-2490-4ab3-aaa8-97a458ec93c9"
                        }
                        "Chocolate Acorn" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Chocolate_Acorn.png?alt=media&token=d2540516-d90d-433c-ad78-95e2fccb7126"
                        }
                        "Chocolate Bacon" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Chocolate_Bacon.png?alt=media&token=a7587b1e-279c-4cee-a0d3-2aeb233cddee"
                        }
                        "Chocolate Banana" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Chocolate_Banana.png?alt=media&token=4c8a7022-adf1-4f52-8abd-90c39fb7af47"
                        }
                        "Chocolate Cake" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Chocolate_cupcake.png?alt=media&token=826bce65-0310-4a7f-bfce-90f0869e4c28"
                        }
                        "Chocolate Cannoli Shell" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Chocolate_Cannoli_Shell.png?alt=media&token=6bc89480-f8d9-4c30-b00d-fa5a06dec7a1"
                        }
                        "Chocolate Chip Crust" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Chocolate_Chip_Crust.png?alt=media&token=b80207b1-5d68-4b1a-a390-1a6cadcac24b"
                        }
                        "Chocolate Chip Mix" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Chocolate_Chip_Mix.png?alt=media&token=d72f6592-a280-4a46-b255-e0a3cbe4abff"
                        }
                        "Chocolate Chips" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/ChocolateChips.png?alt=media&token=fdd76daa-44bc-4c53-b769-ce7650b8d340"
                        }
                        "Chocolate Coin" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Chocolate_Coin.png?alt=media&token=2fe14b30-ec4f-46e6-9462-1eb231730f2e"
                        }
                        "Chocolate Crumbs" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Chocolate_Crumb.png?alt=media&token=c50c7845-6c70-4dfd-81a1-fb2cfe6b18e2"
                        }
                        "Chocolate Crust" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Chocolate_Crust.png?alt=media&token=947780ae-e3f5-47ff-9d2d-62bbd9088956"
                        }
                        "Chocolate Frosting" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Chocolate_Frosting.png?alt=media&token=78934933-8125-4d58-9bff-3f3477e5d627"
                        }
                        "Chocolate Ice Cream" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Chocolate_Ice_Cream.png?alt=media&token=cb78fa18-3040-4f47-a822-3d8649a18568"
                        }
                        "Chocolate Icing" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Chocolate_Icing.png?alt=media&token=1fe7f0fa-e61b-4aa0-b5ed-f0a4dcf31986"
                        }
                        "Chocolate Meringue" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Chocolate_Meringue.png?alt=media&token=3158c12a-3f2a-4e1b-b9bc-6812adfdc64e"
                        }
                        "Chocolate Milk" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Chocolate_Milk.png?alt=media&token=0255bd1e-1ea4-4351-8ec9-999b3c0a3b66"
                        }
                        "Chocolate Mint" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Mint_Bar.png?alt=media&token=1fb54d8f-dc1a-4685-be4f-9cedcdc6ef17"
                        }
                        "Chocolate Mousse" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Chocolate_Mousse.png?alt=media&token=20d82f6a-a71f-4ade-b7bf-0e92bdd66d43"
                        }
                        "Chocolate Mousse Dollops" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Chocolate_Mousse_Dollop.png?alt=media&token=50f7f778-ad9c-49eb-978e-46a40029187d"
                        }
                        "Chocolate Popcorn" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Chocolate_popcorn.png?alt=media&token=7c55a6c9-14fe-42f7-b220-7febbfd8e0aa"
                        }
                        "Chocolate Strawberry" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Chocolate_Strawberry.png?alt=media&token=f4ecdee0-43cc-4af8-9b1c-ddbef8e78698"
                        }
                        "Chocolate Syrup" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Chocolate_Topping.png?alt=media&token=5a417b7e-38db-4838-803b-b62fb9242dbf"
                        }
                        "Chocolate Tea" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Chocolate_Tea.png?alt=media&token=0a4d2540-43dc-4c4d-9ccb-ca219f35e2bc"
                        }
                        "Chorizo" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Chorizo.png?alt=media&token=23525021-4fc6-4a29-8fc1-38b62f1bea31"
                        }
                        "Christmas Jelly Cookies" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Christmas_Jelly_Cookies.png?alt=media&token=e2b82d03-e107-4149-8afb-524315c02dda"
                        }
                        "Churro" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Churro.png?alt=media&token=f9829d6b-ac58-4754-adc8-03e1d6989170"
                        }
                        "Ciabatta" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Ciab.png?alt=media&token=8532c077-7bbc-4dae-953a-675fbcb25d8c"
                        }
                        "Cilantro Lime Soda Shell" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Cilantro_Lime_Shell.png?alt=media&token=e16ffd54-ebde-429d-a75e-e6a692b5512c"
                        }
                        "Cinco Swirls" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Cinco_Swirls.png?alt=media&token=a76d98fa-f2c2-4ac9-9ad8-31f34d7166eb"
                        }
                        "Cinnamon" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Cinnamon.png?alt=media&token=f4624539-adbb-4c22-a199-46a7ae78b092"
                        }
                        "Cinnamon Dolce Syrup" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Cinnamon_Dolce_Syrup.png?alt=media&token=54eceb82-ac19-4e8d-ab29-be92dcf56c7c"
                        }
                        "Cinnamon Roll" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Cinnamon_Roll.png?alt=media&token=06f6d70c-c3bc-44ef-bf36-f2d1c34e7846"
                        }
                        "Cinnamon Roll Cake" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Cinnamon_Roll_Cake.png?alt=media&token=51e690ea-9bef-404a-924e-5651f31a6c1f"
                        }
                        "Cinnamon Sugar" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Cinnamon_Sugar.png?alt=media&token=12188bc5-3f8e-46d0-aba9-73f761bd7fed"
                        }
                        "Cinnamon Swirl Popcorn" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/cinnamon_swirl_popcorn.png?alt=media&token=7b6e31a1-b01d-4aca-ac26-4b6d745d9a66"
                        }
                        "Cinnamon Swirl Slush" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Cinnamon_Swirl_Slush.png?alt=media&token=25e66cc5-6a09-4eca-acdb-6c092f81609f"
                        }
                        "Cinnamon Toast" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Cinnamon_Toast.png?alt=media&token=838fd360-eec6-47bb-98ff-a6eb22fb6afd"
                        }
                        "Circus Peanut Filling" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Circus_Peanut.png?alt=media&token=298c0d7f-6a6c-4ca3-9e85-6f76b529bb27"
                        }
                        "Citri-Shock Syrup" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Citri-shock_syrup.png?alt=media&token=8285c985-7bb4-4f9c-aa25-d266cc73d4bb"
                        }
                        "Citrus Zest" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Citrus_Zest.png?alt=media&token=78c3dcd5-1e68-4b12-baf0-440bba8d09ee"
                        }
                        "City Roast" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/City_Roast.png?alt=media&token=e494ba09-fe87-40b5-80bc-b1edfbe70f7e"
                        }
                        "Clams" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Clams.png?alt=media&token=de4badea-1125-47ca-a84d-f229ef399e4f"
                        }
                        "Classic Bun" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Classic_Bun.png?alt=media&token=0ebf5e5e-e2f7-4c44-bdff-9d9559c3b386"
                        }
                        "Classic Cannoli Shell" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Classic_Cannoli_Shell.png?alt=media&token=897ca4c3-4e79-4922-86bc-0c688db2bae7"
                        }
                        "Clear Glaze" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Clear_Glaze.png?alt=media&token=e618aa8f-9723-4b9c-a897-f89deb707eb6"
                        }
                        "Cloudberry" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Cloudberry.png?alt=media&token=7544c294-39ae-46e9-8576-819c47b046b8"
                        }
                        "Clover Cookie" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Clover_Cookie.png?alt=media&token=6951b99d-ac05-4df3-9178-2f123b9f26d1"
                        }
                        "Cloveroni" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Cloveroni.png?alt=media&token=75590255-3b90-4c3b-9edf-5a43fe54eec9"
                        }
                        "Cobweb Ripple" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Cobweb_Ripple.png?alt=media&token=9a472865-c1fc-44dd-b0e2-2fe57a3d90dd"
                        }
                        "Cocktail Onions" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Coctail_Onions.png?alt=media&token=5c49f037-a61b-4168-9138-5ae8febd77e3"
                        }
                        "Coco Coolada Ice Cream" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Coco_Coolada.png?alt=media&token=f193fbbc-0ab0-4697-8e6d-302bdf42df5e"
                        }
                        "Cocoa" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Cocoa.png?alt=media&token=46d3ef38-49c1-4db0-a720-b6aa3c2e369b"
                        }
                        "Cocoa Chipotle Drizzle" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Cocoa_Chipotle_Syrup.png?alt=media&token=1aa64985-d1ef-4a97-bfca-805d80db6c1e"
                        }
                        "Cocoa Powder" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Cocoa_Powder.png?alt=media&token=6ee93b90-4a11-4fe3-9d5e-93f76cff7aad"
                        }
                        "Coconut" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Coconut.png?alt=media&token=2e71c974-bbc8-4679-9196-96d35a2bc937"
                        }
                        "Coconut Crust" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/CoconutCrust.png?alt=media&token=378eb7c2-5536-4633-a424-ec600ad47395"
                        }
                        "Cod" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Cod.png?alt=media&token=d974f23f-090f-495c-8fea-fad420cdca39"
                        }
                        "Coffee" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Coffee.png?alt=media&token=736acc91-5821-4f2c-aa2c-8bc8e69cf107"
                        }
                        "Colby Jack Cheese" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Colby_Jack_Cheese.png?alt=media&token=f44a96cb-8d6a-450e-adec-f6467f33c746"
                        }
                        "Coleslaw" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Spring_Coleslaw.png?alt=media&token=6de778cb-10e6-45c4-a54d-468003a81faf"
                        }
                        "Confetti Cake" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Confetti_Cake.png?alt=media&token=db06891a-53c6-4652-9011-e74dc27a683d"
                        }
                        "Confetti Pie-Tarts" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Confetti_Pie-Tarts.png?alt=media&token=caac9ca7-2ccc-48d0-a5a7-11e4578331f6"
                        }
                        "Cookie" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Cookie.png?alt=media&token=0c0382c7-5291-42a1-a4d0-120f7d112e54"
                        }
                        "Cookie Dough" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Cookie_Dough.png?alt=media&token=1abdac4c-878c-4e59-8a8e-09d23f27f113"
                        }
                        "Cookie Dough Bits" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Cookie_Dough_Bits.png?alt=media&token=a2e691db-6d7b-4a81-8e92-28f06cbbd073"
                        }
                        "Cookie Dough Cream" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Cookie_Dough_Cream.png?alt=media&token=bea53839-5f41-4551-aba4-7f79331be91e"
                        }
                        "Cookie Dough Ice Cream" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Cookie_Dough_Ice_Cream.png?alt=media&token=4a63214d-03d2-4f12-9d18-82b79735cdbb"
                        }
                        "Cookies and Cream Ice Cream" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Cookies_and_Cream.png?alt=media&token=08a3298e-ebe0-4169-88cc-a44cfafe19dc"
                        }
                        "Corn Dog" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Corn_Dog.png?alt=media&token=c1fc7d80-e3e0-4416-a045-f325f3dccce0"
                        }
                        "Cornbread" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Cornbread.png?alt=media&token=1104af68-7b56-4f81-b6a9-475a787a5d5e"
                        }
                        "Cornbread Crust" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Cornbread_Crust.png?alt=media&token=f177c76b-2b0b-4607-850b-7475443978b5"
                        }
                        "Corned Beef" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/corned_beef.png?alt=media&token=918780a0-6fcc-4725-9e9a-b5cf90c03fb3"
                        }
                        "Corned Beef Barbacoa" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Corned_Beef_Barbacoa.png?alt=media&token=bc697830-66ce-4cbd-8479-8dcdafd2e3ce"
                        }
                        "Corned Beef Hash" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Corned_Beef_Hash.png?alt=media&token=9bee6971-4df9-4cee-a405-2049421d9561"
                        }
                        "Coronation Sauce" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Coronation_Sauce.png?alt=media&token=1d16cb2f-74ae-4654-bcc5-51beda3ea28d"
                        }
                        "Cosmic Coconut" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Cosmic_Coconut.png?alt=media&token=83b84f40-51bb-487e-8f62-75713feb5b25"
                        }
                        "Cosmo Cake" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Cosmo_Cake.png?alt=media&token=ffa58753-d935-46c8-9f51-45763567009d"
                        }
                        "Cotton Candy" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Cotton_Candy.png?alt=media&token=01f78e6a-1d62-4e40-a83c-2e8e2fff57b3"
                        }
                        "Cotton Candy Bubbles" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Cotton_Candy_Bubbles.png?alt=media&token=1b03525f-05b2-483a-8bbb-42756718c4fa"
                        }
                        "Cotton Candy Creameo" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/CottonCandyCreameo.png?alt=media&token=b9525962-5302-46ca-87e7-790bd2950a0a"
                        }
                        "Cotton Candy Icing" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Cotton_Candy_Icing.png?alt=media&token=b775a6a9-4556-4eba-ae09-c1fe36e21b71"
                        }
                        "Cotton Candy Slush" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Cotton_Candy_Slush.png?alt=media&token=ff42b53d-ae7c-4fc4-a200-609d66e37218"
                        }
                        "Cotton Candy Syrup" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Cotton_Candy_Drizzle.png?alt=media&token=52e4fa1f-5ad8-46c8-842a-0889bd336a0c"
                        }
                        "Cotton Puffs" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/cotton_puffs.png?alt=media&token=00830c8f-5c26-483e-ba25-785db23b6159"
                        }
                        "Countdown Candies" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Countdown_Candies.png?alt=media&token=60930125-9c26-4b14-bcd4-3d5258e49ced"
                        }
                        "Countdown Crunch" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/CountdownCrunch.png?alt=media&token=21d7e3f5-468c-4bc5-b09a-a08e17cbd668"
                        }
                        "Country Gravy" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Country_Gravy.png?alt=media&token=54f37455-351a-4e44-8701-11b7a18a17f8"
                        }
                        "Country Steak" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Country_Steak.png?alt=media&token=bd19a64a-1af8-415c-bd36-f060f2f5085c"
                        }
                        "Crab Mezzelune" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Crab%20Mezzelune.png?alt=media&token=268adb1b-b265-4415-9a62-86dbd523f3a0"
                        }
                        "Crab Stick" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Crab_Stick.png?alt=media&token=9f41e939-c41d-4d63-a14c-8c1b90007b9d"
                        }
                        "Crackle Crumbs" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Crackle.png?alt=media&token=1f39aadf-f5a9-47dd-b04e-5a887b5990a5"
                        }
                        "Cranberry Chutney" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Cranberry_Chutney.png?alt=media&token=baa2a4bc-c8ce-4b14-9769-7e59e3d7ed0e"
                        }
                        "Cranberry Juice" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Cranberry_Juice.png?alt=media&token=7d059a19-f274-405f-93b5-dc7f542bc0e5"
                        }
                        "Cranberry Sauce" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Cranberry_sauce.png?alt=media&token=62bdf6e0-92e5-4b0d-bcdf-a20526b42fa3"
                        }
                        "Crater Crust" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/crater_crust.png?alt=media&token=fc33757a-41b4-4d9d-9e0b-66295e2edb89"
                        }
                        "Crawdad" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Crawdads.png?alt=media&token=54615a5f-8b49-4854-9ffa-920396a74b14"
                        }
                        "Cream" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Cream.png?alt=media&token=b7a19dfa-1235-4ecd-9260-e38b6b66825d"
                        }
                        "Cream Cheese" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Cream_Cheese.png?alt=media&token=bb9f4700-b91e-4cc9-934d-ee2b835d3064"
                        }
                        "Cream Soda Slush" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Cream_Soda.png?alt=media&token=d01a07bb-3d30-431f-9f04-d16732038098"
                        }
                        "Creameo" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Creameos.png?alt=media&token=c4061536-3445-4bf0-92ed-ba233fd0201a"
                        }
                        "Creameo Bits" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Creameo_Bits.png?alt=media&token=6d5dc217-7fe4-4307-9974-1d53a88e8182"
                        }
                        "Creameo Cream" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Creameo_Cream.png?alt=media&token=d2e643dc-9bdf-48e0-bc3f-43c3a1212c1b"
                        }
                        "Creameo Crust" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Creameo_Crust.png?alt=media&token=5dc016f0-528c-4215-ab77-0ec064bdc709"
                        }
                        "Creameo Drizzle" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Creameo_Drizzle.png?alt=media&token=3baff081-815e-4005-a257-b7e96b850b8d"
                        }
                        "Creamy Alfredo Sauce" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Creamy_Alfredo.png?alt=media&token=8d3a0391-0a88-481d-b3a6-adfa7c8dfc5c"
                        }
                        "Creamy Cole Slaw" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Creamy_Cole_Slaw.png?alt=media&token=6337f12a-6455-46fd-bf3a-d86db1db0a0f"
                        }
                        "Creamy Garlic Sauce" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/CreamyGarlicSauce.png?alt=media&token=adf4b655-24b0-4664-acdd-e17c71fdd044"
                        }
                        "Creamy Pistachio Sauce" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Creamy_Pistachio_Sauce.png?alt=media&token=2a452c5f-4acf-4d4e-90cb-1906964aa21e"
                        }
                        "Creamy Tomatillo Sauce" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Creamy_Tomatilo.png?alt=media&token=bfa53552-4c17-45b5-960e-dcbf4b3968ca"
                        }
                        "Creme Brulee Syrup" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Creme.Brulee.png?alt=media&token=b276160c-b457-44e9-9b93-9d7a141eb197"
                        }
                        "Cremebury Egg Ice Cream" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Cremebury_Egg_Ice_Cream.png?alt=media&token=21a68eaa-93b1-40a9-8526-6b074dfc73ce"
                        }
                        "Cremebury Eggs" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/CremeburyEggs.png?alt=media&token=0f4f782f-73bf-43ce-8e83-20f8d9f46566"
                        }
                        "Creole Crab Dip" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Creole_Crab.png?alt=media&token=13ca4653-6707-42f5-ba97-cf5ee26409c3"
                        }
                        "Creole Rub" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Creole_Rub.png?alt=media&token=a2aa9a51-41c3-4da5-a224-1febbd7b15b0"
                        }
                        "Crescent Roll" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Crescent_Roll.png?alt=media&token=e8b949d7-35a4-4d4d-9cf0-78dc15624683"
                        }
                        "Crimson and Clove" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Crimson_and_Clove.png?alt=media&token=5c862a88-bdb8-419a-8bd7-164a5f615f01"
                        }
                        "Crinkle Cut Fries" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Crinkle_Cut_Fry.png?alt=media&token=61dd92a0-317b-4ec9-b720-4cb40b210221"
                        }
                        "Crispy Crust" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Crispy_Crust.png?alt=media&token=67d149a5-10fd-47dc-8bb3-4c2909fde3d1"
                        }
                        "Crossbone Bread" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Crossbone_Bread.png?alt=media&token=e0169f14-04b7-4199-ab76-24b4d6ed6130"
                        }
                        "Crushed Candy Canes" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Crushed_Candy_Canes.png?alt=media&token=168268b8-9b20-4156-9bfa-b98d1ebc344a"
                        }
                        "Crushed Coutons" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Crushed_Croutons.png?alt=media&token=4633286f-7abf-4d1c-8037-2b1ff96a4e23"
                        }
                        "Crushed Ice" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Crushed_Ice.png?alt=media&token=b365d6ea-026d-480a-8c85-db00de9c386d"
                        }
                        "Crushed Wafers" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Crushed_wafer.png?alt=media&token=7d4ea8d4-5b7b-4b71-9bf8-4ecbb315a25e"
                        }
                        "Crushida Pepper" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Crushida_Pepper.png?alt=media&token=9949bee4-030c-4422-848a-9fcbd0e1c3d3"
                        }
                        "Cucumber" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Cucumber_Slices.png?alt=media&token=ba25f0d2-f363-48aa-a1b7-c9407e77c568"
                        }
                        "Cucumber Bubbles" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Cucumber_Bubbles.png?alt=media&token=c8d24dc6-f7b5-481d-ad8a-f88a5cbfb583"
                        }
                        "Cupid Cannoli Shell" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Cupid_Cannoli_Shell.png?alt=media&token=84d114df-c6cf-4890-b1e0-9926068f4c6c"
                        }
                        "Cupidberry Derps" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/CupidberryDerps.png?alt=media&token=4a12ff7b-478a-4ee7-9a57-8c42c28d109e"
                        }
                        "Curly Fries" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Curly_Fries.png?alt=media&token=4871ed57-0ac8-4318-a3af-5505cd28b2b6"
                        }
                        "Curry Powder" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Curry_Powder.png?alt=media&token=31cd171e-a4a3-4a61-a260-d271e244ff84"
                        }
                        "Curveball Crunch" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Curveball_Crunch.png?alt=media&token=04917cb7-72a4-4da5-8f03-6068bfc206c9"
                        }
                        "Dark Blue Frosting" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Dark_Blue_Frosting.png?alt=media&token=5f127462-6677-4db2-b7d3-25d226fbb497"
                        }
                        "Datemaki" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Datemaki.png?alt=media&token=c7dad735-d318-4154-8539-a9e357898d69"
                        }
                        "Decaf" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Decaf_Roast.png?alt=media&token=4907afac-7ba3-42b1-a377-c36de3618a8a"
                        }
                        "Deep Purple Frosting" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Deep_Purple_Frosting.png?alt=media&token=29243c3d-3b13-4295-bfea-9a4383566dce"
                        }
                        "Deep-Fried Pickles" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/deep-fried_pickles.png?alt=media&token=a9c13735-ac33-481e-a549-19670e7afe91"
                        }
                        "Deli Soy Paper" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Deli_Soy_Paper.png?alt=media&token=d68d2879-58f3-4feb-90ea-995bff24a78a"
                        }
                        "Diced Green Chiles" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Diced_Green_chills.png?alt=media&token=6815ffed-52bf-4eaf-9fb8-4f2fe55a063b"
                        }
                        "Diced Habaneros" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Diced_Habanero.png?alt=media&token=90980fae-d2be-4a9b-8134-fc4680526030"
                        }
                        "Diet Fizzo" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Diet_Fizzo.png?alt=media&token=1499b674-8f66-4a70-9125-f629c84dfb7e"
                        }
                        "Dipped Pretzel" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/DippedPretzels.png?alt=media&token=bd197a5f-d687-4e20-81f4-2689a6ab35e0"
                        }
                        "Dipped Strawberry" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Dipped_strawberry.png?alt=media&token=ba4f13b6-768f-4c60-8036-7ff203346105"
                        }
                        "Dirt Cake Shell" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Dirt_Cake_Shell.png?alt=media&token=9368a5a4-4508-4c4b-8c2d-798248a83459"
                        }
                        "Doberge Drizzle" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Doberge_Drizzle.png?alt=media&token=3533bfbe-e290-4157-a0f5-6628527f7dfb"
                        }
                        "Donut Pieces" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Donut_Pieces.png?alt=media&token=d2eba482-2496-40cb-bb87-a66fa04ce76e"
                        }
                        "Doppelbock Sauce" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Doppelbock_Sauce.png?alt=media&token=1c38ab7f-1aa7-48a6-bf77-bda10a6f4770"
                        }
                        "Dr Cherry" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Dr._Cherry.png?alt=media&token=ac8cac7b-7d45-452c-b284-22c104ffd011"
                        }
                        "Dr Cherry Drizzle" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Dr._Cherry_Dri.png?alt=media&token=815469a0-0bde-4eab-982b-97b6ff4f48b3"
                        }
                        "Dr Cherry Powder" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Dr._Cherry_Powder.png?alt=media&token=eb45e10e-00cc-4fa2-85e4-18151db8807c"
                        }
                        "Dr Dasher" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Dr._Dasher.png?alt=media&token=16044ac6-92d4-4c06-aba1-e2a0df830c2a"
                        }
                        "Dr Dasher Slush" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Dr._Dasher_Slush.png?alt=media&token=759254ff-da7a-4142-8d0b-52b1c442cb88"
                        }
                        "Dragonfruit Filling" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Dragonfruit_Division.png?alt=media&token=7c5dc6fc-b727-4f85-aa4b-1a11306cec4b"
                        }
                        "Dragonfruit Slush" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Dragonfruit_Slush.png?alt=media&token=70ff5a37-a12d-4dca-b24e-3e0a157a5faa"
                        }
                        "Dream Cream Soda" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Dream_Cream_Soda.png?alt=media&token=7b91e0f9-b5db-4c78-8f4e-0bb6ff128e18"
                        }
                        "Dreamsicle Syrup" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Dreamsicle_drizzle.png?alt=media&token=22e41c4d-ba32-4644-bca7-06d3e4fc8dba"
                        }
                        "Dried Jackfruit" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Dried_Jackfruit.png?alt=media&token=f1664b93-c9cb-4b6d-925a-8d27409e8c42"
                        }
                        "Dried Kiwis" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Dried_Kiwi.png?alt=media&token=54e68600-606e-4ba4-be7f-70f65259fc00"
                        }
                        "Dual Licorice Drizzle" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Dual_Licorice_Drizzle.png?alt=media&token=f4adbf4a-f0ea-49d8-972c-01f0f38ed3e6"
                        }
                        "Dual Licorice Filling" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Dual_Licorice_Filling.png?alt=media&token=0307aadd-fa6c-4e65-82b3-d9e45d090f44"
                        }
                        "Dual Licorice Whip" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Dual_Licorice_Whip.png?alt=media&token=7f0c7fd4-df44-4a7e-8f33-09d1c8924d9e"
                        }
                        "Duck Sauce" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Duck_Sauce.png?alt=media&token=1aff0f8b-536c-4d27-9b27-4992e405c38e"
                        }
                        "Ecto Bread" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Ecto.png?alt=media&token=40a16e4e-7f41-43f3-972a-0f59e030be57"
                        }
                        "Ecto Sauce" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Ecto_Sauce.png?alt=media&token=83fce95d-7dd0-4b1d-a836-a5eb8d3a39e8"
                        }
                        "Ecto Stuffed Crust" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Ecto_Crust.png?alt=media&token=0bd9abcb-cc64-41fc-a6f6-419ab8b015e5"
                        }
                        "Egg Cutter" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/EggDonut.png?alt=media&token=78426e3c-af98-45ff-a1d4-01759b6d9ae9"
                        }
                        "Egg Waffle Shell" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Egg_Waffle_Shell.png?alt=media&token=a77cbed4-b5cf-4a67-8d66-f556a7aaa614"
                        }
                        "Eggnog" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Eggnog.png?alt=media&token=93cf8fce-11e2-4c55-8c92-dfbc5766eaf9"
                        }
                        "Eggnog Alfredo" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Eggnog_Alfredo.png?alt=media&token=be99db7c-b856-4513-8632-6b526b69eddc"
                        }
                        "Eggnog Alioli" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Eggnog_Aioli.png?alt=media&token=14310b18-c391-41e5-9908-1d46af241e1e"
                        }
                        "Eggnog Cream" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Eggnog_Cream.png?alt=media&token=119a843d-52e5-401d-9c44-4aae5f37bd16"
                        }
                        "Eggplant" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Eggplant.png?alt=media&token=91a2aef2-81ac-4a50-83e6-12f39ce23a32"
                        }
                        "Eiskaffee" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Eiskaffee.png?alt=media&token=a45aa0a4-8d7a-4c94-b705-93dfe69d9387"
                        }
                        "Elf Soy Paper" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Elf_Soy_Paper.png?alt=media&token=624bfb90-6999-4e9a-a9bd-88183ca12b43"
                        }
                        "Elf Sugar" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Elf_Sugar.png?alt=media&token=22ad8d7f-a82b-4d45-80c9-7d316f0876ad"
                        }
                        "Enchilada Sauce" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Enchilada_Sauce.png?alt=media&token=cbafeedd-ca7b-4305-ba5b-7bba8b7bd6be"
                        }
                        "English Breakfast Tea" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/English_Breakfast_Tea.png?alt=media&token=98ef2015-5c40-475d-a2a7-d2e8d51403f5"
                        }
                        "Espresso Syrup" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Espresso_Drizzle.png?alt=media&token=1ddd4da6-3e79-44d2-9251-c151ca50e075"
                        }
                        "Fajita Peppers" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Fajita_Peppers.png?alt=media&token=4187aee8-26c0-48b9-87fb-df0dd58954ec"
                        }
                        "Far Out Shell" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Far_Out_Shell.png?alt=media&token=61f44e87-2475-4de0-834c-adfb951b3f1b"
                        }
                        "Feather Cookie" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Feather_Cookie.png?alt=media&token=81a1a2a6-1d3c-4777-bcd4-d334af6baf48"
                        }
                        "Festive Flag" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Festive_Flag.png?alt=media&token=c5d72ef4-9a1f-42b9-b961-7c0644029587"
                        }
                        "Festive Rotini" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Festive_Rotini.png?alt=media&token=875efcb0-a549-4b67-800d-d9a84893be49"
                        }
                        "Festive Swirl Icing" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/FestiveSwirlIcing.png?alt=media&token=44b6632a-d324-4043-8cf2-df76a9082370"
                        }
                        "Feta Cheese" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Feta.png?alt=media&token=afc9afb2-e864-4003-b8ba-ae44bafa3839"
                        }
                        "Fettuccine" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Fettuccine.png?alt=media&token=4eb11c13-d840-4c87-a3ce-41ed2ca9fb37"
                        }
                        "Fiesta Chips" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Fiest.png?alt=media&token=89804c71-6647-4f24-8d9b-ca2410423016"
                        }
                        "Film Reel Crust" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Film_Reel_Crust.png?alt=media&token=c62ca692-0431-4d0e-8e40-b23b9c504072"
                        }
                        "Fiori Risoni" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Fiori_Risoni.png?alt=media&token=54dad4cc-1921-43fc-8700-88db60df5d90"
                        }
                        "Fire Tortilla Strips" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Firetortilla.png?alt=media&token=9c061bbe-9f33-4572-a52c-4863260c1bcb"
                        }
                        "Fish Filet" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Fish_Filet.png?alt=media&token=409d4574-66b2-44ef-986e-59aecad6df6b"
                        }
                        "Fizzo" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Fizzo.png?alt=media&token=646822ef-1d1f-42d2-b097-9b9fe55ed3a7"
                        }
                        "Fizzo Gold" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Fizzo_Gold.png?alt=media&token=1845c9b0-60da-462a-a192-3a9815bd5fc5"
                        }
                        "Fizzo Quartz" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Fizzo_Quartz.png?alt=media&token=00bb144b-2c72-4686-ac0b-ecc680bd629d"
                        }
                        "Flatbread" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Flatbread.png?alt=media&token=36609ce9-3b85-458d-843a-5a1e703a4828"
                        }
                        "Flavor X" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/FlavorXDrizzle.png?alt=media&token=1871dad8-3c80-4f16-8444-17ea27fff604"
                        }
                        "Fleur de Lis Sprinkles" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Fleur_De_Lis_Sprinkles.png?alt=media&token=3e4ee324-465d-448b-b293-f441c6b0cf81"
                        }
                        "Flower Bloom Crust" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Flower_Bloom_Crust.png?alt=media&token=1235916f-7b59-4c8b-b459-07648e74ac96"
                        }
                        "Focaccia" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Focaccia.png?alt=media&token=f76dcafd-688b-4b23-90c8-94ca60b0d978"
                        }
                        "Forest Green Frosting" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Forest_Green_Frosting.png?alt=media&token=b938079d-cdb9-44c9-89a7-b57fdd58b7df"
                        }
                        "Frankennoli" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Frankennoli.png?alt=media&token=c467f65d-38db-4598-b3b9-a2aef0664bd3"
                        }
                        "French Cruller" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/French_Cruller.png?alt=media&token=f9ab6a7f-9fc3-4802-8baf-8a4cea0638a4"
                        }
                        "French Fries" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/French_Fries.png?alt=media&token=0c793d20-60cc-429a-84a8-9568be2bccc1"
                        }
                        "French Onion Dip" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/French_Onion.png?alt=media&token=3a3e0a93-5b6c-4950-8c05-e1a098bd92b4"
                        }
                        "French Roast" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/French_Roast.png?alt=media&token=0a28426b-d9d8-46ac-a38f-89c55d96a546"
                        }
                        "French Toast" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/French_Toast.png?alt=media&token=516493b2-ffd3-4c6b-89e1-49a96d7ab024"
                        }
                        "French Toast Bun" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/French_Toast_Bun.png?alt=media&token=efcfd4cd-a34d-4c3f-a242-6e3e2afb9fbe"
                        }
                        "Fresh Garlic" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/FreshGarlic.png?alt=media&token=18fdfbc4-1228-478d-a123-bf05f808ae0b"
                        }
                        "Fried Calamari" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Fried_Calamari.png?alt=media&token=a4b24e3c-eaa9-411b-b0fa-ba25374281ff"
                        }
                        "Fried Chicken Bun" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Fried_Chicken_Bun.png?alt=media&token=3391c779-15e4-47ce-86ea-564bcaca8515"
                        }
                        "Fried Crispy Noodles" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/FriedCrispy.png?alt=media&token=aca32500-c2d0-487f-9dec-c9a802912066"
                        }
                        "Fried Egg" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/fried_egg.png?alt=media&token=dfb359d2-b0dd-4852-914b-df9ca42381c4"
                        }
                        "Fried Onion Strings" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Fried_Onion_Strings.png?alt=media&token=f77cd55b-00df-4f1a-a4ab-46b94a875178"
                        }
                        "Fried Ravioli" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Fried_Ravioli.png?alt=media&token=6f4b1aca-9d90-44da-a14b-930cf194983d"
                        }
                        "Frostcap Crunch" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Frostcap_Crunch.png?alt=media&token=72f1c355-ae7a-48e3-beb9-a8587aea6bf3"
                        }
                        "Frostcaps" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Frostcaps.png?alt=media&token=7349f17a-5492-42fe-8ce1-1d528728afbc"
                        }
                        "Frosted Flower" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Frosted_flower.png?alt=media&token=2f30fbe5-8231-4570-a8ae-6e626f54d5d5"
                        }
                        "Frosted Onion" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Frosted_Onion.png?alt=media&token=14ced217-66a4-42b9-8f98-e520d4fb924f"
                        }
                        "Frosted Rose" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Frosted_Rose.png?alt=media&token=a40433d1-85c6-4837-a4ea-c18b91d32b83"
                        }
                        "Frosted Sugar Crunch" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Frosted_Sugar_Crunch.png?alt=media&token=d9475a70-76b1-425e-9690-ea61c36bd430"
                        }
                        "Frosted Wreaths" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Frosted_Wreath.png?alt=media&token=bdf6bdfe-4b89-4a99-9155-cbb6da2d758b"
                        }
                        "Fruitcake" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/fruitcake.png?alt=media&token=9f3f3bf1-3c54-4ff2-8274-38c69e372c5c"
                        }
                        "Fruitcake Bun" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Fruitcake_Bun.png?alt=media&token=9f790338-5ab0-4a03-a3dd-8aa898c08690"
                        }
                        "Fruity Hoops" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Fruity_Hoops.png?alt=media&token=ef5b2f30-8496-4a3f-8b5b-9a96e899f69a"
                        }
                        "Fry Seasoning" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Fry_Seasoning.png?alt=media&token=980173b5-5f8f-4573-9c4d-5b2cf2b7794a"
                        }
                        "Frybread" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Frybread.png?alt=media&token=498f416a-389e-49d4-ade5-89e4c610e187"
                        }
                        "Fudge Brownie" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Fudge_Brownie.png?alt=media&token=fa4f9d35-e49f-4bf8-b29c-673e6cefad09"
                        }
                        "Fudge Cookie" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Fudge_Cookie.png?alt=media&token=8d450c05-d4c2-4b61-8811-dd45af68ade2"
                        }
                        "Fudge Filling" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Fudge_Filling.png?alt=media&token=6ff11f16-9d5c-4967-864b-452fbd21d1e3"
                        }
                        "Fudge Swirl Drizzle" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Fudge_Swirl_Drizzle.png?alt=media&token=b353ee76-ecca-4f14-96e2-d857a1019ac9"
                        }
                        "Full Moon Icing" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Full_Moon_Icing.png?alt=media&token=3d581537-7b41-4d32-8c7e-0508e2b0421b"
                        }
                        "Funnel Cake Shell" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Funnel_Cake_Shell.png?alt=media&token=c6061794-a8fd-4606-879c-a2554cabb7c3"
                        }
                        "Furikake" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Furikake.png?alt=media&token=c2297569-aa58-4da6-b710-cc7170918958"
                        }
                        "Galaxy Grape Slush" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Galaxy_Grape_Slush.png?alt=media&token=bfde8f0b-ace0-4a11-9cb5-331d95ea967d"
                        }
                        "Galaxy Grape Tea" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Galaxy_Grape_Tea.png?alt=media&token=d571b561-cf5b-4ccb-8418-6359b9ecd484"
                        }
                        "Garlic Basil" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Garlic_Basil.png?alt=media&token=d8ef0b1c-fc77-4b4e-b8c8-9636044794a4"
                        }
                        "Garlic Breadstick" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Garlic_Breadstick.png?alt=media&token=24222b04-fe76-4169-b81c-50ffa2e1d631"
                        }
                        "Garlic Chipotle Dip" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Garlic_Chipotle.png?alt=media&token=d33188ad-27cf-49b1-917e-e01d946d70d5"
                        }
                        "Garlic Knot Crust" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Garlic_Knot_Crust.png?alt=media&token=5a7a87f1-9aeb-4884-8b07-2754be5f0c6e"
                        }
                        "Garlic Rush" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Garlic_Rush.png?alt=media&token=85e07db2-63ab-425f-bea4-010bf5b2fa87"
                        }
                        "Garlic and Olive Oil Piada" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Garlic_and_oop.png?alt=media&token=65542e22-61a7-4587-8b27-7bff04d2018b"
                        }
                        "Gator Bites" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Gator_Bites.png?alt=media&token=061e808c-bac2-4ece-8724-114239a41bb0"
                        }
                        "Gebrannte Mandeln" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Gebrannte_Mandeln.png?alt=media&token=7ccdc98a-28e7-42be-aaba-04de6f8dbb6a"
                        }
                        "General Tso Sauce" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/General_Tso_Sauce.png?alt=media&token=bf671538-c024-4530-88b3-393e0a66d301"
                        }
                        "Ginger Haze" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Ginger_Haze.png?alt=media&token=f48c7279-915f-4499-b362-1bba64360628"
                        }
                        "Ginger Haze Syrup" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Ginger_Haze_Syrup.png?alt=media&token=ed51a8be-5932-4616-ac20-a7abc4c7c730"
                        }
                        "Ginger Miso Sauce" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Ginger_Miso_Sauce.png?alt=media&token=ed121e53-5dfb-4820-bc96-baafc97b4f69"
                        }
                        "Ginger Spice Cheese" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/ginger_spice_cheese.png?alt=media&token=a20d92e3-f195-43f3-9aa1-4fd5974dcaa5"
                        }
                        "Gingerbread Cookie" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Gingerbread_Cookie.png?alt=media&token=752f8d54-5845-4225-8ca0-22a1e166a3a8"
                        }
                        "Gingerbread Man" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Gingerbread_Man.png?alt=media&token=f038ed35-543e-4854-8ef5-718d26e6393e"
                        }
                        "Gingerbread Shell" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Gingerbread_Shell.png?alt=media&token=f38a9b27-58b7-4acd-96e4-92a62a7fa833"
                        }
                        "Gingersnap Crust" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Gingersnap_Crust.png?alt=media&token=5db1f89b-6e94-4738-8b2b-3926b2d4e3bb"
                        }
                        "Gnocchi" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Gnocchi.png?alt=media&token=4bc14b2b-33da-4f38-b868-0c4182c21046"
                        }
                        "Gochujang Sauce" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Gochujang_Sauce.png?alt=media&token=78430df1-5799-4dcd-9e16-ae3fd19cf3cd"
                        }
                        "Gold Rush Powder" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Gold_Rush_Powder.png?alt=media&token=bbc2d1c2-b1eb-45fe-a948-406460d23835"
                        }
                        "Golden Age Icing" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Golden_Age_Icing.png?alt=media&token=d9f51a34-bde4-4edd-a62b-5c5d26c740a1"
                        }
                        "Golden Age Syrup" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Golden_Age_Syrup.png?alt=media&token=31c8c0bc-9f63-4f7c-8bf6-9f051e04b777"
                        }
                        "Golden CinnaMunchies" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/GoldenCinnaMunchies.png?alt=media&token=3d2b7126-7d3f-41d8-b3ff-9b503bf7ecd4"
                        }
                        "Golden Milk" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Golden_milk.png?alt=media&token=8d4da5fd-c43a-4436-beae-57fdcff7d77e"
                        }
                        "Goose" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Goose.png?alt=media&token=1191c638-ecec-48f7-8ec6-7f0571bd8736"
                        }
                        "Gorgonzola" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Gorgonzola.png?alt=media&token=d0a0aca3-0407-47e2-94ec-49b14983d967"
                        }
                        "Gouda Ghost" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Gouda_Ghosts.png?alt=media&token=7e67eaea-643f-4073-a710-04d7a21101d6"
                        }
                        "Graham Cracker Crust" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Graham_Cracker_Crust.png?alt=media&token=4ef74f20-898e-48a4-9e48-079ee31afebe"
                        }
                        "Grape Jelly Cookies" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Grape_Jelly.png?alt=media&token=7f86fccb-20d9-4af5-937f-d8d52e5d79cd"
                        }
                        "Grated Mozzarella" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Grated_Mozarella.png?alt=media&token=a83f0e78-2102-487e-8910-ce3d3b863c07"
                        }
                        "Grated Parmesan" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Grated_Parmesan.png?alt=media&token=81f184e3-e9da-4577-bef6-aa2047e4ff29"
                        }
                        "Gravy" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Gravy.png?alt=media&token=3b5c4772-b3dc-4ac6-9855-a1b1b1035d2a"
                        }
                        "Green Emerald Cake" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Greenemerald.png?alt=media&token=ac5b1dba-b18e-403e-a186-511e0d0e8523"
                        }
                        "Green Emerald Cream" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Green_Emerald_Cream.png?alt=media&token=a4eb6619-1e83-47e3-bc18-900972e96c2c"
                        }
                        "Green Emerald Icing" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Green_Emerald_Icing.png?alt=media&token=2eebf451-623a-4547-a676-6654ce627ccc"
                        }
                        "Green Frosting" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Green_Frosting.png?alt=media&token=078d2865-a072-4e37-9b35-873be6edd3f6"
                        }
                        "Green Onions" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Green_Onions.png?alt=media&token=9ec78fbc-d417-40bc-8042-556d34eac7cb"
                        }
                        "Green Pepper" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Green_Peppers.png?alt=media&token=02255a6a-d42a-4dbb-b1a4-8ba5ade31c2b"
                        }
                        "Grilled Plantains" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Grilled_Plantains.png?alt=media&token=8ebdda3a-ff19-4f89-8f54-144162c11615"
                        }
                        "Grilled Polenta" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Grilled_Polenta.png?alt=media&token=0495ec34-5e6c-409e-a0c3-8ee928ae4e70"
                        }
                        "Grilled Portobello Cap" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Grilled_Portobello_Cap.png?alt=media&token=023eaf5e-29bd-449e-adbb-e8b4e1b80974"
                        }
                        "Ground Beef" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Ground_beef.png?alt=media&token=9b71c9b8-fc18-4dee-9ed4-0f31ecddb30f"
                        }
                        "Ground Nutmeg" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Ground_Nutmeg.png?alt=media&token=97f90e9b-93d8-402d-bc5a-2ece93448043"
                        }
                        "Gruyere Cheese" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Gruyear.png?alt=media&token=ca9be05b-5b85-47df-88df-ebec2caedf60"
                        }
                        "Guacamole" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Guacamole.png?alt=media&token=80dada61-568a-41f5-9dee-f55d156d6692"
                        }
                        "Guava Rolls" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Guava_Rolls.png?alt=media&token=7881d6f6-d346-4bef-8a66-fffc636763d5"
                        }
                        "Gummy Kraken" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/GummyKraken.png?alt=media&token=fb34f14a-97fb-4f0d-a110-1bd33f2bb88d"
                        }
                        "Gummy Onion" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/GummyOnion.png?alt=media&token=e0a513a6-96dd-4f53-8ed0-bb3e3f889045"
                        }
                        "Gummy Pineapple" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Gummy_Pineapple.png?alt=media&token=08dcd36b-54d7-4c84-9594-6b50883559ec"
                        }
                        "Gummy Spider" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Gummy_Spider.png?alt=media&token=5bf49df6-36ff-4f5d-b170-7b3c7add86e5"
                        }
                        "Gummy Worm" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Gummy_Worm.png?alt=media&token=de918ee6-e197-4833-a0fd-77a6e502fb11"
                        }
                        "Gyro Meat" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Guro_meat.png?alt=media&token=fa356d54-15c7-4aad-a8cd-9833ef137feb"
                        }
                        "Hakuto Ice Cream" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Hakuto_Ice_Cream.png?alt=media&token=3f3bb478-1bc6-4234-b57e-73954e4e7bf5"
                        }
                        "Hakuto Jelly" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Hakuto_Jelly_Donuteria.png?alt=media&token=a89b9a3f-53cb-4e58-b972-11fabc9f258f"
                        }
                        "Hakuto Tea" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Hakuto_Tea.png?alt=media&token=75712a61-fd84-4347-8411-d4c382530117"
                        }
                        "Ham" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Ham.png?alt=media&token=d5dc9d13-5504-495b-99fe-0925bbd97b73"
                        }
                        "Hamburger Patty" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Papa's_Burgeria_-_Medium_Burger.png?alt=media&token=1de5e0d3-1e2d-48f7-83a4-526969978480"
                        }
                        "Hard Shell" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Hard_Taco.png?alt=media&token=ca7ae5cd-4f23-4904-a27e-bfa5502b3402"
                        }
                        "Harvest Leaf Cookies" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Harvest_Leaf_Cookie.png?alt=media&token=3372932d-302f-43c8-8910-324cc10e8283"
                        }
                        "Harvest Stripe Cookie" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Harvest_Stripe_Cookie.png?alt=media&token=42915018-0e91-47af-9828-6043e9ef35b0"
                        }
                        "Harvest Stripe Shell" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Harvest_Stripe_Shell.png?alt=media&token=972fa774-b461-474a-8aeb-fea0a729ae31"
                        }
                        "Harvest Tortellini" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Harvest_Tortellini.png?alt=media&token=56222448-d073-469e-8ff5-02f21a8dbf82"
                        }
                        "Hash Brown Patty" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Hash_Brown_Patty.png?alt=media&token=fefe557a-5bf3-45b7-a73d-c143d6fc715d"
                        }
                        "Hash Browns" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Hashbrowns.png?alt=media&token=bc6d0c0b-d005-4add-949b-dd10ecb25c78"
                        }
                        "Havarti Cheese" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Havarti.png?alt=media&token=69a9b80d-164b-4d68-a11d-d634dd0bf0c6"
                        }
                        "Hawaiian Bun" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Hawaiian_Bun.png?alt=media&token=fac4f12a-159c-4b74-826e-7b9c03786cd9"
                        }
                        "Hawaiian Roll" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Hawaiian_Roll.png?alt=media&token=c950493b-8758-4a08-8245-c48277805db1"
                        }
                        "Haystack" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Haystack.png?alt=media&token=45c89439-ae9f-4d47-a4b3-523959d9b00d"
                        }
                        "Hazelnut Swizzle" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/HazelNut.png?alt=media&token=9981f47c-e8ac-409d-ae85-b32c8df4e33c"
                        }
                        "Heart Cookies" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Heart_Cookie.png?alt=media&token=63b7a381-9dc8-4f2a-8fae-f713fd30ba32"
                        }
                        "Heart Crust" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Heart_Crust.png?alt=media&token=157f8614-51ab-43e4-be39-e852c503f6d2"
                        }
                        "Heart Cutter" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Heart_Cutter.png?alt=media&token=6579f857-dfe3-493d-84d7-eb0275f44460"
                        }
                        "Heartbeet Arrabbiata" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Heartbeet_Arrabbiata.png?alt=media&token=87f25689-c38c-43c3-9c9a-128c7cd445f5"
                        }
                        "Hibachi Sauce" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Hibachi_Sauce.png?alt=media&token=7f615b97-6d30-425c-901a-b7a8e16ad211"
                        }
                        "Hibiscus Powder" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Hibiscus_Powder.png?alt=media&token=0b0ef89f-7b8d-48de-817e-438a30cdbb7e"
                        }
                        "Hoagie Roll" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Hoagie_Roll.png?alt=media&token=ee128956-ab51-43ab-b3e9-2062cffff1fd"
                        }
                        "Hog Wings" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/HogWings.png?alt=media&token=7dbfc456-7464-441a-ad1e-6db76c734265"
                        }
                        "Hokey Pokey" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Hokey_Pokey.png?alt=media&token=9cb3c828-15ee-40c2-b153-d5222c28d4bb"
                        }
                        "Hokkigai" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Hokkigai.png?alt=media&token=dc7bd30b-fe09-420c-a8f3-840f4416a8f0"
                        }
                        "Holi Sugar" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/HoliSugar.png?alt=media&token=7726fd78-019c-40ef-8dca-efa16f933a55"
                        }
                        "Holiday Yum n'Ms" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Holiday_Yum_n'_ms.png?alt=media&token=388aedf2-e938-4df3-8190-f943ef84d7ca"
                        }
                        "Hollandaise Sauce" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Hollandaise.png?alt=media&token=66c7f8d3-0a27-4d8f-bc55-ff62b1140f9e"
                        }
                        "Holly Jolly Cake" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Holly_Jolly_Cake.png?alt=media&token=06d3040b-72e7-49b6-9f59-a5d169af1460"
                        }
                        "Holly Tartz" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Holly_Tarts.png?alt=media&token=106a802a-d8ba-4b14-9ecb-889171982d62"
                        }
                        "Hollywood Bun" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Hollywood_Bun.png?alt=media&token=938611a7-d839-43e6-981e-cfa5ecd2189c"
                        }
                        "Home Fries" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Home_Fries.png?alt=media&token=cb964790-d5c9-4df7-988a-8a712df051f2"
                        }
                        "Honey" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Honey.png?alt=media&token=ce0bc08a-e311-420b-8c45-42dbf0ac6fe2"
                        }
                        "Honey Mustard" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Honey_Mustard.png?alt=media&token=9e46266a-8d5a-4913-9d93-b9477491b122"
                        }
                        "Honeydew Syrup" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Honeydew_Syrup.png?alt=media&token=1b66dccf-aa41-43b1-a66b-337672612d76"
                        }
                        "Honeydew Tea" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Honeydew_Tea.png?alt=media&token=992213c1-d117-4cff-8450-4338a744727c"
                        }
                        "Horchata" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Horchata.png?alt=media&token=30860eeb-adde-4021-ae5b-3c290ccfa9e9"
                        }
                        "Horchata Cake" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/horchata_cake.png?alt=media&token=13614aaf-9efb-4827-ac2d-5d485370130d"
                        }
                        "Horchata Tea" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Horchata_Tea.png?alt=media&token=03397fdf-1caa-4474-8229-67ce3a299355"
                        }
                        "Hot Dog Bites Crust" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/HotDogBitesCrust.png?alt=media&token=7380f4b1-f45b-4372-be50-53808f4b4c21"
                        }
                        "Hot Dog Sausage" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Hot_Dog.png?alt=media&token=e3910761-a058-4e4b-a0fb-99c221167edb"
                        }
                        "Hot Rods" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Hot_Rods.png?alt=media&token=711937c7-3775-4c20-a12f-749eee6cb292"
                        }
                        "Hot Rods Slush" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Hot_Rods_Slush.png?alt=media&token=8721f5d5-5f52-4ba0-a825-9c9bdee1d85f"
                        }
                        "Hot Rods Syrup" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Hot_Rods_Syrup.png?alt=media&token=d30bc804-2beb-4e6f-b131-fd7e259ca123"
                        }
                        "Hot Sauce" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/HotSauce.png?alt=media&token=f0ca833f-142d-4f8f-81a0-f5ea0a2f3e3c"
                        }
                        "Huckleberry Bubbles" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Huckleberry_Bubbles.png?alt=media&token=357c32b1-9705-4277-8d4a-d2df6a981352"
                        }
                        "Huckleberry Syrup" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Huckleberry_Syrup.png?alt=media&token=6b4c525f-fb92-48f2-b2b3-9bfd71cb022e"
                        }
                        "Hula Hula Sauce" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Hula_Hula_Sauce.png?alt=media&token=53bea499-5548-4a9d-994d-f50bf6508eb0"
                        }
                        "Hummus" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Hummus.png?alt=media&token=a78b29a1-4cb4-4d28-aab2-c36a8ea2340d"
                        }
                        "Hurry Curry" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Hurry_Curry.png?alt=media&token=b8c8caf7-e958-49ab-8866-ccf9ece9a388"
                        }
                        "Hyper Green" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Hyper_Green.png?alt=media&token=6d0070df-e60f-4c55-bac9-c20eba5bde80"
                        }
                        "Hyper Green Drizzle" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Hyper_Green_Drizzle.png?alt=media&token=794f9a4d-9143-4ed6-8527-d0a0274d5438"
                        }
                        "Hyper Green Sauce" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Hyper_Green_Sauce.png?alt=media&token=abf13d81-5df7-4019-8685-47693546a6cf"
                        }
                        "Ice" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Ice.png?alt=media&token=7afca1f2-1960-4183-893e-a7c29615876c"
                        }
                        "Ikura" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Ikura.png?alt=media&token=50b95efe-016e-492d-a0b0-e611e36f8747"
                        }
                        "Infinity Loop Cutter" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/InfinityLoop.png?alt=media&token=fa69f7b9-c792-4c34-8224-fcccb59f34d8"
                        }
                        "Irish Cream" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Irish_Cream.png?alt=media&token=1cf73b91-b593-46b0-a760-58cd4e661ced"
                        }
                        "Irish Cream Coffee" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Irish_Cream_Coffee.png?alt=media&token=7710622f-33b7-4c22-9cae-75a6819ad17f"
                        }
                        "Irish Cream Drizzle" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Irish_Cream_Drizzle.png?alt=media&token=2d9edd17-d67a-4b4b-af1b-1332c210c61f"
                        }
                        "Irish Parsley Sauce" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Irish_Parsley_Sauce.png?alt=media&token=9b0c135f-90c7-4591-9d2c-807083f4ffe8"
                        }
                        "Italian Sausage" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Italian_Sausage.png?alt=media&token=cd3336a6-dcec-42cf-94dc-b6cd0a3f9522"
                        }
                        "Italian Seasoning" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Italian_Seasoning.png?alt=media&token=fdb9b146-f3af-4896-9651-32a027f5dcb4"
                        }
                        "Iyokan Slush" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Iyokan_Slush.png?alt=media&token=9d516ef7-47f0-4f31-b2ac-3c55ce59d78e"
                        }
                        "Iyokan Tea" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Iyokan_Tea.png?alt=media&token=d8d421f3-00bc-4abe-9d3b-6f9ffcda11fb"
                        }
                        "Jack Frost Bun" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Jack_Frost_Bun.png?alt=media&token=9c0aafe1-bee8-4ca4-9fdb-78b3608c5e9b"
                        }
                        "Jack-o-Mole" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Jackomole.png?alt=media&token=7c8366da-8e9f-4d1d-ae64-d9e4e8889b68"
                        }
                        "Jalapeños" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/jalapenos.png?alt=media&token=033444ac-6aeb-49f0-8384-348fc2a575de"
                        }
                        "Jambalaya Rice" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Jambalaya_Rice.png?alt=media&token=a17079e4-b79f-4566-803b-4248df39fece"
                        }
                        "Jelly Beans" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Jelly_Beans.png?alt=media&token=f1361e9f-a600-492e-9b24-d2b31a9d7b2e"
                        }
                        "Jellybean Jam" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Jellybean_Jam.png?alt=media&token=4a9354cb-a97c-4c3c-8e3e-c38f168665f1"
                        }
                        "Jolly Roger" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Jolly_Roger.png?alt=media&token=ef4518f3-786b-467c-a311-b558f29126d8"
                        }
                        "Jubilee Jelly" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Jubilee_Jelly.png?alt=media&token=8204db08-4a82-4645-90cd-3c57a072ff18"
                        }
                        "Jubilee Jelly Drizzle" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Jubilee_Jelly_Syrup.png?alt=media&token=2b8bcfbd-f653-443a-bffb-6b7c15f58cbd"
                        }
                        "Jubilee Popcorn" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Jubilee_Popcorn.png?alt=media&token=da2f16ec-6d55-49b9-b31c-7dbb22bb5317"
                        }
                        "Kaiser Onion Roll" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Kaiser_Onion_Roll.png?alt=media&token=bf25488a-cb66-46d5-bec3-22d1ad72c029"
                        }
                        "Kaju Katli" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Kaju_Katli.png?alt=media&token=eab673b9-ddb4-4eec-bdeb-89f812976dd3"
                        }
                        "Kale" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Kale.png?alt=media&token=3be3f164-bf6d-45e4-94d2-22a4d914fe63"
                        }
                        "Kalua Ham" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Kalua_Ham.png?alt=media&token=9fb2fcc6-85eb-434d-adae-b4de0ba08216"
                        }
                        "Kampachi" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Kampachi.png?alt=media&token=afa56d5a-5689-483f-9131-0f7a064b1c47"
                        }
                        "Kanji Syrup" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Kanjisyrup.png?alt=media&token=d8b4a9d6-62b2-47a6-8f61-e74006015d2f"
                        }
                        "Kanpyo" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Kanpyo.png?alt=media&token=1fce18e7-c95d-4437-a75c-accb13d840cf"
                        }
                        "Karashi Mayo" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Karashi_Mayo.png?alt=media&token=58049f1e-f606-410f-90e2-eb6b45a5951a"
                        }
                        "Karmic Korma Sauce" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Karmic_Korma.png?alt=media&token=1d3486ef-448f-4f52-94f2-e6770e669c1f"
                        }
                        "Ketchup" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Ketchup.png?alt=media&token=67c45dd7-d67b-46dc-9873-7d6e03f9ac72"
                        }
                        "Kettle Corn" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Kettle_Corn.png?alt=media&token=a47f73d5-090d-4b23-aecd-0dd836ee89bc"
                        }
                        "Key Lime Drizzle" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Keylime_burned.png?alt=media&token=2b61f19e-ba11-414c-b01b-89f5f2bfa88d"
                        }
                        "Key Lime Filling" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Key_Lime_Filling.png?alt=media&token=e658554b-e7a5-4d34-a08d-7e19920084c3"
                        }
                        "Kielbasa" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Kielbasa.png?alt=media&token=ff6ee5b6-426f-48fb-8eff-72a813d2900e"
                        }
                        "Kiiroi Soy Paper" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Kiiroi_Soy_Paper.png?alt=media&token=1ae25983-d279-42fe-8720-ef966af05e9d"
                        }
                        "Kilauea Sauce" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Kilauea_Sauce.png?alt=media&token=204a479d-6a87-4e68-be78-059fef5d3ff3"
                        }
                        "Kimchi" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Kimchi.png?alt=media&token=4400f422-a7c2-42cf-813e-04518d761d72"
                        }
                        "King Cake Cutter" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/King_Cake.png?alt=media&token=b0ce2203-a08e-4e70-85e3-5eec014af8d7"
                        }
                        "Kiwi" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Kiwi.png?alt=media&token=5257110b-9661-42af-856b-970483d47aef"
                        }
                        "Kiwi Bubbles" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Kiwi_Bubbles.png?alt=media&token=6dfd7e1f-e598-4fe8-8d69-d7e44035be7e"
                        }
                        "Kiwi Cake" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Kiwi_Cake.png?alt=media&token=eb5f6a39-6972-499f-8a82-ce6f1186a408"
                        }
                        "Kiwi Filling" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Kiwi_Filling.png?alt=media&token=1b218389-e205-47c5-9dfc-6a06d2f5e1dd"
                        }
                        "Kiwi Slush" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Kiwi_Slush.png?alt=media&token=25095174-6e7a-4e5c-a8a5-00486e0c5beb"
                        }
                        "Kobumaki" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Kobumaki.png?alt=media&token=4b4a8e68-44fc-42ac-b915-358655e0de7f"
                        }
                        "Konpeito" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Konpeito.png?alt=media&token=5fce3c10-270f-4042-93af-1bf2bcc1b7a6"
                        }
                        "Krampus Sauce" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Krampus_Sauce.png?alt=media&token=978a7d8c-2ca0-48db-81c8-c5d56a97c3df"
                        }
                        "Kumquats" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Kumquat.png?alt=media&token=1196b95f-dc79-44b7-8899-5d7f7a16bd37"
                        }
                        "Kung Pao" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Kung_Pao.png?alt=media&token=ffd62c5a-b60a-4871-9f47-561fbf0d703a"
                        }
                        "Kuri Kinton" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Kuri_kinton.png?alt=media&token=7df6f800-a172-4e7f-acd7-71305e6e12b8"
                        }
                        "Kuromitsu Drizzle" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Kuromitsu_Drizzle.png?alt=media&token=b60b6a75-5663-4d60-a1f7-351a2b7604e6"
                        }
                        "La Catrina Sauce" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/La_Catrina_Sauce.png?alt=media&token=3a0f2b98-8386-4de6-9e8b-2d52cc98a108"
                        }
                        "Ladyfinger" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Ladyfinger.png?alt=media&token=b53cae2c-b3cb-4b2c-a33c-c26b39194e1d"
                        }
                        "Ladyfingers Crust" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Ladyfingers_Crust.png?alt=media&token=751ad88a-ae22-4dc3-897c-3c1b1a079bce"
                        }
                        "Lattice Top" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Lattice_Top.png?alt=media&token=3413b99a-b067-414b-94b7-1505de47eb3b"
                        }
                        "Lava Cake" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Lava_Cake.png?alt=media&token=a48bc41a-6de9-4027-8923-0e1a322cd555"
                        }
                        "Lava MunchMelt" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Lavatsco.png?alt=media&token=0cf5eaf3-cb1c-49ff-81d4-151f6b9a71b3"
                        }
                        "Lavender Cheeps" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Lavender_Cheep.png?alt=media&token=a1ae5d4e-5061-4922-a173-dc0afa0a0989"
                        }
                        "Lavender Frost" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Lavender_Frost.png?alt=media&token=1d1e0729-366a-4161-a39d-f4a8df6cf6f8"
                        }
                        "Lavender Icing" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Lavender_Icing.png?alt=media&token=2eecd416-8def-46f0-af45-a0420863d0ae"
                        }
                        "Lavender Lemonade Syrup" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Lavender_Lemonade_Syrup.png?alt=media&token=5e9eb44a-c84e-41b1-aa48-1132c84289e2"
                        }
                        "Lavender Powder" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Lavender_Powder.png?alt=media&token=fa5b39dd-c2a1-47f7-9906-46b4a5509ee4"
                        }
                        "Lemon Butter" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Lemon_Butter.png?alt=media&token=3126c5e0-3429-4433-ad9b-16b18b5ec36f"
                        }
                        "Lemon Cake" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Lemon_Cake.png?alt=media&token=f9cde266-c7d5-4296-9d9e-6d67c8a432ea"
                        }
                        "Lemon Chiffon" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Lemon_Chiffon.png?alt=media&token=9053049a-2ff4-434f-b12a-ebf070b535ee"
                        }
                        "Lemon Crinkle Cookie" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Lemon_Crinkle_Cookie.png?alt=media&token=1841d23f-3dcd-432a-8394-0408293fcaa7"
                        }
                        "Lemon Filling" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Lemon_Filling.png?alt=media&token=3bff1521-1e2b-49ae-8ba2-5d6f804b73f5"
                        }
                        "Lemon Herb" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Lemon_Herb.png?alt=media&token=37935622-cb4f-48dd-af9c-ea0243f4cb6d"
                        }
                        "Lemon Mist" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Lemon_Mist.png?alt=media&token=a1cffcfc-bedb-4e79-abc6-f324a28603ed"
                        }
                        "Lemon Wedge" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Lemon_Wedge.png?alt=media&token=1512be38-a9fc-4db3-ab42-52ab2a58eacd"
                        }
                        "Lemonade Drizzle" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Lemonade_Drizzle.png?alt=media&token=02b45b1a-34ce-4e47-ad5b-21ee3c0f0ddd"
                        }
                        "Lemonade Slush" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Lemonade_Slush.png?alt=media&token=25e62fde-112b-4e1e-856a-fad8adbadb30"
                        }
                        "Letterbox Cutter" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Letterbox_Donut.png?alt=media&token=fafc30a0-ace5-4393-9a93-3e89b9757010"
                        }
                        "Lettuce" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Lettuce.png?alt=media&token=6df8fa93-4199-4b13-a30d-bd35363704d9"
                        }
                        "Licorice Allsorts" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Licorice.Allsorts.png?alt=media&token=34aa5390-ef12-4487-bb16-1eeb771a0b64"
                        }
                        "Licorice Drizzle" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Licoricedrizzle.png?alt=media&token=e401c45c-8ddd-49a2-9070-ed6c43add652"
                        }
                        "Lightning Crust" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Lightning_Crust.png?alt=media&token=573cfbd2-6d7c-4dcd-b36b-67a0b2736642"
                        }
                        "Linzer Augen" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Linzer_Augen.png?alt=media&token=9080512b-33f9-4fe4-89a1-af16837ffb9a"
                        }
                        "Lobster" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Lobster.png?alt=media&token=6b8d38df-bbd9-4654-b125-901f07c39cd0"
                        }
                        "Lobster Chunks" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Lobster_chunks.png?alt=media&token=9a310f59-e002-46e7-bf86-e3eae4fbde4d"
                        }
                        "Loco Mystery Sauce" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/LocoMysterySauce.png?alt=media&token=538af8a1-f925-46e5-a9e4-d3ea28fe8fea"
                        }
                        "Lollipop Bits" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Lollipop_Bits.png?alt=media&token=1456ea2e-3625-45a3-92f3-dd7abede4f07"
                        }
                        "Lollipop Drizzle" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Lollipop_Drizzle.png?alt=media&token=d66b24ca-8e93-4d3c-b88a-88772eb15050"
                        }
                        "Lollipop Milk" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Lollipop_Milk.png?alt=media&token=7055dc10-a888-4d24-8000-9705429d6c9b"
                        }
                        "Lone Star Pit Sauce" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Lone_Star_Pit_Sauce.png?alt=media&token=e8211c39-d194-48bf-bc68-1dce73b0324c"
                        }
                        "Long John Shape Cutter" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Long_John.png?alt=media&token=d0ea1de0-86e1-4e9c-99f8-6295d1c6e9f1"
                        }
                        "Longevity Noodles" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Longevity_Noodles.png?alt=media&token=f0be204f-2cdc-4306-83cd-fa9df78d21cb"
                        }
                        "Lotus Root" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/LotusRoot.png?alt=media&token=7c60ede7-0dde-43f9-a97c-ac7275392ef1"
                        }
                        "Love Potion Topping" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Love_potion.png?alt=media&token=d981fa15-5b93-41f6-a00e-9e53efe525ed"
                        }
                        "Luau Musubi" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Luau_Musubi.png?alt=media&token=a5113aa8-6126-4182-8a89-403fee5406d2"
                        }
                        "Luau Punch" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Luau_Punch.png?alt=media&token=ef5542e7-a8c7-4022-9950-122fe48165ca"
                        }
                        "Luau Punch Drizzle" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Luau_Punch_Drizzle.png?alt=media&token=805eb4f9-01fe-4847-9baf-45c51d783e13"
                        }
                        "Luau Punch Slush" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Luau_Punch_Slush.png?alt=media&token=bcf635b3-ecbf-4c9f-91c8-ec9f9055e2e9"
                        }
                        "Luau Punch Tea" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Luau_Punch_Tea.png?alt=media&token=e16fa41c-dbc5-4933-aa04-934ecb40053f"
                        }
                        "Lucky Dust" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Lucky_Dust.png?alt=media&token=f4bc80b1-cafc-4bf1-ae82-4929b665cb91"
                        }
                        "Lucky Sevens" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Lucky_Sevens.png?alt=media&token=acd98cae-f94f-4250-9122-108b402da069"
                        }
                        "Lucky Soy Paper" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Lucky_Soy_Paper.png?alt=media&token=232f5d13-5b53-49d4-98c4-b05115012d34"
                        }
                        "Lunar Jerky" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Lunar_Jerky.png?alt=media&token=65f134f9-392d-495d-923a-0ea38b8d01d0"
                        }
                        "Lunar Loaf" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Lunar_Loaf_Bun.png?alt=media&token=09aea8cb-7e21-4fed-822b-a2a0fb9cc0cc"
                        }
                        "Lychee Bubbles" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Lychee_Bubbles.png?alt=media&token=d378e524-212e-472a-8eb6-2f56ff4b5a85"
                        }
                        "Mac n'Cheese" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/mac_n_cheese.png?alt=media&token=da59ec8f-9c92-4848-9bca-b90eafdc3e66"
                        }
                        "Macaroni" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Macaroni.png?alt=media&token=784c8abd-cff6-469f-8154-392b1d25e426"
                        }
                        "Macarons" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Macaron.png?alt=media&token=44a9109d-daab-4958-850d-25ad938f55ed"
                        }
                        "Madeleines" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Madeleines.png?alt=media&token=4af9cfc8-9751-4010-98cb-41a430dd2469"
                        }
                        "Mafaldine" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Mafaldine.png?alt=media&token=253f657c-a516-41cf-8a9a-9b49298fabb8"
                        }
                        "Mambo Sauce" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Mambo_Sauce.png?alt=media&token=38be2640-7f08-47dc-84e4-7615604120d4"
                        }
                        "Mamey Syrup" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Mamey_Syrup.png?alt=media&token=003c4a04-3949-4aae-b3bb-e46c2c63375d"
                        }
                        "Mango Bubbles" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Mango_Bubbles.png?alt=media&token=54fab4f5-c894-4868-af86-b356b7e7aaac"
                        }
                        "Mango Chili Dip" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Mango_Chili_Dip.png?alt=media&token=780ea651-f1f2-42e8-9423-040e7774bd06"
                        }
                        "Mango Chili Sauce" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Mango_chili_sauce.png?alt=media&token=79edcb9b-5132-4af5-8d84-cdf449dcb7ba"
                        }
                        "Mango Cream Cheese" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Mango_Cheese.png?alt=media&token=79276df0-b423-4f96-afee-80ff36489ef7"
                        }
                        "Mango Drizzle" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Mango_Drizzle.png?alt=media&token=34556d5a-1e9b-42d7-bf81-499d3f56495b"
                        }
                        "Mango Powder" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Mango_Powder.png?alt=media&token=d2ac3645-9471-435e-9bed-67ba8f4c8aa3"
                        }
                        "Mango Slices" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Mango_Slices.png?alt=media&token=24f554f5-dd78-4f6c-bac6-d8a60adb69b4"
                        }
                        "Mango de Mayo" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Mango_de_Mayo.png?alt=media&token=b68f053c-cd20-4404-98a1-35c8732cd488"
                        }
                        "Manicotti" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Manicotti_.png?alt=media&token=fda5cd0c-2dde-44be-a326-e3aa2b74c97d"
                        }
                        "Maple Icing" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Maple_Frosting.png?alt=media&token=9069378c-0261-46e8-981a-a263387e78b7"
                        }
                        "Maple Jack Cheese" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Maple_Jack.png?alt=media&token=6c69300c-f570-4b6b-aa7a-3205cd0c3588"
                        }
                        "Maple Mini Sausages" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Maple_Mini.png?alt=media&token=71807b23-675a-41cf-8c37-6d71cec5c03a"
                        }
                        "Maple Syrup" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Maple_Syrup.png?alt=media&token=8767136d-834b-4b6f-a01b-3873e69cae9d"
                        }
                        "Marble Rye" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Marble_Rye_Bread.png?alt=media&token=cc7305af-2798-4ae9-a2ee-0338bbf96e0b"
                        }
                        "Marinara Sauce" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Marinara_Sauce.png?alt=media&token=b6b10a68-3087-4b87-94b2-948701e2c95c"
                        }
                        "Marshmallow Cheep" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Marshmallow_Cheep.png?alt=media&token=b5eac7ca-3e25-4f00-8ae3-96a0049945d3"
                        }
                        "Marshmallow Cheep Cream" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/MarshmallowCheepsCream.png?alt=media&token=679ed2e5-b716-4068-97d6-e70d4465d921"
                        }
                        "Marshmallow Drums" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Marshmallow_Drum.png?alt=media&token=2d2cd01f-c6f9-4f93-b0a5-5c97dd1fd82d"
                        }
                        "Marshmallow Filling" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Marshmallow_Filling.png?alt=media&token=4b3101dc-97a5-46f5-bc5a-a80866e57368"
                        }
                        "Marshmallow Syrup" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Marshmallow_Syrup.png?alt=media&token=5baca54d-9a40-41b3-b391-e10bb8d33a29"
                        }
                        "Marshmallows" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Marshmallows.png?alt=media&token=9b59d0a0-c478-4e8f-aea1-85d5c82ca26a"
                        }
                        "Marzen Mustard" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Marzen_Mustard.png?alt=media&token=6e8d8feb-85e7-4367-9d3d-5962e6a29721"
                        }
                        "Mashed Potatoes" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Mashed_Potatoes.png?alt=media&token=919be285-8794-4f94-865b-22f5ff938529"
                        }
                        "Masquerade Powder" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Masquerade_Powder.png?alt=media&token=fdbe63a9-6f0e-4a45-9b60-bcd4e4ba9861"
                        }
                        "Matcha Drizzle" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Matcha_Drizzle.png?alt=media&token=ad71f103-04cf-4806-b862-3ed461da3bc5"
                        }
                        "Matcha Powder" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Matcha_Powder.png?alt=media&token=0b1c9210-df08-4844-8bc3-938133a06546"
                        }
                        "Matcha Tea" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Matcha_Tea.png?alt=media&token=617a15df-535f-4bc8-8f1f-a735be343772"
                        }
                        "Maui Meringue" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Maui_Meringue.png?alt=media&token=5dd0d26b-9b18-4b17-8784-db84071e17e1"
                        }
                        "Maui Meringue Dollops" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Maui_meringue_dollops.png?alt=media&token=798ae2b8-7194-4173-a586-fb5e7b5be5c5"
                        }
                        "Mayo" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Mayo.png?alt=media&token=c7f4db70-0fa9-433d-ac47-4317f1cc75ee"
                        }
                        "Meatballs" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Meatballs.png?alt=media&token=87120d6e-af44-478a-83e6-ed11094ecea3"
                        }
                        "Medium Sauce" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Medium_Sauce.png?alt=media&token=df3c3db1-5a11-4f71-95b6-22cf2611a3b3"
                        }
                        "Melon Pan Bun" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Melon_Pan_Bun.png?alt=media&token=987e3157-e462-4df5-a089-816c427a1f97"
                        }
                        "Meringue Topping" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Meringue_Topping.png?alt=media&token=8cb41dec-f6a2-4119-ac87-01db7ae545f2"
                        }
                        "Merry Masago" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Merry_Masago.png?alt=media&token=e107b290-873f-4fc2-88db-6db9901ce274"
                        }
                        "Meteor Blastor Soy Paper" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Meteor_Blastor.png?alt=media&token=00386d7b-ceb1-4657-8198-a2b3ebc2934a"
                        }
                        "Midnight Crunch Taco" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Midnight_Crunch_Taco.png?alt=media&token=f79213f2-043d-4145-a1f4-d4a41440bda0"
                        }
                        "Midnight Marsala" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Midnight_Marsala.png?alt=media&token=736a6180-0b0b-478d-b19c-68c197508aa7"
                        }
                        "Midnight Powder" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/MidnightPowder.png?alt=media&token=fabc6e23-b08a-4bd8-ba01-f23fdfba3fb3"
                        }
                        "Midnight Shimmer Powder" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Midnight_Shimmer_Powder.png?alt=media&token=9947d8cc-8371-4275-8348-7c40b7255d0e"
                        }
                        "Mild Sauce" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Mild_Sauce.png?alt=media&token=3834cb80-1e81-4f23-82cc-d4be742dfd17"
                        }
                        "Milk" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Milk.png?alt=media&token=2307d7cc-72ec-4936-a2bc-45c1fe745beb"
                        }
                        "Mini Donut" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Mini_Donut.png?alt=media&token=661cbbb1-918c-4339-8a38-80e8b6d100ff"
                        }
                        "Mini Mallows" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Mini_Mallows.png?alt=media&token=2fb862d9-ae89-4e0c-9443-de238aeb7388"
                        }
                        "Mint Bar Chunks" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Mint_Bar_Chunks.png?alt=media&token=589d1b25-64cc-4e00-8709-3ba17d40917f"
                        }
                        "Mint Chocolate Chip Ice Cream" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Mint_Chocolate_Chip_Ice_Cream.png?alt=media&token=c327348a-9c55-4fcd-8148-9e6da98cae3d"
                        }
                        "Mint Cream" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Mint_Cream.png?alt=media&token=0058287c-3d6d-4126-aae8-676ee02a3f57"
                        }
                        "Mint Creameo Cookies" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Mint_Creameo_Cookies.png?alt=media&token=7104779a-f4f4-4eb8-ba0d-98847590a433"
                        }
                        "Mint Syrup" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/MintDrizzle.png?alt=media&token=ece433f9-2fb7-4d49-b96b-47b8ff0fd064"
                        }
                        "Mission Fig Jam" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Mission_Fig_icon.png?alt=media&token=bdcc642c-a875-457e-8f55-32b74b728835"
                        }
                        "Mission Figs" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Misson_Figs.png?alt=media&token=f41e81a8-73aa-4fb2-97db-3a6b3a8651f8"
                        }
                        "Mixed Microgreens" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Mixed_Microgreens.png?alt=media&token=79d2863e-b673-4a6b-af33-2f1c7f0519df"
                        }
                        "Mizuiro Soy Paper" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Mizuiro_Soy_Paper.png?alt=media&token=04801423-309d-4a96-8480-2a99626aba72"
                        }
                        "Mocha Chocolate Chunk" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Mocha_Chocolate_Chunk.png?alt=media&token=5b41c8ce-dd21-478b-b8bc-e753bb0753fb"
                        }
                        "Mocha Cream" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Mocha_Cream.png?alt=media&token=4064f321-7117-40ac-bd55-67b5289aeaa1"
                        }
                        "Mocha Frosting" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Mocha_Frosting.png?alt=media&token=0b5e4383-45d2-4165-802e-01b94b1f1866"
                        }
                        "Mocha Tea" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Mocha_Tea.png?alt=media&token=e6c02cd4-2933-48da-9de3-c47a81ef1847"
                        }
                        "Mole Mistica" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Mole_Mistica.png?alt=media&token=652943f1-8fb2-41b0-bf14-a6d1195fc980"
                        }
                        "Momoiro Soy Paper" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Momoiro_Soy_Paper.png?alt=media&token=13909c40-9fb5-44ab-8778-e832bd108b3b"
                        }
                        "Monster Muester" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Monster_Muester.png?alt=media&token=26cae1a7-ba23-4256-868f-e7ce2085682b"
                        }
                        "Moon Mist Cream" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Moon_Mist_Cream.png?alt=media&token=6f97c810-c99e-4949-8469-6d059a44a636"
                        }
                        "Moon Mist Ice Cream" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Moon_Mist.png?alt=media&token=6adf5a43-ed65-4b92-8ca4-bacee47a255b"
                        }
                        "Moon Mist Syrup" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Moon_Mist_Syrup.png?alt=media&token=7dc4bc33-c7bd-4f91-ace3-28a04a04b120"
                        }
                        "Muffuletta Sauce" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Muffuletta_Sauce.png?alt=media&token=6ae133d6-fa00-4d04-b883-c8b9b4e89579"
                        }
                        "Mulberry Medley" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Mulberry_Medley_Mixable.png?alt=media&token=487d4881-7619-400a-b0ba-e2f384b95d00"
                        }
                        "Multi-Grain Chips" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Muti_multu.png?alt=media&token=f102e33a-4a10-4685-8616-4d8c23693377"
                        }
                        "Multigrain Bread" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Multigrain_Bread.png?alt=media&token=1e0de9da-1abe-4fcb-8b7c-41732b434662"
                        }
                        "Multigrain Bun" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Multigrain_Bun.png?alt=media&token=a2652399-507c-4f34-a964-180f49f2b156"
                        }
                        "Mummy Dog" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Mummy_Dog.png?alt=media&token=10e654b6-2115-4d61-aab8-a085ca091895"
                        }
                        "Mushrooms" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Mushroom.png?alt=media&token=573969a8-4a94-40e6-99a0-3126ffe41dc5"
                        }
                        "Music Notes" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Music_note.png?alt=media&token=0d130d77-f26f-489f-9eab-761aeccc4ca2"
                        }
                        "Mussel" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Mussels.png?alt=media&token=d5651495-4f33-4133-84a5-f1752f5bff4e"
                        }
                        "Mustard" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Mustardd.png?alt=media&token=8a07c150-1ed4-4235-b952-5fd313fa7704"
                        }
                        "Naan Bread" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Naan.png?alt=media&token=62a124ee-a89c-4e9d-b392-d2e9117b8980"
                        }
                        "Nacho Cheese" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/NachoCheese.png?alt=media&token=55b8d937-ed92-4eda-8a72-5b4c85c2cf91"
                        }
                        "Nacho Cheese Chips" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Nacho_octagon.png?alt=media&token=a6aed161-ccdf-4b5f-93c0-3539d612cdd5"
                        }
                        "Narutomaki" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Narutomaki.png?alt=media&token=7ea08d05-fc70-4e5d-8aae-37e494d2262e"
                        }
                        "Nashville Hot Sauce" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Nashville_Hot_Sauce.png?alt=media&token=0c14c303-a749-479b-9318-4c6232101af0"
                        }
                        "Neapolitan Cream" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Neapolitan_Cream.png?alt=media&token=7ba6a37b-4879-437e-874e-40595653e0ca"
                        }
                        "Neapolitan Syrup" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Neapolitan_Drizzle.png?alt=media&token=e9515c75-1dab-4916-a1c5-a7cca238a613"
                        }
                        "New England Roast" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/New_England_Roast.png?alt=media&token=b2d25bba-b690-4bd8-9201-d46d0a700bfa"
                        }
                        "New Year Topper" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/New_Year_Topper.png?alt=media&token=41853e04-6898-48f5-b91c-3df28c904a9a"
                        }
                        "Nogada Sauce" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Nogada.png?alt=media&token=2da0ef78-bec5-45d3-8da9-81eca250cb8f"
                        }
                        "Nori" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Nori.png?alt=media&token=0b8e57c9-0bdf-4cd3-a98c-629b61d1cd99"
                        }
                        "Nutty Butter Cup" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Nutty_Butter_Cup.png?alt=media&token=6cb68711-2a7a-4e67-88df-af8be822cff6"
                        }
                        "Oatmeal Cookie" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Oatmeal_Cookie.png?alt=media&token=e2472c1b-8c6c-463b-a384-28c39154d395"
                        }
                        "Oaxaca Cheese" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Oaxaca.png?alt=media&token=61d88601-2c61-469f-b3ee-bc41f95fe57d"
                        }
                        "Octopus" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Octopus.png?alt=media&token=d52c5128-4634-46c6-9c2d-919c7ff3c162"
                        }
                        "Oiri" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Oiri.png?alt=media&token=710b4f64-5203-4b11-9c86-1484d23141e6"
                        }
                        "Olive Oil" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/OliveOil.png?alt=media&token=5d876b3b-4cba-48ea-ac00-04b616e80c19"
                        }
                        "Onion Overdrive Ice Cream" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Onion_Overdrive.png?alt=media&token=047b8482-3046-4a97-b316-2877c1762aff"
                        }
                        "Onion Rings" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/OnionRings.png?alt=media&token=8df2aacb-4410-44e2-bcc5-af5fd40a3425"
                        }
                        "Onion Zest" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/OnionZest.png?alt=media&token=952de42f-6fdf-48c3-9fc4-258ad4fcd3b1"
                        }
                        "Onions" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Onions.png?alt=media&token=77d79573-f65e-4d93-a92c-42cb140691a4"
                        }
                        "Orange Frosting" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Orange_Frosting.png?alt=media&token=537a5453-e924-44b3-b968-27393d5e9bac"
                        }
                        "Orange Icing" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Orange_Icing.png?alt=media&token=eb5ecbac-aa2c-463e-bc50-49d1c3f0ec0e"
                        }
                        "Orange Juice" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Orange_Juice.png?alt=media&token=c2b4ed8f-fe7f-46bb-bc27-b43a8a5f1906"
                        }
                        "Orange Mocha Syrup" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Orange_Mocha_Syrup.png?alt=media&token=b4ce12ee-197a-441b-b91b-7916772b065e"
                        }
                        "PB&J Dip" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/PB%26J_Dip.png?alt=media&token=f3f90d94-929e-4900-9242-43b48ea06428"
                        }
                        "Paddy Popcorn" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Paddy_Popcorn.png?alt=media&token=1827f0b1-a429-4c92-b64d-0c0457a8be54"
                        }
                        "Pakoras" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Pakoras.png?alt=media&token=64dff7f8-641f-4585-bea4-dd1e90ca1b4c"
                        }
                        "Pan de Muerto" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Pan_de_Muerto.png?alt=media&token=976b6f4c-10a0-412e-83c8-9e78be1ba134"
                        }
                        "Pancake" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Pancake.png?alt=media&token=c42bc8af-64b2-4b25-a281-9f79676c1972"
                        }
                        "Paneer Cheese" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Paneer_Cheese.png?alt=media&token=304a0d3e-54d1-4b23-8bb7-f4ffe0e5a2cd"
                        }
                        "Panko Breading" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Panko%20Breading.png?alt=media&token=31fcd903-52a4-41ba-ad5b-d69ad6079e0a"
                        }
                        "Papa's Ballpark Mustard" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Papa_s_Ballpark_Mustard.png?alt=media&token=e04df7eb-a5f7-485c-ab98-f63e96fd5fd9"
                        }
                        "Papa's Cheese Blend" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Papa's_Cheese_Blend.png?alt=media&token=b4adaf1e-68f5-4a37-b7ea-80322d9b565d"
                        }
                        "Papa's Original Breading" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Papa's_Original_Breading.png?alt=media&token=9e38f57e-dde1-4cc6-a3f7-c4eaf69e0eb2"
                        }
                        "Papaya" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Papaya.png?alt=media&token=5fca327a-36de-4ada-8f9e-22ec97eb84c9"
                        }
                        "Paper Umbrella" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Paper_Umbrella.png?alt=media&token=548392b8-f8da-45f3-ac8a-b6ff05d9730b"
                        }
                        "Paprikash Sauce" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Paprikash_Sauce.png?alt=media&token=09e387b9-0f81-4d87-97d3-5b2365ea9ad2"
                        }
                        "Parmesan Cheese" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Parmesan.png?alt=media&token=a1145169-6887-4a79-b641-c367aab2903b"
                        }
                        "Parmesan Sauce" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Parmesan_Sauce.png?alt=media&token=4eb049c1-28c2-442b-9dc6-a213f576eeaa"
                        }
                        "Paska Bread" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Paska.png?alt=media&token=fa5b5fc6-48ff-4ec4-b736-4692855577f4"
                        }
                        "Paska Bun" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Paska_Bun.png?alt=media&token=904e7068-f94b-4656-a870-274d33727ab5"
                        }
                        "Pasqua Bun" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Pasqua_Bun.png?alt=media&token=6009e24a-eb49-460d-8ecf-59b7648caf0f"
                        }
                        "Passionfruit Drizzle" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Passionfruit_Drizzle.png?alt=media&token=2da237a6-68cb-4780-afb7-5b20176821ce"
                        }
                        "Passionfruit Filling" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Passionfruit_filling.png?alt=media&token=ef705f9e-b8a6-4ceb-b44f-49e4e82beea0"
                        }
                        "Passionfruit Ice Cream" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Passionfruit_Ice_Cream.png?alt=media&token=ef389d32-86a8-4bc5-ba48-fc90e46f662d"
                        }
                        "PastariO's Sauce" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/PastariOsSauce.png?alt=media&token=23cf97fe-2855-47be-b3fe-7062edd62c3d"
                        }
                        "Pawpaw Bubbles" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Pawpaw_Bubbles.png?alt=media&token=1f7300ad-1c50-44db-a267-8388fe3d5cf9"
                        }
                        "Pawpaw Topping" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Pawpaw_Syrup.png?alt=media&token=fc7b1dd3-5ca9-4e86-9604-34a26e6fc208"
                        }
                        "Peach Cream" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Peach_Cream.png?alt=media&token=3b0e3b0e-e5ec-4c36-998d-07887db2903d"
                        }
                        "Peach Filling" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Peach_filling.png?alt=media&token=adb53535-070c-41ec-a432-940448d26b51"
                        }
                        "Peaches" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Peaches.png?alt=media&token=ca51e251-a61e-4f88-9085-64cb98202057"
                        }
                        "Peanut Butter Cookie" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Peanut_Butter_Cookie.png?alt=media&token=5dc96c6d-0671-4e88-8603-34376fc395b9"
                        }
                        "Peanut Butter Drizzle" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Peanut_Butter_Drizzle.png?alt=media&token=026e7e5a-aeca-4b86-b908-145a943ed920"
                        }
                        "Peanut Butter Fluff" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Peanut_Butter_Fluff.png?alt=media&token=7829a62c-9298-4acc-a16d-76252ce0f8ee"
                        }
                        "Peanut Butter Swirl Crust" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Peanut_Butter_Swirl_Crust.png?alt=media&token=e235cfed-5a7f-463f-96df-0a84917480db"
                        }
                        "Peanuts" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Peanuts.png?alt=media&token=d87941d0-f73b-4397-a572-6b839485e2d0"
                        }
                        "Pecan Crust" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Pecan_Crust.png?alt=media&token=15152d99-bdf1-409c-9c30-b888eef60c82"
                        }
                        "Pecan Milk" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Pecan_Milk.png?alt=media&token=e061f278-c014-4d06-9698-0c6477121523"
                        }
                        "Pecan Mix" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Pecan_Mix.png?alt=media&token=555359e9-a288-4981-8cb7-d53497b56ea1"
                        }
                        "Pecan Pralines" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Pecan_Pralines.png?alt=media&token=188c2478-af29-42c6-bc4b-f5e3184b96d5"
                        }
                        "Pecans" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Pecans.png?alt=media&token=a9f57db6-dbde-4244-a0f7-abccc66be81e"
                        }
                        "Penne" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Penne.png?alt=media&token=c72a8d35-22e1-42e8-a587-0ed7a2cd164c"
                        }
                        "Peppered Pumpkin Sauce" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Peppered_Pumpkin_Sauce.png?alt=media&token=fa29975f-716c-4d05-bd9f-129337ba7a05"
                        }
                        "Pepperjack Cheese" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Pepperjack_Cheese.png?alt=media&token=c7c6293f-5a0c-4305-8a4f-3061f7c5cc3c"
                        }
                        "Pepperjack Rounds" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Pepperjack_rounds.png?alt=media&token=eb5ed715-2d78-4c9f-ab8b-1fb556936ca4"
                        }
                        "Peppermint Powder" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Peppermint_Powder.png?alt=media&token=49e84833-b990-4dde-9bcc-6e151949bff1"
                        }
                        "Peppermint Swirl Cream" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Peppermint_Swirl_Cream.png?alt=media&token=5b9e67d5-3504-4e3d-a742-3ca5cad8b28e"
                        }
                        "Peppermint Tea" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Peppermint_Tea.png?alt=media&token=d7bd88e8-89e1-4ce6-a71a-0d8d45ff7c23"
                        }
                        "Peppermints" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Peppermints.png?alt=media&token=1b0d0e53-40f1-4260-800e-6d00550ac93e"
                        }
                        "Pepperoni" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Pepperoni.png?alt=media&token=0ef306fa-ee76-452d-81cd-1c09fa4aa7c3"
                        }
                        "Pepperoni Bread" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Pepperoni_Bread.png?alt=media&token=5982e919-d815-453f-be33-7c394e6f602e"
                        }
                        "Peri Peri Sauce" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Peri_Peri_Sauce.png?alt=media&token=53df10f1-ec78-41a2-bffd-7047c5e54f0f"
                        }
                        "Phantom Fluff" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Phantom_Fluff.png?alt=media&token=56f07d29-d061-4399-839e-69943f33a59c"
                        }
                        "Philly Steak" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Philly_steak.png?alt=media&token=b82190ee-8b11-4315-a409-6664a0f1a298"
                        }
                        "Pickled Carrots" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Pickled_Carrots.png?alt=media&token=c34e3c55-8f04-4d1d-b2a1-a2cf9f037ab2"
                        }
                        "Pickled Eggs" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Pickled_Eggs.png?alt=media&token=64e6768a-7d43-40cd-9088-d82bc10dfc57"
                        }
                        "Pickled Red Onions" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Pickled_Red_Onions.png?alt=media&token=77666c8d-4bc9-4cfb-8785-16fb204295b0"
                        }
                        "Pickles" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Pickles.png?alt=media&token=a0cab7cc-67a5-488d-838c-e1c7ec6be1a8"
                        }
                        "Pico de Gallo" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Pico_de_Gallo.png?alt=media&token=5aa9912a-1e0a-4d1e-b34e-c54400fbc7c8"
                        }
                        "Pimiento Olives" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Pimiento_Olives.png?alt=media&token=bb2e920e-3edf-4028-8c36-c83502404739"
                        }
                        "Pine Nuts" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Pinenuts.png?alt=media&token=bffedde0-51c6-4ad5-9519-7e23a9e8a9a2"
                        }
                        "Pineapple" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Pineapple.png?alt=media&token=7de6c472-0a8d-43f1-8e6c-3d11be5c4a41"
                        }
                        "Pineapple Filling" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Pineapple_Filling.png?alt=media&token=b21069ef-cc91-419e-a48d-89df423c696c"
                        }
                        "Pineapple Pancetta" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Pineapple_Pancetta.png?alt=media&token=22a5be99-a414-4bcf-9001-6ddf6595d1d2"
                        }
                        "Pineapple Relish" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Pineapple_Relish.png?alt=media&token=165f4734-c705-4b0e-8328-d5be40347058"
                        }
                        "Pineapple Ring" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Pineapple_Slices.png?alt=media&token=64876046-77d7-4eee-b640-e21fc0899919"
                        }
                        "Pineapple Slush" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Pineapple_Slush.png?alt=media&token=e29a86b5-f43e-456d-a1b5-8378247b17a9"
                        }
                        "Pineapple Upside-Down Shell" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Pineapple_Upside-Down_Shell.png?alt=media&token=4225aba5-9876-45cb-b0e0-befac76f71bc"
                        }
                        "Pink Frosting" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Pink_Frosting.png?alt=media&token=970e975d-d74b-47c1-856e-94d3d50aa0e7"
                        }
                        "Pink Lemonade Syrup" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Pink_Lemonade_Drizzle.png?alt=media&token=7fbd4f56-c413-418d-8f57-1bfc4b7e7844"
                        }
                        "Pink Poppyseed Bun" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Pink_Poppyseed_Bun_.png?alt=media&token=adf61e41-1dee-4573-8d60-116db580abb5"
                        }
                        "Pistachio Ice Cream" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Pistachio_IceCream.png?alt=media&token=f4fae5ec-be43-4c41-8aba-cf7321a370dc"
                        }
                        "Pistachio Syrup" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Pistachio_Drizzle.png?alt=media&token=1c2a5043-9dea-403f-8076-732113607823"
                        }
                        "Pistachios" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Pistachios.png?alt=media&token=db6be5dd-b941-4ffb-b51b-bf770ec185fd"
                        }
                        "Pita Bread" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Pita_Bread.png?alt=media&token=5e3e5fa2-876b-4566-995e-d5dde83934cb"
                        }
                        "Pizza Poppers" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/PizzaPoppers.png?alt=media&token=bbb95282-f0be-43bd-8847-98ffe011e89d"
                        }
                        "Pizzelle" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Pizzelle_Topper.png?alt=media&token=4758d27a-8eff-4ee7-89a4-9a293fe75a16"
                        }
                        "Pizzelle Shell" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Pizzelle_Shell.png?alt=media&token=f6853766-63f5-4039-b4ab-155434a389cf"
                        }
                        "Piña Colada Milk" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Pi%C3%B1a_Colada.png?alt=media&token=10c328ab-61e5-48be-a094-b4fbf9d2693a"
                        }
                        "Piña Colada Tea" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Pi%C3%B1a_Colada_Tea.png?alt=media&token=e8bf1350-66e9-4976-945f-3781da5cefa2"
                        }
                        "Planet Cookies" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Planet_Cookies.png?alt=media&token=5c162e18-a03b-4b38-90fd-8a0cad1caa2b"
                        }
                        "Pluto Puffs" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Pluto_Puffs.png?alt=media&token=5f9ec5f1-33cf-4bd1-8d66-10adbe261328"
                        }
                        "Pogo" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Pogos.png?alt=media&token=73203a01-611a-4fda-86e0-83a32b32c405"
                        }
                        "Poke" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Poke.png?alt=media&token=9d2ccc3f-b361-42c2-a9bc-422c5c4ef001"
                        }
                        "Polka Dot Crust" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Polka_Dot_Crust.png?alt=media&token=65b9bfd3-f17a-4fae-b568-bc13fe4df1d3"
                        }
                        "Pomegranate" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Pomegranates.png?alt=media&token=37bb8140-841e-4939-8c0f-9bebf7e3ec7d"
                        }
                        "Pomegranate Filling" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Pomegranate_icon.png?alt=media&token=88f8bd95-45e6-407f-b485-e4b5bfa22fb2"
                        }
                        "Pon de Ring" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Pon_de_Ring.png?alt=media&token=44b3544e-6d1c-4791-8f2b-e8e5c9cd8135"
                        }
                        "Ponzu" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Ponzu.png?alt=media&token=38c3c7fc-2590-4477-b371-d22d92bc9b2e"
                        }
                        "Poppin' Coolada" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Poppin'_Coolada.png?alt=media&token=a74ad6cc-9a9a-481e-9356-4afc42b19ca4"
                        }
                        "Poppyseed Roll" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Poppyseed_Roll.png?alt=media&token=1e5d2df9-2722-4815-bffd-23ce739329c8"
                        }
                        "Pork" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Pork.png?alt=media&token=71e92bd5-af91-4647-9b9f-fc87d03b009f"
                        }
                        "Pork Chop" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Pork_Chop.png?alt=media&token=0065ffc6-ef71-4183-9092-d6c856921009"
                        }
                        "Portallini Confetti" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Portallini_Confetti.png?alt=media&token=567160c9-b588-4071-b8aa-bff66a8cda9b"
                        }
                        "Portobello Mushroom" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Portobello_Mushroom.png?alt=media&token=f9633e0f-5da3-4b94-9581-37f29de4d9a1"
                        }
                        "Pot O' Gold Topping" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Pot_O'_Gold.png?alt=media&token=292bb4ca-19de-427b-b778-46413c172eee"
                        }
                        "Potato Chips" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Potato_Chips.png?alt=media&token=6708d9c9-9347-4e82-b91c-f01a7bbb6071"
                        }
                        "Potato Skins" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Potato_Skins.png?alt=media&token=91fcb7c6-b201-4f1e-8296-53bfcc988d00"
                        }
                        "Potstickers" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Potstickers.png?alt=media&token=e1a62af7-4216-4cd4-ba31-c43bc0a9d644"
                        }
                        "Poutine" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Poutine.png?alt=media&token=22eb0a37-ee3a-4f6c-b9b9-b028942a68db"
                        }
                        "Powdered Sugar" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Powdered_Sugar.png?alt=media&token=df609bb8-3daf-4187-ac10-1a64cb974652"
                        }
                        "Powsicle Cake" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Powsicle_Cake.png?alt=media&token=0580949e-9323-45b6-8eba-e96f39e60da4"
                        }
                        "Powsicle Cream" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Powsicle_Cream.png?alt=media&token=de1f8a02-bbdf-4b92-ae84-9dcf6ae8845a"
                        }
                        "Powsicle Ice Cream" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Powsicle_Ice_Cream.png?alt=media&token=ee6cc3eb-288a-4826-9c45-e6cd3c4a39ab"
                        }
                        "Powsicle Punch" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Powsicle_Punch.png?alt=media&token=ceda83ef-6e4f-471e-b133-4e711546bdda"
                        }
                        "Powsicle Slush" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Powsicle_Slush.png?alt=media&token=039f1c41-aa21-4cda-b01f-4ec1b84a1f3f"
                        }
                        "Powsicle Syrup" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Powsicle_Drizzle.png?alt=media&token=064825cd-9d69-4e1e-9c58-5b5dd06afcf3"
                        }
                        "Powsicle Tea" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Powsicle_Tea.png?alt=media&token=d55fb98b-2af4-428f-86a3-1ce3888a2c5c"
                        }
                        "Praline Sauce" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Praline_Sauce.png?alt=media&token=d0acf7d2-120e-43d4-8154-958f48548706"
                        }
                        "Prawn" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Prawn.png?alt=media&token=855ac0e2-bfeb-447a-a5d4-5605459ad5e5"
                        }
                        "Pretzel Bat" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Pretzel_Bat.png?alt=media&token=4fd4dd9d-bddc-4347-8593-61c23d54f385"
                        }
                        "Pretzel Bits" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Pretzel_Bits.png?alt=media&token=3753bcde-979e-4415-bb95-15e91928f4ed"
                        }
                        "Pretzel Bread" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Pretzel_Bread.png?alt=media&token=7bafefb4-037d-4e6a-81e6-6c70d3702543"
                        }
                        "Pretzel Bun" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Pretzel_Bun.png?alt=media&token=392ad159-4e2c-4ff8-9ea9-421a41cebc7f"
                        }
                        "Pretzel Crisp" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Pretzel_Crisp.png?alt=media&token=b36c1b5b-107e-46c7-8233-01f84a1e4150"
                        }
                        "Pretzel Crust" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/PretzelCrust.png?alt=media&token=1eaf4a06-9df4-4874-8736-35bb00bf3d79"
                        }
                        "Pretzel Crust Breading" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Pretzel_Crust_Breading.png?alt=media&token=2a9f6e42-2bae-475c-9733-53ba6bd5b61c"
                        }
                        "Prosciutto" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Prosciutto.png?alt=media&token=47ebe78c-b511-4563-b50b-550c850df4b9"
                        }
                        "Provolone Cheese" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Provolone_Cheese.png?alt=media&token=3049ef83-5abe-4598-9972-7c3081543dd9"
                        }
                        "Provolone Stars" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Provolone_Stars.png?alt=media&token=528225a5-f413-4aec-82a7-497070687e09"
                        }
                        "Pulled Pork" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Pulled_Pork.png?alt=media&token=e7440185-aa85-493f-97fe-4128eb3315f2"
                        }
                        "Pulsar Pesto" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Pulsar_Pesto.png?alt=media&token=1081b8e3-bba3-4a45-812a-6c4480428f5f"
                        }
                        "Pumpernickel" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Pumpernickel.png?alt=media&token=3d677045-9c71-4204-8ec3-6db0aa31cade"
                        }
                        "Pumpernickel Roll" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/pumpernickel_roll.png?alt=media&token=b8f4bd21-0f81-4b00-a622-75b0d584e70e"
                        }
                        "Pumpkin Bread" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/pumpkin_bread.png?alt=media&token=fe0233e0-9ce0-4cd5-8478-9fcbf5b82a78"
                        }
                        "Pumpkin Cake" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Pumpkin_Donut.png?alt=media&token=4ea67a25-f91e-453a-9518-f5812e3f1e69"
                        }
                        "Pumpkin Filling" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Pumpkin_Filling.png?alt=media&token=79cc55df-3230-4430-ba3e-e53ba8744339"
                        }
                        "Pumpkin Pie Filling" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Pumpkin_Pie_Filling.png?alt=media&token=da67f974-3d89-4e52-a97f-f7ff5b58939e"
                        }
                        "Pumpkin Pie Ice Cream" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Pumpkin_Pie_Ice_Cream.png?alt=media&token=8624e9e8-67ae-4be7-89e7-125564b9c3f7"
                        }
                        "Pumpkin Pie Syrup" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Pumpkin_Pie_Drizzle.png?alt=media&token=27a51901-3d6a-40df-a775-d766fa223f38"
                        }
                        "Pumpkin Pomodoro" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Pumpkin_Pomodoro.png?alt=media&token=95cb055d-e0ce-4392-ad74-b0556e266e4b"
                        }
                        "Pumpkin Spice Coffee" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Pumpkin_Spice_Coffee.png?alt=media&token=19d36703-fa8f-42b1-9ce3-92920e4c1cc6"
                        }
                        "Pumpkin Spice Popcorn" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Pumpkin_Spice_Popcorn.png?alt=media&token=e7715dc4-8e32-47bc-bcc7-bdb9dd7fe49a"
                        }
                        "Pumpkin Spice Slush" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Pumpkin_Spice_Slush.png?alt=media&token=3fd94e7b-f492-46a1-ad10-bcce51a48865"
                        }
                        "Pumpkin Spice Tea" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Pumpkin_Spice_Tea.png?alt=media&token=351497c1-e4f9-4774-8365-ac194d36300b"
                        }
                        "Pumpkin Spice Tortilla" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Pumpkin_spice_shell.png?alt=media&token=cbc711c3-2a5e-470e-adeb-52f6cd89e98e"
                        }
                        "Purple Burple" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Purple_Burple.png?alt=media&token=2874ac7d-1951-41d1-b644-3cea4e6c2a87"
                        }
                        "Purple Burple Ice Cream" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Purple_Burple_Ice_Cream.png?alt=media&token=9875aa83-81e9-4c10-8d3c-65be3d6be650"
                        }
                        "Purple Burple Slush" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Purple_Burple_Slush.png?alt=media&token=d84df4dd-9f29-4fa2-a35f-5bb9b0959960"
                        }
                        "Purple Burple Syrup" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Purple_Burple_Drizzle.png?alt=media&token=95d2944a-838c-4397-9d39-74887ec5f9b5"
                        }
                        "Purple Pesto" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Purple_Pesto.png?alt=media&token=83cfada1-2b81-44aa-bb95-347560471d2c"
                        }
                        "Purple Yam Filling" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Purple_Yam_Filling.png?alt=media&token=9237b8a0-85d0-4d1e-88f8-88ca17cb78de"
                        }
                        "Pyroclastic Crunch" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Pyroclastic_Crunch.png?alt=media&token=7a1cec9f-16fd-468e-a9f1-de82a80f970d"
                        }
                        "Queso Blanco" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/QuesoBlanco.png?alt=media&token=c875bbfd-a626-4b0d-af76-d49c4ef6540c"
                        }
                        "Radiatori" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Radiatori.png?alt=media&token=20009670-3b89-458c-a37a-bc46129e91b2"
                        }
                        "Radicchio" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Radicchio.png?alt=media&token=f5f7969e-5c07-4c3b-85c4-d8027bdc3c7e"
                        }
                        "Radish Sprouts" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Radish_Sprouts.png?alt=media&token=302e5f44-f0dd-4386-95c1-aee501186188"
                        }
                        "Rainbow Gramigna" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Rainbow_Gramigna.png?alt=media&token=bb08d7ab-c99b-42c1-ad85-e6d5f1e50b3f"
                        }
                        "Rainbow Meringue" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Rainbow_Meringue.png?alt=media&token=18618d85-4cb4-4190-b6d2-ca7aed003e74"
                        }
                        "Rainbow Meringue Dollops" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Rainbow_Meringue_Dollop.png?alt=media&token=6915cef6-9593-471f-b6e3-dc600bb2aab2"
                        }
                        "Rainbow Peppercorn" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Rainbow_Peppercorn.png?alt=media&token=22be8859-9014-4404-af9c-14bd0d412674"
                        }
                        "Rainbow Rye Bun" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Rainbow_Rye_Bun.png?alt=media&token=1cc477b8-0352-4d30-bde5-17bcfe9ac6bf"
                        }
                        "Rainbow Sherbet Syrup" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Freezeria_Rainbow_Sherbet_Syrup_(Transparent).png?alt=media&token=fd01a00c-fa94-47fa-aadf-a22c6bb36687"
                        }
                        "Rainbow Soy Paper" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Rainbow_Soy_Paper.png?alt=media&token=1ec1ad34-4d40-4c39-94fa-c6110bf806c7"
                        }
                        "Rainbow Sprinkles" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Rainbow_Sprinkles.png?alt=media&token=99725856-3431-4b03-82b4-ce91052ca33e"
                        }
                        "Rainbow Whip" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Rainbow_Whip.png?alt=media&token=557eb2a5-4ccc-48d5-ab05-bca7f0b25382"
                        }
                        "Rainbow Yokan" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Rainbow_Yokan.png?alt=media&token=33783887-fac0-4dc8-9af1-b7b3b02323cc"
                        }
                        "Rainbow-livian Sauce" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Rainbow-livian_Sauce.png?alt=media&token=4699090a-5f56-4788-a7d9-af504943c2ac"
                        }
                        "Rainforest Whip" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Rainforest_Whip.png?alt=media&token=4e57a6e4-dd3a-4761-ad9d-575e01caa22a"
                        }
                        "Raisin Duds" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Raisin_Duds.png?alt=media&token=cdb7cf56-91bd-44e3-a86f-9eeead3fa5e6"
                        }
                        "Raisin Duds Popcorn" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Raisin_Duds_Popcorn.png?alt=media&token=7c23e106-52f6-4699-9de6-d1f5eb1d0277"
                        }
                        "Raisins" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Raisins.png?alt=media&token=148b96e6-24d2-445b-89fb-527e36f147ad"
                        }
                        "Ramen Crust" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/RamenCrust.png?alt=media&token=cca8d901-a9fc-4eb0-9221-79ee790e56d2"
                        }
                        "Ranch" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Ranch.png?alt=media&token=70c65923-4e3d-48bc-97f5-628ce1892044"
                        }
                        "Ranch Dip" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Ranch_Dip.png?alt=media&token=e6587f8c-70a3-4b1e-8f32-3429aa6a6e21"
                        }
                        "Raspberries" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Raspberries.png?alt=media&token=673fb44f-31ea-4ab9-a70d-6df3708ebbd1"
                        }
                        "Raspberry Bark" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/RaspberryBark.png?alt=media&token=83b6ed07-4222-43d3-b964-230855eeeafb"
                        }
                        "Raspberry Crumble" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Raspberry_Crumble.png?alt=media&token=5a1ce89d-582b-4402-a0e2-eb417c83dcda"
                        }
                        "Raspberry Ripple Ice Cream" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Raspberry_Ripple.png?alt=media&token=179c8b57-4f86-483d-9384-f63beceabbeb"
                        }
                        "Raspberry Tea" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Raspberry_Tea.png?alt=media&token=fc576f75-85b6-4398-9c01-c9c208c70564"
                        }
                        "Raspberry White Chocolate Cake" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Raspberry_White_Chocolate_Cake.png?alt=media&token=9515dc06-498f-4234-8e15-9a1303dae673"
                        }
                        "Rautenflagge Soy Paper" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Rautenflagge_Soy_Paper.png?alt=media&token=d9a8b425-6cf9-4d44-9c03-445ee35e64c4"
                        }
                        "Ravioli" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Ravioli.png?alt=media&token=f7a36e30-a138-4d5b-8c12-c4199b4b4d73"
                        }
                        "Razzle Dazzle" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Razzle_Dazzle.png?alt=media&token=0a893a8e-1515-44f8-aa24-49617a02aca6"
                        }
                        "Recado Rojo" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Recado_Rojo.png?alt=media&token=428cdef1-702e-4e29-b19a-299d4e506b65"
                        }
                        "Red Cabbage Slaw" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Red_Cabbage_Slaw.png?alt=media&token=de21575b-aab7-4343-94da-39a8a45d4d0d"
                        }
                        "Red Cinnamon Drizzle" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/RedCinnamonDrizzle.png?alt=media&token=860199ca-cc01-494d-baae-3b2aa38240bc"
                        }
                        "Red Frosting" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Red_Frosting.png?alt=media&token=3c258532-5320-4882-ad9c-895ad4773ad7"
                        }
                        "Red Hot Popcorn" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Red_Hot_Popcorn.png?alt=media&token=1a6fa60a-7c76-4ea1-95c6-a061064c0859"
                        }
                        "Red Icing" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Red_Rose_Icing.png?alt=media&token=6a11866d-fcf3-4273-8b83-a4c86eeed23f"
                        }
                        "Red Licorice" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Red_Licorice.png?alt=media&token=de7f6ac6-beb4-44ce-9404-d1e268b2af63"
                        }
                        "Red Pepper Crust" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Red_Pepper_Crust.png?alt=media&token=3c1a3dea-1af3-4a1c-acad-aa71be041c3a"
                        }
                        "Red Peppers" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Red_Peppers.png?alt=media&token=33a59998-ae47-4c39-81a2-f37347091930"
                        }
                        "Red Rice" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Red_Rice.png?alt=media&token=ab38c6f6-422c-43b2-8f66-3af9316d7639"
                        }
                        "Red Velvet Cake" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Red_Velvet_Cake.png?alt=media&token=f093b770-da85-4fc7-8ee4-7b5d43f18f88"
                        }
                        "Red Velvet Cookie" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Red_Velvet_Cookie.png?alt=media&token=e0ef6e9e-d2ec-4899-b9d4-cf37e721983c"
                        }
                        "Red Velvet Crust" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Red_Velvet_Crust.png?alt=media&token=8d882ce3-bbec-4f4a-be0e-0cfd908765a7"
                        }
                        "Red Velvet Syrup" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Red_Velvet_Syrup.png?alt=media&token=ab258e29-2736-4de1-ae43-715b40217ee4"
                        }
                        "Red Windsor Cheese" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Red_Windsor_cheese.png?alt=media&token=0a033ce3-a455-4633-8f3b-c415da231280"
                        }
                        "Refried Beans" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Refried_Beans.png?alt=media&token=c7badd8e-145c-42b4-99e9-2443d0ce48c0"
                        }
                        "Regular Dough" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Regular_Dough.png?alt=media&token=52bc50d6-43ba-4ce8-bd08-c4d134484b6f"
                        }
                        "Regular Hot Dog Bun" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Regular_Bun.png?alt=media&token=5992a573-edae-4465-9cd8-808336a8386d"
                        }
                        "Relish" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Relish.png?alt=media&token=c52c88b0-d712-400c-8e4f-b5f408bf18fb"
                        }
                        "Rhubarb Filling" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Rhubarb_Filling.png?alt=media&token=4df80f55-bda7-4381-a3c0-a80e927ebe2c"
                        }
                        "Ricotta Balls" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/RicottaBalls.png?alt=media&token=5f2e91c9-61dc-4491-abf7-8fa01ff11804"
                        }
                        "Ricotta Cheese" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Ricotta_cheese.png?alt=media&token=da1f1e5a-de95-4ef0-9136-741361cceecb"
                        }
                        "Ring Shape Cutter" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Ring_Dough.png?alt=media&token=f13bfd47-d186-488b-ac47-cf889eed3ca1"
                        }
                        "Risalamande" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Risalamande.png?alt=media&token=ca94ad3b-e83d-45c5-ad22-bc338ebc01f7"
                        }
                        "Roasted Asparagus" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Roasted_Asparagus.png?alt=media&token=2f831045-e996-41a2-8edb-ec968b2c8dc7"
                        }
                        "Roasted Chili-Corn Salsa" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Roasted_Chili-Corn_Salsa.png?alt=media&token=6ca67542-7e3d-459c-9d35-279759d888f7"
                        }
                        "Roasted Pumpkin Seeds" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Roasted_Pumpkin_Seeds.png?alt=media&token=5c747312-9acd-448d-9746-9623e3700a55"
                        }
                        "Roasted Romana" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Roasted_Romana.png?alt=media&token=2850a97e-dd3d-403d-8bf4-3fe75d79969d"
                        }
                        "Roasted Turkey" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Roasted_Turkey.png?alt=media&token=4a4968be-eb35-4109-8850-a287bcb0c836"
                        }
                        "Rock Candy" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Rock_Candy.png?alt=media&token=a79bdf94-e230-46ba-86c1-19ef0edb9dda"
                        }
                        "Rocket Ragu" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Rocket_Ragu.png?alt=media&token=da9c8c7b-1272-4654-b66e-21b80d07ad61"
                        }
                        "Rocket Whip" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Rocket_Whip.png?alt=media&token=5c1a889c-197b-4398-8739-e79a11404b80"
                        }
                        "Rockin' Rooibos Powder" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Rockin'_Rooibos_Powder.png?alt=media&token=b73166f0-6b55-4b8b-878b-b5d23b51545d"
                        }
                        "Rocky Road" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Rocky_Road_Ice_Cream.png?alt=media&token=6c0ef8f7-db1d-488e-8d30-3bb7394d066d"
                        }
                        "Roll Shape Cutter" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Roll_shape_cutter.png?alt=media&token=8373335b-555c-407a-8450-cb95cc7ed629"
                        }
                        "Romaine Lettuce" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Romaine_lettuce.png?alt=media&token=5d653976-97de-47ee-ad53-da2df730635d"
                        }
                        "Root Beer" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Root_Beer.png?alt=media&token=c5e9ee42-d68c-4aa5-9694-adbbb13fe2dd"
                        }
                        "Root Beer Float" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Root_Beer_Float.png?alt=media&token=76021358-c7a3-43c3-832f-fc7f91de7574"
                        }
                        "Root Beer Float Cake" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Root_Beer_Float_Cake.png?alt=media&token=213ca590-9119-4718-a587-55900220f92f"
                        }
                        "Root Beer Float Filling" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Root_Beer_Float_Filling.png?alt=media&token=d2d44293-3d76-4f00-b273-b74e3d07164a"
                        }
                        "Root Beer Slush" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Root_Beer_Slush.png?alt=media&token=adc4fe16-a982-4fe7-9ff2-bc62fdaadac8"
                        }
                        "Rose Hip Whip" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Rose_hIp_whip.png?alt=media&token=c0e7a0e7-35d6-4661-b803-cfa7f26f8c28"
                        }
                        "Rosemary" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Rosemary.png?alt=media&token=c25a2b1d-7d7e-4447-92b2-911c05950b7e"
                        }
                        "Rosemary Focaccia" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Rosemary_Focaccia.png?alt=media&token=bca9d1ed-b346-4200-b66e-49bf03531b66"
                        }
                        "Round Shape Cutter" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Round_Dough.png?alt=media&token=e49468ea-4540-4c2c-8000-12a044784b88"
                        }
                        "Rustic Romana" -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Rustic_Romana.png?alt=media&token=abcbfbdb-b66a-42c2-a4f4-c69a9ab473ac"
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