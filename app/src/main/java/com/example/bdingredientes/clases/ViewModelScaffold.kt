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
    var _Aleatorio2 = MutableStateFlow(mutableStateOf(false))
    var Aleatorio2 = _Aleatorio2.asStateFlow()
    var _borrar2 = MutableStateFlow(mutableStateOf(false))
    var borrar2 = _borrar2.asStateFlow()
    var _modificar2 = MutableStateFlow(mutableStateOf(false))
    var modificar2 = _modificar2.asStateFlow()
    var _puedeBorrar2 = MutableStateFlow(mutableStateOf(true))
    var puedeBorrar2 = _puedeBorrar2.asStateFlow()
    var _puedeModificar2 = MutableStateFlow(mutableStateOf(true))
    var puedeModificar2 = _puedeModificar2.asStateFlow()
}