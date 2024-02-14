package com.example.bdingredientes.clases

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ViewModelScaffold : ViewModel() {
    var _Aleatorio = MutableStateFlow(mutableStateOf(false))
    var Aleatorio = _Aleatorio.asStateFlow()
    var _borrar = MutableStateFlow(mutableStateOf(false))
    var borrar = _borrar.asStateFlow()
    var _modificar = MutableStateFlow(mutableStateOf(false))
    var modificar = _modificar.asStateFlow()
    var _puedeBorrar = MutableStateFlow(mutableStateOf(true))
    var puedeBorrar = _puedeBorrar.asStateFlow()
    var _puedeModificar = MutableStateFlow(mutableStateOf(true))
    var puedeModificar = _puedeModificar.asStateFlow()
}