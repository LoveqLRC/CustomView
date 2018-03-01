package loveq.com.customview.ui.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.CheckedTextView;

import loveq.com.customview.R;

public class OtherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other);
        CheckedTextView ctv = findViewById(R.id.ctv);
        ctv.setOnClickListener(v -> ctv.toggle());
    }
}
