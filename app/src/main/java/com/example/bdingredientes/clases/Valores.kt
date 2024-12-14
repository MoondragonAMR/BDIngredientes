package com.example.bdingredientes.clases

import androidx.compose.runtime.mutableStateOf
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

var codigo : String = ""
var nombre : String = ""
var tipo : String = ""
var sabor : String = ""
var deCelebracion : Boolean = false
var celebracion : String = ""
var comida2 : String = ""
var juego3 : String = ""
var parte2 : String = ""
var numero : Int = 0
var codigo2 : String = ""
var nombre2 : String = ""
var tipo2 : String = ""
var celebracion2 : String = ""
var comida : String = ""
var juego2 : String = ""
var parte : String = ""
var numero2 : Int = 0
private var _recognizedTextState = MutableStateFlow(mutableStateOf("Aquí aparecerá el texto transcrito"))
var recognizedTextState = _recognizedTextState.asStateFlow()
