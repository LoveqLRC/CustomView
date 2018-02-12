package loveq.com.customview.widget.baseapi;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by rc on 2018/2/11.
 * Description:
 * measureText 代表绘制字符串所占据的大小
 * TextBounds 包裹字符串的最小矩形
 */

public class TextBoundsView extends View {

    private Paint mPaint;

    public TextBoundsView(Context context) {
        this(context, null);
    }

    public TextBoundsView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TextBoundsView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    private void initPaint() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setTextSize(36);
        mPaint.setColor(Color.BLUE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        String text = "loveqrc";

        Paint.FontMetricsInt mPaintFontMetrics = mPaint.getFontMetricsInt();
        int baseLine = getHeight() / 2 + (mPaintFontMetrics.bottom - mPaintFontMetrics.top) / 2
                - mPaintFontMetrics.bottom;

        //画text所占用的区域 measureText
        int top = baseLine + mPaintFontMetrics.top;
        int bottom = baseLine + mPaintFontMetrics.bottom;
        int width = (int) mPaint.measureText(text);
        Rect rect = new Rect(0, top, width, bottom);
        mPaint.setColor(Color.RED);
        canvas.drawRect(rect, mPaint);

        //画最小矩形 TextBounds
        Rect minRect = new Rect();
        mPaint.getTextBounds(text, 0, text.length(), minRect);
        minRect.top = baseLine + minRect.top;
        minRect.bottom = baseLine + minRect.bottom;
        mPaint.setColor(Color.GREEN);
        canvas.drawRect(minRect, mPaint);

        //画文字
        mPaint.setColor(Color.BLUE);
        canvas.drawText(text, 0, baseLine, mPaint);

    }
}
