package cloud.hendra.petshop.domain.usecase

import cloud.hendra.petshop.data.remote.dto.IndexDto
import cloud.hendra.petshop.data.repository.IndexRepository
import cloud.hendra.petshop.utils.Result

class GetIndexUseCase(
    private val indexRepository: IndexRepository
) {
    suspend operator fun invoke(): Result<IndexDto> {
        Result.Loading
        return try {
            Result.Success(indexRepository.getIndex())
        } catch (e: Exception) {
            Result.Error(e.message ?: "Unknown error")
        }
    }
}