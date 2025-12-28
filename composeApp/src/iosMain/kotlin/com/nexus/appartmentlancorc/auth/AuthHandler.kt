package com.nexus.appartmentlancorc.auth

import platform.Foundation.NSURL
import platform.UIKit.UIApplication

actual class AuthHandler {

    actual fun login(onResult: (AuthResult) -> Unit) {

        val url = NSURL(string =
            "https://YOUR_DOMAIN/authorize" +
                    "?client_id=YOUR_CLIENT_ID" +
                    "&response_type=code" +
                    "&redirect_uri=yourapp://callback"
        )

        UIApplication.sharedApplication.openURL(url!!)
        onResult(AuthResult.Success)
    }
}