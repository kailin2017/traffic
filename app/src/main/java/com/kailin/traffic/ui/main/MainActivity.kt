package com.kailin.traffic.ui.main

import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.kailin.traffic.R
import com.kailin.traffic.app.BaseActivity
import com.kailin.traffic.databinding.ActivityMainBinding

class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>() {

    override val viewModel: MainViewModel by viewModels()

    override fun initBinding() = ActivityMainBinding.inflate(layoutInflater)

    override fun initView() {
        with(findNavController(R.id.navFragment)) {
            viewDataBinding.navView.setupWithNavController(this)
        }
        viewModel.initData()
    }
}