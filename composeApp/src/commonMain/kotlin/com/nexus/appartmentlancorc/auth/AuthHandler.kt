package com.nexus.appartmentlancorc.auth

expect class AuthHandler {
    fun login(onResult: (AuthResult) -> Unit)
}
