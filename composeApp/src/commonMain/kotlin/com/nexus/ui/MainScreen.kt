package com.nexus.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.*
import com.nexus.apartment.ui.TopSection
import com.nexus.appartmentlancorc.navigation.Screen
import com.nexus.appartmentlancorc.SessionManager

@Composable
fun MainScreen() {
    val sessionManager = remember { SessionManager() }

    // Check session on start
    var isLoggedIn by remember { mutableStateOf(sessionManager.isUserAuthenticated()) }
    var currentScreen by remember { mutableStateOf(Screen.HOME) }
    var isAuthMode by remember { mutableStateOf(false) }

    Column {
        TopSection(
            isLoggedIn = isLoggedIn,
            onLoginClick = { isAuthMode = true },
            onLogoutClick = {
                sessionManager.logout() // Clear 10-day session
                isLoggedIn = false
            }
        )

        BodySection(
            isLoggedIn = isLoggedIn,
            isAuthMode = isAuthMode,
            currentScreen = currentScreen,
            onLoginSuccess = {
                sessionManager.login() // Save the 10-day session
                isLoggedIn = true
                isAuthMode = false
            }
        )
    }
}