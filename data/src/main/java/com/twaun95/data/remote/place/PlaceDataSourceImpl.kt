package com.twaun95.data.remote.place

import com.twaun95.data.remote.model.ReqSearchPlaceByKeyword
import com.twaun95.data.service.SearchService
import com.twaun95.domain.entity.ResSearchPlaceByKeyword
import timber.log.Timber
import javax.inject.Inject

class PlaceDataSourceImpl @Inject constructor(
    private val searchService: SearchService
) : PlaceDataSource {
    override suspend fun byKeyword(req: ReqSearchPlaceByKeyword): ResSearchPlaceByKeyword {
        val result = searchService.searchPlaceByKeyword(
            req.query
        )
        return result.body() ?: ResSearchPlaceByKeyword.empty()
    }
}