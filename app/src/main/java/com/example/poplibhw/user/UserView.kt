package com.example.poplibhw.user

import com.example.poplibhw.model.GitHubUser
import io.reactivex.rxjava3.core.Single
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface UserView : MvpView {
    fun initList(list: List<GitHubUser>)
    fun showLoading()
    fun hideLoading()
    fun showError()
}