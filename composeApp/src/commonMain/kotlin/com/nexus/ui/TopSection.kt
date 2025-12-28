package com.nexus.apartment.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.painterResource
import appartmentlancorc.composeapp.generated.resources.Res
import appartmentlancorc.composeapp.generated.resources.lancor_courtyard_logo


@Composable
fun TopSection(
    isLoggedIn: Boolean,
    onLoginClick: () -> Unit,
    onLogoutClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Image(
            painter = painterResource(Res.drawable.lancor_courtyard_logo),
            contentDescription = "Lancor Courtyard Logo",
            modifier = Modifier.height(44.dp),
            contentScale = ContentScale.Fit
        )

        Button(
            onClick = {
                if (isLoggedIn) onLogoutClick() else onLoginClick()
            }
        ) {
            Text(if (isLoggedIn) "Logout" else "Login")
        }
    }
}