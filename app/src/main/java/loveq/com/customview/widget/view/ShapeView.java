package loveq.com.customview.widget.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import loveq.com.customview.R;

/**
 * Created by rc on 2018/2/13.
 * Description:
 */

public class ShapeView extends View {

    private Paint mPaint;
    private @Shape
    int mCurrentShape = Shape.CIRCLE;
    private Path mPath;

    public ShapeView(Context context) {
        this(context, null);
    }

    public ShapeView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ShapeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    private void initPaint() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setDither(true);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(Math.min(width, height), Math.min(width, height));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        switch (mCurrentShape) {
            case Shape.CIRCLE:
                int center = getWidth() / 2;
                mPaint.setColor(ContextCompat.getColor(getContext(), R.color.circle));
                canvas.drawCircle(center, center, center, mPaint);
                break;
            case Shape.SQUARE:
                mPaint.setColor(ContextCompat.getColor(getContext(), R.color.rect));
                canvas.drawRect(0, 0, getRight(), getBottom(), mPaint);
                break;
            case Shape.TRIANGLE:
                mPaint.setColor(ContextCompat.getColor(getContext(), R.color.triangle));
                if (mPath == null) {
                    mPath = new Path();
                    //设置下一次操作的起点
                    mPath.moveTo(0, getWidth());
                    mPath.lineTo(getWidth() / 2, 0);
                    mPath.lineTo(getWidth(), getWidth());
                    mPath.close();// 把路径闭合
                }
                canvas.drawPath(mPath, mPaint);
                break;
        }
    }


    public void changeShape() {
        switch (mCurrentShape) {
            case Shape.CIRCLE:
                mCurrentShape = Shape.SQUARE;
                break;
            case Shape.SQUARE:
                mCurrentShape = Shape.TRIANGLE;
                break;
            case Shape.TRIANGLE:
                mCurrentShape = Shape.CIRCLE;
                break;
        }
        invalidate();
    }

    public @Shape
    int getCurrentShape() {
        return mCurrentShape;
    }

    @IntDef({Shape.CIRCLE, Shape.SQUARE, Shape.TRIANGLE})
    @Retention(RetentionPolicy.RUNTIME)
    public @interface Shape {
        int CIRCLE = 0;
        int SQUARE = 1;
        int TRIANGLE = 2;
    }

}
