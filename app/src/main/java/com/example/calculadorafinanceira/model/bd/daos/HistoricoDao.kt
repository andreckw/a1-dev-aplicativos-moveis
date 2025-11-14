package com.example.calculadorafinanceira.model.bd.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.calculadorafinanceira.model.bd.Historico

@Dao
interface HistoricoDao {

    @Insert
    suspend fun inserir(historico: Historico)

    @Query("SELECT * FROM historicos")
    suspend fun buscarTodos(): List<Historico>
}