package com.baiguoqing.orderhelper.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.baiguoqing.orderhelper.bean.entity.Goods
import com.baiguoqing.orderhelper.dao.GoodsDao


@Database(entities = [Goods::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun goodsDao(): GoodsDao
}