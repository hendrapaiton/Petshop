package cloud.hendra.petshop.data.remote.dto

import com.google.gson.annotations.SerializedName

data class AccessToken(
    @SerializedName("access") val token: String
)
