package com.twaun95.data.remote.place

import com.twaun95.data.remote.model.ReqSearchPlaceByKeyword
import com.twaun95.data.service.SearchService
import com.twaun95.domain.entity.ResSearchPlaceByKeyword
import javax.inject.Inject

class PlaceDataSourceImpl @Inject constructor(
    private val searchService: SearchService
) : PlaceDataSource {
    override suspend fun byKeyword(req: ReqSearchPlaceByKeyword): ResSearchPlaceByKeyword {
        return searchService.searchPlaceByKeyword(
            req.query
        ).body() ?: ResSearchPlaceByKeyword.empty()
    }
}