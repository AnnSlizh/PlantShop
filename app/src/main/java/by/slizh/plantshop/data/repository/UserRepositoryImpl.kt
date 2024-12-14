package by.slizh.plantshop.data.repository

import by.slizh.plantshop.domain.model.User
import by.slizh.plantshop.domain.repository.UserRepository
import by.slizh.plantshop.util.Resource
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await

class UserRepositoryImpl(
    private val firestore: FirebaseFirestore,
    private val firebaseAuth: FirebaseAuth
) : UserRepository {
    private val usersCollection = firestore.collection("users")

    override suspend fun addUser(user: User): Flow<Resource<Void>> =
        flow {
            emit(Resource.Loading())
            try {
                val userData = hashMapOf(
                    "userId" to user.userId,
                    "email" to user.email,
                    "password" to user.password,
                    "name" to user.name,
                    "surname" to user.surname
                )
                usersCollection.document(user.userId).set(userData).await()
                emit(Resource.Success(null))
            } catch (e: Exception) {
                emit(Resource.Error(e.localizedMessage ?: "Error adding user"))
            }
        }

    override suspend fun getUserById(userId: String): Flow<Resource<User>> = flow {
        emit(Resource.Loading())
        try {
            val document = usersCollection.document(
                userId
            ).get().await()
            val user = document.toObject(User::class.java)
            if (user != null) {
                emit(Resource.Success(user))
            } else {
                emit(Resource.Error("User not found"))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Unknown error"))
        }
    }

    override suspend fun updateUser(
        userId: String,
        name: String,
        surname: String
    ): Flow<Resource<Void>> = flow {
        emit(Resource.Loading())
        try {
            usersCollection.document(userId).update(
                mapOf(
                    "name" to name,
                    "surname" to surname
                )
            ).await()
            emit(Resource.Success(null))
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "Error updating user"))
        }
    }
}