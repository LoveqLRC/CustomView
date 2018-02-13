package loveq.com.customview.widget.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewParent;
import android.widget.OverScroller;

import loveq.com.customview.R;

/**
 * Created by rc on 2018/2/13.
 * Description:
 */

public class RulerView extends View {
    public static final int HORIZONTAL = 0;
    public static final int VERTICAL = 1;

    public static final int TOP = 0;
    public static final int BOTTOM = 1;


//    @IntDef({HORIZONTAL, VERTICAL})
//    @Retention(RetentionPolicy.SOURCE)
//    public @interface OrientationMode {
//    }

    private static final boolean DEFAULT_IS_DRAW_TEXT = true;
    private static final int DEFAULT_INDICATE_COLOR = Color.BLACK;
    private static final int DEFAULT_TEXT_COLOR = Color.BLACK;
    private static final int DEFAULT_TEXT_SIZE = 12;
    private static final int DEFAULT_ORIENTATION = HORIZONTAL;
    private static final int DEFAULT_GRAVITY = BOTTOM;
    private static final int DEFAULT_START_INDEX = 0;
    private static final int DEFAULT_END_INDEX = 100;

    private static final int DEFAULT_INDICATE_WIDTH = 12;
    private static final int DEFAULT_INDICATE_HEIGHT = 24;

    private static final int DEFAULT_SMALL_INDICATE_WIDTH = 10;
    private static final int DEFAULT_SMALL_INDICATE_HEIGHT = 10;

    private static final int DEFAULT_SMALL_INDICATE_COUNT = 4;
    private static final int DEFAULT_INDICATE_PADDING = 12;

    private static final int DEFAULT_INDICATE_MARGIN_TEXT = 16;
    private static final int DEFAULT_SMALL_INDICATE_COLOR = Color.YELLOW;
    private static final boolean DEFAULT_IS_SKIP_SMALL_INDICATE = true;
    private static final boolean DEFAULT_IS_AUTO_ADJUST = true;
    private static final int DEFAULT_DISABLE_INDEX = -1;
    private static final int DEFAULT_DISABLE_COLOR = Color.parseColor("#919191");
    private static final int INVALID_POINTER = -1;
    public int mIndicateColor;
    public int mTextColor;
    RulerViewScrollListener mScrollListener;
    private int mTextSize;
    private int mOrientation;
    private int mGravity;
    private int mStartIndex;
    private int mEndIndex;
    private int mIndicateWidth;
    private int mIndicatePadding;
    private int mIndicateMarginText;//指示器距离文字的距离 default 16dp
    private int mIndicateHeight;
    private int mSmallIndicateWidth;
    private int mSmallIndicateHeight;
    private int mSmallIndicateCount;
    private Paint mIndicatePaint;
    private int mSmallIndicateColor;
    private Paint mTextPaint;
    private Paint mSmallIndicatePaint;
    private int mTouchSlop;
    private OverScroller mScroller;
    private boolean mIsDrawText;//是否绘制文字 default true
    private int mRulerViewWidth;
    private int mRulerViewHeight;
    private boolean mIsScroll;
    /**
     * 上一次motion event 位置
     */
    private int mLastMotionX;
    private int mLastMotionY;
    private int mInitialMotionX;
    private int mInitialMotionY;
    private VelocityTracker mVelocityTracker;
    private int mMinimumVelocity;
    private int mMaximumVelocity;
    private int mOverscrollDistance;
    private int mOverflingDistance;
    private boolean mIsBeingDragged;
    private int mActivePointerId;
    private int mDrawTextHeight;//待绘制的文本高度
    private String[] mTextContent;//待绘制的文本数组
    private boolean mIsSkipSmallIndicate;//绘制文本的时候是否跳过小指示器
    private boolean mAutoAdjust;
    private int mDisableIndex;
    private int mDisableColor;

    public RulerView(Context context) {
        this(context, null);
    }

