package com.example.calculadorafinanceira.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.calculadorafinanceira.model.Operacoes

class CalculadoraViewModel : ViewModel() {

    var formula = mutableStateOf("")
        private set

    fun adicionarFormula(valor: String) {

        var ultimoElemento = ""
        if (formula.value.isNotEmpty()) {
            ultimoElemento = formula.value[formula.value.length - 1].toString()
        }

        var ultimoElementoOp = false
        var novoElementoOp = false

        for (op in Operacoes.entries) {
            if (op.op == ultimoElemento && !ultimoElementoOp) {
                ultimoElementoOp = true
            }

            if (op.op == valor && !novoElementoOp) {
                novoElementoOp = true
            }

        }

        if (formula.value.isEmpty() && novoElementoOp) {
            formula.value = "0"
        }


        if (novoElementoOp && ultimoElementoOp) {
            formula.value = formula.value.dropLast(1)
        }


        formula.value += valor
    }

    fun limparFormula(qtd: Int) {
        formula.value = formula.value.dropLast(qtd)
    }

    fun calcular(){

        var operacao: Operacoes? = null
        var fracionado: List<String> = mutableListOf()

        for (op in Operacoes.entries) {
            // Dropa o primeiro para o caso de ser numero negativo
            if (formula.value.drop(1).contains(op.op)) {
                fracionado = formula.value.split(op.op)
                operacao = op
                break
            }
        }

        if (operacao == null) {
            return ;
        }

        var n1 = fracionado[0].replace(Operacoes.SUBTRACAO.op, "").toFloat()
        var n2 = fracionado[1].replace(Operacoes.SUBTRACAO.op, "").toFloat()


        if (fracionado[0].contains(Operacoes.SUBTRACAO.op)) {
            n1 *= -1
        }

        if (fracionado[1].contains(Operacoes.SUBTRACAO.op)) {
            n2 *= -1
        }


        formula.value = operacao.state.calcular(n1, n2).toString().replace(".0", "")

    }
}