package io.github.luteoos.mathcanvasdraw.view

import android.os.Bundle
import com.luteoos.kotlin.mvvmbaselib.BaseActivityMVVM
import io.github.luteoos.mathcanvasdraw.R
import io.github.luteoos.mathcanvasdraw.viewmodels.SplashScreenViewModel

class SplashScreen: BaseActivityMVVM<SplashScreenViewModel>() {

    override fun getLayoutID(): Int = R.layout.activity_splashcreen

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = SplashScreenViewModel()
        connectToVMMessage()
    }
}