package com.example.poplibhw.user

import android.widget.Toast
import com.example.poplibhw.PopLibHW
import com.example.poplibhw.core.navigation.CardUserScreen
import com.example.poplibhw.core.navigation.UsersScreen
import com.example.poplibhw.model.GitHubUser
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

    fun openCardUser(user: GitHubUser) {
        router.navigateTo(CardUserScreen(user))
        Toast.makeText(PopLibHW.instance, user.login, Toast.LENGTH_SHORT).show()
    }

    fun onBackPressed(): Boolean {
        router.exit()
        return true
    }

}
