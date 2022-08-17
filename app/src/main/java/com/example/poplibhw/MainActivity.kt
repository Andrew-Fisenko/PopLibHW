package com.example.poplibhw

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.poplibhw.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), MainView {

    private lateinit var binding: ActivityMainBinding
    private lateinit var presenter: CountersPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initPresenter()
        presenter.onRestartClick()

        with(binding) {
            btnNumber1.setOnClickListener {
                presenter.onCounterClick(0)
            }
            btnNumber2.setOnClickListener {
                presenter.onCounterClick(1)
            }
            btnNumber3.setOnClickListener {
                presenter.onCounterClick(2)
            }
            btnRestart.setOnClickListener {
                presenter.onRestartClick()
            }
        }
    }

    private fun initPresenter() {
        presenter = CountersPresenter(this)
    }

    override fun setText(counter: String, position: Int) {
        with(binding) {
            when (position) {
                0 -> tvText1.text = counter
                1 -> tvText2.text = counter
                2 -> tvText3.text = counter
            }
        }

    }
}