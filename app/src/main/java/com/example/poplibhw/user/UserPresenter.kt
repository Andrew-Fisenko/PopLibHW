package com.example.poplibhw.user

import android.util.Log
import android.widget.Toast
import com.example.poplibhw.PopLibHW
import com.example.poplibhw.core.navigation.CardUserScreen
import com.example.poplibhw.core.navigation.UsersScreen
import com.example.poplibhw.model.GitHubUser
import com.example.poplibhw.repositiry.GitHubRepository
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.schedulers.Schedulers.io
import moxy.MvpPresenter
import java.util.*
import java.util.concurrent.TimeUnit

class UserPresenter (
    private val repository: GitHubRepository,
    private val router: Router

) : MvpPresenter<UserView> () {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.showLoading()
        repository.getUsers()
            .delay(3, TimeUnit.SECONDS,AndroidSchedulers.mainThread())
            .subscribe(
            {
                viewState.initList(it)
                viewState.hideLoading()
            },
            {
                viewState.showError()
            }
        )
    }

    fun openCardUser(user: GitHubUser) {
        router.navigateTo(CardUserScreen(user))
//        Toast.makeText(PopLibHW.instance, user.login, Toast.LENGTH_SHORT).show()
    }

    fun onBackPressed(): Boolean {
        router.exit()
        return true
    }
}
