package com.twaun95.data.di

import com.twaun95.data.remote.place.PlaceDataSource
import com.twaun95.data.remote.place.PlaceDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class RemoteModule {

    @Binds
    @Singleton
    abstract fun bindPlaceDataSource(
        placeDataSourceImpl: PlaceDataSourceImpl
    ): PlaceDataSource

}