package com.example.bdingredientes.ui.theme

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.bdingredientes.clases.Rutas
import com.example.bdingredientes.clases.ViewModelScaffold
import com.google.firebase.auth.FirebaseAuth

var auth = FirebaseAuth.getInstance()
var user = auth.currentUser
var usuario = ""
var admin = false

@Composable
fun PantallaLogin(navController: NavController, sf : ViewModelScaffold = viewModel()) {
    var texto by remember { mutableStateOf("Introduce your Firebase email and password")}
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val idioma = sf.english.collectAsState().value
    if (!idioma.value) {
        texto = "Introduce tu correo electrónico y contraseña de Firebase"
    }

    Column {
        Text(texto)
        val textoEmail: String = if (idioma.value) {
            "Email: "
        } else "Correo electrónico: "
        Text(textoEmail)
        val textoPlaceholderEmail: String = if (idioma.value) {
            "Insert your email"
        } else "Introduce tu correo electrónico"
        TextField(
            value = email,
            onValueChange = { texto -> email = texto },
            placeholder = { Text(textoPlaceholderEmail) })
        val textoPassword: String = if (idioma.value) {
            "Password: "
        } else "Contraseña: "
        Text(textoPassword)
        val textoPlaceholderPassword: String = if (idioma.value) {
            "Insert your password"
        } else "Introduce tu contraseña"
        TextField(
            value = password,
            onValueChange = { texto -> password = texto },
            visualTransformation = PasswordVisualTransformation(),
            placeholder = { Text(textoPlaceholderPassword) })
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
                "sofia@gmail.com" -> {
                    usuario = "TmSc90miHnpHdz76gVdZ"
                    admin = false
                }
                else -> {
                    usuario = "uRxr15PTS2TISErKZ8Hw"
                    admin = false
                }
            }
                navController.navigate(Rutas.Menu.ruta)
                } else {
                    texto = if (idioma.value) {
                        "Email and/or password incorrect. Please introduce them again"
                    } else "Correo electrónico y/o contraseña incorrecta. Por favor introdúcelos de nuevo"
                }
        } }) {
            val textoLogin: String = if (idioma.value) {
                "Log in"
            } else "Iniciar sesión"
            Text(textoLogin)
        }
        Row {
            val textoAuth: String = if (idioma.value) {
                "You are not authenticated?"
            } else "¿No estás autenticad@?"
            Text(textoAuth)
            Button(onClick = { navController.navigate(Rutas.Auth.ruta) }) {
                val textoRegistro: String = if (idioma.value) {
                    "Sign in"
                } else "Regístrate"
                Text(textoRegistro)
            }
        }
    }
}