package com.baiguoqing.orderhelper.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.baiguoqing.orderhelper.adapter.EditGoodsAdapter
import com.baiguoqing.orderhelper.model.GoodsModel
import com.baiguoqing.orderhelper.model.item.GoodsItemModel
import com.baiguoqing.orderhelper.util.isNotEmpty
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.text.Collator
import java.util.*
import kotlin.Comparator

class EditGoodsViewModel : ViewModel() {

    val mItems: MutableLiveData<MutableList<GoodsItemModel>> by lazy {
        MutableLiveData<MutableList<GoodsItemModel>>()
    }

    private val mItemModels = mutableListOf<GoodsItemModel>()

    private val model: GoodsModel by lazy {
        GoodsModel()
    }

    val mAdapter: EditGoodsAdapter by lazy {
        EditGoodsAdapter(mItemModels, this)
    }

    init {
        GlobalScope.launch {
            updateUI(model.initList())
        }
    }

    /**
     * 数据驱动更新UI
     */
    fun updateUI(
        itemModels: MutableList<GoodsItemModel>,
        itemModel: GoodsItemModel? = null,
        type: String? = null
    ) {
        mItems.postValue(itemModels)
        if (isNotEmpty(itemModel) && isNotEmpty(type)) {
            GlobalScope.launch {
                model.update(itemModel!!, type!!)
            }
        }
    }

    /**
     * 列表排序
     */
    fun sort(itemModels: MutableList<GoodsItemModel>) {
        val china: Comparator<GoodsItemModel> = Comparator { o1, o2 ->
            val com = Collator.getInstance(Locale.CHINA)
            com.compare(o1.itemData.name, o2.itemData.name)
        }
        if (itemModels.size > 2) {
            itemModels.subList(1, itemModels.size).sortWith(china)
        }
    }

    /**
     * notify
     */
    fun notifyDataSetChanged(itemModels: MutableList<GoodsItemModel>) {
        mItemModels.clear()
        mItemModels.addAll(itemModels)
        mAdapter.notifyDataSetChanged()
    }

}