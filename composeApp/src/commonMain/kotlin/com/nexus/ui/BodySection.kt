package com.nexus.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
// Crucial imports for Coroutines and API
import kotlinx.coroutines.launch
import com.nexus.appartmentlancorc.navigation.Screen
import com.nexus.appartmentlancorc.service.AuthService

@Composable
fun BodySection(
    authStep: AuthStep,
    currentScreen: Screen,
    email: String,
    onOtpSent: (String) -> Unit,
    onLoginSuccess: () -> Unit,
    onOtpFailed: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        when (authStep) {

            AuthStep.EMAIL -> {
                EmailLoginScreen(
                    onOtpSent = onOtpSent
                )
            }

            AuthStep.OTP -> {
                OtpScreen(
                    email = email,
                    onLoginSuccess = onLoginSuccess,
                    onLogout = onOtpFailed
                )
            }

            AuthStep.LOGGED_IN -> {
                when (currentScreen) {
                    Screen.HOME -> Text("Welcome to Lancor Courtyard")
                    Screen.USERS -> Text("Users screen (coming soon)")
                    Screen.UNITS -> Text("Units screen (coming soon)")
                    Screen.OWNERS -> Text("Owners screen (coming soon)")
                }
            }
        }
    }
}
