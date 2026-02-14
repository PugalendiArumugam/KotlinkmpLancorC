package com.nexus.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.role
import androidx.compose.ui.unit.dp
import com.nexus.appartmentlancorc.navigation.Screen

@Composable
fun MenuBar(
    enabled: Boolean,
    onMenuSelected: (Screen) -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shadowElevation = 8.dp,
        tonalElevation = 4.dp,
        color = MaterialTheme.colorScheme.surface
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            MenuItem("Home", Icons.Default.Home, Screen.HOME, enabled, onMenuSelected)
            MenuItem("Users", Icons.Default.Person, Screen.USERS, enabled, onMenuSelected)
            MenuItem("Units", Icons.Default.Apartment, Screen.UNITS, enabled, onMenuSelected)
            MenuItem("Owners", Icons.Default.AccountCircle, Screen.OWNERS, enabled, onMenuSelected)
        }
    }
}

@Composable
fun MenuItem(
    title: String,
    icon: ImageVector,
    screen: Screen,
    enabled: Boolean,
    onMenuSelected: (Screen) -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clip(MaterialTheme.shapes.medium)
            .clickable(
                enabled = enabled,
                onClick = { onMenuSelected(screen) },
                interactionSource = interactionSource,
                indication = null // ✅ Let Material3 handle ripple automatically
            )
            .semantics { role = Role.Button }
            .padding(horizontal = 12.dp, vertical = 8.dp)
            .alpha(if (enabled) 1f else 0.4f),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = title,
            modifier = Modifier.size(24.dp),
            tint = if (enabled)
                MaterialTheme.colorScheme.onSurface
            else
                MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f)
        )
        Text(
            text = title,
            style = MaterialTheme.typography.labelSmall,
            color = if (enabled)
                MaterialTheme.colorScheme.onSurface
            else
                MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f)
        )
    }
}