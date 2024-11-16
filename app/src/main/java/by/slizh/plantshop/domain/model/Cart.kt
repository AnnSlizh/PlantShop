package by.slizh.plantshop.domain.model

data class Cart(
    val id: String = "",
    val plantId: Int = 0,
    val plantName: String = "",
    val plantPrice: Int = 0,
    val plantPhoto: String = "",
    val userId: String = ""
)
