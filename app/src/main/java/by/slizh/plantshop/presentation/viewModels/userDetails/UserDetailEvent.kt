package by.slizh.plantshop.presentation.viewModels.userDetails

sealed class UserDetailEvent {
    data class OnNameChange(val name: String) : UserDetailEvent()
    data class OnSurnameChange(val surname: String) : UserDetailEvent()
    object SaveChanges : UserDetailEvent()
}