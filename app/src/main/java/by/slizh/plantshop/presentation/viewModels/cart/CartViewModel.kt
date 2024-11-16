package by.slizh.plantshop.presentation.viewModels.cart

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import by.slizh.plantshop.domain.model.Cart
import by.slizh.plantshop.domain.model.Plant
import by.slizh.plantshop.domain.repository.CartRepository
import by.slizh.plantshop.util.Resource
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val cartRepository: CartRepository,
    private val firebaseAuth: FirebaseAuth
) : ViewModel() {

    private val _cartState = MutableStateFlow(CartState())
    val cartState: StateFlow<CartState> = _cartState.asStateFlow()

    fun onEvent(event: CartEvent) {
        when (event) {
            is CartEvent.LoadCart -> loadCart()
            is CartEvent.AddToCart -> addToCart(event.plant)
            is CartEvent.RemoveFromCart -> removeFromCart(event.cartId)
        }
    }

    private fun removeFromCart(cartId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            cartRepository.removeFromCart(cartId).collect { result ->
                if (result is Resource.Success) {
                    loadCart()
                }
                handleVoidResult(result)
            }
        }
    }

    private fun addToCart(plant: Plant) {
        val userId = firebaseAuth.currentUser?.uid ?: return
        val cartId = UUID.randomUUID().toString()
        val cartItem = Cart(
            id = cartId,
            plantId = plant.id,
            plantName = plant.name,
            plantPrice = plant.price,
            plantPhoto = plant.photo,
            userId = userId
        )

        viewModelScope.launch(Dispatchers.IO) {
            cartRepository.addToCart(cartItem).collect { result ->
                if (result is Resource.Success) {
                    loadCart()
                }
                handleVoidResult(result)
            }
        }
    }

    private fun loadCart() {
        viewModelScope.launch(Dispatchers.IO) {
            firebaseAuth.currentUser?.let { user ->
                cartRepository.getCartPlants(user.uid).collect { result ->
                    handleResult(result)
                }
            }
        }
    }

    private fun handleResult(result: Resource<List<Cart>>) {
        when (result) {
            is Resource.Loading ->
                _cartState.value = _cartState.value.copy(isLoading = result.isLoading)

            is Resource.Success ->
                _cartState.value = _cartState.value.copy(
                    cartPlants = result.data ?: emptyList(),
                    isLoading = false
                )

            is Resource.Error ->
                _cartState.value = _cartState.value.copy(error = result.message, isLoading = false)

        }
    }

    private fun handleVoidResult(result: Resource<Void>) {
        when (result) {
            is Resource.Loading -> _cartState.value =
                _cartState.value.copy(isLoading = result.isLoading)

            is Resource.Success -> {
                _cartState.value = _cartState.value.copy(isLoading = false)
            }

            is Resource.Error -> _cartState.value =
                _cartState.value.copy(error = result.message, isLoading = false)
        }
    }
}