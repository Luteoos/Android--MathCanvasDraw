package io.github.luteoos.mathcanvasdraw.view.fragment

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.luteoos.kotlin.mvvmbaselib.BaseFragmentMVVM
import io.github.luteoos.mathcanvasdraw.R
import io.github.luteoos.mathcanvasdraw.view.activity.ChartCreatorActivity
import io.github.luteoos.mathcanvasdraw.viewmodels.MainScreenViewModel
import kotlinx.android.synthetic.main.fragment_create_chart_main.*

class CreateChartMainFragment : BaseFragmentMVVM<MainScreenViewModel>() {

    override fun getLayoutID(): Int = R.layout.fragment_create_chart_main

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = Companion.getViewModel(activity!!)
        setBindings()
    }

    private fun setBindings(){
        btnGoToCartCreator.setOnClickListener {
            startChartCreatorActivity()
        }
    }

    private fun startChartCreatorActivity(){
        val intent = Intent(context, ChartCreatorActivity::class.java)
        startActivity(intent)
    }
}