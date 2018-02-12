package loveq.com.customview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import loveq.com.customview.ui.baseapi.BaseApiActivity;
import loveq.com.customview.ui.view.ColorTrackActivity;
import loveq.com.customview.ui.view.LetterSideBarActivity;
import loveq.com.customview.ui.view.RatingBarActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void BaseApi(View view) {
        Intent intent = new Intent(this, BaseApiActivity.class);
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
}
