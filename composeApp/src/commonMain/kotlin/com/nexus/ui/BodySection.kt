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
    isAuthMode: Boolean, // Added parameter
    currentScreen: Screen,
    onLoginSuccess: () -> Unit
) {
    val scope = rememberCoroutineScope()
    val authService = remember { AuthService() }

    var email by remember { mutableStateOf("") }
    var otp by remember { mutableStateOf("") }
    var isOtpSent by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }

    Box(modifier = Modifier.fillMaxSize().padding(16.dp), contentAlignment = Alignment.Center) {
        // Condition 1: User is logged in - show app content
        if (isLoggedIn) {
            when (currentScreen) {
                Screen.HOME -> Text("Welcome to Lancor Courtyard")
                Screen.USERS -> Text("Users screen (coming soon)")
                Screen.UNITS -> Text("Units screen (coming soon)")
                Screen.OWNERS -> Text("Owners screen (coming soon)")
            }
        }
        // Condition 2: User clicked Login button - show Login Flow
        else if (isAuthMode) {
            Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(10.dp)) {
                if (errorMessage.isNotEmpty()) {
                    Text(errorMessage, color = MaterialTheme.colorScheme.error)
                }

                if (!isOtpSent) {
                    TextField(value = email, onValueChange = { email = it }, label = { Text("Email") })
                    Button(onClick = {
                        scope.launch {
                            try {
                                val response = authService.requestOtp(email)
                                if (response.success) isOtpSent = true else errorMessage = response.message
                            } catch (e: Exception) {
                                errorMessage = "Network Error"
                            }
                        }
                    }) { Text("Send OTP") }
                } else {
                    TextField(value = otp, onValueChange = { otp = it }, label = { Text("Enter OTP") })
                    Button(onClick = {
                        scope.launch {
                            try {
                                val response = authService.verifyOtp(email, otp)
                                if (response.success) onLoginSuccess() else errorMessage = response.message
                            } catch (e: Exception) {
                                errorMessage = "Verification Failed"
                            }
                        }
                    }) { Text("Verify & Login") }
                }
            }
        }
        // Condition 3: Initial Load - show blank home page
        else {
            Text("Please click Login to access Lancor Courtyard")
        }
    }
}



