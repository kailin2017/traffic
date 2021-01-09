package com.kailin.traffic.ui.main

import android.Manifest
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.kailin.traffic.R
import com.kailin.traffic.app.BaseActivity
import com.kailin.traffic.databinding.ActivityMainBinding
import com.kailin.traffic.util.permission.PermissionHelper

class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>() {

    override val viewModel: MainViewModel by viewModels()

    override fun initBinding() = ActivityMainBinding.inflate(layoutInflater)

    override fun initView() {
        with(findNavController(R.id.navFragment)) {
            viewDataBinding.navView.setupWithNavController(this)
        }
        viewModel.initData()
        PermissionHelper.instance.checkPermission(
            activity = this,
            permissions = arrayOf(
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION
            ),
            deniedCallback = { finish() }
        )
    }
}