package com.text.rexiufu.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ComposeShader;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.text.rexiufu.R;

public class CreateCircleImageView extends View {

    private Bitmap mBitmap;
    private Paint mBitmapPaint;

    public CreateCircleImageView(Context context) {
        this(context, null);
    }

    public CreateCircleImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mBitmapPaint = new Paint();
        mBitmapPaint.setColor(Color.BLACK);
        mBitmapPaint.setAntiAlias(true);
        mBitmapPaint.setTextAlign(Paint.Align.CENTER);

        //创建位图
        mBitmap = ((BitmapDrawable) getResources().getDrawable(R.mipmap.dilireba)).getBitmap();
        //将图scale成我们想要的大小
        mBitmap = Bitmap.createScaledBitmap(mBitmap, 800, 800, false);

        // 创建位图渲染
        BitmapShader bitmapShader = new BitmapShader(mBitmap, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
        // 创建环形渐变
        RadialGradient radialGradient = new RadialGradient(350, 300, 100,
                Color.TRANSPARENT, Color.WHITE, Shader.TileMode.MIRROR);
        // 创建组合渐变，由于直接按原样绘制就好，所以选择Mode.SRC_OVER
        ComposeShader composeShader = new ComposeShader(bitmapShader, radialGradient,
                PorterDuff.Mode.SRC_OVER);
        // 将组合渐变设置给paint
        mBitmapPaint.setShader(composeShader);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(350, 300, 100, mBitmapPaint);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }
}
