package loveq.com.customview.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by rc on 2018/2/12.
 * Description:
 * 通过getDimensionPixelSize获取配置在xml中的sp值
 * 通过TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
 * DEFAULT_TEXT_SIZE_SP, getResources().getDisplayMetrics())获取sp值
 */

public class LetterSideBar extends View {

    private Paint mPaint;
    private int mCurrentPosition = -1;
    private LetterTouchListener mLetterTouchListener;


    public static String[] mLetters = {"A", "B", "C", "D", "E", "F", "G", "H", "I",
            "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
            "W", "X", "Y", "Z", "#"};
    private String mLetter;
    private Paint mSelectedPaint;


    public LetterSideBar(Context context) {
        this(context, null);
    }

    public LetterSideBar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LetterSideBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    private void initPaint() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setDither(true);
        mPaint.setColor(Color.BLUE);
        float textSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 14,
                getResources().getDisplayMetrics());
        mPaint.setTextSize(textSize);

        mSelectedPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mSelectedPaint.setColor(Color.RED);
        mSelectedPaint.setTextSize(textSize);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//        float textWidth = mPaint.measureText("M");
//        int width = (int) (getPaddingLeft() + getPaddingRight() + textWidth);
//        int height = MeasureSpec.getSize(heightMeasureSpec);
//        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int parentHeight = getHeight() - getPaddingBottom() - getPaddingTop();
        int itemHeight = parentHeight / mLetters.length;
        Paint.FontMetricsInt metrics = mPaint.getFontMetricsInt();
        for (int i = 0; i < mLetters.length; i++) {
            int baseLine = itemHeight * i + itemHeight / 2 + (metrics.bottom - metrics.top) / 2
                    - metrics.bottom + getPaddingTop();
            if (i == mCurrentPosition) {
                canvas.drawText(mLetters[i], 0, baseLine, mSelectedPaint);
            } else {
                canvas.drawText(mLetters[i], 0, baseLine, mPaint);
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                float moveY = event.getY();
                int height = getHeight() - getPaddingTop() - getPaddingBottom();
                int itemHeight = height / mLetters.length;
                int currentPosition = (int) (moveY / itemHeight);

                currentPosition = currentPosition < 0 ? 0 : currentPosition;
                currentPosition = currentPosition > mLetters.length - 1 ?
                        mLetters.length - 1 : currentPosition;

                if (mCurrentPosition != currentPosition) {
                    mLetter = mLetters[currentPosition];
                    mCurrentPosition = currentPosition;
                    if (mLetterTouchListener != null) {
                        mLetterTouchListener.onTouch(mLetter, false);
                    }
                    invalidate();
                }

                break;
            case MotionEvent.ACTION_UP:
                if (mLetterTouchListener != null) {
                    mLetterTouchListener.onTouch(mLetter, true);
                }
                break;

        }
        return true;
    }

    public interface LetterTouchListener {
        void onTouch(String letter, boolean isLeave);
    }

    public void setLetterTouchListener(LetterTouchListener letterTouchListener) {
        mLetterTouchListener = letterTouchListener;
    }
}
