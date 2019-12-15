package com.baiguoqing.orderhelper.model

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.baiguoqing.orderhelper.BR
import com.baiguoqing.orderhelper.bean.ItemData

/**
 * Created by "nullpointexception0" on 2019/12/15.
 */
class ItemModel constructor(
    item: ItemData? = null
) : BaseObservable() {

    @Bindable
    var itemData: ItemData? = item
        set(value) {
            field = value
            notifyPropertyChanged(BR.data)
        }

}