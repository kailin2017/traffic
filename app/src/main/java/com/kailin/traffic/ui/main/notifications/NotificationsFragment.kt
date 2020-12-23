package com.kailin.traffic.ui.main.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.kailin.traffic.R
import com.kailin.traffic.app.BaseFragment
import com.kailin.traffic.databinding.FragmentNotificationsBinding

class NotificationsFragment : BaseFragment<NotificationsViewModel, FragmentNotificationsBinding>() {

    override val viewModel: NotificationsViewModel by viewModels()

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentNotificationsBinding.inflate(inflater, container, false)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_notifications, container, false)
        val textView: TextView = root.findViewById(R.id.text_notifications)
        viewModel.text.observe(viewLifecycleOwner, { textView.text = it })
        return root
    }

    override fun initView() {

    }
}