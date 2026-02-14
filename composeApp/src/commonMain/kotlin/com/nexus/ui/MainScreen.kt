package com.nexus.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.nexus.ui.theme.LancorCourtyardTheme
import com.nexus.appartmentlancorc.navigation.Screen
import com.nexus.appartmentlancorc.SessionManager
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.background
import androidx.compose.ui.Alignment

@Composable
fun MainScreen() {
    LancorCourtyardTheme {
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

        Scaffold(
            topBar = {
                TopSection(  // Just use your TopSection composable
                    isLoggedIn = authStep == AuthStep.LOGGED_IN,
                    onLogoutClick = {
                        sessionManager.logout()
                        authStep = AuthStep.EMAIL
                        email = ""
                    }
                )
            },
            bottomBar = {
                if (authStep == AuthStep.LOGGED_IN) {
                    MenuBar(
                        enabled = true,
                        onMenuSelected = { currentScreen = it }
                    )
                }
            }
        ) { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .background(MaterialTheme.colorScheme.background),
                contentAlignment = Alignment.Center
            ) {
                BodySection(
                    authStep = authStep,
                    currentScreen = currentScreen,
                    email = email,
                    onOtpSent = { enteredEmail ->
                        email = enteredEmail
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
            }
        }
    }
}