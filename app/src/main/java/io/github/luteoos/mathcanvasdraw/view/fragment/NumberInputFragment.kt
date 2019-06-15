package io.github.luteoos.mathcanvasdraw.view.fragment

import android.arch.lifecycle.Observer
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.luteoos.kotlin.mvvmbaselib.BaseFragmentMVVM
import io.github.luteoos.mathcanvasdraw.R
import io.github.luteoos.mathcanvasdraw.viewmodels.OnScreenMathKeyboardViewModel
import kotlinx.android.synthetic.main.fragment_number_input.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import java.util.ArrayList

class NumberInputFragment: BaseFragmentMVVM<OnScreenMathKeyboardViewModel>() {
    override fun getLayoutID(): Int = R.layout.fragment_number_input

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view =  super.onCreateView(inflater, container, savedInstanceState)
        viewModel = getViewModel(activity!!)
        connectToVMMessage()
        setBindings(view!!)
        //viewModel.getFunction().observe(this, Observer { value -> tvTEST2.text = value })
        return view
    }

    private fun setBindings(view: View){
        val buttons : ArrayList<View>? = view.touchables
        buttons?.forEach {component ->
            if(component is Button)
                component.let {
                    it.onClick {
                        viewModel.addToFunction((it as Button).text.toString())
                    }
                }
        }
    }

}