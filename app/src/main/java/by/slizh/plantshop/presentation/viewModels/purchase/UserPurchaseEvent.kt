package by.slizh.plantshop.presentation.viewModels.purchase

import by.slizh.plantshop.presentation.viewModels.cart.CartEvent

sealed class UserPurchaseEvent {
    object LoadPurchases : UserPurchaseEvent()
}