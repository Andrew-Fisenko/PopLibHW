package com.example.poplibhw.repo

import com.example.poplibhw.model.Repo
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface RepoView : MvpView {

    fun initRepo(repo: Repo)
    fun showLoading()
    fun hideLoading()
    fun showError()
}