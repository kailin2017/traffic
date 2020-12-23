package com.kailin.traffic.ui.main.home

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.widget.SearchView
import com.kailin.traffic.R
import com.kailin.traffic.app.BaseFragment
import com.kailin.traffic.data.bus.route.BusRouteData
import com.kailin.traffic.databinding.FragmentHomeBinding
import com.kailin.traffic.ui.bus.BusRouteActivity

class HomeFragment : BaseFragment<HomeViewModel, FragmentHomeBinding>(),
    SearchView.OnQueryTextListener {

    override val viewModel: HomeViewModel by viewModels()

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentHomeBinding.inflate(inflater, container, false)

    override fun initView() {
        viewDataBinding.apply {
            setToolbar(toolbar)
            recyclerView.adapter = HomeRecyclerAdapter {
                if (it.tag is BusRouteData) {
                    BusRouteActivity.startBusRouteActivity(requireContext(), it.tag as BusRouteData)
                }
            }.apply {
                viewModel.searchBusRouteData.observe(this@HomeFragment, { updateData(it) })
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        with(menu) {
            inflater.inflate(R.menu.toolbar_menu, this)
            findItem(R.id.toolbar_search).actionView.apply {
                if (this is SearchView) {
                    Log.e("onCreateOptionsMenu", "SearchView")
                    setOnQueryTextListener(this@HomeFragment)
                }
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.toolbar_search -> {
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }

    override fun onQueryTextSubmit(p0: String?): Boolean {
        p0?.let { viewModel.search(it) }
        return true
    }

    override fun onQueryTextChange(p0: String?): Boolean {
        p0?.let { viewModel.search(it) }
        return true
    }
}