package com.example.bdingredientes.ui.theme

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import com.google.firebase.auth.FirebaseAuth

var auth = FirebaseAuth.getInstance()
var user = auth.currentUser
var usuario = ""
var admin = false

@Composable
fun PantallaLogin(navController: NavController) {
    var texto by remember { mutableStateOf("Introduce your Firebase email and password")}
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

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
        Button(onClick = { auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
                task -> if (task.isSuccessful) {
                user = auth.currentUser
            when (user?.email) {
                "aymaragamer14@gmail.com" -> {
                    usuario = "RDTQ7Q7OmNbcvu6n1hiC"
                    admin = true
                }
                "nayaragamer14@gmail.com" -> {
                    usuario = "53VeNd9JZizsabjCLtrM"
                    admin = true
                }
                "alejandro@falso.com" -> {
                    usuario = "5k8EK1sExXOs0twUjZWD"
                    admin = false
                }
                else -> {
                    usuario = "uRxr15PTS2TISErKZ8Hw"
                    admin = false
                }
            }
                navController.navigate(Rutas.Menu.ruta)
                } else {
                    texto = "Email and/or password incorrect. Please introduce them again"
                    email = ""
                    password = ""
                }
        } }) {
            Text("Log in")
        }
        Row {
            Text("You are not authenticated?")
            Button(onClick = { navController.navigate(Rutas.Auth.ruta) }) {
                Text("Sign in")
            }
        }
    }
}