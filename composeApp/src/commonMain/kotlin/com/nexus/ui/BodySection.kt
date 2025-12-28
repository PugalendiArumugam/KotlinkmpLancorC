package com.nexus.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.nexus.appartmentlancorc.navigation.Screen

@Composable
fun BodySection(
    isLoggedIn: Boolean,
    currentScreen: Screen
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        if (!isLoggedIn) {
            Text("Please login to continue")
        } else {
            when (currentScreen) {
                Screen.HOME -> Text("Welcome to Lancor Courtyard")
                Screen.USERS -> Text("Users screen (coming soon)")
                Screen.UNITS -> Text("Units screen (coming soon)")
                Screen.OWNERS -> Text("Owners screen (coming soon)")
            }
        }
    }
}
