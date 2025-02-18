package cloud.hendra.petshop.data.repository

import cloud.hendra.petshop.data.remote.ProtectedService
import cloud.hendra.petshop.data.remote.RefreshService
import cloud.hendra.petshop.data.remote.dto.LoginResponse
import cloud.hendra.petshop.utils.auth.TokenManager
import cloud.hendra.petshop.utils.state.GuardState

class GuardRepositoryImpl(
    private val tokenManager: TokenManager,
    private val accessService: ProtectedService,
    private val refreshService: RefreshService
) : GuardRepository {
    override suspend fun checkAuthState(): GuardState {
        val accessToken = tokenManager.getAccessToken()
        if (accessToken.isNullOrEmpty()) {
            return GuardState.Unauthenticated
        }
        val code = accessService.getProtectedData().code()
        return when (code) {
            200 -> GuardState.Authenticated
            401 -> GuardState.NeedsRefresh
            else -> GuardState.Unauthenticated
        }
    }

    override suspend fun refreshToken(): GuardState {
        val token = refreshService.refresh()
        return if (token.code() == 200) {
            token.body()?.let { tokenManager.saveAccessToken(LoginResponse(it.token)) }
            GuardState.Authenticated
        } else {
            GuardState.Unauthenticated
        }
    }
}