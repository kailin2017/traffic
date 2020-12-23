package com.kailin.traffic.ui.bus.info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.kailin.traffic.app.BaseFragment
import com.kailin.traffic.databinding.FragmentBusInfoBinding
import com.kailin.traffic.ui.bus.BusRouteViewModel

class InfoFragment() : BaseFragment<BusRouteViewModel, FragmentBusInfoBinding>() {

    override val viewModel: BusRouteViewModel by viewModels()

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentBusInfoBinding.inflate(inflater, container, false)

    override fun initView() {
        TODO("Not yet implemented")
    }
}