package com.baiguoqing.orderhelper.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.baiguoqing.orderhelper.BR
import com.baiguoqing.orderhelper.R
import com.baiguoqing.orderhelper.app.App
import com.baiguoqing.orderhelper.bean.ItemData
import com.baiguoqing.orderhelper.bean.entity.Goods
import com.baiguoqing.orderhelper.model.ItemModel
import com.baiguoqing.orderhelper.util.log
import com.baiguoqing.orderhelper.viewmodel.EditGoodsViewModel
import com.baiguoqing.orderhelper.widget.CommonDialog
import com.baiguoqing.orderhelper.widget.CommonItemView

class EditGoodsAdapter(
    private val items: MutableList<ItemModel>,
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
    }

    private fun clickItem(view: View, position: Int) {
        log("clickItem:$view")
        val data: MutableList<ItemModel> = itemViewModel.mItems.value!!
        commonDialog = CommonDialog.Builder(view.context)
            .setGoodsName(data[position].itemData.name)
            .setPriceIn(data[position].itemData.priceIn)
            .setPriceOut(data[position].itemData.priceOut)
            .setPositive(object : CommonDialog.Positive {
                override fun positive(view: View) {
                    val item = ItemModel(
                        ItemData(
                            CommonItemView.ITEM_TYPE_COMMODITY,
                            commonDialog.mGoodsName,
                            String.format("%.2f", commonDialog.mGoodsPriceIn).toFloat(),
                            String.format("%.2f", commonDialog.mGoodsPriceOut).toFloat(),
                            0,
                            "",
                            0f,
                            0f,
                            "",
                            0f
                        )
                    )
                    val goods = Goods(
                        data.size,
                        item.itemData.name,
                        item.itemData.priceIn,
                        item.itemData.priceOut,
                        item.itemData.num
                    )
                    if (position == 0) {
                        data.add(item)
                        Thread {
                            App.instance.dbManager.goodsDB.insert(goods)
                        }.start()
                    } else {
                        data[position] = item
                        Thread {
                            App.instance.dbManager.goodsDB.update(goods)
                        }.start()
                    }
                    itemViewModel.mItems.postValue(data)
                }
            })
            .show()
    }

    class EditGoodsAdapterHolder(val viewDataBinding: ViewDataBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root)
}