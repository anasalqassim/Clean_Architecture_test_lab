package com.anas.cleanarchitecturetestlab.di

import com.anas.cleanarchitecturetestlab.firebase.data.repo.FirebaseRepoImpl
import com.anas.cleanarchitecturetestlab.firebase.domain.repo.FirebaseRepo
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MainModule {


    @Provides
    @Singleton
    fun provideFirebase() = Firebase

    @Provides
    @Singleton
    fun provideRepo(firebase: Firebase) :FirebaseRepo = FirebaseRepoImpl(firebase)


}