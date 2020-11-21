package com.kailin.traffic.ui.main.bus

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.kailin.traffic.R
import com.kailin.traffic.app.BaseFragment
import com.kailin.traffic.databinding.FragmentBusBinding

class BusFragment : BaseFragment<BusViewModel, FragmentBusBinding>() {

    override val viewModelClass = BusViewModel::class.java

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentBusBinding = FragmentBusBinding.inflate(inflater, container, false)

    override fun initView() {
        viewModel.initData()
    }
}