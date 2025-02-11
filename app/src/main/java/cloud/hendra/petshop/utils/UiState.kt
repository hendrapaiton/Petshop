package cloud.hendra.petshop.utils

import cloud.hendra.petshop.domain.model.Index

sealed interface Result {
    data object Loading : Result
    data class Success(val index: Index) : Result
    data class Error(val message: String) : Result
}