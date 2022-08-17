package com.example.poplibhw.card

import com.example.poplibhw.repositiry.GitHubRepository
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter

class CardUserPresenter (
    private val repository: GitHubRepository,
    private val router: Router

) : MvpPresenter<CardUserView> () {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.initUser(repository.getUser())
    }

    fun onBackPressed(): Boolean {
        router.exit()
        return true
    }

}