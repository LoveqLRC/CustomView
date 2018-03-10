package loveq.com.customview.ui.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import loveq.com.customview.R;
import loveq.com.customview.ui.other.OtherActivity;
import loveq.com.customview.ui.other.ViewLocationActivity;

public class BaseViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
    }

    public void ClipRect(View view) {
        Intent intent = new Intent(this, ClipRectActivity.class);
        startActivity(intent);
    }

    public void DrawText(View view) {
        Intent intent = new Intent(this, DrawTextActivity.class);
        startActivity(intent);
    }

    public void TextBounds(View view) {
        Intent intent = new Intent(this, TextBoundsActivity.class);
        startActivity(intent);
    }

    public void ImageViewScaleType(View view) {
        Intent intent = new Intent(this, ImageViewMatrixActivity.class);
        startActivity(intent);
    }

    public void customStates(View view) {
        Intent intent = new Intent(this, CustomStatesActivity.class);
        startActivity(intent);
    }

    public void other(View view) {
        Intent intent = new Intent(this, OtherActivity.class);
        startActivity(intent);
    }
    public void SemiCircle(View view) {
        Intent intent = new Intent(this, SemiCircleActivity.class);
        startActivity(intent);
    }

    public void location(View view) {
        Intent intent = new Intent(this, ViewLocationActivity.class);
        startActivity(intent);
    }
}
