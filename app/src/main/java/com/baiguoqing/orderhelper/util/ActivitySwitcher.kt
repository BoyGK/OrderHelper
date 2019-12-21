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
        switchToActivity(activity, EditGoodsActivity::class.java)
    }

    fun switchToNewOrderActivity(activity: Activity) {
        switchToActivity(activity, NewOrderActivity::class.java)
    }

    private fun switchToActivity(activity: Activity, clazz: Class<*>) {
        log("from ${activity.javaClass.name} to ${clazz.name}")
        activity.startActivity(Intent(activity, clazz))
    }

}