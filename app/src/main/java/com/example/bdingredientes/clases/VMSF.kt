package com.example.bdingredientes.clases

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.ModeEdit
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.example.bdingredientes.R

@Composable
fun BarraInferior(ventana : Int) {
    BottomAppBar(Modifier.fillMaxWidth()) {
        Row() {
            IconButton(onClick = {}) {
                Icon(Icons.Default.Add, contentDescription = "")
                if (ventana == 1) {
                    Icon(Icons.Default.Delete, contentDescription = "")
                    Icon(Icons.Default.ModeEdit, contentDescription = "")
                }
            }
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BarraSuperior(ventana : Int) {
    TopAppBar(title = { /*TODO*/ }, actions = {Row() {
        IconButton(onClick = {}) {
            Icon(painterResource(id = R.drawable.baseline_shuffle_24), contentDescription = "")
            Icon(painterResource(id = R.drawable.baseline_music_note_24), contentDescription = "")
            Icon(painterResource(id = R.drawable.baseline_list_alt_24), contentDescription = "")
        }
    }
    } )
}