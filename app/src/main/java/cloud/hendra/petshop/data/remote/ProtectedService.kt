package cloud.hendra.petshop.data.remote

import cloud.hendra.petshop.data.remote.dto.ProtectedDto
import retrofit2.Response
import retrofit2.http.GET

interface ProtectedService {
    @GET("api/v1/protected/")
    suspend fun getProtectedData(): Response<ProtectedDto>
}