package loveq.com.customview.utils;

import android.os.Build;
import android.util.IntProperty;
import android.util.Property;

/**
 * Created by rc on 2018/2/11.
 * Description:
 */

public class AnimUtils {

    private AnimUtils() {
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
