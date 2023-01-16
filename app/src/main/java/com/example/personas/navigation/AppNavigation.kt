package com.example.personas.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.personas.screens.*


@Composable
fun AppNavigation() {
    val navigationController = rememberNavController()
    NavHost(navController = navigationController, startDestination = AppScreens.Index.ruta)

    {
        composable(AppScreens.Index.ruta) {Index(navigationController) }
        composable(AppScreens.NuevaPersona.ruta) {NuevaPersona(navigationController) }
        composable(AppScreens.ModificarPersona.ruta) {ModificarPersona(navigationController) }
        composable(AppScreens.EliminarPersona.ruta) {EliminarPersona(navigationController) }
        composable(AppScreens.BuscarPersona.ruta) {BuscarPersona(navigationController) }
        composable(AppScreens.ListaPersona.ruta) {ListaPersona(navigationController) }
    }
}