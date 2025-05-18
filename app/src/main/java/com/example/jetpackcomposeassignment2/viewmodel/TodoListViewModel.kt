package com.example.jetpackcomposeassignment2.viewmodel

import android.content.Context // Ensure this import is present
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpackcomposeassignment2.model.Todo
import com.example.jetpackcomposeassignment2.repository.TodoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed class TodoUiState {
    object Loading : TodoUiState()
    data class Success(val todos: List<Todo>) : TodoUiState()
    data class Error(val message: String) : TodoUiState()
}

class TodoListViewModel(
    private val repository: TodoRepository,
    private val context: Context
) : ViewModel() {

    private val _uiState = MutableStateFlow<TodoUiState>(TodoUiState.Loading)
    val uiState: StateFlow<TodoUiState> = _uiState.asStateFlow()

    init {
        loadTodos()
    }

    fun loadTodos() {
        if (!isInternetAvailable()) {
            _uiState.value = TodoUiState.Error("No internet connection. Please check your network and try again.")
            return
        }

        viewModelScope.launch {
            _uiState.value = TodoUiState.Loading
            try {
                val todos = repository.getTodos()
                _uiState.value = TodoUiState.Success(todos)
            } catch (e: Exception) {
                val errorMessage = e.localizedMessage ?: "Unknown error"
                _uiState.value = TodoUiState.Error(errorMessage)
            }
        }
    }

    private fun isInternetAvailable(): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkCapabilities = connectivityManager.activeNetwork?.let {
            connectivityManager.getNetworkCapabilities(it)
        }
        return networkCapabilities?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) == true
    }
}