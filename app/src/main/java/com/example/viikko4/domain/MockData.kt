package com.example.viikko4.domain

import java.time.LocalDate

val mockTasks: List<Task> = listOf(
    Task(1, "Kotlin perusteet", "Data class ja funktiot", Priority.HIGH, LocalDate.now().plusDays(1), false),
    Task(2, "Compose aloitus", "Column, Row ja Modifier", Priority.MEDIUM, LocalDate.now().plusDays(2), false),
    Task(3, "Listat", "List ja filter, map", Priority.MEDIUM, LocalDate.now().plusDays(3), false),
    Task(4, "Github", "Push ja week1 tag", Priority.HIGH, LocalDate.now().plusDays(4), false),
    Task(5, "YouTube demo", "1 minuutin video", Priority.MEDIUM, LocalDate.now().plusDays(5), false),
    Task(6, "APK build", "Debug APK", Priority.LOW, LocalDate.now().plusDays(6), false),
    Task(7, "README", "Selitä datamalli", Priority.MEDIUM, LocalDate.now().plusDays(7), false),
    Task(8, "Bugien korjaus", "Testaa kaikki napit", Priority.HIGH, LocalDate.now().plusDays(8), false),
    Task(9, "Viimeistely", "UI marginaalit ja luettavuus", Priority.LOW, LocalDate.now().plusDays(9), false),
    Task(10, "Palautus", "Lähetä linkit", Priority.HIGH, LocalDate.now().plusDays(10), true)

)