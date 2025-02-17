package cloud.hendra.petshop.data.remote

import cloud.hendra.petshop.data.remote.dto.AccessToken
import retrofit2.Response
import retrofit2.http.POST

interface RefreshService {
    @POST
    suspend fun refresh(): Response<AccessToken>
}