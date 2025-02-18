package cloud.hendra.petshop.ui.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cloud.hendra.petshop.domain.usecase.AuthUseCase
import cloud.hendra.petshop.utils.auth.AuthState
import cloud.hendra.petshop.utils.auth.AuthState.*
import cloud.hendra.petshop.utils.auth.TokenManager
import cloud.hendra.petshop.utils.state.Result
import kotlinx.coroutines.launch

class AuthViewModel(
    private val authUseCase: AuthUseCase,
    private val tokenManager: TokenManager
) : ViewModel() {
    private val _uiState = mutableStateOf<AuthState>(Idle)
    val uiState: State<AuthState> = _uiState

    fun login(username: String, password: String) {
        viewModelScope.launch {
            _uiState.value = Loading
            when (val result = authUseCase(username, password)) {
                is Result.Success -> {
                    _uiState.value = Success(result.data)
                    tokenManager.saveAccessToken(result.data)
                }
                is Result.Error -> {
                    _uiState.value = Error(result.message as String)
                }
                Result.Loading -> Loading
            }
        }
    }
}