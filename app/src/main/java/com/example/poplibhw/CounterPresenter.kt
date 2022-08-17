package com.example.poplibhw

class CountersPresenter(
    private val view: MainView
) {
    private val model = CountersModel()

    fun onCounterClick(id: Int) {
        val newValue = model.next(id)
        view.setText(newValue.toString(), id)
    }

    fun onRestartClick() {
        var i = 0
        val zero = 0
        while (i < 3) {
            model.set(i, 0)
            view.setText(zero.toString(), i)
            i++
        }
    }
}
