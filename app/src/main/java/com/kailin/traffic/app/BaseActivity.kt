package com.kailin.traffic.app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.kailin.traffic.widget.DialogHelper

abstract class BaseActivity<T : BaseViewModel, B : ViewBinding> : AppCompatActivity() {

    protected lateinit var viewModel: T
    protected lateinit var viewBinding: B

    protected abstract val viewModelClass: Class<T>

    protected val dialogHelper = DialogHelper.instance

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = initBinding(layoutInflater).apply { setContentView(root) }
        viewModel = ViewModelProvider(this).get(viewModelClass).apply {
            msgText.observe(this@BaseActivity, {
                dialogHelper.msgDialog(this@BaseActivity, msg = it)
            })
            loading.observe(this@BaseActivity, {
                if (it) {
                    progressOn()
                } else {
                    progressOff()
                }
            })
        }

        initView()
    }

    protected abstract fun initBinding(inflater: LayoutInflater): B

    protected abstract fun initView()

    protected fun progressOn() {}

    protected fun progressOff() {}
}