package com.kailin.traffic.app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.kailin.traffic.widget.DialogHelper

abstract class BaseFragment<T : BaseViewModel, B : ViewBinding> : Fragment() {

    protected lateinit var viewModel: T
    protected lateinit var viewBinding: B

    protected abstract val viewModelClass: Class<T>

    protected val dialogHelper = DialogHelper.instance

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(viewModelClass)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel.apply {
            msgText.observe(viewLifecycleOwner, {
                dialogHelper.msgDialog(requireContext(), msg = it)
            })
            loading.observe(viewLifecycleOwner, {
                if (it) {
                    progressOn()
                } else {
                    progressOff()
                }
            })
        }
        viewBinding = initBinding(inflater, container, savedInstanceState)
        initView()
        return viewBinding.root
    }

    protected abstract fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): B

    protected abstract fun initView()

    protected fun progressOn() {}

    protected fun progressOff() {}
}