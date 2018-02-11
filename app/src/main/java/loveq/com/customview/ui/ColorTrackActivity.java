package loveq.com.customview.ui;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import loveq.com.customview.R;
import loveq.com.customview.widget.ColorTrackTextView;

public class ColorTrackActivity extends AppCompatActivity {

    private ColorTrackTextView mCttv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_track);
        mCttv = findViewById(R.id.cttv);
    }

    public void LeftToRight(View view) {
        mCttv.setOrientation(ColorTrackTextView.Orientation.LEFT_TO_RIGHT);
        ValueAnimator animator = ObjectAnimator.ofFloat(0, 1);
        animator.setDuration(2000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                mCttv.setCurrentProgress(value);
            }
        });
        animator.start();
    }

    public void RightToLeft(View view) {
        mCttv.setOrientation(ColorTrackTextView.Orientation.RIGHT_TO_LEFT);
        ValueAnimator animator = ObjectAnimator.ofFloat(0, 1);
        animator.setDuration(2000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                mCttv.setCurrentProgress(value);
            }
        });
        animator.start();

    }
}
