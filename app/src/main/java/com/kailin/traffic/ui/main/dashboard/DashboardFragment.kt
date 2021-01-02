package com.kailin.traffic.ui.main.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.kailin.traffic.app.BaseFragment
import com.kailin.traffic.databinding.FragmentDashboardBinding

class DashboardFragment : BaseFragment<DashboardViewModel, FragmentDashboardBinding>() {

    override val viewModel: DashboardViewModel by viewModels()

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentDashboardBinding.inflate(inflater, container, false)

    override fun initView() {
    }
}