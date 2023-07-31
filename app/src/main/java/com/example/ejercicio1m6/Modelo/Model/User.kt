package com.example.ejercicio1m6.Modelo.Model

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName= "USER_TABLE")

data class User(
@PrimaryKey
val User: String,

val UserName: String,

val UserAge: Int? = 0

)