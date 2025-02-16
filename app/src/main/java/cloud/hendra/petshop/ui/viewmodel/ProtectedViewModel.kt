package cloud.hendra.petshop.ui.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cloud.hendra.petshop.data.repository.ProtectedRepository
import cloud.hendra.petshop.utils.auth.TokenManager
import cloud.hendra.petshop.utils.state.ProtectedState
import coil.network.HttpException
import kotlinx.coroutines.launch

class ProtectedViewModel(
    private val protectedRepository: ProtectedRepository,
    private val tokenManager: TokenManager
) : ViewModel() {
    private val _uiState = mutableStateOf<ProtectedState>(ProtectedState.Idle)
    val uiState: State<ProtectedState> = _uiState

    init {
        checkTokenValidity()
    }

    private fun checkTokenValidity() {
        if (tokenManager.getToken().isNullOrEmpty()) {
            _uiState.value = ProtectedState.Unauthorized
        }
    }

    fun loadProtectedData() {
        viewModelScope.launch {
            _uiState.value = ProtectedState.Loading
            try {
                val data = protectedRepository.getProtectedData()
                _uiState.value = ProtectedState.Success(data)
            } catch (e: Exception) {

            }
        }
    }

    private fun handleError(e: Exception) {
        when (e) {
            is HttpException -> {
                if (e.response.code == 401) {
                    tokenManager.clearToken()
                    _uiState.value = ProtectedState.Unauthorized
                } else {
                    _uiState.value = ProtectedState.Error("Server error: ${e.response.code}")
                }
            }
        }
    }
}