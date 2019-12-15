package com.baiguoqing.orderhelper.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.baiguoqing.orderhelper.BR
import com.baiguoqing.orderhelper.R
import com.baiguoqing.orderhelper.model.ItemModel

/**
 * Created by "nullpointexception0" on 2019/12/15.
 */
class MainAdapter(private val items: List<ItemModel>) :
    RecyclerView.Adapter<MainAdapter.MainAdapterHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainAdapterHolder {
        val viewDataBinding = DataBindingUtil.inflate<ViewDataBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_activity_main,
            parent,
            false
        )
        return MainAdapterHolder(viewDataBinding)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: MainAdapterHolder, position: Int) {
        holder.viewDataBinding.setVariable(BR.data, items[position])
        holder.viewDataBinding.executePendingBindings()
    }

    class MainAdapterHolder(val viewDataBinding: ViewDataBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root)
}