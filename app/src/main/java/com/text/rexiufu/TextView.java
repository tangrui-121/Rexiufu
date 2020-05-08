package com.text.rexiufu;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;

import androidx.annotation.IntDef;
import androidx.annotation.Nullable;

public class TextView extends View {

    private Paint mPaint;
    private Paint mPaint1;
    private Path mPath;
    private RectF mRectF;

    public TextView(Context context) {
        this(context, null);
    }

    public TextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
        initPath();
    }

    private void initPaint() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.BLUE);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(10);

        mPaint1 = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint1.setColor(Color.RED);
        mPaint1.setStyle(Paint.Style.STROKE);
        mPaint1.setStrokeWidth(6);

    }

    private void initPath() {
        mPath = new Path();
        mPath.moveTo(10, 10);
        mRectF = new RectF(100, 10, 200, 100);
        mPath.arcTo(mRectF, 0, 300,true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        DisplayMetrics metrics = new DisplayMetrics();
        ((Activity) getContext()).getWindowManager().getDefaultDisplay().getMetrics(metrics);
        canvas.translate(metrics.widthPixels / 2, metrics.heightPixels / 2);

        canvas.drawRect(mRectF, mPaint1);
        canvas.drawPath(mPath, mPaint);

        init(Lever.A);
    }

    private void init(@Lever int a){

    }

    @SuppressLint("UniqueConstants")
    @IntDef({Lever.A, Lever.B, Lever.C, Lever.D, Lever.E})
    @interface Lever{
        public static final int A = 1;
        public static final int B = 1;
        public static final int C = 1;
        public static final int D = 1;
        public static final int E = 1;
    }
}
