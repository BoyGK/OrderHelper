package com.baiguoqing.orderhelper.model

import com.baiguoqing.orderhelper.app.App
import com.baiguoqing.orderhelper.bean.entity.Goods
import com.baiguoqing.orderhelper.db.GoodsDB
import com.baiguoqing.orderhelper.model.item.GoodsItemModel
import com.baiguoqing.orderhelper.widget.CommonItemView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GoodsModel(private val itemModels: MutableList<GoodsItemModel>) {

    private val db: GoodsDB by lazy { App.instance.dbManager.goodsDB }

    /**
     * 初始化货物列表
     */
    suspend fun initList(): MutableList<GoodsItemModel> {
        val arr = mutableListOf<GoodsItemModel>()
        arr.add(
            GoodsItemModel(
                Goods(
                    CommonItemView.ITEM_TYPE_ADD, "", 0f, 0f, 0
                )
            )
        )
        val list = withContext(Dispatchers.Default) { db.loadAll() }
        for (goods in list) {
            arr.add(GoodsItemModel(goods))
        }
        return arr
    }

    fun update(data: MutableList<GoodsItemModel>) {
        when {
            itemModels.size == data.size -> {
                //db update

            }
            itemModels.size > data.size -> {
                //db delete
            }
            itemModels.size < data.size -> {
                //db insert
            }
        }
    }

    fun compareAndReturn(): GoodsItemModel {
        for (goodsItemModel in itemModels) {

        }
    }

}