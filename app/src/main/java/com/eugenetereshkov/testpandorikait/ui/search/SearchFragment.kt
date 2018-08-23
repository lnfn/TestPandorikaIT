package com.eugenetereshkov.testpandorikait.ui.search


import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import androidx.core.widget.toast
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.eugenetereshkov.testpandorikait.DI
import com.eugenetereshkov.testpandorikait.R
import com.eugenetereshkov.testpandorikait.entity.Result
import com.eugenetereshkov.testpandorikait.extention.bindTo
import com.eugenetereshkov.testpandorikait.model.system.SchedulersProvider
import com.eugenetereshkov.testpandorikait.presentation.search.SearchPresenter
import com.eugenetereshkov.testpandorikait.presentation.search.SearchView
import com.eugenetereshkov.testpandorikait.ui.global.BaseFragment
import com.jakewharton.rxbinding2.widget.textChanges
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.fragment_search.*
import toothpick.Toothpick
import java.util.concurrent.TimeUnit
import javax.inject.Inject


class SearchFragment : BaseFragment(), SearchView {

    companion object {
        const val TAG = "search_fragment"

        fun newInstance() = SearchFragment()
    }

    override val idResLayout: Int = R.layout.fragment_search

    @InjectPresenter
    lateinit var presenter: SearchPresenter
    @Inject
    lateinit var appSchedulers: SchedulersProvider

    private val disposable = CompositeDisposable()
    private val adapter by lazy { SearchAdapter() }

    @ProvidePresenter
    fun providePresenter(): SearchPresenter =
            Toothpick.openScopes(DI.NETWORK_SCOPE, DI.SEARCH_FRAGMENT_SCOPE)
                    .getInstance(SearchPresenter::class.java).also {
                        Toothpick.closeScope(DI.SEARCH_FRAGMENT_SCOPE)
                    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Toothpick.inject(this, Toothpick.openScope(DI.APP_SCOPE))
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = this@SearchFragment.adapter
        }
    }

    override fun onResume() {
        super.onResume()

        editTextSearch.textChanges()
                .debounce(300, TimeUnit.MILLISECONDS)
                .map { it.trim() }
                .filter { it.isNotEmpty() }
                .map { it.toString() }
                .distinctUntilChanged()
                .observeOn(appSchedulers.ui())
                .subscribe(presenter::search)
                .bindTo(disposable)
    }

    override fun onPause() {
        super.onPause()
        disposable.clear()
    }

    override fun setData(data: List<Result>) {
        adapter.submitList(data)
    }

    override fun showToast(string: String) {
        context?.toast(string)
    }
}
