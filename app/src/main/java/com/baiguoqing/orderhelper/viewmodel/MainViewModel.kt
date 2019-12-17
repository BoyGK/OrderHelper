package com.baiguoqing.orderhelper.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.baiguoqing.orderhelper.adapter.MainAdapter
import com.baiguoqing.orderhelper.bean.ItemData
import com.baiguoqing.orderhelper.model.ItemModel
import java.util.*
import kotlin.collections.ArrayList

/**
 * Created by "nullpointexception0" on 2019/12/15.
 */
class MainViewModel : ViewModel() {

    val mItems: MutableLiveData<List<ItemModel>> by lazy {
        MutableLiveData<List<ItemModel>>()
    }

    private var mItemModels = ArrayList<ItemModel>()

    val mAdapter: MainAdapter by lazy {
        MainAdapter(mItemModels)
    }

    init {
        Thread(Runnable {
            Thread.sleep(1000)
            mItems.postValue(
                listOf(
                    ItemModel(
                        ItemData(
                            "commodity", "ABC", 1.23f, 3.21f, 999,
                            "", 0f, 0f, "", 0f
                        )
                    ),
                    ItemModel(
                        ItemData(
                            "date", "", 0f, 0f, 0,
                            Date().time.toString(), 12345f, 1666f, "", 0f
                        )
                    ),
                    ItemModel(
                        ItemData(
                            "client", "", 0f, 0f, 0,
                            "", 0f, 0f, "BGQ", 6666f
                        )
                    ),
                    ItemModel(
                        ItemData(
                            "add", "ABC", 0f, 0f, 0,
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
        mItemModels.sortBy { it.itemData.name }
        mAdapter.notifyDataSetChanged()
    }

    fun notifyDataSetChangedByAdd(itemModel: ItemModel) {
        mItemModels.addAll(listOf(itemModel))
        mItemModels.sortBy { it.itemData.name }
        mAdapter.notifyDataSetChanged()
    }

    fun notifyOneItemChanged(position: Int, itemModel: ItemModel) {
        mItemModels[position] = itemModel
        mItemModels.sortBy { it.itemData.name }
        mAdapter.notifyDataSetChanged()
    }

}