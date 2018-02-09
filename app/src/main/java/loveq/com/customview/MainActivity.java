package loveq.com.customview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import loveq.com.customview.ui.ClipRectActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void ClipRect(View view) {
        Intent intent = new Intent(this, ClipRectActivity.class);
        startActivity(intent);
    }
}
