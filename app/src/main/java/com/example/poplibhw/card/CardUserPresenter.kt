package com.example.poplibhw.card

import android.os.Handler
import android.os.Looper
import android.util.Log
import com.example.poplibhw.core.navigation.RepoScreen
import com.example.poplibhw.repository.GitHubRepository
import com.example.poplibhw.repository.RepoRepository
import com.example.poplibhw.utils.disposeBy
import com.example.poplibhw.utils.subscribeByDefault
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.disposables.CompositeDisposable
import moxy.MvpPresenter

class CardUserPresenter(
    private val gitHubRepository: GitHubRepository,
    private val repoRepository: RepoRepository,
    private val router: Router
) : MvpPresenter<CardUserView>() {

    private val bag = CompositeDisposable()

    fun loadUser(login: String) {
        viewState.showLoading()
        gitHubRepository.getUserByLogin(login)
            .subscribeByDefault()
            .subscribe(
                {
                    viewState.initUser(it)
                    viewState.hideLoading()
                }, {
                    viewState.hideLoading()
                }
            ).disposeBy(bag)
        repoRepository.getReposByUserLogin(login)
            .subscribeByDefault()
            .subscribe(
                {
                    Log.w("", "Download RX")
                    viewState.initRepos(it)
                },
                {
                    viewState.hideLoading()
                }
            ).disposeBy(bag)
    }

    fun openRepo(login: String, name: String) {
        router.navigateTo(RepoScreen(login, name))
    }

    fun onBackPressed(): Boolean {
        router.exit()
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        bag.dispose()
    }

}
