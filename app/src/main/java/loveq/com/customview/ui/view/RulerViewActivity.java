package loveq.com.customview.ui.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import loveq.com.customview.R;
import loveq.com.customview.widget.view.RulerView;

public class RulerViewActivity extends AppCompatActivity {

    private RulerView mRulerView;
    private TextView mTvPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ruler_view);
        mRulerView = findViewById(R.id.ruler);
        mTvPosition = findViewById(R.id.tv_position);
        mRulerView.setOnScrollListener(new RulerView.RulerViewOnScrollListener() {
            @Override
            public void onScroll(int position) {
                mTvPosition.setText("position:" + position);
            }
        });


    }

    public void go(View view) {
        mRulerView.setCurrentPosition(66);
    }

    public void clearDisableIndex(View view) {
        mRulerView.clearDisableIndex();
    }


    public void setDisableIndex(View view) {
        mRulerView.setDisableIndex(16);
    }
}
