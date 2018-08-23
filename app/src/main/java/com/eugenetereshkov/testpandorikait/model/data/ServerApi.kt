package com.eugenetereshkov.testpandorikait.model.data

import com.eugenetereshkov.testpandorikait.entity.SearchResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query


interface ServerApi {

    companion object {
        const val API_URL = "https://itunes.apple.com/"
    }

    @GET("search")
    fun search(@Query("term") term: String): Single<SearchResponse>
}
