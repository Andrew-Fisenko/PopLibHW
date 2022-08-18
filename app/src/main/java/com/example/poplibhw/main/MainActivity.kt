package com.example.poplibhw.main

import android.os.Bundle
import com.example.poplibhw.MainPresenter
import com.example.poplibhw.PopLibHW
import com.example.poplibhw.R
import com.example.poplibhw.core.OnBackPressedListener
import com.example.poplibhw.databinding.ActivityMainBinding
import com.github.terrakok.cicerone.androidx.AppNavigator
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter

class MainActivity : MvpAppCompatActivity(), MainView {

    private val navigator = AppNavigator(this, R.id.containerMain)
    private lateinit var binding: ActivityMainBinding

    private val presenter by moxyPresenter { MainPresenter(PopLibHW.instance.router) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        PopLibHW.instance.navigationHolder.setNavigator(navigator)
    }

    override fun onPause() {
        PopLibHW.instance.navigationHolder.removeNavigator()
        super.onPause()
    }

    override fun onBackPressed() {
        supportFragmentManager.fragments.forEach { currentFragment ->
            if (currentFragment is OnBackPressedListener && currentFragment.onBackPressed()) {
                return
            }
        }
        presenter.onBackPressed()
    }

}
