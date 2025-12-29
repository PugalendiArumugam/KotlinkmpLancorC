package com.nexus.appartmentlancorc.auth

sealed class AuthResult {
    object Success : AuthResult()
    data class Error(val message: String) : AuthResult()
    object OtpSent : AuthResult() // New state
}