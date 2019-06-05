package io.github.luteoos.mathcanvasdraw.view.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import io.github.luteoos.mathcanvasdraw.view.fragment.FunctionsInputFragment
import io.github.luteoos.mathcanvasdraw.view.fragment.NumberInputFragment

class InputPagerAdapter(manager: FragmentManager): FragmentPagerAdapter(manager) {
    private val numbersFragment = NumberInputFragment()
    private  val functionsFragment = FunctionsInputFragment()
    override fun getItem(p0: Int): Fragment =
        when(p0){
            0 -> numbersFragment
            1 -> functionsFragment
            else -> numbersFragment
        }

    override fun getCount(): Int = 2
}