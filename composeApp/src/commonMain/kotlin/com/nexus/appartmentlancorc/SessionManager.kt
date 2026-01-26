package com.nexus.appartmentlancorc

import com.russhwolf.settings.Settings
import kotlinx.datetime.Clock

class SessionManager(
    private val settings: Settings = Settings()
) {

    fun isUserAuthenticated(): Boolean {
        val isLoggedIn = settings.getBoolean("is_logged_in", false)
        if (!isLoggedIn) return false

        val loginTime = settings.getLong("login_timestamp", 0L)
        val now = Clock.System.now().toEpochMilliseconds()

        val tenDaysInMillis = 10L * 24 * 60 * 60 * 1000

        return if (now - loginTime < tenDaysInMillis) {
            true
        } else {
            logout()
            false
        }
    }

    fun login() {
        settings.putBoolean("is_logged_in", true)
        settings.putLong("login_timestamp", Clock.System.now().toEpochMilliseconds())
    }

    fun logout() {
        settings.putBoolean("is_logged_in", false)
        settings.putLong("login_timestamp", 0L)
    }
}