package com.example.bdingredientes.clases

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ViewModelScaffold : ViewModel() {
    private var _aleatorio1 = MutableStateFlow(mutableStateOf(false))
    var aleatorio1 = _aleatorio1.asStateFlow()
    private var _borrar = MutableStateFlow(mutableStateOf(false))
    var borrar = _borrar.asStateFlow()
    private var _modificar = MutableStateFlow(mutableStateOf(false))
    var modificar = _modificar.asStateFlow()
    private var _puedeBorrar = MutableStateFlow(mutableStateOf(true))
    var puedeBorrar = _puedeBorrar.asStateFlow()
    private var _puedeModificar = MutableStateFlow(mutableStateOf(true))
    var puedeModificar = _puedeModificar.asStateFlow()
    private var _aleatorio2 = MutableStateFlow(mutableStateOf(false))
    var aleatorio2 = _aleatorio2.asStateFlow()
    private var _borrar2 = MutableStateFlow(mutableStateOf(false))
    var borrar2 = _borrar2.asStateFlow()
    private var _modificar2 = MutableStateFlow(mutableStateOf(false))
    var modificar2 = _modificar2.asStateFlow()
    private var _puedeBorrar2 = MutableStateFlow(mutableStateOf(true))
    var puedeBorrar2 = _puedeBorrar2.asStateFlow()
    private var _puedeModificar2 = MutableStateFlow(mutableStateOf(true))
    var puedeModificar2 = _puedeModificar2.asStateFlow()
    private var _aleatorio3 = MutableStateFlow(mutableStateOf(false))
    var aleatorio3 = _aleatorio3.asStateFlow()
    private var _borrar3 = MutableStateFlow(mutableStateOf(false))
    var borrar3 = _borrar3.asStateFlow()
    private var _modificar3 = MutableStateFlow(mutableStateOf(false))
    var modificar3 = _modificar3.asStateFlow()
    private var _puedeBorrar3 = MutableStateFlow(mutableStateOf(true))
    var puedeBorrar3 = _puedeBorrar3.asStateFlow()
    private var _puedeModificar3 = MutableStateFlow(mutableStateOf(true))
    var puedeModificar3 = _puedeModificar3.asStateFlow()
    private var _aleatorio4 = MutableStateFlow(mutableStateOf(false))
    var aleatorio4 = _aleatorio4.asStateFlow()
    private var _borrar4 = MutableStateFlow(mutableStateOf(false))
    var borrar4 = _borrar4.asStateFlow()
    private var _modificar4 = MutableStateFlow(mutableStateOf(false))
    var modificar4 = _modificar4.asStateFlow()
    private var _puedeBorrar4 = MutableStateFlow(mutableStateOf(true))
    var puedeBorrar4 = _puedeBorrar4.asStateFlow()
    private var _puedeModificar4 = MutableStateFlow(mutableStateOf(true))
    var puedeModificar4 = _puedeModificar4.asStateFlow()
}