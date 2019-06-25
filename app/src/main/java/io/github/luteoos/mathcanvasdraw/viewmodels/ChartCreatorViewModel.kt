package io.github.luteoos.mathcanvasdraw.viewmodels

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.luteoos.kotlin.mvvmbaselib.BaseViewModel
import io.github.luteoos.mathcanvasdraw.network.API
import io.github.luteoos.mathcanvasdraw.network.RestApi
import io.github.luteoos.mathcanvasdraw.network.request.FunctionRequest
import io.github.luteoos.mathcanvasdraw.network.response.FunctionResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.*

class ChartCreatorViewModel : BaseViewModel() {

    private val validatedFunction : MutableLiveData<FunctionResponse> = MutableLiveData()

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