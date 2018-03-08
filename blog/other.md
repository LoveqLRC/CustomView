
# 杂记

## @CheckResult注解
该注解意味着需要对方法的返回值进行处理，需要进行后续操作。
例如：

	 public @CheckResult
	    static String trim(String s) {
	        return s.trim();
	    }

当调用该方法的时候，单单只是调用`s.trim();`可能就没有达到想要的结果(没有去除空格)。
最理想的调用方式应该是`s = s.trim();`使用函数的返回值才能达到效果，而`@CheckResult`注解就是解决这样的问题。


## 改变Menu字体颜色
`Menu`依附在`Toolbar`上，所以给`Toolbar`设置对应的主题即可

    <android.support.v7.widget.Toolbar
        android:id="@+id/toolbar"
        android:layout_width="match_parent"
        android:layout_height="?actionBarSize"
        android:background="#444444"
        android:theme="@style/MenuTheme"
        app:navigationIcon="@drawable/ic_back">

    </android.support.v7.widget.Toolbar>

#


    <style name="MenuTheme" parent="@style/ThemeOverlay.AppCompat.ActionBar">
        <item name="actionMenuTextColor">@android:color/white</item> <!--  menu字体颜色-->
    </style>


![](https://github.com/LoveqLRC/CustomView/blob/master/screenshot/menu_text_color.png)



## CheckedTextView
`CheckedTextView`不提供直接设置选中和不选中的样式，当然也可以选择自定义drawable实现，但是这样做原生的动画效果将会丢失。
可以通过设置 `android:theme`解决选中和不选中的样式问题。

    <CheckedTextView
        android:id="@+id/ctv"
        style="?android:borderlessButtonStyle"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:drawableLeft="?android:listChoiceIndicatorSingle"
        android:text="rc"
        android:theme="@style/CheckedTextViewStyle"/>



#

    <style name="CheckedTextViewStyle">
        <!--选中的颜色-->
        <item name="colorAccent">#3c3c3c</item>
        <!--未选中的颜色-->
        <item name="android:textColorSecondary">@color/colorAccent</item>
    </style>



同理`RadioButton`也可以这样解决。

