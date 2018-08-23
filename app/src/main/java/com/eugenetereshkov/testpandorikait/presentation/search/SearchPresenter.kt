package com.eugenetereshkov.testpandorikait.presentation.search

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.eugenetereshkov.testpandorikait.R
import com.eugenetereshkov.testpandorikait.entity.Result
import com.eugenetereshkov.testpandorikait.model.repository.ISearchRepository
import com.eugenetereshkov.testpandorikait.model.system.ResourceManager
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import javax.inject.Inject


@InjectViewState
class SearchPresenter @Inject constructor(
        private val searchRepository: ISearchRepository,
        private val resourceManager: ResourceManager
) : MvpPresenter<SearchView>() {

    private val compositeDisposable = CompositeDisposable()
    private var disposable: Disposable? = null

    override fun onDestroy() {
        disposable?.dispose()
        super.onDestroy()
    }

    fun search(query: String) {
        if (disposable?.isDisposed == false) disposable?.dispose()

        disposable = searchRepository.search(query.replace(" ", "+"))
                .subscribe(this::handleResult, this::handleError)
    }

    private fun handleResult(data: List<Result>) {
        viewState.setData(data)
    }

    private fun handleError(t: Throwable) {
        viewState.showToast(resourceManager.getString(R.string.error))
    }
}
