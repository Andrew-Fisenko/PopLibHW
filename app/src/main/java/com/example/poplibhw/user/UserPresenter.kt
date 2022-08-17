package com.example.poplibhw.user

import com.example.poplibhw.core.navigation.UsersScreen
import com.example.poplibhw.repositiry.GitHubRepository
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter

class UserPresenter (
    private val repository: GitHubRepository,
    private val router: Router

) : MvpPresenter<UserView> () {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.initList(repository.getUsers())
    }

    fun onBackPressed(): Boolean {
        router.exit()
        return true
    }

}
