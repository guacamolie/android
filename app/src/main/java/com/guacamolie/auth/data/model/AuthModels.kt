package com.guacamolie.auth.data.model

data class LoginRequest(
    val email: String,
    val password: String
)

data class RegisterRequest(
    val email: String,
    val password: String,
    val name: String? = null
)

data class AuthResponse(
    val token: String,
    val user: User? = null
)

data class User(
    val id: String,
    val email: String,
    val name: String?
)

data class ErrorResponse(
    val message: String,
    val error: String? = null
)