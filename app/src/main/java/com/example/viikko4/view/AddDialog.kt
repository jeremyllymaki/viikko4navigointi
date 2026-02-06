package com.example.viikko4.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.text.input.KeyboardType
import com.example.viikko4.domain.Priority
import com.example.viikko4.domain.Task
import java.time.LocalDate

@Composable
fun AddDialog(
    nextId: Int,
    onAdd: (Task) -> Unit,
    onDismiss: () -> Unit
) {
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var dueDateText by remember { mutableStateOf(LocalDate.now().plusDays(7).toString()) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Lisää tehtävä") },
        text = {
            Column {
                OutlinedTextField(
                    value = title,
                    onValueChange = { title = it },
                    label = { Text("Title") },
                    singleLine = true
                )

                OutlinedTextField(
                    value = description,
                    onValueChange = { description = it },
                    label = { Text("Description (optional)") }
                )

                OutlinedTextField(
                    value = dueDateText,
                    onValueChange = { dueDateText = it },
                    label = { Text("Due date (YYYY-MM-DD)") },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
            }
        },
        confirmButton = {
            TextButton(
                onClick = {
                    val cleanTitle = title.trim()
                    if (cleanTitle.isEmpty()) return@TextButton

                    val due = runCatching { LocalDate.parse(dueDateText.trim()) }
                        .getOrElse { LocalDate.now().plusDays(7) }

                    onAdd(
                        Task(
                            id = nextId,
                            title = cleanTitle,
                            description = description.trim(),
                            priority = Priority.MEDIUM,
                            dueDate = due,
                            done = false
                        )
                    )
                }
            ) { Text("Tallenna") }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) { Text("Peruuta") }
        }
    )
}