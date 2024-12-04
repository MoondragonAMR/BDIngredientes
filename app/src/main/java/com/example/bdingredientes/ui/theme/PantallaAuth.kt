package com.example.bdingredientes.ui.theme

import androidx.compose.foundation.layout.Column
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

@Composable
fun PantallaAuth(navController: NavController, sf : ViewModelScaffold = viewModel()) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var texto by remember { mutableStateOf("Insert the new user's email and password")}
    val idioma = sf.english.collectAsState().value

    Column {
        texto = if (idioma.value) {
            "Insert the new user's email and password"
        } else "Introduce el correo electrónico y la contraseña del nuevo usuario"
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
        Button(onClick = {
            auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
                    task -> if (task.isSuccessful) {
                        navController.navigate(Rutas.Login.ruta)
                    } else {
                    texto = if (idioma.value) {
                        "Email must contain an @. Password must contain a number. Please introduce them again"
                    } else "El correo electrónico debe contener un @. La contraseña debe contener un número. Por favor introdúcelos de nuevo"
                    email = ""
                    password = ""
                    }
            }
        }) {
            val textoConfirm: String = if (idioma.value) {
                "Confirm"
            } else "Confirmar"
            Text(textoConfirm)
        }
    }
}