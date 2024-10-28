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
    var _Aleatorio3 = MutableStateFlow(mutableStateOf(false))
    var Aleatorio3 = _Aleatorio3.asStateFlow()
    var _borrar3 = MutableStateFlow(mutableStateOf(false))
    var borrar3 = _borrar3.asStateFlow()
    var _modificar3 = MutableStateFlow(mutableStateOf(false))
    var modificar3 = _modificar3.asStateFlow()
    var _puedeBorrar3 = MutableStateFlow(mutableStateOf(true))
    var puedeBorrar3 = _puedeBorrar3.asStateFlow()
    var _puedeModificar3 = MutableStateFlow(mutableStateOf(true))
    var puedeModificar3 = _puedeModificar3.asStateFlow()
    var _Aleatorio4 = MutableStateFlow(mutableStateOf(false))
    var Aleatorio4 = _Aleatorio4.asStateFlow()
    var _borrar4 = MutableStateFlow(mutableStateOf(false))
    var borrar4 = _borrar4.asStateFlow()
    var _modificar4 = MutableStateFlow(mutableStateOf(false))
    var modificar4 = _modificar4.asStateFlow()
    var _puedeBorrar4 = MutableStateFlow(mutableStateOf(true))
    var puedeBorrar4 = _puedeBorrar4.asStateFlow()
    var _puedeModificar4 = MutableStateFlow(mutableStateOf(true))
    var puedeModificar4 = _puedeModificar4.asStateFlow()
}