package com.twaun95.domain.di

import com.twaun95.domain.repository.PlaceRepository
import com.twaun95.domain.usecase.GetPlaceByKeywordUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Singleton
    @Provides
    fun provideGetPlaceByKeyword(
        repository: PlaceRepository
    ): GetPlaceByKeywordUseCase {
        return GetPlaceByKeywordUseCase(repository)
    }
}