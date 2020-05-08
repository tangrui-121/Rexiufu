package com.text.rexiufu.xiaofeiji;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.text.rexiufu.R;

import androidx.annotation.Nullable;

public class PathView extends ViewGroup {

    public static final String TAG = "PathView";

    private float currentValue = 0;
    PathMeasure measure;
    private float[] pos;//当前飞机的实际位置
    private float[] tan;//当前飞机的tangent值,用于计算图片所需旋转的角度
    private Bitmap mBitmap;//飞机图片
    private Matrix mMatrix;//飞机显示的矩阵

    private Path mPath;//小河路径
    private Paint mPaint;//小河画笔
    private Paint mBackPaint;//背景画笔
    private int mWidth;//屏幕宽，画背景矩形时用到

    private RowingView rowingView;

    public PathView(Context context) {
        this(context, null);
    }

    public PathView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PathView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
        setImage(context);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }

    private void initPaint() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(0x60ffffff);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(100);
        mPaint.setStrokeJoin(Paint.Join.ROUND);

        setWillNotDraw(false);
    }

    public void setImage(Context context) {
        pos = new float[2];
        tan = new float[2];
//        BitmapFactory.Options options = new BitmapFactory.Options();
//        mBitmap = BitmapFactory.decodeResource(getContext().getResources(), R.mipmap.feiji, options);
//        mMatrix = new Matrix();
        rowingView = new RowingView(context);
        rowingView.layout(0, 0, 200, 200);
    }

    public void setPath(Path path) {
        mPath = path;
        measure = new PathMeasure(mPath, false);
    }

    public void setBackPaint(int color, int width) {
        mWidth = width;
        mBackPaint = new Paint(Paint.ANTI_ALIAS_FLAG); //设置无锯齿 也可以使用setAntiAlias(true)
        mBackPaint.setColor(color);//设置画笔颜色
        mBackPaint.setAlpha(200);
        mBackPaint.setStrokeWidth(1.5f);//设置线宽
        mBackPaint.setStyle(Paint.Style.FILL);//设置样式：FILL表示颜色填充整个；STROKE表示空心
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        removeAllViews();
        canvas.drawRoundRect(new RectF(30, 30, mWidth - 30, 720), 20, 20, mBackPaint);
        canvas.drawPath(mPath, mPaint);
        addView(rowingView);

//        if (currentValue > 0) {
//            canvas.save();//保存当前状态
//            measure.getPosTan(measure.getLength() * currentValue + 30, pos, tan);// 获取当前位置的坐标以及趋势
////            mMatrix.reset();// 重置Matrix
//            float degrees = (float) (Math.atan2(tan[1], tan[0]) * 180.0 / Math.PI); // 计算图片旋转角度
////            mMatrix.postRotate(degrees + 90, mBitmap.getWidth() / 2, mBitmap.getHeight() / 2);   // 旋转图片
////            mMatrix.postTranslate(pos[0] - mBitmap.getWidth() / 2, pos[1] - mBitmap.getHeight() / 2);   // 将图片绘制中心调整到与当前点重合
////            canvas.drawBitmap(mBitmap, mMatrix, mPaint);
//            canvas.translate(pos[0], pos[1]);
//            canvas.rotate(degrees + 180);
//
//            addView(rowingView);
//            canvas.restore();//取出之前的状态绘制
//        }
    }

    public void moveFeiji(float value) {
        currentValue = value;// 计算当前的位置在总长度上的比例[0,1]
        if (currentValue >= 1) {
            currentValue = 0;
        }
        postInvalidate();
    }
}