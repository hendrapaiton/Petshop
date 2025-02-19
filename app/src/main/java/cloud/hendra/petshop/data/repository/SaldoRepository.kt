package cloud.hendra.petshop.data.repository

import cloud.hendra.petshop.data.remote.dto.SaldoResponse
import cloud.hendra.petshop.utils.state.Result

interface SaldoRepository {
    suspend fun openStore(shift: String, awal: Int): Result<SaldoResponse>
}