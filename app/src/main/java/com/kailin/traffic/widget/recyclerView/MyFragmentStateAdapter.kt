package com.kailin.traffic.widget.recyclerView

import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.viewpager2.adapter.FragmentStateAdapter

abstract class MyFragmentStateAdapter<D>(activity: FragmentActivity): FragmentStateAdapter(activity) {

    protected val data: MutableList<D> = mutableListOf()

    override fun getItemCount() = data.size

    fun updateData(newData: MutableList<D>) {
        calculateDiff(MyRecyclerDiffCallBack(data, newData), newData)
    }

    fun calculateDiff(callBack: MyRecyclerDiffCallBack<D>, newData: MutableList<D>) {
        data.apply {
            val result = DiffUtil.calculateDiff(callBack)
            clear()
            addAll(newData)
            result.dispatchUpdatesTo(this@MyFragmentStateAdapter)
        }
    }
}