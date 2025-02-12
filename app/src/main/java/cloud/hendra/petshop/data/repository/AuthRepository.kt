package cloud.hendra.petshop.data.repository

import cloud.hendra.petshop.data.remote.dto.LoginResponse
import cloud.hendra.petshop.utils.Result

interface AuthRepository {
    suspend fun login(username: String, password: String): Result<LoginResponse>
}