package io.github.luteoos.mathcanvasdraw.viewmodels

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.luteoos.kotlin.mvvmbaselib.BaseViewModel
import io.github.luteoos.mathcanvasdraw.network.response.ChartResponse
import io.realm.Realm

class MainScreenViewModel: BaseViewModel() {

    private val chartList : MutableLiveData<MutableList<ChartResponse>> = MutableLiveData()

    fun getChartList() : LiveData<MutableList<ChartResponse>> = chartList

    fun loadAllChartList(){
        var tempList = mutableListOf<ChartResponse>()
        Realm.getDefaultInstance().executeTransaction {
            tempList = it.where(ChartResponse::class.java).findAll().toMutableList()
        }
        chartList.value = tempList
    }

}