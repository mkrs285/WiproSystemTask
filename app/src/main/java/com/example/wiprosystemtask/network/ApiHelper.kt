package com.example.wiprosystemtask.network

import com.example.wiprosystemtask.repo.factsResponse.FactsResponse
import io.reactivex.Single
import retrofit2.http.GET

interface ApiHelper {

    @GET("s/2iodh4vg0eortkl/facts")
    fun getFacts(
    ): Single<FactsResponse>

}