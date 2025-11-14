package com.example.calculadorafinanceira.model.bd

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "historicos")
data class Historico(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val formula: String,
    val resultado: Float
)
