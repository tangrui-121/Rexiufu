package com.text.rexiufu.xiaofeiji;

import android.content.Context;
import android.graphics.Path;
import android.view.View;
import android.view.ViewGroup;

import com.text.rexiufu.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PathAdapterB extends RecyclerView.Adapter<PathAdapterB.PathViewHolder> {
    private Context mContext;
    private Path mLeftPath;
    private Path mRightPath;

    public PathAdapterB(Context context, int width) {
        mContext = context;
        initLeftPath(width);
        initRightPath(width);
    }

    @NonNull
    @Override
    public PathViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        PathViewB pathView = new PathViewB(mContext);
        pathView.setBackground(mContext.getDrawable(R.drawable.background_xiaofeiji));
        RecyclerView.LayoutParams ll = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 600);
        ll.bottomMargin = 30;
        ll.leftMargin = 30;
        ll.rightMargin = 30;
        ll.topMargin = 30;
        pathView.setLayoutParams(ll);
        if (viewType % 2 == 0)
            pathView.setPath(mLeftPath);
        else
            pathView.setPath(mRightPath);

        switch (viewType) {
            case 0:
                pathView.setBackgroundColor(0xff00AD69);
                break;
            case 1:
                pathView.setBackgroundColor(0xff007AB9);
                break;
            case 2:
                pathView.setBackgroundColor(0xffB07F36);
                break;
            case 3:
                pathView.setBackgroundColor(0xffFF991B);
                break;
            case 4:
                pathView.setBackgroundColor(0xffB07F36);
                break;
        }


        pathView.setImage(R.mipmap.feiji);
        return new PathViewHolder(pathView);
    }

    private void initLeftPath(int width) {
        mLeftPath = new Path();
        mLeftPath.moveTo(width / 5, 0);
        mLeftPath.lineTo(width / 5, 380);
        mLeftPath.lineTo(width / 5 * 4, 380);
        mLeftPath.lineTo(width / 5 * 4, 600);
    }

    private void initRightPath(int width) {
        mRightPath = new Path();
        mRightPath.moveTo(width / 5 * 4, 0);
        mRightPath.lineTo(width / 5 * 4, 380);
        mRightPath.lineTo(width / 5, 380);
        mRightPath.lineTo(width / 5, 600);
    }

    @Override
    public void onBindViewHolder(@NonNull PathViewHolder holder, int position) {

    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    class PathViewHolder extends RecyclerView.ViewHolder {
        public PathViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}