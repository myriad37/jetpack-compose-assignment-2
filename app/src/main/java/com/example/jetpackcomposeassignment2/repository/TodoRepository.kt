package com.example.jetpackcomposeassignment2.repository

import android.content.Context
import androidx.room.Room
import com.example.jetpackcomposeassignment2.local.TodoDao
import com.example.jetpackcomposeassignment2.local.TodoDatabase
import com.example.jetpackcomposeassignment2.local.TodoEntity
import com.example.jetpackcomposeassignment2.model.Todo
import com.example.jetpackcomposeassignment2.network.RetrofitInstance

class TodoRepository(context: Context) {

    // Initialize Room DB
    private val db: TodoDatabase = Room.databaseBuilder(
        context.applicationContext,
        TodoDatabase::class.java,
        "todo_database"
    ).build()

    private val dao: TodoDao = db.todoDao()

    // Fetch data from Room first, then update from network
    suspend fun getTodos(): List<Todo> {
        val cachedTodos = dao.getAllTodos()
        return if (cachedTodos.isNotEmpty()) {
            cachedTodos.map { it.toTodo() }
        } else {
            val remoteTodos = RetrofitInstance.api.getTodos()
            dao.insertTodos(remoteTodos.map { it.toEntity() })
            remoteTodos
        }
    }

    // Entity to model
    private fun TodoEntity.toTodo(): Todo {
        return Todo(
            userId = this.userId,
            id = this.id,
            title = this.title,
            completed = this.completed
        )
    }

    // Model to entity
    private fun Todo.toEntity(): TodoEntity {
        return TodoEntity(
            userId = this.userId,
            id = this.id,
            title = this.title,
            completed = this.completed
        )
    }
}