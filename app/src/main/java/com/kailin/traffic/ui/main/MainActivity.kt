package com.kailin.traffic.ui.main

import android.view.LayoutInflater
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.kailin.traffic.R
import com.kailin.traffic.app.BaseActivity
import com.kailin.traffic.databinding.ActivityMainBinding

class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>() {

    override val viewModelClass: Class<MainViewModel> = MainViewModel::class.java

    override fun initBinding(inflater: LayoutInflater): ActivityMainBinding =
        ActivityMainBinding.inflate(inflater)

    override fun initView() {
        findNavController(R.id.nav_host_fragment).apply {
            viewBinding.navView.setupWithNavController(this)
        }
        viewModel.initData()
    }
}