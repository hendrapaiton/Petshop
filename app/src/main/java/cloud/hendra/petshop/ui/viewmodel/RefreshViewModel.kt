package cloud.hendra.petshop.ui.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cloud.hendra.petshop.data.remote.dto.AccessToken
import cloud.hendra.petshop.domain.usecase.RefreshUseCase
import cloud.hendra.petshop.utils.state.Result
import kotlinx.coroutines.launch

class RefreshViewModel(
    private val refreshUseCase: RefreshUseCase
) : ViewModel() {
    private val _uiState = mutableStateOf<Result<AccessToken>>(Result.Loading)
    val uiState: State<Result<AccessToken>> = _uiState

    fun getAccessToken() {
        _uiState.value = Result.Loading
        viewModelScope.launch {
            when (val result = refreshUseCase()) {
                is Result.Success -> {
                    _uiState.value = Result.Success(result.data)
                }

                is Result.Error -> {
                    _uiState.value = Result.Error(result.message as String)
                }

                is Result.Loading -> Unit
            }
        }
    }
}