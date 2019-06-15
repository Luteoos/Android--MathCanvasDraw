package io.github.luteoos.mathcanvasdraw.view.activity

import android.os.Bundle
import android.os.Handler
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.view.Gravity
import android.view.MenuItem
import android.view.View
import com.luteoos.kotlin.mvvmbaselib.BaseActivityMVVM
import es.dmoral.toasty.Toasty
import io.github.luteoos.mathcanvasdraw.R
import io.github.luteoos.mathcanvasdraw.session.Session
import io.github.luteoos.mathcanvasdraw.viewmodels.MainScreenViewModel
import kotlinx.android.synthetic.main.activity_main_screen.*
import kotlinx.android.synthetic.main.header_main_screen.view.*
import org.jetbrains.anko.sdk27.coroutines.onClick

class MainScreen: BaseActivityMVVM<MainScreenViewModel>(), NavigationView.OnNavigationItemSelectedListener {
    private var doublePressToExit = false

    private lateinit var header: View
    override fun getLayoutID(): Int = R.layout.activity_main_screen
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = getViewModel(this)
        connectToVMMessage()
        getHeader()
        setBindings()
    }

    private fun setBindings(){
        nav_view.setNavigationItemSelectedListener(this)
//      TODO Uncomment this b4 release
//       if(Session.userUUDString == "")
//            nav_view.menu.findItem(R.id.menuLogout).isVisible = false
        header.button2.onClick {
            Toasty.error(this@MainScreen, "NIE").show()
        }
    }

    override fun onNavigationItemSelected(p0: MenuItem): Boolean {
        when(p0.itemId){
            R.id.menuLogout -> Session.logout(this)
        }
        drawer_layout.closeDrawer(Gravity.START)
        return true
    }

    private fun getHeader(){
        drawer_layout.openDrawer(Gravity.START)
        header = nav_view.getHeaderView(0)
    }

    override fun onBackPressed() {
        if (doublePressToExit) {
            super.onBackPressed()
        }
        val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            drawer.openDrawer(GravityCompat.START)
        }
        doublePressToExit = true
        Toasty.info(this, "Press again to exit!").show()

        Handler().postDelayed({ doublePressToExit = false }, 2000)
    }
}