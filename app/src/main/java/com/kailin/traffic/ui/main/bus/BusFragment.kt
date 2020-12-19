package com.kailin.traffic.ui.main.bus

import com.kailin.traffic.R
import com.kailin.traffic.app.BaseFragment
import com.kailin.traffic.databinding.FragmentBusBinding

class BusFragment : BaseFragment<BusViewModel, FragmentBusBinding>() {

    override val viewModelClass = BusViewModel::class.java

    override val viewLayoutRes = R.layout.fragment_bus

    override fun initView() {
    }
}