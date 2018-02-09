package loveq.com.customview.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by rc on 2018/2/9.
 * Description: Canvas.clipRect 用于裁剪画布，也就是设置画布的显示区域
 */

public class ClipRectView extends View {
    public ClipRectView(Context context) {
        this(context, null);
    }

    public ClipRectView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ClipRectView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.RED);
        //为了避免裁剪之后对后面的操作影响，裁剪之前先进行备份
        canvas.save();
        //限制画布大小为原来的四分之一
        canvas.clipRect(0, 0, getWidth() / 2, getHeight() / 2);
        //再次画背景，只会画四分之一
        canvas.drawColor(Color.BLUE);
        //还原画布
        canvas.restore();
    }
}
