package loveq.com.customview.widget.viewgroup;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.LinearLayout;

import loveq.com.customview.R;
import loveq.com.customview.widget.view.ShapeView;

/**
 * Created by rc on 2018/2/13.
 * Description:
 */

public class LoadingView extends LinearLayout {

    private ShapeView mShapeView;
    private View mShadowView;
    private float mTranslationDistance;
    // 动画执行的时间
    private final long ANIMATOR_DURATION = 350;

    public LoadingView(Context context) {
        this(context, null);
    }

    public LoadingView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mTranslationDistance = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 80, getResources().getDisplayMetrics());
        initLayout();
    }

    private void initLayout() {
        inflate(getContext(), R.layout.layout_loading_view, this);
        mShapeView = findViewById(R.id.shape_view);
        mShadowView = findViewById(R.id.shadow_view);
        post(new Runnable() {
            @Override
            public void run() {
                startFallAnimator();
            }
        });
    }

    private void startFallAnimator() {
        ObjectAnimator translationAnimator = ObjectAnimator.ofFloat(mShapeView,
                "translationY", 0, mTranslationDistance);

        ObjectAnimator scaleAnimator = ObjectAnimator.ofFloat(mShadowView,
                "scaleX", 1f, 0.3f);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(ANIMATOR_DURATION);
        animatorSet.setInterpolator(new AccelerateInterpolator());
        animatorSet.playTogether(translationAnimator, scaleAnimator);

        animatorSet.start();

        animatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                mShapeView.changeShape();
                startUpAnimator();
            }
        });


    }

    private void startUpAnimator() {

    }

}
