package by.slizh.plantshop.domain.repository

import by.slizh.plantshop.domain.model.Cart
import by.slizh.plantshop.util.Resource
import kotlinx.coroutines.flow.Flow

interface CartRepository {
    suspend fun addToCart(cart: Cart): Flow<Resource<Void>>
    suspend fun removeFromCart(cartId: String): Flow<Resource<Void>>
    suspend fun getCartPlants(userId: String): Flow<Resource<List<Cart>>>
    suspend fun clearCart(userId: String): Flow<Resource<Void>>
}