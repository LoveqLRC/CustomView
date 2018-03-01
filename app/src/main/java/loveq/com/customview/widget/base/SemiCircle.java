package loveq.com.customview.widget.base;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import loveq.com.customview.R;

/**
 * Created by rc on 2018/2/28.
 * Description:
 */

public class SemiCircle extends View {
    private static final int DEFAULT_COLOR = Color.BLACK;
    private static final int DEFAULT_DIRECTION = Direction.BOTTOM;
    private int mCircleColor;
    private @Direction
    int mDirection;
    private Paint mPaint;

    public SemiCircle(Context context) {
        this(context, null);
    }

    public SemiCircle(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SemiCircle(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(context, attrs);
        initPaint();
    }


    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.SemiCircle);
        mCircleColor = array.getColor(R.styleable.SemiCircle_semi_color, DEFAULT_COLOR);
        mDirection = array.getInt(R.styleable.SemiCircle_semi_direction, DEFAULT_DIRECTION);
        array.recycle();
    }

    private void initPaint() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(mCircleColor);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        switch (mDirection) {
            case Direction.TOP:
            case Direction.BOTTOM:
                height = width / 2;
                break;
            case Direction.LEFT:
            case Direction.RIGHT:
                width = height / 2;
                break;
        }
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        float cx = getWidth() / 2, cy = 0;
        switch (mDirection) {
            case Direction.TOP:
                cx = getWidth() / 2;
                cy = 0;
                break;
            case Direction.BOTTOM:
                cx = getWidth() / 2;
                cy = getHeight();
                break;
            case Direction.LEFT:
                cx = 0;
                cy = getHeight() / 2;
                break;
            case Direction.RIGHT:
                cx = getWidth();
                cy = getHeight() / 2;
                break;
        }
        canvas.drawCircle(cx, cy, getWidth() / 2, mPaint);
    }


    @IntDef({Direction.TOP, Direction.BOTTOM, Direction.LEFT, Direction.RIGHT})
    @Retention(RetentionPolicy.SOURCE)
    @interface Direction {
        int TOP = 0;
        int BOTTOM = 1;
        int LEFT = 2;
        int RIGHT = 3;
    }
}
