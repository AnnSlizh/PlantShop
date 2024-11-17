package by.slizh.plantshop.presentation.viewModels.purchase

import by.slizh.plantshop.domain.model.Purchase

data class UserPurchasesState(
    val isLoading: Boolean = false,
    val purchases: List<Purchase> = emptyList(),
    val error: String? = null
)