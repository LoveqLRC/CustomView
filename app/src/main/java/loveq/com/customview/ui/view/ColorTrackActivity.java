package loveq.com.customview.ui.view;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import loveq.com.customview.R;
import loveq.com.customview.widget.view.ColorTrackTextView;

public class ColorTrackActivity extends AppCompatActivity {

    private ColorTrackTextView mCttv;
    private LinearLayout mIndicator;
    private String[] mItems = {"前端", "后台", "android", "设计", "产品"};
    private ViewPager mVpColorTrack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_track);
        mCttv = findViewById(R.id.cttv);
        mIndicator = findViewById(R.id.ll_indicate);
        mVpColorTrack = findViewById(R.id.vp_color_track);
        initIndicator();
        initViewPager();
    }

    private void initIndicator() {
        for (String item : mItems) {
            ColorTrackTextView colorTrackTextView = new ColorTrackTextView(this);
            colorTrackTextView.setOriginColor(Color.GRAY);
            colorTrackTextView.setTrackColor(Color.RED);
            colorTrackTextView.setText(item);
            colorTrackTextView.setTextSize(24);
            mIndicator.addView(colorTrackTextView, new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
        }
    }

    private void initViewPager() {
        mVpColorTrack.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return mItems.length;
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                TextView textView = new TextView(container.getContext());
                textView.setText(mItems[position]);
                textView.setTextSize(36);
                textView.setGravity(Gravity.CENTER);
                container.addView(textView, new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT));
                return textView;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView((View) object);
            }
        });

        mVpColorTrack.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                ColorTrackTextView trackTextView = (ColorTrackTextView) mIndicator.getChildAt(position);
                trackTextView.setOrientation(ColorTrackTextView.Orientation.RIGHT_TO_LEFT);
                trackTextView.setCurrentProgress(1 - positionOffset);
                try {
                    ColorTrackTextView nextTrackTextView =(ColorTrackTextView)  mIndicator.getChildAt(position + 1);
                    nextTrackTextView.setOrientation(ColorTrackTextView.Orientation.LEFT_TO_RIGHT);
                    nextTrackTextView.setCurrentProgress(positionOffset);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
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
