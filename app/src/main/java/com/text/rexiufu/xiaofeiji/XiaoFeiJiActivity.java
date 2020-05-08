package com.text.rexiufu.xiaofeiji;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.text.rexiufu.R;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class XiaoFeiJiActivity extends AppCompatActivity {

    public static final String TAG = "XiaoFeiJiActivity";
    private PathViewB currentPathView;
    Context context;

    private static View getRootView(Context context) {
        Activity activity = (Activity) context;
        return activity.getWindow().getDecorView().findViewById(android.R.id.content);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiaofeiji);
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        int screenWidthDp = getResources().getConfiguration().screenWidthDp;
        final float scale = getResources().getDisplayMetrics().density;
        recyclerView.setAdapter(new PathAdapterB(this, (int) (screenWidthDp * scale + 0.5f)));
        recyclerView.setClipChildren(false);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                Log.e(TAG, "dy = " + dy);
                int childCount = recyclerView.getChildCount();
                Log.e(TAG, "childCount = " + childCount);
                for (int i = 0; i < childCount; i++) {
                    PathViewB child = (PathViewB) recyclerView.getChildAt(i);
                    float line = (getResources().getConfiguration().screenHeightDp * scale + 0.5f) / 3;//根据手机的分辨率从 dp 的单位 转成为 px(像素)
                    Log.e(TAG, "line = " + line);//660.1667
                    Log.e(TAG, "height = " + child.getHeight());//600
                    Log.e(TAG, "i " + i + " child.getTop() " + child.getTop());//30 690=600+30+30+30
                    if (child.getTop() > line) {
                        int progress = (int) ((child.getTop() - line) / child.getHeight() * 100);
                        Log.e(TAG, "progress " + progress);//4
                        Log.e(TAG, "(child.getTop() - line) " + (child.getTop() - line));//4
                        Log.e(TAG, "(child.getTop() - line) / child.getHeight() " + (child.getTop() - line) / child.getHeight());//4
                        Log.e(TAG, "(child.getTop() - line) / child.getHeight() * 100 " + (child.getTop() - line) / child.getHeight() * 100);//4
                        if (progress < 0 || progress > 100) {
                            Log.e(TAG, "onScrolled: error progress " + progress);
                        }
                        if (child != currentPathView) {
                            if (currentPathView != null) {
                                child.setProgress(100 - progress);
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                    child.setZ(1);
                                }
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                    currentPathView.setZ(0);
                                }
                                currentPathView.setProgress(-1);

                            } else {
                                child.setProgress(100 - progress);
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                    child.setZ(1);
                                }
                            }
                            currentPathView = child;
                        } else {
                            child.setProgress(100 - progress);
                        }
                        return;
                    }
                }

            }
        });
    }
}