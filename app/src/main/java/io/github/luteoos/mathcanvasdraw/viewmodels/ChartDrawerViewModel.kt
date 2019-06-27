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
import java.util.*

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
            val client = RestApi.createService(API::class.java).getEvvaluateFunction(function.guid!!)
            CompositeDisposable().add(client
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe ({
                    if(it.code() == 200) {
                        it.body().let {
                            evaluatedFunctionsList.add(
                                FunctionEvaluateWrapper().apply {
                                    x = it!!.x
                                    y = it.y
                                    guid = function.guid
                                    color = Parameters.colorList[evaluatedFunctionsList.size]
                                }
                            )
                        }
                        listUpdated()
                    }
                    else
                        send(-10)
                },{
                    send(-10)
                }))
        }
    }

    //TODO here all loading evaluating from api etc
//    fun drawOnCanvas(holder: SurfaceHolder){
//        this.holder = holder
//        val canvas = holder.lockCanvas()
//        canvas.apply{
//            drawLine(0f,0f,100f,0f, getPaint("#FFFFFF"))
//            drawLine(0f,0f,0f,100f, getPaint("#FFFFFF"))
//            drawLine(0f,0f,canvasWidth-100f,1000f, getPaint("#FFFFFF"))
//        }
//        holder.unlockCanvasAndPost(canvas)
//    }
//
//    fun test(){
//        val canvas = holder!!.lockCanvas()
//        canvas.apply{
//            drawLine(0f,0f,100f,0f, getPaint("#aaaaFF"))
//            drawLine(0f,0f,0f,100f, getPaint("#bbbbFF"))
//            drawLine(0f,0f,1000f,1000f, getPaint("#ddFFdd"))
//            drawLine(-10f,-50f, 100f, 190f, getPaint("#aaaaaa"))
//        }
//        holder!!.unlockCanvasAndPost(canvas)
//    }

    private fun listUpdated(){
        if( evaluatedFunctionsList.size < chart.value?.functions!!.size)
            return
        CanvasDrawerHelper(holder!!, canvasWidth, canvasHeight).calculateConstrains(evaluatedFunctionsList)
    }
}