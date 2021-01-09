package com.kailin.traffic.ui.bus

import android.content.Context
import android.content.Intent
import androidx.lifecycle.coroutineScope
import com.google.android.libraries.maps.SupportMapFragment
import com.google.maps.android.ktx.awaitMap
import com.kailin.traffic.R
import com.kailin.traffic.app.BaseActivity
import com.kailin.traffic.data.bus.route.BusRouteData
import com.kailin.traffic.databinding.ActivityBusRouteBinding
import com.kailin.traffic.util.MapHelper

class BusRouteActivity : BaseActivity<BusRouteViewModel, ActivityBusRouteBinding>() {

    private lateinit var mapHelper: MapHelper
    private lateinit var mapFragment: SupportMapFragment
    override val viewModel: BusRouteViewModel by viewModels()

//    private lateinit var bottomBehavior: BottomSheetBehavior<View>

    override fun initBinding() = ActivityBusRouteBinding.inflate(layoutInflater)

    override fun initView() {
        BusRouteData.fromBundle(intent.extras)?.let {
            viewModel.busRouteData.postValue(it)
            viewModel.initData(it.City, it.RouteName.Zh_tw, it.RouteUID)
        }

        mapFragment =
            supportFragmentManager.findFragmentById(R.id.mapFragment) as SupportMapFragment

        lifecycle.coroutineScope.launchWhenCreated {
            mapHelper = MapHelper(this@BusRouteActivity, mapFragment.awaitMap())
            mapHelper.moveCameraByLocation(viewModel::onError)
        }

        viewModel.selectedPage.observe(this) { p ->
            viewModel.busStopOfRoute.value?.let { mapHelper.drawBusStopOfRoute(it[p]) }
        }
    }

    companion object {

        fun startBusRouteActivity(context: Context, data: BusRouteData) {
            context.startActivity(
                Intent(context, BusRouteActivity::class.java).apply {
                    putExtras(data.toBundle())
                }
            )
        }
    }
}