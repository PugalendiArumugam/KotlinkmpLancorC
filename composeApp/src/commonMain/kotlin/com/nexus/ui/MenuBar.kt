package com.nexus.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.nexus.appartmentlancorc.navigation.Screen

@Composable
fun MenuBar(
    enabled: Boolean,
    onMenuSelected: (Screen) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {

        MenuItem("Home", Icons.Default.Home, enabled) {
            onMenuSelected(Screen.HOME)
        }

        MenuItem("Users", Icons.Default.Person, enabled) {
            onMenuSelected(Screen.USERS)
        }

        MenuItem("Units", Icons.Default.Apartment, enabled) {
            onMenuSelected(Screen.UNITS)
        }

        MenuItem("Owners", Icons.Default.AccountCircle, enabled) {
            onMenuSelected(Screen.OWNERS)
        }
    }
}

@Composable
fun MenuItem(
    title: String,
    icon: ImageVector,
    enabled: Boolean,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .alpha(if (enabled) 1f else 0.4f)
            .clickable(enabled = enabled, onClick = onClick)
    ) {
        Icon(icon, contentDescription = title)
        Text(title)
    }
}