package com.example.calculadorafinanceira.model

import com.example.calculadorafinanceira.model.operacoes.AdicaoState
import com.example.calculadorafinanceira.model.operacoes.DivisaoState
import com.example.calculadorafinanceira.model.operacoes.IOperacaoState
import com.example.calculadorafinanceira.model.operacoes.SubtracaoState

enum class Operacoes (val op: String, val state: IOperacaoState) {
    ADICAO("+", AdicaoState()),
    SUBTRACAO("-", SubtracaoState()),
    DIVISAO("/", DivisaoState()),
    MULTIPLICACAO("*", SubtracaoState()),
    
    
}