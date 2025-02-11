package cloud.hendra.petshop.data.repository

import cloud.hendra.petshop.data.remote.dto.IndexDto
import cloud.hendra.petshop.domain.model.Index

interface IndexRepository {
    suspend fun getIndex(): IndexDto
}