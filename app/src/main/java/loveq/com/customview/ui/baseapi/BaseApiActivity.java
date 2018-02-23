package loveq.com.customview.ui.baseapi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import loveq.com.customview.R;

public class BaseApiActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_api);
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
}
