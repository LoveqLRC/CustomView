package loveq.com.customview.widget.base;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Checkable;

/**
 * Created by rc on 2018/2/23.
 * Description:
 */

public class CustomDrawableStates extends View implements Checkable {
    private static final int[] CHECKED_STATE_SET = {android.R.attr.state_checked};
    private boolean mIsChecked = false;

    public CustomDrawableStates(Context context) {
        super(context);

    }

    public CustomDrawableStates(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomDrawableStates(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected int[] onCreateDrawableState(int extraSpace) {
        final int[] drawableState = super.onCreateDrawableState(extraSpace + 1);

        return super.onCreateDrawableState(extraSpace);
    }

    @Override
    public void setChecked(boolean checked) {
        if (mIsChecked != checked) {
            mIsChecked = checked;
            refreshDrawableState();
        }
    }

    @Override
    public boolean isChecked() {
        return mIsChecked;
    }

    @Override
    public void toggle() {
        setChecked(!isChecked());
    }
}
