package io.github.luteoos.mathcanvasdraw.viewmodels

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.luteoos.kotlin.mvvmbaselib.BaseViewModel
import io.github.luteoos.mathcanvasdraw.network.response.ChartResponse
import io.realm.Realm

class ChartDrawerViewModel : BaseViewModel() {

    private val chart : MutableLiveData<ChartResponse> = MutableLiveData()

    fun getChart() : LiveData<ChartResponse> = chart

    fun loadChart(guid: String){
        var chartTemp: ChartResponse? = null
        Realm.getDefaultInstance().executeTransaction {
            chartTemp = it.where(ChartResponse::class.java)
                .equalTo("guid", guid)
                .findFirst()
        }
        chart.value = chartTemp
    }

}