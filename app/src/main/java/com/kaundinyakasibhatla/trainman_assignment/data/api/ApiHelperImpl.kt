package com.kaundinyakasibhatla.trainman_assignment.data.api

import com.kaundinyakasibhatla.trainman_assignment.data.model.ResultModel
import retrofit2.Response
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(private val apiService: ApiService) : ApiHelper {

    override suspend fun getTrendingGifs(
        apiKey: String,
        limit: Int,
        offset: Int
    ): Response<ResultModel> = apiService.getTrendingGifs(apiKey, limit, offset)

}