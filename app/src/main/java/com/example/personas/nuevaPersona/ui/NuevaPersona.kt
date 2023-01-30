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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.personas.R
import com.example.personas.dbConection.db
import com.example.personas.nuevaPersona.ui.NuevaPersonaViewModel
import com.google.firebase.firestore.FirebaseFirestore


@Composable

fun NuevaPersona(navController: NavHostController, ViewModel: NuevaPersonaViewModel) {


    val mensaje_confirmacion: String by ViewModel.mensaje_confirmacion.observeAsState(initial = "")
    val numero_persona: String by ViewModel.numero_persona.observeAsState(initial = "")
    val nombre_persona: String by ViewModel.nombre_persona.observeAsState(initial = "")
    val rol_persona: String by ViewModel.rol_persona.observeAsState(initial = "")
    val elemento_persona: String by ViewModel.elemento_persona.observeAsState(initial = "")




    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painter = painterResource(R.drawable.fondo),
                contentScale = ContentScale.FillWidth),

        ) {

        Image(
            painter = painterResource(R.drawable.enlazar),
            contentDescription = "LogoAñadir",
            modifier = Modifier.size(200.dp)
        )

        Spacer(modifier = Modifier.size(20.dp))

        OutlinedTextField(
            value = numero_persona,
            onValueChange = { ViewModel.onCompletedFields(numero_persona = it, nombre_persona = nombre_persona, rol_persona = rol_persona, elemento_persona = elemento_persona) },
            label = { Text("ID Persona:") },
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.Black,
                unfocusedBorderColor = Color.White,
                focusedLabelColor = Color.Black,
                unfocusedLabelColor = Color.White,
                backgroundColor = Color.Gray.copy(alpha = 0.4f)),
            singleLine = true,
        )

        Spacer(modifier = Modifier.size(5.dp))

        OutlinedTextField(
            value = nombre_persona,
            onValueChange = { ViewModel.onCompletedFields(numero_persona = numero_persona, nombre_persona = it, rol_persona = rol_persona, elemento_persona = elemento_persona) },
            label = { Text("Nombre Persona:") },
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.Black,
                unfocusedBorderColor = Color.White,
                focusedLabelColor = Color.Black,
                unfocusedLabelColor = Color.White,
                backgroundColor = Color.Gray.copy(alpha = 0.4f)),
            singleLine = true,
        )

        Spacer(modifier = Modifier.size(5.dp))

        OutlinedTextField(
            value = rol_persona,
            onValueChange = { ViewModel.onCompletedFields(numero_persona = numero_persona, nombre_persona = nombre_persona, rol_persona = it, elemento_persona = elemento_persona) },
            label = { Text("Rol Persona:") },
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.Black,
                unfocusedBorderColor = Color.White,
                focusedLabelColor = Color.Black,
                unfocusedLabelColor = Color.White,
                backgroundColor = Color.Gray.copy(alpha = 0.4f)),
            singleLine = true,
        )

        Spacer(modifier = Modifier.size(5.dp))



        OutlinedTextField(
            value = elemento_persona,
            onValueChange = { ViewModel.onCompletedFields(numero_persona = numero_persona, nombre_persona = nombre_persona, rol_persona = rol_persona, elemento_persona = it) },
            label = { Text("Elemento Persona:") },
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.Black,
                unfocusedBorderColor = Color.White,
                focusedLabelColor = Color.Black,
                unfocusedLabelColor = Color.White,
                backgroundColor = Color.Gray.copy(alpha = 0.4f)),
            singleLine = true,
        )


        Spacer(modifier = Modifier.size(5.dp))

        val dato = hashMapOf(
            "ID Persona" to numero_persona.toString(),
            "nombre" to nombre_persona.toString(),
            "rol" to rol_persona.toString(),
            "elemento" to elemento_persona.toString(),

        )


        Button(
            onClick = {
                ViewModel.insertarPersona(numero_persona, nombre_persona, rol_persona, elemento_persona, dato)
                ViewModel.emptyFields()

            },
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),

        )
        {

            Text(text = "Enlazar")


        }
        Spacer(modifier = Modifier.size(5.dp))

        Text(text = mensaje_confirmacion, color = Color.White)

        Spacer(modifier = Modifier.size(5.dp))

        Button(
            onClick = {
                navController.navigate("com/example/personas/Index")
            },
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
        )
        {
            Text(text = "Menú Inicial")
        }
    }

}

