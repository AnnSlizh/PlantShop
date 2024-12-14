package by.slizh.plantshop.domain.repository

import by.slizh.plantshop.domain.model.User
import by.slizh.plantshop.util.Resource
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun addUser(user: User): Flow<Resource<Void>>
    suspend fun getUserById(userId: String): Flow<Resource<User>>
    suspend fun updateUser(userId: String, name: String, surname: String): Flow<Resource<Void>>
}