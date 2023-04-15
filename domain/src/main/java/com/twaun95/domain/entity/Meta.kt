package com.twaun95.domain.entity

data class Meta(
    val same_name: RegionInfo,
    val pageable_count: Int,
    val total_count: Int,
    val is_end: Boolean
) {
    companion object {
        fun empty() = Meta(
            RegionInfo.empty(),
            0,
            0,
            true
        )
    }
}
