package com.nexus.appartmentlancorc.auth

import kotlinx.serialization.Serializable

@Serializable
data class OtpRequest(
    val email: String
)

@Serializable
data class VerifyRequest(
    val email: String,
    val otp: String
)

@Serializable
data class AuthResponse(
    val success: Boolean,
    val message: String
)