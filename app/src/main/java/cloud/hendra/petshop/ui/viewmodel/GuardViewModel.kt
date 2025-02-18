package cloud.hendra.petshop.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cloud.hendra.petshop.data.repository.GuardRepository
import cloud.hendra.petshop.utils.state.GuardState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class GuardViewModel(
    private val guardRepository: GuardRepository
) : ViewModel() {
    private val _authState = MutableStateFlow<GuardState>(GuardState.Authenticated)
    val authState = _authState.asStateFlow()

    fun checkAuthState() = viewModelScope.launch {
        _authState.value = guardRepository.checkAuthState()
        if (_authState.value is GuardState.NeedsRefresh) {
            _authState.value = guardRepository.refreshToken()
        }
    }
}