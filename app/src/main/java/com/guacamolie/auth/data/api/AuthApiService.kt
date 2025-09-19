package com.guacamolie.auth.data.api

import com.guacamolie.auth.data.model.AuthResponse
import com.guacamolie.auth.data.model.LoginRequest
import com.guacamolie.auth.data.model.RegisterRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApiService {
    
    @POST("api/auth/signin")
    suspend fun login(@Body loginRequest: LoginRequest): Response<AuthResponse>
    
    @POST("api/auth/signup")
    suspend fun register(@Body registerRequest: RegisterRequest): Response<AuthResponse>
}