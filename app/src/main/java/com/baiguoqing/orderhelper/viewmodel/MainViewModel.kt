package com.baiguoqing.orderhelper.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.baiguoqing.orderhelper.adapter.MainAdapter
import com.baiguoqing.orderhelper.model.MainModel
import com.baiguoqing.orderhelper.model.item.MainItemModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * Created by "nullpointexception0" on 2019/12/15.
 */
class MainViewModel : ViewModel() {

    val mItems: MutableLiveData<MutableList<MainItemModel>> by lazy {
        MutableLiveData<MutableList<MainItemModel>>()
    }

    private var mItemModels = mutableListOf<MainItemModel>()

    private val model: MainModel by lazy {
        MainModel()
    }

    val mAdapter: MainAdapter by lazy {
        MainAdapter(mItemModels)
    }

    init {
        reloadList()
    }

    /**
     * 加载主页列表
     */
    fun reloadList() {
        GlobalScope.launch {
            mItems.postValue(model.initList())
        }
    }

    fun notifyDataSetChanged(itemModels: MutableList<MainItemModel>) {
        mItemModels.clear()
        mItemModels.addAll(itemModels)
        mItemModels.sortBy { it.itemData.name }
        mAdapter.notifyDataSetChanged()
    }

}