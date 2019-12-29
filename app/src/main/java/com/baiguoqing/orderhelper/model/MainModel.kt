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

    /**
     * 1.对接安全信使安全群聊协议接口：包括群组创建协议，群组操作协议，群组列表协议，群组聊天协议等。
    2.制定消息加密内容加密方式，对接调试加密消息发送流程。
    3.合并最近联系人列表，重写安全聊天列表和普通聊天列表管理类。

    1.实现安全信使最近联系人列表的方案改版。
    2.实现安全聊天剩余协议的对接，完成安全聊天功能的全部开发。
    3.开始开发家族区块链项目，按计划安排完成‘我的’相关界面开发。

    参与写书活动，参考博客以及源码，详细介绍了App启动流程优化过程。
     */

}
