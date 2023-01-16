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
fun BuscarPersona(navController: NavHostController) {

    var nombre_coleccion = "personas"
    val db = FirebaseFirestore.getInstance()
    //var datos by remember { mutableStateOf("") }

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

        Spacer(modifier = Modifier.size(20.dp))

        //DECLARAMOS LA VARIABLE QUE VA A RECOGER LOS DATOS DE LA CONSULTA CON EL ESTADO REMEMBER
        var datos by remember { mutableStateOf("") }
        var id by remember { mutableStateOf("") }
        var nombre_persona by remember { mutableStateOf("") }
        var rol_persona by remember { mutableStateOf("") }
        var elemento_persona by remember { mutableStateOf("") }

        var nombre_busqueda by remember { mutableStateOf("") }
        val field_busqueda ="nombre"
        OutlinedTextField(
            value = nombre_busqueda,
            onValueChange = { nombre_busqueda = it },
            label = { Text("Introduce el nombre a consultar") },
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

        Button(
            onClick = {

                // VACIAMOS VARIABLE AL DAR AL BOTON
                datos = ""
                id = ""
                nombre_persona = ""
                rol_persona = ""
                elemento_persona = ""

                // HACEMOS LA CONSULTA A LA COLECCION CON GET
                db.collection(nombre_coleccion)
                    .whereEqualTo(field_busqueda,nombre_busqueda)
                    .get()

                    //SI SE CONECTA CORRECTAMENTE
                    // RECORRO TODOS LOS DATOS ENCONTRADOS EN LA COLECCIÓN Y LOS ALMACENO EN DATOS
                    .addOnSuccessListener { resultado ->
                        for (encontrado in resultado) {
                            //Para crear un HashMap con todos los datos
                            datos += "${encontrado.id}: ${encontrado.data}\n"

                            //Para crear un HashMap con todos los datos
                            id += encontrado["persona"].toString()
                            nombre_persona += encontrado["nombre"].toString()
                            rol_persona += encontrado["habilidad"].toString()
                            elemento_persona += encontrado["elemento"].toString()
                            //Log.i("DATOS:", datos)
                        }

                        if (datos.isEmpty()) {
                            datos = "No has establecido una conexion con el codice"
                        }
                    }
                    //SI NO CONECTA CORRECTAMENTE
                    .addOnFailureListener { resultado ->
                        datos = "La conexión a FireStore no se ha podido completar"
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

            Text(text = "Consultar el grimorio")
        }

        Spacer(modifier = Modifier.size(10.dp))

        // PINTAMOS EL RESULTADO DE LA CONSULTA A LA BASE DE DATOS
        //Text (text = datos)
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            elevation = 112.dp,
            shape = MaterialTheme.shapes.small,
            backgroundColor = Color.Black,
            contentColor = Color.DarkGray,
            border = BorderStroke(1.dp, Color.Black)
        ) {
            Text (text = "ID: " + id,color = Color.Black)
        }
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            elevation = 112.dp,
            shape = MaterialTheme.shapes.small,
            backgroundColor = Color.Black,
            contentColor = Color.DarkGray,
            border = BorderStroke(1.dp, Color.Black)
        ) {
            Text (text = "Nombre: " + nombre_persona,color = Color.Black)
        }
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            elevation = 112.dp,
            shape = MaterialTheme.shapes.small,
            backgroundColor = Color.Black,
            contentColor = Color.DarkGray,
            border = BorderStroke(1.dp, Color.Black)
        ) {
            Text (text = "Habilidad: " + rol_persona,color = Color.Black)
        }
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            elevation = 112.dp,
            shape = MaterialTheme.shapes.small,
            backgroundColor = Color.White,
            contentColor = Color.DarkGray,
            border = BorderStroke(1.dp, Color.Black)
        ) {
            Text (text = "Elemento: " + elemento_persona,color = Color.Black)
        }
    }
}