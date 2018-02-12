package loveq.com.customview.widget.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import loveq.com.customview.R;

/**
 * Created by rc on 2018/2/11.
 * Description:评分条
 */

public class RatingBar extends View {

    private Bitmap mStarNormalBitmap;
    private Bitmap mStarSelectedBitmap;
    private int mStarGrade;
    //当前的评级
    private int mCurrentGrade = 0;

    public RatingBar(Context context) {
        this(context, null);
    }

    public RatingBar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RatingBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.RatingBar);

        int startNormalId = array.getResourceId(R.styleable.RatingBar_normal_icon, R.drawable.star_normal);
        mStarNormalBitmap = BitmapFactory.decodeResource(getResources(), startNormalId);

        int starSelectedId = array.getResourceId(R.styleable.RatingBar_star_icon, R.drawable.star_selected);
        mStarSelectedBitmap = BitmapFactory.decodeResource(getResources(), starSelectedId);

        mStarGrade = array.getInt(R.styleable.RatingBar_star_grade, 5);
        array.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        for (int i = 0; i < mStarGrade; i++) {
            int startX = mStarNormalBitmap.getWidth() * i;
            if (i < mCurrentGrade) {
                canvas.drawBitmap(mStarSelectedBitmap, startX, 0, null);
            } else {
                canvas.drawBitmap(mStarNormalBitmap, startX, 0, null);
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                float moveX = event.getX();
                int currentGrade = (int) (moveX / mStarNormalBitmap.getWidth());
                currentGrade = currentGrade < 0 ? 0 : currentGrade;
                currentGrade = currentGrade > mStarGrade ? mStarGrade : currentGrade;
                if (currentGrade != mCurrentGrade) {
                    mCurrentGrade = currentGrade;
                    invalidate();
                }
                break;
        }

        return true;
    }
}
