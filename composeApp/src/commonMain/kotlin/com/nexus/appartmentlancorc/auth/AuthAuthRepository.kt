package com.nexus.appartmentlancorc.auth

class AuthRepository {
    // Replace with your actual IP/Domain
    private val baseUrl = "http://10.0.2.2:8080/api/auth"

    suspend fun sendOtp(email: String): Boolean {
        // Use Ktor to POST to /generate-otp
        return true
    }

    suspend fun verifyOtp(email: String, otp: String): Boolean {
        // Use Ktor to POST to /verify-otp
        return true
    }
}