package io.github.luteoos.mathcanvasdraw.view.activity

import android.content.Intent
import android.os.Bundle
import com.luteoos.kotlin.mvvmbaselib.BaseActivityMVVM
import es.dmoral.toasty.Toasty
import io.github.luteoos.mathcanvasdraw.R
import io.github.luteoos.mathcanvasdraw.view.activity.input.OnScreenMathKeyboard
import io.github.luteoos.mathcanvasdraw.viewmodels.SplashScreenViewModel
import kotlinx.android.synthetic.main.activity_splashcreen.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import timber.log.Timber

class SplashScreen: BaseActivityMVVM<SplashScreenViewModel>() {

    override fun getLayoutID(): Int = R.layout.activity_splashcreen

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = getViewModel(this)
        connectToVMMessage()
        button.onClick {
            startActivity()
        }
    }

    override fun onVMMessage(msg: Int?) {
        super.onVMMessage(msg)
        when(msg){

        }
    }

    private fun startActivity(){
        val intent = Intent(this, OnScreenMathKeyboard::class.java)
        //intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }
}