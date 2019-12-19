package com.baiguoqing.orderhelper.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.baiguoqing.orderhelper.adapter.EditGoodsAdapter
import com.baiguoqing.orderhelper.app.App
import com.baiguoqing.orderhelper.bean.ItemData
import com.baiguoqing.orderhelper.model.ItemModel
import com.baiguoqing.orderhelper.widget.CommonItemView
import java.text.Collator
import java.util.*
import kotlin.Comparator

class EditGoodsViewModel : ViewModel() {

    val mItems: MutableLiveData<MutableList<ItemModel>> by lazy {
        MutableLiveData<MutableList<ItemModel>>()
    }

    private var mItemModels = mutableListOf<ItemModel>()

    val mAdapter: EditGoodsAdapter by lazy {
        EditGoodsAdapter(mItemModels, this)
    }

    init {
        val arr = mutableListOf(
            ItemModel(
                ItemData(
                    CommonItemView.ITEM_TYPE_ADD, "", 0f, 0f, 0,
                    "", 0f, 0f, "", 0f
                )
            )
        )
        arr.addAll(App.instance.dbManager.goodsDB.loadAll())
        mItems.postValue(arr)
    }

    fun notifyDataSetChanged(itemModels: MutableList<ItemModel>) {
        mItemModels.clear()
        mItemModels.addAll(itemModels)
        sort()
        mAdapter.notifyDataSetChanged()
    }

    private fun sort() {
        val china: Comparator<ItemModel> = Comparator { o1, o2 ->
            val com = Collator.getInstance(Locale.CHINA)
            com.compare(o1.itemData.name, o2.itemData.name)
        }
        mItemModels.subList(1, mItemModels.size).sortWith(china)
    }

}