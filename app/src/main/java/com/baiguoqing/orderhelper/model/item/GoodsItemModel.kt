package com.baiguoqing.orderhelper.model.item

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.baiguoqing.orderhelper.BR
import com.baiguoqing.orderhelper.bean.entity.Goods


class GoodsItemModel(item: Goods) : BaseObservable() {

    @Bindable
    var itemData: Goods = item
        set(value) {
            field = value
            notifyPropertyChanged(BR.data)
        }

}