package com.example.bdingredientes.ui.theme

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.bdingredientes.R
import com.example.bdingredientes.clases.ViewModelScaffold

var juego = ""
var celebraciones = false

@Composable
fun PantallaRandom(navController: NavController, sf : ViewModelScaffold = viewModel()) {

    BackHandler{
        navController.popBackStack()
    }

    var elegido = false
    val idioma = sf.english.collectAsState().value

    Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        Row {
            val textoHolidays = if (idioma.value) {
                "Holidays"
            } else {
                "Celebraciones"
            }
            Text(textoHolidays)
            Switch(checked = celebraciones, onCheckedChange = { celebraciones = !celebraciones})
        }
        Row{
            Image(painter = painterResource(R.drawable.featured_pizzeria_a), contentDescription = ""
                , Modifier.padding(4.dp).clickable { juego = "Pizzeria"; elegido = true }.size(60.dp).border(width = 2.dp, color = if (juego == "Pizzeria") {
                    Color.Cyan
                } else Color.White
                ))
            Image(painter = painterResource(R.drawable.featured_burgeria_a), contentDescription = ""
                , Modifier.padding(4.dp).clickable { juego = "Burgeria"; elegido = true }.size(60.dp).border(width = 2.dp, color = if (juego == "Burgeria") {
                    Color.Cyan
                } else Color.White
                ))
            Image(painter = painterResource(R.drawable.featured_tacomia_a), contentDescription = ""
                , Modifier.padding(4.dp).clickable { juego = "Taco Mia"; elegido = true }.size(60.dp).border(width = 2.dp, color = if (juego == "Taco Mia") {
                    Color.Cyan
                } else Color.White
                ))
            Image(painter = painterResource(R.drawable.featured_freezeria_a), contentDescription = ""
                , Modifier.padding(4.dp).clickable { juego = "Freezeria"; elegido = true }.size(60.dp).border(width = 2.dp, color = if (juego == "Freezeria") {
                    Color.Cyan
                } else Color.White
                ))
            Image(painter = painterResource(R.drawable.featured_pancakeria_a), contentDescription = ""
                , Modifier.padding(4.dp).clickable { juego = "Pancakeria"; elegido = true }.size(60.dp).border(width = 2.dp, color = if (juego == "Pancakeria") {
                    Color.Cyan
                } else Color.White
                ))
        }
        Row {
            Image(painter = painterResource(R.drawable.featured_wingeria_a), contentDescription = ""
                , Modifier.padding(4.dp).clickable { juego = "Wingeria"; elegido = true }.size(60.dp).border(width = 2.dp, color = if (juego == "Wingeria") {
                    Color.Cyan
                } else Color.White
                ))
            Image(painter = painterResource(R.drawable.featured_hotdoggeria_a), contentDescription = ""
                , Modifier.padding(4.dp).clickable { juego = "Hot Doggeria"; elegido = true }.size(60.dp).border(width = 2.dp, color = if (juego == "Hot Doggeria") {
                    Color.Cyan
                } else Color.White
                ))
            Image(painter = painterResource(R.drawable.featured_cupcakeria_a), contentDescription = ""
                , Modifier.padding(4.dp).clickable { juego = "Cupcakeria"; elegido = true }.size(60.dp).border(width = 2.dp, color = if (juego == "Cupcakeria") {
                    Color.Cyan
                } else Color.White
                ))
            Image(painter = painterResource(R.drawable.featured_pastaria_a), contentDescription = ""
                , Modifier.padding(4.dp).clickable { juego = "Pastaria"; elegido = true }.size(60.dp).border(width = 2.dp, color = if (juego == "Pastaria") {
                    Color.Cyan
                } else Color.White
                ))
            Image(painter = painterResource(R.drawable.featured_donuteria_a), contentDescription = ""
                , Modifier.padding(4.dp).clickable { juego = "Donuteria"; elegido = true }.size(60.dp).border(width = 2.dp, color = if (juego == "Donuteria") {
                    Color.Cyan
                } else Color.White
                ))
        }
        Row {
            Image(painter = painterResource(R.drawable.featured_cheeseria_a), contentDescription = ""
                , Modifier.padding(4.dp).clickable { juego = "Cheeseria"; elegido = true }.size(60.dp).border(width = 2.dp, color = if (juego == "Cheeseria") {
                    Color.Cyan
                } else Color.White
                ))
            Image(painter = painterResource(R.drawable.featured_bakeria_a), contentDescription = ""
                , Modifier.padding(4.dp).clickable { juego = "Bakeria"; elegido = true }.size(60.dp).border(width = 2.dp, color = if (juego == "Bakeria") {
                    Color.Cyan
                } else Color.White
                ))
            Image(painter = painterResource(R.drawable.featured_sushiria_a), contentDescription = ""
                , Modifier.padding(4.dp).clickable { juego = "Sushiria"; elegido = true }.size(60.dp).border(width = 2.dp, color = if (juego == "Sushiria") {
                    Color.Cyan
                } else Color.White
                ))
            Image(painter = painterResource(R.drawable.featured_scooperia_a), contentDescription = ""
                , Modifier.padding(4.dp).clickable { juego = "Scooperia"; elegido = true }.size(60.dp).border(width = 2.dp, color = if (juego == "Scooperia") {
                    Color.Cyan
                } else Color.White
                ))
            Image(painter = painterResource(R.drawable.featured_mocharia_a), contentDescription = ""
                , Modifier.padding(4.dp).clickable { juego = "Mocharia"; elegido = true }.size(60.dp).border(width = 2.dp, color = if (juego == "Mocharia") {
                    Color.Cyan
                } else Color.White
                ))
        }
        Row {
            Image(painter = painterResource(R.drawable.featured_cluckeria_a), contentDescription = ""
                , Modifier.padding(4.dp).clickable { juego = "Cluckeria"; elegido = true }.size(60.dp).border(width = 2.dp, color = if (juego == "Cluckeria") {
                    Color.Cyan
                } else Color.White
                ))
            Image(painter = painterResource(R.drawable.featured_paleteria_a), contentDescription = ""
                , Modifier.padding(4.dp).clickable { juego = "Paleteria"; elegido = true }.size(60.dp).border(width = 2.dp, color = if (juego == "Paleteria") {
                    Color.Cyan
                } else Color.White
                ))
        }
        Row {
            val textoConfirm = if (idioma.value) {
                "Confirm"
            } else {
                "Confirmar"
            }
            Button(onClick = {  }, Modifier.padding(4.dp), enabled = elegido) {
                Text(textoConfirm)
            }
        }
    }

}