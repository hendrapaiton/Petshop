package cloud.hendra.petshop.data.repository

import cloud.hendra.petshop.data.remote.dto.AccessToken
import cloud.hendra.petshop.utils.state.Result

interface RefreshRepository {
    suspend fun getAccessToken(): Result<AccessToken>
}