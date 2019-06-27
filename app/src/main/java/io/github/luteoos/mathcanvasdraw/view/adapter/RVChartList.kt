package io.github.luteoos.mathcanvasdraw.view.adapter

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.content.Context
import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.github.luteoos.mathcanvasdraw.R
import io.github.luteoos.mathcanvasdraw.network.response.ChartResponse
import io.github.luteoos.mathcanvasdraw.network.response.FunctionResponse
import kotlinx.android.synthetic.main.rv_chart_list.view.*
import kotlinx.android.synthetic.main.rv_functions_chart_creator.view.*
import org.jetbrains.anko.textColor

class RVChartList(var ctx: Context,
                  var data: MutableList<ChartResponse>) : RecyclerView.Adapter<RVChartList.ChartVH>()  {

    private val clicked : MutableLiveData<String> = MutableLiveData()

    fun getClicked() : LiveData<String> = clicked

    override fun onBindViewHolder(view: ChartVH, position: Int) {
        data[position].let {
            view.name.text = it.name
        }
        view.view.setOnClickListener {
            clicked.value = data[position].guid
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int) = ChartVH(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.rv_chart_list, parent, false))

    override fun getItemCount(): Int = data.size

    class ChartVH(val view: View) : RecyclerView.ViewHolder(view){
        var name = view.tvChartName
        }
}