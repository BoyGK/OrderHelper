package com.baiguoqing.orderhelper.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.baiguoqing.orderhelper.adapter.EditGoodsAdapter
import com.baiguoqing.orderhelper.bean.ItemData
import com.baiguoqing.orderhelper.model.ItemModel
import com.baiguoqing.orderhelper.widget.CommonItemView
import java.text.Collator
import java.util.*
import kotlin.collections.ArrayList

class EditGoodsViewModel : ViewModel() {

    val mItems: MutableLiveData<List<ItemModel>> by lazy {
        MutableLiveData<List<ItemModel>>()
    }

    private var mItemModels = ArrayList<ItemModel>()

    val mAdapter: EditGoodsAdapter by lazy {
        EditGoodsAdapter(mItemModels)
    }

    init {
        Thread(Runnable {
            mItems.postValue(
                listOf(
                    ItemModel(
                        ItemData(
                            CommonItemView.ITEM_TYPE_ADD, "", 0f, 0f, 0,
                            "", 0f, 0f, "", 0f
                        )
                    ),
                    ItemModel(
                        ItemData(
                            "commodity", "阿斯顿", 0f, 0f, 0,
                            "", 0f, 0f, "", 0f
                        )
                    ),
                    ItemModel(
                        ItemData(
                            "commodity", "请求", 0f, 0f, 0,
                            "", 0f, 0f, "", 0f
                        )
                    ),
                    ItemModel(
                        ItemData(
                            "commodity", "哈哈", 0f, 0f, 0,
                            "", 0f, 0f, "", 0f
                        )
                    ),
                    ItemModel(
                        ItemData(
                            "commodity", "啊啊ss", 0f, 0f, 0,
                            "", 0f, 0f, "", 0f
                        )
                    )
                )
            )
        }).start()
    }

    fun notifyDataSetChanged(itemModels: List<ItemModel>) {
        mItemModels.clear()
        mItemModels.addAll(itemModels)
        sort()
        mAdapter.notifyDataSetChanged()
    }

    fun notifyDataSetChangedByAdd(itemModel: ItemModel) {
        mItemModels.addAll(listOf(itemModel))
        sort()
        mAdapter.notifyDataSetChanged()
    }

    fun notifyOneItemChanged(position: Int, itemModel: ItemModel) {
        mItemModels[position] = itemModel
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