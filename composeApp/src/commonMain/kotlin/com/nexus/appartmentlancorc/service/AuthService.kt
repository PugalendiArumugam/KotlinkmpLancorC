package com.nexus.appartmentlancorc.service

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

// These are your custom data models
// If they are in the 'auth' package, import them like this:
import com.nexus.appartmentlancorc.auth.OtpRequest
import com.nexus.appartmentlancorc.auth.VerifyRequest
import com.nexus.appartmentlancorc.auth.AuthResponse


class AuthService {
    private val client = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                isLenient = true // Helpful for Spring Boot JSON variations
            })
        }
    }

    private val baseUrl = "http://10.0.2.2:8080/auth"

    suspend fun requestOtp(email: String): AuthResponse {
        return client.post("$baseUrl/request-otp") {
            contentType(ContentType.Application.Json)
            setBody(OtpRequest(email))
        }.body<AuthResponse>() // Explicitly cast the response
    }

    suspend fun verifyOtp(email: String, otp: String): AuthResponse {
        return client.post("$baseUrl/verify-otp") {
            contentType(ContentType.Application.Json)
            setBody(VerifyRequest(email, otp))
        }.body<AuthResponse>() // Explicitly cast the response
    }
}