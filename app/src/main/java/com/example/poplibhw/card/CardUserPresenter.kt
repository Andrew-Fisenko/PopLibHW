package com.example.poplibhw.card

import com.example.poplibhw.repositiry.GitHubRepository
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import moxy.MvpPresenter
import java.util.concurrent.TimeUnit

class CardUserPresenter(
    private val repository: GitHubRepository,
    private val router: Router
) : MvpPresenter<CardUserView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.showLoading()
        repository.getUser()
            .delay(1, TimeUnit.SECONDS, AndroidSchedulers.mainThread())
            .subscribe(
                {
                    viewState.initUser(it)
                    viewState.hideLoading()
                },
                {
                    viewState.showError()
                    Thread.sleep(3000)
                    viewState.hideLoading()
//                    Thread.sleep(1000)
//                    onBackPressed()
                }
            )
    }

    fun onBackPressed(): Boolean {
        router.exit()
        return true
    }
}
