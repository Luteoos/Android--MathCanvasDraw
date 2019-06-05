package io.github.luteoos.mathcanvasdraw.viewmodels

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.luteoos.kotlin.mvvmbaselib.BaseViewModel

class OnScreenMathKeyboardViewModel: BaseViewModel() {

   private val func : MutableLiveData<String> = MutableLiveData()

init{
    func.value = ""
}
    fun getFunction(): LiveData<String> = func

    fun add(){
        func.value += 1
    }
}