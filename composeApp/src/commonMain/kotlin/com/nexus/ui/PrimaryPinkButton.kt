package com.nexus.ui

// Compose runtime
import androidx.compose.runtime.Composable

// Compose layout
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height

// Material 3
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text

// UI basics
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

// Shapes
import androidx.compose.foundation.shape.RoundedCornerShape

@Composable
fun PrimaryPinkButton(
    text: String,
    onClick: () -> Unit,
    enabled: Boolean = true
) {
    Button(
        onClick = onClick,
        enabled = enabled,
        shape = RoundedCornerShape(24.dp),
        modifier = Modifier
            .fillMaxWidth(0.7f)
            .height(48.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFFFF3F6C),
            disabledContainerColor = Color(0xFFFF3F6C).copy(alpha = 0.5f)
        )
    ) {
        Text(text, color = Color.White)
    }
}
