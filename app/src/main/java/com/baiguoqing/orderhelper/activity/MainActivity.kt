package com.baiguoqing.orderhelper.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.baiguoqing.bottomtools.BottomTools
import com.baiguoqing.orderhelper.BR
import com.baiguoqing.orderhelper.R
import com.baiguoqing.orderhelper.databinding.ActivityMainBinding
import com.baiguoqing.orderhelper.model.ItemModel
import com.baiguoqing.orderhelper.util.ActivitySwitcher
import com.baiguoqing.orderhelper.util.dp2px
import com.baiguoqing.orderhelper.util.log
import com.baiguoqing.orderhelper.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var mViewBinding: ActivityMainBinding
    private lateinit var mViewModel: MainViewModel
    private lateinit var mBottomTools: BottomTools.Builder

    /**
     * 底部状态栏高度
     */
    companion object {
        private const val MARGIN_CLOSE = 16
        private const val MARGIN_JUDGE = 64
        private const val MARGIN_OPEN = 117
        private const val BOTTOM_TOOLS_HEIGHT = 101
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mViewBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mViewBinding.act = this

        mViewModel = ViewModelProvider(
            viewModelStore,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(MainViewModel::class.java)
        mViewBinding.adapter = mViewModel.mAdapter

        val viewDataBinding = DataBindingUtil.inflate<ViewDataBinding>(
            LayoutInflater.from(this), R.layout.menu_main_bottom, null, false
        )
        viewDataBinding.setVariable(BR.act, this)

        mBottomTools = BottomTools.Builder(this, viewDataBinding.root)
        mBottomTools.setFixSoftInputHeight(false)
        mBottomTools.setBottomToolsHeight(dp2px(BOTTOM_TOOLS_HEIGHT))

        observer()
    }

    override fun onRestart() {
        super.onRestart()
        mBottomTools.restart()
    }

    override fun onPause() {
        super.onPause()
        mBottomTools.destroy()
    }

    fun onClickEdit(view: View) {
        log("onClickEdit:$view")
        val params = mViewBinding.actionButton.layoutParams as ConstraintLayout.LayoutParams
        if (params.bottomMargin < dp2px(MARGIN_JUDGE)) {
            params.bottomMargin = dp2px(MARGIN_OPEN)
            mBottomTools.show()
        } else if (params.bottomMargin > dp2px(MARGIN_JUDGE)) {
            params.bottomMargin = dp2px(MARGIN_CLOSE)
            mBottomTools.dismiss()
        }
        mViewBinding.actionButton.layoutParams = params
    }

    fun onClickEditGoodsList(view: View) {
        log("onClickEditGoodsList:$view")
        ActivitySwitcher.switchToEditGoodsActivity(this)
        onClickEdit(view)
    }

    fun onClickNewOrder(view: View) {
        log("onClickNewOrder:$view")
        ActivitySwitcher.switchToNewOrderActivity(this)
        onClickEdit(view)
    }

    private fun observer() {
        mViewModel.mItems.observe(this, mItemDataSetChanged)
    }

    private val mItemDataSetChanged: Observer<List<ItemModel>> = Observer {
        mViewModel.notifyDataSetChanged(it)
    }
}
