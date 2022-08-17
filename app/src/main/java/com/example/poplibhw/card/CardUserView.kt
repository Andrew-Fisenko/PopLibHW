package com.example.poplibhw.card

import com.example.poplibhw.model.GitHubUser
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface CardUserView : MvpView {
    fun initUser(user: GitHubUser)
}