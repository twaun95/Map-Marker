package com.twaun95.data.remote.model

data class ReqSearchPlaceByKeyword(
    val query: String,
    val category_group_code: String? = null,
    val x: String? = null,
    val y: String? = null,
    val radius: String? = null,
    val rect: String? = null,
    val page: Int? = null,
    val size: Int? = null,
    val sort: String? = null
)
