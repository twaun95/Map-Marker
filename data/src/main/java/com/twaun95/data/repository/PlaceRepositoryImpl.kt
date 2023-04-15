package com.twaun95.data.repository

import com.twaun95.data.remote.model.ReqSearchPlaceByKeyword
import com.twaun95.data.remote.place.PlaceDataSource
import com.twaun95.domain.repository.PlaceRepository
import timber.log.Timber
import javax.inject.Inject

class PlaceRepositoryImpl @Inject constructor(
    private val placeDataSource: PlaceDataSource
) : PlaceRepository{
    override suspend fun byKeyword() {
        val result = placeDataSource.byKeyword(
            ReqSearchPlaceByKeyword(
                "커피"
            )
        )

        Timber.d("${result.documents.first()}")
    }
}