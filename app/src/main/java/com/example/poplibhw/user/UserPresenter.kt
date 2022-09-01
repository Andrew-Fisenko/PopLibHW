package com.example.poplibhw.user

import android.util.Log
import com.example.poplibhw.core.navigation.CardUserScreen
import com.example.poplibhw.repository.GitHubRepository
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpPresenter
import java.util.concurrent.TimeUnit

class UserPresenter(
    private val repository: GitHubRepository,
    private val router: Router

) : MvpPresenter<UserView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.showLoading()
        repository.getUsers()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .delay(3, TimeUnit.SECONDS, AndroidSchedulers.mainThread())
            .subscribe(
                {
                    viewState.initList(it)
                    viewState.hideLoading()
                },
                {
                    viewState.showError()
                    Log.e("USER_LIST", it.message ?: "!!!")
                }
            )
    }

    fun onItemClick(login: String) {
        router.navigateTo(CardUserScreen(login))
    }

    fun onBackPressed(): Boolean {
        router.exit()
        return true
    }
}
