package com.example.calculadorafinanceira.model.operacoes

import com.example.calculadorafinanceira.model.Operacoes

interface IOperacaoState {

    fun calcular(n1: Float, n2: Float): Float;

    fun calcular(n1: Float, n2: Float, op: IOperacaoState?, utilizarN1: Boolean): Float;

}