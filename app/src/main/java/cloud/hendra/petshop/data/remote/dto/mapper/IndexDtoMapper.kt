package cloud.hendra.petshop.data.remote.dto.mapper

import cloud.hendra.petshop.data.remote.dto.IndexDto
import cloud.hendra.petshop.domain.model.Index

fun IndexDto.toDomainModel(): Index {
    return Index(
        name = name,
        version = version
    )
}