package com.kailin.traffic.ui.main

import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.kailin.traffic.R
import com.kailin.traffic.app.BaseActivity
import com.kailin.traffic.databinding.ActivityMainBinding

class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>() {

    override val viewModelClass: Class<MainViewModel> = MainViewModel::class.java

    override val viewLayoutRes = R.layout.activity_main

    override fun initView() {
        with(findNavController(R.id.nav_host_fragment)) {
            viewDataBinding.navView.setupWithNavController(this)
        }
        viewModel.initData()
    }
}