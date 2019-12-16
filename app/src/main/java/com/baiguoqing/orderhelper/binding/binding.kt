package com.baiguoqing.orderhelper.binding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.baiguoqing.orderhelper.widget.CommonItemView

/**
 * Created by "nullpointexception0" on 2019/12/15.
 */
object BindingAdapters {
    @JvmStatic
    @BindingAdapter(value = ["adapter"])
    fun <T : RecyclerView.ViewHolder> setAdapter(
        recyclerView: RecyclerView,
        adapter: RecyclerView.Adapter<T>
    ) {
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(recyclerView.context)
        recyclerView.adapter = adapter
    }

    @JvmStatic
    @BindingAdapter(value = ["itemClient"])
    fun itemClient(commonItemView: CommonItemView, client: String) {
        commonItemView.setClientName(client)
    }

    @JvmStatic
    @BindingAdapter(value = ["itemDate"])
    fun itemDate(commonItemView: CommonItemView, date: String) {
        commonItemView.setDate(date)
    }

    @JvmStatic
    @BindingAdapter(value = ["itemInCome"])
    fun itemInCome(commonItemView: CommonItemView, inCome: Float) {
        commonItemView.setInCome(inCome)
    }

    @JvmStatic
    @BindingAdapter(value = ["itemName"])
    fun itemName(commonItemView: CommonItemView, name: String) {
        commonItemView.setName(name)
    }

    @JvmStatic
    @BindingAdapter(value = ["itemNum"])
    fun itemNum(commonItemView: CommonItemView, num: Int) {
        commonItemView.setNum(num)
    }

    @JvmStatic
    @BindingAdapter(value = ["itemPrice"])
    fun itemPrice(commonItemView: CommonItemView, price: Float) {
        commonItemView.setPrice(price)
    }

    @JvmStatic
    @BindingAdapter(value = ["itemPriceAll"])
    fun itemPriceAll(commonItemView: CommonItemView, priceAll: Float) {
        commonItemView.setPriceAll(priceAll)
    }

    @JvmStatic
    @BindingAdapter(value = ["itemPriceIn"])
    fun itemPriceIn(commonItemView: CommonItemView, priceIn: Float) {
        commonItemView.setPriceIn(priceIn)
    }

    @JvmStatic
    @BindingAdapter(value = ["itemPriceOut"])
    fun itemPriceOut(commonItemView: CommonItemView, priceOut: Float) {
        commonItemView.setPriceOut(priceOut)
    }

    @JvmStatic
    @BindingAdapter(value = ["itemType"])
    fun itemType(commonItemView: CommonItemView, type: String) {
        commonItemView.changeType(type)
    }
}