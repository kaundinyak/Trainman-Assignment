package com.kaundinyakasibhatla.trainman_assignment.data.repository


import com.kaundinyakasibhatla.trainman_assignment.BuildConfig
import com.kaundinyakasibhatla.trainman_assignment.data.api.ApiHelper
import retrofit2.http.Query
import javax.inject.Inject

class MainRepository @Inject constructor(private val apiHelper: ApiHelper) {

    suspend fun getTrendingGifs(apiKey: String, limit: Int, offset:Int) =  apiHelper.getTrendingGifs(apiKey, limit, offset)


}