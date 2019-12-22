package com.baiguoqing.orderhelper.model

import com.baiguoqing.orderhelper.app.App
import com.baiguoqing.orderhelper.bean.entity.Order
import com.baiguoqing.orderhelper.db.GoodsDB
import com.baiguoqing.orderhelper.db.OrderDB
import com.baiguoqing.orderhelper.model.item.GoodsItemModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Created by "nullpointexception0" on 2019/12/22.
 */
class OrderModel {

    private val gdb: GoodsDB by lazy { App.instance.dbManager.goodsDB }
    private val odb: OrderDB by lazy { App.instance.dbManager.orderDB }

    /**
     * 初始化货物列表
     */
    suspend fun initList(): MutableList<GoodsItemModel> {
        val arr = mutableListOf<GoodsItemModel>()
        val list = withContext(Dispatchers.Default) { gdb.loadAll() }
        for (goods in list) {
            arr.add(GoodsItemModel(goods))
        }
        return arr
    }

    fun update(data: Order, type: String) {
        when (type) {
            "update" -> {
                odb.update(data)
            }
            "delete" -> {
                odb.delete(data)
            }
            "insert" -> {
                odb.insert(data)
            }
        }
    }

}