package com.example.personas.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*

import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.personas.R
import com.google.firebase.firestore.FirebaseFirestore

@Composable
fun ListaPersona(navController: NavHostController) {


    val db = FirebaseFirestore.getInstance()


    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
            .paint(
                painter = painterResource(R.drawable.fondo),
                contentScale = ContentScale.FillWidth),

        ) {

        Image(
            painter = painterResource(R.drawable.buscador),
            contentDescription = "LogoMostrar",
            modifier = Modifier.size(200.dp)
        )

        Spacer(modifier = Modifier.size(20.dp))

        var datos by remember { mutableStateOf("") }

        var id by remember { mutableStateOf("") }
        var nombre_persona by remember { mutableStateOf("") }
        var rol_persona by remember { mutableStateOf("") }
        var elemento_persona by remember { mutableStateOf("") }

        Button(
            onClick = {

                datos = ""
                id =""
                nombre_persona=""
                rol_persona=""
                elemento_persona=""


                db.collection("pookemon")
                    .get()

                    .addOnSuccessListener { resultado ->
                        for (encontrado in resultado) {
                            datos += "ID: : ${encontrado.getString("ID Persona")?: "No disponible"}\n"
                            datos += "Nombre ${encontrado.getString("nombre") ?: "No disponible"}\n"
                            datos += "Rol: ${encontrado.getString("rol")?: "No disponible"}\n"
                            datos += "Elemento: ${encontrado.getString("elemento")?: "No disponible"}\n"

                        }

                        if (datos.isEmpty()) {
                            datos = "Codice vacio"
                        }
                    }

                    .addOnFailureListener { resultado ->
                        datos = "La conexión a FireStore no se ha podido completar"
                    }
            },

            shape = RoundedCornerShape(20.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color(0xFF000000),
                contentColor = Color.White
            ),
            border = BorderStroke(1.dp, Color.Black)
        )
        {

            Text(text = "Abrir codice")
        }

        Spacer(modifier = Modifier.size(10.dp))

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            elevation = 12.dp,
            shape = MaterialTheme.shapes.small,
            backgroundColor = Color.Black.copy(alpha = 0.8f),
            contentColor = Color.DarkGray,
            border = BorderStroke(1.dp, Color.Yellow)
        ){
            Text (
                text = datos,
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 18.sp,
                    fontFamily = FontFamily.Monospace,
                    letterSpacing = 4.sp,
                    textAlign = TextAlign.Center,)
            )
        }

    }
}