package cloud.hendra.petshop.data.remote.dto

import com.google.gson.annotations.SerializedName

data class ProtectedDto(
    @SerializedName("detail")
    val detail: String
)
