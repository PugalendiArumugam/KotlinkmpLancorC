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
    isLoggedIn: Boolean,
    currentScreen: Screen,
    onLoginSuccess: () -> Unit
) {
    // 1. Initialize Coroutine Scope for the API calls
    val scope = rememberCoroutineScope()

    // 2. Initialize the Service (Make sure AuthService class exists in your auth package)
    val authService = remember { AuthService() }

    // 3. UI State
    var email by remember { mutableStateOf("") }
    var otp by remember { mutableStateOf("") }
    var isOtpSent by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }

    Box(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        if (!isLoggedIn) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                if (errorMessage.isNotEmpty()) {
                    Text(
                        text = errorMessage,
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodySmall
                    )
                }

                if (!isOtpSent) {
                    // EMAIL INPUT SECTION
                    TextField(
                        value = email,
                        onValueChange = { email = it },
                        label = { Text("Email") },
                        singleLine = true
                    )
                    Button(onClick = {
                        errorMessage = "" // Clear previous errors
                        scope.launch {
                            try {
                                val response = authService.requestOtp(email)
                                if (response.success) {
                                    isOtpSent = true
                                } else {
                                    errorMessage = response.message
                                }
                            } catch (e: Exception) {
                                errorMessage = "Check Backend Connection"
                            }
                        }
                    }) {
                        Text("Send OTP")
                    }
                } else {
                    // OTP INPUT SECTION
                    TextField(
                        value = otp,
                        onValueChange = { otp = it },
                        label = { Text("Enter OTP") },
                        singleLine = true
                    )
                    Button(onClick = {
                        errorMessage = ""
                        scope.launch {
                            try {
                                val response = authService.verifyOtp(email, otp)
                                if (response.success) {
                                    onLoginSuccess()
                                } else {
                                    errorMessage = response.message
                                }
                            } catch (e: Exception) {
                                errorMessage = "Verification Error"
                            }
                        }
                    }) {
                        Text("Verify & Login")
                    }
                    TextButton(onClick = { isOtpSent = false }) {
                        Text("Edit Email")
                    }
                }
            }
        } else {
            // LOGGED IN CONTENT
            when (currentScreen) {
                Screen.HOME -> Text("Welcome to Lancor Courtyard")
                Screen.USERS -> Text("Users screen (coming soon)")
                Screen.UNITS -> Text("Units screen (coming soon)")
                Screen.OWNERS -> Text("Owners screen (coming soon)")
            }
        }
    }
}