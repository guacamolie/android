package com.guacamolie.auth.data.repository

import com.guacamolie.auth.data.api.AuthApiService
import com.guacamolie.auth.data.model.AuthResponse
import com.guacamolie.auth.data.model.LoginRequest
import com.guacamolie.auth.data.model.RegisterRequest
import com.guacamolie.auth.utils.PasswordUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AuthRepository(private val apiService: AuthApiService) {
    
    suspend fun login(email: String, password: String): Result<AuthResponse> {
        return withContext(Dispatchers.IO) {
            try {
                // Hash password with bcrypt before sending to API
                // This ensures consistency with Next.js frontend
                val hashedPassword = PasswordUtils.hashPassword(password)
                val response = apiService.login(LoginRequest(email, hashedPassword))
                if (response.isSuccessful) {
                    response.body()?.let {
                        Result.success(it)
                    } ?: Result.failure(Exception("Empty response body"))
                } else {
                    Result.failure(Exception("Login failed: ${response.message()}"))
                }
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }
    
    suspend fun register(email: String, password: String, name: String? = null): Result<AuthResponse> {
        return withContext(Dispatchers.IO) {
            try {
                // Hash password with bcrypt before sending to API
                // This ensures consistency with Next.js frontend
                val hashedPassword = PasswordUtils.hashPassword(password)
                val response = apiService.register(RegisterRequest(email, hashedPassword, name))
                if (response.isSuccessful) {
                    response.body()?.let {
                        Result.success(it)
                    } ?: Result.failure(Exception("Empty response body"))
                } else {
                    Result.failure(Exception("Registration failed: ${response.message()}"))
                }
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }
}