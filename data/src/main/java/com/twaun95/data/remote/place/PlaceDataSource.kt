package com.twaun95.data.remote.place

import com.twaun95.data.remote.model.ReqSearchPlaceByKeyword
import com.twaun95.domain.entity.ResSearchPlaceByKeyword

interface PlaceDataSource {
    suspend fun byKeyword(req: ReqSearchPlaceByKeyword): ResSearchPlaceByKeyword
}