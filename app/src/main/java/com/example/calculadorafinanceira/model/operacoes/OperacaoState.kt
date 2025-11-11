package com.example.calculadorafinanceira.model.operacoes

interface IOperacaoState {

    fun calcular(n1: Float, n2: Float): Float;

}