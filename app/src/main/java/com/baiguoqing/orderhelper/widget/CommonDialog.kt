package com.baiguoqing.orderhelper.widget

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.view.View
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import com.baiguoqing.orderhelper.R
import com.baiguoqing.orderhelper.util.showToast

/**
 * Created by "nullpointexception0" on 2019/12/17.
 */
class CommonDialog(context: Context) : Dialog(context) {

    var mGoodsName = ""
    var mGoodsPriceIn = 0f
    var mGoodsPriceOut = 0f

    private var mPositive: Positive? = null
    private var mNegative: Negative? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.common_dialog)

        val name = findViewById<AppCompatEditText>(R.id.dialog_goods_name)
        val priceIn = findViewById<AppCompatEditText>(R.id.dialog_goods_priceIn)
        val priceOut = findViewById<AppCompatEditText>(R.id.dialog_goods_priceOut)
        val ok = findViewById<AppCompatButton>(R.id.dialog_ok)

        name.text = SpannableStringBuilder(mGoodsName)
        priceIn.hint = SpannableStringBuilder(mGoodsPriceIn.toString())
        priceOut.hint = SpannableStringBuilder(mGoodsPriceOut.toString())

        ok.setOnClickListener {
            mGoodsName = name.text.toString()
            if (priceIn.text.toString() != "") {
                mGoodsPriceIn = priceIn.text.toString().toFloat()
            }
            if (priceOut.text.toString() != "") {
                mGoodsPriceOut = priceOut.text.toString().toFloat()
            }
            if ("" != mGoodsName) dismiss() else {
                showToast(context, context.getString(R.string.goods_name_can_not_null))
                return@setOnClickListener
            }
            mPositive?.positive(it)
        }
    }

    override fun dismiss() {
        super.dismiss()
        mNegative?.negative()
    }

    interface Positive {
        fun positive(view: View)
    }

    interface Negative {
        fun negative()
    }

    class Builder(context: Context) {

        var dialog: CommonDialog = CommonDialog(context)

        fun setGoodsName(name: String): Builder {
            dialog.mGoodsName = name
            return this
        }

        fun setPriceIn(priceIn: Float): Builder {
            dialog.mGoodsPriceIn = priceIn
            return this
        }

        fun setPriceOut(priceOut: Float): Builder {
            dialog.mGoodsPriceOut = priceOut
            return this
        }

        fun setPositive(positive: Positive): Builder {
            dialog.mPositive = positive
            return this
        }

        fun setNegative(negative: Negative): Builder {
            dialog.mNegative = negative
            return this
        }

        fun show(): CommonDialog {
            dialog.show()
            return dialog
        }

    }

}