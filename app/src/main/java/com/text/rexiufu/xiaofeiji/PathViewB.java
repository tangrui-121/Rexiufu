package com.text.rexiufu.xiaofeiji;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

public class PathViewB extends View {
    public static final String TAG = "PathView";

    private Path mPath;
    private Paint mPaint;
    private int mProgress = -1;
    private Bitmap mBitmap;
    private Rect mRect;
    private float[] pos = new float[2];
    private float[] tan = new float[2];
    private PathMeasure mPathMeasure;
    private float mLength;
    private float mMax = 100f;

    public PathViewB(Context context) {
        this(context, null);
    }

    public PathViewB(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PathViewB(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    private void initPaint() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(0x60ffffff);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(120);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
    }

    public void setPath(Path path) {
        mPath = path;
        mPathMeasure = new PathMeasure(mPath, false);
        mLength = mPathMeasure.getLength();
        postInvalidate();
    }

    public void setMax(int max) {
        mMax = max;
    }

    public void setProgress(int progress) {
        mProgress = progress;
        if (mPath != null) {
            mPathMeasure.getPosTan(progress / mMax * mLength, pos, tan);//传入当前的距离，和两个空数组，此方法将该点的坐标和正切值写入数组
            Log.e(TAG, "pos " + pos[0] + " " + pos[1] + " " + "tan " + tan[0] + " " + tan[1]);
        }
        postInvalidate();
    }

    public void setImage(int imageRes) {
        mBitmap = BitmapFactory.decodeResource(getResources(), imageRes);
        int width = mBitmap.getWidth();
        int height = mBitmap.getHeight();
        mRect = new Rect(-width / 2, -height / 2, width / 2, height / 2);
        postInvalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawPath(mPath, mPaint);
        if (mProgress >= 0) {
            canvas.save();//保存当前状态
            canvas.translate(pos[0], pos[1]);//将圆点移动到当前的点
            double atan = Math.atan(tan[1] / tan[0]);//计算正切值
            float degrees = (float) (atan * 180 / Math.PI);//计算角度
            Log.e(TAG, "degrees " + degrees);
            degrees += 90;
            if (tan[0] < 0) {
                degrees += 180;
            }
            canvas.rotate(degrees);//画布旋转
            canvas.drawBitmap(mBitmap, null, mRect, mPaint);//绘制小飞机
            canvas.restore();//取出之前的状态绘制
        }
    }
}