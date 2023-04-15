package com.twaun95.domain.entity

data class RegionInfo(
    val region: List<String>,
    val keyword: String,
    val selected_region: String
) {
    companion object {
        fun empty() = RegionInfo(
            listOf(),
            "",
            ""
        )
    }
}
