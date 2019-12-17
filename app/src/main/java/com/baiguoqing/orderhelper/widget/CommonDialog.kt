package com.baiguoqing.orderhelper.widget

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import com.baiguoqing.orderhelper.R

/**
 * Created by "nullpointexception0" on 2019/12/17.
 */
class CommonDialog(context: Context) : Dialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.common_dialog)
    }

}