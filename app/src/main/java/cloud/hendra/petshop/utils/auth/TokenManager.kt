package cloud.hendra.petshop.utils.auth

import cloud.hendra.petshop.data.remote.dto.LoginResponse

interface TokenManager {
    fun getAccessToken(): String?
    fun saveAccessToken(access: LoginResponse)
    fun clearAccessToken()
    fun getRefreshToken(): String?
    fun saveRefreshToken(refresh: String)
}