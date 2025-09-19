package com.guacamolie.auth.ui.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.guacamolie.auth.data.repository.AuthRepository
import com.guacamolie.auth.di.NetworkModule
import kotlinx.coroutines.launch

data class AuthState(
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val error: String? = null,
    val token: String? = null
)

class AuthViewModel(
    private val repository: AuthRepository = NetworkModule.authRepository
) : ViewModel() {
    
    private val _authState = mutableStateOf(AuthState())
    val authState: State<AuthState> = _authState
    
    fun login(email: String, password: String) {
        viewModelScope.launch {
            _authState.value = _authState.value.copy(isLoading = true, error = null)
            
            repository.login(email, password).fold(
                onSuccess = { response ->
                    _authState.value = _authState.value.copy(
                        isLoading = false,
                        isSuccess = true,
                        token = response.token
                    )
                },
                onFailure = { exception ->
                    _authState.value = _authState.value.copy(
                        isLoading = false,
                        error = exception.message
                    )
                }
            )
        }
    }
    
    fun register(email: String, password: String, name: String? = null) {
        viewModelScope.launch {
            _authState.value = _authState.value.copy(isLoading = true, error = null)
            
            repository.register(email, password, name).fold(
                onSuccess = { response ->
                    _authState.value = _authState.value.copy(
                        isLoading = false,
                        isSuccess = true,
                        token = response.token
                    )
                },
                onFailure = { exception ->
                    _authState.value = _authState.value.copy(
                        isLoading = false,
                        error = exception.message
                    )
                }
            )
        }
    }
    
    fun clearError() {
        _authState.value = _authState.value.copy(error = null)
    }
    
    fun resetState() {
        _authState.value = AuthState()
    }
}