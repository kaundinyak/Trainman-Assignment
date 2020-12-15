package com.kaundinyakasibhatla.trainman_assignment.data.api

import com.kaundinyakasibhatla.trainman_assignment.BuildConfig
import com.kaundinyakasibhatla.trainman_assignment.data.model.ResultModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("trending")
    suspend fun getTrendingGifs(
        @Query("api_key") apiKey: String,
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): Response<ResultModel>

}