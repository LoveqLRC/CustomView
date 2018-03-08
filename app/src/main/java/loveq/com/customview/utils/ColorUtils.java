package loveq.com.customview.utils;

import android.support.annotation.CheckResult;
import android.support.annotation.ColorInt;
import android.support.annotation.IntRange;

/**
 * Created by rc on 2018/3/8.
 * Description:
 */

public class ColorUtils {
    private ColorUtils() {
    }


    /**
     * 设置{@code color}的alpha成{@code alpha}
     */
    public static @CheckResult
    @ColorInt
    int modifyAlpha(@ColorInt int color,
                    @IntRange(from = 0, to = 255) int alpha) {
        return (color & 0x00ffffff) | (alpha << 24);
    }


}
