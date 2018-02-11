package loveq.com.customview.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;

import loveq.com.customview.R;

/**
 * Created by rc on 2018/2/9.
 * Description:
 */

public class ColorTrackTextView extends TextView {

    private int mTrackColor;
    private int mOriginColor;
    private Paint mTrackPaint;
    private Paint mOriginPaint;
    private int mCurrentProgress;

    public ColorTrackTextView(Context context) {
        this(context, null);
    }

    public ColorTrackTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ColorTrackTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint(context, attrs);
    }

    private void initPaint(Context context, AttributeSet attrs) {
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.ColorTrackTextView);
        mTrackColor = array.getColor(R.styleable.ColorTrackTextView_track_textColor, getTextColors().getDefaultColor());
        mOriginColor = getTextColors().getDefaultColor();
        mTrackPaint = getPaintByColor(mTrackColor);
        mOriginPaint = getPaintByColor(mOriginColor);
        array.recycle();
    }

    private Paint getPaintByColor(int color) {
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(color);
        paint.setDither(true);
        paint.setTextSize(getTextSize());
        return paint;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //view的onDraw是空实现，但是TextView的不是。所以我们需要自定义TextView的onDraw
        // super.onDraw(canvas);
        int distant = getWidth() * mCurrentProgress;
        drawText(canvas, mTrackPaint, 0, distant);
        drawText(canvas, mOriginPaint, distant, getWidth());
    }

    private void drawText(Canvas canvas, Paint paint, int left, int right) {
        //因为要对画布进行剪裁，所以先进行备份
        canvas.save();
        RectF rectF = new RectF(left, 0, right, getHeight());
        canvas.clipRect(rectF);
        String content = getText().toString();
        Rect bounds = new Rect();
        paint.getTextBounds(content, 0, content.length(), bounds);
        int textWidth = getWidth() / 2 - bounds.width() / 2;

        Paint.FontMetricsInt fontMetricsInt = paint.getFontMetricsInt();
        int dy = (fontMetricsInt.bottom - fontMetricsInt.top) / 2 - fontMetricsInt.bottom;
        int baseline = getHeight() / 2 + dy;
        canvas.drawText(content, textWidth, baseline, paint);
        canvas.restore();
    }
}
