package cloud.hendra.petshop.utils.auth

import cloud.hendra.petshop.data.remote.dto.LoginResponse

interface TokenManager {
    fun getToken(): String?
    fun saveToken(access: LoginResponse)
    fun clearToken()
}