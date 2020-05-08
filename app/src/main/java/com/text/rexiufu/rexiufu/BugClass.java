package com.text.rexiufu.rexiufu;

import android.content.Context;
import android.widget.Toast;

/**
 * bug测试类
 */
public class BugClass {

    public BugClass(Context context) {
        Toast.makeText(context, "这是一个优美的bug！", Toast.LENGTH_SHORT).show();
    }
}