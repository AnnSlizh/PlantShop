package by.slizh.plantshop.domain.repository

import by.slizh.plantshop.domain.model.Plant
import by.slizh.plantshop.util.Resource
import kotlinx.coroutines.flow.Flow

interface PlantRepository {
    suspend fun getPlants(): Flow<Resource<List<Plant>>>
    suspend fun getPlantById(id: Int): Flow<Resource<Plant>>
}