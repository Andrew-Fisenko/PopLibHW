package com.example.poplibhw.card

import com.example.poplibhw.model.GitHubUser
import com.example.poplibhw.model.Repo
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface CardUserView : MvpView {
    fun initUser(user: GitHubUser)
    fun initRepos(list: List<Repo>)
    fun showLoading()
    fun hideLoading()
    fun showError()
}