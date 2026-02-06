package com.example.viikko4.view

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SettingsScreen(
    navigateBack: () -> Unit,
    toggleTheme: () -> Unit
) {
    Column(Modifier.padding(16.dp)) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Settings", style = MaterialTheme.typography.headlineMedium)
            Button(onClick = navigateBack) { Text("Back") }
        }

        Spacer(Modifier.height(16.dp))

        Text("Theme selection", style = MaterialTheme.typography.bodyLarge)
        Spacer(Modifier.height(8.dp))
        Button(onClick = toggleTheme) {
            Text("Toggle Theme")
        }
    }
}