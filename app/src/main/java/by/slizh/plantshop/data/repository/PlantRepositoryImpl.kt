package by.slizh.plantshop.data.repository

import by.slizh.plantshop.domain.model.Plant
import by.slizh.plantshop.domain.repository.PlantRepository
import by.slizh.plantshop.util.Resource
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await

class PlantRepositoryImpl(private val firestore: FirebaseFirestore) : PlantRepository {

    private val plantsCollection = firestore.collection("plants")

    override suspend fun getPlants(): Flow<Resource<List<Plant>>> =
        flow {
            emit(Resource.Loading())
            try {
                val snapshot = plantsCollection.get().await()
                val plants = snapshot.toObjects(Plant::class.java)
                emit(Resource.Success(plants))
            } catch (e: Exception) {
                emit(Resource.Error(e.message ?: "Unknown error"))
            }
        }

    override suspend fun getPlantById(id: Int): Flow<Resource<Plant>> = flow {
        emit(Resource.Loading())
        try {
            val document = plantsCollection.document(id.toString()).get().await()
            val plant = document.toObject(Plant::class.java)
            if (plant != null) {
                emit(Resource.Success(plant))
            } else {
                emit(Resource.Error("Plant not found"))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Unknown error"))
        }
    }
}
