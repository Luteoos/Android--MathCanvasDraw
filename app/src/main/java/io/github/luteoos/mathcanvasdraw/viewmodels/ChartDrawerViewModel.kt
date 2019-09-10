package io.github.luteoos.mathcanvasdraw.viewmodels

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.SurfaceHolder
import com.luteoos.kotlin.mvvmbaselib.BaseViewModel
import io.github.luteoos.mathcanvasdraw.network.API
import io.github.luteoos.mathcanvasdraw.network.RestApi
import io.github.luteoos.mathcanvasdraw.network.request.ChartRequest
import io.github.luteoos.mathcanvasdraw.network.response.ChartResponse
import io.github.luteoos.mathcanvasdraw.utils.CanvasDrawerHelper
import io.github.luteoos.mathcanvasdraw.utils.Parameters
import io.github.luteoos.mathcanvasdraw.wrapper.FunctionEvaluateWrapper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.realm.Realm
import timber.log.Timber
import java.util.*
import java.util.concurrent.TimeUnit

class ChartDrawerViewModel : BaseViewModel() {

    private val chart : MutableLiveData<ChartResponse> = MutableLiveData()
    private val evaluatedFunctionsList : MutableList<FunctionEvaluateWrapper> = mutableListOf()
    private var holder : SurfaceHolder? = null
    private var canvasWidth = 0
    private var canvasHeight = 0

    fun getChart() : LiveData<ChartResponse> = chart

    fun initHolder(holder: SurfaceHolder, width: Int, height: Int){
        this.holder = holder
        canvasWidth = width
        canvasHeight = height
        getEvaluatedFunctions()
    }

    fun loadChart(guid: String){
        var chartTemp: ChartResponse? = null
        Realm.getDefaultInstance().executeTransaction {
            chartTemp = it.where(ChartResponse::class.java)
                .equalTo("guid", guid)
                .findFirst()
        }
        chart.value = chartTemp
    }

    private fun getEvaluatedFunctions(){
        chart.value!!.functions!!.forEach {function ->
            function.color = function.customColor ?: Parameters.colorList[evaluatedFunctionsList.size]
            val client = RestApi.createService(API::class.java).getEvvaluateFunction(function.guid!!)
            CompositeDisposable().add(client
                .timeout(60, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe ({
                    if(it.code() == 200) {
                        Timber.i("MathDrawerCanvas code 200 Request: ${it.headers()}")
                        it.body().let {
                            evaluatedFunctionsList.add(
                                FunctionEvaluateWrapper().apply {
                                    x = it!!.x
                                    y = it.y
                                    guid = function.guid
                                    color = function.color
                                }
                            )
                        }
                        listUpdated()
                    }
                    else {
                        Timber.e("MathDrawerCanvas error ${it.code()}")
                        send(-10)
                    }
                },{
                    Timber.e("MathDrawerCanvas error ${it.message}")
                    send(-10)
                }))
        }
    }

    private fun listUpdated(){
        if( evaluatedFunctionsList.size < chart.value?.functions!!.size)
            return
        Timber.i("MathDrawerCanvas All evaluate calls ended")
        CanvasDrawerHelper(holder!!, canvasWidth, canvasHeight).calculateConstrains(evaluatedFunctionsList)
    }
}