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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.calculadorafinanceira.model.bd.AppDatabase
import com.example.calculadorafinanceira.ui.theme.CalculadoraFinanceiraTheme
import com.example.calculadorafinanceira.viewmodel.CalculadoraViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CalculadoraFinanceiraTheme {
                AppNavigator()
            }
        }
    }
}


@Composable
fun AppNavigator(){
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "inicio"
    ){
        composable("inicio"){
            Inicio(
                irParaFinanceira = {
                    navController.navigate("financeira")
                }
            )
        }
        composable("financeira") {
            TelaCalculadoraFinanceira(
                voltar = { navController.popBackStack() }
            )
        }
    }
}

@Composable
fun TelaCalculadoraFinanceira(voltar: () -> Unit) {
    var capital by remember { mutableStateOf("") }
    var taxa by remember { mutableStateOf("") }
    var tempo by remember { mutableStateOf("") }

    var resultadoSimples by remember { mutableStateOf("") }
    var resultadoComposto by remember { mutableStateOf("") }
    var resultadoPrice by remember { mutableStateOf("") }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(20.dp)
    ) {

        Button(
            onClick = { voltar() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Voltar")
        }

        Spacer(Modifier.height(20.dp))

        Text(
            "Calculadora Financeira",
            fontSize = 22.sp
        )

        Spacer(Modifier.height(20.dp))

        TextField(
            value = capital,
            onValueChange = { capital = it },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Capital (R$)") }
        )

        TextField(
            value = taxa,
            onValueChange = { taxa = it },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Taxa (%)") }
        )

        TextField(
            value = tempo,
            onValueChange = { tempo = it },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Tempo (meses)") }
        )

        Spacer(Modifier.height(20.dp))


        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                val c = capital.toDoubleOrNull() ?: 0.0
                val i = taxa.toDoubleOrNull()?.div(100) ?: 0.0
                val t = tempo.toDoubleOrNull() ?: 0.0

                val juros = c * i * t
                val montante = c + juros

                resultadoSimples = "Montante: R$ %.2f (Juros: R$ %.2f)".format(montante, juros)
            }
        ) {
            Text("Calcular Juros Simples")
        }

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                val c = capital.toDoubleOrNull() ?: 0.0
                val i = taxa.toDoubleOrNull()?.div(100) ?: 0.0
                val t = tempo.toDoubleOrNull() ?: 0.0

                val montante = c * Math.pow((1 + i), t)
                val juros = montante - c

                resultadoComposto = "Montante: R$ %.2f (Juros: R$ %.2f)".format(montante, juros)
            }
        ) {
            Text("Calcular Juros Compostos")
        }

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                val p = capital.toDoubleOrNull() ?: 0.0
                val i = taxa.toDoubleOrNull()?.div(100) ?: 0.0
                val n = tempo.toDoubleOrNull() ?: 0.0

                val pagamento =
                    (p * i) / (1 - Math.pow(1 + i, -n))

                resultadoPrice =
                    "Prestação mensal: R$ %.2f".format(pagamento)
            }
        ) {
            Text("Calcular Amortização")
        }

        Spacer(Modifier.height(20.dp))

        if (resultadoSimples.isNotEmpty())
            Text("Juros Simples: $resultadoSimples")

        if (resultadoComposto.isNotEmpty())
            Text("Juros Compostos: $resultadoComposto")

        if (resultadoPrice.isNotEmpty())
            Text("Amortização: $resultadoPrice")
    }
}

@Composable
fun Inicio(irParaFinanceira: () -> Unit = {}) {
    val viewModel : CalculadoraViewModel = viewModel()
    val formula by viewModel.formula
    var historio by viewModel.historico
    val db = AppDatabase.getDatabase(LocalContext.current)

    LaunchedEffect(Unit) {
        viewModel.carregarHistorico(db.historicoDao())
    }


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

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { irParaFinanceira() }
        ) {
            Text("Ir para Calculadora Financeira")
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text("Histórico:")

        LazyColumn {
            items (viewModel.historico.value) {


                Text("${it.formula} = ${it.resultado}")

            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun InicioPreview() {
    Inicio()
}