package com.eugenetereshkov.testpandorikait.presentation.search

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.eugenetereshkov.testpandorikait.entity.Result


@StateStrategyType(AddToEndSingleStrategy::class)
interface SearchView : MvpView {
    fun showToast(string: String)
    fun setData(data: List<Result>)
}
