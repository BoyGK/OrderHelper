package com.baiguoqing.orderhelper.util

import android.app.Activity
import android.content.Intent
import com.baiguoqing.orderhelper.activity.EditGoodsActivity

/**
 * Activity跳转
 */
object ActivitySwitcher {

    fun switchToEditGoodsActivity(activity: Activity) {
        switchToActivity(activity, EditGoodsActivity::class.java)
    }

    private fun switchToActivity(activity: Activity, clazz: Class<*>) {
        log("from ${activity.javaClass.name} to ${clazz.name}")
        activity.startActivity(Intent(activity, clazz))
    }

}