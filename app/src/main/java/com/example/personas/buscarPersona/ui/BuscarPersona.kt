package com.example.personas.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.example.personas.R
import com.example.personas.buscarPersona.ui.BuscarPersonaViewModel
import com.example.personas.dbConection.db
import com.example.personas.dbConection.nombre_coleccion
import com.google.firebase.firestore.FirebaseFirestore

@Composable
fun BuscarPersona(navController: NavHostController, ViewModel: BuscarPersonaViewModel) {


    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painter = painterResource(R.drawable.fondo),
                contentScale = ContentScale.FillWidth),

        ) {

        Image(
            painter = painterResource(R.drawable.buscador),
            contentDescription = "LogoConsulta",
            modifier = Modifier.size(200.dp)
        )

        Spacer(modifier = Modifier.size(250.dp))

        // declaracion de variables
        val datos:String by ViewModel.datos.observeAsState(initial = "")
        val numero_persona:String by ViewModel.numero_persona.observeAsState(initial = "")
        val nombre_persona:String by ViewModel.nombre_persona.observeAsState(initial = "")
        val rol_persona:String by ViewModel.rol_persona.observeAsState(initial = "")
        val elemento_persona:String by ViewModel.elemento_persona.observeAsState(initial = "")
        val mensaje:String by ViewModel.mensaje.observeAsState(initial = "")


        OutlinedTextField(
            value = numero_persona,
            onValueChange = { ViewModel.onCompletedFields(nombre_busqueda = it) },
            label = { Text("Nombre de la Persona") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)

        )

        Spacer(modifier = Modifier.size(5.dp))

        Text(text = mensaje,
            color = Color(0xffFFFFFF),
            fontWeight = FontWeight.ExtraBold)

        Spacer(modifier = Modifier.size(5.dp))

        // PINTAMOS EL RESULTADO DE LA CONSULTA A LA BASE DE DATOS

        if (datos.isNotEmpty() && datos.isNotBlank()) {
            Text (text = "ID: " + numero_persona,
                color = Color(0xffFFFFFF),
                fontWeight = FontWeight.ExtraBold
            )
            Text (text = "Nombre: " + nombre_persona,
                color = Color(0xffFFFFFF),
                fontWeight = FontWeight.ExtraBold
            )
            Text (text = "ROL: " + rol_persona,
                color = Color(0xffFFFFFF),
                fontWeight = FontWeight.ExtraBold
            )
            Text (text = "Afinidad: " + elemento_persona,
                color = Color(0xffFFFFFF),
                fontWeight = FontWeight.ExtraBold
            )
        }


        Spacer(modifier = Modifier.size(10.dp))

    }
}