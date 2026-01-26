package com.nexus.ui

// Compose core
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

// Compose Material 3
import androidx.compose.material3.Text
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.MaterialTheme

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

@Composable
fun EmailLoginScreen(
    onOtpSent: (String) -> Unit   // ✅ return email
) {
    var email by remember { mutableStateOf("") }
    var error by remember { mutableStateOf("") }
    val scope = rememberCoroutineScope()
    val authService = remember { AuthService() }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {

        Text("Login", style = MaterialTheme.typography.headlineMedium)

        Spacer(Modifier.height(16.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            placeholder = { Text("Enter email id") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(0.8f)
        )

        Spacer(Modifier.height(20.dp))

        PrimaryPinkButton(
            text = "Continue",
            onClick = {
                scope.launch {
                    try {
                        val res = authService.requestOtp(email)
                        if (res.success) {
                            onOtpSent(email)
                        } else {
                            error = res.message
                        }
                    } catch (e: Exception) {
                        error = "Network error"
                    }
                }
            }
        )

        if (error.isNotEmpty()) {
            Spacer(Modifier.height(12.dp))
            Text(error, color = MaterialTheme.colorScheme.error)
        }
    }
}