package cloud.hendra.petshop.data.remote

import cloud.hendra.petshop.data.remote.dto.SaldoRequest
import cloud.hendra.petshop.data.remote.dto.SaldoResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface SaldoService {
    @POST("api/v1/open/")
    suspend fun openStore(@Body saldoDto: SaldoRequest): Response<SaldoResponse>
}