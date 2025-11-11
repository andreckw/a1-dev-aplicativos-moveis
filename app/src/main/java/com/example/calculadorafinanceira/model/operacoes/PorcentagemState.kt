package com.example.calculadorafinanceira.model.operacoes

import com.example.calculadorafinanceira.model.Operacoes

class PorcentagemState: IOperacaoState {
    override fun calcular(n1: Float, n2: Float): Float {
        return  n1 + (n1 * n2 / 100)
    }

    override fun calcular(n1: Float, n2: Float, op: IOperacaoState?, utilizarN1: Boolean): Float {
        var nPorcento = n2 / 100
        var nUtilizado = n1

        if (utilizarN1) {
            nPorcento = n1 / 100
            nUtilizado = n2
        }

        val newOp = op ?: Operacoes.ADICAO.state

        if (newOp.javaClass == Operacoes.ADICAO.state.javaClass
            || newOp.javaClass == Operacoes.SUBTRACAO.state.javaClass
        ) {
            nPorcento *= nUtilizado
        }

        //
        return newOp.calcular(n1=nUtilizado, n2=nPorcento)
    }


}