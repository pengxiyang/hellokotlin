package com.test.hellokotlin.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Paint.Cap
import android.graphics.RectF
import android.graphics.Typeface
import android.util.AttributeSet
import com.test.hellokotlin.R

class RoundProgressBarWidthNumber @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : HorizontalProgressBarWithNumber(context, attrs!!) {
    /**
     * mRadius of view
     */
    private var mRadius = dp2px(30)
    @Synchronized
    override fun onMeasure(
        widthMeasureSpec: Int,
        heightMeasureSpec: Int
    ) {
        var widthMeasureSpec = widthMeasureSpec
        var heightMeasureSpec = heightMeasureSpec
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)
        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val paintWidth = Math.max(
            mReachedProgressBarHeight,
            mUnReachedProgressBarHeight
        )
        if (heightMode != MeasureSpec.EXACTLY) {
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(
                (paddingTop + paddingBottom
                        + mRadius * 2 + paintWidth),
                MeasureSpec.EXACTLY
            )
        }
        if (widthMode != MeasureSpec.EXACTLY) {
            widthMeasureSpec = MeasureSpec.makeMeasureSpec(
                (paddingLeft + paddingRight
                        + mRadius * 2 + paintWidth),
                MeasureSpec.EXACTLY
            )
        }
        super.onMeasure(heightMeasureSpec, heightMeasureSpec)
    }

    @Synchronized
    override fun onDraw(canvas: Canvas) {
        val text = "$progress%"
        // mPaint.getTextBounds(text, 0, text.length(), mTextBound);
        val textWidth = mPaint.measureText(text)
        val textHeight = (mPaint.descent() + mPaint.ascent()) / 2
        canvas.save()
        canvas.translate(paddingLeft.toFloat(), paddingTop.toFloat())
        mPaint.style = Paint.Style.STROKE
        // draw unreaded bar
        mPaint.color = mUnReachedBarColor
        mPaint.strokeWidth = mUnReachedProgressBarHeight.toFloat()
        canvas.drawCircle(mRadius.toFloat(), mRadius.toFloat(), mRadius.toFloat(), mPaint)
        // draw reached bar
        mPaint.color = mReachedBarColor
        mPaint.strokeWidth = mReachedProgressBarHeight.toFloat()
        val sweepAngle = progress * 1.0f / max * 360
        canvas.drawArc(
            RectF(0f, 0f, (mRadius * 2).toFloat(), (mRadius * 2).toFloat()), 0f,
            sweepAngle, false, mPaint
        )
        // draw text
        mPaint.style = Paint.Style.FILL
        mPaint.typeface = Typeface.DEFAULT_BOLD
        mPaint.color = mTextColor
        canvas.drawText(
            text, mRadius - textWidth / 2, mRadius - textHeight,
            mPaint
        )
        canvas.restore()
    }

    init {

        //   mReachedProgressBarHeight = (int) (mUnReachedProgressBarHeight * 2.5f);
        val ta = context.obtainStyledAttributes(
            attrs,
            R.styleable.RoundProgressBarWithNumber
        )
        mRadius = ta.getDimension(
            R.styleable.RoundProgressBarWithNumber_radius, mRadius.toFloat()
        ).toInt()
        ta.recycle()
        val mTextSize = sp2px(20)
        mPaint.style = Paint.Style.STROKE
        mPaint.isAntiAlias = true
        mPaint.isDither = true
        mPaint.strokeCap = Cap.ROUND
    }
}