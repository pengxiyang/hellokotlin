package com.test.hellokotlin.view

import android.content.Context
import androidx.viewpager.widget.ViewPager
import android.util.AttributeSet
import android.view.MotionEvent

/*
 *自定义不会滑动的viewpager
 **/
class NoScrollViewPager : ViewPager {
    constructor(context: Context?) : super(context!!) {}
    constructor(context: Context?, attrs: AttributeSet?) : super(context!!, attrs) {}

    override fun onTouchEvent(arg0: MotionEvent): Boolean {
        return false
    }

    override fun onInterceptTouchEvent(arg0: MotionEvent): Boolean {
        return false
    }
}