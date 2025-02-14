package cloud.hendra.petshop.data.remote

import cloud.hendra.petshop.data.remote.dto.IndexDto
import retrofit2.http.GET

interface IndexService {
    @GET("api/v1/")
    suspend fun getIndex(): IndexDto
}