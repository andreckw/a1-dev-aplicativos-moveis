package com.example.calculadorafinanceira.model.operacoes

class AdicaoState: IOperacaoState {

    override fun calcular(n1: Float, n2: Float): Float {
        return n1 + n2
    }
}