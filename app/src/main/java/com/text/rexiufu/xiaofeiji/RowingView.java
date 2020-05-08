package com.text.rexiufu.xiaofeiji;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.text.rexiufu.R;

import androidx.annotation.Nullable;

public class RowingView extends ViewGroup {

    private ImageView mBoat;
    private ImageView mMan;
    private ImageView mPaddle1;
    private ImageView mPaddle2;

    private float mAngle;
    private boolean isAdd;

    private void init(Context context) {
        mBoat = new ImageView(context);
        mMan = new ImageView(context);
        mPaddle1 = new ImageView(context);
        mPaddle2 = new ImageView(context);

        mBoat.setImageResource(R.mipmap.rowing_01);
        mBoat.layout(0, 0, 200, 200);
        addView(mBoat);

        mPaddle1.setImageResource(R.mipmap.rowing_02);
        mPaddle1.layout(50, 50, 150, 120);//放置在这个矩形区域内
        mPaddle1.setPivotX(mPaddle1.getPivotX() - 15f);//按照中心店旋转一定角度
        addView(mPaddle1);

        mPaddle2.setImageResource(R.mipmap.rowing_02);
        mPaddle2.layout(50, 80, 150, 150);
        mPaddle2.setPivotX(mPaddle2.getPivotX() - 15f);
        addView(mPaddle2);

        mMan.setImageResource(R.mipmap.rowing_04);
        mMan.layout(70, 80, 110, 120);
        addView(mMan);

        setWillNotDraw(false);//viewgroup默认不走ondraw  想走就将这个设置为false
    }

    public RowingView(Context context) {
        super(context);
    }

    public RowingView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RowingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mAngle > 90) {
            isAdd = false;
        }

        if (mAngle <= 0) {
            isAdd = true;
        }

        mPaddle1.setRotation(135f - mAngle);//顺时针旋转
        mPaddle2.setRotation(-135f + mAngle);

        if (isAdd) {
            mAngle += 2f;
        } else {
            mAngle -= 2f;
        }

        mMan.setTranslationX(mAngle / 5);//平移

        invalidate();
    }
}
