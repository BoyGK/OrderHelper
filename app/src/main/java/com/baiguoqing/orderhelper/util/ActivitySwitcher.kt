package com.baiguoqing.orderhelper.util

import android.content.Context
import android.content.Intent
import com.baiguoqing.orderhelper.activity.EditGoodsActivity

/**
 * Activity跳转
 */
object ActivitySwitcher {

    fun switchToEditGoodsActivity(context: Context) {
        context.startActivity(Intent(context, EditGoodsActivity::class.java))
    }

}