package loveq.com.customview.widget.plaid;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

import java.util.ArrayList;
import java.util.List;

import loveq.com.customview.R;
import loveq.com.customview.utils.AnimUtils;

/**
 * Created by rc on 2018/3/8.
 * Description:
 */

public class ElasticDragDismissFrameLayout extends FrameLayout {

    private float dragDismissFraction = -1f;
    private float dragDismissScale = 1f;
    private boolean shouldScale = false;
    private float dragElacticity = 0.8f;
    private float dragDismissDistance = Float.MAX_VALUE;

    private boolean draggingDown = false;
    private boolean draggingUp = false;

    private float totalDrag;

    private List<ElasticDragDismissCallback> callbacks;


    public ElasticDragDismissFrameLayout(@NonNull Context context) {
        this(context, null, 0, 0);
    }

    public ElasticDragDismissFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0, 0);
    }

    public ElasticDragDismissFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public ElasticDragDismissFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.ElasticDragDismissFrameLayout, 0, 0);
        if (array.hasValue(R.styleable.ElasticDragDismissFrameLayout_dragDismissDistance)) {
            dragDismissDistance = array.getDimensionPixelSize(R.styleable
                    .ElasticDragDismissFrameLayout_dragDismissDistance, 0);
        } else if (array.hasValue(R.styleable.ElasticDragDismissFrameLayout_dragDismissFraction)) {
            dragDismissFraction = array.getFloat(R.styleable
                    .ElasticDragDismissFrameLayout_dragDismissFraction, dragDismissFraction);
        }
        if (array.hasValue(R.styleable.ElasticDragDismissFrameLayout_dragDismissScale)) {
            dragDismissScale = array.getFloat(R.styleable
                    .ElasticDragDismissFrameLayout_dragDismissScale, dragDismissScale);
            shouldScale = dragDismissScale != 1f;
        }
        if (array.hasValue(R.styleable.ElasticDragDismissFrameLayout_dragElasticity)) {
            dragElacticity = array.getFloat(R.styleable.ElasticDragDismissFrameLayout_dragElasticity,
                    dragElacticity);
        }
        array.recycle();
    }

    public static abstract class ElasticDragDismissCallback {
        void onDrag(float elasticOffset, float elasticOffsetPixels,
                    float rawOffset, float rawOffsetPixels) {
        }

        void onDragDismissed() {
        }
    }

    @Override
    public boolean onStartNestedScroll(View child, View target, int nestedScrollAxes) {
        //只关心垂直方向
        return (nestedScrollAxes & View.SCROLL_AXIS_VERTICAL) != 0;
    }


    /**
     * 滚动前回调
     *
     * @param target
     * @param dx
     * @param dy
     * @param consumed
     */
    @Override
    public void onNestedPreScroll(View target, int dx, int dy, int[] consumed) {
        Log.d("ElasticDragDismissFrame", "dy:" + dy);
        if (draggingDown && dy > 0 || draggingUp && dy < 0) {
            dragScale(dy);
            consumed[1] = dy;
        }
    }


    /**
     * 正在滚动
     *
     * @param target
     * @param dxConsumed
     * @param dyConsumed
     * @param dxUnconsumed
     * @param dyUnconsumed
     */
    @Override
    public void onNestedScroll(View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        dragScale(dyUnconsumed);
    }

    /**
     * 停止滚动
     *
     * @param child
     */
    @Override
    public void onStopNestedScroll(View child) {
        if (Math.abs(totalDrag) >= dragDismissDistance) {
            dispatchDismissCallback();
        } else {
            animate()
                    .translationY(0f)
                    .scaleX(1f)
                    .scaleY(1f)
                    .setDuration(200L)
                    .setInterpolator(AnimUtils.getFastOutLinearInInterpolator(getContext()))
                    .setListener(null)
                    .start();
            totalDrag = 0;
            draggingDown = draggingUp = false;
            dispatchDismissCallback();

        }
    }


    private void dragScale(int dy) {
        if (dy == 0) {
            return;
        }
        totalDrag += dy;
        if (dy < 0 && !draggingUp && !draggingDown) {
            draggingDown = true;
            if (shouldScale) setPivotY(getHeight());
        } else if (dy > 0 && !draggingUp && !draggingDown) {
            draggingUp = true;
            if (shouldScale) setPivotY(0f);
        }

        float dragFraction = (float) Math.log10(1 + (Math.abs(totalDrag) / dragDismissDistance));

        float dragTo = dragFraction * dragDismissDistance * dragElacticity;

        if (draggingUp) {
            // as we use the absolute magnitude when calculating the drag fraction, need to
            // re-apply the drag direction
            dragTo *= -1;
        }

        setTranslationY(dragTo);

        if (shouldScale) {
            final float scale = 1 - ((1 - dragDismissScale) * dragFraction);
            setScaleX(scale);
            setScaleY(scale);
        }

    }

    private void dispatchDismissCallback() {


    }

    private void dispatchDragCallback(float elasticOffset, float elasticOffsetPixels,
                                      float rawOffset, float rawOffsetPixels) {
        if (callbacks != null && !callbacks.isEmpty()) {
            for (ElasticDragDismissCallback callback : callbacks) {
                callback.onDrag(elasticOffset, elasticOffsetPixels,
                        rawOffset, rawOffsetPixels);
            }
        }
    }


    public void addListener(ElasticDragDismissCallback listener) {
        if (callbacks == null) {
            callbacks = new ArrayList<>();
        }
        callbacks.add(listener);
    }

    public void removeListener(ElasticDragDismissCallback listener) {
        if (callbacks != null && callbacks.size() > 0) {
            callbacks.remove(listener);
        }
    }


}
