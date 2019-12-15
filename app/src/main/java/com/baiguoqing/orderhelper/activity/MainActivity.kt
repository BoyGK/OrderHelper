package com.baiguoqing.orderhelper.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.baiguoqing.orderhelper.BR
import com.baiguoqing.orderhelper.R
import com.baiguoqing.orderhelper.adapter.MainAdapter
import com.baiguoqing.orderhelper.bean.ItemData
import com.baiguoqing.orderhelper.model.ItemModel
import com.baiguoqing.orderhelper.viewmodel.MainViewModel
import java.util.*

class MainActivity : AppCompatActivity() {

    lateinit var viewBinding: ViewDataBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewBinding.setVariable(
            BR.adapter, MainAdapter(
                listOf(
                    ItemModel(
                        ItemData(
                            "commodity", "ABC", 1.23f, 3.21f, 999,
                            "", 0f, 0f, "", 0f
                        )
                    ),
                    ItemModel(
                        ItemData(
                            "date", "", 0f, 0f, 0,
                            Date().time.toString(), 12345f, 1666f, "", 0f
                        )
                    ),
                    ItemModel(
                        ItemData(
                            "client", "", 0f, 0f, 0,
                            "", 0f, 0f, "BGQ", 6666f
                        )
                    ),
                    ItemModel(
                        ItemData(
                            "add", "ABC", 0f, 0f, 0,
                            "", 0f, 0f, "", 0f
                        )
                    )
                )
            )
        )
        val vm = ViewModelProvider.AndroidViewModelFactory.getInstance(application)
            .create(MainViewModel::class.java)
        vm.items.observe(this, Observer {

        })
        vm.items.postValue(
            listOf(
                ItemModel(
                    ItemData(
                        "commodity", "ABC", 1.23f, 3.21f, 999,
                        "", 0f, 0f, "", 0f
                    )
                ),
                ItemModel(
                    ItemData(
                        "date", "", 0f, 0f, 0,
                        Date().time.toString(), 12345f, 1666f, "", 0f
                    )
                ),
                ItemModel(
                    ItemData(
                        "client", "", 0f, 0f, 0,
                        "", 0f, 0f, "BGQ", 6666f
                    )
                ),
                ItemModel(
                    ItemData(
                        "add", "ABC", 0f, 0f, 0,
                        "", 0f, 0f, "", 0f
                    )
                )
            )
        )
    }
}
