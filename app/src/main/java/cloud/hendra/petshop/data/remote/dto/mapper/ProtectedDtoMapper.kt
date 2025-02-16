package cloud.hendra.petshop.data.remote.dto.mapper

import cloud.hendra.petshop.data.remote.dto.ProtectedDto
import cloud.hendra.petshop.domain.model.Protected

fun ProtectedDto.toDomainModel(): Protected {
    return Protected(
        detail = detail
    )
}