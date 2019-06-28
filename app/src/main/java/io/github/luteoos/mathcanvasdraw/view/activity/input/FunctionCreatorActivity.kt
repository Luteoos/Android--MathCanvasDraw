package io.github.luteoos.mathcanvasdraw.view.activity.input

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.luteoos.kotlin.mvvmbaselib.BaseActivityMVVM
import es.dmoral.toasty.Toasty
import io.github.luteoos.mathcanvasdraw.R
import io.github.luteoos.mathcanvasdraw.network.request.FunctionRequest
import io.github.luteoos.mathcanvasdraw.network.response.FunctionResponse
import io.github.luteoos.mathcanvasdraw.utils.Parameters
import io.github.luteoos.mathcanvasdraw.viewmodels.SplashScreenViewModel
import kotlinx.android.synthetic.main.activity_function_creator.*

class FunctionCreatorActivity : BaseActivityMVVM<SplashScreenViewModel>() {

    private val REQUEST_MATHKEYBOARD_INPUT = 1410

    override fun getLayoutID(): Int = R.layout.activity_function_creator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = getViewModel(this)
        setBindings()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode){
            REQUEST_MATHKEYBOARD_INPUT ->{
                if(resultCode == Activity.RESULT_OK)
                    tvFunctionBody.text = data?.dataString
            }
        }
    }

    private fun setBindings(){
        fabConfirm.setOnClickListener {
         returnFunctionResponse()
        }
        btnAddFunctionBody.setOnClickListener {
            startMathKeyboardInput()
        }
    }

    private fun returnFunctionResponse(){
        if(etApprox.text.isNullOrEmpty() ||
                etMax.text.isNullOrEmpty() ||
                etMin.text.isNullOrEmpty() ||
                etName.text.isNullOrEmpty() ||
                tvFunctionBody.text.isNullOrEmpty()){
            Toasty.error(this, "All fields must be filled").show()
            return
        }
        val result = FunctionRequest().apply {
            functionString = tvFunctionBody.text.toString()
            name = etName.text.toString()
            max = etMax.text.toString().toDouble()
            min = etMin.text.toString().toDouble()
            approximation = etApprox.text.toString().split(".")[0].toInt()

        }
        if(result.min!! >= result.max!!){
            Toasty.error(this, "Minimal value can't be bigger than maximal").show()
            return
        }
        val data = Intent().putExtra(Parameters.GET_FUNCTION_AS_EXTRAS, result)
        setResult(Activity.RESULT_OK, data)
        finish()
    }

    private fun startMathKeyboardInput(){
        val intent = Intent(this, OnScreenMathKeyboard::class.java)
        startActivityForResult(intent, REQUEST_MATHKEYBOARD_INPUT)
    }
}