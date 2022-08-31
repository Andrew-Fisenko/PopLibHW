package com.example.poplibhw.card

import android.os.Handler
import android.os.Looper
import com.example.poplibhw.core.navigation.RepoScreen
import com.example.poplibhw.repositiry.GitHubRepository
import com.example.poplibhw.repositiry.RepoRepository
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
        gitHubRepository.getUserById(login)
            .subscribeByDefault()
            .subscribe(
                {
                    viewState.initUser(it)
//                    viewState.hideLoading()
                },
                {
//                    viewState.showLoading()
//                    viewState.showError()
//                    Handler(Looper.getMainLooper()).postDelayed({
//                        onBackPressed()
//                    }, 3000)
                }
            ).disposeBy(bag)
        repoRepository.getRepo(login)
            .subscribeByDefault()
            .subscribe(
                {
                    viewState.initRepos(it)
                },
                {

                }
            )
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
