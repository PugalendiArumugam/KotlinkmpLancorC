package com.nexus.ui.theme

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

// Material Design 8dp Grid System spacing utilities
object Spacing {
    val xs = 4.dp      // 0.5x grid
    val sm = 8.dp      // 1x grid
    val md = 16.dp     // 2x grid
    val lg = 24.dp     // 3x grid
    val xl = 32.dp     // 4x grid
    val xxl = 48.dp    // 6x grid
    val xxxl = 64.dp   // 8x grid
}

// Composable spacing utilities
@Composable
fun VerticalSpacer(height: androidx.compose.ui.unit.Dp = Spacing.md) {
    Spacer(Modifier.height(height))
}

@Composable
fun HorizontalSpacer(width: androidx.compose.ui.unit.Dp = Spacing.md) {
    Spacer(Modifier.width(width))
}