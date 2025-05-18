package com.example.jetpackcomposeassignment2.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todos")
data class TodoEntity(
    val userId: Int,
    @PrimaryKey val id: Int,
    val title: String,
    val completed: Boolean
)
