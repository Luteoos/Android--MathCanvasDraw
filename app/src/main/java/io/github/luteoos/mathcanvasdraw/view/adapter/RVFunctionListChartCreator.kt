package io.github.luteoos.mathcanvasdraw.view.adapter

import android.content.Context
import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.github.luteoos.mathcanvasdraw.R
import io.github.luteoos.mathcanvasdraw.network.response.FunctionResponse
import kotlinx.android.synthetic.main.rv_functions_chart_creator.view.*
import org.jetbrains.anko.textColor

class RVFunctionListChartCreator(var ctx: Context,
                                 var data: MutableList<FunctionResponse>,
                                 var isDeletePossible: Boolean = true) : RecyclerView.Adapter<RVFunctionListChartCreator.FunctionVH>()  {

    private val colorList = listOf("#f47146", "#d3f441", "#77709e", "#41f4dc", "#8241f4", "#190477", "#f441e5", "#c4ddb5", "#f44152", "#f44842", "#5e0109", "#dbcea0", "#e241f4", "#5311a3", "#bab851", "#42f450", "#d8f4d0", "#726063", "#d8c5b6", "#ebf707", "#490a1b", "#9668d6", "#635102", "#82736d", "#657a6d", "#6c4b91", "#a0776e", "#04a7d8", "#1e4c26", "#4f3528", "#542c07", "#f91d51", "#444423", "#e8c5d5", "#15243a", "#121347", "#cb8adb", "#93af7c", "#35590b", "#3f1056", "#590a44", "#4f051a", "#7c93a3", "#290f51", "#edd5e4", "#064f23", "#f6f2f7", "#60064b", "#8cf2ac", "#630707")

    fun addFunctionToData(func : FunctionResponse){
        data.add(func)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(view: FunctionVH, position: Int) {
        if(!isDeletePossible)
            view.button.visibility = View.GONE
        data[position].let {
            it.color = colorList[position]
            view.name.text = it.name ?: ""
            view.body.text = it.functionBody ?: ""
            view.details.text = "(${it.min ?: ""},${it.max ?: ""}) approx: ${it.approximation?: ""}"
            view.color.textColor = Color.parseColor(it.color)

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
        var color = view.tvColor
    }
}