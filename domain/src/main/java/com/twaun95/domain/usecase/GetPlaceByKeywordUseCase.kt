package com.twaun95.domain.usecase

import com.twaun95.domain.entity.Place
import com.twaun95.domain.repository.PlaceRepository
import javax.inject.Inject

class GetPlaceByKeywordUseCase @Inject constructor(
    private val placeRepository: PlaceRepository
){

    suspend operator fun invoke(
        keyword: String
    ) : List<Place>{
      return placeRepository.byKeyword(keyword)
    }
}