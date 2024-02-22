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
import com.example.bdingredientes.clases.ImagenIngrediente
import com.example.bdingredientes.clases.Ingredient
import com.example.bdingredientes.clases.VMBD
import com.example.bdingredientes.clases.VMBD2
import com.example.bdingredientes.clases.ViewModelScaffold
import com.example.bdingredientes.clases.imagenes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaIngredients(db : VMBD, sf: ViewModelScaffold) {
    //var db: VMBD = viewModel()
    var ingredients = db.ingredients.collectAsState().value
    var db2: VMBD2 = viewModel()
    var estado by remember { mutableStateOf(false) }
    var busqueda by remember { mutableStateOf("") }
    var filtro by remember { mutableStateOf("") }
    var numero by remember { mutableStateOf(1334L) }
    var valor by remember { mutableStateOf(numero.toString())}
    var parametro by remember { mutableStateOf("id") }
    var filtroParametro by remember { mutableStateOf("None") }
    var activado by remember { mutableStateOf(false) }
    var activado2 by remember { mutableStateOf(false) }
    //var sf: ViewModelScaffold = viewModel()
    var aleatorio = sf.Aleatorio.collectAsState().value
    var ingredientsRandom = db.ingredientsAleatorio.collectAsState().value
    var listaMostrar = db.listaMostrar.collectAsState().value
    var url by remember { mutableStateOf("") }

    DisposableEffect(db) {
        db.crearListener()
        onDispose { db.borrarListener() }
    }

    Column() {

        Row() {
            Text("Click on an ingredient to add it to your list", fontWeight = FontWeight.Bold)
        }
        SearchBar(placeholder = { Text("Search ingredients by name") },
            query = busqueda,
            onQueryChange = { textoIntroducido -> busqueda = textoIntroducido },
            onSearch = { filtro = it; estado = false },
            active = estado,
            onActiveChange = { estado = !estado }) {
            LazyColumn() {
                items(ingredients.size) {
                    if ((ingredients[it].name.contains(
                            busqueda,
                            true
                        )) || (ingredients[it].type.lowercase()
                            .contains(busqueda)) || (busqueda.isBlank())
                    ) {
                        ListItem(
                            headlineContent = { Text(ingredients[it].name) },
                            Modifier.clickable { busqueda = ingredients[it].name })
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
        LazyColumn {


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
                    val nombre = listaMostrar[it].name
                    val tipo = listaMostrar[it].type
                    val sabor = listaMostrar[it].flavor
                    val deCelebracion = listaMostrar[it].holidayExclusive
                    val celebracion = listaMostrar   [it].holiday

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
                        "L" -> {
                            url = ""
                        }
                        else -> {
                            url = "https://firebasestorage.googleapis.com/v0/b/frutas-b0267.appspot.com/o/Al%20azar.png?alt=media&token=ab58f4fe-3730-48ca-870d-439e3e65fb44"
                        }
                    }

                    Column(
                        Modifier
                            .padding(4.dp)
                            .border(width = 2.dp, color = Color.Cyan)
                            .clickable() {
                                db2.anyadirIngrediente(
                                    nombre,
                                    tipo,
                                    sabor,
                                    deCelebracion,
                                    celebracion
                                )
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