package com.baiguoqing.orderhelper.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.baiguoqing.orderhelper.R
import com.baiguoqing.orderhelper.databinding.ActivityEditGoodsBinding
import com.baiguoqing.orderhelper.model.ItemModel
import com.baiguoqing.orderhelper.viewmodel.EditGoodsViewModel

class EditGoodsActivity : AppCompatActivity() {

    private lateinit var mEditGoodsBinding: ActivityEditGoodsBinding
    private lateinit var mViewModel: EditGoodsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_goods)
        mEditGoodsBinding = DataBindingUtil.setContentView(this, R.layout.activity_edit_goods)
        mViewModel = ViewModelProvider(
            viewModelStore,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(EditGoodsViewModel::class.java)
        mEditGoodsBinding.adapter = mViewModel.mAdapter

        observer()
    }

    private fun observer() {
        mViewModel.mItems.observe(this, mItemDataSetChanged)
    }

    private val mItemDataSetChanged: Observer<ArrayList<ItemModel>> = Observer {
        mViewModel.notifyDataSetChanged(it)
    }
}
