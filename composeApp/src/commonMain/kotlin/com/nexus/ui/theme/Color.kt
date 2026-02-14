package com.nexus.ui.theme

import androidx.compose.ui.graphics.Color

// Your brand color
val LancorBlue = Color(0xFF1591EA)

// Light theme color scheme
val LightColorScheme = androidx.compose.material3.lightColorScheme(
    primary = LancorBlue,
    onPrimary = Color.White,
    primaryContainer = Color(0xFFD1E4FF),
    onPrimaryContainer = Color(0xFF001D36),
    secondary = Color(0xFF535F70),
    onSecondary = Color.White,
    secondaryContainer = Color(0xFFD7E3F8),
    onSecondaryContainer = Color(0xFF101C2B),
    tertiary = Color(0xFF6B5778),
    onTertiary = Color.White,
    tertiaryContainer = Color(0xFFF2DAFF),
    onTertiaryContainer = Color(0xFF251431),
    background = Color(0xFFF8F9FC),
    onBackground = Color(0xFF1A1C1E),
    surface = Color.White,
    onSurface = Color(0xFF1A1C1E),
    surfaceVariant = Color(0xFFDFE2EB),
    onSurfaceVariant = Color(0xFF43474E),
    outline = Color(0xFF73777F),
    outlineVariant = Color(0xFFC3C6CF),
    scrim = Color.Black,
    inverseSurface = Color(0xFF2F3033),
    inverseOnSurface = Color(0xFFF1F0F4),
    inversePrimary = Color(0xFF9ECBFF),
    surfaceTint = LancorBlue,
)

// Dark theme color scheme
val DarkColorScheme = androidx.compose.material3.darkColorScheme(
    primary = Color(0xFF9ECBFF),
    onPrimary = Color(0xFF003258),
    primaryContainer = Color(0xFF00497D),
    onPrimaryContainer = Color(0xFFD1E4FF),
    secondary = Color(0xFFBBC7DB),
    onSecondary = Color(0xFF253140),
    secondaryContainer = Color(0xFF3B4858),
    onSecondaryContainer = Color(0xFFD7E3F8),
    tertiary = Color(0xFFD5BEE5),
    onTertiary = Color(0xFF3A2948),
    tertiaryContainer = Color(0xFF514060),
    onTertiaryContainer = Color(0xFFF2DAFF),
    background = Color(0xFF1A1C1E),
    onBackground = Color(0xFFE2E2E6),
    surface = Color(0xFF1A1C1E),
    onSurface = Color(0xFFE2E2E6),
    surfaceVariant = Color(0xFF43474E),
    onSurfaceVariant = Color(0xFFC3C6CF),
    outline = Color(0xFF8D9199),
    outlineVariant = Color(0xFF43474E),
    scrim = Color.Black,
    inverseSurface = Color(0xFFE2E2E6),
    inverseOnSurface = Color(0xFF1A1C1E),
    inversePrimary = LancorBlue,
    surfaceTint = Color(0xFF9ECBFF),
)

// Optional: Helper colors for direct access
object LancorColors {
    val Primary = LancorBlue
    val Light = Color(0xFF4DA8F5)
    val Dark = Color(0xFF0069B7)
    val Background = Color(0xFFF8F9FC)
    val Surface = Color.White
}