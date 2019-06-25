package io.github.luteoos.mathcanvasdraw.view.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.github.luteoos.mathcanvasdraw.R
import io.github.luteoos.mathcanvasdraw.network.response.FunctionResponse
import kotlinx.android.synthetic.main.rv_functions_chart_creator.view.*

class RVFunctionListChartCreator(var ctx: Context,
                                 var data: MutableList<FunctionResponse>) : RecyclerView.Adapter<RVFunctionListChartCreator.FunctionVH>()  {

    fun addFunctionToData(func : FunctionResponse){
        data.add(func)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(view: FunctionVH, position: Int) {
        data[position].let {
            view.name.text = it.name ?: ""
            view.body.text = it.functionBody ?: ""
            view.details.text = "(${it.min ?: ""},${it.max ?: ""}) approx: ${it.approximation?: ""}"

        }
        view.button.setOnClickListener {
            data.removeAt(position)
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int) = FunctionVH(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.rv_functions_chart_creator, parent, false))

    override fun getItemCount(): Int = data.size

    class FunctionVH(val view: View) : RecyclerView.ViewHolder(view){
        var name = view.tvFunctionName
        var body = view.tvFunctionBody
        var button = view.btnDeleteFunc
        var details = view.tvBoundaries
    }
}