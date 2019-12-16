package com.baiguoqing.orderhelper.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.baiguoqing.orderhelper.R
import com.baiguoqing.orderhelper.databinding.ActivityMainBinding
import com.baiguoqing.orderhelper.model.ItemModel
import com.baiguoqing.orderhelper.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    lateinit var mViewBinding: ActivityMainBinding
    lateinit var mViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mViewModel = ViewModelProvider(
            viewModelStore,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(MainViewModel::class.java)
        mViewBinding.adapter = mViewModel.mAdapter

        observer()
    }

    private fun observer() {
        mViewModel.mItems.observe(this, mItemDataSetChanged)
    }

    private val mItemDataSetChanged: Observer<List<ItemModel>> = Observer {
        mViewModel.notifyDataSetChanged(it)
    }
}
