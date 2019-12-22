package com.baiguoqing.orderhelper.dao

import androidx.room.*
import com.baiguoqing.orderhelper.bean.entity.Order

/**
 * Created by "nullpointexception0" on 2019/12/22.
 */

@Dao
interface OrderDao {

    @Query("SELECT * FROM `order`")
    fun loadAll(): MutableList<Order>

    @Query("SELECT * FROM `order` WHERE name IN (:names)")
    fun loadByIds(names: Array<String>): MutableList<Order>

    @Query("SELECT * FROM `order` WHERE name = (:name)")
    fun loadById(name: String): Order

    @Insert
    fun insert(data: Order)

    @Update
    fun update(data: Order)

    @Delete
    fun delete(data: Order)
}