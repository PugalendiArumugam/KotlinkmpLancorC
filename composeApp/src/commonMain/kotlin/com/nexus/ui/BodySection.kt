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
    onOtpFailed: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
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
                    Screen.HOME -> WelcomeScreen()
                    Screen.USERS -> ComingSoonScreen("Users Management")
                    Screen.UNITS -> ComingSoonScreen("Units Management")
                    Screen.OWNERS -> ComingSoonScreen("Owners Management")
                }
            }
        }
    }
}

@Composable
private fun WelcomeScreen() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        shape = MaterialTheme.shapes.large
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Welcome to Lancor Courtyard",
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(Modifier.height(8.dp))
            Text(
                text = "Your premium apartment management system",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun ComingSoonScreen(featureName: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        shape = MaterialTheme.shapes.large
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = featureName,
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(Modifier.height(8.dp))
            Text(
                text = "Coming Soon",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(Modifier.height(4.dp))
            Text(
                text = "This feature is under development and will be available soon.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}
