package com.nexus.appartmentlancorc.auth

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

@Composable
actual fun rememberAuthHandler(): AuthHandler {
    return remember { AuthHandler() }
}