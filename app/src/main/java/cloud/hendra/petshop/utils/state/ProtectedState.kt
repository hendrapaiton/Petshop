package cloud.hendra.petshop.utils.state

import cloud.hendra.petshop.domain.model.Protected

sealed class ProtectedState {
    object Idle : ProtectedState()
    object Loading : ProtectedState()
    data class Success(val data: Protected) : ProtectedState()
    data class Error(val message: String) : ProtectedState()
    object Unauthorized : ProtectedState()
}