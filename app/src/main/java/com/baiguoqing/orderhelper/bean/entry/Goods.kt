package com.baiguoqing.orderhelper.bean.entry

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "goods")
data class Goods(
    @PrimaryKey(autoGenerate = true) val goodsId: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "priceIn") val priceIn: Float,
    @ColumnInfo(name = "priceOut") val priceOut: Float,
    @ColumnInfo(name = "num") val num: Int,
    @ColumnInfo(name = "date") val date: String,
    @ColumnInfo(name = "priceAll") val priceAll: Float,
    @ColumnInfo(name = "inCome") val inCome: Float,
    @ColumnInfo(name = "client") val client: String,
    @ColumnInfo(name = "price") val price: Float,
    @ColumnInfo(name = "type") val type: String
)