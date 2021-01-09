package com.kailin.traffic.app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.annotation.MainThread
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.createViewModelLazy
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.navigation.fragment.findNavController
import com.kailin.traffic.R
import com.kailin.traffic.widget.DialogHelper

abstract class BaseFragment<M : BaseViewModel, V : ViewDataBinding> : Fragment() {


    protected lateinit var viewDataBinding: V
    protected abstract val viewModel: M

    protected val dialogHelper = DialogHelper.instance

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = initBinding(inflater, container, savedInstanceState)
        return viewDataBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        with(viewModel) {
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
        initView()
    }

    protected abstract fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): V

    protected abstract fun initView()

    protected open fun progressOn() {}

    protected open fun progressOff() {}

    protected fun setToolbar(toolbar: Toolbar, title: String? = null, isShowBack: Boolean = false) {
        requireActivity().apply {
            if (this is AppCompatActivity) {
                setSupportActionBar(toolbar)
                setHasOptionsMenu(true)
                if (isShowBack) {
                    toolbar.title = title
                    toolbar.setNavigationIcon(R.drawable.ic_arrow_back_24dp)
                    toolbar.setNavigationOnClickListener { onBackPressed() }
                }
            }
        }
    }

    @MainThread
    inline fun <reified VM : ViewModel> Fragment.viewModels(
        noinline ownerProducer: () -> ViewModelStoreOwner = { this },
        noinline factoryProducer: (() -> ViewModelProvider.Factory)? = null
    ) = createViewModelLazy(VM::class, { ownerProducer().viewModelStore }, factoryProducer)

    @MainThread
    inline fun <reified VM : ViewModel> Fragment.activityViewModels(
        noinline factoryProducer: (() -> ViewModelProvider.Factory)? = null
    ) = createViewModelLazy(VM::class, { requireActivity().viewModelStore },
        factoryProducer ?: { requireActivity().defaultViewModelProviderFactory })
}