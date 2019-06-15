package io.github.luteoos.mathcanvasdraw.viewmodels

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.luteoos.kotlin.mvvmbaselib.BaseViewModel

class OnScreenMathKeyboardViewModel: BaseViewModel() {

    private val func : MutableLiveData<String> = MutableLiveData()
    private val list: MutableList<String> = mutableListOf()
    private val operators: MutableList<String> = mutableListOf("+","*","/","^")
    private val longOperators: MutableList<String> = mutableListOf("cos","sin","tg","ctg","Sqrt","ln")
    private val BEGIN = "f(x)="
init{
    func.value = BEGIN
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
            list.add(test)
            updateFunc()
        }
    }

    fun backspaceFunction(){
        if(list.size != 0) {
            list.removeAt(list.size - 1)
            updateFunc()
        }
    }

    fun getData(): String? {
        if(func.value!!.length <= 5)
            return null
        return func.value
    }

    private fun updateFunc(){
        func.value = BEGIN + listToString(list)
    }

    private fun listToString(list: MutableList<String>): String {
        var new = ""
        list.forEach {
            new += it
        }
        return new
    }
}