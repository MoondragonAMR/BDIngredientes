package com.example.bdingredientes.ui.theme

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.bdingredientes.R

var juego = ""
var celebraciones = false

@Composable
fun PantallaRandom(navController: NavController) {

    BackHandler{
        navController.popBackStack()
    }

    Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        Row {
            Text("Holidays")
            Switch(checked = celebraciones, onCheckedChange = { celebraciones = !celebraciones})
        }
        Row{
            Button(onClick = { juego = "Pizzeria" }, Modifier.padding(4.dp)) {
                Icon(painterResource(R.drawable.featured_pizzeria_a), contentDescription = "")
            }
            Button(onClick = { juego = "Burgeria" }, Modifier.padding(4.dp)) {
                Icon(painterResource(R.drawable.featured_burgeria_a), contentDescription = "")
            }
            Button(onClick = { juego = "Taco Mia" }, Modifier.padding(4.dp)) {
                Icon(painterResource(R.drawable.featured_tacomia_a), contentDescription = "")
            }
        }
        Row {
            Button(onClick = { juego = "Freezeria" }, Modifier.padding(4.dp)) {
                Icon(painterResource(R.drawable.featured_freezeria_a), contentDescription = "")
            }
            Button(onClick = { juego = "Pancakeria" }, Modifier.padding(4.dp)) {
                Icon(painterResource(R.drawable.featured_pancakeria_a), contentDescription = "")
            }
            Button(onClick = { juego = "Wingeria" }, Modifier.padding(4.dp)) {
                Icon(painterResource(R.drawable.featured_wingeria_a), contentDescription = "")
            }
        }
        Row {
            Button(onClick = { juego = "Hot Doggeria" }, Modifier.padding(4.dp)) {
                Icon(painterResource(R.drawable.featured_hotdoggeria_a), contentDescription = "")
            }
            Button(onClick = { juego = "Cupcakeria" }, Modifier.padding(4.dp)) {
                Icon(painterResource(R.drawable.featured_cupcakeria_a), contentDescription = "")
            }
            Button(onClick = { juego = "Pastaria" }, Modifier.padding(4.dp)) {
                Icon(painterResource(R.drawable.featured_pastaria_a), contentDescription = "")
            }
        }
        Row {
            Button(onClick = { juego = "Donuteria" }, Modifier.padding(4.dp)) {
                Icon(painterResource(R.drawable.featured_donuteria_a), contentDescription = "")
            }
            Button(onClick = { juego = "Cheeseria" }, Modifier.padding(4.dp)) {
                Icon(painterResource(R.drawable.featured_cheeseria_a), contentDescription = "")
            }
            Button(onClick = { juego = "Bakeria" }, Modifier.padding(4.dp)) {
                Icon(painterResource(R.drawable.featured_bakeria_a), contentDescription = "")
            }
        }
        Row {
            Button(onClick = { juego = "Sushiria" }, Modifier.padding(4.dp)) {
                Icon(painterResource(R.drawable.featured_sushiria_a), contentDescription = "")
            }
            Button(onClick = { juego = "Scooperia" }, Modifier.padding(4.dp)) {
                Icon(painterResource(R.drawable.featured_scooperia_a), contentDescription = "")
            }
            Button(onClick = { juego = "Mocharia" }, Modifier.padding(4.dp)) {
                Icon(painterResource(R.drawable.featured_mocharia_a), contentDescription = "")
            }
        }
        Row {
            Button(onClick = { juego = "Cluckeria" }, Modifier.padding(4.dp)) {
                Icon(painterResource(R.drawable.featured_cluckeria_a), contentDescription = "")
            }
            Button(onClick = { juego = "Paleteria" }, Modifier.padding(4.dp)) {
                Icon(painterResource(R.drawable.featured_paleteria_a), contentDescription = "")
            }
        }
        Row {
            Button(onClick = {  }, Modifier.padding(4.dp)) {
                Text("Confirm")
            }
        }
    }

}