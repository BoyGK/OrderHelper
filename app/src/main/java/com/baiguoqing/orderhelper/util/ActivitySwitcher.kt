package com.baiguoqing.orderhelper.util

import android.app.Activity
import android.content.Intent
import com.baiguoqing.orderhelper.activity.EditGoodsActivity
import com.baiguoqing.orderhelper.activity.NewOrderActivity

/**
 * Activity跳转
 */
object ActivitySwitcher {

    fun switchToEditGoodsActivity(activity: Activity) {
        switchToActivity(activity, Intent(activity, EditGoodsActivity::class.java))
    }

    fun switchToNewOrderActivity(activity: Activity) {
        switchToActivity(activity, Intent(activity, NewOrderActivity::class.java))
    }

    fun switchToOrderViewActivity(activity: Activity, orderName: String) {
        val intent = Intent(activity, NewOrderActivity::class.java)
        intent.putExtra("orderName", orderName)
        switchToActivity(activity, intent)
    }

    private fun switchToActivity(activity: Activity, intent: Intent) {
        log("from ${activity.javaClass.name} to ${intent.component?.className}")
        activity.startActivity(intent)
    }

}