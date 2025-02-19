package cloud.hendra.petshop.data.repository

import cloud.hendra.petshop.data.remote.SaldoService
import cloud.hendra.petshop.data.remote.dto.SaldoRequest
import cloud.hendra.petshop.data.remote.dto.SaldoResponse
import cloud.hendra.petshop.utils.state.Result

class SaldoRepositoryImpl(private val service: SaldoService) : SaldoRepository {
    override suspend fun openStore(shift: String, awal: Int): Result<SaldoResponse> {
        return try {
            val response = service.openStore(SaldoRequest(shift, awal))
            if (response.isSuccessful) {
                Result.Success(response.body()!!)
            } else {
                Result.Error(response.message())
            }
        } catch (e: Exception) {
            Result.Error(e.message)
        }
    }
}