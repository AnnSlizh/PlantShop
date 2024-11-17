package by.slizh.plantshop.presentation.viewModels.purchase

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import by.slizh.plantshop.domain.model.Purchase
import by.slizh.plantshop.domain.repository.PurchaseRepository
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
class PurchaseViewModel @Inject constructor(
    private val purchaseRepository: PurchaseRepository,
    private val firebaseAuth: FirebaseAuth
) : ViewModel() {

    private val _userPurchasesState = MutableStateFlow(UserPurchasesState())
    val userPurchasesState: StateFlow<UserPurchasesState> = _userPurchasesState.asStateFlow()

    init {
        loadPurchases()
    }

    private fun loadPurchases() {
        val userId = firebaseAuth.currentUser?.uid ?: return
        viewModelScope.launch(Dispatchers.IO) {
            purchaseRepository.getUserPurchases(userId).collect { result ->
                handleResult(result)
            }
        }
    }

    private fun handleResult(result: Resource<List<Purchase>>) {
        when (result) {
            is Resource.Loading -> _userPurchasesState.value =
                _userPurchasesState.value.copy(isLoading = result.isLoading)

            is Resource.Success -> _userPurchasesState.value =
                _userPurchasesState.value.copy(
                    purchases = result.data ?: emptyList(),
                    isLoading = false
                )

            is Resource.Error -> _userPurchasesState.value =
                _userPurchasesState.value.copy(error = result.message, isLoading = false)
        }
    }
}