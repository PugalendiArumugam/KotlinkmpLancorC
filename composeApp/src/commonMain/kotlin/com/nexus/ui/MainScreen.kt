package com.nexus.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.*
import com.nexus.apartment.ui.TopSection
import com.nexus.appartmentlancorc.navigation.Screen

@Composable
fun MainScreen() {
    var isLoggedIn by remember { mutableStateOf(false) }
    var currentScreen by remember { mutableStateOf(Screen.HOME) }

    Column {
        TopSection(
            isLoggedIn = isLoggedIn,
            onLoginClick = { /* Scroll to login or show dialog */ },
            onLogoutClick = {
                isLoggedIn = false
                currentScreen = Screen.HOME
            }
        )

        MenuBar(
            enabled = isLoggedIn,
            onMenuSelected = { currentScreen = it }
        )

        // Pass the success callback here
        BodySection(
            isLoggedIn = isLoggedIn,
            currentScreen = currentScreen,
            onLoginSuccess = { isLoggedIn = true }
        )
    }
}