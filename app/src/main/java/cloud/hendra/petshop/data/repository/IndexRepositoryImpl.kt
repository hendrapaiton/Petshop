package cloud.hendra.petshop.data.repository

import cloud.hendra.petshop.data.remote.IndexService
import cloud.hendra.petshop.data.remote.dto.IndexDto

class IndexRepositoryImpl(
    private val indexService: IndexService
) : IndexRepository {
    override suspend fun getIndex(): IndexDto {
        return indexService.getIndex()
    }
}