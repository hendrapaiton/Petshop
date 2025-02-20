package cloud.hendra.petshop.data.repository

import cloud.hendra.petshop.data.remote.AuthService
import cloud.hendra.petshop.data.remote.dto.LoginRequest
import cloud.hendra.petshop.data.remote.dto.LoginResponse
import cloud.hendra.petshop.utils.state.Result

class AuthRepositoryImpl(
    private val authService: AuthService
) : AuthRepository {
    override suspend fun login(
        username: String,
        password: String
    ): Result<LoginResponse> {
        return try {
            val response = authService.login(LoginRequest(username, password))
            if (response.isSuccessful) {
                Result.Success(response.body()!!)
            } else {
                Result.Error(response.message())
            }
        } catch (e: Exception) {
            Result.Error(e.message)
        }
    }
}