package com.Quijano.lab3_juegodeadivinarnmero

import android.os.Bundle
import android.service.autofill.OnClickAction
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.random.Random
import com.Quijano.lab3_juegodeadivinarnmero.ui.theme.Lab3_JuegodeAdivinarNúmeroTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            App()
            /**
             * Lab3_JuegodeAdivinarNúmeroTheme {
             *                 Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
             *                     Greeting(
             *                         name = "Android",
             *                         modifier = Modifier.padding(innerPadding)
             *                     )
             *                 }
             *             }
             */

        }
    }
}
@Preview(showBackground = true)
@Composable
fun App(){
    var numTrue by remember { mutableStateOf(Random.nextInt(1, 100)) }
    var userGuess by remember { mutableStateOf("") }
    var intentos by remember { mutableStateOf(3) }
    var estadoGame by remember { mutableStateOf("") }
    var finGame by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Adivina un numero entre 1 a 100",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)

        )

        Text(
            text = "Tiene 3 intentos para adivinar",
            fontSize = 18.sp,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        Text(
            text = "Número a Adivinar: ?",
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(bottom = 16.dp))


        OutlinedTextField(
            value = userGuess,
            onValueChange = { if (it.isEmpty() || it.toIntOrNull() != null) userGuess = it },
            label = { Text("Ingrese su número") },
            singleLine = true,
            enabled = !finGame,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Button(
            onClick = {
                val guess = userGuess.toIntOrNull()
                if (guess != null){
                    when {
                        guess == numTrue -> {
                            estadoGame = "¡Correcto! Has adivinado el número."
                            finGame = true
                        }
                        guess < numTrue -> estadoGame = "El número es mayor"
                        else -> estadoGame = "El número es menor"
                    }

                    if (intentos == 0 && guess != numTrue) {
                        estadoGame = "¡Se acabaron los intentos! El número era 3"
                        finGame = true
                    }
                }



            },
            enabled = userGuess.isNotEmpty() && !finGame,
            modifier = Modifier.padding(bottom = 16.dp)
        ){ Text("Adivinar") }
        Text(
            text = estadoGame,
            fontSize = 16.sp,
            color = if (estadoGame.contains("Correcto")) MaterialTheme.colorScheme.primary
            else MaterialTheme.colorScheme.error,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(vertical = 8.dp)
        )

        if (finGame) {
            Button(
                onClick = {
                    numTrue = Random.nextInt(1, 101)
                    userGuess = ""
                    intentos = 3
                    estadoGame = ""
                    finGame = false
                },
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Text("Jugar de nuevo")
            }
        }
    }
}




/**
 * @Composable
 * fun Greeting(name: String, modifier: Modifier = Modifier) {
 *     Text(
 *         text = "Hello $name!",
 *         modifier = modifier
 *     )
 * }
 * @Preview(showBackground = true)
 * @Composable
 * fun otherpreview(){
 *     Text(text="Hello Kaily", modifier = Modifier.background(5,5,5))
 * }
 *
 * @Preview(showBackground = true)
 * @Composable
 * fun GreetingPreview() {
 *     Lab3_JuegodeAdivinarNúmeroTheme {
 *         Greeting("Tony")
 *     }
 * }

 */
