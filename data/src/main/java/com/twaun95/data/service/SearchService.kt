package com.twaun95.data.service

import com.twaun95.domain.entity.ResSearchPlaceByKeyword
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchService {

    // 키워드로 장소 검색하기
    @GET("v2/local/search/keyword.json")
    suspend fun searchPlaceByKeyword(
        @Query("query") query: String,
        @Query("category_group_code") category_group_code: String? = null,
        @Query("x") x: String? = null,
        @Query("y") y: String? = null,
        @Query("radius") radius: Int? = null,
        @Query("rect") rect: String? = null,
        @Query("page") page: Int? = null,
        @Query("size") size: Int? = null,
        @Query("sort") sort: String? = null
    ) : Response<ResSearchPlaceByKeyword>

}