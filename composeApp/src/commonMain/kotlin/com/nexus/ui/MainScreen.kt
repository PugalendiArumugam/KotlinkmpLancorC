package com.nexus.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.nexus.apartment.ui.TopSection
import com.nexus.appartmentlancorc.auth.AuthResult
import com.nexus.appartmentlancorc.auth.rememberAuthHandler
import com.nexus.appartmentlancorc.navigation.Screen

@Composable
fun MainScreen() {

    var isLoggedIn by remember { mutableStateOf(false) }
    var currentScreen by remember { mutableStateOf(Screen.HOME) }

    // Android context injection happens here ONLY
    val authHandler = rememberAuthHandler()

    Column {

        TopSection(
            isLoggedIn = isLoggedIn,
            onLoginClick = {
                authHandler.login { result ->
                    if (result is AuthResult.Success) {
                        isLoggedIn = true
                    }
                }
            },
            onLogoutClick = {
                isLoggedIn = false
                currentScreen = Screen.HOME
            }
        )

        MenuBar(
            enabled = isLoggedIn,
            onMenuSelected = { currentScreen = it }
        )

        BodySection(isLoggedIn, currentScreen)
    }
}