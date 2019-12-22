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
 * Created by "nullpt" on 2019/12/17.
 */
@SuppressLint("InflateParams")
class CommonDialog(context: Context) : Dialog(context) {

    private var mPositive: Positive? = null
    private var mPositiveWithData: PositiveWithEditData? = null
    private var mNegative: Negative? = null
    private var mOtherAction: OtherAction? = null

    private val mRootView: ViewGroup by lazy {
        LayoutInflater.from(context).inflate(R.layout.common_dialog, null) as ViewGroup
    }

    private val mEditTextContent = mutableMapOf<Any, String>()
    private var mTittleViewIndex = 0
    private var mEditTextViewIndex = 0
    private var mHintViewIndex = 0
    private var mButtonViewIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mRootView)
    }

    override fun dismiss() {
        super.dismiss()
        mNegative?.negative()
    }

    /**
     * Max Tittle is one
     */
    private fun createTittleView(tittle: String) {
        if (mTittleViewIndex++ > 1) {
            return
        }
        val view = LayoutInflater.from(context).inflate(R.layout.common_dialog_tittle, mRootView)
        val tvTittle = view.findViewById<AppCompatTextView>(R.id.common_dialog_tittle)
        tvTittle.text = tittle
    }

    /**
     * Max EditText is three
     */
    private fun createEditView(leftText: String, tag: Any) {
        if (mEditTextViewIndex++ > 3) {
            return
        }
        val view: View
        var leftView: AppCompatTextView? = null
        var rightView: AppCompatEditText? = null
        when (mEditTextViewIndex) {
            1 -> {
                view = LayoutInflater.from(context).inflate(R.layout.common_dialog_edit1, mRootView)
                leftView = view.findViewById(R.id.common_dialog_edit_name1)
                rightView = view.findViewById(R.id.common_dialog_edit_text1)
            }
            2 -> {
                view = LayoutInflater.from(context).inflate(R.layout.common_dialog_edit2, mRootView)
                leftView = view.findViewById(R.id.common_dialog_edit_name2)
                rightView = view.findViewById(R.id.common_dialog_edit_text2)
            }
            3 -> {
                view = LayoutInflater.from(context).inflate(R.layout.common_dialog_edit3, mRootView)
                leftView = view.findViewById(R.id.common_dialog_edit_name3)
                rightView = view.findViewById(R.id.common_dialog_edit_text3)
            }
        }
        leftView!!.text = leftText
        rightView!!.tag = tag
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

    /**
     * Max HintView is three
     */
    private fun createHintView(leftText: String, rightHint: String) {
        if (mHintViewIndex++ > 3) {
            return
        }
        val view: View
        var leftView: AppCompatTextView? = null
        var rightView: AppCompatTextView? = null
        when (mHintViewIndex) {
            1 -> {
                view = LayoutInflater.from(context).inflate(R.layout.common_dialog_hint1, mRootView)
                leftView = view.findViewById(R.id.common_dialog_hint_name1)
                rightView = view.findViewById(R.id.common_dialog_hint_text1)
            }
            2 -> {
                view = LayoutInflater.from(context).inflate(R.layout.common_dialog_hint2, mRootView)
                leftView = view.findViewById(R.id.common_dialog_hint_name2)
                rightView = view.findViewById(R.id.common_dialog_hint_text2)
            }
            3 -> {
                view = LayoutInflater.from(context).inflate(R.layout.common_dialog_hint3, mRootView)
                leftView = view.findViewById(R.id.common_dialog_hint_name3)
                rightView = view.findViewById(R.id.common_dialog_hint_text3)
            }
        }
        leftView!!.text = leftText
        rightView!!.text = rightHint
    }

    /**
     * Max ButtonView is one
     */
    private fun createButtonView(model: Int) {
        if (mButtonViewIndex++ > 1) {
            return
        }
        val view = LayoutInflater.from(context).inflate(R.layout.common_dialog_btn, mRootView)
        val ok = view.findViewById<AppCompatButton>(R.id.common_dialog_btn_ok)
        val other = view.findViewById<AppCompatButton>(R.id.common_dialog_btn_other)
        if (model == 0) {
            other.visibility = View.GONE
        }
        ok.setOnClickListener {
            if (mPositive?.positive(it) == true) {
                dismiss()
            }
            if (mPositiveWithData?.positive(it, mEditTextContent) == true) {
                dismiss()
            }
        }
        other.setOnClickListener {
            if (mOtherAction?.action(it) == true) {
                dismiss()
            }
        }
    }

    interface Positive {
        fun positive(view: View): Boolean
    }

    interface PositiveWithEditData {
        fun positive(view: View, mutableMap: MutableMap<Any, String>): Boolean
    }

    interface OtherAction {
        fun action(view: View): Boolean
    }

    interface Negative {
        fun negative()
    }

    class Builder(context: Context) {

        private val dialog: CommonDialog = CommonDialog(context)

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

        fun setButtonView(positive: PositiveWithEditData): Builder {
            dialog.mPositiveWithData = positive
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