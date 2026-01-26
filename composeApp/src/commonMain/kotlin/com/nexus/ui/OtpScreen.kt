package com.nexus.ui

// Compose runtime
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

// Compose layout
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.fillMaxWidth

// Material 3
import androidx.compose.material3.Text
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextButton

// Compose UI
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

// Coroutines
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.launch

// App-specific
import com.nexus.appartmentlancorc.service.AuthService
import com.nexus.ui.PrimaryPinkButton

private const val MAX_OTP_ATTEMPTS = 3
@Composable
fun OtpScreen(
    email: String,
    onLoginSuccess: () -> Unit,
    onLogout: () -> Unit
) {
    var otp by remember { mutableStateOf("") }
    var error by remember { mutableStateOf("") }
    var attempts by remember { mutableStateOf(0) }

    val scope = rememberCoroutineScope()
    val authService = remember { AuthService() }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {

        Text("Login", style = MaterialTheme.typography.headlineMedium)

        Spacer(Modifier.height(16.dp))

        OutlinedTextField(
            value = otp,
            onValueChange = { otp = it },
            placeholder = { Text("Enter OTP") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(0.8f),
            enabled = attempts < MAX_OTP_ATTEMPTS   // 🔒 lock input
        )

        Spacer(Modifier.height(20.dp))

        PrimaryPinkButton(
            text = "Continue",
            enabled = attempts < MAX_OTP_ATTEMPTS,
            onClick = {
                scope.launch {
                    try {
                        val res = authService.verifyOtp(email, otp)
                        if (res.success) {
                            onLoginSuccess()
                        } else {
                            attempts++
                            error = res.message
                        }
                    } catch (e: Exception) {
                        attempts++
                        error = "OTP verification failed"
                    }
                }
            }
        )

        Spacer(Modifier.height(12.dp))
        Text("OTP successfully sent to your email")

        if (error.isNotEmpty()) {
            Spacer(Modifier.height(8.dp))
            Text(error, color = MaterialTheme.colorScheme.error)
        }

        if (attempts >= MAX_OTP_ATTEMPTS) {
            Spacer(Modifier.height(16.dp))
            TextButton(onClick = onLogout) {
                Text("Go back to Login")
            }
        }
    }
}