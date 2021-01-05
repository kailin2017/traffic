package com.kailin.traffic.ui.bus.map

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.kailin.traffic.app.BaseFragment
import com.kailin.traffic.databinding.FragmentBusMapBinding
import com.kailin.traffic.ui.bus.BusRouteViewModel

class MapFragment() : BaseFragment<BusRouteViewModel, FragmentBusMapBinding>() {

    override val viewModel: BusRouteViewModel by viewModels()

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentBusMapBinding.inflate(inflater, container, false)

    override fun initView() {
    }
}