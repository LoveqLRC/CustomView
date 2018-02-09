package loveq.com.customview.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by rc on 2018/2/9.
 * Description:
 */

public class DrawTextView extends View {

    private Paint mPaint;

    public DrawTextView(Context context) {
        this(context, null);
    }

    public DrawTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DrawTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    private void initPaint() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setTextSize(160);
        mPaint.setColor(Color.BLACK);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int baseLineY = 200;
        canvas.drawText("loveqrc", 0, baseLineY, mPaint);
        Paint.FontMetrics fontMetrics = mPaint.getFontMetrics();
        float ascent = baseLineY + fontMetrics.ascent;
        float descent = baseLineY + fontMetrics.descent;
        float top = baseLineY + fontMetrics.top;
        float bottom = baseLineY + fontMetrics.bottom;

        //画基线
        mPaint.setColor(Color.RED);
        canvas.drawLine(0, baseLineY, getWidth(), baseLineY, mPaint);

        //画top
        mPaint.setColor(Color.YELLOW);
        canvas.drawLine(0, top, getWidth(), top, mPaint);

        //画ascent
        mPaint.setColor(Color.BLUE);
        canvas.drawLine(0, ascent, getWidth(), ascent, mPaint);

        //画descent
        mPaint.setColor(Color.RED);
        canvas.drawLine(0, descent, getWidth(), descent, mPaint);

        //画bottom
        mPaint.setColor(Color.YELLOW);
        canvas.drawLine(0, bottom, getWidth(), bottom, mPaint);
    }
}
