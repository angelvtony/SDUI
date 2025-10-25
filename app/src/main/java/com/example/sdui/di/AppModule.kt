package com.example.sdui.di

import com.example.sdui.data.repository.SDUIRepository
import com.example.sdui.domain.GenerateUIUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideRepository(): SDUIRepository = SDUIRepository()

    @Provides
    fun provideGenerateUIUseCase(repository: SDUIRepository): GenerateUIUseCase =
        GenerateUIUseCase(repository)
}
