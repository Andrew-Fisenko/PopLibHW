package com.example.poplibhw

class CountersModel {

    private val counters = mutableListOf(1, 1, 1)

    fun getCurrent(position: Int): Int {
        return counters[position]
    }

    fun next(position: Int): Int {
        counters[position]++
        return counters[position]
    }

    fun set(position: Int, value: Int) {
        counters[position] = value
    }
}
