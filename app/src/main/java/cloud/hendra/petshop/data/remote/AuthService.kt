package cloud.hendra.petshop.data.remote

import cloud.hendra.petshop.data.remote.dto.LoginRequest
import cloud.hendra.petshop.data.remote.dto.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("api/v1/token/")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>
}