package loveq.com.customview.ui;

import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import loveq.com.customview.R;

public class ImageViewMatrixActivity extends AppCompatActivity {

    private ImageView mIvParallax;
    private int position = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_view_matrix);
        mIvParallax = findViewById(R.id.iv_parallax);
        mIvParallax.setScaleType(ImageView.ScaleType.MATRIX);
        Glide.with(this).load(R.drawable.test_image_1).into(mIvParallax);
    }

    public void translate(View view) {
        Matrix matrix = new Matrix();
        position++;
        int dy = position * -16;
        matrix.postTranslate(0, dy);
        mIvParallax.setImageMatrix(matrix);
    }
}
