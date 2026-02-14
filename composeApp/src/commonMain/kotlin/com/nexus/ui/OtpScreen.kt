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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll

// Material 3
import androidx.compose.material3.Text
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextButton
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LinearProgressIndicator

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
    var isLoading by remember { mutableStateOf(false) }

    val scope = rememberCoroutineScope()
    val authService = remember { AuthService() }
    val remainingAttempts = MAX_OTP_ATTEMPTS - attempts

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Spacer(Modifier.height(32.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
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
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = "Verify OTP",
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Text(
                    text = "Enter the verification code sent to",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Text(
                    text = email,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.primary
                )

                OutlinedTextField(
                    value = otp,
                    onValueChange = { 
                        otp = it
                        error = "" // Clear error when user types
                    },
                    placeholder = { Text("Enter 6-digit OTP") },
                    label = { Text("Verification Code") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    shape = MaterialTheme.shapes.medium,
                    enabled = attempts < MAX_OTP_ATTEMPTS && !isLoading,
                    isError = error.isNotEmpty()
                )

                // Progress indicator for attempts
                if (attempts > 0) {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        LinearProgressIndicator(
                            progress = { attempts.toFloat() / MAX_OTP_ATTEMPTS },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(6.dp),
                            color = MaterialTheme.colorScheme.error,
                            trackColor = MaterialTheme.colorScheme.errorContainer
                        )
                        Text(
                            text = "Attempts remaining: $remainingAttempts/$MAX_OTP_ATTEMPTS",
                            style = MaterialTheme.typography.bodySmall,
                            color = if (remainingAttempts <= 1) 
                                MaterialTheme.colorScheme.error 
                            else 
                                MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }

                if (isLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier.height(24.dp),
                        color = MaterialTheme.colorScheme.primary
                    )
                } else {
                    PrimaryPinkButton(
                        text = "Verify OTP",
                        enabled = attempts < MAX_OTP_ATTEMPTS && otp.isNotBlank(),
                        onClick = {
                            scope.launch {
                                try {
                                    isLoading = true
                                    val res = authService.verifyOtp(email, otp)
                                    if (res.success) {
                                        onLoginSuccess()
                                    } else {
                                        attempts++
                                        error = res.message
                                    }
                                } catch (e: Exception) {
                                    attempts++
                                    error = "OTP verification failed. Please try again."
                                } finally {
                                    isLoading = false
                                }
                            }
                        }
                    )
                }

                if (error.isNotEmpty()) {
                    Text(
                        text = error,
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }

                if (attempts >= MAX_OTP_ATTEMPTS) {
                    Spacer(Modifier.height(16.dp))
                    TextButton(onClick = onLogout) {
                        Text("Go back to Login")
                    }
                } else {
                    TextButton(onClick = onLogout) {
                        Text("Change email address")
                    }
                }
            }
        }
    }
}