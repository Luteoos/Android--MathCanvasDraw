package io.github.luteoos.mathcanvasdraw.view.activity

import android.arch.lifecycle.Observer
import android.content.Intent
import android.graphics.Canvas
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.luteoos.kotlin.mvvmbaselib.BaseActivityMVVM
import io.github.luteoos.mathcanvasdraw.R
import io.github.luteoos.mathcanvasdraw.network.response.FunctionResponse
import io.github.luteoos.mathcanvasdraw.utils.Parameters
import io.github.luteoos.mathcanvasdraw.view.adapter.RVFunctionListChartCreator
import io.github.luteoos.mathcanvasdraw.viewmodels.ChartDrawerViewModel
import kotlinx.android.synthetic.main.activity_chart_drawer.*

class ChartDrawerActivity : BaseActivityMVVM<ChartDrawerViewModel>() {

    override fun getLayoutID(): Int = R.layout.activity_chart_drawer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = getViewModel(this)
        setBindings()
        viewModel.loadChart(intent.getStringExtra(Parameters.GET_CHART_GUID))
    }

    private fun setBindings(){
        viewModel.getChart().observe(this, Observer {
            if(it != null){
                setRVFunctions(
                    if(it.functions != null)
                        it.functions!!.toMutableList()
                    else
                        mutableListOf()
                )
            }
        })
        btnMainScreen.setOnClickListener {
            startMainScreenActivity()
        }
//        canvas.holder.unlockCanvasAndPost()
    }

    private fun setRVFunctions(funcList : MutableList<FunctionResponse>){
        rvFunctionList.apply {
            layoutManager = LinearLayoutManager(this@ChartDrawerActivity)
            adapter = RVFunctionListChartCreator(this@ChartDrawerActivity, funcList, false)
        }
    }

    private fun startMainScreenActivity(){
        val intent = Intent(this, MainScreen::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }
}