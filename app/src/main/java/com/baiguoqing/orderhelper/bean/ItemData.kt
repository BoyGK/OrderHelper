package com.baiguoqing.orderhelper.bean

/**
 * Created by "nullpointexception0" on 2019/12/15.
 */
data class ItemData(
    var type: String,
    var name: String,
    var priceIn: Float,
    var priceOut: Float,
    var num: Int,
    var date: String,
    var priceAll: Float,
    var inCome: Float,
    var client: String,
    var price: Float
)