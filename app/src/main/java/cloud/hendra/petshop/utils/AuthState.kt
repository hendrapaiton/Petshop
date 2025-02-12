package cloud.hendra.petshop.utils

import cloud.hendra.petshop.data.remote.dto.LoginResponse

sealed class AuthState {
    object Idle : AuthState()
    object Loading : AuthState()
    data class Success(val user: LoginResponse) : AuthState()
    data class Error(val message: String) : AuthState()
}