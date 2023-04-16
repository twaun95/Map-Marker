package com.twaun95.domain.repository

import com.twaun95.domain.entity.Place

interface PlaceRepository {
    suspend fun byKeyword(keyword: String) : List<Place>
}