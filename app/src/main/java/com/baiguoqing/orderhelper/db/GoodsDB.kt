package com.baiguoqing.orderhelper.db

import androidx.room.Room
import com.baiguoqing.orderhelper.app.App
import com.baiguoqing.orderhelper.bean.entity.Goods

class GoodsDB {

    private val db: AppDatabase by lazy {
        Room.databaseBuilder(
            App.instance.applicationContext,
            AppDatabase::class.java,
            "goods"
        ).build()
    }

    fun loadAll(): MutableList<Goods> {
        return db.goodsDao().loadAll()
    }

    fun loadByIds(ids: IntArray): MutableList<Goods> {
        return db.goodsDao().loadByIds(ids)
    }

    fun loadById(id: Int): Goods {
        return db.goodsDao().loadById(id)
    }

    fun insert(data: Goods) {
        return db.goodsDao().insert(data)
    }

    fun update(data: Goods) {
        return db.goodsDao().update(data)
    }

    fun delete(data: Goods) {
        return db.goodsDao().delete(data)
    }

}