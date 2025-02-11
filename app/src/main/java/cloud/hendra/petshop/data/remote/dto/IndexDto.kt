package cloud.hendra.petshop.data.remote.dto

import com.google.gson.annotations.SerializedName

data class IndexDto(
    @SerializedName("name")
    val name: String,

    @SerializedName("version")
    val version: String
)
