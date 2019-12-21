package com.baiguoqing.orderhelper.dao

import androidx.room.*
import com.baiguoqing.orderhelper.bean.entity.Goods

@Dao
interface GoodsDao {

    @Query("SELECT * FROM goods")
    fun loadAll(): MutableList<Goods>

    @Query("SELECT * FROM goods WHERE name IN (:names)")
    fun loadByIds(names: Array<String>): MutableList<Goods>

    @Query("SELECT * FROM goods WHERE name = (:name)")
    fun loadById(name: String): Goods

    @Insert
    fun insert(data: Goods)

    @Update
    fun update(data: Goods)

    @Delete
    fun delete(data: Goods)
}