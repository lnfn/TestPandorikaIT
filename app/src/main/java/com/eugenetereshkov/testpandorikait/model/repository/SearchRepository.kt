package com.eugenetereshkov.testpandorikait.model.repository

import com.eugenetereshkov.testpandorikait.entity.Result
import com.eugenetereshkov.testpandorikait.model.data.ServerApi
import com.eugenetereshkov.testpandorikait.model.system.SchedulersProvider
import io.reactivex.Single
import javax.inject.Inject


interface ISearchRepository {
    fun search(term: String): Single<List<Result>>
}

class SearchRepository @Inject constructor(
        private val api: ServerApi,
        private val schedulersProvider: SchedulersProvider
) : ISearchRepository {

    override fun search(term: String): Single<List<Result>> =
            api.search(term)
                    .subscribeOn(schedulersProvider.io())
                    .observeOn(schedulersProvider.ui())
                    .map { it.results }
}
