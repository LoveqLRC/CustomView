package loveq.com.customview.ui.view;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.transition.TransitionManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import loveq.com.customview.R;
import loveq.com.customview.widget.view.LetterSideBar;

public class LetterSideBarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_letter_side_bar);
        final TextView tvLetter = findViewById(R.id.tv_letter);
        LetterSideBar lsb = findViewById(R.id.lsb);
        final ViewGroup root = findViewById(R.id.root);
        lsb.setLetterTouchListener(new LetterSideBar.LetterTouchListener() {
            @Override
            public void onTouch(String letter, boolean isLeave) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    TransitionManager.beginDelayedTransition(root);
                }
                tvLetter.setVisibility(isLeave ? View.GONE : View.VISIBLE);
                tvLetter.setText(letter);
            }
        });
    }
}
