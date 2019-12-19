package com.baiguoqing.orderhelper.db

import androidx.room.Room
import com.baiguoqing.orderhelper.app.App
import com.baiguoqing.orderhelper.dao.IDao

class GoodsDB : IDao {

    private val db: AppDatabase by lazy {
        Room.databaseBuilder(
            App.instance.applicationContext,
            AppDatabase::class.java,
            "goods"
        ).build()
    }

    override fun <T : Any> loadAll(): MutableList<T> {
        return db.goodsDao().loadAll()
    }

    override fun <T : Any> loadByIds(ids: IntArray): MutableList<T> {
        return db.goodsDao().loadByIds(ids)
    }

    override fun <T : Any> loadById(id: Int): T {
        return db.goodsDao().loadById(id)
    }

    override fun <T : Any> insert(vararg data: T) {
        return db.goodsDao().insert(data)
    }

    override fun <T : Any> update(data: T) {
        return db.goodsDao().update(data)
    }

    override fun <T : Any> delete(data: T) {
        return db.goodsDao().delete(data)
    }
}