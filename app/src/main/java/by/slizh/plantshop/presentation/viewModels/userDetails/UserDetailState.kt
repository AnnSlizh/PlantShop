package by.slizh.plantshop.presentation.viewModels.userDetails

import by.slizh.plantshop.domain.model.User

data class UserDetailState(
    val isLoading: Boolean = false,
    val user: User? = null,
    val error: String? = null,
    val successMessage: String? = null
)