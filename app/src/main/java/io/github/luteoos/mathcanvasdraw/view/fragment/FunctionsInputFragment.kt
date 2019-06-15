package io.github.luteoos.mathcanvasdraw.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.luteoos.kotlin.mvvmbaselib.BaseFragmentMVVM
import io.github.luteoos.mathcanvasdraw.R
import io.github.luteoos.mathcanvasdraw.viewmodels.OnScreenMathKeyboardViewModel
import org.jetbrains.anko.sdk27.coroutines.onClick
import java.util.*

class FunctionsInputFragment: BaseFragmentMVVM<OnScreenMathKeyboardViewModel>() {
    override fun getLayoutID(): Int = R.layout.fragment_functions_input

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view =  super.onCreateView(inflater, container, savedInstanceState)
        viewModel = getViewModel(activity!!)
        connectToVMMessage()
        setBindings(view!!)
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