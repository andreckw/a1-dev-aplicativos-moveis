package com.example.calculadorafinanceira.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calculadorafinanceira.ui.theme.CalculadoraFinanceiraTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CalculadoraFinanceiraTheme {
                Inicio()
            }
        }
    }
}


@Composable
fun Inicio() {
    var numeros by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize().padding(15.dp)) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            Text("Calculadora",
                fontSize=20.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold
            )
        }
        
        Text(numeros,modifier = Modifier.height(200.dp), fontSize = 40.sp)

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            Button(
                onClick = {
                    numeros += "+"
                }
            ) {
                Text("+")
            }

            Button(
                onClick = {
                    numeros += "1"
                }
            ) {
                Text("1")
            }

            Button(
                onClick = {
                    numeros += "2"
                }
            ) {
                Text("2")
            }

            Button(
                onClick = {
                    numeros += "3"
                }
            ) {
                Text("3")
            }
        }

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            Button(
                onClick = {
                    numeros += "-"
                }
            ) {
                Text("-")
            }

            Button(
                onClick = {
                    numeros += "4"
                }
            ) {
                Text("4")
            }

            Button(
                onClick = {
                    numeros += "5"
                }
            ) {
                Text("5")
            }

            Button(
                onClick = {
                    numeros += "6"
                }
            ) {
                Text("6")
            }
        }

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            Button(
                onClick = {
                    numeros += "*"
                }
            ) {
                Text("*")
            }

            Button(
                onClick = {
                    numeros += "7"
                }
            ) {
                Text("7")
            }

            Button(
                onClick = {
                    numeros += "8"
                }
            ) {
                Text("8")
            }

            Button(
                onClick = {
                    numeros += "9"
                }
            ) {
                Text("9")
            }
        }
        
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            Button(
                onClick = {
                    numeros += "/"
                }
            ) {
                Text("/")
            }

            Button(
                onClick = {
                    numeros = ""
                }
            ) {
                Text("C")
            }

            Button(
                onClick = {
                    numeros += "0"
                }
            ) {
                Text("0")
            }

            Button(
                onClick = {
                    numeros = ""
                }
            ) {
                Text("CE")
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun InicioPreview() {
    Inicio()
}