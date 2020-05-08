package com.text.rexiufu.view;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.LinearLayout;

import com.text.rexiufu.R;

public class PointActivity extends AppCompatActivity {
    private LinearLayout lin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_point);
        lin = findViewById(R.id.lin);
        lin.addView(new PorterDuffXfermodeView(this));
    }
}