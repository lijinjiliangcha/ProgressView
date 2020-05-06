package com.lijinjiliangcha.progressview

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View


class ProgressView : View {

    var bgColor: Int
        set(value) {
            field = value
            bgPaint.color = value
            invalidate()
        }
    var proColor: Int
        set(value) {
            field = value
            proPaint.color = value
            invalidate()
        }

    private val bgPaint: Paint
    private val proPaint: Paint

    private var rect: RectF? = null
    private var path: Path? = null


    var progress: Float = 0f
        set(value) {
            if (value < 0)
                field = 0f
            else if (value > 1)
                field = 1f
            else {
                field = value

            }
            invalidate()
        }

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {

        bgPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        proPaint = Paint(Paint.ANTI_ALIAS_FLAG)

        val array = context.obtainStyledAttributes(attrs, R.styleable.ProgressView)
        bgColor = array.getColor(R.styleable.ProgressView_bgColor, 0xFFF9F1D1.toInt())
        proColor = array.getColor(R.styleable.ProgressView_proColor, 0xFFF8D9A2.toInt())
        progress = array.getFloat(R.styleable.ProgressView_progress, 0f)
        array.recycle()


//        bgPaint.color = bgColor
//
//        proPaint.color = proColor
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        rect = RectF(0f, 0f, w.toFloat(), h.toFloat())
        onArc(w, h)
    }

    override fun onDraw(canvas: Canvas) {
        canvas.clipPath(path!!)
        canvas.drawRect(rect!!, bgPaint)
        canvas.drawRect(0f, 0f, width.toFloat() * progress, height.toFloat(), proPaint)
    }

    private fun onArc(w: Int, h: Int) {
        val radius = (if (w > h) h.toFloat() / 2 else w.toFloat() / 2) + 0.5f
        path = Path()
        path?.let {
            it.moveTo(0f, radius)
            val lF = RectF(0f, 0f, 2 * radius, h.toFloat())
            it.arcTo(lF, 90f, 180f, false)
            val rF = RectF(w - 2 * radius, 0f, w.toFloat(), h.toFloat())
            it.arcTo(rF, 270f, 180f, false)
            it.lineTo(radius, h.toFloat())
        }
    }

}