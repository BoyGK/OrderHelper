package com.baiguoqing.orderhelper.widget

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.baiguoqing.orderhelper.R

class CommonItemView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attributeSet, defStyleAttr) {

    companion object {
        const val ITEM_TYPE_COMMODITY = "commodity"
        const val ITEM_TYPE_ORDER_DATE = "date"
        const val ITEM_TYPE_ORDER_CLIENT = "client"
        const val ITEM_TYPE_ADD = "add"
    }

    /**
     * 商品item
     */
    private var mItemCommodity: ConstraintLayout
    private var mItemName: TextView
    private var mItemIn: TextView
    private var mItemOut: TextView
    private var mItemNum: TextView

    /**
     * 带日期item
     */
    private var mItemWithDate: ConstraintLayout
    private var mItemDate: TextView
    private var mItemPriceAll: TextView
    private var mItemInCome: TextView

    /**
     * 客户item
     */
    private var mItemClient: ConstraintLayout
    private var mItemClientName: TextView
    private var mItemPrice: TextView

    /**
     * Add item
     */
    private var mItemAdd: ConstraintLayout

    init {
        LayoutInflater.from(context).inflate(R.layout.common_item, this)
        mItemCommodity = findViewById(R.id.item_commodity)
        mItemName = findViewById(R.id.item_common_name)
        mItemIn = findViewById(R.id.item_common_price_in)
        mItemOut = findViewById(R.id.item_common_price_out)
        mItemNum = findViewById(R.id.item_common_num)
        mItemWithDate = findViewById(R.id.item_withTime)
        mItemDate = findViewById(R.id.item_common_date)
        mItemPriceAll = findViewById(R.id.item_common_order_all_price)
        mItemInCome = findViewById(R.id.item_common_income)
        mItemClient = findViewById(R.id.item_client)
        mItemClientName = findViewById(R.id.item_common_client)
        mItemPrice = findViewById(R.id.item_common_order_price)
        mItemAdd = findViewById(R.id.item_add)
        isClickable = true
        isFocusable = true
        if (attributeSet != null) {
            initItemType(attributeSet)
        }
    }

    private fun initItemType(attributeSet: AttributeSet) {
        val typedArray: TypedArray =
            context.obtainStyledAttributes(attributeSet, R.styleable.CommonItemView)
        val type = typedArray.getString(R.styleable.CommonItemView_itemType)
        when (type ?: ITEM_TYPE_COMMODITY) {
            ITEM_TYPE_COMMODITY -> {
                mItemCommodity.visibility = View.VISIBLE
                val name = typedArray.getString(R.styleable.CommonItemView_itemName)
                val priceIn = typedArray.getFloat(R.styleable.CommonItemView_itemPriceIn, 0f)
                val priceOut = typedArray.getFloat(R.styleable.CommonItemView_itemPriceOut, 0f)
                val num = typedArray.getInteger(R.styleable.CommonItemView_itemNum, 0)
                initItem(name ?: "", priceIn, priceOut, num)
            }
            ITEM_TYPE_ORDER_DATE -> {
                mItemWithDate.visibility = View.VISIBLE
                val date = typedArray.getString(R.styleable.CommonItemView_itemDate)
                val priceAll = typedArray.getFloat(R.styleable.CommonItemView_itemPriceAll, 0f)
                val inCome = typedArray.getFloat(R.styleable.CommonItemView_itemInCome, 0f)
                initItem(date ?: "", priceAll, inCome)
            }
            ITEM_TYPE_ORDER_CLIENT -> {
                mItemClient.visibility = View.VISIBLE
                val client = typedArray.getString(R.styleable.CommonItemView_itemClient)
                val price = typedArray.getFloat(R.styleable.CommonItemView_itemPrice, 0f)
                initItem(client ?: "", price)
            }
            ITEM_TYPE_ADD -> {
                mItemAdd.visibility = View.VISIBLE
            }
            else -> {
                throw Exception("unknown exception!!!")
            }
        }
        typedArray.recycle()
    }

    /**
     * 初始化商品列表
     */
    fun initItem(name: String, priceIn: Float, priceOut: Float, num: Int) {
        setName(name)
        setPriceIn(priceIn)
        setPriceOut(priceOut)
        setNum(num)
    }

    fun setName(name: String) {
        mItemName.text = name
    }

    fun setPriceIn(priceIn: Float) {
        val text = context.getString(R.string.price_in) + priceIn
        mItemIn.text = text
    }

    fun setPriceOut(priceOut: Float) {
        val text = context.getString(R.string.price_out) + priceOut
        mItemOut.text = text
    }

    fun setNum(num: Int) {
        val text = context.getString(R.string.num) + num
        mItemNum.text = text
    }

    /**
     * 初始化日期列表
     */
    fun initItem(date: String, priceAll: Float, inCome: Float) {
        setDate(date)
        setPriceAll(priceAll)
        setInCome(inCome)
    }

    fun setDate(date: String) {
        mItemDate.text = date
    }

    fun setPriceAll(priceAll: Float) {
        val text = context.getString(R.string.order_price_all) + priceAll
        mItemPriceAll.text = text
    }

    fun setInCome(inCome: Float) {
        val text = context.getString(R.string.order_income) + inCome
        mItemInCome.text = text
    }

    /**
     * 初始化客户列表
     */
    fun initItem(clientName: String, price: Float) {
        setClientName(clientName)
        setPrice(price)
    }

    fun setClientName(clientName: String) {
        mItemClientName.text = clientName
    }

    fun setPrice(price: Float) {
        val text = context.getString(R.string.order_price) + price
        mItemPrice.text = text
    }

    /**
     * 更改item展示
     */
    fun changeType(type: String) {
        mItemCommodity.visibility = View.GONE
        mItemWithDate.visibility = View.GONE
        mItemClient.visibility = View.GONE
        mItemAdd.visibility = View.GONE
        when (type) {
            ITEM_TYPE_COMMODITY -> {
                mItemCommodity.visibility = View.VISIBLE
            }
            ITEM_TYPE_ORDER_DATE -> {
                mItemWithDate.visibility = View.VISIBLE
            }
            ITEM_TYPE_ORDER_CLIENT -> {
                mItemClient.visibility = View.VISIBLE
            }
            ITEM_TYPE_ADD -> {
                mItemAdd.visibility = View.VISIBLE
            }
            else -> {
            }
        }
    }

}
