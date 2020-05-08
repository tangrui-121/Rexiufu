package com.text.rexiufu.view;

import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;

import com.text.rexiufu.R;

public class ChangePicActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {

//    色值采用16进制，取值在 0 － 255 之间 ，0（0x00） 即 完全没有 ，255(0xff) 代表满值 ;

    private SeekBar seek_red, seek_green, seek_blue, seek_alpha;
    private ImageView text_img;
    private ColorMatrix mColorMatrix;
    private float mRedFilter = 1;
    private float mGreenFilter = 1;
    private float mBlueFilter = 1;
    private float mAlphaFilter = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changepic);
        initView();
    }

    private void initView() {
        seek_red = findViewById(R.id.seek_red);
        seek_green = findViewById(R.id.seek_green);
        seek_blue = findViewById(R.id.seek_blue);
        seek_alpha = findViewById(R.id.seek_alpha);
        text_img = findViewById(R.id.text_img);

        seek_red.setOnSeekBarChangeListener(this);
        seek_green.setOnSeekBarChangeListener(this);
        seek_blue.setOnSeekBarChangeListener(this);
        seek_alpha.setOnSeekBarChangeListener(this);

        seek_red.setProgress(100);
        seek_green.setProgress(100);
        seek_blue.setProgress(100);
        seek_alpha.setProgress(100);

        setArgb();
    }

    private void setArgb() {
        mColorMatrix = new ColorMatrix(new float[]{
                mRedFilter, 0, 0, 0, 0,
                0, mGreenFilter, 0, 0, 0,
                0, 0, mBlueFilter, 0, 0,
                0, 0, 0, mAlphaFilter, 0,
        });
        text_img.setColorFilter(new ColorMatrixColorFilter(mColorMatrix));
        text_img.postInvalidate();
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        float filter = (float) progress / 100;
        if (seekBar == seek_red) {
            mRedFilter = filter;
        } else if (seekBar == seek_green) {
            mGreenFilter = filter;
        } else if (seekBar == seek_blue) {
            mBlueFilter = filter;
        } else if (seekBar == seek_alpha) {
            mAlphaFilter = filter;
        }
        setArgb();
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

//    private void CreateCircleImageView(){
//        //创建位图
//        Bitmap mBitmap = ((BitmapDrawable) getResources().getDrawable(R.mipmap.dilireba)).getBitmap();
//        //将图scale成我们想要的大小
//        mBitmap = Bitmap.createScaledBitmap(mBitmap, mTotalWidth, mTotalWidth, false);
//
//        // 创建位图渲染
//        BitmapShader bitmapShader = new BitmapShader(mBitmap, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
//        // 将位图渲染设置给paint
//        mBitmapPaint.setShader(bitmapShader);
//    }

    private static final int MUL_COLOR = 0xff00ffff;
    private static final int ADD_COLOR = 0x000000ff;

    public void LightingColorFilter(View view) {
        text_img.setColorFilter(new LightingColorFilter(MUL_COLOR, ADD_COLOR));
    }

    public void ColorFilter(View view) {
        text_img.setColorFilter(new PorterDuffColorFilter(MUL_COLOR, PorterDuff.Mode.DARKEN));//变暗
    }
}