package by.slizh.plantshop.presentation.viewModels.userDetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import by.slizh.plantshop.domain.repository.UserRepository
import by.slizh.plantshop.util.Resource
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserDetailsViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val firebaseAuth: FirebaseAuth
) : ViewModel() {

    private val _userDetailState = MutableStateFlow(UserDetailState())
    val userDetailState: StateFlow<UserDetailState> = _userDetailState.asStateFlow()

    init {
        loadUserDetails()
    }

    private fun loadUserDetails() {
        viewModelScope.launch(Dispatchers.IO) {
            val userId = firebaseAuth.currentUser?.uid
            if (userId != null) {
                userRepository.getUserById(userId).collect { result ->
                    when (result) {
                        is Resource.Loading -> {
                            _userDetailState.value = _userDetailState.value.copy(isLoading = true)
                        }

                        is Resource.Success -> {
                            _userDetailState.value = _userDetailState.value.copy(
                                isLoading = false,
                                user = result.data
                            )
                        }

                        is Resource.Error -> {
                            _userDetailState.value = _userDetailState.value.copy(
                                isLoading = false,
                                error = result.message
                            )
                        }
                    }
                }
            } else {
                _userDetailState.value = _userDetailState.value.copy(
                    isLoading = false,
                    error = "User not found"
                )
            }
        }
    }

    fun onUserDetailEvent(event: UserDetailEvent) {
        when (event) {
            is UserDetailEvent.OnNameChange -> {
                _userDetailState.value = _userDetailState.value.copy(
                    user = _userDetailState.value.user?.copy(name = event.name)
                )
            }

            is UserDetailEvent.OnSurnameChange -> {
                _userDetailState.value = _userDetailState.value.copy(
                    user = _userDetailState.value.user?.copy(surname = event.surname)
                )
            }

            is UserDetailEvent.SaveChanges -> {
                saveUserDetails()
            }
        }
    }

    private fun saveUserDetails() {
        viewModelScope.launch {
            val user = _userDetailState.value.user
            if (user != null) {
                userRepository.updateUser(user.userId, user.name, user.surname)
                    .collect { result ->
                        when (result) {
                            is Resource.Loading -> {
                                _userDetailState.value =
                                    _userDetailState.value.copy(isLoading = true)
                            }

                            is Resource.Success -> {
                                _userDetailState.value = _userDetailState.value.copy(
                                    isLoading = false,
                                    successMessage = "User details updated successfully"
                                )
                            }

                            is Resource.Error -> {
                                _userDetailState.value = _userDetailState.value.copy(
                                    isLoading = false,
                                    error = result.message
                                )
                            }
                        }
                    }
            }
        }
    }
}