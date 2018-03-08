package loveq.com.customview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import loveq.com.customview.ui.base.BaseViewActivity;
import loveq.com.customview.ui.pliad.ElasticDragActivity;
import loveq.com.customview.ui.view.ColorTrackActivity;
import loveq.com.customview.ui.view.LetterSideBarActivity;
import loveq.com.customview.ui.view.RatingBarActivity;
import loveq.com.customview.ui.view.RulerViewActivity;
import loveq.com.customview.ui.viewgroup.LoadingViewActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void BaseApi(View view) {
        Intent intent = new Intent(this, BaseViewActivity.class);
        startActivity(intent);
    }

    public void ColorTrack(View view) {
        Intent intent = new Intent(this, ColorTrackActivity.class);
        startActivity(intent);
    }

    public void RatingBar(View view) {
        Intent intent = new Intent(this, RatingBarActivity.class);
        startActivity(intent);
    }

    public void LetterSideBar(View view) {
        Intent intent = new Intent(this, LetterSideBarActivity.class);
        startActivity(intent);
    }

    public void LoadingView(View view) {
        Intent intent = new Intent(this, LoadingViewActivity.class);
        startActivity(intent);
    }

    public void RulerView(View view) {
        Intent intent = new Intent(this, RulerViewActivity.class);
        startActivity(intent);
    }


    public void ElasticDrag(View view) {
        Intent intent = new Intent(this, ElasticDragActivity.class);
        startActivity(intent);
    }
}
