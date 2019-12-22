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
import com.baiguoqing.orderhelper.viewmodel.EditGoodsViewModel
import com.baiguoqing.orderhelper.widget.CommonDialog
import com.baiguoqing.orderhelper.widget.CommonItemView

class EditGoodsAdapter(
    private val items: MutableList<GoodsItemModel>,
    private val itemViewModel: EditGoodsViewModel
) : RecyclerView.Adapter<EditGoodsAdapter.EditGoodsAdapterHolder>() {

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

        val data: MutableList<GoodsItemModel> = itemViewModel.mItems.value!!
        val itemName = data[position].itemData.name

        val builder = CommonDialog.Builder(view.context)
        if (itemName != "") {
            builder.setEditWithHint(view.context.getString(R.string.goodsName), itemName)
        } else {
            builder.setEditText(view.context.getString(R.string.goodsName), "name")
        }
        builder.setEditText(view.context.getString(R.string.priceIn), "priceIn")
            .setEditText(view.context.getString(R.string.priceOut), "priceOut")
            .setButtonView(object : CommonDialog.PositiveWithEditData {
                override fun positive(view: View, mutableMap: MutableMap<Any, String>): Boolean {
                    val item = GoodsItemModel(
                        Goods(
                            CommonItemView.ITEM_TYPE_COMMODITY,
                            mutableMap["name"] ?: itemName,
                            String.format(
                                "%.2f",
                                (mutableMap["priceIn"] ?: "0").toFloat()
                            ).toFloat(),
                            String.format(
                                "%.2f",
                                (mutableMap["priceOut"] ?: "0").toFloat()
                            ).toFloat(),
                            0
                        )
                    )
                    if (position == 0) {
                        data.add(item)
                        itemViewModel.updateUI(data, item, "insert")
                    } else {
                        data[position] = item
                        itemViewModel.updateUI(data, item, "update")
                    }
                    return true
                }

            })
            .show()
    }

    private fun longClickItem(view: View, position: Int): Boolean {
        log("longClickItem:$view")
        if (position == 0) {
            return true
        }
        val data: MutableList<GoodsItemModel> = itemViewModel.mItems.value!!
        CommonDialog.Builder(view.context)
            .setTittle("确定删除该条目？")
            .setButtonView(object : CommonDialog.Positive {
                override fun positive(view: View): Boolean {
                    val item = data[position]
                    data.removeAt(position)
                    itemViewModel.updateUI(data, item, "delete")
                    return true
                }
            })
            .show()
        return true
    }

    class EditGoodsAdapterHolder(val viewDataBinding: ViewDataBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root)
}