package com.alex.customlayout

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide


class CustomLayout : ViewGroup {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle)

    private var layoutWidth = 0
    private var layoutHeight = 0

    private var itemTop = 0
    private var itemLeft = 0
    private var itemRight = 0
    private var itemBottom = 0

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        layoutWidth = MeasureSpec.getSize(widthMeasureSpec)
        layoutHeight = MeasureSpec.getSize(heightMeasureSpec)
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        val childCount = childCount
        var child: View
        for (i in 0 until childCount) {
            child = getChildAt(i)
            val layoutParams = child.layoutParams as CustomLayoutParams
            when (childCount) {
                1 -> oneItem()
                2 -> twoItems(i)
                3 -> threeItems(i)
                4 -> fourItems(i)
                5 -> fiveItems(i)
            }
            child.layout(itemLeft, itemTop, itemRight, itemBottom)
            Glide.with(child)
                    .load(layoutParams.picture)
                    .into((child as ImageView))
        }
    }

    private fun fiveItems(position: Int) {
        when (position) {
            0 -> {
                itemTop = paddingTop
                itemLeft = paddingStart
                itemRight = layoutWidth / 2 - paddingRight / 2
                itemBottom = layoutHeight / 3 - paddingBottom / 2
            }
            1 -> {
                itemTop = paddingTop
                itemLeft = layoutWidth / 2 + paddingRight / 2
                itemRight = layoutWidth - paddingRight
                itemBottom = layoutHeight / 3 - paddingBottom / 2
            }
            2 -> {
                itemTop = layoutHeight / 3 + paddingBottom / 2
                itemLeft = paddingStart
                itemRight = layoutWidth / 2 - paddingRight / 2
                itemBottom = layoutHeight / 3 * 2 - paddingBottom / 2
            }
            3 -> {
                itemTop = layoutHeight / 3 + paddingBottom / 2
                itemLeft = layoutWidth / 2 + paddingRight / 2
                itemRight = layoutWidth - paddingRight
                itemBottom = layoutHeight / 3 * 2 - paddingBottom / 2
            }
            4 -> {
                itemTop = layoutHeight / 3 * 2 + paddingBottom / 2
                itemLeft = paddingStart
                itemRight = layoutWidth - paddingRight
                itemBottom = layoutHeight - paddingBottom
            }
        }
    }

    private fun fourItems(position: Int) {
        when (position) {
            0 -> {
                itemTop = paddingTop
                itemLeft = paddingStart
                itemRight = layoutWidth / 2 - paddingRight / 2
                itemBottom = layoutHeight / 3 - paddingBottom / 2
            }
            1 -> {
                itemTop = paddingTop
                itemLeft = layoutWidth / 2 + paddingRight / 2
                itemRight = layoutWidth - paddingRight
                itemBottom = layoutHeight / 3 - paddingBottom / 2
            }
            2 -> {
                itemTop = layoutHeight / 3 + paddingBottom / 2
                itemLeft = paddingStart
                itemRight = layoutWidth - paddingRight
                itemBottom = layoutHeight / 3 * 2 - paddingBottom / 2
            }
            3 -> {
                itemTop = layoutHeight / 3 * 2 + paddingBottom / 2
                itemLeft = paddingStart
                itemRight = layoutWidth - paddingRight
                itemBottom = layoutHeight - paddingBottom
            }
        }
    }

    private fun threeItems(position: Int) {
        when (position) {
            0 -> {
                itemTop = paddingTop
                itemLeft = paddingStart
                itemRight = layoutWidth - paddingRight
                itemBottom = layoutHeight / 2
            }
            1 -> {
                itemTop = paddingTop + layoutHeight / 2
                itemLeft = paddingStart
                itemRight = layoutWidth / 2 - paddingRight / 2
                itemBottom = layoutHeight - paddingBottom
            }
            2 -> {
                itemTop = paddingTop + layoutHeight / 2
                itemLeft = layoutWidth / 2 + paddingStart / 2
                itemRight = layoutWidth - paddingRight
                itemBottom = layoutHeight - paddingBottom
            }
        }
    }

    private fun twoItems(position: Int) {
        when (position) {
            0 -> {
                itemTop = paddingTop
                itemLeft = paddingStart
                itemRight = layoutWidth - paddingRight
                itemBottom = layoutHeight / 2
            }
            1 -> {
                itemTop = paddingTop + layoutHeight / 2
                itemLeft = paddingStart
                itemRight = layoutWidth - paddingRight
                itemBottom = layoutHeight - paddingBottom
            }
        }
    }

    private fun oneItem() {
        itemTop = paddingTop
        itemLeft = paddingStart
        itemRight = layoutWidth - paddingRight
        itemBottom = layoutHeight - paddingBottom
    }

    override fun generateLayoutParams(attrs: AttributeSet): ViewGroup.LayoutParams { //set LayoutParams to all children
        return CustomLayoutParams(context, attrs)
    }

    override fun generateLayoutParams(p: LayoutParams): LayoutParams {
        return CustomLayoutParams(p)
    }

    override fun generateDefaultLayoutParams(): LayoutParams {
        return CustomLayoutParams()
    }

    override fun checkLayoutParams(p: LayoutParams?): Boolean {
        return p is CustomLayoutParams
    }

    class CustomLayoutParams : ViewGroup.LayoutParams {

        var picture: String? = null

        constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
            val a = context.obtainStyledAttributes(attrs, R.styleable.CustomLayout)
            picture = a.getString(R.styleable.CustomLayout_picture)
            a.recycle()
        }

        constructor(params: ViewGroup.LayoutParams) : super(params) {
            if (params is CustomLayoutParams) {
                picture = params.picture
                height = params.height
                width = params.width
            }
        }

        @JvmOverloads constructor(width: Int = ViewGroup.LayoutParams.MATCH_PARENT,
                                  height: Int = ViewGroup.LayoutParams.MATCH_PARENT) : super(width, height)
    }
}
