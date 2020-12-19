package com.kailin.traffic.app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.kailin.traffic.widget.DialogHelper

abstract class BaseFragment<M : BaseViewModel, V : ViewDataBinding> : Fragment() {

    protected lateinit var viewModel: M
    protected lateinit var viewDataBinding: V

    protected abstract val viewModelClass: Class<M>
    protected abstract val viewLayoutRes: Int

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
        viewDataBinding = DataBindingUtil.inflate(inflater, viewLayoutRes, container, false)
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
        return viewDataBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initView()
    }

    protected abstract fun initView()

    protected fun progressOn() {}

    protected fun progressOff() {}

    protected fun setToolbar(toolbar: Toolbar) {
        requireActivity().apply {
            if (this is AppCompatActivity) {
                setSupportActionBar(toolbar)
                setHasOptionsMenu(true)
            }
        }
    }
}