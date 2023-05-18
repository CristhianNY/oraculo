package com.cristhianbonilla.oraculo.data.api

import com.cristhianbonilla.oraculo.data.GptRequestEntity
import com.cristhianbonilla.oraculo.data.response.GptResponseEntity
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface GptApi {

    @POST("v1/completions")
    suspend fun callCompletionEndpoint(@Body request: GptRequestEntity): Response<GptResponseEntity>
}
