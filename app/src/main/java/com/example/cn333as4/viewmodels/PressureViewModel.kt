package com.example.cn333as4.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cn333as4.R

class PressureViewModel : ViewModel() {
    private val _unit: MutableLiveData<Int> = MutableLiveData(R.string.bar)

    val unit: LiveData<Int>
        get() = _unit

    fun setUnit(value: Int) {
        _unit.value = value
    }

    private val _pressure: MutableLiveData<String> = MutableLiveData("")

    val pressure: LiveData<String>
        get() = _pressure

    fun getPressureAsFloat(): Float = (_pressure.value ?: "").let {
        return try {
            it.toFloat()
        } catch (e: NumberFormatException) {
            Float.NaN
        }
    }

    fun setPressure(value: String) {
        _pressure.value = value
    }

    fun convert() = getPressureAsFloat().let {
        if (!it.isNaN())
            if (_unit.value == R.string.bar)
                it / 0.0689475729F
            else
                it * 0.0689475729F
        else
            Float.NaN
    }
}