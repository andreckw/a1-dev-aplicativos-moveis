package com.example.calculadorafinanceira.data

import retrofit2.http.GET

interface ApiService {
    @GET("/basicos")
    suspend fun listarHistorico(): List<Historico>
}