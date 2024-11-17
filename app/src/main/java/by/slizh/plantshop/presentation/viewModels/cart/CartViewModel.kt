package by.slizh.plantshop.presentation.viewModels.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import by.slizh.plantshop.domain.model.Cart
import by.slizh.plantshop.domain.model.Plant
import by.slizh.plantshop.domain.model.Purchase
import by.slizh.plantshop.domain.repository.CartRepository
import by.slizh.plantshop.domain.repository.PurchaseRepository
import by.slizh.plantshop.util.Resource
import com.google.firebase.Timestamp
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
    private val purchaseRepository: PurchaseRepository,
    private val firebaseAuth: FirebaseAuth
) : ViewModel() {

    private val _cartState = MutableStateFlow(CartState())
    val cartState: StateFlow<CartState> = _cartState.asStateFlow()

    fun onCartEvent(event: CartEvent) {
        when (event) {
            is CartEvent.LoadCart -> loadCart()
            is CartEvent.AddToCart -> addToCart(event.plant)
            is CartEvent.RemoveFromCart -> removeFromCart(event.cartId)
            is CartEvent.ClearCart -> clearCart()
            is CartEvent.PlaceOrder -> placeOrder()
        }
    }

    private fun loadCart() {
        viewModelScope.launch(Dispatchers.IO) {
            firebaseAuth.currentUser?.let { user ->
                cartRepository.getCartPlants(user.uid).collect { result ->
                    handleCartResult(result)
                }
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

    private fun clearCart() {
        val userId = firebaseAuth.currentUser?.uid ?: return
        viewModelScope.launch(Dispatchers.IO) {
            cartRepository.clearCart(userId).collect { result ->
                handleVoidResult(result)
                if (result is Resource.Success) {
                    _cartState.value =
                        _cartState.value.copy(cartPlants = emptyList(), isLoading = false)
                }
            }
        }
    }

    private fun placeOrder() {
        val userId = firebaseAuth.currentUser?.uid ?: return
        viewModelScope.launch(Dispatchers.IO) {
            val cartItems = cartState.value.cartPlants
            if (cartItems.isEmpty()) return@launch

            val plantNames = cartItems.map { it.plantName }
            val totalPrice = cartItems.sumOf { it.plantPrice }

            val purchase = Purchase(
                id = UUID.randomUUID().toString(),
                totalPrice = totalPrice,
                date = Timestamp.now(),
                userId = userId,
                plantNames = plantNames
            )

            purchaseRepository.addPurchase(purchase).collect { result ->
                if (result is Resource.Success) {
                    loadCart()
                }
                handleVoidResult(result)
            }
        }
    }

    private fun handleCartResult(result: Resource<List<Cart>>) {
        when (result) {
            is Resource.Loading -> {
                _cartState.value = _cartState.value.copy(isLoading = result.isLoading)
            }

            is Resource.Success -> {
                _cartState.value = _cartState.value.copy(
                    cartPlants = result.data ?: emptyList(),
                    isLoading = false
                )
            }

            is Resource.Error -> {
                _cartState.value = _cartState.value.copy(error = result.message, isLoading = false)
            }
        }
    }

    private fun handleVoidResult(result: Resource<Void>) {
        when (result) {
            is Resource.Loading -> {
                _cartState.value = _cartState.value.copy(isLoading = result.isLoading)
            }

            is Resource.Success -> {
                _cartState.value = _cartState.value.copy(isLoading = false)
            }

            is Resource.Error -> {
                _cartState.value = _cartState.value.copy(error = result.message, isLoading = false)
            }
        }
    }
}

