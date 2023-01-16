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
fun EliminarPersona(navController: NavHostController) {


    val db = FirebaseFirestore.getInstance()

    var nombre_coleccion = "personas"
    var nombre_persona by remember { mutableStateOf("") }


    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painter = painterResource(R.drawable.fondo),
                contentScale = ContentScale.FillWidth),

        ) {

        Image(
            painter = painterResource(R.drawable.eliminar),
            contentDescription = "LogoBorrar",
            modifier = Modifier.size(200.dp)
        )

        Spacer(modifier = Modifier.size(10.dp))

        OutlinedTextField(
            value = nombre_persona,
            onValueChange = { nombre_persona = it },
            label = { Text("Introduce el nombre del confidente que deseas eliminar") },
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

        var mensaje_borrado by remember { mutableStateOf("") }

        Button(

            onClick = {
                if (nombre_persona.isNotBlank()) {
                    db.collection(nombre_coleccion)
                        .document(nombre_persona)
                        .delete()
                        .addOnSuccessListener {
                            mensaje_borrado = "Enlace disuelto correctamente"
                            nombre_persona = ""
                        }
                        .addOnFailureListener {
                            mensaje_borrado = "Es muy pesado, no quiere irse"
                            nombre_persona = ""
                        }
                }
            },

            shape = RoundedCornerShape(20.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color(0xFF000000),
                contentColor = Color.Black
            ),
            border = BorderStroke(1.dp, Color.Black)
        )
        {

            Text(text = "Borrar")


        }
        Spacer(modifier = Modifier.size(5.dp))
        Text(text = mensaje_borrado)
    }
}