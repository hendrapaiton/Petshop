package cloud.hendra.petshop.data.remote.dto

data class SaldoRequest(
    val shift: String,
    val awal: Int
)

data class SaldoResponse(
    val id: Int,
    val open: Boolean,
    val shift: String,
    val date: String,
    val awal: Int,
    val akhir: Int,
    val note: String
)