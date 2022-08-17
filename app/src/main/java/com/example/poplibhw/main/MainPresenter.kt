package com.example.poplibhw

import com.example.poplibhw.core.navigation.UsersScreen
import com.example.poplibhw.main.MainView
import com.example.poplibhw.repositiry.GitHubRepository
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter

class MainPresenter(
    private val router: Router
): MvpPresenter<MainView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.navigateTo(UsersScreen)
    }

    fun onBackPressed() {
       router.exit()
    }
}
