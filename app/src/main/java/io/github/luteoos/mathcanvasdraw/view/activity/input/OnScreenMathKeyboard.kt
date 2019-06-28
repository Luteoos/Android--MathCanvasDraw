package io.github.luteoos.mathcanvasdraw.view.activity.input

import android.app.Activity
import android.arch.lifecycle.Observer
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.widget.Button
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
        setBindings()
    }

    private fun setBindings(){
        fab.onClick {
            when(inputPager.currentItem){
                0 -> {
                    inputPager.setCurrentItem(1, true)
                    fab.setImageDrawable(getDrawable(R.drawable.ic_arrow_back_black_24dp))
                }
                1 -> {inputPager.setCurrentItem(0, true)
                    fab.setImageDrawable(getDrawable(R.drawable.ic_arrow_forward_black_24dp))}
            }
        }
        fabBackspace.onClick {
            viewModel.backspaceFunction()
        }
        btnAccept.onClick{
            returnResult()
        }
        tvFunction.movementMethod = ScrollingMovementMethod()
        viewModel.getFunction().observe(this, Observer { value -> tvFunction.text = value })
        btnX.let {
            it.onClick {
            viewModel.addToFunction((it as Button).text.toString())
            }
        }
        btnPlus.let {
            it.onClick {
                viewModel.addToFunction((it as Button).text.toString())
            }
        }
        btnMinus.let {
            it.onClick {
                viewModel.addToFunction((it as Button).text.toString())
            }
        }
        btnMultiply.let {
            it.onClick {
                viewModel.addToFunction((it as Button).text.toString())
            }
        }
        btnDivide.let {
            it.onClick {
                viewModel.addToFunction((it as Button).text.toString())
            }
        }

    }

    private fun returnResult(){
        val result = viewModel.getData() ?: return
        val data = Intent().setData(Uri.parse(result))
        setResult(Activity.RESULT_OK, data)
        finish()
    }

    override fun onVMMessage(msg: Int?) {
        super.onVMMessage(msg)

    }
}