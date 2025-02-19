package cloud.hendra.petshop.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cloud.hendra.petshop.data.remote.dto.SaldoResponse
import cloud.hendra.petshop.domain.usecase.OpenStoreUseCase
import cloud.hendra.petshop.utils.state.Result
import cloud.hendra.petshop.utils.state.Result.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SaldoViewModel(
    private val useCase: OpenStoreUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<Result<SaldoResponse>>(Loading)
    val uiState: StateFlow<Result<SaldoResponse>> = _uiState.asStateFlow()

    fun openStore(shift: String, awal: Int) {
        viewModelScope.launch {
            _uiState.value = Loading

            when (val result = useCase(shift, awal)) {
                is Success -> {
                    _uiState.value = Success(result.data)
                }

                is Error -> {
                    _uiState.value = Error(result.message)
                }

                Loading -> Unit
            }
        }
    }
}