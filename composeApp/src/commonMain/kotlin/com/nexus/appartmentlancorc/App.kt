package com.nexus.appartmentlancorc

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.nexus.ui.MainScreen

@Composable
fun App() {
    MaterialTheme {
        // Let MainScreen handle the login state internally
        MainScreen()
    }
}