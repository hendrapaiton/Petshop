package cloud.hendra.petshop.domain.usecase

import cloud.hendra.petshop.data.repository.SaldoRepository

class OpenStoreUseCase(private val repository: SaldoRepository) {
    suspend operator fun invoke(shift: String, awal: Int) = repository.openStore(shift, awal)
}