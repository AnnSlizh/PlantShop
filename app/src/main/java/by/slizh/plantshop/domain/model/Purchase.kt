package by.slizh.plantshop.domain.model

import com.google.firebase.Timestamp

data class Purchase(
    val id: String = "",
    val totalPrice: Int = 0,
    val date: Timestamp = Timestamp.now(),
    val userId: String = "",
    val plantNames: List<String> = emptyList()
)
