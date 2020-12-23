package com.kailin.traffic.ui.bus

import android.content.Context
import android.content.Intent
import com.kailin.traffic.app.BaseActivity
import com.kailin.traffic.data.bus.route.BusRouteData
import com.kailin.traffic.databinding.ActivityBusRouteBinding

class BusRouteActivity() : BaseActivity<BusRouteViewModel, ActivityBusRouteBinding>() {

    override val viewModel: BusRouteViewModel by viewModels()

//    private lateinit var bottomBehavior: BottomSheetBehavior<View>

    override fun initBinding() = ActivityBusRouteBinding.inflate(layoutInflater)

    override fun initView() {
        BusRouteData.fromBundle(intent.extras)?.let {
            viewModel.busRouteData.postValue(it)
            viewModel.initData(it.City, it.RouteName.Zh_tw, it.RouteUID)
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