package io.github.luteoos.mathcanvasdraw.utils

import android.graphics.Color
import android.graphics.Paint
import android.view.SurfaceHolder
import io.github.luteoos.mathcanvasdraw.wrapper.FunctionEvaluateWrapper
import kotlin.math.abs
import kotlin.math.max

class CanvasDrawerHelper(var holder: SurfaceHolder,
                         var width: Int,
                         var height: Int) {
    private var minX = 0.0
    private var maxX = 0.0
    private var minY = 0.0
    private var maxY = 0.0
    private var spanX = 0.0
    private var spanY = 0.0
    private var scaleX = 0.0
    private var scaleY = 0.0
    private var scale = 0.0

    fun calculateConstrains(list : MutableList<FunctionEvaluateWrapper>){
        val first = list.firstOrNull()
        minX =
            if(first != null)
                first.x!![0]!!
            else
                0.0
        maxX =
            if(first != null)
                first.x!!.last()!!
        else
                0.0
        minY =
            if(first != null)
                if(first.y!![0] != null)
                    -first.y!![0]!!
        else
                    0.0
        else
                0.0
        maxY =
            if(first != null)
                if(first.y!![0] != null)
                    -first.y!![0]!!
                else
                    0.0
        else
                0.0

        list.forEach {
            it.x!!.forEach {
                if(it!! < minX)
                    minX = it
                if(it!! > maxX)
                    maxX = it
            }
            it.y!!.forEach {
                if(it != null) {
                    if (it < minY)
                        minY = it
                    if (it > maxY)
                        maxY = it
                }
            }
        }
        spanX = maxX - minX
        spanY = abs(maxY - minY)
        getScale()
        list.forEach {
            for(i in 0 until it.x!!.size){
                if(it.y!![i] != null)
                    drawPoint(it.x!![i]!!, it.y!![i]!!, it.color)
            }
        }
        drawAxis()

    }

    private fun drawAxis(){
        val canvas = holder!!.lockCanvas()
        canvas.apply{
            drawLine(getPointXScaled(minX),getPointYScaled(0.0),getPointXScaled(maxX),getPointYScaled(0.0), getLinePaint("#FFFFFF"))
            drawLine(getPointXScaled(0.0),getPointYScaled(minY),getPointXScaled(0.0),getPointYScaled(maxY), getLinePaint("#FFFFFF"))
        }
        holder!!.unlockCanvasAndPost(canvas)
    }

    private fun drawPoint(x: Double, y: Double, color: String){
        val canvas = holder!!.lockCanvas()
        canvas.apply {
            drawPoint(getPointXScaled(x), getPointYScaled(y), getPaint(color))
        }
        holder.unlockCanvasAndPost(canvas)
    }

    private fun getPaint(colorString: String) =
        Paint().apply{
            color = Color.parseColor(colorString)
            strokeWidth = 2f
            style = Paint.Style.FILL_AND_STROKE
        }

    private fun getLinePaint(colorString: String) =
        Paint().apply{
            color = Color.parseColor(colorString)
            strokeWidth = 4f
            style = Paint.Style.FILL_AND_STROKE
        }

    private fun getScale(){
        scaleX = width/(spanX*1.3)
        scaleY = height/(spanY*1.3)
    }

    private fun getPointXScaled(x : Double)  =
        ((x - minX) * scaleX).toFloat()

    private fun getPointYScaled(y : Double)  =
        (-(y - maxY)*scaleY).toFloat()
}