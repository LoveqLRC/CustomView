package loveq.com.customview.ui.other;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.Menu;
import android.widget.CheckedTextView;
import android.widget.TextView;

import loveq.com.customview.R;

public class OtherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other);
        initToolbar();
        initCheckedTextView();
        initSpanString();
    }

    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.save, menu);
        return true;
    }

    private void initCheckedTextView() {
        CheckedTextView ctv = findViewById(R.id.ctv);
        ctv.setOnClickListener(v -> ctv.toggle());
    }

    private void initSpanString() {
        TextView tvSpan = findViewById(R.id.spanStr);
        SpannableString spannableString = new SpannableString("16");
        ForegroundColorSpan dayColorSpan = new ForegroundColorSpan(Color.parseColor("#ffb400"));
        spannableString.setSpan(dayColorSpan, 0, spannableString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvSpan.append("第");
        tvSpan.append(spannableString);
        tvSpan.append("天");
    }
}
