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
import com.google.firebase.auth.FirebaseAuth

var auth = FirebaseAuth.getInstance()
var user = auth.currentUser
var usuario = ""

@Composable
fun PantallaLogin(navController: NavController) {
    var texto by remember { mutableStateOf("Introduce your Firebase email and password")}
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column() {
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
        Button(onClick = { auth.signInWithEmailAndPassword(email, password).addOnCompleteListener() {
                task -> if (task.isSuccessful) {
                user = auth.currentUser
                if (user?.email == "aymaragamer14@gmail.com") {
                    usuario = "RDTQ7Q7OmNbcvu6n1hiC"
                } else if (user?.email == "nayaragamer14@gmail.com") {
                    usuario = "53VeNd9JZizsabjCLtrM"
                } else if (user?.email == "lasaraylacharo@gmail.com") {
                    usuario = "5k8EK1sExXOs0twUjZWD"
                } else {
                    usuario = "uRxr15PTS2TISErKZ8Hw"
                }
                navController.navigate(Rutas.Usuario.Ruta)
                } else {
                    texto = "Username and/or password incorrect. Please introduce them again"
                    email = ""
                    password = ""
                }
        } }) {
            Text("Log in")
        }
    }
}