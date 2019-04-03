package io.github.luteoos.mathcanvasdraw.view

import android.os.Bundle
import com.luteoos.kotlin.mvvmbaselib.BaseActivityMVVM
import es.dmoral.toasty.Toasty
import io.github.luteoos.mathcanvasdraw.R
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
    }

    override fun onVMMessage(msg: String?) {
        Timber.log(0, msg)
    }
}