package com.nexus.appartmentlancorc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import com.nexus.ui.theme.LancorCourtyardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Enable edge-to-edge (optional)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            // Wrap your app with your custom theme
            LancorCourtyardTheme {
                App()
            }
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    // Also wrap preview with theme
    LancorCourtyardTheme {
        App()
    }
}
