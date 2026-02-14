package com.nexus.appartmentlancorc

import androidx.compose.runtime.Composable
import com.nexus.ui.MainScreen
import com.nexus.ui.theme.LancorCourtyardTheme

@Composable
fun App() {
    LancorCourtyardTheme {
        // Let MainScreen handle the login state internally
        MainScreen()
    }
}