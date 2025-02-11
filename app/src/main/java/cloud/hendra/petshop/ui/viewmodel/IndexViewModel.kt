package cloud.hendra.petshop.ui.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cloud.hendra.petshop.domain.usecase.GetIndexUseCase
import cloud.hendra.petshop.utils.Result
import kotlinx.coroutines.launch

class IndexViewModel(
    private val getIndexUseCase: GetIndexUseCase
) : ViewModel() {
    private val _uiState = mutableStateOf<Result>(Result.Loading)
    val uiState: State<Result> = _uiState

    init {
        loadIndex()
    }

    fun loadIndex() {
        viewModelScope.launch {
            _uiState.value = Result.Loading
            try {
                val result = getIndexUseCase()
                _uiState.value = when (result) {
                    is Result.Success -> Result.Success(result.index)
                    is Result.Error -> Result.Error(result.message)
                    else -> Result.Error("Unknown error")
                }
            } catch (e: Exception) {
                _uiState.value = Result.Error(e.message ?: "Unknown error")
            }
        }
    }
}
