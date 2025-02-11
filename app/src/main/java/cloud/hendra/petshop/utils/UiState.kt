package cloud.hendra.petshop.utils

import cloud.hendra.petshop.data.remote.dto.IndexDto

sealed interface Result {
    data object Loading : Result
    data class Success(val index: IndexDto) : Result
    data class Error(val message: String) : Result
}