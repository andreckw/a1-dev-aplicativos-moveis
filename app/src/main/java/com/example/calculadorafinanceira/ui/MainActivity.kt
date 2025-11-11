package com.example.calculadorafinanceira.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.calculadorafinanceira.ui.theme.CalculadoraFinanceiraTheme
import com.example.calculadorafinanceira.viewmodel.CalculadoraViewModel

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
    val viewModel : CalculadoraViewModel = viewModel()
    val formula by viewModel.formula


    Column(modifier = Modifier.fillMaxSize().padding(15.dp)) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            Text("Calculadora",
                fontSize=20.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(15.dp))

        TextField(
            value = formula,
            onValueChange = {},
            modifier = Modifier.fillMaxWidth(),
            enabled = false,

            textStyle = TextStyle(fontSize = 30.sp,
                color = Color(0, 0, 0, 255),
                textAlign = TextAlign.Right
            ),
        )

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {

            Button(onClick = {
                viewModel.limparFormula(formula.length)
            }) {
                Text("C")
            }

            Button(onClick = {
                viewModel.limparFormula(1)
            }) {
                Text("<-")
            }

            Button(onClick = {
                viewModel.adicionarFormula("%")
            }) {
                Text("%")
            }

            Button(onClick = {
                viewModel.adicionarFormula("/")
            }) {
                Text("/")
            }
        }

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {

            Button(onClick = {
                viewModel.adicionarFormula("7")
            }) {
                Text("7")
            }

            Button(onClick = {
                viewModel.adicionarFormula("8")
            }) {
                Text("8")
            }

            Button(onClick = {
                viewModel.adicionarFormula("9")
            }) {
                Text("9")
            }

            Button(onClick = {
                viewModel.adicionarFormula("*")
            }) {
                Text("*")
            }
        }

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {

            Button(onClick = {
                viewModel.adicionarFormula("4")
            }) {
                Text("4")
            }

            Button(onClick = {
                viewModel.adicionarFormula("5")
            }) {
                Text("5")
            }

            Button(onClick = {
                viewModel.adicionarFormula("6")
            }) {
                Text("6")
            }

            Button(onClick = {
                viewModel.adicionarFormula("-")
            }) {
                Text("-")
            }
        }

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {

            Button(onClick = {
                viewModel.adicionarFormula("1")
            }) {
                Text("1")
            }

            Button(onClick = {
                viewModel.adicionarFormula("2")
            }) {
                Text("2")
            }

            Button(onClick = {
                viewModel.adicionarFormula("3")
            }) {
                Text("3")
            }

            Button(onClick = {
                viewModel.adicionarFormula("+")
            }) {
                Text("+")
            }
        }

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {

            Button(onClick = {
                viewModel.limparFormula(formula.length)
            }) {
                // Limpa o historio junto
                Text("AC")
            }

            Button(onClick = {
                viewModel.adicionarFormula("0")
            }) {
                Text("0")
            }

            Button(onClick = {
                viewModel.adicionarFormula(".")
            }) {
                Text(".")
            }

            Button(onClick = {
                viewModel.calcular()
            }) {
                Text("=")
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun InicioPreview() {
    Inicio()
}