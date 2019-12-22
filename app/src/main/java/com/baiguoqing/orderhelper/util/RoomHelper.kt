package com.baiguoqing.orderhelper.util

import com.baiguoqing.orderhelper.bean.entity.Goods
import com.google.gson.Gson

/**
 * Created by "nullpointexception0" on 2019/12/22.
 */

data class GoodsList(val goodsList: MutableList<Goods>)

fun roomGoodsListToJson(goodsList: GoodsList): String {
    return Gson().toJson(goodsList)
}

fun roomJsonToGoodsList(json: String): GoodsList {
    return Gson().fromJson(json, GoodsList::class.java)
}