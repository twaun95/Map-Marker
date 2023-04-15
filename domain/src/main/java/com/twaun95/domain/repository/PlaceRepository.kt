package com.twaun95.domain.repository

interface PlaceRepository {
    suspend fun byKeyword()
}