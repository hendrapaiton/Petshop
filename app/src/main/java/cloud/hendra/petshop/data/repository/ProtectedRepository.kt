package cloud.hendra.petshop.data.repository

import cloud.hendra.petshop.domain.model.Protected

interface ProtectedRepository {
    suspend fun getProtectedData(): Protected
}