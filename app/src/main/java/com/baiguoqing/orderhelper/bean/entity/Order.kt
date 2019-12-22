package com.baiguoqing.orderhelper.bean.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by "nullpointexception0" on 2019/12/22.
 */
@Entity(tableName = "order")
data class Order(
    @ColumnInfo(name = "type") val type: String,
    @PrimaryKey
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "date") val date: String,
    @ColumnInfo(name = "price") val price: Float,
    @ColumnInfo(name = "income") val income: Float,
    @ColumnInfo(name = "state") val state: String,
    @ColumnInfo(name = "goods") val orderList: String
)