package com.baiguoqing.orderhelper.db

import androidx.room.Room
import com.baiguoqing.orderhelper.app.App
import com.baiguoqing.orderhelper.bean.entity.Order

/**
 * Created by "nullpointexception0" on 2019/12/22.
 */
class OrderDB {

    private val db: AppDatabase by lazy {
        Room.databaseBuilder(
            App.instance.applicationContext,
            AppDatabase::class.java,
            "order"
        ).build()
    }

    fun loadAll(): MutableList<Order> {
        return db.orderDao().loadAll()
    }

    fun loadByIds(names: Array<String>): MutableList<Order> {
        return db.orderDao().loadByIds(names)
    }

    fun loadById(name: String): Order {
        return db.orderDao().loadById(name)
    }

    fun insert(data: Order) {
        return db.orderDao().insert(data)
    }

    fun update(data: Order) {
        return db.orderDao().update(data)
    }

    fun delete(data: Order) {
        return db.orderDao().delete(data)
    }

}