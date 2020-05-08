package com.text.rexiufu.xiaofeiji;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.text.rexiufu.R;
import com.text.rexiufu.TextView;

import androidx.annotation.Nullable;

public class RoundActivity extends Activity {

    private LinearLayout aaaaaaaaa;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_round);
        aaaaaaaaa = findViewById(R.id.aaaaaaaaa);

//        aaaaaaaaa.addView(new RoundView(this));
        aaaaaaaaa.addView(new TextView(this));
    }
}
