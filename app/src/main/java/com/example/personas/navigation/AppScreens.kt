package com.example.personas.navigation

sealed class AppScreens(val ruta: String){
    object Index: AppScreens("com/example/personas/index")
    object NuevaPersona: AppScreens("com/example/personas/nuevaPersona")
    object ModificarPersona: AppScreens("com/example/personas/modificarPersona")
    object EliminarPersona: AppScreens("com/example/personas/eliminarPersona")
    object BuscarPersona: AppScreens("com/example/personas/buscarPersona")
    object ListaPersona: AppScreens("com/example/personas/listaPersona")
}