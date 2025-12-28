package com.nexus.appartmentlancorc.auth

import android.content.Context
import android.content.Intent
import android.net.Uri

actual class AuthHandler(
    private val context: Context
) {

    actual fun login(onResult: (AuthResult) -> Unit) {

        val authUrl =
            "https://accounts.google.com/o/oauth2/v2/auth" +
                    "?client_id=833746638979-soqldqmuidc1gnp026ter2no7q1f3cve.apps.googleusercontent.com" +
                    "&redirect_uri=https://auth.lancorcourtyard.com/oauth" +
                    "&response_type=code" +
                    "&scope=openid%20email%20profile"

        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(authUrl))
        context.startActivity(intent)
    }
}