package com.nexus.appartmentlancorc

import kotlin.time.Duration
import kotlin.time.Duration.Companion.days
import kotlin.time.TimeSource

class SessionManager {
    private var loginMark: TimeSource.Monotonic.ValueTimeMark? = null
    private val sessionDuration = 10.days

    fun login() {
        loginMark = TimeSource.Monotonic.markNow()
    }

    fun logout() {
        loginMark = null
    }

    fun isUserAuthenticated(): Boolean {
        val mark = loginMark ?: return false
        return mark.elapsedNow() < sessionDuration
    }
}