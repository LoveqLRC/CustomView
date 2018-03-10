package loveq.com.customview.ui.other;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import loveq.com.customview.R;

public class ViewLocationActivity extends AppCompatActivity {

    private View mViewLocation;
    private TextView mTvContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_location);
        mTvContent = findViewById(R.id.tv_content);
        mViewLocation = findViewById(R.id.v_location);
    }

    public void translationY(View view) {
        float translationY = mViewLocation.getTranslationY();
        translationY += 10;
        mViewLocation.setTranslationY(translationY);

        mTvContent.setText("Top : " + mViewLocation.getTop() + "\n" +
                "TranslationY :" + mViewLocation.getTranslationY()
                + "\n" +
                "Height :" +  mViewLocation.getHeight()
                + "\n" +
                "Y :" + mViewLocation.getY() + "\n");
        float y = mViewLocation.getTop() + mViewLocation.getTranslationY();
        if (mViewLocation.getY() == y) {
            mTvContent.append("y=top+translationY");
        } else {
            mTvContent.append("y!=top+translationY");

        }
    }

    public void top(View view) {
        int top = mViewLocation.getTop();
        top += 10;
        mViewLocation.setTop(top);
        mTvContent.setText("Top : " + mViewLocation.getTop() + "\n" +
                "TranslationY :" + mViewLocation.getTranslationY() + "\n" +
                "Height :" + mViewLocation.getHeight()
                + "\n" +
                "Y :" +mViewLocation.getY() + "\n");

        float y = mViewLocation.getTop() + mViewLocation.getTranslationY();
        if (mViewLocation.getY() == y) {
            mTvContent.append("y=top+translationY");
        } else {
            mTvContent.append("y!=top+translationY");

        }
    }
}
