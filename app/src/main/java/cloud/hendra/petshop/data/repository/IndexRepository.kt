package cloud.hendra.petshop.data.repository

import cloud.hendra.petshop.data.remote.dto.IndexDto

interface IndexRepository {
    suspend fun getIndex(): IndexDto
}