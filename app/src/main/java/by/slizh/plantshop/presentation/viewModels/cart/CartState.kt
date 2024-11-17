package by.slizh.plantshop.presentation.viewModels.cart

import by.slizh.plantshop.domain.model.Cart

data class CartState(
    val cartPlants: List<Cart> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
    val totalPrice: Int = 0,
)
