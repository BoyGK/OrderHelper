package com.baiguoqing.orderhelper.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.baiguoqing.orderhelper.BR
import com.baiguoqing.orderhelper.R
import com.baiguoqing.orderhelper.model.item.GoodsItemModel
import com.baiguoqing.orderhelper.util.log
import com.baiguoqing.orderhelper.viewmodel.EditGoodsViewModel
import com.baiguoqing.orderhelper.widget.CommonDialog

class EditGoodsAdapter(
    private val items: MutableList<GoodsItemModel>,
    private val itemViewModel: EditGoodsViewModel
) : RecyclerView.Adapter<EditGoodsAdapter.EditGoodsAdapterHolder>() {

    lateinit var commonDialog: CommonDialog

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
        holder.viewDataBinding.root.setOnClickListener {
            clickItem(it, position)
        }
        holder.viewDataBinding.root.setOnLongClickListener {
            longClickItem(it, position)
        }
    }

    private fun clickItem(view: View, position: Int) {
        log("clickItem:$view")
        /*
        val data: MutableList<GoodsItemModel> = itemViewModel.mItems.value!!
        commonDialog = CommonDialog.Builder(view.context)
            .setGoodsName(data[position].itemData.name)
            .setPriceIn(data[position].itemData.priceIn)
            .setPriceOut(data[position].itemData.priceOut)
            .setPositive(object : CommonDialog.Positive {
                override fun positive(view: View) {
                    val item = GoodsItemModel(
                        Goods(
                            CommonItemView.ITEM_TYPE_COMMODITY,
                            commonDialog.mGoodsName,
                            String.format("%.2f", commonDialog.mGoodsPriceIn).toFloat(),
                            String.format("%.2f", commonDialog.mGoodsPriceOut).toFloat(),
                            0
                        )
                    )
                    val type: String
                    if (position == 0) {
                        data.add(item)
                        type = "insert"
                    } else {
                        data[position] = item
                        type = "update"
                    }
                    itemViewModel.updateUI(data, item, type)
                }
            })
            .show()*/
    }

    private fun longClickItem(view: View, position: Int): Boolean {
        log("longClickItem:$view")
        val data: MutableList<GoodsItemModel> = itemViewModel.mItems.value!!
        CommonDialog.Builder(view.context)
            .setTittle("确定删除该条目？")
            .setButtonView(object : CommonDialog.Positive {
                override fun positive(view: View) {
                    val type = "delete"
                    val item = data[position]
                    data.removeAt(position)
                    itemViewModel.updateUI(data, item, type)
                }
            })
            .show()
        return true
    }

    class EditGoodsAdapterHolder(val viewDataBinding: ViewDataBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root)
}