
# 杂记

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

