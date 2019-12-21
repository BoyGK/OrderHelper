package com.baiguoqing.orderhelper.util

import android.app.Activity
import android.content.Context
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast

/**
 * 日志
 */
fun log(msg: String) {
    Log.d(TAG, msg)
}

/**
 * 单位转换dp转px
 */
fun Activity.dp2px(dp: Int): Int {
    return (resources.displayMetrics.density * dp).toInt()
}

/**
 * 开启软键盘
 */
fun Activity.openSoftInput(view: View) {
    val inputService: InputMethodManager =
        getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputService.showSoftInput(view, InputMethodManager.HIDE_NOT_ALWAYS)
}

/**
 * 关闭软件盘
 */
fun Activity.closeSoftInput(view: View) {
    val inputService: InputMethodManager =
        getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputService.hideSoftInputFromWindow(view.applicationWindowToken, 0)
}

/**
 * 弹框提示
 */
fun Activity.showToast(msg: String) {
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}

/**
 * 弹框提示
 */
fun showToast(context: Context, msg: String) {
    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
}

/**
 * 不为空
 */
fun isNotEmpty(obj: Any?): Boolean {
    if (obj == null) {
        return false
    }
    return true
}