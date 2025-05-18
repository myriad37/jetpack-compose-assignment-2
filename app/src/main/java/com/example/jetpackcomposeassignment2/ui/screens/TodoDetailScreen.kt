package com.example.jetpackcomposeassignment2.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposeassignment2.model.Todo
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoDetailScreen(todo: Todo, onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Todo Detail") },
                navigationIcon = {
                    IconButton(onClick = { onBack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text("ID: ${todo.id}", style = MaterialTheme.typography.titleLarge)
            Text("User ID: ${todo.userId}", style = MaterialTheme.typography.bodyLarge)
            Text("Title: ${todo.title}", style = MaterialTheme.typography.bodyLarge)
            Text(
                "Status: ${if (todo.completed) "Completed" else "Incomplete"}",
                style = MaterialTheme.typography.bodyLarge,
                color = if (todo.completed) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error
            )
        }
    }
}