package by.slizh.plantshop.domain.repository

import by.slizh.plantshop.domain.model.Purchase
import by.slizh.plantshop.util.Resource
import kotlinx.coroutines.flow.Flow

interface PurchaseRepository {
    suspend fun addPurchase(purchase: Purchase): Flow<Resource<Void>>
    suspend fun getUserPurchases(userId: String): Flow<Resource<List<Purchase>>>
}