package com.example.poplibhw.repo

import android.os.Handler
import android.os.Looper
import com.example.poplibhw.repositiry.RepoRepository
import com.example.poplibhw.utils.disposeBy
import com.example.poplibhw.utils.subscribeByDefault
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import moxy.MvpPresenter
import java.util.concurrent.TimeUnit

class RepoPresenter(
    private val repoRepository: RepoRepository,
    private val router: Router
) : MvpPresenter<RepoView>() {

    private val bag = CompositeDisposable()

    fun loadRepo(login: String, name: String) {
        repoRepository.getReposByLogin(login, name)
            .subscribeByDefault()
            .delay(1, TimeUnit.SECONDS, AndroidSchedulers.mainThread())
            .subscribe(
                {
                    viewState.initRepo(it)
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
