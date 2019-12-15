package com.baiguoqing.orderhelper.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.baiguoqing.orderhelper.model.ItemModel

/**
 * Created by "nullpointexception0" on 2019/12/15.
 */
class MainViewModel : ViewModel() {

    val items: MutableLiveData<List<ItemModel>> by lazy {
        MutableLiveData<List<ItemModel>>()
    }

}