package com.baiguoqing.orderhelper.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.baiguoqing.orderhelper.R
import com.baiguoqing.orderhelper.databinding.ActivityNewOrderBinding
import com.baiguoqing.orderhelper.model.item.GoodsItemModel
import com.baiguoqing.orderhelper.util.ActivitySwitcher
import com.baiguoqing.orderhelper.util.log
import com.baiguoqing.orderhelper.util.showToast
import com.baiguoqing.orderhelper.viewmodel.NewOrderViewModel
import com.baiguoqing.orderhelper.widget.CommonDialog

class NewOrderActivity : AppCompatActivity() {

    private lateinit var mDataBinding: ActivityNewOrderBinding
    private lateinit var mViewModel: NewOrderViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_new_order)
        mViewModel = ViewModelProvider(
            viewModelStore,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(NewOrderViewModel::class.java)
        mDataBinding.act = this
        mDataBinding.adapter = mViewModel.mAdapter

        observer()
    }

    fun clickEditName(view: View) {
        log("clickEditName $view")
        CommonDialog.Builder(this)
            .setEditText("商家名称:", "name")
            .setButtonView(object : CommonDialog.PositiveWithEditData {
                override fun positive(view: View, mutableMap: MutableMap<Any, String>): Boolean {
                    mViewModel.updateName(mutableMap["name"] ?: "")
                    return true
                }
            })
            .show()
    }

    fun clickCreate(view: View) {
        log("clickCreate $view")
        CommonDialog.Builder(this)
            .setTittle("是否创建配送订单？")
            .setButtonView(object : CommonDialog.Positive {
                override fun positive(view: View): Boolean {
                    if (mViewModel.createOrder()) {
                        ActivitySwitcher.switchToOrderViewActivity(
                            this@NewOrderActivity,
                            mViewModel.mName.value!!
                        )
                    } else {
                        showToast(getString(R.string.create_order_error))
                    }
                    return true
                }
            })
            .show()
    }

    private fun observer() {
        mViewModel.mItems.observe(this, mItemDataSetChanged)
        mViewModel.mName.observe(this, mNameDataSetChanged)
        mViewModel.mPrice.observe(this, mPriceDataSetChanged)
        mViewModel.mInCome.observe(this, mInComeDataSetChanged)
    }

    private val mItemDataSetChanged: Observer<MutableList<GoodsItemModel>> = Observer {
        mViewModel.sort(it)
        mViewModel.notifyDataSetChanged(it)
    }

    private val mNameDataSetChanged: Observer<String> = Observer {
        mDataBinding.name = it
    }

    private val mPriceDataSetChanged: Observer<String> = Observer {
        mDataBinding.price = it
    }

    private val mInComeDataSetChanged: Observer<String> = Observer {
        mDataBinding.inCome = it
    }
}