    public RulerView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RulerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initRulerView();
        initAttrs(context, attrs);
        initPaint();
    }

    public int getSmallIndicateCount() {
        return mSmallIndicateCount + 1;
    }

    private void initRulerView() {

        mScroller = new OverScroller(getContext());
        ViewConfiguration configuration = ViewConfiguration.get(getContext());
        mTouchSlop = configuration.getScaledTouchSlop();
        mMinimumVelocity = configuration.getScaledMinimumFlingVelocity();
        mMaximumVelocity = configuration.getScaledMaximumFlingVelocity();
        mOverscrollDistance = configuration.getScaledOverscrollDistance();
        mOverflingDistance = configuration.getScaledOverflingDistance();
        setOverScrollMode(OVER_SCROLL_ALWAYS);

    }

    private void initAttrs(Context context, AttributeSet attrs) {

        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.RulerView);
        mIndicateColor = array.getColor(R.styleable.RulerView_indicateColor, DEFAULT_INDICATE_COLOR);
        mTextColor = array.getColor(R.styleable.RulerView_rulerTextColor, DEFAULT_TEXT_COLOR);
        mSmallIndicateColor = array.getColor(R.styleable.RulerView_smallIndicateColor, DEFAULT_SMALL_INDICATE_COLOR);
        mTextSize = array.getDimensionPixelSize(R.styleable.RulerView_rulerTextSize, DEFAULT_TEXT_SIZE);
        mOrientation = array.getInt(R.styleable.RulerView_orientation, DEFAULT_ORIENTATION);
        mGravity = array.getInt(R.styleable.RulerView_gravity, DEFAULT_GRAVITY);
        mStartIndex = array.getInt(R.styleable.RulerView_startIndex, DEFAULT_START_INDEX);
        mEndIndex = array.getInt(R.styleable.RulerView_endIndex, DEFAULT_END_INDEX);
        mIndicateWidth = array.getDimensionPixelSize(R.styleable.RulerView_indicateWidth, DEFAULT_INDICATE_WIDTH);
        mIndicateHeight = array.getDimensionPixelSize(R.styleable.RulerView_indicateHeight, DEFAULT_INDICATE_HEIGHT);
        mSmallIndicateWidth = array.getDimensionPixelSize(R.styleable.RulerView_smallIndicateWidth, DEFAULT_SMALL_INDICATE_WIDTH);
        mSmallIndicateHeight = array.getDimensionPixelSize(R.styleable.RulerView_smallIndicateHeight, DEFAULT_SMALL_INDICATE_HEIGHT);
        mIndicatePadding = array.getDimensionPixelSize(R.styleable.RulerView_indicatePadding, DEFAULT_INDICATE_PADDING);
        mIndicateMarginText = array.getDimensionPixelSize(R.styleable.RulerView_indicateMarginText, DEFAULT_INDICATE_MARGIN_TEXT);
        mSmallIndicateCount = array.getInt(R.styleable.RulerView_smallIndicateCount, DEFAULT_SMALL_INDICATE_COUNT);
        mIsDrawText = array.getBoolean(R.styleable.RulerView_isDrawText, DEFAULT_IS_DRAW_TEXT);
        mIsSkipSmallIndicate = array.getBoolean(R.styleable.RulerView_isSkipSmallIndicate, DEFAULT_IS_SKIP_SMALL_INDICATE);
        mAutoAdjust = array.getBoolean(R.styleable.RulerView_isAutoAdjust, DEFAULT_IS_AUTO_ADJUST);
        mDisableIndex = array.getInt(R.styleable.RulerView_disableIndex, DEFAULT_DISABLE_INDEX);
        mDisableColor = array.getColor(R.styleable.RulerView_disableColor, DEFAULT_DISABLE_COLOR);
        int textArrayId = array.getResourceId(R.styleable.RulerView_rulerTextArray, 0);

        if (textArrayId != 0) {
            mTextContent = getResources().getStringArray(textArrayId);
            if (mIsSkipSmallIndicate) {
                mStartIndex = 0;
                mEndIndex = mSmallIndicateCount * mTextContent.length + mTextContent.length;
            } else {
                mStartIndex = 0;
                mEndIndex = mTextContent.length;
            }
        } else {
            mTextContent = new String[]{};
        }

        array.recycle();
    }

    private void initPaint() {
        mIndicatePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mIndicatePaint.setStyle(Paint.Style.FILL);
        mIndicatePaint.setColor(mIndicateColor);

        mSmallIndicatePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mSmallIndicatePaint.setStyle(Paint.Style.FILL);
        mSmallIndicatePaint.setColor(mSmallIndicateColor);

        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setStyle(Paint.Style.FILL);
        mTextPaint.setTextAlign(Paint.Align.CENTER);
        mTextPaint.setTextSize(mTextSize);
        mTextPaint.setColor(mTextColor);
        Paint.FontMetrics metrics = mTextPaint.getFontMetrics();
        mDrawTextHeight = (int) Math.ceil(Math.ceil(metrics.descent - metrics.top));
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mRulerViewWidth = w;
        mRulerViewHeight = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        drawIndicate(canvas);
        drawIndicateText(canvas);
    }

    private void drawIndicate(Canvas canvas) {
        for (int i = 0; i < mEndIndex - mStartIndex; i++) {
            if (i % (mSmallIndicateCount + 1) == 0) {
                selectPaintColor(i);
                //画长指示器
                int index = i / (mSmallIndicateCount + 1);
                //中间短指示器的总长度
                int midSmallIndicateWidth = (mSmallIndicateWidth + mIndicatePadding) * mSmallIndicateCount;

                int startIndicateWidth = mIndicateWidth + mIndicatePadding;

                int left = (midSmallIndicateWidth + startIndicateWidth) * index + mIndicatePadding / 2;
                int right = (midSmallIndicateWidth + startIndicateWidth) * (index + 1)
                        - mIndicatePadding / 2 - midSmallIndicateWidth;

                if (mOrientation == HORIZONTAL && mGravity == BOTTOM) {
                    canvas.drawRect(left, 0, right, mIndicateHeight, mIndicatePaint);
                }

                if (mOrientation == HORIZONTAL && mGravity == TOP) {
                    canvas.drawRect(left, mRulerViewHeight - mIndicateHeight, right,
                            mRulerViewHeight, mIndicatePaint);
                }

                if (mOrientation == VERTICAL && mGravity == BOTTOM) {
                    canvas.drawRect(0, left, mIndicateHeight, right, mIndicatePaint);
                }

                if (mOrientation == VERTICAL && mGravity == TOP) {
                    canvas.drawRect(mRulerViewWidth - mIndicateHeight, left, mRulerViewWidth, right, mIndicatePaint);
                }


            } else {
                selectPaintColor(i);
                //画短指示器
                int index = i / (mSmallIndicateCount + 1);
                int innerIndex = i % (mSmallIndicateCount + 1);
                //中间短指示器的总长度
                int midSmallIndicateWidth = (mSmallIndicateWidth + mIndicatePadding) * mSmallIndicateCount;
                int startIndicateWidth = mIndicateWidth + mIndicatePadding;
                int left = (midSmallIndicateWidth + startIndicateWidth) * index +
                        mIndicatePadding + mIndicateWidth +
                        (mIndicatePadding + mSmallIndicateWidth) * (innerIndex - 1) + mIndicatePadding / 2;
                int right = (midSmallIndicateWidth + startIndicateWidth) * index +
                        mIndicatePadding + mIndicateWidth +
                        (mIndicatePadding + mSmallIndicateWidth) * (innerIndex) - mIndicatePadding / 2;

                if (mOrientation == HORIZONTAL && mGravity == BOTTOM) {
                    canvas.drawRect(left, 0, right, mSmallIndicateHeight, mSmallIndicatePaint);
                }
                if (mOrientation == HORIZONTAL && mGravity == TOP) {
                    canvas.drawRect(left, mRulerViewHeight - mSmallIndicateHeight,
                            right, mRulerViewHeight, mSmallIndicatePaint);
                }

                if (mOrientation == VERTICAL && mGravity == BOTTOM) {
                    canvas.drawRect(0, left, mSmallIndicateHeight, right, mSmallIndicatePaint);
                }

                if (mOrientation == VERTICAL && mGravity == TOP) {
                    canvas.drawRect(mRulerViewWidth - mSmallIndicateHeight, left, mRulerViewWidth, right, mSmallIndicatePaint);
                }
            }
        }
    }

    private void selectPaintColor(int position) {
        if (position < mDisableIndex) {
            mTextPaint.setColor(mDisableColor);
            mIndicatePaint.setColor(mDisableColor);
            mSmallIndicatePaint.setColor(mDisableColor);
        } else {
            mTextPaint.setColor(mTextColor);
            mIndicatePaint.setColor(mIndicateColor);
            mSmallIndicatePaint.setColor(mSmallIndicateColor);
        }
    }

    private void drawIndicateText(Canvas canvas) {
        if (!mIsDrawText) {
            return;
        }
        for (int i = 0, text = mStartIndex; i < mEndIndex - mStartIndex; text++, i++) {
            if (i % (mSmallIndicateCount + 1) == 0) {
                selectPaintColor(i);
                int index = i / (mSmallIndicateCount + 1);
                if (mTextContent.length == 0) {
                    drawText(canvas, String.valueOf(text), index);
                } else {
                    drawText(canvas, mTextContent[index], index);
                }
            }
        }

//        for (int i = 0; i < mTextContent.length; i++) {
//            if (i % (mSmallIndicateCount + 1) == 0) {
//                int index = i / (mSmallIndicateCount + 1);
//                drawText(canvas, mTextContent[i], index);
//            }
//        }

    }

    private void drawText(Canvas canvas, String text, int index) {
        int midSmallIndicateWidth = (mSmallIndicateWidth + mIndicatePadding) * mSmallIndicateCount;
        int startIndicateWidth = mIndicateWidth + mIndicatePadding;
        if (mOrientation == HORIZONTAL) {
            mTextPaint.setTextAlign(Paint.Align.CENTER);
            int x = (midSmallIndicateWidth + startIndicateWidth) * index +
                    mIndicatePadding / 2 + mIndicateWidth / 2;
            Paint.FontMetrics metrics = mTextPaint.getFontMetrics();
            if (mGravity == BOTTOM) {
                int top = mIndicateHeight + mIndicateMarginText;
                canvas.drawText(text, x, top - metrics.top, mTextPaint);
            }
            if (mGravity == TOP) {
                int bottom = mRulerViewHeight - (mIndicateHeight + mIndicateMarginText);
                canvas.drawText(text, x, bottom - metrics.bottom, mTextPaint);
            }
        }

        if (mOrientation == VERTICAL) {
            mTextPaint.setTextAlign(Paint.Align.LEFT);
            int y = (midSmallIndicateWidth + startIndicateWidth) * index +
                    mIndicatePadding / 2 + mIndicateWidth / 2;
            Paint.FontMetrics metrics = mTextPaint.getFontMetrics();
            int baseLineY = (int) (y + (metrics.bottom - metrics.top) / 2 - metrics.bottom);
            if (mGravity == BOTTOM) {
                int x = mIndicateHeight + mIndicateMarginText;
                canvas.drawText(text, x, baseLineY, mTextPaint);
            }
            if (mGravity == TOP) {
                int textWidth = (int) mTextPaint.measureText(text);
                int x = mRulerViewWidth - (mIndicateHeight + mIndicateMarginText) - textWidth;
                canvas.drawText(text, x, baseLineY, mTextPaint);
            }

        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (mVelocityTracker == null) {
            mVelocityTracker = VelocityTracker.obtain();
        }
        mVelocityTracker.addMovement(event);

        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                if ((mIsBeingDragged = !mScroller.isFinished())) {
                    final ViewParent parent = getParent();
                    if (parent != null) {
                        parent.requestDisallowInterceptTouchEvent(true);
                    }
                }
                if (!mScroller.isFinished()) {
                    mScroller.abortAnimation();
                }

                mLastMotionX = mInitialMotionX = (int) event.getX();
                mLastMotionY = mInitialMotionY = (int) event.getY();
                mActivePointerId = event.getPointerId(0);
                break;
            case MotionEvent.ACTION_MOVE:
                if (!mIsBeingDragged) {
                    int pointerIndex = event.findPointerIndex(mActivePointerId);
                    if (pointerIndex == -1) {
                        resetTouch();
                        break;
                    }
                    float x = event.getX(pointerIndex);
                    float xDiff = Math.abs(x - mLastMotionX);
                    float y = event.getY(pointerIndex);
                    float yDiff = Math.abs(y - mLastMotionY);
                    if (xDiff > mTouchSlop && xDiff > yDiff && mOrientation == HORIZONTAL) {
                        mIsBeingDragged = true;
                        //因为第一次拖动的距离必须大约mTouchSlop
                        //所以为了使拖动效果连续，这里补偿缺少的距离
                        mLastMotionX = x - mInitialMotionX > 0 ? mInitialMotionX + mTouchSlop :
                                mInitialMotionX - mTouchSlop;
                        if (getParent() != null) {
                            getParent().requestDisallowInterceptTouchEvent(true);
                        }
                    }
                    if (yDiff > mTouchSlop && yDiff > xDiff && mOrientation == VERTICAL) {
                        mIsBeingDragged = true;
                        mLastMotionY = y - mInitialMotionY > 0 ? mInitialMotionY + mTouchSlop :
                                mInitialMotionY - mTouchSlop;
                        if (getParent() != null) {
                            getParent().requestDisallowInterceptTouchEvent(true);
                        }
                    }

                }
                if (mIsBeingDragged) {
                    performDrag(event);
                }
                break;
            case MotionEvent.ACTION_UP:
                if (mIsBeingDragged) {
                    VelocityTracker velocityTracker = mVelocityTracker;
                    velocityTracker.computeCurrentVelocity(1000, mMaximumVelocity);
                    int initialVelocity = (int) velocityTracker.getXVelocity(mActivePointerId);
                    int yVelocity = (int) velocityTracker.getYVelocity(mActivePointerId);
                    if ((Math.abs(initialVelocity) > mMinimumVelocity)) {
                        if (mOrientation == HORIZONTAL) {
                            fling(-initialVelocity);
                        } else {
                            flingY(-yVelocity);
                        }

                    } else {
                        adjustIndicate();
                    }
                }
                endDrag();
                break;
            case MotionEvent.ACTION_CANCEL:
                endDrag();
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                int index = event.getActionIndex();

                mLastMotionX = mInitialMotionX = (int) event.getX(index);
                mLastMotionY = mInitialMotionY = (int) event.getY(index);
                mActivePointerId = event.getPointerId(index);
                break;
            case MotionEvent.ACTION_POINTER_UP:
                onSecondaryPointerUp(event);
                mLastMotionX = (int) event.getX(event.findPointerIndex(mActivePointerId));
                break;

            default:
                break;
        }
        return true;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int rulerHeight;
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        if (heightMode == MeasureSpec.UNSPECIFIED) {
            if (mIsDrawText) {
                rulerHeight = getPaddingBottom() + getPaddingTop()
                        + mIndicateHeight + mIndicateMarginText + mDrawTextHeight;
            } else {
                rulerHeight = getPaddingBottom() + getPaddingTop()
                        + mIndicateHeight;
            }
        } else {
            rulerHeight = heightSize;
        }
        setMeasuredDimension(widthSize, rulerHeight);

    }

    private void flingY(int i) {
        mScroller.fling(getScrollX(), getScrollY(),
                0, i, 0, 0, getMinScrollY(), getScrollRange() + getMaxOverScrollY());
        postInvalidate();
    }

    private void fling(int velocity) {
        if (mOrientation == HORIZONTAL) {
            mScroller.fling(getScrollX(), getScrollY(),
                    velocity, 0, getMinScrollX(), getScrollRange() + getMaxOverScrollX(), 0, 0);
        }
        if (mOrientation == VERTICAL) {
            mScroller.fling(getScrollX(), getScrollY(),
                    0, velocity, 0, 0, getMinScrollY(), getScrollRange() + getMaxOverScrollY());
        }
        postInvalidate();
    }

    private void resetTouch() {
        mActivePointerId = INVALID_POINTER;
        endDrag();
    }

    private void onSecondaryPointerUp(MotionEvent event) {
        final int pointerIndex = event.getActionIndex();
        final int pointerId = event.getPointerId(pointerIndex);
        //最后一个按下的pointer和松开的相等
        if (pointerId == mActivePointerId) {
            final int newPointerIndex = pointerIndex == 0 ? 1 : 0;
            mLastMotionX = mInitialMotionX = (int) event.getX(newPointerIndex);
            mLastMotionY = mInitialMotionY = (int) event.getY(newPointerIndex);
            mActivePointerId = event.getPointerId(newPointerIndex);
            if (mVelocityTracker != null) {
                mVelocityTracker.clear();
            }
        }
    }

    private void performDrag(MotionEvent event) {
        if (mOrientation == HORIZONTAL) {
            int activePointerIndex = event.findPointerIndex(mActivePointerId);
            int x = (int) event.getX(activePointerIndex);
            int deltaX = mLastMotionX - x;
            if (overScrollBy(deltaX, 0, getScrollX(), getScrollY(),
                    getScrollRange(), 0,
                    getMaxOverScrollX(), 0, true)) {
                mVelocityTracker.clear();
            }
            mLastMotionX = x;
        }
        if (mOrientation == VERTICAL) {
            int activePointerIndex = event.findPointerIndex(mActivePointerId);
            int y = (int) event.getY(activePointerIndex);
            int deltaY = mLastMotionY - y;
            if (overScrollBy(0, deltaY, getScrollX(), getScrollY(),
                    0, getScrollRange(), 0, getMaxOverScrollY(), true)) {
                mVelocityTracker.clear();
            }
            mLastMotionY = y;
        }

    }

    private void endDrag() {
        mIsBeingDragged = false;

        if (mVelocityTracker != null) {
            mVelocityTracker.recycle();
            mVelocityTracker = null;
        }
    }

    @Override
    protected void onOverScrolled(int scrollX, int scrollY, boolean clampedX, boolean clampedY) {
        if (!mScroller.isFinished()) {
            int oldX = getScrollX();
            int oldY = getScrollY();
            scrollTo(scrollX, scrollY);
            onScrollChanged(scrollX, scrollY, oldX, oldY);
        } else {
            super.scrollTo(scrollX, scrollY);
        }
    }

    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            int oldX = getScrollX();
            int oldY = getScrollY();
            int x = mScroller.getCurrX();
            int y = mScroller.getCurrY();
            if (mOrientation == HORIZONTAL) {
                overScrollBy(x - oldX, y - oldY, oldX, oldY, getScrollRange(), 0,
                        getMaxOverScrollX(), 0, false);
            } else {
                overScrollBy(x - oldX, y - oldY, oldX, oldY, 0, getScrollRange(),
                        0, getMaxOverScrollY(), false);
            }
            postInvalidate();
        } else if (!mIsBeingDragged && mAutoAdjust) {
            adjustIndicate();
        }
        if (mScrollListener != null) {
            mScrollListener.onScroll(getCurrentPosition());
        }
    }

    public void setDisableIndex(int disableIndex) {
        mDisableIndex = disableIndex;
        invalidate();
    }

    /**
     * 清除disable index
     */
    public void clearDisableIndex() {
        mDisableIndex = -1;
        invalidate();
    }

    private void adjustIndicate() {
        if (!mScroller.isFinished()) {
            mScroller.abortAnimation();
        }
        int currentPosition = getCurrentPosition();
        if (currentPosition < mDisableIndex) {
            currentPosition = mDisableIndex;
        }
        int scrollX = getScrollByPosition(currentPosition);
        if (scrollX != 0) {
            mScroller.startScroll(getScrollX(), getScrollY(), scrollX, 0);
            invalidate();
        }
    }

    private int getScrollByPosition(int position) {
        int currentX = position * (mIndicateWidth + mIndicatePadding);
        return -(getScrollX() + getMaxOverScrollX() - currentX);
    }


    /**
     * 根据当前滚动的距离获取所在的位置
     */
    private int getCurrentPosition() {
        //这里加mIndicatePadding / 2是为了判断当停留在padding区间时，是属于上一个还是下一个
        //如果不加，那么判断会是上一个
        int offsetX = getScrollX() + getMaxOverScrollX() + mIndicatePadding / 2;
        return offsetX / (mIndicateWidth + mIndicatePadding);
    }

    /**
     * 设置当前位置
     *
     * @param position 设置当前位置
     */
    public void setCurrentPosition(int position) {
        if (position < 0 || position > mEndIndex - 1) {
            throw new IndexOutOfBoundsException();
        }
        int scrollX = getScrollByPosition(position);
        if (scrollX != 0) {
            mScroller.startScroll(getScrollX(), getScrollY(), scrollX, 0);
            invalidate();
        }
    }

    /**
     * @return 获取滚动的范围
     */
    private int getScrollRange() {
        int indicateCount = mEndIndex - mStartIndex;
        int cellCount = indicateCount / (mSmallIndicateCount + 1);
        int extraCount = indicateCount % (mSmallIndicateCount + 1);
        int cellIndicateLength = cellCount * (mSmallIndicateCount * (mSmallIndicateWidth + mIndicatePadding) +
                mIndicateWidth + mIndicatePadding) + extraCount * (mSmallIndicateWidth + mIndicatePadding);
        return mOrientation == HORIZONTAL ? cellIndicateLength - mRulerViewWidth : cellIndicateLength - mRulerViewHeight;
    }

    /**
     * 设置滚动监听
     *
     * @param scrollListener
     */
    public void setScrollListener(RulerViewScrollListener scrollListener) {
        mScrollListener = scrollListener;
    }

    private int getMinScrollX() {
        return -getMaxOverScrollX();
    }

    private int getMinScrollY() {
        return -getMaxOverScrollY();
    }

    private int getMaxOverScrollX() {
        return mRulerViewWidth / 2 - mIndicatePadding / 2 - mIndicateWidth / 2;
    }

    private int getMaxOverScrollY() {
        return mRulerViewHeight / 2;
    }

    public interface RulerViewScrollListener {
        void onScroll(int position);
    }


}
