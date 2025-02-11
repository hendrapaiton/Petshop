package cloud.hendra.petshop.data.remote

import cloud.hendra.petshop.data.remote.dto.IndexDto
import retrofit2.Response
import retrofit2.http.GET

interface IndexService {
    @GET("/")
    suspend fun getIndex(): Response<IndexDto>
}