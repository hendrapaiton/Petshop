package cloud.hendra.petshop.data.repository

import cloud.hendra.petshop.data.remote.IndexService
import cloud.hendra.petshop.domain.model.Index
import cloud.hendra.petshop.data.remote.dto.mapper.toDomainModel

class IndexRepositoryImpl(
    private val indexService: IndexService
) : IndexRepository {
    override suspend fun getIndex(): Index {
        return indexService.getIndex().body()!!.toDomainModel()
    }
}