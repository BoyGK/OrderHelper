package com.baiguoqing.orderhelper.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.baiguoqing.orderhelper.BR
import com.baiguoqing.orderhelper.R
import com.baiguoqing.orderhelper.bean.entity.Goods
import com.baiguoqing.orderhelper.model.item.GoodsItemModel
import com.baiguoqing.orderhelper.util.log
import com.baiguoqing.orderhelper.viewmodel.NewOrderViewModel
import com.baiguoqing.orderhelper.widget.CommonDialog
import com.baiguoqing.orderhelper.widget.CommonItemView

/**
 * Created by "nullpointexception0" on 2019/12/22.
 */
class NewOrderAdapter(
    private val items: MutableList<GoodsItemModel>,
    private val itemViewModel: NewOrderViewModel
) : RecyclerView.Adapter<NewOrderAdapter.NewOrderAdapterHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewOrderAdapterHolder {
        val viewDataBinding = DataBindingUtil.inflate<ViewDataBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_activity_neworder,
            parent,
            false
        )
        return NewOrderAdapterHolder(viewDataBinding)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: NewOrderAdapterHolder, position: Int) {
        holder.viewDataBinding.setVariable(BR.data, items[position])
        holder.viewDataBinding.executePendingBindings()
        holder.viewDataBinding.root.setOnClickListener {
            clickItem(it, position)
        }
    }

    private fun clickItem(view: View, position: Int) {
        log("clickItem $view")
        val data: MutableList<GoodsItemModel> = itemViewModel.mItems.value!!
        CommonDialog.Builder(view.context)
            .setEditText("数量：", "num")
            .setButtonView(object : CommonDialog.PositiveWithEditData {
                override fun positive(view: View, mutableMap: MutableMap<Any, String>): Boolean {
                    val item = GoodsItemModel(
                        Goods(
                            CommonItemView.ITEM_TYPE_COMMODITY,
                            data[position].itemData.name,
                            data[position].itemData.priceIn,
                            data[position].itemData.priceOut,
                            (mutableMap["num"] ?: "0").toInt()
                        )
                    )
                    data[position] = item
                    itemViewModel.updateUI(data)
                    return true
                }

            })
            .show()
    }

    class NewOrderAdapterHolder(val viewDataBinding: ViewDataBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root)
}