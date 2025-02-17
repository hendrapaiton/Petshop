package cloud.hendra.petshop.data.repository

import cloud.hendra.petshop.data.remote.RefreshService
import cloud.hendra.petshop.data.remote.dto.AccessToken
import cloud.hendra.petshop.utils.state.Result

class RefreshRepositoryImpl(private val service: RefreshService) : RefreshRepository {
    override suspend fun getAccessToken(): Result<AccessToken> {
        return try {
            val response = service.refresh()
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