package com.kailin.traffic.app

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.kailin.traffic.widget.recyclerView.MyRecyclerDiffCallBack

abstract class MyRecyclerAdapter<V : ViewDataBinding, D> : RecyclerView.Adapter<ViewHolder<V>>() {

    private lateinit var viewDataBinding: V
    protected abstract val viewLayoutRes: Int
    protected val data: MutableList<D> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder<V> {
        createBinding(parent, viewType)
        return ViewHolder(viewDataBinding)
    }

    private fun createBinding(parent: ViewGroup, viewType: Int) {
        viewDataBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), viewLayoutRes, parent, false
        )
    }

    override fun getItemCount() = data.size

    fun updateData(newData: MutableList<D>) {
        calculateDiff(MyRecyclerDiffCallBack(data, newData), newData)
    }

    fun calculateDiff(callBack: MyRecyclerDiffCallBack<D>, newData: MutableList<D>) {
        data.apply {
            val result = DiffUtil.calculateDiff(callBack)
            clear()
            addAll(newData)
            result.dispatchUpdatesTo(this@MyRecyclerAdapter)
        }
    }
}

class ViewHolder<V : ViewDataBinding>(val binding: V) : RecyclerView.ViewHolder(binding.root)