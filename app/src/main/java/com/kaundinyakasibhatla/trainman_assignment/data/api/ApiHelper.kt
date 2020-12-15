package com.kaundinyakasibhatla.trainman_assignment.data.api

import com.kaundinyakasibhatla.trainman_assignment.data.model.ResultModel
import retrofit2.Response

interface ApiHelper {
    suspend fun getTrendingGifs(apiKey: String, limit: Int, offset: Int): Response<ResultModel>

}