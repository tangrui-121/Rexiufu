package com.text.rexiufu.xiaofeiji;

import android.content.Context;
import android.graphics.Path;
import android.graphics.RectF;
import android.view.View;
import android.view.ViewGroup;

import com.text.rexiufu.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PathAdapter extends RecyclerView.Adapter<PathAdapter.PathViewHolder> {
    private Context mContext;
    private Path mLeftPath;
    private Path mRightPath;
    private int mWidth;

    public PathAdapter(Context context, int width) {
        mWidth = width;
        mContext = context;
        initLeftPath(width);
        initRightPath(width);
    }

    @NonNull
    @Override
    public PathViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        PathView pathView = new PathView(mContext);
        RecyclerView.LayoutParams ll = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 720);
        pathView.setLayoutParams(ll);
        if (viewType % 2 == 0)
            pathView.setPath(mLeftPath);
        else
            pathView.setPath(mRightPath);

        switch (viewType) {
            case 0:
                pathView.setBackPaint(0xff00AD69, mWidth);
                break;
            case 1:
                pathView.setBackPaint(0xff007AB9, mWidth);
                break;
            case 2:
                pathView.setBackPaint(0xffB07F36, mWidth);
                break;
            case 3:
                pathView.setBackPaint(0xffFF991B, mWidth);
                break;
            case 4:
                pathView.setBackPaint(0xffB07F36, mWidth);
                break;
        }
        return new PathViewHolder(pathView);
    }

    private void initLeftPath(int width) {
        mLeftPath = new Path();
        mLeftPath.moveTo(width / 5, 0);
        mLeftPath.lineTo(width / 5, 400);
        mLeftPath.arcTo(width / 5, 300, width / 5 + 200, 500, -180, -90, false);
        mLeftPath.lineTo(width / 5 * 4 - 200, 500);
        mLeftPath.arcTo(width / 5 * 4 - 200, 500, width / 5 * 4, 700, -90, 90, false);
        mLeftPath.lineTo(width / 5 * 4, 720);
    }

    private void initRightPath(int width) {
        mRightPath = new Path();
        mRightPath.moveTo(width / 5 * 4, 0);
        mRightPath.lineTo(width / 5 * 4, 400);
        mRightPath.arcTo(width / 5 * 4 - 200, 300, width / 5 * 4, 500, 0, 90, false);
        mRightPath.lineTo(width / 5 + 200, 500);
        mRightPath.arcTo(width / 5, 500, width / 5 + 200, 700, -90, -90, false);
        mRightPath.lineTo(width / 5, 720);
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