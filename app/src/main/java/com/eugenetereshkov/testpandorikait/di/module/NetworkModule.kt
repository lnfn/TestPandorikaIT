package com.eugenetereshkov.testpandorikait.di.module

import com.eugenetereshkov.testpandorikait.di.provider.GsonProvider
import com.eugenetereshkov.testpandorikait.di.provider.OkHttpClientProvider
import com.eugenetereshkov.testpandorikait.di.provider.ServerApiProvider
import com.eugenetereshkov.testpandorikait.model.data.ServerApi
import com.eugenetereshkov.testpandorikait.model.repository.ISearchRepository
import com.eugenetereshkov.testpandorikait.model.repository.SearchRepository
import com.google.gson.Gson
import okhttp3.OkHttpClient
import toothpick.config.Module


class NetworkModule : Module() {

    init {
        bind(OkHttpClient::class.java).toProvider(OkHttpClientProvider::class.java).providesSingletonInScope()
        bind(Gson::class.java).toProvider(GsonProvider::class.java).providesSingletonInScope()
        bind(ServerApi::class.java).toProvider(ServerApiProvider::class.java).providesSingletonInScope()

        bind(ISearchRepository::class.java).to(SearchRepository::class.java)
    }
}
