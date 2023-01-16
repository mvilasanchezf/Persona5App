package com.example.personas.navigation

sealed class AppScreens(val ruta: String){
    object Index: AppScreens("Index")
    object NuevaPersona: AppScreens("NuevaPersona")
    object ModificarPersona: AppScreens("ModificarPersona")
    object EliminarPersona: AppScreens("EliminarPersona")
    object BuscarPersona: AppScreens("BuscarPersona")
    object ListaPersona: AppScreens("ListaPersona")
}