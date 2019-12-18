package com.baiguoqing.orderhelper.dao

import androidx.room.*
import com.baiguoqing.orderhelper.bean.entry.Goods

@Dao
interface GoodsDao {

    @Query("SELECT * FROM goods")
    fun getAll(): List<Goods>

    @Query("SELECT * FROM goods WHERE goodsId IN (:goodsIds)")
    fun loadAllByIds(goodsIds: IntArray): List<Goods>

    @Query("SELECT * FROM goods WHERE name = (:name)")
    fun findByName(name: String): Goods

    @Insert
    fun insertAll(vararg goods: Goods)

    @Update
    fun update(goods: Goods);

    @Delete
    fun delete(goods: Goods)

}