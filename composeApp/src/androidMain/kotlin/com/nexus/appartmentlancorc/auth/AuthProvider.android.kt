package com.nexus.appartmentlancorc.auth

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext

@Composable
actual fun rememberAuthHandler(): AuthHandler {
    val context = LocalContext.current
    return remember { AuthHandler(context) }
}