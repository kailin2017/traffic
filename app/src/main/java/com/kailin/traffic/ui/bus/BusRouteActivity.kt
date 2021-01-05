package com.kailin.traffic.ui.bus

import android.content.Context
import android.content.Intent
import androidx.lifecycle.coroutineScope
import com.google.android.libraries.maps.CameraUpdateFactory
import com.google.android.libraries.maps.SupportMapFragment
import com.google.android.libraries.maps.model.LatLng
import com.google.maps.android.ktx.awaitMap
import com.kailin.traffic.R
import com.kailin.traffic.app.BaseActivity
import com.kailin.traffic.data.bus.route.BusRouteData
import com.kailin.traffic.databinding.ActivityBusRouteBinding

class BusRouteActivity : BaseActivity<BusRouteViewModel, ActivityBusRouteBinding>() {

    override val viewModel: BusRouteViewModel by viewModels()

//    private lateinit var bottomBehavior: BottomSheetBehavior<View>

    override fun initBinding() = ActivityBusRouteBinding.inflate(layoutInflater)

    override fun initView() {
        BusRouteData.fromBundle(intent.extras)?.let {
            viewModel.busRouteData.postValue(it)
            viewModel.initData(it.City, it.RouteName.Zh_tw, it.RouteUID)
        }
        supportFragmentManager.findFragmentById(R.id.mapFragment)?.let {
            if (it is SupportMapFragment)
                lifecycle.coroutineScope.launchWhenCreated {
                    val googleMap = it.awaitMap()
                    googleMap.moveCamera(
                        CameraUpdateFactory.newLatLngZoom(LatLng(51.403186, -0.126446), 10F)
                    )
                }
        }
//        bottomBehavior = BottomSheetBehavior.from(viewDataBinding.bottomSheet)
//        setBottomViewVisible(bottomBehavior.state != BottomSheetBehavior.STATE_EXPANDED)
    }
//    private fun setBottomViewVisible(showFlag: Boolean) {
//        bottomBehavior.state = if (showFlag) {
//            BottomSheetBehavior.STATE_EXPANDED
//        } else {
//            BottomSheetBehavior.STATE_COLLAPSED
//        }
//    }

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