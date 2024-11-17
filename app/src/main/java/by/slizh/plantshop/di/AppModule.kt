package by.slizh.plantshop.di

import by.slizh.plantshop.data.repository.CartRepositoryImpl
import by.slizh.plantshop.data.repository.PlantRepositoryImpl
import by.slizh.plantshop.data.repository.PurchaseRepositoryImpl
import by.slizh.plantshop.domain.repository.CartRepository
import by.slizh.plantshop.domain.repository.PlantRepository
import by.slizh.plantshop.domain.repository.PurchaseRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthModule {
    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    @Singleton
    fun provideFirestore(): FirebaseFirestore = FirebaseFirestore.getInstance()

    @Provides
    @Singleton
    fun providePlantRepository(firestore: FirebaseFirestore): PlantRepository =
        PlantRepositoryImpl(firestore)

    @Provides
    @Singleton
    fun provideCartRepository(
        firestore: FirebaseFirestore,
        firebaseAuth: FirebaseAuth
    ): CartRepository =
        CartRepositoryImpl(firestore, firebaseAuth)

    @Provides
    @Singleton
    fun providePurchaseRepository(
        firestore: FirebaseFirestore,
        firebaseAuth: FirebaseAuth
    ): PurchaseRepository =
        PurchaseRepositoryImpl(firestore, firebaseAuth)
}