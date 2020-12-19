package com.kailin.traffic.app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import com.kailin.traffic.widget.DialogHelper

abstract class BaseActivity<M : BaseViewModel, V : ViewDataBinding> : AppCompatActivity() {

    protected lateinit var viewModel: M
    protected lateinit var viewDataBinding: V

    protected abstract val viewModelClass: Class<M>
    protected abstract val viewLayoutRes: Int

    protected val dialogHelper = DialogHelper.instance

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewDataBinding = DataBindingUtil.setContentView(this, viewLayoutRes)
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

    protected abstract fun initView()

    protected fun progressOn() {}

    protected fun progressOff() {}
}