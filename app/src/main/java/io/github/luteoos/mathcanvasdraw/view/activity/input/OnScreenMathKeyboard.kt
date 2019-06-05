package io.github.luteoos.mathcanvasdraw.view.activity.input

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import com.luteoos.kotlin.mvvmbaselib.BaseActivityMVVM
import io.github.luteoos.mathcanvasdraw.R
import io.github.luteoos.mathcanvasdraw.view.adapter.InputPagerAdapter
import io.github.luteoos.mathcanvasdraw.viewmodels.OnScreenMathKeyboardViewModel
import kotlinx.android.synthetic.main.activity_math_keyboard.*
import org.jetbrains.anko.sdk27.coroutines.onClick

class OnScreenMathKeyboard : BaseActivityMVVM<OnScreenMathKeyboardViewModel>() {
    override fun getLayoutID(): Int = R.layout.activity_math_keyboard

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = getViewModel(this)
        connectToVMMessage()
        inputPager.adapter = InputPagerAdapter(supportFragmentManager)
        inputPager.offscreenPageLimit = 2
        tvFunction.text = "TEST TU KLIKNIJ PAL"
        setBindings()
    }

    private fun setBindings(){
        fab.onClick {
            when(inputPager.currentItem){
                0 -> inputPager.setCurrentItem(1, true)
                1 -> inputPager.setCurrentItem(0, true)
            }
        }
        tvFunction.movementMethod = ScrollingMovementMethod()
        viewModel.getFunction().observe(this, Observer { value -> tvFunction.text = value })
        tvFunction.onClick {
            viewModel.add()

        }
    }

    override fun onVMMessage(msg: Int?) {
        super.onVMMessage(msg)

    }
}