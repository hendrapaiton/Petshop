package cloud.hendra.petshop.domain.usecase

import cloud.hendra.petshop.data.repository.RefreshRepository

class RefreshUseCase(private val repository: RefreshRepository) {
    suspend operator fun invoke() = repository.getAccessToken()
}