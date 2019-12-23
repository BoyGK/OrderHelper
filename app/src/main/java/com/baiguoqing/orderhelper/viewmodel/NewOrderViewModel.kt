package com.baiguoqing.orderhelper.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.baiguoqing.orderhelper.R
import com.baiguoqing.orderhelper.adapter.NewOrderAdapter
import com.baiguoqing.orderhelper.app.App
import com.baiguoqing.orderhelper.bean.entity.Goods
import com.baiguoqing.orderhelper.bean.entity.Order
import com.baiguoqing.orderhelper.model.OrderModel
import com.baiguoqing.orderhelper.model.item.GoodsItemModel
import com.baiguoqing.orderhelper.util.GoodsList
import com.baiguoqing.orderhelper.util.isNotEmpty
import com.baiguoqing.orderhelper.util.roomGoodsListToJson
import com.baiguoqing.orderhelper.widget.CommonItemView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.text.Collator
import java.util.*
import kotlin.Comparator

/**
 * Created by "nullpointexception0" on 2019/12/22.
 */
class NewOrderViewModel : ViewModel() {

    val mItems: MutableLiveData<MutableList<GoodsItemModel>> by lazy {
        MutableLiveData<MutableList<GoodsItemModel>>()
    }

    val mName: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val mPrice: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val mInCome: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }


    private val mItemModels = mutableListOf<GoodsItemModel>()

    private val model: OrderModel by lazy {
        OrderModel()
    }

    val mAdapter: NewOrderAdapter by lazy {
        NewOrderAdapter(mItemModels, this)
    }

    init {
        GlobalScope.launch {
            updateUI(model.initList())
        }
    }


    fun updateName(name: String) {
        mName.postValue(name)
    }

    /**
     * 数据驱动更新UI
     */
    fun updateUI(itemModels: MutableList<GoodsItemModel>) {
        mItems.postValue(itemModels)
        var priceInAll = 0f
        var priceOutAll = 0f
        for (itemModel in itemModels) {
            if (itemModel.itemData.num != 0) {
                priceInAll += itemModel.itemData.priceIn * itemModel.itemData.num
                priceOutAll += itemModel.itemData.priceOut * itemModel.itemData.num
            }
        }
        mPrice.postValue(String.format("%.2f", priceInAll))
        mInCome.postValue(String.format("%.2f", priceOutAll - priceInAll))
    }


    fun createOrder(): Boolean {
        if (!isNotEmpty(mName.value) || !isNotEmpty(mPrice.value) || !isNotEmpty(mInCome.value) || mName.value == "") {
            return false
        }
        val goodsList = mutableListOf<Goods>()
        for (mItemModel in mItemModels) {
            goodsList.add(mItemModel.itemData)
        }
        val order = Order(
            CommonItemView.ITEM_TYPE_ORDER_CLIENT,
            mName.value!!,
            "${Calendar.YEAR}-${Calendar.MONTH}-${Calendar.DAY_OF_MONTH}",
            mPrice.value!!.toFloat(),
            mInCome.value!!.toFloat(),
            App.instance.getString(R.string.order_running),
            roomGoodsListToJson(GoodsList(goodsList))
        )
        GlobalScope.launch {
            model.update(order, "insert")
        }
        return true
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