package com.nexus.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.*
import com.nexus.apartment.ui.TopSection
import com.nexus.appartmentlancorc.navigation.Screen
import com.nexus.appartmentlancorc.SessionManager

@Composable
fun MainScreen() {
    val sessionManager = remember { SessionManager() }

    var email by remember { mutableStateOf("") }

    var authStep by remember {
        mutableStateOf(
            if (sessionManager.isUserAuthenticated())
                AuthStep.LOGGED_IN
            else
                AuthStep.EMAIL
        )
    }

    var currentScreen by remember { mutableStateOf(Screen.HOME) }

    Column {

        TopSection(
            isLoggedIn = authStep == AuthStep.LOGGED_IN,
            onLogoutClick = {
                sessionManager.logout()
                authStep = AuthStep.EMAIL
                email = ""              // ✅ clear email on logout
            }
        )

        BodySection(
            authStep = authStep,
            currentScreen = currentScreen,
            email = email,           // ✅ pass email DOWN
            onOtpSent = { enteredEmail ->
                email = enteredEmail // ✅ store email
                authStep = AuthStep.OTP
            },
            onLoginSuccess = {
                sessionManager.login()
                authStep = AuthStep.LOGGED_IN
                currentScreen = Screen.HOME
            },
            onOtpFailed = {
                authStep = AuthStep.EMAIL
                email = ""
            }
        )

        if (authStep == AuthStep.LOGGED_IN) {
            MenuBar(
                enabled = true,
                onMenuSelected = { currentScreen = it }
            )
        }
    }
}