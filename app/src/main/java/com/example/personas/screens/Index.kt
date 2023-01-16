package com.example.personas.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.personas.R

@Composable
fun Index(navController: NavHostController) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painter = painterResource(R.drawable.fondo),
                contentScale = ContentScale.FillWidth),

        ) {



        Image(
            painter = painterResource(R.drawable.logo),
            contentDescription = "LogoPersona",
            modifier = Modifier.size(250.dp)
        )

        //Spacer(modifier = Modifier.size(10.dp))

        Spacer(modifier = Modifier.size(10.dp))

        Button(

            onClick = {
                navController.navigate("NuevaPersona")
            },
            shape = RoundedCornerShape(20.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color(0xFF000000),
                contentColor = Color.Yellow
            ),
            border = BorderStroke(1.dp, Color.Black)
        )
        {
            Text(text = "Añadir Persona")
        }

        Spacer(modifier = Modifier.size(8.dp))

        Button(
            onClick = {
                navController.navigate("ModificarPersona")
            },
            shape = RoundedCornerShape(20.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color(0xFF000000),
                contentColor = Color.Yellow
            ),
            border = BorderStroke(1.dp, Color.Black)
        )
        {
            Text(text = "Editar")
        }

        Spacer(modifier = Modifier.size(8.dp))

        Button(
            onClick = {
                navController.navigate("EliminarPersona")
            },
            shape = RoundedCornerShape(20.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color(0xFF000000),
                contentColor = Color.Yellow
            ),
            border = BorderStroke(1.dp, Color.Black)
        )
        {
            Text(text = "Eliminar")
        }

        Spacer(modifier = Modifier.size(8.dp))

        Button(
            onClick = {
                navController.navigate("BuscarPersona")
            },
            shape = RoundedCornerShape(20.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color(0xFF000000),
                contentColor = Color.Yellow
            ),
            border = BorderStroke(1.dp, Color.Black)
        )
        {
            Text(text = "Codice")
        }

        Spacer(modifier = Modifier.size(8.dp))

        Button(
            onClick = {
                navController.navigate("ListaPersona")
            },
            shape = RoundedCornerShape(20.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color(0xFF000000),
                contentColor = Color.Yellow
            ),
            border = BorderStroke(1.dp, Color.Black)
        )
        {
            Text(text = "Listado Completo")
        }

    }

}


@Preview(showBackground = true)
@Composable
fun Index () {
}