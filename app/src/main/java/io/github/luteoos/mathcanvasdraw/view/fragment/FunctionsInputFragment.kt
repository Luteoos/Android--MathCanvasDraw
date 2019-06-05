package io.github.luteoos.mathcanvasdraw.view.fragment

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.luteoos.kotlin.mvvmbaselib.BaseFragmentMVVM
import io.github.luteoos.mathcanvasdraw.R
import io.github.luteoos.mathcanvasdraw.viewmodels.OnScreenMathKeyboardViewModel
import kotlinx.android.synthetic.main.fragment_functions_input.*

class FunctionsInputFragment: BaseFragmentMVVM<OnScreenMathKeyboardViewModel>() {
    override fun getLayoutID(): Int = R.layout.fragment_functions_input

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view =  super.onCreateView(inflater, container, savedInstanceState)
        viewModel = getViewModel(activity!!)
        connectToVMMessage()
        viewModel.getFunction().observe(this, Observer { value -> textView2.text = value })
        return view
    }
}