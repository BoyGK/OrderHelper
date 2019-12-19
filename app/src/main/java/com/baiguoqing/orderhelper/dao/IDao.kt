package com.baiguoqing.orderhelper.dao

interface IDao {

    fun <T : Any> loadAll(): MutableList<T>

    fun <T : Any> loadByIds(ids: IntArray): MutableList<T>

    fun <T : Any> loadById(id: Int): T

    fun <T : Any> insert(vararg data: T)

    fun <T : Any> update(data: T)

    fun <T : Any> delete(data: T)

}