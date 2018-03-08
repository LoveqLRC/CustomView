package loveq.com.customview.utils;

import android.content.Context;
import android.os.Build;
import android.util.IntProperty;
import android.util.Property;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;

/**
 * Created by rc on 2018/2/11.
 * Description:
 */

public class AnimUtils {

    private AnimUtils() {
    }

    private static Interpolator fastOutSlowIn;
    private static Interpolator fastOutLinearIn;
    private static Interpolator linearOutSlowIn;
    private static Interpolator linear;

    public static Interpolator getFastOutSlowInInterpolator(Context context) {
        if (fastOutSlowIn == null) {
            fastOutSlowIn = AnimationUtils.loadInterpolator(context,
                    android.R.interpolator.fast_out_slow_in);
        }
        return fastOutSlowIn;
    }

    public static Interpolator getFastOutLinearInInterpolator(Context context) {
        if (fastOutLinearIn == null) {
            fastOutLinearIn = AnimationUtils.loadInterpolator(context,
                    android.R.interpolator.fast_out_linear_in);
        }
        return fastOutLinearIn;
    }

    public static Interpolator getLinearOutSlowInInterpolator(Context context) {
        if (linearOutSlowIn == null) {
            linearOutSlowIn = AnimationUtils.loadInterpolator(context,
                    android.R.interpolator.linear_out_slow_in);
        }
        return linearOutSlowIn;
    }

    public static Interpolator getLinearInterpolator() {
        if (linear == null) {
            linear = new LinearInterpolator();
        }
        return linear;
    }

    public static <T> Property<T, Integer> createIntProperty(final IntProp<T> impl) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return new IntProperty<T>(impl.name) {
                @Override
                public void setValue(T object, int value) {
                    impl.set(object, value);
                }

                @Override
                public Integer get(T object) {
                    return impl.get(object);
                }
            };
        } else {
            return new Property<T, Integer>(Integer.class, impl.name) {
                @Override
                public Integer get(T object) {
                    return impl.get(object);
                }

                @Override
                public void set(T object, Integer value) {
                    impl.set(object, value);
                }
            };
        }
    }


    /**
     * 创建<code>int</code>类型的{@link Property}的代理
     */
    public static abstract class IntProp<T> {
        public final String name;

        public IntProp(String name) {
            this.name = name;
        }

        public abstract void set(T object, int value);

        public abstract int get(T object);
    }
}
