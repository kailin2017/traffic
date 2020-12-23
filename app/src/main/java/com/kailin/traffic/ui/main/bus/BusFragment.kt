package com.kailin.traffic.ui.main.bus

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.kailin.traffic.app.BaseFragment
import com.kailin.traffic.databinding.FragmentBusBinding

class BusFragment : BaseFragment<BusViewModel, FragmentBusBinding>() {

    override val viewModel: BusViewModel by viewModels()

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentBusBinding.inflate(inflater, container, false)

    override fun initView() {
    }
}