package cloud.hendra.petshop.data.repository

import cloud.hendra.petshop.data.remote.ProtectedService
import cloud.hendra.petshop.data.remote.dto.mapper.toDomainModel
import cloud.hendra.petshop.domain.model.Protected
import retrofit2.HttpException

class ProtectedRepositoryImpl(private val service: ProtectedService) : ProtectedRepository {
    override suspend fun getProtectedData(): Protected {
        val response = service.getProtectedData()
        if (!response.isSuccessful) {
            throw HttpException(response)
        }
        return response.body()!!.toDomainModel()
    }
}