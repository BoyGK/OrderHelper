package com.baiguoqing.orderhelper.bean.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "goods")
data class Goods(
    @ColumnInfo(name = "type") val type: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "priceIn") val priceIn: Float,
    @ColumnInfo(name = "priceOut") val priceOut: Float,
    @ColumnInfo(name = "num") val num: Int,
    @PrimaryKey(autoGenerate = true) val goodsId: Int? = null
)