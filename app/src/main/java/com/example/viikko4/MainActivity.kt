package com.example.viikko4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.viikko4.navigation.ROUTE_CALENDAR
import com.example.viikko4.navigation.ROUTE_HOME
import com.example.viikko4.navigation.ROUTE_SETTINGS
import com.example.viikko4.view.CalendarScreen
import com.example.viikko4.view.HomeScreen
import com.example.viikko4.view.SettingsScreen
import com.example.viikko4.viewmodel.TaskViewModel
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import com.example.viikko4.ui.theme.Viikko4Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Tämän avulla saadaan dark/light teeman togglaus
            var isDarkTheme by remember { mutableStateOf(false) }

            Viikko4Theme(darkTheme = isDarkTheme) {
                MainApp(isDarkTheme, toggleTheme = { isDarkTheme = !isDarkTheme })
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainApp(
    isDarkTheme: Boolean,
    toggleTheme: () -> Unit,
    taskViewModel: TaskViewModel = viewModel()
) {
    val navController = rememberNavController()

    Scaffold(
        topBar = { TopAppBar(title = { Text("ToDo App") }) }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = ROUTE_HOME,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(ROUTE_HOME) {
                HomeScreen(
                    taskViewModel = taskViewModel,
                    onGoToCalendar = { navController.navigate(ROUTE_CALENDAR) },
                    onGoToSettings = { navController.navigate(ROUTE_SETTINGS) }
                )
            }
            composable(ROUTE_CALENDAR) {
                CalendarScreen(
                    taskViewModel = taskViewModel,
                    onBackToList = { navController.popBackStack() }
                )
            }
            composable(ROUTE_SETTINGS) {
                SettingsScreen(
                    navigateBack = { navController.popBackStack() },
                    toggleTheme = toggleTheme
                )
            }
        }
    }
}