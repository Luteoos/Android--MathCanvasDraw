package io.github.luteoos.mathcanvasdraw.view.fragment

import android.arch.lifecycle.Observer
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.luteoos.kotlin.mvvmbaselib.BaseFragmentMVVM
import io.github.luteoos.mathcanvasdraw.R
import io.github.luteoos.mathcanvasdraw.network.response.ChartResponse
import io.github.luteoos.mathcanvasdraw.utils.Parameters
import io.github.luteoos.mathcanvasdraw.view.activity.ChartCreatorActivity
import io.github.luteoos.mathcanvasdraw.view.activity.ChartDrawerActivity
import io.github.luteoos.mathcanvasdraw.view.adapter.RVChartList
import io.github.luteoos.mathcanvasdraw.viewmodels.MainScreenViewModel
import kotlinx.android.synthetic.main.fragment_create_chart_main.*
import java.util.*

class CreateChartMainFragment : BaseFragmentMVVM<MainScreenViewModel>() {

    override fun getLayoutID(): Int = R.layout.fragment_create_chart_main

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = Companion.getViewModel(activity!!)
        setBindings()
        viewModel.loadAllChartList()
    }

    private fun setBindings(){
        viewModel.getChartList().observe(this, Observer{
            if(it != null)
                initRVChart(it)
        })
        btnGoToCartCreator.setOnClickListener {
            startChartCreatorActivity()
        }
    }

    private fun initRVChart(chartList: MutableList<ChartResponse>){
        val chartAdapter = RVChartList(activity!!, chartList)
        rvChartList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = chartAdapter
        }
        chartAdapter.getClicked().observe(this, Observer {
            if(it != null){
                startChartDrawerActivity(it)
            }
        })
    }

    private fun startChartCreatorActivity(){
        val intent = Intent(context, ChartCreatorActivity::class.java)
        startActivity(intent)
    }

    private fun startChartDrawerActivity(chartUUID: String){
        val intent = Intent(activity!!, ChartDrawerActivity::class.java)
        intent.putExtra(Parameters.GET_CHART_GUID, chartUUID)
        startActivity(intent)
    }
}