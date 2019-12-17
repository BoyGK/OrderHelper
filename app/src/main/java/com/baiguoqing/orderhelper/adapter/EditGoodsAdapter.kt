package com.baiguoqing.orderhelper.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.baiguoqing.orderhelper.BR
import com.baiguoqing.orderhelper.R
import com.baiguoqing.orderhelper.model.ItemModel

class EditGoodsAdapter(private val items: List<ItemModel>) :
    RecyclerView.Adapter<EditGoodsAdapter.EditGoodsAdapterHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EditGoodsAdapterHolder {
        val viewDataBinding = DataBindingUtil.inflate<ViewDataBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_activity_editgoods,
            parent,
            false
        )
        return EditGoodsAdapterHolder(viewDataBinding)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: EditGoodsAdapterHolder, position: Int) {
        holder.viewDataBinding.setVariable(BR.data, items[position])
        holder.viewDataBinding.executePendingBindings()
    }

    class EditGoodsAdapterHolder(val viewDataBinding: ViewDataBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root)
}