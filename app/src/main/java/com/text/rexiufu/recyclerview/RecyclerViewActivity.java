package com.text.rexiufu.recyclerview;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.text.rexiufu.R;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);
        setDate();
    }

    private void setDate() {
        final List<String> datas = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            datas.add("item " + i);
        }
        recyclerView = findViewById(R.id.id_recyclerview);
        //布局管理器 不使用就会一片空白
//        recyclerView.setLayoutManager(new GridLayoutManager(this,3));
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, datas);
        recyclerView.setAdapter(adapter);

        //不用post 报空的话 是因为还没有绘制好
        recyclerView.post(new Runnable() {
            @Override
            public void run() {
                //外部获取item的viewholder
                int firstItemPosition = layoutManager.findFirstVisibleItemPosition();
                if (5 - firstItemPosition >= 0) {
                    //得到要更新的item的view
                    View view = recyclerView.getChildAt(5 - firstItemPosition);
                    if (null != recyclerView.getChildViewHolder(view)) {
                        RecyclerViewAdapter.MyViewHolder viewHolder = (RecyclerViewAdapter.MyViewHolder) recyclerView.getChildViewHolder(view);
                        Log.e("1111", "1111text === " + viewHolder.textView.getText().toString());
                    }
                }

                View view = recyclerView.getChildAt(5);
                TextView textView = view.findViewById(R.id.text);
                Log.e("1111", "2222text === " + textView.getText().toString());
            }
        });


        //点击事件
        adapter.setOnItemClickListener(new RecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(RecyclerViewActivity.this, datas.get(position) + "被点击了",
                        Toast.LENGTH_SHORT).show();
            }
        });
        //长按事件
        adapter.setOnItemLongClickListener(new RecyclerViewAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(View view, int position) {
                Toast.makeText(RecyclerViewActivity.this, datas.get(position) + "被长按了",
                        Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (mShouldScroll) {
                    mShouldScroll = false;
                    smoothMoveToPosition(recyclerView, mToPosition);
                }
            }
        });
    }

    /**
     * RecyclerView 移动到当前位置，
     *
     * @param manager  设置RecyclerView对应的manager
     * @param n  要跳转的位置
     */
    public static void MoveToPosition(LinearLayoutManager manager, int n) {
        manager.scrollToPositionWithOffset(n, 0);
        manager.setStackFromEnd(true);
    }


    /**
     * RecyclerView 移动到当前位置，
     * 这个相对复杂一些，但是可以有效地避免指针越界
     * @param manager   设置RecyclerView对应的manager
     * @param mRecyclerView  当前的RecyclerView
     * @param n  要跳转的位置
     */
    public static void MoveToPosition(LinearLayoutManager manager, RecyclerView mRecyclerView, int n) {


        int firstItem = manager.findFirstVisibleItemPosition();
        int lastItem = manager.findLastVisibleItemPosition();
        if (n <= firstItem) {
            mRecyclerView.scrollToPosition(n);
        } else if (n <= lastItem) {
            int top = mRecyclerView.getChildAt(n - firstItem).getTop();
            mRecyclerView.scrollBy(0, top);
        } else {
            mRecyclerView.scrollToPosition(n);
        }

    }

    /**
     * 目标项是否在最后一个可见项之后
     */
    private boolean mShouldScroll;
    /**
     * 记录目标项位置
     */
    private int mToPosition;

    /**
     * 滑动到指定位置
     *
     * @param mRecyclerView
     * @param position
     */
    private void smoothMoveToPosition(RecyclerView mRecyclerView, final int position) {
        // 第一个可见位置
        int firstItem = mRecyclerView.getChildLayoutPosition(mRecyclerView.getChildAt(0));
        // 最后一个可见位置
        int lastItem = mRecyclerView.getChildLayoutPosition(mRecyclerView.getChildAt(mRecyclerView.getChildCount() - 1));

        if (position < firstItem) {
            // 如果跳转位置在第一个可见位置之前，就smoothScrollToPosition可以直接跳转
            mRecyclerView.smoothScrollToPosition(position);
        } else if (position <= lastItem) {
            // 跳转位置在第一个可见项之后，最后一个可见项之前
            // smoothScrollToPosition根本不会动，此时调用smoothScrollBy来滑动到指定位置
            int movePosition = position - firstItem;
            if (movePosition >= 0 && movePosition < mRecyclerView.getChildCount()) {
                int top = mRecyclerView.getChildAt(movePosition).getTop();
                mRecyclerView.smoothScrollBy(0, top);
            }
        } else {
            // 如果要跳转的位置在最后可见项之后，则先调用smoothScrollToPosition将要跳转的位置滚动到可见位置
            // 再通过onScrollStateChanged控制再次调用smoothMoveToPosition，执行上一个判断中的方法
            mRecyclerView.smoothScrollToPosition(position);
            mToPosition = position;
            mShouldScroll = true;
        }
    }

}