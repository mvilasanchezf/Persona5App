package com.example.personas.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.personas.R
import com.google.firebase.firestore.FirebaseFirestore


@Composable

fun NuevaPersona(navController: NavHostController) {
    val db = FirebaseFirestore.getInstance()

    var nombre_coleccion = "personas"
    var id by remember { mutableStateOf("") }
    var nombre_persona by remember { mutableStateOf("") }
    var rol_persona by remember { mutableStateOf("") }
    var elemento_persona by remember { mutableStateOf("") }



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
            value = id,
            onValueChange = { id = it },
            label = { Text("ID Persona") },
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.Yellow,
                unfocusedBorderColor = Color.White,
                focusedLabelColor = Color.Yellow,
                unfocusedLabelColor = Color.White,
                cursorColor = Color.Yellow,
                backgroundColor = Color.Gray.copy(alpha = 0.4f)),
            singleLine = true,
        )

        Spacer(modifier = Modifier.size(5.dp))

        OutlinedTextField(
            value = nombre_persona,
            onValueChange = { nombre_persona = it },
            label = { Text("Introduce el nombre") },
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.Yellow,
                unfocusedBorderColor = Color.White,
                focusedLabelColor = Color.Yellow,
                unfocusedLabelColor = Color.White,
                backgroundColor = Color.Gray.copy(alpha = 0.4f)),
            singleLine = true,
        )

        Spacer(modifier = Modifier.size(5.dp))

        OutlinedTextField(
            value = rol_persona,
            onValueChange = { rol_persona = it },
            label = { Text("Introduce el rol en el equipo") },
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.Yellow,
                unfocusedBorderColor = Color.White,
                focusedLabelColor = Color.Yellow,
                unfocusedLabelColor = Color.White,
                backgroundColor = Color.Gray.copy(alpha = 0.4f)),
            singleLine = true,
        )

        Spacer(modifier = Modifier.size(5.dp))



        OutlinedTextField(
            value = elemento_persona,
            onValueChange = { elemento_persona = it },
            label = { Text("Introduce su afinidad elemental") },
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.Yellow,
                unfocusedBorderColor = Color.White,
                focusedLabelColor = Color.Yellow,
                unfocusedLabelColor = Color.White,
                backgroundColor = Color.Gray.copy(alpha = 0.4f)),
            singleLine = true,
        )


        Spacer(modifier = Modifier.size(5.dp))
        val dato = hashMapOf(
            "ID Persona" to id.toString(),
            "nombre" to nombre_persona.toString(),
            "rol" to rol_persona.toString(),
            "elemento" to elemento_persona.toString(),

        )

        var mensaje_confirmacion by remember { mutableStateOf("") }

        Button(

            onClick = {
                db.collection(nombre_coleccion)
                    .document(id)
                    .set(dato)
                    .addOnSuccessListener {
                        mensaje_confirmacion ="Persona enlazada correctamente"
                        id =""
                        nombre_persona=""
                        rol_persona=""
                        elemento_persona=""

                    }
                    .addOnFailureListener {
                        mensaje_confirmacion ="No has llegado a un acuerdo"
                        id =""
                        nombre_persona=""
                        rol_persona=""
                        elemento_persona=""
                    }
            },

            shape = RoundedCornerShape(20.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color(0xFF000000),
                contentColor = Color.Yellow
            ),
            border = BorderStroke(1.dp, Color.Black)
        )
        {

            Text(text = "Enlazar")


        }
        Spacer(modifier = Modifier.size(5.dp))
        Text(text = mensaje_confirmacion)
    }

}

