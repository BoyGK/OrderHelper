package com.baiguoqing.orderhelper.model.item

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.baiguoqing.orderhelper.BR
import com.baiguoqing.orderhelper.bean.ItemData

/**
 * Created by "nullpointexception0" on 2019/12/23.
 */
class MainItemModel(item: ItemData) : BaseObservable() {

    @Bindable
    var itemData: ItemData = item
        set(value) {
            field = value
            notifyPropertyChanged(BR.data)
        }

}