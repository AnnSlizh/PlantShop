package by.slizh.plantshop.data.repository

import by.slizh.plantshop.domain.model.Cart
import by.slizh.plantshop.domain.repository.CartRepository
import by.slizh.plantshop.util.Resource
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await

class CartRepositoryImpl(
    private val firestore: FirebaseFirestore,
    private val firebaseAuth: FirebaseAuth
) : CartRepository {
    private val cartCollection = firestore.collection("cart")

    override suspend fun addToCart(cart: Cart): Flow<Resource<Void>> =
        flow {
            emit(Resource.Loading())
            try {
                val document = cartCollection.document(cart.id)
                document.set(cart).await()
                emit(Resource.Success(null))
            } catch (e: Exception) {
                emit(Resource.Error(e.message ?: "Unknown error"))
            }
        }

    override suspend fun removeFromCart(cartId: String): Flow<Resource<Void>> =
        flow {
            emit(Resource.Loading())
            try {
                val document = cartCollection.document(cartId)
                document.delete().await()
                emit(Resource.Success(null))
            } catch (e: Exception) {
                emit(Resource.Error(e.message ?: "Unknown error"))
            }
        }

    override suspend fun getCartPlants(userId: String): Flow<Resource<List<Cart>>> =
        flow {
            emit(Resource.Loading())
            try {
                val snapshot = cartCollection.whereEqualTo("userId", userId).get().await()
                val cartItems = snapshot.toObjects(Cart::class.java)
                emit(Resource.Success(cartItems))
            } catch (e: Exception) {
                emit(Resource.Error(e.message ?: "Unknown error"))
            }
        }

    override suspend fun clearCart(userId: String): Flow<Resource<Void>> =
        flow {
            emit(Resource.Loading())
            try {
                val cartItems = cartCollection.whereEqualTo("userId", userId).get().await()
                for (document in cartItems.documents) {
                    document.reference.delete().await()
                }
                emit(Resource.Success(null))
            } catch (e: Exception) {
                emit(Resource.Error(e.localizedMessage ?: "Error clearing cart"))
            }
        }
}