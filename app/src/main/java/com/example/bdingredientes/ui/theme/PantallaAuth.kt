package com.example.bdingredientes.ui.theme

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.navigation.NavController
import com.example.bdingredientes.clases.Rutas

@Composable
fun PantallaAuth(navController: NavController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var texto by remember { mutableStateOf("Insert the new user's email and password")}

    Column {
        Text(texto)
        Text("Email: ")
        TextField(
            value = email,
            onValueChange = { texto -> email = texto },
            placeholder = { Text("Insert your email") })
        Text("Password: ")
        TextField(
            value = password,
            onValueChange = { texto -> password = texto },
            visualTransformation = PasswordVisualTransformation(),
            placeholder = { Text("Insert your password") })
        Button(onClick = {
            auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
                    task -> if (task.isSuccessful) {
                        navController.navigate(Rutas.Login.ruta)
                    } else {
                    texto = "Email must contain an @. Password must contain a number. Please introduce them again"
                    email = ""
                    password = ""
                    }
            }
        }) {
            Text("Confirm")
        }
    }
}