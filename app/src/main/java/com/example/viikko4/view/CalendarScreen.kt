package com.example.viikko4.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.example.viikko4.domain.Task
import com.example.viikko4.viewmodel.TaskViewModel
import androidx.compose.material.icons.filled.List


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalendarScreen(
    taskViewModel: TaskViewModel,
    onBackToList: () -> Unit = {}
) {
    val tasks by taskViewModel.tasks.collectAsState()
    var selectedTask by remember { mutableStateOf<Task?>(null) }
    val grouped = remember(tasks) { tasks.groupBy { it.dueDate } }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Kalenteri") },
                actions = {
                    IconButton(onClick = onBackToList) {
                        Icon(Icons.Default.List, contentDescription = "Lista")
                    }
                }
            )
        }
    ) { padding ->

        if (tasks.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentAlignment = Alignment.Center
            ) {
                Text("Ei tehtäviä kalenterissa.")
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                grouped.toSortedMap().forEach { (date, dayTasks) ->
                    item {
                        Text(
                            text = date.toString(),
                            style = MaterialTheme.typography.titleMedium
                        )
                        Spacer(Modifier.height(6.dp))
                    }

                    items(dayTasks) { task ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { selectedTask = task },
                            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                        ) {
                            Row(
                                modifier = Modifier.padding(12.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Column(modifier = Modifier.weight(1f)) {
                                    Text(task.title, style = MaterialTheme.typography.titleMedium)
                                    Text(
                                        text = if (task.done) "Valmis" else "Kesken",
                                        style = MaterialTheme.typography.bodySmall
                                    )
                                }
                                IconButton(onClick = { taskViewModel.removeTask(task.id) }) {
                                    Icon(Icons.Default.Delete, contentDescription = "Poista")
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    // EDIT dialogi myös kalenterista
    selectedTask?.let { t ->
        DetailDialog(
            task = t,
            onDismiss = { selectedTask = null },
            onSave = { updated ->
                taskViewModel.updateTask(updated)
                selectedTask = null
            },
            onDelete = {
                taskViewModel.removeTask(t.id)
                selectedTask = null
            }
        )
    }
}