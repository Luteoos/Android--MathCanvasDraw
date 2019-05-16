package io.github.luteoos.mathcanvasdraw.view.activity.input

import android.os.Bundle
import com.luteoos.kotlin.mvvmbaselib.BaseActivityMVVM
import io.github.luteoos.mathcanvasdraw.R
import io.github.luteoos.mathcanvasdraw.viewmodels.OnScreenMathKeyboardViewModel

class OnScreenMathKeyboard : BaseActivityMVVM<OnScreenMathKeyboardViewModel>() {
    override fun getLayoutID(): Int = R.layout.activity_math_keyboard

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = getViewModel(this)
        connectToVMMessage()
    }

    override fun onVMMessage(msg: Int?) {
        super.onVMMessage(msg)

    }
}