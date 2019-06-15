package io.github.luteoos.mathcanvasdraw.viewmodels

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.luteoos.kotlin.mvvmbaselib.BaseViewModel

class OnScreenMathKeyboardViewModel: BaseViewModel() {

    private val func : MutableLiveData<String> = MutableLiveData()
    private val operators: MutableList<String> = mutableListOf("+","*","/","^")
    private val longOperators: MutableList<String> = mutableListOf("cos","sin","tg","ctg","Sqrt","ln")
    private var lastComponentLength = 0
init{
    func.value = "f(x)="
}
    fun getFunction(): LiveData<String> = func

    fun addToFunction(test: String){
        var test = test
        if(func.value!!.isEmpty())
            func.value += test
        else {
            if ((operators.contains(test) || longOperators.contains(test)) && operators.contains(func.value!!.last().toString()))
                return
            if(operators.contains(test) && func.value!!.last().toString() == "-")
                return
            if (test == "-" && func.value!!.last().toString() == "-")
                return
            if (test == "x" && func.value!!.last().toString() == "x")
                return
            if(longOperators.contains(test))
                test += "("
            func.value += test
            lastComponentLength = test.length
        }
    }

    fun backspaceFunction(){
        if(func.value!!.last().toString() != "=")
            func.value = func.value!!.substring(0, func.value!!.length - 1)
    }

    fun getData(): String? {
        if(func.value!!.length <= 5)
            return null
        return func.value
    }
}