package by.slizh.plantshop.presentation.viewModels.cart

import by.slizh.plantshop.domain.model.Plant

sealed class CartEvent {
    object LoadCart : CartEvent()
    object ClearCart : CartEvent()
    object PlaceOrder : CartEvent()
    data class AddToCart(val plant: Plant) : CartEvent()
    data class RemoveFromCart(val cartId: String) : CartEvent()
}