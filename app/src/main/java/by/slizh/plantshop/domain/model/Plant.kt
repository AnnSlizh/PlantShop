package by.slizh.plantshop.domain.model

data class Plant(
    val id: Int = 0,
    val name: String = "",
    val price: Int = 0,
    val photo: String = "",
    val category: String = "",
    val rating: String = "",
    val size: String = "",
    val humidity: Int = 0,
    val description: String = ""
)
