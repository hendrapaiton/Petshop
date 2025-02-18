package cloud.hendra.petshop.data.repository

import cloud.hendra.petshop.utils.state.GuardState

interface GuardRepository {
    suspend fun checkAuthState(): GuardState
    suspend fun refreshToken(): GuardState
}