package io.github.luteoos.mathcanvasdraw.viewmodels

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.luteoos.kotlin.mvvmbaselib.BaseViewModel
import io.github.luteoos.mathcanvasdraw.network.API
import io.github.luteoos.mathcanvasdraw.network.RestApi
import io.github.luteoos.mathcanvasdraw.network.request.ChartRequest
import io.github.luteoos.mathcanvasdraw.network.request.FunctionRequest
import io.github.luteoos.mathcanvasdraw.network.response.ChartResponse
import io.github.luteoos.mathcanvasdraw.network.response.FunctionResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.realm.Realm
import io.realm.RealmList
import java.util.*

class ChartCreatorViewModel : BaseViewModel() {

    val CHART_SUCCESS_UPLOAD = 666

    private val validatedFunction : MutableLiveData<FunctionResponse> = MutableLiveData()
    private val chartUUID : MutableLiveData<String> = MutableLiveData()
    private var chart : ChartResponse = ChartResponse()

    fun getChartUUID() : LiveData<String> = chartUUID

    fun getValidatedFunction() : LiveData<FunctionResponse> = validatedFunction

    fun validateFunction(request: FunctionRequest){
        val client = RestApi.createService(API::class.java).postFunction(request)
        CompositeDisposable().add(client
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe ({
                if(it.code() == 200)
                    updateValidated(request, it.body()!!)
                else
                    send(-10)
            },{
            send(-10)
        }))
    }

    fun createChart(){
        val request = ChartRequest().apply {
            name = Date().time.toString()
        }
        val client = RestApi.createService(API::class.java).postChart(request)
        CompositeDisposable().add(client
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe ({
                if(it.code() == 200) {
                    chart.guid = it.body()
                    chart.name = request.name
                    send(CHART_SUCCESS_UPLOAD)
                }
                else
                    send(-10)
            },{
                send(-10)
            }))
    }

    fun updateValidatedChart(funcList : MutableList<FunctionResponse>){
        chart.apply {
            functions = RealmList<FunctionResponse>().apply {
                addAll(funcList)
            }
        }
        Realm.getDefaultInstance().executeTransaction{
            it.copyToRealmOrUpdate(chart)
        }
        chartUUID.value = chart.guid
    }

    private fun updateValidated(request: FunctionRequest, updatedGuid: String){
        val validated = FunctionResponse().apply {
            guid = updatedGuid
            request.let {
                functionBody = it.functionString
                name = it.name
                min = it.min
                max = it.max
                approximation = it.approximation
            }
        }
        validatedFunction.value = validated
        validatedFunction.value = null
    }
}