package com.twaun95.domain.entity

data class ResSearchPlaceByKeyword(
    val meta: Meta,
    val documents: List<ResultSearchPlaceByKeyword>
) {
    companion object {
        fun empty() = ResSearchPlaceByKeyword(
            Meta.empty(),
            listOf()
        )
    }
}
