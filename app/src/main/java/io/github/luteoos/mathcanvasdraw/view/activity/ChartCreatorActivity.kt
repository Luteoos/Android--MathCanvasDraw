package io.github.luteoos.mathcanvasdraw.view.activity

import android.app.Activity
import android.arch.lifecycle.Observer
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.luteoos.kotlin.mvvmbaselib.BaseActivityMVVM
import es.dmoral.toasty.Toasty
import io.github.luteoos.mathcanvasdraw.R
import io.github.luteoos.mathcanvasdraw.network.request.FunctionRequest
import io.github.luteoos.mathcanvasdraw.network.response.FunctionResponse
import io.github.luteoos.mathcanvasdraw.utils.Parameters
import io.github.luteoos.mathcanvasdraw.view.activity.input.FunctionCreatorActivity
import io.github.luteoos.mathcanvasdraw.view.adapter.RVFunctionListChartCreator
import io.github.luteoos.mathcanvasdraw.viewmodels.ChartCreatorViewModel
import kotlinx.android.synthetic.main.activity_chart_creator.*

class ChartCreatorActivity : BaseActivityMVVM<ChartCreatorViewModel>() {

    private val REQUEST_ADD_FUNCTION = 1945
    private lateinit var rvAdapter : RVFunctionListChartCreator

    override fun getLayoutID(): Int = R.layout.activity_chart_creator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = getViewModel(this)
        connectToVMMessage()
        setBindings()
        initRV()
    }

    override fun onVMMessage(msg: Int?) {
        super.onVMMessage(msg)
        when(msg){
            -10 -> Toasty.error(this, "Error, something went wrong").show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode){
            REQUEST_ADD_FUNCTION -> {
                if(resultCode == Activity.RESULT_OK)
                   viewModel.validateFunction(data!!.getSerializableExtra(Parameters.GET_FUNCTION_AS_EXTRAS) as FunctionRequest)
            }
        }
    }

    private fun setBindings(){
        viewModel.getValidatedFunction().observe(this, Observer {
            if(it != null)
                rvAdapter.addFunctionToData(it)
        })

        btnAddFunction.setOnClickListener {
            addFunction()
        }
    }

    private fun initRV(){
        rvAdapter = RVFunctionListChartCreator(this, mutableListOf())
        rvFunctions.layoutManager = LinearLayoutManager(this)
        rvFunctions.adapter = rvAdapter
    }

    private fun addFunction(){
        val intent = Intent(this, FunctionCreatorActivity::class.java)
        startActivityForResult(intent, REQUEST_ADD_FUNCTION)
    }
}