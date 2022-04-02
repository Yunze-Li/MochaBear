package com.arctos.mochabear.animationdemo

import android.content.Context
import android.graphics.Canvas
import androidx.appcompat.widget.AppCompatTextView

class CustomView(context: Context) : AppCompatTextView(context) {

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
    }

    override fun setText(text: CharSequence?, type: BufferType?) {
        super.setText("frfr", type)
    }
}