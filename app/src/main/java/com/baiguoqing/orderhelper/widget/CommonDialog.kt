package com.baiguoqing.orderhelper.widget

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatTextView
import com.baiguoqing.orderhelper.R

/**
 * Created by "nullpointexception0" on 2019/12/17.
 */
@SuppressLint("InflateParams")
class CommonDialog(context: Context) : Dialog(context) {

    private var mPositive: Positive? = null
    private var mNegative: Negative? = null
    private var mOtherAction: OtherAction? = null

    private val mRootView: ViewGroup by lazy {
        LayoutInflater.from(context).inflate(R.layout.common_dialog, null) as ViewGroup
    }

    val mEditTextContent = mutableMapOf<Any, String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mRootView)
    }

    override fun dismiss() {
        super.dismiss()
        mNegative?.negative()
    }

    private fun createTittleView(tittle: String) {
        val view = LayoutInflater.from(context).inflate(R.layout.common_dialog_tittle, mRootView)
        val tvTittle = view.findViewById<AppCompatTextView>(R.id.common_dialog_tittle)
        tvTittle.text = tittle
    }

    private fun createEditView(leftText: String, tag: Any) {
        val view = LayoutInflater.from(context).inflate(R.layout.common_dialog_edit, mRootView)
        val leftView = view.findViewById<AppCompatTextView>(R.id.common_dialog_edit_name)
        val rightView = view.findViewById<AppCompatEditText>(R.id.common_dialog_edit_text)
        leftView.text = leftText
        rightView.tag = tag
        rightView.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                mEditTextContent[tag] = s.toString()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })
    }

    private fun createHintView(leftText: String, rightHint: String) {
        val view = LayoutInflater.from(context).inflate(R.layout.common_dialog_hint, mRootView)
        val leftView = view.findViewById<AppCompatTextView>(R.id.common_dialog_hint_name)
        val rightView = view.findViewById<AppCompatTextView>(R.id.common_dialog_hint_text)
        leftView.text = leftText
        rightView.text = rightHint
    }

    private fun createButtonView(model: Int) {
        val view = LayoutInflater.from(context).inflate(R.layout.common_dialog_btn, mRootView)
        val ok = view.findViewById<AppCompatButton>(R.id.common_dialog_btn_ok)
        val other = view.findViewById<AppCompatButton>(R.id.common_dialog_btn_other)
        if (model == 0) {
            other.visibility = View.GONE
        }
        ok.setOnClickListener {
            mPositive?.positive(it)
        }
        other.setOnClickListener {
            mOtherAction?.action(it)
        }
    }

    interface Positive {
        fun positive(view: View)
    }

    interface Negative {
        fun negative()
    }

    interface OtherAction {
        fun action(view: View)
    }

    class Builder(context: Context) {

        var dialog: CommonDialog = CommonDialog(context)

        fun setTittle(tittle: String): Builder {
            dialog.createTittleView(tittle)
            return this
        }

        fun setEditText(leftText: String, tag: Any): Builder {
            dialog.createEditView(leftText, tag)
            return this
        }

        fun setEditWithHint(leftText: String, rightText: String): Builder {
            dialog.createHintView(leftText, rightText)
            return this
        }

        fun setButtonView(positive: Positive): Builder {
            dialog.mPositive = positive
            dialog.createButtonView(0)
            return this
        }

        fun setButtonView(positive: Positive, action: OtherAction): Builder {
            dialog.mPositive = positive
            dialog.mOtherAction = action
            dialog.createButtonView(1)
            return this
        }

        fun show() {
            dialog.show()
        }

    }

}