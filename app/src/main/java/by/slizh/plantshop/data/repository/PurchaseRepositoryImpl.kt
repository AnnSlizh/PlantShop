package by.slizh.plantshop.data.repository

import by.slizh.plantshop.domain.model.Purchase
import by.slizh.plantshop.domain.repository.PurchaseRepository
import by.slizh.plantshop.util.Resource
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class PurchaseRepositoryImpl @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val firebaseAuth: FirebaseAuth
) : PurchaseRepository {
    private val purchasesCollection = firestore.collection("purchases")

    override suspend fun addPurchase(purchase: Purchase): Flow<Resource<Void>> =
        flow {
            emit(Resource.Loading())
            try {
                val purchaseData = hashMapOf(
                    "id" to purchase.id,
                    "totalPrice" to purchase.totalPrice,
                    "date" to purchase.date,
                    "userId" to purchase.userId,
                    "plantNames" to purchase.plantNames
                )
                purchasesCollection.document(purchase.id).set(purchaseData).await()
                Resource.Success(null)
            } catch (e: Exception) {
                Resource.Error(e.localizedMessage ?: "Error adding purchase")
            }
        }

    override suspend fun getUserPurchases(userId: String): Flow<Resource<List<Purchase>>> =
        flow {
            try {
                val querySnapshot = purchasesCollection.whereEqualTo("userId", userId).get().await()
                val purchases = querySnapshot.documents.mapNotNull { document ->
                    document.toObject(Purchase::class.java)
                }
                emit(Resource.Success(purchases))
            } catch (e: Exception) {
                emit(Resource.Error(e.localizedMessage ?: "Error fetching purchases"))
            }
        }
}





