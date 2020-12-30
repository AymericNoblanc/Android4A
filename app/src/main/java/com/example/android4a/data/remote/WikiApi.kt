package com.example.android4a.data.remote

import com.example.android4a.data.remote.remote.RestApiResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface WikiApi {
    @GET
    fun getApiResponse(@Url url: String?): Call<RestApiResponse>
}