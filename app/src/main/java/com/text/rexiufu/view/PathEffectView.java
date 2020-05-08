package com.text.rexiufu.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ComposePathEffect;
import android.graphics.CornerPathEffect;
import android.graphics.DashPathEffect;
import android.graphics.DiscretePathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;
import android.graphics.PathEffect;
import android.graphics.SumPathEffect;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class PathEffectView extends View {

    //xml是调用两个参数的，xml里面的属性都要从attrbuteSet里面取，so在只写一个构造函数时就会报错
    //this和super的区别

    private float mPhase;
    private PathEffect[] mEffects = new PathEffect[7];
    private int[] mColors;
    private Paint mPaint;
    private Path mPath;

    public PathEffectView(Context context) {
        this(context,null);
    }

    public PathEffectView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public PathEffectView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr,0);
    }

    public PathEffectView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init(){
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(4);
        // 创建,并初始化Path
        mPath = new Path();
        mPath.moveTo(0, 0);
        for (int i = 1; i <= 15; i++) {
            // 生成15个点,随机生成它们的坐标,并将它们连成一条Path
            mPath.lineTo(i * 20, (float) Math.random() * 60);
        }
        // 初始化七个颜色
        mColors = new int[]{
                Color.BLACK, Color.BLUE, Color.CYAN,
                Color.GREEN, Color.MAGENTA, Color.RED, Color.YELLOW
        };
    }

    protected void onDraw(Canvas canvas) {
        // 将背景填充成白色
        canvas.drawColor(Color.WHITE);
        // -------下面开始初始化7种路径的效果
        // 使用路径效果－－原始效果
        mEffects[0] = null;
        // 使用CornerPathEffect路径效果--参数为圆角半径
        mEffects[1] = new CornerPathEffect(10);
        // 初始化DiscretePathEffect －－ segmentLength指定最大的段长，deviation指定偏离量
        mEffects[2] = new DiscretePathEffect(3.0f, 5.0f);
        // 初始化DashPathEffect －－intervals为虚线的ON和OFF数组，offset为绘制时的偏移量
        mEffects[3] = new DashPathEffect(new float[]{
                20, 10, 5, 10
        }, mPhase);
        // 初始化PathDashPathEffect
        Path p = new Path();
        p.addRect(0, 0, 8, 8, Path.Direction.CCW);
        // shape则是指填充图形，advance指每个图形间的间距，phase为绘制时的偏移量，style为该类自由的枚举值
        mEffects[4] = new PathDashPathEffect(p, 12, mPhase, PathDashPathEffect.Style.ROTATE);
        // 组合效果
        mEffects[5] = new ComposePathEffect(mEffects[2], mEffects[4]);
        // 叠加效果
        mEffects[6] = new SumPathEffect(mEffects[4], mEffects[3]);
        // 将画布移到8,8处开始绘制
        canvas.translate(8, 8);
        // 依次使用7种不同路径效果,7种不同的颜色来绘制路径
        for (int i = 0; i < mEffects.length; i++) {
            mPaint.setPathEffect(mEffects[i]);
            mPaint.setColor(mColors[i]);
            canvas.drawPath(mPath, mPaint);
            canvas.translate(0, 60);
        }
        // 改变phase值,形成动画效果
        mPhase += 1;
        invalidate();
    }
}