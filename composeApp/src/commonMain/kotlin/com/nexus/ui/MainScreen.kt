package com.nexus.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.*
import com.nexus.apartment.ui.TopSection
import com.nexus.appartmentlancorc.navigation.Screen

@Composable
fun MainScreen() {
    var isLoggedIn by remember { mutableStateOf(false) }
    var currentScreen by remember { mutableStateOf(Screen.HOME) }
    // New state to track if we should show the email/otp fields
    var isAuthMode by remember { mutableStateOf(false) }

    Column {
        TopSection(
            isLoggedIn = isLoggedIn,
            onLoginClick = {
                isAuthMode = true // Show login fields when clicked
                currentScreen = Screen.HOME
            },
            onLogoutClick = {
                isLoggedIn = false
                isAuthMode = false
                currentScreen = Screen.HOME
            }
        )

        MenuBar(
            enabled = isLoggedIn,
            onMenuSelected = {
                currentScreen = it
                isAuthMode = false // Hide login fields if a menu is selected
            }
        )

        BodySection(
            isLoggedIn = isLoggedIn,
            isAuthMode = isAuthMode, // Pass the new state
            currentScreen = currentScreen,
            onLoginSuccess = {
                isLoggedIn = true
                isAuthMode = false // Hide fields after success
            }
        )
    }
}