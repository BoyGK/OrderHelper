package com.baiguoqing.orderhelper.dao

import androidx.room.*
import com.baiguoqing.orderhelper.bean.entity.Goods

@Dao
interface GoodsDao {

    @Query("SELECT * FROM goods")
    fun loadAll(): MutableList<Goods>

    @Query("SELECT * FROM goods WHERE goodsId IN (:ids)")
    fun loadByIds(ids: IntArray): MutableList<Goods>

    @Query("SELECT * FROM goods WHERE goodsId = (:id)")
    fun loadById(id: Int): Goods

    @Insert
    fun insert(data: Goods)

    @Update
    fun update(data: Goods)

    @Delete
    fun delete(data: Goods)
}