package com.twaun95.data.repository

import com.twaun95.data.remote.model.ReqSearchPlaceByKeyword
import com.twaun95.data.remote.place.PlaceDataSource
import com.twaun95.domain.entity.Place
import com.twaun95.domain.repository.PlaceRepository
import timber.log.Timber
import javax.inject.Inject

class PlaceRepositoryImpl @Inject constructor(
    private val placeDataSource: PlaceDataSource
) : PlaceRepository{
    override suspend fun byKeyword(keyword: String): List<Place> {
        val result = placeDataSource.byKeyword(
            ReqSearchPlaceByKeyword(
                query = keyword
            )
        )

        Timber.d("${result.documents.first()}")
        return result.documents.map {
            Place(
                it.id,
                it.place_name,
                it.address_name,
                it.category_name,
                it.category_group_code,
                it.phone,
                it.place_url,
                it.x,
                it.y
            )
        }
    }
}