package loveq.com.customview.widget.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Point
import android.util.AttributeSet
import android.view.View

/**
 * Author：Rc
 * 0n 2018/2/19 09:44
 */
class LockPatternView : View {
    private var mPoints: Array<Array<Point?>> = Array(3) { Array<Point?>(3, { null }) }

    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) :
            super(context, attrs, defStyleAttr) {
        initDot()
        initPaint()
    }

    private fun initDot() {
        var offsetX = 0
        var offsetY = 0
        if (height > width) {
            offsetY = (height - width) / 2
        }

    }

    private fun initPaint() {

    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
    }
}