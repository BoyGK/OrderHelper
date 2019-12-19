package com.baiguoqing.orderhelper.dao

import androidx.room.*

@Dao
interface GoodsDao : IDao {

    @Query("SELECT * FROM goods")
    override fun <T : Any> loadAll(): MutableList<T>

    @Query("SELECT * FROM goods WHERE goodsId IN (:ids)")
    override fun <T : Any> loadByIds(ids: IntArray): MutableList<T>

    @Query("SELECT * FROM goods WHERE goodsId = (:id)")
    override fun <T : Any> loadById(id: Int): T

    @Insert
    override fun <T : Any> insert(vararg data: T)

    @Update
    override fun <T : Any> update(data: T)

    @Delete
    override fun <T : Any> delete(data: T)
}