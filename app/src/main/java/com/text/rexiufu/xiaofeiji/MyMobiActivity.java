package com.text.rexiufu.xiaofeiji;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.text.rexiufu.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MyMobiActivity extends Activity {

    private RecyclerView comment_list;

    private Button plan_b;

    private PathView currentPathView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comment_list);

        comment_list = findViewById(R.id.comment_list);
        plan_b = findViewById(R.id.plan_b);
        plan_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MyMobiActivity.this, XiaoFeiJiActivity.class));
            }
        });


        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        comment_list.setLayoutManager(layoutManager);
        int screenWidthDp = getResources().getConfiguration().screenWidthDp;
        final float scale = getResources().getDisplayMetrics().density;
        PathAdapter adapter = new PathAdapter(this, (int) (screenWidthDp * scale + 0.5f));
        comment_list.setAdapter(adapter);

//        comment_list.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
//                int childCount = recyclerView.getChildCount();
//                for (int i = 0; i < childCount; i++) {
//                    PathView child = (PathView) recyclerView.getChildAt(i);
//                    float line = (getResources().getConfiguration().screenHeightDp * scale + 0.5f) / 3;//根据手机的分辨率从 dp 的单位 转成为 px(像素)
//                    if (child.getTop() > line) {
//                        float progress = (child.getTop() - line) / child.getHeight();
//                        //第一次必然不等于，currentPathView也必然是空 所以child.setProgress(100 - progress); 再将currentPathView = child;
//                        //currentPathView != null 只有在切换item时会调用掉
//                        if (child != currentPathView) {
//                            if (currentPathView != null) {
//                                child.moveFeiji(1 - progress);//目前的item开始绘制小飞机
//                                currentPathView.moveFeiji(-1);//上一个item清除小飞机
//                            } else {
//                                child.moveFeiji(1 - progress);//有且只有第一次进来时调用
//                            }
//                            currentPathView = child;
//                        } else {
//                            child.moveFeiji(1 - progress);//在同一个item里面就这里不停去刷新
//                        }
//                        return;
//                    }
//                }
//
//            }
//        });

    }
}