package com.example.calculadorafinanceira.data

import com.example.calculadorafinanceira.model.bd.Historico
import retrofit2.http.GET

interface ApiService {
    @GET("/basicos")
    suspend fun listarHistorico(): List<Historico>
}