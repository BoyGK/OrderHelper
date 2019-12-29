package com.baiguoqing.orderhelper.model

import com.baiguoqing.orderhelper.app.App
import com.baiguoqing.orderhelper.bean.ItemData
import com.baiguoqing.orderhelper.db.OrderDB
import com.baiguoqing.orderhelper.model.item.MainItemModel
import com.baiguoqing.orderhelper.widget.CommonItemView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Created by "nullpointexception0" on 2019/12/23.
 */
class MainModel {

    private val db: OrderDB by lazy { App.instance.dbManager.orderDB }

    /**
     * 初始化货物列表
     */
    suspend fun initList(): MutableList<MainItemModel> {

        val orderList = withContext(Dispatchers.Default) { db.loadAll() }
        if (orderList.size == 0) {
            return mutableListOf()
        }

        var date = mutableListOf<String>()
        var price = mutableListOf<Float>()
        var incom = mutableListOf<Float>()
        for (order in orderList) {

        }

        val listTittle = mutableListOf<MainItemModel>()
        val list = mutableListOf<MainItemModel>()
        val parentItem = MainItemModel(
            ItemData(
                CommonItemView.ITEM_TYPE_ORDER_DATE,
                "", 0f, 0f, 0,
                orderList[0].date,
                orderList[0].price,
                orderList[0].income,
                "", 0f
            )
        )
        list.add(parentItem)
        for (order in orderList) {
            val item = MainItemModel(
                ItemData(
                    CommonItemView.ITEM_TYPE_ORDER_CLIENT,
                    "", 0f, 0f, 0,
                    "", 0f, 0f,
                    order.name, order.price
                )
            )
            list.add(item)
        }
        return list
    }

}
