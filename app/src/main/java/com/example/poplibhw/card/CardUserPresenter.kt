package com.example.poplibhw.card

import android.os.Handler
import android.os.Looper
import com.example.poplibhw.repositiry.GitHubRepository
import com.example.poplibhw.utils.disposeBy
import com.example.poplibhw.utils.subscribeByDefault
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.disposables.CompositeDisposable
import moxy.MvpPresenter

class CardUserPresenter(
    private val repository: GitHubRepository,
    private val router: Router
) : MvpPresenter<CardUserView>() {

    private val bag = CompositeDisposable()

    fun loadUser(login: String) {

        viewState.showLoading()
        repository.getUserById(login)

            .subscribeByDefault()
            .subscribe(
                {
                    viewState.initUser(it)
                    viewState.hideLoading()
                },
                {
                    viewState.showLoading()
                    viewState.showError()
                    Handler(Looper.getMainLooper()).postDelayed({
                        onBackPressed()
                    }, 3000)
                }
            ).disposeBy(bag)
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
