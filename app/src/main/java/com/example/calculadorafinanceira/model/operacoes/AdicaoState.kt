package com.example.calculadorafinanceira.model.operacoes

import com.example.calculadorafinanceira.model.Operacoes

class AdicaoState: IOperacaoState {

    override fun calcular(n1: Float, n2: Float): Float {
        return n1 + n2
    }

    override fun calcular(n1: Float, n2: Float, op: IOperacaoState?, utilizarN1: Boolean): Float {
        return n1 + n2
    }
}