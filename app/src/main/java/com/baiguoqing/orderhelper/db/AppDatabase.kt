package com.baiguoqing.orderhelper.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.baiguoqing.orderhelper.bean.entity.Goods
import com.baiguoqing.orderhelper.bean.entity.Order
import com.baiguoqing.orderhelper.dao.GoodsDao
import com.baiguoqing.orderhelper.dao.OrderDao


@Database(entities = [Goods::class, Order::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun goodsDao(): GoodsDao
    abstract fun orderDao(): OrderDao
}