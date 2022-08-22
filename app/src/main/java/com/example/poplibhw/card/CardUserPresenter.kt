package com.example.poplibhw.card

import android.os.Handler
import android.os.Looper
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
            .delay(2, TimeUnit.SECONDS, AndroidSchedulers.mainThread())
            .subscribe(
                {
                    viewState.initUser(it)
                    viewState.hideLoading()
                },
                {
                    viewState.showLoading()
                    viewState.showError()
                    Handler(Looper.getMainLooper()).postDelayed({
                        viewState.hideLoading()
                        onBackPressed()
                    }, 3000)
                }
            )
    }

    fun onBackPressed(): Boolean {
        router.exit()
        return true
    }
}
