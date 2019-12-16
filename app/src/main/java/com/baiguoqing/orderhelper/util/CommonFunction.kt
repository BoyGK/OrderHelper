package com.baiguoqing.orderhelper.util

import android.app.Activity

fun Activity.dp2px(dp: Int): Int {
    return (resources.displayMetrics.density * dp).toInt()
}